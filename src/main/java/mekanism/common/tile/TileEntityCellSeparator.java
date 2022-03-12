package mekanism.common.tile;

import mekanism.common.block.states.BlockStateMachine.MachineType;
import mekanism.common.recipe.RecipeHandler.Recipe;
import mekanism.common.recipe.inputs.ItemStackInput;
import mekanism.common.recipe.machines.CellSeparatorRecipe;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;

import java.util.Map;

public class TileEntityCellSeparator extends TileEntityChanceMachine<CellSeparatorRecipe> {

    public TileEntityCellSeparator() {
        super("sawmill", MachineType.CELL_SEPARATOR, 200, MekanismUtils.getResource(ResourceType.GUI, "GuiBasicMachine.png"));
    }

    @Override
    public Map<ItemStackInput, CellSeparatorRecipe> getRecipes() {
        return Recipe.CELL_SEPARATOR.get();
    }
}