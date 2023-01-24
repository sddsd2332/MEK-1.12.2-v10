package mekanism.common.tile;

import io.netty.buffer.ByteBuf;
import mekanism.api.EnumColor;
import mekanism.api.TileNetworkList;
import mekanism.api.gas.*;
import mekanism.api.transmitters.TransmissionType;
import mekanism.common.MekanismItems;
import mekanism.common.SideData;
import mekanism.common.Upgrade;
import mekanism.common.base.ISustainedData;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.recipe.GasConversionHandler;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.recipe.inputs.AdvancedMachineInput;
import mekanism.common.recipe.machines.FarmMachineRecipe;
import mekanism.common.recipe.outputs.ChanceOutput;
import mekanism.common.tile.component.TileComponentConfig;
import mekanism.common.tile.component.TileComponentEjector;
import mekanism.common.tile.prefab.TileEntityUpgradeableMachine;
import mekanism.common.block.states.BlockStateMachine.MachineType;
import mekanism.common.util.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public abstract class TileEntityFarmMachine<RECIPE extends FarmMachineRecipe<RECIPE>>
        extends TileEntityUpgradeableMachine<AdvancedMachineInput, ChanceOutput, RECIPE>
        implements IGasHandler, ISustainedData {

    private static final String[] methods = new String[] { "getEnergy", "getSecondaryStored", "getProgress", "isActive",
            "facing", "canOperate", "getMaxEnergy", "getEnergyNeeded" };

    public static final int BASE_TICKS_REQUIRED = 200;
    public static final int BASE_GAS_PER_TICK = 1;
    public static int MAX_GAS = 210;

    /**
     * How much secondary energy (fuel) this machine uses per tick, not including
     * upgrades.
     */
    public int BASE_SECONDARY_ENERGY_PER_TICK;
    /**
     * How much secondary energy this machine uses per tick, including upgrades.
     */
    public double secondaryEnergyPerTick;
    public int secondaryEnergyThisTick;
    public GasTank gasTank;
    public Gas prevGas;

    public TileEntityFarmMachine(String soundPath, MachineType type, int ticksRequired, int secondaryPerTick) {
        super(soundPath, type, 5, ticksRequired,
                MekanismUtils.getResource(MekanismUtils.ResourceType.GUI, "GuiAdvancedMachine.png"));
        configComponent = new TileComponentConfig(this, TransmissionType.ITEM, TransmissionType.ENERGY);

        configComponent.addOutput(TransmissionType.ITEM, new SideData("None", EnumColor.GREY, InventoryUtils.EMPTY));
        configComponent.addOutput(TransmissionType.ITEM, new SideData("Input", EnumColor.RED, new int[] { 0 }));
        configComponent.addOutput(TransmissionType.ITEM,
                new SideData("Output", EnumColor.BLUE, new int[] { 2, 4 }));
        configComponent.addOutput(TransmissionType.ITEM, new SideData("Energy", EnumColor.GREEN, new int[] { 3 }));
        configComponent.addOutput(TransmissionType.ITEM, new SideData("Extra", EnumColor.ORANGE, new int[] { 1 }));

        configComponent.setConfig(TransmissionType.ITEM, new byte[] { 2, 1, 0, 0, 0, 3 });
        configComponent.setInputConfig(TransmissionType.ENERGY);

        gasTank = new GasTank(MAX_GAS);

        inventory = NonNullList.withSize(6, ItemStack.EMPTY);

        BASE_SECONDARY_ENERGY_PER_TICK = secondaryPerTick;
        secondaryEnergyPerTick = secondaryPerTick;

        if (upgradeableSecondaryEfficiency()) {
            upgradeComponent.setSupported(Upgrade.GAS);
        }
        ejectorComponent = new TileComponentEjector(this);
        ejectorComponent.setOutputData(TransmissionType.ITEM, configComponent.getOutputs(TransmissionType.ITEM).get(3));
    }

    @Override
    protected void upgradeInventory(TileEntityFactory factory) {

        factory.configComponent.getOutputs(TransmissionType.ITEM).get(2).availableSlots = new int[] { 4, 8, 9, 10 };
        factory.gasTank.setGas(gasTank.getGas());
        factory.inventory.set(5, inventory.get(0));
        factory.inventory.set(4, inventory.get(1));
        factory.inventory.set(5 + 3, inventory.get(2));
        factory.inventory.set(1, inventory.get(3));
        factory.inventory.set(0, inventory.get(4));
    }

    @Nullable
    public GasStack getItemGas(ItemStack itemStack) {
        return GasConversionHandler.getItemGas(itemStack, gasTank, this::isValidGas);
    }

    public abstract boolean isValidGas(Gas gas);

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!world.isRemote) {
            ChargeUtils.discharge(3, this);
            handleSecondaryFuel();
            boolean inactive = false;
            RECIPE recipe = getRecipe();
            secondaryEnergyThisTick = useStatisticalMechanics() ? StatUtils.inversePoisson(secondaryEnergyPerTick)
                    : (int) Math.ceil(secondaryEnergyPerTick);
            if (canOperate(recipe) && MekanismUtils.canFunction(this) && getEnergy() >= energyPerTick
                    && gasTank.getStored() >= secondaryEnergyThisTick) {
                setActive(true);
                operatingTicks++;
                if (operatingTicks >= ticksRequired) {
                    operate(recipe);
                    operatingTicks = 0;
                }
                gasTank.draw(secondaryEnergyThisTick, true);
                electricityStored -= energyPerTick;
            } else {
                inactive = true;
                setActive(false);
            }
            if (inactive && getRecipe() == null) {
                operatingTicks = 0;
            }
            prevEnergy = getEnergy();
            if (!(gasTank.getGasType() == null || gasTank.getStored() == 0)) {
                prevGas = gasTank.getGasType();
            }
        }
    }

    public void handleSecondaryFuel() {
        ItemStack itemStack = inventory.get(1);
        int needed = gasTank.getNeeded();
        if (!itemStack.isEmpty() && needed > 0) {
            GasStack gasStack = getItemGas(itemStack);
            if (gasStack != null && needed >= gasStack.amount) {
                if (itemStack.getItem() instanceof IGasItem) {
                    IGasItem item = (IGasItem) itemStack.getItem();
                    gasTank.receive(item.removeGas(itemStack, gasStack.amount), true);
                } else {
                    gasTank.receive(gasStack, true);
                    itemStack.shrink(1);
                }
            }
        }
    }

    public boolean upgradeableSecondaryEfficiency() {
        return false;
    }

    public boolean useStatisticalMechanics() {
        return false;
    }

    @Override
    public boolean isItemValidForSlot(int slotID, @Nonnull ItemStack itemstack) {
        if (slotID == 2 || slotID == 4) {
            return false;
        } else if (slotID == 5) {
            return itemstack.getItem() == MekanismItems.SpeedUpgrade
                    || itemstack.getItem() == MekanismItems.EnergyUpgrade;
        } else if (slotID == 0) {
            for (AdvancedMachineInput input : getRecipes().keySet()) {
                if (ItemHandlerHelper.canItemStacksStack(input.itemStack, itemstack)) {
                    return true;
                }
            }
        } else if (slotID == 3) {
            return ChargeUtils.canBeDischarged(itemstack);
        } else if (slotID == 1) {
            return getItemGas(itemstack) != null;
        }
        return false;
    }

    @Override
    public AdvancedMachineInput getInput() {
        return new AdvancedMachineInput(inventory.get(0), prevGas);
    }

    @Override
    public RECIPE getRecipe() {
        AdvancedMachineInput input = getInput();
        if (cachedRecipe == null || !input.testEquality(cachedRecipe.getInput())) {
            cachedRecipe = RecipeHandler.getFarmRecipe(input, getRecipes());
        }
        return cachedRecipe;
    }

    @Override
    public void operate(RECIPE recipe) {
        recipe.operate(inventory, 0, gasTank, secondaryEnergyThisTick, 2, 4);
        markDirty();
    }

    @Override
    public boolean canOperate(RECIPE recipe) {
        return recipe != null && recipe.canOperate(inventory, 0, gasTank, secondaryEnergyThisTick, 2, 4);
    }

    @Override
    public void handlePacketData(ByteBuf dataStream) {
        super.handlePacketData(dataStream);
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            TileUtils.readTankData(dataStream, gasTank);
        }
    }

    @Override
    public TileNetworkList getNetworkedData(TileNetworkList data) {
        super.getNetworkedData(data);
        TileUtils.addTankData(data, gasTank);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTags) {
        super.readFromNBT(nbtTags);
        gasTank.read(nbtTags.getCompoundTag("gasTank"));
        gasTank.setMaxGas(MAX_GAS);
        GasUtils.clearIfInvalid(gasTank, this::isValidGas);
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTags) {
        super.writeToNBT(nbtTags);
        nbtTags.setTag("gasTank", gasTank.write(new NBTTagCompound()));
        return nbtTags;
    }

    public int getScaledGasLevel(int i) {
        return gasTank.getStored() * i / gasTank.getMaxGas();
    }

    @Override
    public boolean canExtractItem(int slotID, @Nonnull ItemStack itemstack, @Nonnull EnumFacing side) {
        if (slotID == 3) {
            return ChargeUtils.canBeOutputted(itemstack, false);
        }
        return slotID == 2 || slotID == 4;
    }

    @Override
    public int receiveGas(EnumFacing side, GasStack stack, boolean doTransfer) {
        return 0;
    }

    @Override
    public GasStack drawGas(EnumFacing side, int amount, boolean doTransfer) {
        return null;
    }

    @Override
    public boolean canReceiveGas(EnumFacing side, Gas type) {
        return false;
    }

    @Override
    public boolean canDrawGas(EnumFacing side, Gas type) {
        return false;
    }

    @Override
    @Nonnull
    public GasTankInfo[] getTankInfo() {
        return new GasTankInfo[] { gasTank };
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
    public void recalculateUpgradables(Upgrade upgrade) {
        super.recalculateUpgradables(upgrade);
        if (upgrade == Upgrade.SPEED || (upgradeableSecondaryEfficiency() && upgrade == Upgrade.GAS)) {
            secondaryEnergyPerTick = MekanismUtils.getSecondaryEnergyPerTickMean(this, BASE_SECONDARY_ENERGY_PER_TICK);
        }
    }

    @Override
    public Map<AdvancedMachineInput, RECIPE> getRecipes() {
        return null;
    }

    @Override
    public String[] getMethods() {
        return methods;
    }

    @Override
    public Object[] invoke(int method, Object[] arguments) throws NoSuchMethodException {
        switch (method) {
            case 0:
                return new Object[] { getEnergy() };
            case 1:
                return new Object[] { gasTank.getStored() };
            case 2:
                return new Object[] { operatingTicks };
            case 3:
                return new Object[] { isActive };
            case 4:
                return new Object[] { facing };
            case 5:
                return new Object[] { canOperate(getRecipe()) };
            case 6:
                return new Object[] { maxEnergy };
            case 7:
                return new Object[] { maxEnergy - getEnergy() };
            default:
                throw new NoSuchMethodException();
        }
    }

    @Override
    public void writeSustainedData(ItemStack itemStack) {
        GasUtils.writeSustainedData(gasTank, itemStack);
    }

    @Override
    public void readSustainedData(ItemStack itemStack) {
        GasUtils.readSustainedData(gasTank, itemStack);
    }

}
