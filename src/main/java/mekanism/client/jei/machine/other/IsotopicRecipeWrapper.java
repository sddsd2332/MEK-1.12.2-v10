package mekanism.client.jei.machine.other;

import mekanism.client.jei.MekanismJEI;
import mekanism.client.jei.machine.MekanismRecipeWrapper;
import mekanism.common.recipe.machines.IsotopicRecipe;
import mezz.jei.api.ingredients.IIngredients;

public class IsotopicRecipeWrapper<RECIPE extends IsotopicRecipe> extends MekanismRecipeWrapper<RECIPE> {

    public IsotopicRecipeWrapper(RECIPE recipe) {
        super(recipe);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(MekanismJEI.TYPE_GAS, recipe.getInput().ingredient);
        ingredients.setOutput(MekanismJEI.TYPE_GAS, recipe.getOutput().output);
    }
}