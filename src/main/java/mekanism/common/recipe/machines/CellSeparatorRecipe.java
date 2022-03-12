package mekanism.common.recipe.machines;

import mekanism.common.recipe.inputs.ItemStackInput;
import mekanism.common.recipe.outputs.ChanceOutput;
import net.minecraft.item.ItemStack;

public class CellSeparatorRecipe extends ChanceMachineRecipe<CellSeparatorRecipe> {

    public CellSeparatorRecipe(ItemStackInput input, ChanceOutput output) {
        super(input, output);
    }

    public CellSeparatorRecipe(ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, double chance) {
        this(new ItemStackInput(input), new ChanceOutput(primaryOutput, secondaryOutput, chance));
    }

    public CellSeparatorRecipe(ItemStack input, ItemStack primaryOutput) {
        this(new ItemStackInput(input), new ChanceOutput(primaryOutput));
    }

    @Override
    public CellSeparatorRecipe copy() {
        return new CellSeparatorRecipe(getInput().copy(), getOutput().copy());
    }
}