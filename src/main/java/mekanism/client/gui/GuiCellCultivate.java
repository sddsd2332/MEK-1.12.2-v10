package mekanism.client.gui;

import mekanism.client.gui.element.GuiProgress;
import mekanism.common.recipe.machines.CellCultivateRecipe;
import mekanism.common.tile.prefab.TileEntityCultivateElectricMachine;
import net.minecraft.entity.player.InventoryPlayer;

public class GuiCellCultivate extends GuiCultivateElectricMachine<CellCultivateRecipe>{

    public GuiCellCultivate(InventoryPlayer inventory, TileEntityCultivateElectricMachine<CellCultivateRecipe> tile){
        super(inventory, tile);
    }

    @Override
    public GuiProgress.ProgressBar getProgressType() {
        return GuiProgress.ProgressBar.YELLOW;
    }

}
