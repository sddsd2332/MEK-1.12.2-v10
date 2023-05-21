package mekanism.common.integration.crafttweaker.handlers;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
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
import mekanism.common.recipe.inputs.NucleosynthesizerInput;
import mekanism.common.recipe.machines.NucleosynthesizerRecipe;
import mekanism.common.recipe.outputs.NucleosynthesizerOutput;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;

@ZenClass("mods.mekanism.nucleosynthesizer")
@ZenRegister
public class Nucleosynthesizer {

    public static final String NAME = Mekanism.MOD_NAME + " Nucleosynthesizer";

    @ZenMethod
    public static void addRecipe(IIngredient ingredientInput, IGasStack gasInput, IItemStack itemOutput, double energy, int duration) {
        if (IngredientHelper.checkNotNull(NAME, ingredientInput, gasInput)) {
            GasStack gas = GasHelper.toGas(gasInput);
            NucleosynthesizerOutput output = new NucleosynthesizerOutput(CraftTweakerMC.getItemStack(itemOutput));
            List<NucleosynthesizerRecipe> recipes = new ArrayList<>();
            for (ItemStack stack : CraftTweakerMC.getIngredient(ingredientInput).getMatchingStacks()) {
                recipes.add(new NucleosynthesizerRecipe(new NucleosynthesizerInput(stack, gas), output, energy, duration));
            }
            CrafttweakerIntegration.LATE_ADDITIONS.add(new AddMekanismRecipe<>(NAME, Recipe.ANTIPROTONIC_NUCLEOSYNTHESIZER, recipes));
        }
    }

    @ZenMethod
    public static void removeRecipe(IIngredient itemOutput, @Optional IIngredient itemInput, @Optional IIngredient gasInput) {
        if (IngredientHelper.checkNotNull(NAME)) {
            CrafttweakerIntegration.LATE_REMOVALS.add(new RemoveMekanismRecipe<>(NAME, Recipe.ANTIPROTONIC_NUCLEOSYNTHESIZER, new IngredientWrapper(itemOutput),
                    new IngredientWrapper(itemInput, gasInput)));
        }
    }

    @ZenMethod
    public static void removeAllRecipes() {
        CrafttweakerIntegration.LATE_REMOVALS.add(new RemoveAllMekanismRecipe<>(NAME, Recipe.ANTIPROTONIC_NUCLEOSYNTHESIZER));
    }
}