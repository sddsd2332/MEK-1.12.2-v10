package mekanism.common.recipe.machines;

import mekanism.api.gas.GasStack;
import mekanism.api.gas.GasTank;
import mekanism.common.recipe.inputs.ItemStackInput;
import mekanism.common.recipe.outputs.GasOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class NutritionalRecipe extends MachineRecipe<ItemStackInput, GasOutput, NutritionalRecipe> {

    public NutritionalRecipe(ItemStackInput input, GasOutput output) {
        super(input, output);
    }

    public NutritionalRecipe(ItemStack input, GasStack output) {
        this(new ItemStackInput(input), new GasOutput(output));
    }

    @Override
    public NutritionalRecipe copy() {
        return new NutritionalRecipe(getInput().copy(), getOutput().copy());
    }

    public boolean canOperate(NonNullList<ItemStack> inventory, GasTank outputTank) {
        return getInput().useItemStackFromInventory(inventory, 0, false) && getOutput().applyOutputs(outputTank, false, 1);
    }

    public void operate(NonNullList<ItemStack> inventory, GasTank outputTank) {
        if (getInput().useItemStackFromInventory(inventory, 0, true)) {
            getOutput().applyOutputs(outputTank, true, 1);
        }
    }
}