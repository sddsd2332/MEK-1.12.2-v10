package mekanism.client.jei.machine;

import mekanism.api.gas.Gas;
import mekanism.api.gas.GasStack;
import mekanism.client.jei.MekanismJEI;
import mekanism.common.recipe.GasConversionHandler;
import mekanism.common.recipe.inputs.CultivateMachineInput;
import mekanism.common.recipe.machines.CultivateMachineRecipe;
import mekanism.common.tile.prefab.TileEntityAdvancedElectricMachine;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class CultivateMachineRecipeWrapper<RECIPE extends CultivateMachineRecipe<RECIPE>> extends MekanismRecipeWrapper<RECIPE> {

    public CultivateMachineRecipeWrapper(RECIPE recipe) {
        super(recipe);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        CultivateMachineInput input = recipe.getInput();
        ingredients.setInputs(VanillaTypes.ITEM, Arrays.asList(input.itemStack, input.extraStack));
        ingredients.setInput(MekanismJEI.TYPE_GAS, new GasStack(recipe.getInput().gasType,
                TileEntityAdvancedElectricMachine.BASE_TICKS_REQUIRED * TileEntityAdvancedElectricMachine.BASE_GAS_PER_TICK));
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getOutput().output);
    }

    public List<ItemStack> getFuelStacks(Gas gasType) {
        return GasConversionHandler.getStacksForGas(gasType);
    }
}