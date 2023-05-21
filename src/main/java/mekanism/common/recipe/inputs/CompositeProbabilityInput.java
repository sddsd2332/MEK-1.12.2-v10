package mekanism.common.recipe.inputs;

import mekanism.api.gas.GasStack;
import mekanism.api.gas.GasTank;
import mekanism.common.util.StackUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Random;

public class CompositeProbabilityInput extends MachineInput<CompositeProbabilityInput> implements IWildInput<CompositeProbabilityInput> {

    public static Random rand = new Random();
    public int ingredient;
    public ItemStack itemStack = ItemStack.EMPTY;
    public FluidStack theFluid;

    public GasStack theGas;
    public double itemStackChance;
    public double theFluidChance;

    public double theGasChance;

    public CompositeProbabilityInput(int integer, ItemStack item, double itemchance, FluidStack fluid, double fluidchance, GasStack gas, double gaschance) {
        ingredient = integer;
        itemStack = item;
        itemStackChance = itemchance;
        theFluid = fluid;
        theFluidChance = fluidchance;
        theGas = gas;
        theGasChance = gaschance;
    }

    public CompositeProbabilityInput() {
    }

    @Override
    public void load(NBTTagCompound nbtTags) {
        ingredient = nbtTags.getInteger("input");
        itemStack = new ItemStack(nbtTags.getCompoundTag("itemStackinput"));
        itemStackChance = nbtTags.getDouble("itemStackChance");
        theFluid = FluidStack.loadFluidStackFromNBT(nbtTags.getCompoundTag("fluidInput"));
        theFluidChance = nbtTags.getDouble("fluidChance");
        theGas = GasStack.readFromNBT(nbtTags.getCompoundTag("gasInput"));
        theGasChance = nbtTags.getDouble("gasChance");
    }

    @Override
    public CompositeProbabilityInput copy() {
        return new CompositeProbabilityInput(ingredient, itemStack.copy(), itemStackChance, theFluid.copy(), theFluidChance, theGas.copy(), theGasChance);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    public boolean checkSecondary() {
        return rand.nextDouble() <= itemStackChance;
    }

    public boolean checkFluid() {
        return rand.nextDouble() <= theFluidChance;
    }

    public boolean checkGas() {
        return rand.nextDouble() <= theGasChance;
    }

    public boolean useItem(NonNullList<ItemStack> inventory, int index, FluidTank fluidTank, GasTank gasTank, boolean deplete) {
        if (inputContains(inventory.get(index), itemStack)) {
            if (deplete) {
                if (checkSecondary()) {
                    inventory.set(index, StackUtils.subtract(inventory.get(index), itemStack));
                }
                if (checkFluid()) {
                    fluidTank.drain(theFluid.amount, true);
                }
                if (checkGas()) {
                    gasTank.draw(theGas.amount, true);
                }
            }
            return true;
        }
        return false;
    }

    public ItemStack getSolid() {
        return itemStack;
    }

    public FluidStack getFluid() {
        return theFluid;
    }

    public GasStack getGas() {
        return theGas;
    }

    public boolean containsType(ItemStack stack) {
        if (stack.isEmpty() || stack.getCount() == 0) {
            return false;
        }
        return MachineInput.inputItemMatches(stack, itemStack);
    }

    public boolean containsType(FluidStack stack) {
        if (stack == null || stack.amount == 0) {
            return false;
        }
        return stack.isFluidEqual(theFluid);
    }


    public boolean containsType(GasStack stack) {
        if (stack == null || stack.amount == 0) {
            return false;
        }
        return stack.isGasEqual(theGas);
    }

    public boolean matches(CompositeProbabilityInput input) {
        return StackUtils.equalsWildcard(itemStack, input.itemStack) && input.itemStack.getCount() >= itemStack.getCount();
    }


    //todo:Here it is needed <<
    @Override
    public int hashIngredients() {
        return ingredient;
    }

    @Override
    public boolean testEquality(CompositeProbabilityInput other) {
        return ingredient == other.ingredient &&
                other.containsType(itemStack) &&
                other.containsType(theFluid) &&
                other.containsType(theGas);
    }

    @Override
    public boolean isInstance(Object other) {
        return other instanceof CompositeProbabilityInput;
    }

    @Override
    public CompositeProbabilityInput wildCopy() {
        return new CompositeProbabilityInput(ingredient,
                new ItemStack(itemStack.getItem(), itemStack.getCount(), OreDictionary.WILDCARD_VALUE), itemStackChance,
                theFluid, theFluidChance,
                theGas, theGasChance
        );
    }
}