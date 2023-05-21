package mekanism.client.jei.machine.other;

import mekanism.client.jei.machine.MekanismRecipeWrapper;
import mekanism.common.InfuseStorage;
import mekanism.common.config.MekanismConfig;
import mekanism.common.recipe.machines.MetallurgicInfuserRecipe;
import mekanism.common.util.LangUtils;
import mekanism.common.util.MekanismUtils;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MetallurgicInfuserRecipeWrapper<RECIPE extends MetallurgicInfuserRecipe> extends MekanismRecipeWrapper<RECIPE> {

    public MetallurgicInfuserRecipeWrapper(RECIPE recipe) {
        super(recipe);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        List<ItemStack> inputStacks = Collections.singletonList(recipe.recipeInput.inputStack);
        List<ItemStack> infuseStacks = MetallurgicInfuserRecipeCategory.getInfuseStacks(recipe.getInput().infuse.getType());
        ingredients.setInput(VanillaTypes.ITEM, recipe.recipeInput.inputStack);
        ingredients.setInputLists(VanillaTypes.ITEM, Arrays.asList(inputStacks, infuseStacks));
        ingredients.setOutput(VanillaTypes.ITEM, recipe.recipeOutput.output);
    }

    @Override
    public void drawInfo(Minecraft mc, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        if (mc.currentScreen != null) {
            mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            mc.currentScreen.drawTexturedModalRect(2, 2, recipe.getInput().infuse.getType().sprite, 4, 52);
        }
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        List<String> tooltip = new ArrayList<>();
        if (mouseX >= 2 && mouseX < 6 && mouseY >= 2 && mouseY < 54) {
            InfuseStorage infuse = recipe.getInput().infuse;
             tooltip.add(infuse.getType().getLocalizedName() + ": " + infuse.getAmount());
        } else if (mouseX >= 162 && mouseX < 166 && mouseY >= 6 && mouseY < 6 + 52) {
            tooltip.add(LangUtils.localize("gui.additionalprocessingdescription") + ":" + MekanismUtils.convertToDisplay(MekanismConfig.current().usage.metallurgicInfuser.val()) + " " + MekanismConfig.current().general.energyUnit.val() + "/" + " " + "tick");
        }
        return tooltip;
    }
}