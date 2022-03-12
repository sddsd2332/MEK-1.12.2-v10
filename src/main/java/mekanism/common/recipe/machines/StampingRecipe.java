package mekanism.common.recipe.machines;

import mekanism.common.recipe.inputs.ItemStackInput;
import mekanism.common.recipe.outputs.ItemStackOutput;
import net.minecraft.item.ItemStack;

public class StampingRecipe extends BasicMachineRecipe<StampingRecipe> {

    public StampingRecipe(ItemStackInput input, ItemStackOutput output) {
        super(input, output);
    }

    public StampingRecipe(ItemStack input, ItemStack output) {
        super(input, output);
    }

    @Override
    public StampingRecipe copy() {
        return new StampingRecipe(getInput().copy(), getOutput().copy());
    }
}