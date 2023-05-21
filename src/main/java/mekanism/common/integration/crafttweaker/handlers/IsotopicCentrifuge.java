package mekanism.common.integration.crafttweaker.handlers;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
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
import mekanism.common.recipe.machines.IsotopicRecipe;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.mekanism.isotopiccentrifuge")
@ZenRegister
public class IsotopicCentrifuge {

    public static final String NAME = Mekanism.MOD_NAME + " Isotopic Centrifuge";

    @ZenMethod
    public static void addRecipe(IGasStack gasInput, IGasStack gasOutput) {
        if (IngredientHelper.checkNotNull(NAME, gasInput, gasOutput)) {
            CrafttweakerIntegration.LATE_ADDITIONS.add(new AddMekanismRecipe<>(NAME, Recipe.ISOTOPIC_CENTRIFUGE, new IsotopicRecipe(GasHelper.toGas(gasInput),
                    GasHelper.toGas(gasOutput))));
        }
    }

    @ZenMethod
    public static void removeRecipe(IIngredient gasOutput, @Optional IIngredient gasInput) {
        if (IngredientHelper.checkNotNull(NAME, gasOutput)) {
            CrafttweakerIntegration.LATE_REMOVALS.add(new RemoveMekanismRecipe<>(NAME, Recipe.ISOTOPIC_CENTRIFUGE, new IngredientWrapper(gasOutput),
                    new IngredientWrapper(gasInput)));
        }
    }

    @ZenMethod
    public static void removeAllRecipes() {
        CrafttweakerIntegration.LATE_REMOVALS.add(new RemoveAllMekanismRecipe<>(NAME, Recipe.ISOTOPIC_CENTRIFUGE));
    }
}