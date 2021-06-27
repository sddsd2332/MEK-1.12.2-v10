package mekanism.common.recipe.machines;

import mekanism.api.gas.GasStack;
import mekanism.api.gas.GasTank;
import mekanism.common.recipe.inputs.GasInput;
import mekanism.common.recipe.outputs.GasOutput;

public class IsotopicRecipe extends MachineRecipe<GasInput, GasOutput, IsotopicRecipe> {

    public IsotopicRecipe(GasStack input, GasStack output) {
        super(new GasInput(input), new GasOutput(output));
    }

    public IsotopicRecipe(GasInput input, GasOutput output) {
        super(input, output);
    }

    @Override
    public IsotopicRecipe copy() {
        return new IsotopicRecipe(getInput(), getOutput());
    }

    public boolean canOperate(GasTank inputTank, GasTank outputTank) {
        return getInput().useGas(inputTank, false, 1) && getOutput().applyOutputs(outputTank, false, 1);
    }

    public void operate(GasTank inputTank, GasTank outputTank, int scale) {
        if (getInput().useGas(inputTank, true, scale)) {
            getOutput().applyOutputs(outputTank, true, scale);
        }
    }
}