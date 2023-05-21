package mekanism.common.recipe.outputs;

import mekanism.api.gas.GasStack;
import mekanism.api.gas.GasTank;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;

public class CompositeProbabilityOutput extends MachineOutput<CompositeProbabilityOutput> {

    private static final Random rand = new Random();

    public ItemStack ItemStackOutput = ItemStack.EMPTY;

    public FluidStack fluidOutput;

    public GasStack gasOutput;

    public double ItemStackOutputChance;

    public double gasOutputChance;

    public double fluidOutputChance;

    public CompositeProbabilityOutput(ItemStack primary, double chance, FluidStack fluid, double fluidchance, GasStack gas, double gaschance) {
        ItemStackOutput = primary;
        ItemStackOutputChance = chance;
        fluidOutput = fluid;
        fluidOutputChance = fluidchance;
        gasOutput = gas;
        gasOutputChance = gaschance;
    }

    public CompositeProbabilityOutput() {
    }

    @Override
    public void load(NBTTagCompound nbtTags) {
        ItemStackOutput = new ItemStack(nbtTags.getCompoundTag("ItemStackOutput"));
        ItemStackOutputChance = nbtTags.getDouble("ItemStackOutputChance");
        fluidOutput = FluidStack.loadFluidStackFromNBT(nbtTags.getCompoundTag("output"));
        fluidOutputChance = nbtTags.getDouble("fluidOutputChance");
        gasOutput = GasStack.readFromNBT(nbtTags.getCompoundTag("gasOutput"));
        gasOutputChance = nbtTags.getDouble("gasOutputChance");
    }

    public boolean canFillTank(GasTank tank) {
        return tank.canReceive(gasOutput.getGas()) && tank.getNeeded() >= gasOutput.amount;
    }


    public boolean canAddProducts(NonNullList<ItemStack> inventory, int index) {
        ItemStack stack = inventory.get(index);
        return stack.isEmpty() || (ItemHandlerHelper.canItemStacksStack(stack, ItemStackOutput) && stack.getCount() + ItemStackOutput.getCount() <= stack.getMaxStackSize());
    }


    public void fillGasTank(GasTank tank) {
        tank.receive(gasOutput, true);
    }

    public void fillFliudTank(FluidTank tank) {
        if (tank.fill(fluidOutput, false) > 0) {
            tank.fill(fluidOutput, true);
        }
    }


    public void addProducts(NonNullList<ItemStack> inventory, int index) {
        ItemStack stack = inventory.get(index);
        if (stack.isEmpty()) {
            inventory.set(index, ItemStackOutput.copy());
        } else if (ItemHandlerHelper.canItemStacksStack(stack, ItemStackOutput)) {
            stack.grow(ItemStackOutput.getCount());
        }
    }

    public boolean applyOutputs(NonNullList<ItemStack> inventory, int index, FluidTank fluidTank, GasTank tank, boolean doEmit) {
        if (canFillTank(tank) && canAddProducts(inventory, index)) {
            if (doEmit) {
                if (checkSecondary()) {
                    addProducts(inventory, index);
                }
                if (checkGasChance()) {
                    fillGasTank(tank);
                }
                if (checkfluidChance()) {
                    fillFliudTank(fluidTank);
                }
            }
            return true;
        }
        return false;
    }

    public boolean checkSecondary() {
        return rand.nextDouble() <= ItemStackOutputChance;
    }

    public boolean checkfluidChance() {
        return rand.nextDouble() <= fluidOutputChance;
    }

    public boolean checkGasChance() {
        return rand.nextDouble() <= gasOutputChance;
    }


    public ItemStack getItemOutput() {
        return ItemStackOutput;
    }

    public GasStack getGasOutput() {
        return gasOutput;
    }

    public FluidStack getFluidOutput() {
        return fluidOutput;
    }

    @Override
    public CompositeProbabilityOutput copy() {
        return new CompositeProbabilityOutput(ItemStackOutput.copy(), ItemStackOutputChance, fluidOutput.copy(), fluidOutputChance, gasOutput.copy(), gasOutputChance);
    }
}