package mekanism.client.jei.machine.other;

import mekanism.api.gas.GasStack;
import mekanism.client.gui.element.GuiPowerBar;
import mekanism.client.gui.element.GuiProgress;
import mekanism.client.gui.element.GuiSlot;
import mekanism.client.gui.element.gauge.GuiFluidGauge;
import mekanism.client.gui.element.gauge.GuiGasGauge;
import mekanism.client.gui.element.gauge.GuiGauge;
import mekanism.client.jei.BaseRecipeCategory;
import mekanism.client.jei.MekanismJEI;
import mekanism.common.recipe.RecipeHandler.Recipe;
import mekanism.common.recipe.machines.IsotopicRecipe;
import mekanism.common.recipe.machines.NucleosynthesizerRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiIngredientGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;

public class AntiprotonicNucleosynthesizerRecipeCategory<WRAPPER extends AntiprotonicNucleosynthesizerRecipeWrapper<NucleosynthesizerRecipe>> extends BaseRecipeCategory<WRAPPER> {

    public AntiprotonicNucleosynthesizerRecipeCategory(IGuiHelper helper) {
        super(helper, "mekanism:gui/nei/GuiPRC.png", Recipe.ANTIPROTONIC_NUCLEOSYNTHESIZER.getJEICategory(),
              "tile.MachineBlock3.antiprotonicnucleosynthesizer.name",  GuiProgress.ProgressBar.MEDIUM, 20, 10, 150, 60);
    }

    @Override
    protected void addGuiElements() {
        guiElements.add(new GuiSlot(GuiSlot.SlotType.INPUT, this, guiLocation, 53, 34));
        guiElements.add(new GuiSlot(GuiSlot.SlotType.POWER, this, guiLocation, 140, 18).with(GuiSlot.SlotOverlay.POWER));
        guiElements.add(new GuiSlot(GuiSlot.SlotType.OUTPUT, this, guiLocation, 115, 34));
        guiElements.add(GuiGasGauge.getDummy(GuiGauge.Type.STANDARD_RED, this, guiLocation, 28, 10));
        guiElements.add(new GuiPowerBar(this, new GuiPowerBar.IPowerInfoHandler() {
            @Override
            public double getLevel() {
                return 1F;
            }
        }, guiLocation, 164, 15));
        guiElements.add(new GuiProgress(new GuiProgress.IProgressInfoHandler() {
            @Override
            public double getProgress() {
                return (float) timer.getValue() / 20F;
            }
        }, progressBar, this, guiLocation, 75, 37));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, WRAPPER recipeWrapper, IIngredients ingredients) {
        NucleosynthesizerRecipe tempRecipe = recipeWrapper.getRecipe();
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        itemStacks.init(0, true, 53 - xOffset, 34 - yOffset);
        itemStacks.init(1, false, 115 - xOffset, 34 - yOffset);
        itemStacks.set(0, tempRecipe.recipeInput.getSolid());
        itemStacks.set(1, tempRecipe.recipeOutput.getItemOutput());
        IGuiIngredientGroup<GasStack> gasStacks = recipeLayout.getIngredientsGroup(MekanismJEI.TYPE_GAS);
        initGas(gasStacks, 0, true, 29 - xOffset, 11 - yOffset, 16, 58, tempRecipe.recipeInput.getGas(), true);
    }
}