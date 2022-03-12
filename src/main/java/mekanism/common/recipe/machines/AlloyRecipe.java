package mekanism.common.recipe.machines;

import mekanism.common.recipe.inputs.DoubleMachineInput;
import mekanism.common.recipe.outputs.ItemStackOutput;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class AlloyRecipe extends DoubleMachineRecipe<AlloyRecipe> {

    public AlloyRecipe(DoubleMachineInput input, ItemStackOutput output) {
        super(input, output);
    }

    public AlloyRecipe(ItemStack input, ItemStack extra, ItemStack output) {
        super(input, extra, output);
    }


    @Override
    public AlloyRecipe copy() {
        return new AlloyRecipe(getInput().copy(), getOutput().copy());
    }
}