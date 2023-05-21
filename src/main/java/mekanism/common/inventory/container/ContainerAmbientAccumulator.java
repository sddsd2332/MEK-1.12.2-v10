package mekanism.common.inventory.container;

import mekanism.common.tile.TileEntityAmbientAccumulator;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerAmbientAccumulator extends ContainerFluidStorage<TileEntityAmbientAccumulator> {

    public ContainerAmbientAccumulator(InventoryPlayer inventory, TileEntityAmbientAccumulator tile) {
        super(tile, inventory);
    }


    @Override
    protected void addSlots() {

    }

    @Override
    protected int getInventoryOffset() {
        return 89;
    }
}