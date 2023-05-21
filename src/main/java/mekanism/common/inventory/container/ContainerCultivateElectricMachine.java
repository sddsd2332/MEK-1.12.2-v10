package mekanism.common.inventory.container;

import mekanism.common.inventory.slot.SlotEnergy;
import mekanism.common.inventory.slot.SlotOutput;
import mekanism.common.recipe.inputs.CultivateMachineInput;
import mekanism.common.recipe.machines.CultivateMachineRecipe;
import mekanism.common.tile.prefab.TileEntityCultivateElectricMachine;
import mekanism.common.util.ChargeUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;

public class ContainerCultivateElectricMachine<RECIPE extends CultivateMachineRecipe<RECIPE>> extends ContainerMekanism<TileEntityCultivateElectricMachine<RECIPE>> {

    public ContainerCultivateElectricMachine(InventoryPlayer inventory, TileEntityCultivateElectricMachine<RECIPE> tile) {
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
            if (slotID == 3) {
                if (!mergeItemStack(slotStack, 5, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (ChargeUtils.canBeDischarged(slotStack)) {
                if (slotID != 4) {
                    if (!mergeItemStack(slotStack, 4, 5, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!mergeItemStack(slotStack, 5, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (isExtraItem(slotStack)) {
                if (slotID == 1) {
                    if (!mergeItemStack(slotStack, 1, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!mergeItemStack(slotStack, 5, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (tileEntity.getItemGas(slotStack) != null) {
                if (slotID != 2) {
                    if (!mergeItemStack(slotStack, 1, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!mergeItemStack(slotStack, 5, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (isInputItem(slotStack)) {
                if (slotID != 0) {
                    if (!mergeItemStack(slotStack, 0, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!mergeItemStack(slotStack, 5, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotID >= 5 && slotID <= 30) {
                if (!mergeItemStack(slotStack, 32, inventorySlots.size(), false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotID > 30) {
                if (!mergeItemStack(slotStack, 5, 31, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!mergeItemStack(slotStack, 5, inventorySlots.size(), true)) {
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

    private boolean isInputItem(ItemStack itemstack) {
        for (CultivateMachineInput input : tileEntity.getRecipes().keySet()) {
            if (ItemHandlerHelper.canItemStacksStack(input.itemStack, itemstack)) {
                return true;
            }
        }
        return false;
    }

    private boolean isExtraItem(ItemStack itemstack) {
        for (CultivateMachineInput input : tileEntity.getRecipes().keySet()) {
            if (ItemHandlerHelper.canItemStacksStack(input.extraStack, itemstack)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void addSlots() {
        addSlotToContainer(new Slot(tileEntity, 0, 56, 17));
        addSlotToContainer(new Slot(tileEntity, 1, 56 - 9, 54));
        addSlotToContainer(new Slot(tileEntity, 2, 56 + 9, 54));
        addSlotToContainer(new SlotOutput(tileEntity, 3, 116, 35));
        addSlotToContainer(new SlotEnergy.SlotDischarge(tileEntity, 4, 31, 35));
    }
}