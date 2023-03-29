package mekanism.client.gui;

import mekanism.client.gui.element.GuiProgress.ProgressBar;
import mekanism.common.recipe.machines.RecyclerRecipe;
import mekanism.common.tile.TileEntityChanceMachine2;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiRecycler extends GuiChanceMachine2<RecyclerRecipe> {

    public GuiRecycler(InventoryPlayer inventory, TileEntityChanceMachine2<RecyclerRecipe> tile) {
        super(inventory, tile);
    }

    @Override
    public ProgressBar getProgressType() {
        return ProgressBar.CRUSH;
    }
}