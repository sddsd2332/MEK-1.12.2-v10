package mekanism.common.recipe.machines;

import mekanism.api.gas.GasStack;
import mekanism.api.gas.GasTank;
import mekanism.common.recipe.inputs.NucleosynthesizerInput;
import mekanism.common.recipe.outputs.NucleosynthesizerOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

public class NucleosynthesizerRecipe extends MachineRecipe<NucleosynthesizerInput, NucleosynthesizerOutput, NucleosynthesizerRecipe> {

    public double extraEnergy;

    public int ticks;

    public NucleosynthesizerRecipe(ItemStack inputSolid, GasStack inputGas, ItemStack outputSolid, double energy, int duration) {
        this(new NucleosynthesizerInput(inputSolid, inputGas), new NucleosynthesizerOutput(outputSolid), energy, duration);
    }

    public NucleosynthesizerRecipe(NucleosynthesizerInput NucleosynthesizerInput, NucleosynthesizerOutput  NucleosynthesizerOutput, double energy, int duration) {
        super(NucleosynthesizerInput, NucleosynthesizerOutput);
        extraEnergy = energy;
        ticks = duration;
    }

    public NucleosynthesizerRecipe(NucleosynthesizerInput pressurizedInput, NucleosynthesizerOutput NucleosynthesizerOutput, NBTTagCompound extraNBT) {
        super(pressurizedInput, NucleosynthesizerOutput);
        extraEnergy = extraNBT.getDouble("extraEnergy");
        ticks = extraNBT.getInteger("duration");
    }

    @Override
    public NucleosynthesizerRecipe copy() {
        return new NucleosynthesizerRecipe(getInput().copy(), getOutput().copy(), extraEnergy, ticks);
    }

    public boolean canOperate(NonNullList<ItemStack> inventory, GasTank inputGasTank) {
        return getInput().use(inventory, 0,  inputGasTank, false) && getOutput().applyOutputs(inventory, 2, false);
    }

    public void operate(NonNullList<ItemStack> inventory,  GasTank inputGasTank) {
        if (getInput().use(inventory, 0,  inputGasTank, true)) {
            getOutput().applyOutputs(inventory, 2,true);
        }
    }
}