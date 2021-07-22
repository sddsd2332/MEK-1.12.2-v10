package mekanism.common.tile;

import io.netty.buffer.ByteBuf;
import java.util.List;
import javax.annotation.Nonnull;
import mekanism.api.Coord4D;
import mekanism.api.TileNetworkList;
import mekanism.api.gas.*;
import mekanism.common.Upgrade;
import mekanism.common.Upgrade.IUpgradeInfoHandler;
import mekanism.common.base.IBoundingBlock;
import mekanism.common.base.IComparatorSupport;
import mekanism.common.base.ISustainedData;
import mekanism.common.base.ITankManager;
import mekanism.common.block.states.BlockStateMachine;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.recipe.inputs.GasInput;
import mekanism.common.recipe.machines.IsotopicRecipe;

import mekanism.common.tile.prefab.TileEntityMachine;
import mekanism.common.util.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;

import net.minecraftforge.common.capabilities.Capability;

import net.minecraftforge.fml.common.FMLCommonHandler;


public class TileEntityIsotopicCentrifuge extends TileEntityMachine implements ISustainedData,IBoundingBlock,IGasHandler,IUpgradeInfoHandler,ITankManager,IComparatorSupport{

    public static final int MAX_GAS = 64000;

    private static final int[] INPUT_SLOT = {0};
    private static final int[] OUTPUT_SLOT = {1};
    private static final int[] ENERGY_SLOT = {2};
    private int currentRedstoneLevel;

    public GasTank inputTank = new GasTank(MAX_GAS);
    public GasTank outputTank = new GasTank(MAX_GAS);
    public RedstoneControl controlType = RedstoneControl.DISABLED;
    private IsotopicRecipe cachedRecipe;

    public TileEntityIsotopicCentrifuge() {
        super("machine.rotarycondensentrator", BlockStateMachine.MachineType.ISOTOPIC_CENTRIFUGE, 3);
        inventory = NonNullList.withSize(6, ItemStack.EMPTY);
    }


    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!world.isRemote) {
            TileUtils.receiveGas(inventory.get(0), inputTank);
            TileUtils.drawGas(inventory.get(1), outputTank);
            ChargeUtils.discharge(2,this);
            IsotopicRecipe Recipe= getRecipe();
            if (!inventory.get(0).isEmpty()) {

                if (RecipeHandler.Recipe.ISOTOPIC_CENTRIFUGE.containsRecipe(inventory.get(0))){
                      if (FluidContainerUtils.isFluidContainer(inventory.get(0))) {
                          outputTank.draw(GasUtils.addGas(inventory.get(0), inputTank.getGas()), true);

                    }
                }
            }
            if (!inventory.get(1).isEmpty() && outputTank.getStored() > 0) {
                outputTank.draw(GasUtils.addGas(inventory.get(1), outputTank.getGas()), true);
                MekanismUtils.saveChunk(this);
            }
            IsotopicRecipe recipe = getRecipe();
            if (canOperate(recipe) && getEnergy() >= energyPerTick && MekanismUtils.canFunction(this)) {
                setActive(true);
                operate(recipe);
            } else if (prevEnergy >= getEnergy()) {
                setActive(false);
            }
            prevEnergy = getEnergy();
            int newRedstoneLevel = getRedstoneLevel();
            if (newRedstoneLevel != currentRedstoneLevel) {
                world.updateComparatorOutputLevel(pos, getBlockType());
                currentRedstoneLevel = newRedstoneLevel;
            }
        }
    }

    public boolean canOperate(IsotopicRecipe recipe) {
        return recipe != null && recipe.canOperate(inputTank, outputTank);
    }

    public IsotopicRecipe getRecipe() {
        GasInput input = getInput();
        if (cachedRecipe == null || !input.testEquality(cachedRecipe.getInput())) {
            cachedRecipe = RecipeHandler.getIsotopicRecipe(getInput());
        }
        return cachedRecipe;
    }
    public GasInput getInput() {
        return new GasInput(inputTank.getGas());
    }
    @Nonnull
    @Override
    public int[] getSlotsForFace(@Nonnull EnumFacing side) {
        if (side == MekanismUtils.getLeft(facing)) {
            //Gas
            return INPUT_SLOT;
        } else if (side == MekanismUtils.getRight(facing)) {
            //Fluid
            return OUTPUT_SLOT;
        }
        return ENERGY_SLOT;
    }

    public void operate(IsotopicRecipe recipe) {
        recipe.operate(inputTank, outputTank, getUpgradedUsage());
    }
    @Override
    public void writeSustainedData(ItemStack itemStack) {
        if (inputTank.getGas() != null) {
            ItemDataUtils.setCompound(itemStack, "inputTank", inputTank.getGas().write(new NBTTagCompound()));
        }
        if (outputTank.getGas() != null) {
            ItemDataUtils.setCompound(itemStack, "outputTank", outputTank.getGas().write(new NBTTagCompound()));
        }
    }

    @Nonnull
    @Override
    public GasTankInfo[] getTankInfo() {
        return new GasTankInfo[]{inputTank, outputTank};
    }


    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing side) {
        if (isCapabilityDisabled(capability, side)) {
            return false;
        }
        return capability == Capabilities.GAS_HANDLER_CAPABILITY || super.hasCapability(capability, side);
    }

    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing side) {
        if (isCapabilityDisabled(capability, side)) {
            return null;
        } else if (capability == Capabilities.GAS_HANDLER_CAPABILITY) {
            return Capabilities.GAS_HANDLER_CAPABILITY.cast(this);
        }
        return super.getCapability(capability, side);
    }

    @Override
    public boolean isCapabilityDisabled(@Nonnull Capability<?> capability, EnumFacing side) {
        if (capability == Capabilities.GAS_HANDLER_CAPABILITY) {
            return side != null && side != facing && side != EnumFacing.DOWN;
        }
        return super.isCapabilityDisabled(capability, side);
    }


    @Override
    public void readSustainedData(ItemStack itemStack) {
        inputTank.setGas(GasStack.readFromNBT(ItemDataUtils.getCompound(itemStack, "inputTank")));
        outputTank.setGas(GasStack.readFromNBT(ItemDataUtils.getCompound(itemStack, "outputTank")));
    }
    @Override
    public void onPlace() {
        MekanismUtils.makeBoundingBlock(world, Coord4D.get(this).offset(EnumFacing.UP).getPos(), Coord4D.get(this));
    }
    @Override
    public void onBreak() {
        world.setBlockToAir(getPos().up());
        world.setBlockToAir(getPos());
    }
    @Override
    public int receiveGas(EnumFacing side, GasStack stack, boolean doTransfer) {
        if (canReceiveGas(side, stack != null ? stack.getGas() : null)) {
            return inputTank.receive(stack, doTransfer);
        }
        return 0;
    }

    public int getUpgradedUsage() {
        int possibleProcess = (int) Math.pow(2, upgradeComponent.getUpgrades(Upgrade.SPEED));
        possibleProcess = Math.min(Math.min(inputTank.getStored(), outputTank.getNeeded()), possibleProcess);
        return possibleProcess;
    }

    @Override
    public void handlePacketData(ByteBuf dataStream) {
        super.handlePacketData(dataStream);
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            isActive = dataStream.readBoolean();
            controlType = RedstoneControl.values()[dataStream.readInt()];
            TileUtils.readTankData(dataStream, inputTank);
            TileUtils.readTankData(dataStream, outputTank);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTags) {
        super.readFromNBT(nbtTags);
        isActive = nbtTags.getBoolean("isActive");
        controlType = RedstoneControl.values()[nbtTags.getInteger("controlType")];
        inputTank.read(nbtTags.getCompoundTag("inputTank"));
        outputTank.read(nbtTags.getCompoundTag("outputTank"));
    }
    @Override
    public TileNetworkList getNetworkedData(TileNetworkList data) {
        super.getNetworkedData(data);
        data.add(isActive);
        data.add(controlType.ordinal());
        TileUtils.addTankData(data, inputTank);
        TileUtils.addTankData(data, outputTank);
        return data;
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTags) {
        super.writeToNBT(nbtTags);
        nbtTags.setBoolean("isActive", isActive);
        nbtTags.setInteger("controlType", controlType.ordinal());
        nbtTags.setTag("inputTank", inputTank.write(new NBTTagCompound()));
        nbtTags.setTag("outputTank", outputTank.write(new NBTTagCompound()));
        return nbtTags;
    }

    @Override
    public GasStack drawGas(EnumFacing side, int amount, boolean doTransfer) {
        if (canDrawGas(side, null)) {
            return outputTank.draw(amount, doTransfer);
        }
        return null;
    }
    @Override
    public boolean canReceiveGas(EnumFacing side, Gas type) {
        return side == EnumFacing.DOWN && inputTank.canReceive(type);
    }
    @Override
    public boolean canDrawGas(EnumFacing side, Gas type) {
        return side == facing && outputTank.canDraw(type);
    }

    @Override
    public List<String> getInfo(Upgrade upgrade) {
        return upgrade == Upgrade.SPEED ? upgrade.getExpScaledInfo(this) : upgrade.getMultScaledInfo(this);
    }

    @Override
    public Object[] getTanks() {
        return new Object[]{inputTank, outputTank};
    }

    @Override
    public int getRedstoneLevel() {
        return MekanismUtils.redstoneLevelFromContents(inputTank.getStored(), inputTank.getMaxGas());
    }
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        if (slot == 0) {
            //Gas INPUT
            return stack.getItem() instanceof IGasItem;
        }else if (slot == 1) {
            //Gas output
            return stack.getItem() instanceof IGasItem;
        } else if (slot == 2) {
            return ChargeUtils.canBeDischarged(stack);
        }
        return false;
    }
    }