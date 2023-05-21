package mekanism.client.jei.machine.other;

import mekanism.api.gas.GasStack;
import mekanism.client.jei.BaseRecipeCategory;
import mekanism.client.jei.MekanismJEI;
import mekanism.common.recipe.RecipeHandler.Recipe;
import mekanism.common.recipe.machines.IsotopicRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiIngredientGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;

public class IsotopicRecipeCategory<WRAPPER extends IsotopicRecipeWrapper<IsotopicRecipe>> extends BaseRecipeCategory<WRAPPER> {

    public IsotopicRecipeCategory(IGuiHelper helper) {
        super(helper, "mekanism:gui/nei/GuiSolarNeutronActivator.png", Recipe.ISOTOPIC_CENTRIFUGE.getJEICategory(),
                "tile.MachineBlock3.IsotopicCentrifuge.name", null, 3, 12, 170, 63);
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        super.drawExtras(minecraft);
        drawTexturedRect(64 - xOffset, 39 - yOffset, 176, 58, (int)(55* ((float) timer.getValue() / 20F)), 8);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, WRAPPER recipeWrapper, IIngredients ingredients) {
        IsotopicRecipe tempRecipe = recipeWrapper.getRecipe();
        IGuiIngredientGroup<GasStack> gasStacks = recipeLayout.getIngredientsGroup(MekanismJEI.TYPE_GAS);
        initGas(gasStacks, 0, true, 26 - xOffset, 14 - yOffset, 16, 58, tempRecipe.recipeInput.ingredient, true);
        initGas(gasStacks, 1, false, 134 - xOffset, 14 - yOffset, 16, 58, tempRecipe.recipeOutput.output, true);
    }
}