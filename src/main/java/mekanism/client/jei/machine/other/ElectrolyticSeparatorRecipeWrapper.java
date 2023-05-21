package mekanism.client.jei.machine.other;

import mekanism.client.jei.MekanismJEI;
import mekanism.client.jei.machine.MekanismRecipeWrapper;
import mekanism.common.config.MekanismConfig;
import mekanism.common.recipe.machines.SeparatorRecipe;
import mekanism.common.util.LangUtils;
import mekanism.common.util.MekanismUtils;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ElectrolyticSeparatorRecipeWrapper<RECIPE extends SeparatorRecipe> extends MekanismRecipeWrapper<RECIPE> {

    public ElectrolyticSeparatorRecipeWrapper(RECIPE recipe) {
        super(recipe);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.FLUID, recipe.recipeInput.ingredient);
        ingredients.setOutputs(MekanismJEI.TYPE_GAS, Arrays.asList(recipe.recipeOutput.leftGas, recipe.recipeOutput.rightGas));
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        List<String> tooltip = new ArrayList<>();
        if (mouseX >= 162 && mouseX < 166 && mouseY >= 6 && mouseY < 6 + 52) {
            tooltip.add(LangUtils.localize("gui.additionalprocessingdescription") + ":" + MekanismUtils.convertToDisplay(recipe.energyUsage) + " " + MekanismConfig.current().general.energyUnit.val() + "/" + "tick");
        }
        return tooltip;
    }
}