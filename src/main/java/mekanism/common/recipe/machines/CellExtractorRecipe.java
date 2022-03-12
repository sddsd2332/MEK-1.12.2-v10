package mekanism.common.recipe.machines;

import mekanism.common.recipe.inputs.ItemStackInput;
import mekanism.common.recipe.outputs.ChanceOutput;
import net.minecraft.item.ItemStack;

public class CellExtractorRecipe extends ChanceMachineRecipe<CellExtractorRecipe> {

    public CellExtractorRecipe(ItemStackInput input, ChanceOutput output) {
        super(input, output);
    }

    public CellExtractorRecipe(ItemStack input, ItemStack primaryOutput, ItemStack secondaryOutput, double chance) {
        this(new ItemStackInput(input), new ChanceOutput(primaryOutput, secondaryOutput, chance));
    }

    public CellExtractorRecipe(ItemStack input, ItemStack primaryOutput) {
        this(new ItemStackInput(input), new ChanceOutput(primaryOutput));
    }

    @Override
    public CellExtractorRecipe copy() {
        return new CellExtractorRecipe(getInput().copy(), getOutput().copy());
    }
}