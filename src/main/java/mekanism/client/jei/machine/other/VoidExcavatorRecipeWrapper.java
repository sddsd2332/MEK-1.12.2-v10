package mekanism.client.jei.machine.other;

import mekanism.client.jei.MekanismJEI;
import mekanism.client.jei.machine.MekanismRecipeWrapper;
import mekanism.common.config.MekanismConfig;
import mekanism.common.recipe.inputs.CompositeProbabilityInput;
import mekanism.common.recipe.machines.VoidRecipe;
import mekanism.common.recipe.outputs.CompositeProbabilityOutput;
import mekanism.common.util.LangUtils;
import mekanism.common.util.MekanismUtils;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;


public class VoidExcavatorRecipeWrapper<RECIPE extends VoidRecipe> extends MekanismRecipeWrapper<RECIPE> {

    public VoidExcavatorRecipeWrapper(RECIPE recipe) {
        super(recipe);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.ITEM, recipe.recipeInput.getSolid());
        ingredients.setInput(VanillaTypes.FLUID, recipe.recipeInput.getFluid());
        ingredients.setInput(MekanismJEI.TYPE_GAS, recipe.recipeInput.getGas());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.recipeOutput.getItemOutput());
        ingredients.setOutput(VanillaTypes.FLUID, recipe.recipeOutput.getFluidOutput());
        ingredients.setOutput(MekanismJEI.TYPE_GAS, recipe.recipeOutput.getGasOutput());
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        FontRenderer fontRendererObj = minecraft.fontRenderer;
        CompositeProbabilityInput input = recipe.getInput();
        CompositeProbabilityOutput output = recipe.getOutput();
        if (output != null && input != null) {
            fontRendererObj.drawString(LangUtils.localize("gui.dimension2") + ":" + input.ingredient, 44, 2, 0x33ff99);
            fontRendererObj.drawString(LangUtils.localize("gui.itemInputoutprobability") + ":" + Math.round(input.itemStackChance * 100) + "%" + "/" + Math.round(output.ItemStackOutputChance * 100) + "%", 44, 11, 0x33ff99);
            fontRendererObj.drawString(LangUtils.localize("gui.fluidInputoutprobability") + ":" + Math.round(input.theFluidChance * 100) + "%" + "/" + Math.round(output.fluidOutputChance * 100) + "%", 44, 20, 0x33ff99);
            fontRendererObj.drawString(LangUtils.localize("gui.gasInputoutprobability") + ":" + Math.round(input.theGasChance * 100) + "%" + "/" + Math.round(output.gasOutputChance * 100) + "%", 44, 29, 0x33ff99);
            fontRendererObj.drawString(LangUtils.localize("gui.additionalprocessingdescription") + ":" + MekanismUtils.convertToDisplay(MekanismConfig.current().usage.voidMiner.val() + recipe.extraEnergy) + " " + MekanismConfig.current().general.energyUnit.val() + "/" + recipe.ticks + " " + "tick", 44, 38, 0x33ff99);
        }
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        List<String> tooltip = new ArrayList<>();
        if (mouseX >= 3  && mouseX < 206  && mouseY >= 74  && mouseY < 78 ) {
            tooltip.add(LangUtils.localize("gui.additionalprocessingdescription") + ":" + MekanismUtils.convertToDisplay(MekanismConfig.current().usage.pressurizedReactionBase.val() + recipe.extraEnergy) + " " + MekanismConfig.current().general.energyUnit.val() + "/" + recipe.ticks + " " + "tick");
        }
        return tooltip;
    }
}
