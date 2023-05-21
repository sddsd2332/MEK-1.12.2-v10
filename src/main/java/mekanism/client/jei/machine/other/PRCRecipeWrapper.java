package mekanism.client.jei.machine.other;

import mekanism.client.jei.MekanismJEI;
import mekanism.client.jei.machine.MekanismRecipeWrapper;
import mekanism.common.config.MekanismConfig;
import mekanism.common.recipe.machines.PressurizedRecipe;
import mekanism.common.util.LangUtils;
import mekanism.common.util.MekanismUtils;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import java.util.ArrayList;
import java.util.List;

public class PRCRecipeWrapper<RECIPE extends PressurizedRecipe> extends MekanismRecipeWrapper<RECIPE> {

    public PRCRecipeWrapper(RECIPE recipe) {
        super(recipe);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.ITEM, recipe.recipeInput.getSolid());
        ingredients.setInput(VanillaTypes.FLUID, recipe.recipeInput.getFluid());
        ingredients.setInput(MekanismJEI.TYPE_GAS, recipe.recipeInput.getGas());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.recipeOutput.getItemOutput());
        ingredients.setOutput(MekanismJEI.TYPE_GAS, recipe.recipeOutput.getGasOutput());
    }


    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        List<String> tooltip = new ArrayList<>();
        if (mouseX >= 162 && mouseX < 166 && mouseY >= 6 && mouseY < 6 + 52) {
            tooltip.add(LangUtils.localize("gui.additionalprocessingdescription") + ":" + MekanismUtils.convertToDisplay(MekanismConfig.current().usage.pressurizedReactionBase.val() + recipe.extraEnergy) + " " + MekanismConfig.current().general.energyUnit.val() + "/" + recipe.ticks + " " + "tick");
        }
        return tooltip;
    }
}