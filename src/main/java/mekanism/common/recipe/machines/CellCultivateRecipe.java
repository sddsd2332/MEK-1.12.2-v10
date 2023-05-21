package mekanism.common.recipe.machines;

import mekanism.api.gas.Gas;
import mekanism.common.recipe.inputs.CultivateMachineInput;
import mekanism.common.recipe.outputs.ItemStackOutput;
import net.minecraft.item.ItemStack;

public class CellCultivateRecipe extends CultivateMachineRecipe<CellCultivateRecipe> {

    public CellCultivateRecipe(CultivateMachineInput input, ItemStackOutput output) {
        super(input, output);
    }

    public CellCultivateRecipe(ItemStack input, ItemStack extra, Gas gas, ItemStack output) {
        super(input, extra, gas, output);
    }

    @Override
    public CellCultivateRecipe copy() {
        return new CellCultivateRecipe(getInput().copy(), getOutput().copy());
    }
}
