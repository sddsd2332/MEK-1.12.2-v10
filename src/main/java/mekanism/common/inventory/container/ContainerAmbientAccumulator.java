package mekanism.common.inventory.container;

import mekanism.common.tile.TileEntityAmbientAccumulator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ContainerAmbientAccumulator extends ContainerFluidStorage<TileEntityAmbientAccumulator> {

    public ContainerAmbientAccumulator(InventoryPlayer inventory, TileEntityAmbientAccumulator tile) {
        super(tile, inventory);
    }


    @Override
    protected void addSlots() {

    }
}