package mekanism.common.recipe.machines;

import mekanism.common.recipe.inputs.ItemStackInput;
import mekanism.common.recipe.outputs.ChanceOutput2;
import net.minecraft.item.ItemStack;

public class RecyclerRecipe extends Chance2MachineRecipe<RecyclerRecipe> {

    public RecyclerRecipe(ItemStackInput input, ChanceOutput2 output) {
        super(input, output);
    }

    public RecyclerRecipe(ItemStack input, ItemStack primaryOutput, double chance) {
        this(new ItemStackInput(input), new ChanceOutput2(primaryOutput, chance));
    }


    @Override
    public RecyclerRecipe copy() {
        return new RecyclerRecipe(getInput().copy(), getOutput().copy());
    }
}