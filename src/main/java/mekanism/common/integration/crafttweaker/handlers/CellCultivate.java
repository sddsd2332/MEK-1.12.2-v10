package mekanism.common.integration.crafttweaker.handlers;


import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import mekanism.api.gas.Gas;
import mekanism.common.Mekanism;
import mekanism.common.integration.crafttweaker.CrafttweakerIntegration;
import mekanism.common.integration.crafttweaker.gas.IGasStack;
import mekanism.common.integration.crafttweaker.helpers.GasHelper;
import mekanism.common.integration.crafttweaker.helpers.IngredientHelper;
import mekanism.common.integration.crafttweaker.util.AddMekanismRecipe;
import mekanism.common.integration.crafttweaker.util.IngredientWrapper;
import mekanism.common.integration.crafttweaker.util.RemoveAllMekanismRecipe;
import mekanism.common.integration.crafttweaker.util.RemoveMekanismRecipe;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.recipe.inputs.CultivateMachineInput;
import mekanism.common.recipe.machines.CellCultivateRecipe;
import mekanism.common.recipe.outputs.ItemStackOutput;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;

@ZenClass("mods.mekanism.cellcultivate")
@ZenRegister
public class CellCultivate {

    public static final String NAME = Mekanism.MOD_NAME + " cellcultivate";

    @ZenMethod
    public static void addRecipe(IIngredient ingredientInput, IIngredient ingredientExtra, IGasStack gasInput, IItemStack itemOutput) {
        if (IngredientHelper.checkNotNull(NAME, ingredientInput, ingredientExtra, gasInput, itemOutput)) {
            ItemStackOutput output = new ItemStackOutput(CraftTweakerMC.getItemStack(itemOutput));
            Gas gas = GasHelper.toGas(gasInput).getGas();
            List<CellCultivateRecipe> recipes = new ArrayList<>();
            ItemStack[] extraInputs = CraftTweakerMC.getIngredient(ingredientExtra).getMatchingStacks();
            for (ItemStack stack : CraftTweakerMC.getIngredient(ingredientInput).getMatchingStacks()) {
                for (ItemStack extra : extraInputs) {
                    recipes.add(new CellCultivateRecipe(new CultivateMachineInput(stack, extra, gas), output));
                }
                CrafttweakerIntegration.LATE_ADDITIONS.add(new AddMekanismRecipe<>(NAME, RecipeHandler.Recipe.CELL_CULTIVATE, recipes));
            }
        }
    }

    @ZenMethod
    public static void removeRecipe(IIngredient itemOutput, @Optional IIngredient itemInput, @Optional IIngredient extraInput, @Optional IIngredient gasInput) {
        if (IngredientHelper.checkNotNull(NAME, itemOutput)) {
            CrafttweakerIntegration.LATE_REMOVALS.add(new RemoveMekanismRecipe<>(NAME, RecipeHandler.Recipe.CELL_CULTIVATE, new IngredientWrapper(itemOutput),
                    new IngredientWrapper(itemInput, extraInput, gasInput)));
        }
    }


    @ZenMethod
    public static void removeAllRecipes() {
        CrafttweakerIntegration.LATE_REMOVALS.add(new RemoveAllMekanismRecipe<>(NAME, RecipeHandler.Recipe.CELL_CULTIVATE));
    }


}
