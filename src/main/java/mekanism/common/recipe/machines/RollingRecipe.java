package mekanism.common.recipe.machines;

import mekanism.common.recipe.inputs.ItemStackInput;
import mekanism.common.recipe.outputs.ItemStackOutput;
import net.minecraft.item.ItemStack;

public class RollingRecipe extends BasicMachineRecipe<RollingRecipe> {

    public RollingRecipe(ItemStackInput input, ItemStackOutput output) {
        super(input, output);
    }

    public RollingRecipe(ItemStack input, ItemStack output) {
        super(input, output);
    }

    @Override
    public RollingRecipe copy() {
        return new RollingRecipe(getInput().copy(), getOutput().copy());
    }
}