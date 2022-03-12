package mekanism.common.recipe.machines;

import mekanism.api.gas.Gas;
import mekanism.api.gas.GasTank;
import mekanism.common.recipe.inputs.CultivateMachineInput;
import mekanism.common.recipe.outputs.ItemStackOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public abstract class CultivateMachineRecipe<RECIPE extends CultivateMachineRecipe<RECIPE>> extends MachineRecipe<CultivateMachineInput, ItemStackOutput,RECIPE> {

    public CultivateMachineRecipe(CultivateMachineInput input, ItemStackOutput output) {
        super(input, output);
    }

    public CultivateMachineRecipe(ItemStack input, ItemStack extra, Gas gas, ItemStack output) {
        this(new CultivateMachineInput(input, extra, gas), new ItemStackOutput(output));
    }


    public boolean inputMatches(NonNullList<ItemStack> inventory, int inputIndex, int extraIndex, GasTank gasTank, int amount) {
        return getInput().useItem(inventory, inputIndex, false) && getInput().useExtra(inventory, extraIndex, false) && getInput().useSecondary(gasTank, amount, false);
    }

    public boolean canOperate(NonNullList<ItemStack> inventory, int inputIndex, int extraIndex, GasTank gasTank, int amount,int outputIndex) {
        return inputMatches(inventory, inputIndex,extraIndex,gasTank, amount)&& getOutput().applyOutputs(inventory, outputIndex, false);
    }

    public void operate(NonNullList<ItemStack> inventory, int inputIndex, int extraIndex, int outputIndex,GasTank gasTank, int needed){
        if (getInput().useItem(inventory, inputIndex, true) && getInput().useExtra(inventory, extraIndex, true) && getInput().useSecondary(gasTank, needed, true)){
            getOutput().applyOutputs(inventory, outputIndex, true);
        }
    }

}

