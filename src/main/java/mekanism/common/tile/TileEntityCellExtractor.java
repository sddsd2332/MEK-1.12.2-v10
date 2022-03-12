package mekanism.common.tile;

import mekanism.common.block.states.BlockStateMachine.MachineType;
import mekanism.common.recipe.RecipeHandler.Recipe;
import mekanism.common.recipe.inputs.ItemStackInput;
import mekanism.common.recipe.machines.CellExtractorRecipe;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;

import java.util.Map;

public class TileEntityCellExtractor extends TileEntityChanceMachine<CellExtractorRecipe> {

    public TileEntityCellExtractor() {
        super("sawmill", MachineType.CELL_EXTRACTOR, 200, MekanismUtils.getResource(ResourceType.GUI, "GuiBasicMachine.png"));
    }

    @Override
    public Map<ItemStackInput, CellExtractorRecipe> getRecipes() {
        return Recipe.CELL_EXTRACTOR.get();
    }
}