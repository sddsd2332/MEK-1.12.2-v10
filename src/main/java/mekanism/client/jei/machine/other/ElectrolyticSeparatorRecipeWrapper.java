package mekanism.client.jei.machine.other;

import mekanism.client.jei.MekanismJEI;
import mekanism.client.jei.machine.MekanismRecipeWrapper;
import mekanism.common.recipe.machines.SeparatorRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;

import java.util.Arrays;

public class ElectrolyticSeparatorRecipeWrapper<RECIPE extends SeparatorRecipe> extends MekanismRecipeWrapper<RECIPE> {

    public ElectrolyticSeparatorRecipeWrapper(RECIPE recipe) {
        super(recipe);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.FLUID, recipe.recipeInput.ingredient);
        ingredients.setOutputs(MekanismJEI.TYPE_GAS, Arrays.asList(recipe.recipeOutput.leftGas, recipe.recipeOutput.rightGas));
    }
}