package mekanism.common.integration.groovyscript.machinerecipe;

import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.compat.mods.mekanism.recipe.VirtualizedMekanismRegistry;
import com.cleanroommc.groovyscript.helper.ingredient.IngredientHelper;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.recipe.inputs.DoubleMachineInput;
import mekanism.common.recipe.machines.AlloyRecipe;
import net.minecraft.item.ItemStack;

public class Alloy extends VirtualizedMekanismRegistry<AlloyRecipe> {

    public Alloy() {
        super(RecipeHandler.Recipe.ALLOY, "Alloy", "alloy");
    }

    public AlloyRecipe add(IIngredient ingredient, ItemStack extra, ItemStack output) {
        GroovyLog.Msg msg = GroovyLog.msg("Error adding Mekanism Alloy recipe").error();
        msg.add(IngredientHelper.isEmpty(ingredient), () -> "input must not be empty");
        msg.add(IngredientHelper.isEmpty(extra), () -> "extra input must not be empty");
        msg.add(IngredientHelper.isEmpty(output), () -> "output must not be empty");
        if (msg.postIfNotEmpty()) return null;

        extra = extra.copy();
        output = output.copy();
        AlloyRecipe recipe1 = null;
        for (ItemStack itemStack : ingredient.getMatchingStacks()) {
            AlloyRecipe recipe = new AlloyRecipe(itemStack.copy(), extra, output);
            if (recipe1 == null) recipe1 = recipe;
            recipeRegistry.put(recipe);
            addScripted(recipe);
        }
        return recipe1;
    }

    public boolean removeByInput(IIngredient ingredient, ItemStack extra) {
        GroovyLog.Msg msg = GroovyLog.msg("Error removing Mekanism Alloy recipe").error();
        msg.add(IngredientHelper.isEmpty(ingredient), () -> "input must not be empty");
        msg.add(IngredientHelper.isEmpty(extra), () -> "extra input must not be empty");
        if (msg.postIfNotEmpty()) return false;

        boolean found = false;
        for (ItemStack itemStack : ingredient.getMatchingStacks()) {
            AlloyRecipe recipe = recipeRegistry.get().remove(new DoubleMachineInput(itemStack, extra));
            if (recipe != null) {
                addBackup(recipe);
                found = true;
            }
        }
        if (!found) {
            removeError("could not find recipe for %s and %s", ingredient, extra);
        }
        return found;
    }
}
