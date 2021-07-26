package mekanism.common.tile;

import io.netty.buffer.ByteBuf;
import java.util.List;
import javax.annotation.Nonnull;
import mekanism.api.Coord4D;
import mekanism.api.TileNetworkList;
import mekanism.api.gas.*;
import mekanism.common.Upgrade;
import mekanism.common.Upgrade.IUpgradeInfoHandler;
import mekanism.common.base.*;
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
    public static final int MAX_GAS = 10000;
    public GasTank inputTank = new GasTank(MAX_GAS);
    public GasTank outputTank = new GasTank(MAX_GAS);
    private static final int[] INPUT_SLOT = {0};
    private static final int[] OUTPUT_SLOT = {1};
    public int gasOutput = 256;
    public IsotopicRecipe cachedRecipe;
    private int currentRedstoneLevel;
    public double clientEnergyUsed;

    public TileEntityIsotopicCentrifuge() {
        super("machine.washer", BlockStateMachine.MachineType.ISOTOPIC_CENTRIFUGE, 4);
        inventory = NonNullList.withSize(5, ItemStack.EMPTY);
    }
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!world.isRemote) {
            ChargeUtils.discharge(2, this);
            TileUtils.receiveGas(inventory.get(0), inputTank);
            TileUtils.drawGas(inventory.get(1), outputTank);
            IsotopicRecipe recipe = getRecipe();
            if (canOperate(recipe) && getEnergy() >= energyPerTick && MekanismUtils.canFunction(this)) {
                setActive(true);
                int operations = operate(recipe);
                double prev = getEnergy();
                setEnergy(getEnergy() - energyPerTick * operations);
                clientEnergyUsed = prev - getEnergy();
            } else if (prevEnergy >= getEnergy()) {
                setActive(false);
            }
            TileUtils.emitGas(this, outputTank, gasOutput, facing);
            prevEnergy = getEnergy();
            int newRedstoneLevel = getRedstoneLevel();
            if (newRedstoneLevel != currentRedstoneLevel) {
                world.updateComparatorOutputLevel(pos, getBlockType());
                currentRedstoneLevel = newRedstoneLevel;
            }
        }
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

    public boolean canOperate(IsotopicRecipe recipe) {
        return recipe != null && recipe.canOperate(inputTank , outputTank);
    }
    public int operate(IsotopicRecipe recipe) {
        int operations = getUpgradedUsage();
        recipe.operate(inputTank, outputTank, operations);
        return operations;
    }

    public int getUpgradedUsage() {
        int possibleProcess = (int) Math.pow(2, upgradeComponent.getUpgrades(Upgrade.SPEED));
        possibleProcess = Math.min(Math.min(inputTank.getStored(), outputTank.getNeeded()), possibleProcess);
        possibleProcess = Math.min((int) (getEnergy() / energyPerTick), possibleProcess);
        return possibleProcess;
    }
    @Override
    public void handlePacketData(ByteBuf dataStream) {
        super.handlePacketData(dataStream);
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            clientEnergyUsed = dataStream.readDouble();
            TileUtils.readTankData(dataStream, inputTank);
            TileUtils.readTankData(dataStream, outputTank);
        }
    }

    @Override
    public TileNetworkList getNetworkedData(TileNetworkList data) {
        super.getNetworkedData(data);
        data.add(clientEnergyUsed);
        TileUtils.addTankData(data, inputTank);
        TileUtils.addTankData(data, outputTank);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTags) {
        super.readFromNBT(nbtTags);
        inputTank.read(nbtTags.getCompoundTag("rightTank"));
        outputTank.read(nbtTags.getCompoundTag("centerTank"));
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTags) {
        super.writeToNBT(nbtTags);
        nbtTags.setTag("rightTank", inputTank.write(new NBTTagCompound()));
        nbtTags.setTag("centerTank", outputTank.write(new NBTTagCompound()));
        return nbtTags;
    }

    public boolean canSetFacing(@Nonnull EnumFacing facing) {
        return facing != EnumFacing.DOWN;
    }

    public GasTank getTank(EnumFacing side) {
        if (side == MekanismUtils.getLeft(facing)) {
            return inputTank;
        } else if (side == MekanismUtils.getRight(facing)) {
            return outputTank;
        }
        return null;
    }

    @Override
    public boolean canReceiveGas(EnumFacing side, Gas type) {
        return side == EnumFacing.DOWN && inputTank.canReceive(type);
    }

    @Override
    public int receiveGas(EnumFacing side, GasStack stack, boolean doTransfer) {
        if (canReceiveGas(side, stack != null ? stack.getGas() : null)) {
            return inputTank.receive(stack, doTransfer);
        }
        return 0;
    }

    @Override
    public GasStack drawGas(EnumFacing side, int amount, boolean doTransfer) {
        if (canDrawGas(side, null)) {
            return outputTank.draw(amount, doTransfer);
        }
        return null;
    }

    @Override
    public boolean canDrawGas(EnumFacing side, Gas type) {
        return side == facing && outputTank.canDraw(type);
    }

    @Nonnull
    @Override
    public GasTankInfo[] getTankInfo() {
        return new GasTankInfo[]{inputTank, outputTank};
    }

    @Override
    public boolean isItemValidForSlot(int slot, @Nonnull ItemStack stack) {
        return stack.getItem() instanceof IGasItem;
    }

    @Override
    public boolean canExtractItem(int slotID, @Nonnull ItemStack itemstack, @Nonnull EnumFacing side) {
        if (slotID == 1) {
            return !itemstack.isEmpty() && itemstack.getItem() instanceof IGasItem && ((IGasItem) itemstack.getItem()).canProvideGas(itemstack, null);
        } else if (slotID == 2) {
            return ChargeUtils.canBeOutputted(itemstack, false);
        }
        return false;
    }

    @Nonnull
    @Override
    public int[] getSlotsForFace(@Nonnull EnumFacing side) {
        return side == facing ? OUTPUT_SLOT : INPUT_SLOT;
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
    public void writeSustainedData(ItemStack itemStack) {
        if (inputTank.getGas() != null) {
            ItemDataUtils.setCompound(itemStack, "inputTank", inputTank.getGas().write(new NBTTagCompound()));
        }
        if (outputTank.getGas() != null) {
            ItemDataUtils.setCompound(itemStack, "outputTank", outputTank.getGas().write(new NBTTagCompound()));
        }
    }

    @Override
    public void readSustainedData(ItemStack itemStack) {
        inputTank.setGas(GasStack.readFromNBT(ItemDataUtils.getCompound(itemStack, "inputTank")));
        outputTank.setGas(GasStack.readFromNBT(ItemDataUtils.getCompound(itemStack, "outputTank")));
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
    @Override
    public void onPlace() {
        MekanismUtils.makeBoundingBlock(world, Coord4D.get(this).offset(EnumFacing.UP).getPos(), Coord4D.get(this));
    }
    @Override
    public void onBreak() {
        world.setBlockToAir(getPos().up());
        world.setBlockToAir(getPos());
    }
}

