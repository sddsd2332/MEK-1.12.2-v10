package mekanism.common.recipe.machines;

import mekanism.common.recipe.inputs.ItemStackInput;
import mekanism.common.recipe.outputs.ItemStackOutput;
import net.minecraft.item.ItemStack;

public class TurningRecipe extends BasicMachineRecipe<TurningRecipe> {

    public TurningRecipe(ItemStackInput input, ItemStackOutput output) {
        super(input, output);
    }

    public TurningRecipe(ItemStack input, ItemStack output) {
        super(input, output);
    }

    @Override
    public TurningRecipe copy() {
        return new TurningRecipe(getInput().copy(), getOutput().copy());
    }
}