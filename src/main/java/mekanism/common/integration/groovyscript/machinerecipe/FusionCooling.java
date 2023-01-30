package mekanism.common.integration.groovyscript.machinerecipe;

import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.compat.mods.mekanism.recipe.VirtualizedMekanismRegistry;
import com.cleanroommc.groovyscript.helper.ingredient.IngredientHelper;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.recipe.inputs.FluidInput;
import mekanism.common.recipe.machines.FusionCoolingRecipe;
import net.minecraftforge.fluids.FluidStack;

public class FusionCooling extends VirtualizedMekanismRegistry<FusionCoolingRecipe> {

    public FusionCooling(){
        super(RecipeHandler.Recipe.FUSION_COOLING,"fc","FC","FusionReactor","ReactorController");
    }
    public FusionCoolingRecipe add(FluidStack input, FluidStack output) {
        GroovyLog.Msg msg = GroovyLog.msg("Error adding MekanismGenerators Fusion Reactor recipe").error();
        msg.add(IngredientHelper.isEmpty(input), () -> "input must not be empty");
        msg.add(IngredientHelper.isEmpty(output), () -> "output must not be empty");
        if (msg.postIfNotEmpty()) return null;
        FusionCoolingRecipe recipe = new FusionCoolingRecipe(input.copy(),output.copy());
        recipeRegistry.put(recipe);
        addScripted(recipe);
        return recipe;
    }
    public boolean removeByInput(FluidStack input) {
        GroovyLog.Msg msg = GroovyLog.msg("Error removing MekanismGenerators Fusion Reactor  recipe").error();
        msg.add(IngredientHelper.isEmpty(input), () -> "input must not be empty");
        if (msg.postIfNotEmpty()) return false;
        FusionCoolingRecipe recipe = recipeRegistry.get().remove(new FluidInput(input));
        if (recipe != null) {
            addBackup(recipe);
            return true;
        }
        removeError("could not find recipe for %", input);
        return false;
    }
}
