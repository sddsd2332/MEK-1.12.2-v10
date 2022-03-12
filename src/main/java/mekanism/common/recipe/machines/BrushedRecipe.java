package mekanism.common.recipe.machines;

import mekanism.common.recipe.inputs.ItemStackInput;
import mekanism.common.recipe.outputs.ItemStackOutput;
import net.minecraft.item.ItemStack;

public class BrushedRecipe extends BasicMachineRecipe<BrushedRecipe> {

    public BrushedRecipe(ItemStackInput input, ItemStackOutput output) {
        super(input, output);
    }

    public BrushedRecipe(ItemStack input, ItemStack output) {
        super(input, output);
    }

    @Override
    public BrushedRecipe copy() {
        return new BrushedRecipe(getInput().copy(), getOutput().copy());
    }
}