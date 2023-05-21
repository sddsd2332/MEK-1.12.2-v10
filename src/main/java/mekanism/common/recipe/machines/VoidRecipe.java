package mekanism.common.recipe.machines;

import mekanism.api.gas.GasStack;
import mekanism.api.gas.GasTank;
import mekanism.common.recipe.inputs.CompositeProbabilityInput;
import mekanism.common.recipe.outputs.CompositeProbabilityOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class VoidRecipe extends MachineRecipe<CompositeProbabilityInput, CompositeProbabilityOutput, VoidRecipe> {

    public double extraEnergy;

    public int ticks;

    public VoidRecipe(int dim, ItemStack inputSolid, double inputSolidchane,
                      FluidStack inputFluid, double inputFluidchane,
                      GasStack inputGas, double inputGaschane,
                      ItemStack outputSolid, double outputSolidchane,
                      FluidStack outputFluid, double outputFluidchane,
                      GasStack outputGas, double outputGaschane,
                      double energy, int duration) {
        this(new CompositeProbabilityInput(dim, inputSolid, inputSolidchane,
                inputFluid, inputFluidchane,
                inputGas, inputGaschane
        ), new CompositeProbabilityOutput(outputSolid, outputSolidchane,
                outputFluid, outputFluidchane,
                outputGas, outputGaschane), energy, duration);
    }

    public VoidRecipe(CompositeProbabilityInput Input, CompositeProbabilityOutput Output, double energy, int duration) {
        super(Input, Output);
        extraEnergy = energy;
        ticks = duration;
    }

    public VoidRecipe(CompositeProbabilityInput input, CompositeProbabilityOutput output, NBTTagCompound extraNBT) {
        super(input, output);
        extraEnergy = extraNBT.getDouble("extraEnergy");
        ticks = extraNBT.getInteger("duration");
    }

    @Override
    public VoidRecipe copy() {
        return new VoidRecipe(getInput().copy(), getOutput().copy(), extraEnergy, ticks);
    }

    public boolean canOperate(int cachedDimensionId, NonNullList<ItemStack> inventory, FluidTank inputFluidTank, GasTank inputGasTank, FluidTank outpitFluidTank, GasTank outputGasTank) {
        return getInput().ingredient == cachedDimensionId && getInput().useItem(inventory, 0, inputFluidTank, inputGasTank, false)
                && getOutput().applyOutputs(inventory, 2, outpitFluidTank, outputGasTank, false);
    }

    public void operate(int cachedDimensionId, NonNullList<ItemStack> inventory, FluidTank inputFluidTank, GasTank inputGasTank, FluidTank outpitFluidTank, GasTank outputGasTank) {
        if (getInput().ingredient == cachedDimensionId && getInput().useItem(inventory, 0, inputFluidTank, inputGasTank, true)) {
            getOutput().applyOutputs(inventory, 2, outpitFluidTank, outputGasTank, true);
        }
    }

    public double getExtraEnergy() {
        return extraEnergy;
    }

}