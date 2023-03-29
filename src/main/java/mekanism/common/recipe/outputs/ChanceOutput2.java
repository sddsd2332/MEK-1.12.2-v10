package mekanism.common.recipe.outputs;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;

public class ChanceOutput2 extends MachineOutput<ChanceOutput2> {

    private static Random rand = new Random();

    public ItemStack primaryOutput = ItemStack.EMPTY;

    public double primaryChance;

    public ChanceOutput2(ItemStack primary, double chance) {
        primaryOutput = primary;
        primaryChance = chance;
    }

    public ChanceOutput2() {
    }


    @Override
    public void load(NBTTagCompound nbtTags) {
        primaryOutput = new ItemStack(nbtTags.getCompoundTag("primaryOutput"));
        primaryChance = nbtTags.getDouble("primaryChance");
    }

    public boolean checkSecondary() {
        return rand.nextDouble() <= primaryChance;
    }

    public boolean hasPrimary() {
        return !primaryOutput.isEmpty();
    }

    public boolean applyOutputs(NonNullList<ItemStack> inventory, int primaryIndex, boolean doEmit) {
        if (hasPrimary() && (!doEmit || checkSecondary())) {
            if (applyOutputs(inventory, primaryIndex, doEmit, primaryOutput)) {
                return false;
            }
        }
        return true;
    }

    private boolean applyOutputs(NonNullList<ItemStack> inventory, int index, boolean doEmit, ItemStack output) {
        ItemStack stack = inventory.get(index);
        if (stack.isEmpty()) {
            if (doEmit) {
                inventory.set(index, output.copy());
            }
            return false;
        } else if (ItemHandlerHelper.canItemStacksStack(stack, output) && stack.getCount() + output.getCount() <= stack.getMaxStackSize()) {
            if (doEmit) {
                stack.grow(output.getCount());
            }
            return false;
        }
        return true;
    }

    @Override
    public ChanceOutput2 copy() {
        return new ChanceOutput2(primaryOutput.copy(), primaryChance);
    }
}