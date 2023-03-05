package mekanism.client.jei.machine;

import mekanism.common.recipe.machines.Chance2MachineRecipe;
import mekanism.common.recipe.outputs.ChanceOutput2;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class Chance2MachineRecipeWrapper<RECIPE extends Chance2MachineRecipe<RECIPE>> extends MekanismRecipeWrapper<RECIPE> {

    public Chance2MachineRecipeWrapper(RECIPE recipe) {
        super(recipe);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ChanceOutput2 output = recipe.getOutput();
        ingredients.setInput(VanillaTypes.ITEM, recipe.getInput().ingredient);
        ingredients.setOutputs(VanillaTypes.ITEM, Arrays.asList(output.primaryOutput));
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        ChanceOutput2 output = recipe.getOutput();
        if (output.hasPrimary()) {
            FontRenderer fontRendererObj = minecraft.fontRenderer;
            fontRendererObj.drawString(Math.round(output.primaryChance * 100) + "%", 91, 41, 0x404040, false);
        }
    }
}