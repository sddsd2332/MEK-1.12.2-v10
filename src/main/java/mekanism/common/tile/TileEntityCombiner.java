package mekanism.common.tile;

import mekanism.common.block.states.BlockStateMachine.MachineType;
import mekanism.common.recipe.RecipeHandler.Recipe;
import mekanism.common.recipe.inputs.DoubleMachineInput;
import mekanism.common.recipe.machines.CombinerRecipe;
import mekanism.common.tile.prefab.TileEntityDoubleElectricMachine;

import java.util.Map;

public class TileEntityCombiner extends TileEntityDoubleElectricMachine<CombinerRecipe> {

    public TileEntityCombiner() {
        super("combiner", MachineType.COMBINER, 200);
    }

    @Override
    public Map<DoubleMachineInput, CombinerRecipe> getRecipes() {
        return Recipe.COMBINER.get();
    }
}