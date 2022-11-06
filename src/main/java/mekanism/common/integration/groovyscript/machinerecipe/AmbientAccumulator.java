/*
package mekanism.common.integration.groovyscript.machinerecipe;

import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.compat.mods.mekanism.Mekanism;
import com.cleanroommc.groovyscript.compat.mods.mekanism.recipe.VirtualizedMekanismRegistry;
import mekanism.api.gas.GasStack;
import mekanism.common.integration.groovyscript.GrSMekanismAdd;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.recipe.inputs.IntegerInput;
import mekanism.common.recipe.machines.AmbientGasRecipe;

public class AmbientAccumulator extends VirtualizedMekanismRegistry<AmbientGasRecipe>{

    public AmbientAccumulator() {
        super(RecipeHandler.Recipe.AMBIENT_ACCUMULATOR,"AmbientAccumulator","ambient_accumulator");
    }

    public AmbientGasRecipe add(int ingredient, GasStack output) {
        GroovyLog.Msg msg = GroovyLog.msg("Error adding Mekanism Ambient Accumulator recipe").error();
        msg.add(GrSMekanismAdd.isEmpty(ingredient), () -> "input must not be empty");
        msg.add(Mekanism.isEmpty(output), () -> "output must not be empty");
        if (msg.postIfNotEmpty()) return null;

        AmbientGasRecipe recipe = new AmbientGasRecipe(ingredient, String.valueOf(output));
        recipeRegistry.put(recipe);
        addScripted(recipe);
        return recipe;
    }

    public boolean removeByInput(int ingredient) {
        GroovyLog.Msg msg = GroovyLog.msg("Error removing Mekanism Isotopic Centrifuge recipe").error();
        msg.add(GrSMekanismAdd.isEmpty(ingredient), () -> "input must not be empty");
        if (msg.postIfNotEmpty()) return false;

        AmbientGasRecipe recipe = recipeRegistry.get().remove(new IntegerInput(ingredient));
        if (recipe != null) {
            addBackup(recipe);
            return true;
        }
        removeError("could not find recipe for %", ingredient);
        return false;
    }

}
*/