package mekanism.common.inventory.container;

import mekanism.common.inventory.slot.SlotEnergy.SlotDischarge;
import mekanism.common.inventory.slot.SlotOutput;
import mekanism.common.recipe.RecipeHandler;
import mekanism.common.tile.TileEntityVoid;
import mekanism.common.util.ChargeUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ContainerVoid extends ContainerMekanism<TileEntityVoid> {

    public ContainerVoid(InventoryPlayer inventory, TileEntityVoid tile) {
        super(tile, inventory);
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
        ItemStack stack = ItemStack.EMPTY;
        Slot currentSlot = inventorySlots.get(slotID);
        if (currentSlot != null && currentSlot.getHasStack()) {
            ItemStack slotStack = currentSlot.getStack();
            stack = slotStack.copy();
            if (slotID == 2) {
                if (!mergeItemStack(slotStack, 3, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (ChargeUtils.canBeDischarged(slotStack)) {
                if (slotID != 1) {
                    if (!mergeItemStack(slotStack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!mergeItemStack(slotStack, 3, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (RecipeHandler.isInVoidRecipe(slotStack)) {
                if (slotID != 0) {
                    if (!mergeItemStack(slotStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!mergeItemStack(slotStack, 3, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotID >= 3 && slotID <= 29) {
                if (!mergeItemStack(slotStack, 30, inventorySlots.size(), false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotID > 29) {
                if (!mergeItemStack(slotStack, 3, 29, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!mergeItemStack(slotStack, 3, inventorySlots.size(), true)) {
                return ItemStack.EMPTY;
            }
            if (slotStack.getCount() == 0) {
                currentSlot.putStack(ItemStack.EMPTY);
            } else {
                currentSlot.onSlotChanged();
            }
            if (slotStack.getCount() == stack.getCount()) {
                return ItemStack.EMPTY;
            }
            currentSlot.onTake(player, slotStack);
        }
        return stack;
    }

    @Override
    protected void addSlots() {
        addSlotToContainer(new Slot(tileEntity, 0, 50, 66));
        addSlotToContainer(new SlotDischarge(tileEntity, 1, 196, 94));
        addSlotToContainer(new SlotOutput(tileEntity, 2, 154, 66));
    }

    @Override
    protected int getInventoryOffset() {
        return 102;
    }

    @Override
    protected void addInventorySlots(InventoryPlayer inventory) {
        int offset = getInventoryOffset();
        for (int slotY = 0; slotY < 3; slotY++) {
            for (int slotX = 0; slotX < 9; slotX++) {
                addSlotToContainer(new Slot(inventory, slotX + slotY * 9 + 9, 30 + slotX * 18, offset + slotY * 18));
            }
        }
        offset += 58;
        for (int slotY = 0; slotY < 9; slotY++) {
            addSlotToContainer(new Slot(inventory, slotY, 30 + slotY * 18, offset));
        }
    }
}