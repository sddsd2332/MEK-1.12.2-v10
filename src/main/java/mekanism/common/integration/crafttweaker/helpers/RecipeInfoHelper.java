package mekanism.common.integration.crafttweaker.helpers;

import crafttweaker.mc1120.item.MCItemStack;
import mekanism.api.gas.Gas;
import mekanism.api.gas.GasStack;
import mekanism.common.recipe.inputs.MachineInput;
import mekanism.common.recipe.machines.MachineRecipe;
import mekanism.common.recipe.outputs.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.Map.Entry;

public class RecipeInfoHelper {

    private RecipeInfoHelper() {
    }

    public static String getRecipeInfo(Entry<? extends MachineInput, ? extends MachineRecipe> recipe) {
        MachineOutput output = recipe.getValue().recipeOutput;
        if (output instanceof ItemStackOutput) {
            return getItemName(((ItemStackOutput) output).output);
        } else if (output instanceof GasOutput) {
            return getGasName(((GasOutput) output).output);
        } else if (output instanceof FluidOutput) {
            return getFluidName(((FluidOutput) output).output);
        } else if (output instanceof ChemicalPairOutput) {
            ChemicalPairOutput out = (ChemicalPairOutput) output;
            return "[" + getGasName(out.leftGas) + ", " + getGasName(out.rightGas) + "]";
        } else if (output instanceof ChanceOutput) {
            return getItemName(((ChanceOutput) output).primaryOutput);
        } else if (output instanceof PressurizedOutput) {
            PressurizedOutput out = (PressurizedOutput) output;
            return "[" + getItemName(out.getItemOutput()) + ", " + getGasName(out.getGasOutput()) + "]";
        }
        return null;
    }

    public static String getGasName(GasStack stack) {
        return stack.amount > 1 ? String.format("<gas:%s> * %s", stack.getGas().getName(), stack.amount) : getGasName(stack.getGas());
    }

    public static String getGasName(Gas gas) {
        return String.format("<gas:%s>", gas.getName());
    }

    public static String getFluidName(FluidStack stack) {
        return stack.amount > 1 ? String.format("<liquid:%s> * %s", stack.getFluid().getName(), stack.amount) : getFluidName(stack.getFluid());
    }

    public static String getFluidName(Fluid fluid) {
        return String.format("<liquid:%s>", fluid.getName());
    }

    public static String getItemName(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return "nothing";
        }
        return new MCItemStack(stack).toString();
    }
}