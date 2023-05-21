package mekanism.common.integration.groovyscript.machinerecipe;

import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.compat.mods.mekanism.Mekanism;
import com.cleanroommc.groovyscript.compat.mods.mekanism.recipe.VirtualizedMekanismRegistry;
import mekanism.api.gas.GasStack;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.recipe.inputs.IntegerInput;
import mekanism.common.recipe.machines.AmbientGasRecipe;

public class AmbientAccumulator extends VirtualizedMekanismRegistry<AmbientGasRecipe> {

    public AmbientAccumulator() {
        super(RecipeHandler.Recipe.AMBIENT_ACCUMULATOR, "AmbientAccumulator", "ambient_accumulator");
    }

    public AmbientGasRecipe add(GasStack output) {
        return add(1, output);
    }


    public AmbientGasRecipe add(int dim, GasStack output) {
        GroovyLog.Msg msg = GroovyLog.msg("Error adding Mekanism Ambient Accumulator recipe").error();
        msg.add(Mekanism.isEmpty(output), () -> "output must not be empty");
        if (msg.postIfNotEmpty()) return null;

        AmbientGasRecipe recipe = new AmbientGasRecipe(dim, output.copy());
        recipeRegistry.put(recipe);
        addScripted(recipe);
        return recipe;
    }

    public boolean removeByInput(int dim) {
        GroovyLog.Msg msg = GroovyLog.msg("Error removing Mekanism Ambient Accumulator recipe").error();
        if (msg.postIfNotEmpty()) return false;

        AmbientGasRecipe recipe = recipeRegistry.get().remove(new IntegerInput(dim));
        if (recipe != null) {
            addBackup(recipe);
            return true;
        }
        removeError("could not find recipe for %", dim);
        return false;
    }

}