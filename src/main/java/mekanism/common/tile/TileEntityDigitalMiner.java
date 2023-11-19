package mekanism.common.tile;

import io.netty.buffer.ByteBuf;
import mekanism.api.Chunk3D;
import mekanism.api.Coord4D;
import mekanism.api.Range4D;
import mekanism.api.TileNetworkList;
import mekanism.common.HashList;
import mekanism.common.Mekanism;
import mekanism.common.Upgrade;
import mekanism.common.base.*;
import mekanism.common.block.states.BlockStateMachine.MachineType;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.chunkloading.IChunkLoader;
import mekanism.common.config.MekanismConfig;
import mekanism.common.content.miner.MItemStackFilter;
import mekanism.common.content.miner.MOreDictFilter;
import mekanism.common.content.miner.MinerFilter;
import mekanism.common.content.miner.ThreadMinerSearch;
import mekanism.common.content.miner.ThreadMinerSearch.State;
import mekanism.common.content.transporter.InvStack;
import mekanism.common.content.transporter.TransitRequest;
import mekanism.common.content.transporter.TransitRequest.TransitResponse;
import mekanism.common.inventory.container.ContainerFilter;
import mekanism.common.inventory.container.ContainerNull;
import mekanism.common.network.PacketTileEntity.TileEntityMessage;
import mekanism.common.tile.component.TileComponentChunkLoader;
import mekanism.common.tile.component.TileComponentSecurity;
import mekanism.common.tile.component.TileComponentUpgrade;
import mekanism.common.tile.prefab.TileEntityElectricBlock;
import mekanism.common.util.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.Constants.WorldEvents;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TileEntityDigitalMiner extends TileEntityElectricBlock implements IUpgradeTile, IRedstoneControl, IActiveState, ISustainedData, IChunkLoader, IAdvancedBoundingBlock {

    private static final int[] INV_SLOTS = IntStream.range(0, 28).toArray();
    public final double BASE_ENERGY_USAGE = MachineType.DIGITAL_MINER.getUsage();
    public Map<Chunk3D, BitSet> oresToMine = new HashMap<>();
    public Map<Integer, MinerFilter> replaceMap = new HashMap<>();
    public HashList<MinerFilter> filters = new HashList<>();
    public ThreadMinerSearch searcher = new ThreadMinerSearch(this);
    public double energyUsage = BASE_ENERGY_USAGE;
    public boolean inverse;
    public int minY = 0;
    public int maxY = 60;
    public boolean doEject = false;
    public boolean doPull = false;
    public ItemStack missingStack = ItemStack.EMPTY;
    public int BASE_DELAY = 80;
    public int delay;
    public int delayLength = BASE_DELAY;
    public int clientToMine;
    public boolean isActive;
    public boolean clientActive;
    public boolean silkTouch;
    public boolean running;
    public double prevEnergy;
    public int delayTicks;
    public boolean initCalc = false;
    public int numPowering;
    public boolean clientRendering = false;
    /**
     * This machine's current RedstoneControl type.
     */
    public RedstoneControl controlType = RedstoneControl.DISABLED;
    public TileComponentUpgrade upgradeComponent = new TileComponentUpgrade(this, INV_SLOTS.length);
    public TileComponentSecurity securityComponent = new TileComponentSecurity(this);
    public TileComponentChunkLoader chunkLoaderComponent = new TileComponentChunkLoader(this);
    public String[] methods = {"setRadius", "setMin", "setMax", "addFilter", "removeFilter", "addOreFilter", "removeOreFilter", "reset", "start", "stop", "getToMine"};
    private int radius;
    private Set<ChunkPos> chunkSet;

    public TileEntityDigitalMiner() {
        super("DigitalMiner", MachineType.DIGITAL_MINER.getStorage());
        inventory = NonNullList.withSize(INV_SLOTS.length + 1, ItemStack.EMPTY);
        radius = 10;
        upgradeComponent.setSupported(Upgrade.ANCHOR);
        upgradeComponent.setSupported(Upgrade.STONE_GENERATOR);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (getActive()) {
            for (EntityPlayer player : new HashSet<>(playersUsing)) {
                if (player.openContainer instanceof ContainerNull || player.openContainer instanceof ContainerFilter) {
                    player.closeScreen();
                }
            }
        }

        if (!world.isRemote) {
            if (!initCalc) {
                if (searcher.state == State.FINISHED) {
                    boolean prevRunning = running;
                    reset();
                    start();
                    running = prevRunning;
                }
                initCalc = true;
            }

            ChargeUtils.discharge(27, this);

            if (MekanismUtils.canFunction(this) && running && getEnergy() >= getPerTick() && searcher.state == State.FINISHED && !oresToMine.isEmpty()) {
                setActive(true);
                if (delay > 0) {
                    delay--;
                }
                setEnergy(getEnergy() - getPerTick());
                if (delay == 0) {
                    boolean did = false;
                    for (Iterator<Chunk3D> it = oresToMine.keySet().iterator(); it.hasNext(); ) {
                        Chunk3D chunk = it.next();
                        BitSet set = oresToMine.get(chunk);
                        int next = 0;
                        while (!did) {
                            int index = set.nextSetBit(next);
                            Coord4D coord = getCoordFromIndex(index);
                            if (index == -1) {
                                it.remove();
                                break;
                            }

                            if (!coord.exists(world)) {
                                set.clear(index);
                                if (set.cardinality() == 0) {
                                    it.remove();
                                    break;
                                }
                                next = index + 1;
                                continue;
                            }

                            IBlockState state = coord.getBlockState(world);
                            Block block = state.getBlock();
                            int meta = block.getMetaFromState(state);

                            if (coord.isAirBlock(world)) {
                                set.clear(index);
                                if (set.cardinality() == 0) {
                                    it.remove();
                                    break;
                                }
                                next = index + 1;
                                continue;
                            }

                            boolean hasFilter = false;
                            ItemStack is = new ItemStack(block, 1, meta);
                            for (MinerFilter filter : filters) {
                                if (filter.canFilter(is)) {
                                    hasFilter = true;
                                    break;
                                }
                            }

                            if (inverse == hasFilter || !canMine(coord)) {
                                set.clear(index);
                                if (set.cardinality() == 0) {
                                    it.remove();
                                    break;
                                }
                                next = index + 1;
                                continue;
                            }

                            List<ItemStack> drops = MinerUtils.getDrops(world, coord, silkTouch, this.pos);
                            if (canInsert(drops) && setReplace(coord, index)) {
                                did = true;
                                add(drops);
                                set.clear(index);
                                if (set.cardinality() == 0) {
                                    it.remove();
                                }
                                world.playEvent(WorldEvents.BREAK_BLOCK_EFFECTS, coord.getPos(), Block.getStateId(state));
                                missingStack = ItemStack.EMPTY;
                            }
                            break;
                        }
                    }
                    delay = getDelay();
                }
            } else if (prevEnergy >= getEnergy()) {
                setActive(false);
            }

            TransitRequest ejectMap = getEjectItemMap();
            if (doEject && delayTicks == 0 && !ejectMap.isEmpty()) {
                TileEntity ejectInv = getEjectInv();
                TileEntity ejectTile = getEjectTile();
                if (ejectInv != null && ejectTile != null) {
                    ILogisticalTransporter capability = CapabilityUtils.getCapability(ejectInv, Capabilities.LOGISTICAL_TRANSPORTER_CAPABILITY, facing.getOpposite());
                    TransitResponse response;
                    if (capability == null) {
                        response = InventoryUtils.putStackInInventory(ejectInv, ejectMap, facing.getOpposite(), false);
                    } else {
                        response = TransporterUtils.insert(ejectTile, capability, ejectMap, null, true, 0);
                    }
                    if (!response.isEmpty()) {
                        response.getInvStack(ejectTile, facing.getOpposite()).use();
                    }
                    delayTicks = 10;
                }
            } else if (delayTicks > 0) {
                delayTicks--;
            }

            if (playersUsing.size() > 0) {
                for (EntityPlayer player : playersUsing) {
                    Mekanism.packetHandler.sendTo(new TileEntityMessage(this, getSmallPacket(new TileNetworkList())), (EntityPlayerMP) player);
                }
            }
            prevEnergy = getEnergy();
        }
    }

    public double getPerTick() {
        double ret = energyUsage;
        if (silkTouch) {
            ret *= MekanismConfig.current().general.minerSilkMultiplier.val();
        }
        int baseRad = Math.max(radius - 10, 0);
        ret *= 1 + ((float) baseRad / 22F);
        int baseHeight = Math.max(maxY - minY - 60, 0);
        ret *= 1 + ((float) baseHeight / 195F);
        return ret;
    }

    public int getDelay() {
        return delayLength;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int newRadius) {
        boolean changed = radius != newRadius;
        radius = newRadius;
        // If the radius changed and we're on the server, go ahead and refresh
        // the chunk set
        if (changed && hasWorld() && world.isRemote) {
            chunkSet = null;
            getChunkSet();
        }
    }

    /*
     * returns false if unsuccessful
     */
    public boolean setReplace(Coord4D obj, int index) {
        ItemStack stack = getReplace(index);
        BlockPos pos = obj.getPos();
        EntityPlayer fakePlayer = Objects.requireNonNull(Mekanism.proxy.getDummyPlayer((WorldServer) world, this.pos).get());

        //if its a shulker box, remove it TE so it can't drop itself in breakBlock - we've already captured its itemblock
        TileEntity te = world.getTileEntity(pos);
        TileEntityShulkerBox tileEntityShulkerBox = null;
        if (te instanceof TileEntityShulkerBox) {
            tileEntityShulkerBox = (TileEntityShulkerBox) te;
            world.removeTileEntity(pos);
        }

        if (!stack.isEmpty()) {
            world.setBlockState(pos, StackUtils.getStateForPlacement(stack, world, pos, fakePlayer), 3);
            IBlockState s = obj.getBlockState(world);
            if (s.getBlock() instanceof BlockBush && !((BlockBush) s.getBlock()).canBlockStay(world, pos, s)) {
                s.getBlock().dropBlockAsItem(world, pos, s, 1);
                world.setBlockToAir(pos);
            }
            return true;
        } else {
            MinerFilter filter = replaceMap.get(index);
            if (filter == null || filter.replaceStack.isEmpty() || !filter.requireStack) {
                world.setBlockToAir(pos);
                return true;
            }
            missingStack = filter.replaceStack;

            // something failed, so put that thing back where it came from
            if (tileEntityShulkerBox != null) {
                tileEntityShulkerBox.validate();
                world.setTileEntity(pos, tileEntityShulkerBox);
            }
            return false;
        }
    }

    private boolean canMine(Coord4D coord) {
        IBlockState state = coord.getBlockState(world);
        //Check if the block is breakable, to avoid blocks like bedrock being being mined.
        if (state.getBlockHardness(world, coord.getPos()) < 0) {
            return false;
        }

        EntityPlayer dummy = Objects.requireNonNull(Mekanism.proxy.getDummyPlayer((WorldServer) world, pos).get());
        BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(world, coord.getPos(), state, dummy);
        MinecraftForge.EVENT_BUS.post(event);
        return !event.isCanceled();
    }

    public ItemStack getReplace(int index) {
        MinerFilter filter = replaceMap.get(index);
        if (filter == null || filter.replaceStack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        for (int i = 0; i < 27; i++) {
            ItemStack stack = inventory.get(i);
            if (!stack.isEmpty() && stack.isItemEqual(filter.replaceStack)) {
                stack.shrink(1);
                return StackUtils.size(filter.replaceStack, 1);
            }
        }

        Item replaceStackItem = filter.replaceStack.getItem();

        if (replaceStackItem == Item.getByNameOrId("minecraft:cobblestone") || replaceStackItem == Item.getByNameOrId("minecraft:stone")){
            if (upgradeComponent.getUpgrades(Upgrade.STONE_GENERATOR) > 0){
                return new ItemStack(replaceStackItem);
            }
        }

        if (doPull && getPullInv() != null) {
            InvStack stack = InventoryUtils.takeDefinedItem(getPullInv(), EnumFacing.UP, filter.replaceStack.copy(), 1, 1);
            if (stack != null) {
                stack.use();
                return StackUtils.size(filter.replaceStack, 1);
            }
        }
        return ItemStack.EMPTY;
    }

    public NonNullList<ItemStack> copy(NonNullList<ItemStack> stacks) {
        NonNullList<ItemStack> toReturn = NonNullList.withSize(stacks.size(), ItemStack.EMPTY);
        for (int i = 0; i < stacks.size(); i++) {
            toReturn.set(i, !stacks.get(i).isEmpty() ? stacks.get(i).copy() : ItemStack.EMPTY);
        }
        return toReturn;
    }

    public TransitRequest getEjectItemMap() {
        TransitRequest request = new TransitRequest();
        for (int i = 27 - 1; i >= 0; i--) {
            ItemStack stack = inventory.get(i);
            if (!stack.isEmpty() && !isReplaceStack(stack)) {
                request.addItem(stack, i);
            }
        }
        return request;
    }

    public boolean canInsert(List<ItemStack> stacks) {
        if (stacks.isEmpty()) {
            return true;
        }
        NonNullList<ItemStack> testInv = copy(inventory);
        int added = 0;

        stacks:
        for (ItemStack stack : stacks) {
            stack = stack.copy();
            if (stack.isEmpty()) {
                continue;
            }
            for (int i = 0; i < 27; i++) {
                ItemStack existingStack = testInv.get(i);
                if (existingStack.isEmpty()) {
                    testInv.set(i, stack);
                    added++;
                    continue stacks;
                } else if (ItemHandlerHelper.canItemStacksStack(existingStack, stack) && existingStack.getCount() + stack.getCount() <= stack.getMaxStackSize()) {
                    existingStack.grow(stack.getCount());
                    added++;
                    continue stacks;
                }
            }
        }
        return added == stacks.size();

    }

    public TileEntity getPullInv() {
        return Coord4D.get(this).translate(0, 2, 0).getTileEntity(world);
    }

    public TileEntity getEjectInv() {
        final EnumFacing side = facing.getOpposite();
        final BlockPos pos = getPos().up().offset(side, 2);
        if (world.isBlockLoaded(pos)) {
            return world.getTileEntity(pos);
        }
        return null;
    }

    public void add(List<ItemStack> stacks) {
        if (stacks.isEmpty()) {
            return;
        }

        for (ItemStack stack : stacks) {
            for (int i = 0; i < 27; i++) {
                ItemStack currentStack = inventory.get(i);
                if (currentStack.isEmpty()) {
                    inventory.set(i, stack);
                    break;
                } else if (ItemHandlerHelper.canItemStacksStack(currentStack, stack) && currentStack.getCount() + stack.getCount() <= stack.getMaxStackSize()) {
                    currentStack.grow(stack.getCount());
                    break;
                }
            }
        }
    }

    public void start() {
        if (searcher.state == State.IDLE) {
            BlockPos startingPos = getStartingCoord().getPos();
            searcher.setChunkCache(new ChunkCache(getWorld(), startingPos, startingPos.add(getDiameter(), maxY - minY + 1, getDiameter()), 0));
            searcher.start();
        }
        running = true;
        MekanismUtils.saveChunk(this);
    }

    public void stop() {
        if (searcher.state == State.SEARCHING) {
            searcher.interrupt();
            reset();
            return;
        } else if (searcher.state == State.FINISHED) {
            running = false;
        }
        MekanismUtils.saveChunk(this);
    }

    public void reset() {
        searcher = new ThreadMinerSearch(this);
        running = false;
        oresToMine.clear();
        replaceMap.clear();
        missingStack = ItemStack.EMPTY;
        setActive(false);
        MekanismUtils.saveChunk(this);
    }

    public boolean isReplaceStack(ItemStack stack) {
        for (MinerFilter filter : filters) {
            if (!filter.replaceStack.isEmpty() && filter.replaceStack.isItemEqual(stack)) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        int size = 0;
        for (Chunk3D chunk : oresToMine.keySet()) {
            size += oresToMine.get(chunk).cardinality();
        }
        return size;
    }

    @Override
    public void openInventory(@Nonnull EntityPlayer player) {
        super.openInventory(player);
        if (!world.isRemote) {
            Mekanism.packetHandler.sendTo(new TileEntityMessage(this), (EntityPlayerMP) player);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTags) {
        super.readFromNBT(nbtTags);
        clientActive = isActive = nbtTags.getBoolean("isActive");
        running = nbtTags.getBoolean("running");
        delay = nbtTags.getInteger("delay");
        numPowering = nbtTags.getInteger("numPowering");
        searcher.state = State.values()[nbtTags.getInteger("state")];
        controlType = RedstoneControl.values()[nbtTags.getInteger("controlType")];
        setConfigurationData(nbtTags);
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTags) {
        super.writeToNBT(nbtTags);
        if (searcher.state == State.SEARCHING) {
            reset();
        }
        nbtTags.setBoolean("isActive", isActive);
        nbtTags.setBoolean("running", running);
        nbtTags.setInteger("delay", delay);
        nbtTags.setInteger("numPowering", numPowering);
        nbtTags.setInteger("state", searcher.state.ordinal());
        nbtTags.setInteger("controlType", controlType.ordinal());
        return getConfigurationData(nbtTags);
    }

    private void readBasicData(ByteBuf dataStream) {
        setRadius(dataStream.readInt());//client allowed to use whatever server sends
        minY = dataStream.readInt();
        maxY = dataStream.readInt();
        doEject = dataStream.readBoolean();
        doPull = dataStream.readBoolean();
        clientActive = dataStream.readBoolean();
        running = dataStream.readBoolean();
        silkTouch = dataStream.readBoolean();
        numPowering = dataStream.readInt();
        searcher.state = State.values()[dataStream.readInt()];
        clientToMine = dataStream.readInt();
        controlType = RedstoneControl.values()[dataStream.readInt()];
        inverse = dataStream.readBoolean();
        if (dataStream.readBoolean()) {
            missingStack = new ItemStack(Item.getItemById(dataStream.readInt()), 1, dataStream.readInt());
        } else {
            missingStack = ItemStack.EMPTY;
        }
    }

    @Override
    public void handlePacketData(ByteBuf dataStream) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            int type = dataStream.readInt();
            switch (type) {
                case 0:
                    doEject = !doEject;
                    break;
                case 1:
                    doPull = !doPull;
                    break;
                case 3:
                    start();
                    break;
                case 4:
                    stop();
                    break;
                case 5:
                    reset();
                    break;
                case 6:
                    setRadius(Math.min(dataStream.readInt(), MekanismConfig.current().general.digitalMinerMaxRadius.val()));
                    break;
                case 7:
                    minY = dataStream.readInt();
                    break;
                case 8:
                    maxY = dataStream.readInt();
                    break;
                case 9:
                    silkTouch = !silkTouch;
                    break;
                case 10:
                    inverse = !inverse;
                    break;
                case 11: {
                    // Move filter up
                    int filterIndex = dataStream.readInt();
                    filters.swap(filterIndex, filterIndex - 1);
                    for (EntityPlayer player : playersUsing) {
                        openInventory(player);
                    }
                    break;
                }
                case 12: {
                    // Move filter down
                    int filterIndex = dataStream.readInt();
                    filters.swap(filterIndex, filterIndex + 1);
                    for (EntityPlayer player : playersUsing) {
                        openInventory(player);
                    }
                    break;
                }
            }

            MekanismUtils.saveChunk(this);
            for (EntityPlayer player : playersUsing) {
                Mekanism.packetHandler.sendTo(new TileEntityMessage(this, getGenericPacket(new TileNetworkList())), (EntityPlayerMP) player);
            }
            return;
        }

        super.handlePacketData(dataStream);

        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            int type = dataStream.readInt();
            if (type == 0) {
                readBasicData(dataStream);
                filters.clear();
                int amount = dataStream.readInt();
                for (int i = 0; i < amount; i++) {
                    filters.add(MinerFilter.readFromPacket(dataStream));
                }
            } else if (type == 1) {
                readBasicData(dataStream);
            } else if (type == 2) {
                filters.clear();
                int amount = dataStream.readInt();
                for (int i = 0; i < amount; i++) {
                    filters.add(MinerFilter.readFromPacket(dataStream));
                }
            } else if (type == 3) {
                clientActive = dataStream.readBoolean();
                running = dataStream.readBoolean();
                clientToMine = dataStream.readInt();
                if (dataStream.readBoolean()) {
                    missingStack = new ItemStack(Item.getItemById(dataStream.readInt()), 1, dataStream.readInt());
                } else {
                    missingStack = ItemStack.EMPTY;
                }
            }
            if (clientActive != isActive) {
                isActive = clientActive;
                MekanismUtils.updateBlock(world, getPos());
            }
        }
    }

    private void addBasicData(TileNetworkList data) {
        data.add(radius);
        data.add(minY);
        data.add(maxY);
        data.add(doEject);
        data.add(doPull);
        data.add(isActive);
        data.add(running);
        data.add(silkTouch);
        data.add(numPowering);
        data.add(searcher.state.ordinal());

        if (searcher.state == State.SEARCHING) {
            data.add(searcher.found);
        } else {
            data.add(getSize());
        }

        data.add(controlType.ordinal());
        data.add(inverse);
        if (!missingStack.isEmpty()) {
            data.add(true);
            data.add(MekanismUtils.getID(missingStack));
            data.add(missingStack.getItemDamage());
        } else {
            data.add(false);
        }
    }

    @Override
    public TileNetworkList getNetworkedData(TileNetworkList data) {
        super.getNetworkedData(data);
        data.add(0);
        addBasicData(data);
        data.add(filters.size());
        for (MinerFilter filter : filters) {
            filter.write(data);
        }
        return data;
    }

    public TileNetworkList getSmallPacket(TileNetworkList data) {
        super.getNetworkedData(data);

        data.add(3);

        data.add(isActive);
        data.add(running);

        if (searcher.state == State.SEARCHING) {
            data.add(searcher.found);
        } else {
            data.add(getSize());
        }
        if (!missingStack.isEmpty()) {
            data.add(true);
            data.add(MekanismUtils.getID(missingStack));
            data.add(missingStack.getItemDamage());
        } else {
            data.add(false);
        }
        return data;
    }

    public TileNetworkList getGenericPacket(TileNetworkList data) {
        super.getNetworkedData(data);
        data.add(1);
        addBasicData(data);
        return data;
    }

    public TileNetworkList getFilterPacket(TileNetworkList data) {
        super.getNetworkedData(data);
        data.add(2);
        data.add(filters.size());
        for (MinerFilter filter : filters) {
            filter.write(data);
        }
        return data;
    }

    public int getTotalSize() {
        return getDiameter() * getDiameter() * (maxY - minY + 1);
    }

    public int getDiameter() {
        return (radius * 2) + 1;
    }

    public Coord4D getStartingCoord() {
        return new Coord4D(getPos().getX() - radius, minY, getPos().getZ() - radius, world.provider.getDimension());
    }

    public Coord4D getCoordFromIndex(int index) {
        int diameter = getDiameter();
        Coord4D start = getStartingCoord();
        int x = start.x + index % diameter;
        int y = start.y + (index / diameter / diameter);
        int z = start.z + (index / diameter) % diameter;
        return new Coord4D(x, y, z, world.provider.getDimension());
    }

    @Override
    public boolean isPowered() {
        return redstone || numPowering > 0;
    }

    @Override
    public boolean canPulse() {
        return false;
    }

    @Override
    public RedstoneControl getControlType() {
        return controlType;
    }

    @Override
    public void setControlType(RedstoneControl type) {
        controlType = type;
        MekanismUtils.saveChunk(this);
    }

    @Override
    public TileComponentUpgrade getComponent() {
        return upgradeComponent;
    }

    @Override
    public boolean getActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
        if (clientActive != active) {
            Mekanism.packetHandler.sendUpdatePacket(this);
            clientActive = active;
        }
    }

    @Override
    public boolean renderUpdate() {
        return false;
    }

    @Override
    public boolean lightUpdate() {
        return false;
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    @Override
    public void onPlace() {
        for (int x = -1; x <= +1; x++) {
            for (int y = 0; y <= +1; y++) {
                for (int z = -1; z <= +1; z++) {
                    if (x == 0 && y == 0 && z == 0) {
                        continue;
                    }
                    BlockPos pos1 = getPos().add(x, y, z);
                    MekanismUtils.makeAdvancedBoundingBlock(world, pos1, Coord4D.get(this));
                    world.notifyNeighborsOfStateChange(pos1, getBlockType(), true);
                }
            }
        }
    }

    @Override
    public boolean canSetFacing(@Nonnull EnumFacing facing) {
        return facing != EnumFacing.DOWN && facing != EnumFacing.UP;
    }

    @Override
    public void onBreak() {
        for (int x = -1; x <= +1; x++) {
            for (int y = 0; y <= +1; y++) {
                for (int z = -1; z <= +1; z++) {
                    world.setBlockToAir(getPos().add(x, y, z));
                }
            }
        }
    }

    @Nonnull
    @Override
    public int[] getSlotsForFace(@Nonnull EnumFacing side) {
        //Allow for automation via the top (as that is where it can auto pull from)
        return side == EnumFacing.UP || side == facing.getOpposite() ? INV_SLOTS : InventoryUtils.EMPTY;
    }

    @Override
    public boolean isItemValidForSlot(int slotID, @Nonnull ItemStack stack) {
        return slotID != 27 || ChargeUtils.canBeDischarged(stack);
    }

    public TileEntity getEjectTile() {
        final EnumFacing side = facing.getOpposite();
        final BlockPos pos = getPos().up().offset(side);
        if (world.isBlockLoaded(pos)) {
            return world.getTileEntity(pos);
        }
        return null;
    }

    @Override
    public boolean canInsertItem(int slotID, @Nonnull ItemStack itemstack, @Nonnull EnumFacing side) {
        if (side == EnumFacing.UP) {
            if (slotID == 27) {
                return ChargeUtils.canBeDischarged(itemstack);
            }
            return !itemstack.isEmpty() && isReplaceStack(itemstack);
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int slotID, @Nonnull ItemStack itemstack, @Nonnull EnumFacing side) {
        if (side == facing.getOpposite()) {
            if (slotID == 27) {
                return !ChargeUtils.canBeDischarged(itemstack);
            }
            return itemstack.isEmpty() || !isReplaceStack(itemstack);
        }
        return false;
    }

    @Override
    public void onPower() {
        numPowering++;
    }

    @Override
    public void onNoPower() {
        numPowering--;
    }

    @Override
    public String[] getMethods() {
        return methods;
    }

    @Override
    public Object[] invoke(int method, Object[] arguments) throws NoSuchMethodException {
        if (method == 0) {
            if (arguments.length != 1 || !(arguments[0] instanceof Double)) {
                return new Object[]{"Invalid parameters."};
            }
            setRadius(Math.min(((Double) arguments[0]).intValue(), MekanismConfig.current().general.digitalMinerMaxRadius.val()));
        } else if (method == 1) {
            if (arguments.length != 1 || !(arguments[0] instanceof Double)) {
                return new Object[]{"Invalid parameters."};
            }
            minY = ((Double) arguments[0]).intValue();
        } else if (method == 2) {
            if (arguments.length != 1 || !(arguments[0] instanceof Double)) {
                return new Object[]{"Invalid parameters."};
            }
            maxY = ((Double) arguments[0]).intValue();
        } else if (method == 3) {
            if (arguments.length < 1 || !(arguments[0] instanceof Double)) {
                return new Object[]{"Invalid parameters."};
            }
            int id = ((Double) arguments[0]).intValue();
            int meta = 0;
            if (arguments.length > 1) {
                if (arguments[1] instanceof Double) {
                    meta = ((Double) arguments[1]).intValue();
                }
            }
            filters.add(new MItemStackFilter(new ItemStack(Item.getItemById(id), 1, meta)));
            return new Object[]{"Added filter."};
        } else if (method == 4) {
            if (arguments.length < 1 || !(arguments[0] instanceof Double)) {
                return new Object[]{"Invalid parameters."};
            }
            int id = ((Double) arguments[0]).intValue();
            Iterator<MinerFilter> iter = filters.iterator();
            while (iter.hasNext()) {
                MinerFilter filter = iter.next();
                if (filter instanceof MItemStackFilter) {
                    if (MekanismUtils.getID(((MItemStackFilter) filter).getItemStack()) == id) {
                        iter.remove();
                        return new Object[]{"Removed filter."};
                    }
                }
            }
            return new Object[]{"Couldn't find filter."};
        } else if (method == 5) {
            if (arguments.length < 1 || !(arguments[0] instanceof String)) {
                return new Object[]{"Invalid parameters."};
            }
            String ore = (String) arguments[0];
            MOreDictFilter filter = new MOreDictFilter();
            filter.setOreDictName(ore);
            filters.add(filter);
            return new Object[]{"Added filter."};
        } else if (method == 6) {
            if (arguments.length < 1 || !(arguments[0] instanceof String)) {
                return new Object[]{"Invalid parameters."};
            }
            String ore = (String) arguments[0];
            Iterator<MinerFilter> iter = filters.iterator();
            while (iter.hasNext()) {
                MinerFilter filter = iter.next();
                if (filter instanceof MOreDictFilter) {
                    if (((MOreDictFilter) filter).getOreDictName().equals(ore)) {
                        iter.remove();
                        return new Object[]{"Removed filter."};
                    }
                }
            }
            return new Object[]{"Couldn't find filter."};
        } else if (method == 7) {
            reset();
            return new Object[]{"Reset miner."};
        } else if (method == 8) {
            start();
            return new Object[]{"Started miner."};
        } else if (method == 9) {
            stop();
            return new Object[]{"Stopped miner."};
        } else if (method == 10) {
            return new Object[]{searcher != null ? searcher.found : 0};
        }
        for (EntityPlayer player : playersUsing) {
            Mekanism.packetHandler.sendTo(new TileEntityMessage(this, getGenericPacket(new TileNetworkList())), (EntityPlayerMP) player);
        }
        return null;
    }

    @Override
    public NBTTagCompound getConfigurationData(NBTTagCompound nbtTags) {
        nbtTags.setInteger("radius", radius);
        nbtTags.setInteger("minY", minY);
        nbtTags.setInteger("maxY", maxY);
        nbtTags.setBoolean("doEject", doEject);
        nbtTags.setBoolean("doPull", doPull);
        nbtTags.setBoolean("silkTouch", silkTouch);
        nbtTags.setBoolean("inverse", inverse);
        NBTTagList filterTags = new NBTTagList();
        for (MinerFilter filter : filters) {
            filterTags.appendTag(filter.write(new NBTTagCompound()));
        }
        if (filterTags.tagCount() != 0) {
            nbtTags.setTag("filters", filterTags);
        }
        return nbtTags;
    }

    @Override
    public void setConfigurationData(NBTTagCompound nbtTags) {
        setRadius(Math.min(nbtTags.getInteger("radius"), MekanismConfig.current().general.digitalMinerMaxRadius.val()));
        minY = nbtTags.getInteger("minY");
        maxY = nbtTags.getInteger("maxY");
        doEject = nbtTags.getBoolean("doEject");
        doPull = nbtTags.getBoolean("doPull");
        silkTouch = nbtTags.getBoolean("silkTouch");
        inverse = nbtTags.getBoolean("inverse");
        if (nbtTags.hasKey("filters")) {
            NBTTagList tagList = nbtTags.getTagList("filters", NBT.TAG_COMPOUND);
            for (int i = 0; i < tagList.tagCount(); i++) {
                filters.add(MinerFilter.readFromNBT(tagList.getCompoundTagAt(i)));
            }
        }
    }

    @Override
    public String getDataType() {
        return getBlockType().getTranslationKey() + "." + fullName + ".name";
    }

    @Override
    public void writeSustainedData(ItemStack itemStack) {
        ItemDataUtils.setBoolean(itemStack, "hasMinerConfig", true);

        ItemDataUtils.setInt(itemStack, "radius", radius);
        ItemDataUtils.setInt(itemStack, "minY", minY);
        ItemDataUtils.setInt(itemStack, "maxY", maxY);
        ItemDataUtils.setBoolean(itemStack, "doEject", doEject);
        ItemDataUtils.setBoolean(itemStack, "doPull", doPull);
        ItemDataUtils.setBoolean(itemStack, "silkTouch", silkTouch);
        ItemDataUtils.setBoolean(itemStack, "inverse", inverse);

        NBTTagList filterTags = new NBTTagList();

        for (MinerFilter filter : filters) {
            filterTags.appendTag(filter.write(new NBTTagCompound()));
        }

        if (filterTags.tagCount() != 0) {
            ItemDataUtils.setList(itemStack, "filters", filterTags);
        }
    }

    @Override
    public void readSustainedData(ItemStack itemStack) {
        if (ItemDataUtils.hasData(itemStack, "hasMinerConfig")) {
            setRadius(Math.min(ItemDataUtils.getInt(itemStack, "radius"), MekanismConfig.current().general.digitalMinerMaxRadius.val()));
            minY = ItemDataUtils.getInt(itemStack, "minY");
            maxY = ItemDataUtils.getInt(itemStack, "maxY");
            doEject = ItemDataUtils.getBoolean(itemStack, "doEject");
            doPull = ItemDataUtils.getBoolean(itemStack, "doPull");
            silkTouch = ItemDataUtils.getBoolean(itemStack, "silkTouch");
            inverse = ItemDataUtils.getBoolean(itemStack, "inverse");

            if (ItemDataUtils.hasData(itemStack, "filters")) {
                NBTTagList tagList = ItemDataUtils.getList(itemStack, "filters");
                for (int i = 0; i < tagList.tagCount(); i++) {
                    filters.add(MinerFilter.readFromNBT(tagList.getCompoundTagAt(i)));
                }
            }
        }
    }

    @Override
    public void recalculateUpgradables(Upgrade upgrade) {
        super.recalculateUpgradables(upgrade);
        switch (upgrade) {
            case SPEED:
                delayLength = MekanismUtils.getTicks(this, BASE_DELAY);
            case ENERGY:
                energyUsage = MekanismUtils.getEnergyPerTick(this, BASE_ENERGY_USAGE);
                maxEnergy = MekanismUtils.getMaxEnergy(this, BASE_MAX_ENERGY);
                setEnergy(Math.min(getMaxEnergy(), getEnergy()));
            default:
                break;
        }
    }

    @Override
    public boolean canBoundReceiveEnergy(BlockPos coord, EnumFacing side) {
        EnumFacing left = MekanismUtils.getLeft(facing);
        EnumFacing right = MekanismUtils.getRight(facing);
        if (coord.equals(getPos().offset(left))) {
            return side == left;
        } else if (coord.equals(getPos().offset(right))) {
            return side == right;
        }
        return false;
    }

    @Override
    public boolean sideIsConsumer(EnumFacing side) {
        return side == MekanismUtils.getLeft(facing) || side == MekanismUtils.getRight(facing) || side == EnumFacing.DOWN;
    }

    @Override
    public TileComponentSecurity getSecurity() {
        return securityComponent;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing side) {
        return capability == Capabilities.CONFIG_CARD_CAPABILITY || capability == Capabilities.SPECIAL_CONFIG_DATA_CAPABILITY || super.hasCapability(capability, side);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing side) {
        if (capability == Capabilities.CONFIG_CARD_CAPABILITY || capability == Capabilities.SPECIAL_CONFIG_DATA_CAPABILITY) {
            return (T) this;
        }
        return super.getCapability(capability, side);
    }

    @Override
    public boolean hasOffsetCapability(@Nonnull Capability<?> capability, EnumFacing side, @Nonnull Vec3i offset) {
        if (isOffsetCapabilityDisabled(capability, side, offset)) {
            return false;
        }
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        } else if (isStrictEnergy(capability) || capability == CapabilityEnergy.ENERGY || isTesla(capability, side)) {
            return true;
        }
        return hasCapability(capability, side);
    }

    @Override
    public <T> T getOffsetCapability(@Nonnull Capability<T> capability, EnumFacing side, @Nonnull Vec3i offset) {
        if (isOffsetCapabilityDisabled(capability, side, offset)) {
            return null;
        } else if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(getItemHandler(side));
        } else if (isStrictEnergy(capability)) {
            return (T) this;
        } else if (isTesla(capability, side)) {
            return (T) getTeslaEnergyWrapper(side);
        } else if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(getForgeEnergyWrapper(side));
        }
        return getCapability(capability, side);
    }

    @Override
    public boolean isOffsetCapabilityDisabled(@Nonnull Capability<?> capability, EnumFacing side, @Nonnull Vec3i offset) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            //Input
            if (offset.equals(new Vec3i(0, 1, 0))) {
                //If input then disable if wrong face of input
                return side != EnumFacing.UP;
            }
            //Output
            EnumFacing back = facing.getOpposite();
            if (offset.equals(new Vec3i(back.getXOffset(), 1, back.getZOffset()))) {
                //If output then disable if wrong face of output
                return side != back;
            }
            return true;
        }
        if (isStrictEnergy(capability) || capability == CapabilityEnergy.ENERGY || isTesla(capability, side)) {
            if (offset.equals(Vec3i.NULL_VECTOR)) {
                //Disable if it is the bottom port but wrong side of it
                return side != EnumFacing.DOWN;
            }
            EnumFacing left = MekanismUtils.getLeft(facing);
            EnumFacing right = MekanismUtils.getRight(facing);
            if (offset.equals(new Vec3i(left.getXOffset(), 0, left.getZOffset()))) {
                //Disable if left power port but wrong side of the port
                return side != left;
            } else if (offset.equals(new Vec3i(right.getXOffset(), 0, right.getZOffset()))) {
                //Disable if right power port but wrong side of the port
                return side != right;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isCapabilityDisabled(@Nonnull Capability<?> capability, EnumFacing side) {
        //Return some capabilities as disabled, and handle them with offset capabilities instead
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        } else if (isStrictEnergy(capability) || capability == CapabilityEnergy.ENERGY || isTesla(capability, side)) {
            return true;
        }
        return super.isCapabilityDisabled(capability, side);
    }

    @Override
    public TileComponentChunkLoader getChunkLoader() {
        return chunkLoaderComponent;
    }

    @Override
    public Set<ChunkPos> getChunkSet() {
        if (chunkSet == null) {
            chunkSet = new Range4D(Coord4D.get(this)).expandFromCenter(radius).getIntersectingChunks().stream().map(Chunk3D::getPos).collect(Collectors.toSet());
        }
        return chunkSet;
    }

    @Nonnull
    @Override
    public BlockFaceShape getOffsetBlockFaceShape(@Nonnull EnumFacing face, @Nonnull Vec3i offset) {
        if (offset.equals(new Vec3i(0, 1, 0))) {
            return BlockFaceShape.SOLID;
        }
        EnumFacing back = facing.getOpposite();
        if (offset.equals(new Vec3i(back.getXOffset(), 1, back.getZOffset()))) {
            return BlockFaceShape.SOLID;
        }
        return BlockFaceShape.UNDEFINED;
    }
}