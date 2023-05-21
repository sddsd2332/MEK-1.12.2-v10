package mekanism.common.recipe.outputs;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemHandlerHelper;

public class NucleosynthesizerOutput extends MachineOutput<NucleosynthesizerOutput> {

    private ItemStack itemOutput = ItemStack.EMPTY;

    public NucleosynthesizerOutput(ItemStack item) {
        itemOutput = item;
    }

    public NucleosynthesizerOutput() {
    }

    @Override
    public void load(NBTTagCompound nbtTags) {
        itemOutput = new ItemStack(nbtTags.getCompoundTag("itemOutput"));
    }


    public boolean canAddProducts(NonNullList<ItemStack> inventory, int index) {
        ItemStack stack = inventory.get(index);
        return stack.isEmpty() || (ItemHandlerHelper.canItemStacksStack(stack, itemOutput) && stack.getCount() + itemOutput.getCount() <= stack.getMaxStackSize());
    }


    public void addProducts(NonNullList<ItemStack> inventory, int index) {
        ItemStack stack = inventory.get(index);
        if (stack.isEmpty()) {
            inventory.set(index, itemOutput.copy());
        } else if (ItemHandlerHelper.canItemStacksStack(stack, itemOutput)) {
            stack.grow(itemOutput.getCount());
        }
    }

    public boolean applyOutputs(NonNullList<ItemStack> inventory, int index, boolean doEmit) {
        if (canAddProducts(inventory, index)) {
            if (doEmit) {
                addProducts(inventory, index);
            }
            return true;
        }
        return false;
    }

    public ItemStack getItemOutput() {
        return itemOutput;
    }


    @Override
    public NucleosynthesizerOutput copy() {
        return new NucleosynthesizerOutput(itemOutput.copy());
    }
}