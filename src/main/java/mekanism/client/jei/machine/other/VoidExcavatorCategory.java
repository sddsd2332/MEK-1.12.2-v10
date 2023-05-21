package mekanism.client.jei.machine.other;

import mekanism.api.gas.GasStack;
import mekanism.client.gui.element.GuiPowerBar;
import mekanism.client.gui.element.GuiProgress;
import mekanism.client.gui.element.GuiProgress.*;
import mekanism.client.gui.element.GuiSlot;
import mekanism.client.gui.element.gauge.GuiFluidGauge;
import mekanism.client.gui.element.gauge.GuiGasGauge;
import mekanism.client.gui.element.gauge.GuiGauge;
import mekanism.client.jei.BaseRecipeCategory;
import mekanism.client.jei.MekanismJEI;
import mekanism.common.recipe.RecipeHandler.Recipe;
import mekanism.common.recipe.machines.VoidRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiIngredientGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;



public class VoidExcavatorCategory<WRAPPER extends VoidExcavatorRecipeWrapper<VoidRecipe>> extends BaseRecipeCategory<WRAPPER> {

    public VoidExcavatorCategory(IGuiHelper helper) {
        super(helper, "mekanism:gui/GuiVoidExcavator.png", Recipe.VOID.getJEICategory(),
                "tile.MachineBlock4.Void.name", ProgressBar.LARGE_RIGHT, 6, 12, 213 - 6, 91 - 12);
    }

    @Override
    protected void addGuiElements() {
        guiElements.add(new GuiSlot(GuiSlot.SlotType.INPUT, this, guiLocation, 49, 65)); //INPUT
        guiElements.add(GuiFluidGauge.getDummy(GuiGauge.Type.STANDARD_YELLOW, this, guiLocation, 7, 13));
        guiElements.add(GuiGasGauge.getDummy(GuiGauge.Type.STANDARD_ORANGE, this, guiLocation, 28, 13));
        guiElements.add(new GuiSlot(GuiSlot.SlotType.OUTPUT, this, guiLocation, 153, 65)); //OUTPUT
        guiElements.add(GuiGasGauge.getDummy(GuiGauge.Type.STANDARD_ORANGE, this, guiLocation, 174, 13));
        guiElements.add(GuiFluidGauge.getDummy(GuiGauge.Type.STANDARD_YELLOW, this, guiLocation, 195, 13));
        guiElements.add(new GuiProgress(new IProgressInfoHandler() {
            @Override
            public double getProgress() {
                return (float) timer.getValue() / 20F;
            }
        }, progressBar, this, guiLocation, 86 - xOffset, 70 - yOffset));
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        super.drawExtras(minecraft);
        drawTexturedRect(9 - xOffset, 86 - yOffset, 0, 185, 203, 4);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, WRAPPER recipeWrapper, IIngredients ingredients) {
        VoidRecipe tempRecipe = recipeWrapper.getRecipe();
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        itemStacks.init(0, true, 49 - xOffset, 65 - yOffset);
        itemStacks.init(1, false, 153 - xOffset, 65 - yOffset);
        itemStacks.set(0, tempRecipe.recipeInput.getSolid());
        itemStacks.set(1, tempRecipe.recipeOutput.getItemOutput());
        IGuiFluidStackGroup fluidStacks = recipeLayout.getFluidStacks();
        fluidStacks.init(0, true, 8 - xOffset, 14 - yOffset, 16, 58, tempRecipe.getInput().getFluid().amount, false, fluidOverlayLarge);
        fluidStacks.init(1, false, 196 - xOffset, 14 - yOffset, 16, 58, tempRecipe.getInput().getFluid().amount, false, fluidOverlayLarge);
        fluidStacks.set(0, tempRecipe.recipeInput.getFluid());
        fluidStacks.set(1, tempRecipe.recipeOutput.getFluidOutput());
        IGuiIngredientGroup<GasStack> gasStacks = recipeLayout.getIngredientsGroup(MekanismJEI.TYPE_GAS);
        initGas(gasStacks, 0, true, 29 - xOffset, 14 - yOffset, 16, 58, tempRecipe.recipeInput.getGas(), true);
        initGas(gasStacks, 1, false, 175 - xOffset, 14 - yOffset, 16, 58, tempRecipe.recipeOutput.getGasOutput(), true);
    }

}