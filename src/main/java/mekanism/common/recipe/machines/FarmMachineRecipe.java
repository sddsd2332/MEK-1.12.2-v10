package mekanism.common.recipe.machines;

import mekanism.api.gas.GasTank;
import mekanism.common.recipe.inputs.AdvancedMachineInput;
import mekanism.common.recipe.outputs.ChanceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public abstract class FarmMachineRecipe<RECIPE extends FarmMachineRecipe<RECIPE>> extends MachineRecipe<AdvancedMachineInput, ChanceOutput, RECIPE> {

    public FarmMachineRecipe(AdvancedMachineInput input, ChanceOutput output) {
        super(input, output);
    }

    public boolean inputMatches(NonNullList<ItemStack> inventory, int inputIndex, GasTank gasTank, int amount) {
        return getInput().useItem(inventory, inputIndex, false) && getInput().useSecondary(gasTank, amount, false);
    }

    public boolean canOperate(NonNullList<ItemStack> inventory, int inputIndex, GasTank gasTank, int amount, int primaryIndex, int secondaryIndex) {
        return inputMatches(inventory, inputIndex, gasTank, amount) && getOutput().applyOutputs(inventory, primaryIndex, secondaryIndex, false);
    }

    public void operate(NonNullList<ItemStack> inventory, int inputIndex, GasTank gasTank, int needed, int primaryIndex, int secondaryIndex) {
        if (getInput().useItem(inventory, inputIndex, true) && getInput().useSecondary(gasTank, needed, true)) {
            getOutput().applyOutputs(inventory, primaryIndex, secondaryIndex, true);
        }
    }
}