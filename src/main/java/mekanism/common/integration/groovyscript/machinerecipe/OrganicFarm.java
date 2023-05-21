package mekanism.common.integration.groovyscript.machinerecipe;

import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.compat.mods.mekanism.Mekanism;
import com.cleanroommc.groovyscript.compat.mods.mekanism.recipe.VirtualizedMekanismRegistry;
import com.cleanroommc.groovyscript.helper.ingredient.IngredientHelper;
import mekanism.api.gas.GasStack;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.recipe.inputs.AdvancedMachineInput;
import mekanism.common.recipe.machines.FarmRecipe;
import mekanism.common.recipe.outputs.ChanceOutput;
import net.minecraft.item.ItemStack;

public class OrganicFarm extends VirtualizedMekanismRegistry<FarmRecipe> {

    public OrganicFarm() {
        super(RecipeHandler.Recipe.ORGANIC_FARM, "OrganicFarm", "Organic_Farm");
    }


    public FarmRecipe add(IIngredient ingredient, GasStack gasInput, ItemStack output) {
        return add(ingredient, gasInput, output, null, 0.0);

    }

    public FarmRecipe add(IIngredient ingredient, GasStack gasInput, ItemStack output, ItemStack secondary) {
        return add(ingredient, gasInput, output, secondary, 1.0);
    }

    public FarmRecipe add(IIngredient ingredient, GasStack gasInput, ItemStack output, ItemStack secondary, double chance) {
        GroovyLog.Msg msg = GroovyLog.msg("Error adding Mekanism Organic Farm recipe").error();
        msg.add(IngredientHelper.isEmpty(ingredient), () -> "input must not be empty");
        msg.add(Mekanism.isEmpty(gasInput), () -> "input must not be empty");
        msg.add(IngredientHelper.isEmpty(output), () -> "output must not be empty");
        if (msg.postIfNotEmpty()) return null;
        boolean withSecondary = !IngredientHelper.isEmpty(secondary);
        if (withSecondary) {
            if (chance <= 0) chance = 1;
            secondary = secondary.copy();
        }
        output = output.copy();
        FarmRecipe recipe1 = null;
        for (ItemStack itemStack : ingredient.getMatchingStacks()) {
            FarmRecipe recipe;
            ChanceOutput chanceOutput = withSecondary ? new ChanceOutput(output, secondary, chance) : new ChanceOutput(output);
            recipe = new FarmRecipe(new AdvancedMachineInput(itemStack.copy(), gasInput.getGas()), chanceOutput);
            if (recipe1 == null) recipe1 = recipe;
            recipeRegistry.put(recipe);
            addScripted(recipe);
        }
        return recipe1;
    }

    public boolean removeByInput(IIngredient inputSolid, GasStack inputGas) {
        if (GroovyLog.msg("Error removing Mekanism Organic Farm recipe").error()
                .add(IngredientHelper.isEmpty(inputSolid), () -> "item input must not be empty")
                .add(Mekanism.isEmpty(inputGas), () -> "input gas must not be empty")
                .error()
                .postIfNotEmpty()) {
            return false;
        }
        boolean found = false;
        for (ItemStack itemStack : inputSolid.getMatchingStacks()) {
            FarmRecipe recipe = recipeRegistry.get().remove(new AdvancedMachineInput(itemStack, inputGas.getGas()));
            if (recipe != null) {
                addBackup(recipe);
                found = true;
            }
        }
        if (!found) {
            removeError("could not find recipe for %s and %s", inputSolid, inputGas);
        }
        return found;
    }


}
