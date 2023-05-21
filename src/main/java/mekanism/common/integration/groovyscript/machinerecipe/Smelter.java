package mekanism.common.integration.groovyscript.machinerecipe;

import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.compat.mods.mekanism.recipe.VirtualizedMekanismRegistry;
import com.cleanroommc.groovyscript.helper.ingredient.IngredientHelper;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.recipe.inputs.ItemStackInput;
import mekanism.common.recipe.machines.SmeltingRecipe;
import net.minecraft.item.ItemStack;

public class Smelter extends VirtualizedMekanismRegistry<SmeltingRecipe> {

    private static boolean removedRecipe = false;
    private static boolean addedRecipe = false;

    public static boolean hasRemovedRecipe() {
        return removedRecipe;
    }

    public static boolean hasAddedRecipe() {
        return addedRecipe;
    }

    public Smelter() {
        super(RecipeHandler.Recipe.ENERGIZED_SMELTER, "Smelter", "smelter", "Energized_Smelter");
    }

    public SmeltingRecipe add(IIngredient ingredient, ItemStack output) {
        GroovyLog.Msg msg = GroovyLog.msg("Error adding Energized Smelter recipe").error();
        msg.add(IngredientHelper.isEmpty(ingredient), () -> "input must not be empty");
        msg.add(IngredientHelper.isEmpty(output), () -> "output must not be empty");
        if (msg.postIfNotEmpty()) return null;
        output = output.copy();
        SmeltingRecipe recipe1 = null;
        for (ItemStack itemStack : ingredient.getMatchingStacks()) {
            SmeltingRecipe recipe = new SmeltingRecipe(itemStack.copy(), output);
            if (recipe1 == null) recipe1 = recipe;
            recipeRegistry.put(recipe);
            addScripted(recipe);
            addedRecipe = true;
        }
        return recipe1;
    }

    public boolean removeByInput(IIngredient ingredient) {
        if (IngredientHelper.isEmpty(ingredient)) {
            removeError("input must not be empty");
            return false;
        }
        boolean found = false;
        for (ItemStack itemStack : ingredient.getMatchingStacks()) {
            SmeltingRecipe recipe = recipeRegistry.get().remove(new ItemStackInput(itemStack));
            if (recipe != null) {
                addBackup(recipe);
                found = true;
                removedRecipe = true;
            }
        }
        if (!found) {
            removeError("could not find recipe for %s", ingredient);
        }
        return found;
    }
}
