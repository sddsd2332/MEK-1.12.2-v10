package mekanism.common.integration.groovyscript.machinerecipe;

import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.compat.mods.mekanism.recipe.VirtualizedMekanismRegistry;
import com.cleanroommc.groovyscript.helper.ingredient.IngredientHelper;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.recipe.inputs.ItemStackInput;
import mekanism.common.recipe.machines.RecyclerRecipe;
import mekanism.common.recipe.outputs.ChanceOutput2;
import net.minecraft.item.ItemStack;

public class Recycler extends VirtualizedMekanismRegistry<RecyclerRecipe> {

    public Recycler() {
        super(RecipeHandler.Recipe.RECYCLER, "Recycler", "recycler");
    }


    public RecyclerRecipe add(IIngredient ingredient, ItemStack output) {
        return add(ingredient, output, 1.0);
    }

    public RecyclerRecipe add(IIngredient ingredient, ItemStack output, double chance) {
        GroovyLog.Msg msg = GroovyLog.msg("Error adding Mekanism Recycler recipe").error();
        msg.add(IngredientHelper.isEmpty(ingredient), () -> "input must not be empty");
        msg.add(IngredientHelper.isEmpty(output), () -> "output must not be empty");
        if (msg.postIfNotEmpty()) return null;
        boolean withSecondary = !IngredientHelper.isEmpty(output);
        if (withSecondary) {
            if (chance <= 0) chance = 1;
            output = output.copy();
        }
        RecyclerRecipe recipe1 = null;
        for (ItemStack itemStack : ingredient.getMatchingStacks()) {
            RecyclerRecipe recipe;
            ChanceOutput2 chanceOutput = new ChanceOutput2(output, chance);
            recipe = new RecyclerRecipe(new ItemStackInput(itemStack.copy()), chanceOutput);
            if (recipe1 == null) recipe1 = recipe;
            recipeRegistry.put(recipe);
            addScripted(recipe);
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
            RecyclerRecipe recipe = recipeRegistry.get().remove(new ItemStackInput(itemStack));
            if (recipe != null) {
                addBackup(recipe);
                found = true;
            }
        }
        if (!found) {
            removeError("could not find recipe for %s", ingredient);
        }
        return found;
    }


}
