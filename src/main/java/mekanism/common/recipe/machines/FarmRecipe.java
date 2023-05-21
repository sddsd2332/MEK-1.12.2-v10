package mekanism.common.recipe.machines;

import mekanism.api.gas.Gas;
import mekanism.common.recipe.inputs.AdvancedMachineInput;
import mekanism.common.recipe.outputs.ChanceOutput;
import net.minecraft.item.ItemStack;

public class FarmRecipe extends FarmMachineRecipe<FarmRecipe> {


    public FarmRecipe(AdvancedMachineInput input, ChanceOutput output) {
        super(input, output);
    }


    public FarmRecipe(ItemStack itemStack, Gas gas, ItemStack primaryOutput, ItemStack secondaryOutput, double chance) {
        this(new AdvancedMachineInput(itemStack, gas), new ChanceOutput(primaryOutput, secondaryOutput, chance));
    }

    public FarmRecipe(ItemStack itemStack, Gas gas, ItemStack primaryOutput) {
        this(new AdvancedMachineInput(itemStack, gas), new ChanceOutput(primaryOutput));
    }

    @Override
    public FarmRecipe copy() {
        return new FarmRecipe(getInput().copy(), getOutput().copy());
    }
}
