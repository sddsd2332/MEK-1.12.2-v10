package mekanism.client.jei.machine.other;

import mekanism.client.jei.BaseRecipeCategory;
import mekanism.common.recipe.RecipeHandler.Recipe;
import mekanism.common.recipe.machines.FusionCoolingRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;

public class FusionCoolingRecipeCategory <WRAPPER extends FusionCoolingRecipeWrapper<FusionCoolingRecipe>> extends BaseRecipeCategory<WRAPPER> {

    public FusionCoolingRecipeCategory(IGuiHelper helper) {
        super(helper,"mekanism:gui/nei/GuiSolarNeutronActivator.png",
                Recipe.FUSION_COOLING.getJEICategory(),"gui.FusionCooling",null, 24, 12, 130, 63);
    }
    @Override
    public void drawExtras(Minecraft minecraft) {
        super.drawExtras(minecraft);
        drawTexturedRect(64 - xOffset, 39 - yOffset, 176, 58, 55, 8);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, WRAPPER recipeWrapper, IIngredients ingredients){
        FusionCoolingRecipe tempRecipe = recipeWrapper.getRecipe();
        IGuiFluidStackGroup fluidStacks = recipeLayout.getFluidStacks();
        fluidStacks.init(0, true,26 - xOffset, 14 - yOffset, 16, 58, tempRecipe.getInput().ingredient.amount, false, fluidOverlayLarge);
        fluidStacks.init(1, false, 134 - xOffset, 14 - yOffset, 16, 58, tempRecipe.getOutput().output.amount, false, fluidOverlayLarge);
        fluidStacks.set(0, tempRecipe.recipeInput.ingredient);
        fluidStacks.set(1, tempRecipe.recipeOutput.output);
    }
}
