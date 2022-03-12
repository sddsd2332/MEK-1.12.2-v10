package mekanism.client.jei.machine;

import mekanism.api.gas.Gas;
import mekanism.api.gas.GasStack;
import mekanism.client.jei.MekanismJEI;
import mekanism.common.recipe.GasConversionHandler;
import mekanism.common.recipe.machines.FarmMachineRecipe;
import mekanism.common.recipe.outputs.ChanceOutput;
import mekanism.common.tile.TileEntityFarmMachine;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class FarmMachineRecipeWrapper<RECIPE extends FarmMachineRecipe<RECIPE>> extends MekanismRecipeWrapper<RECIPE> {

    public FarmMachineRecipeWrapper(RECIPE recipe) {
        super(recipe);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ChanceOutput output = recipe.getOutput();
        ingredients.setInput(VanillaTypes.ITEM, recipe.getInput().itemStack);
        ingredients.setInput(MekanismJEI.TYPE_GAS, new GasStack(recipe.getInput().gasType, TileEntityFarmMachine.BASE_TICKS_REQUIRED * TileEntityFarmMachine.BASE_GAS_PER_TICK));
        ingredients.setOutputs(VanillaTypes.ITEM, Arrays.asList(output.primaryOutput, output.secondaryOutput));
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        ChanceOutput output = recipe.getOutput();
        if (output.hasSecondary()) {
            FontRenderer fontRendererObj = minecraft.fontRenderer;
            fontRendererObj.drawString(Math.round(output.secondaryChance * 100) + "%", 104, 41, 0x404040, false);
        }
    }

    public List<ItemStack> getFuelStacks(Gas gasType) {
        return GasConversionHandler.getStacksForGas(gasType);
    }

}