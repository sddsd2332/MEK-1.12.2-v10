package mekanism.common.integration.crafttweaker.handlers;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.minecraft.CraftTweakerMC;
import mekanism.api.gas.GasStack;
import mekanism.common.Mekanism;
import mekanism.common.integration.crafttweaker.CrafttweakerIntegration;
import mekanism.common.integration.crafttweaker.gas.IGasStack;
import mekanism.common.integration.crafttweaker.helpers.GasHelper;
import mekanism.common.integration.crafttweaker.helpers.IngredientHelper;
import mekanism.common.integration.crafttweaker.util.AddMekanismRecipe;
import mekanism.common.integration.crafttweaker.util.IngredientWrapper;
import mekanism.common.integration.crafttweaker.util.RemoveAllMekanismRecipe;
import mekanism.common.integration.crafttweaker.util.RemoveMekanismRecipe;
import mekanism.common.recipe.RecipeHandler.Recipe;
import mekanism.common.recipe.machines.NutritionalRecipe;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;

@ZenClass("mods.mekanism.nutritionalliquifier")
@ZenRegister
public class NutritionalLiquifier {

    public static final String NAME = Mekanism.MOD_NAME + " Nutritional Liquifier";

    @ZenMethod
    public static void addRecipe(IIngredient ingredientInput, IGasStack gasOutput) {
        if (IngredientHelper.checkNotNull(NAME, ingredientInput, gasOutput)) {
            GasStack output = GasHelper.toGas(gasOutput);
            List<NutritionalRecipe> recipes = new ArrayList<>();
            for (ItemStack stack : CraftTweakerMC.getIngredient(ingredientInput).getMatchingStacks()) {
                recipes.add(new NutritionalRecipe(stack, output));
            }
            CrafttweakerIntegration.LATE_ADDITIONS.add(new AddMekanismRecipe<>(NAME, Recipe.Nutritional_Liquifier, recipes));
        }
    }

    @ZenMethod
    public static void removeRecipe(IIngredient gasOutput, @Optional IIngredient itemInput) {
        if (IngredientHelper.checkNotNull(NAME, gasOutput)) {
            CrafttweakerIntegration.LATE_REMOVALS.add(new RemoveMekanismRecipe<>(NAME, Recipe.Nutritional_Liquifier, new IngredientWrapper(gasOutput),
                    new IngredientWrapper(itemInput)));
        }
    }

    @ZenMethod
    public static void removeAllRecipes() {
        CrafttweakerIntegration.LATE_REMOVALS.add(new RemoveAllMekanismRecipe<>(NAME, Recipe.Nutritional_Liquifier));
    }
}