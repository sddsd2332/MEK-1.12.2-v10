package mekanism.common.integration.groovyscript.machinerecipe;

import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.api.IIngredient;
import com.cleanroommc.groovyscript.compat.mods.mekanism.Mekanism;
import com.cleanroommc.groovyscript.compat.mods.mekanism.recipe.VirtualizedMekanismRegistry;
import com.cleanroommc.groovyscript.helper.ingredient.IngredientHelper;
import com.cleanroommc.groovyscript.helper.recipe.AbstractRecipeBuilder;
import mekanism.api.gas.GasStack;
import mekanism.common.integration.groovyscript.GrSMekanismAdd;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.recipe.inputs.NucleosynthesizerInput;
import mekanism.common.recipe.machines.NucleosynthesizerRecipe;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AntiprotonicNucleosynthesizer extends VirtualizedMekanismRegistry<NucleosynthesizerRecipe> {

    public AntiprotonicNucleosynthesizer() {
        super(RecipeHandler.Recipe.ANTIPROTONIC_NUCLEOSYNTHESIZER, "AntiprotonicNucleosynthesizer", "antiprotonic_nucleosynthesizer");
    }

    public RecipeBuilder recipeBuilder() {
        return new RecipeBuilder();
    }


    public NucleosynthesizerRecipe add(IIngredient inputSolid, GasStack inputGas,  ItemStack outputSolid, double energy, int duration){
        NucleosynthesizerRecipe r =null;
        for (ItemStack item  : inputSolid.getMatchingStacks()){
            NucleosynthesizerRecipe recipe = new NucleosynthesizerRecipe(item,inputGas.copy(),outputSolid.copy(),energy,duration);
            if (r == null) r= recipe;
            recipeRegistry.put(recipe);
            addScripted(recipe);
        }
        return r;
    }

    public boolean removeByInput(IIngredient inputSolid, GasStack inputGas) {
        if (GroovyLog.msg("Error removing Mekanism Antiprotonic Nucleosynthesizer recipe").error()
                .add(IngredientHelper.isEmpty(inputSolid), () -> "item input must not be empty")
                .add(Mekanism.isEmpty(inputGas), () -> "input gas must not be empty")
                .error()
                .postIfNotEmpty()) {
            return false;
        }
        boolean found = false;
        for (ItemStack itemStack : inputSolid.getMatchingStacks()) {
            NucleosynthesizerRecipe recipe = recipeRegistry.get().remove(new NucleosynthesizerInput(itemStack, inputGas));
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




    public static class RecipeBuilder extends AbstractRecipeBuilder<NucleosynthesizerRecipe> {

        private final List<GasStack> gasInput = new ArrayList<>();
        private int duration;
        private double energy;

        public RecipeBuilder gasInput(GasStack gas) {
            this.gasInput.add(gas);
            return this;
        }

        public RecipeBuilder gasInput(Collection<GasStack> gases) {
            if (gases != null && !gases.isEmpty()) {
                for (GasStack gas : gasInput) {
                    gasInput(gas);
                }
            }
            return this;
        }

        public RecipeBuilder gasInput(GasStack... gases) {
            if (gases != null && gases.length > 0) {
                for (GasStack gas : gasInput) {
                    gasInput(gas);
                }
            }
            return this;
        }



        public RecipeBuilder durazqtion(int duration) {
            this.duration = duration;
            return this;
        }

        public RecipeBuilder energy(double energy) {
            this.energy = energy;
            return this;
        }

        @Override
        public String getErrorMsg() {
            return "Error adding Mekanism Antiprotonic Nucleosynthesizer recipe";
        }

        @Override
        public void validate(GroovyLog.Msg msg) {
            validateItems(msg, 1, 1, 1, 1);
            this.gasInput.removeIf(Mekanism::isEmpty);
            msg.add(this.gasInput.size() != 1, () -> getRequiredString(1, 1, " gas input") + ", but found " + this.gasInput.size());
            if (duration <= 0) duration = 100;
            if (energy <= 0) energy = 8000;
        }

        @Override
        public @Nullable NucleosynthesizerRecipe register() {
            if (!validate()) return null;
            return GrSMekanismAdd.modSupportContainer.get().antiprotonicNucleosynthesizer.add(input.get(0), gasInput.get(0), output.get(0), energy, duration);
        }
    }

}
