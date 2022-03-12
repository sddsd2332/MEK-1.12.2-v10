package mekanism.common.recipe.inputs;

import mekanism.api.gas.Gas;
import mekanism.api.gas.GasTank;
import mekanism.common.util.StackUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class CultivateMachineInput extends MachineInput<CultivateMachineInput>implements IWildInput<CultivateMachineInput> {
    public ItemStack itemStack = ItemStack.EMPTY;
    public ItemStack extraStack = ItemStack.EMPTY;
    public Gas gasType;

    public CultivateMachineInput(ItemStack item , ItemStack extra, Gas gas){
        itemStack = item;
        extraStack = extra;
        gasType = gas;
    }

    public CultivateMachineInput(){}

    @Override
    public void load(NBTTagCompound nbtTags) {
        itemStack = new ItemStack(nbtTags.getCompoundTag("input"));
        extraStack = new ItemStack(nbtTags.getCompoundTag("extra"));
        gasType = Gas.readFromNBT(nbtTags.getCompoundTag("gasType"));
    }

    @Override
    public CultivateMachineInput copy(){
        return new CultivateMachineInput(itemStack.copy(),extraStack.copy(),gasType);
    }

    @Override
    public boolean isValid() {
        return !itemStack.isEmpty() && !extraStack.isEmpty() && gasType != null;
    }

    protected boolean useItemInternal(ItemStack stack, NonNullList<ItemStack> inventory, int index, boolean deplete) {
        if (inputContains(inventory.get(index), stack)) {
            if (deplete) {
                inventory.set(index, StackUtils.subtract(inventory.get(index), stack));
            }
            return true;
        }
        return false;
    }

    public boolean useItem(NonNullList<ItemStack> inventory, int index, boolean deplete) {
        return useItemInternal(itemStack, inventory, index, deplete);
    }

    public boolean useExtra(NonNullList<ItemStack> inventory, int index, boolean deplete) {
        return useItemInternal(extraStack, inventory, index, deplete);
    }

    public boolean useSecondary(GasTank gasTank, int amountToUse, boolean deplete) {
        if (gasTank.getGasType() == gasType && gasTank.getStored() >= amountToUse) {
            gasTank.draw(amountToUse, deplete);
            return true;
        }
        return false;
    }

    public boolean matches(CultivateMachineInput input){
        return  StackUtils.equalsWildcard(itemStack,input.itemStack)&& input.itemStack.getCount()>= itemStack.getCount()
                && StackUtils.equalsWildcard(extraStack,input.extraStack)&& input.extraStack.getCount()>= extraStack.getCount();
    }

    @Override
    public int hashIngredients() {
        return  StackUtils.hashItemStack(itemStack) ^ Integer.reverse(StackUtils.hashItemStack(extraStack)) | gasType.getID();
    //    return StackUtils.hashItemStack(itemStack) << 8 | gasType.getID();
    }

    @Override
    public boolean testEquality(CultivateMachineInput other){
        if (!isValid()) {
            return !other.isValid();
        }
        return MachineInput.inputItemMatches(itemStack, other.itemStack) && MachineInput.inputItemMatches(extraStack, other.extraStack) && gasType.getID() == other.gasType.getID();
    }

    @Override
    public boolean isInstance(Object other) {
        return other instanceof CultivateMachineInput;
    }

    @Override
    public CultivateMachineInput wildCopy() {
        return new CultivateMachineInput(new ItemStack(itemStack.getItem(), itemStack.getCount(), OreDictionary.WILDCARD_VALUE),
                new ItemStack(extraStack.getItem(), extraStack.getCount(), OreDictionary.WILDCARD_VALUE), gasType);
    }

}

