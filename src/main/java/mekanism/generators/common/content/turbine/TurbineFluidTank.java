package mekanism.generators.common.content.turbine;

import mekanism.common.base.MultiblockFluidTank;
import mekanism.generators.common.tile.turbine.TileEntityTurbineCasing;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

public class TurbineFluidTank extends MultiblockFluidTank<TileEntityTurbineCasing> {

    public TurbineFluidTank(TileEntityTurbineCasing tileEntity) {
        super(tileEntity);
    }

    @Override
    @Nullable
    public FluidStack getFluid() {
        return multiblock.structure != null ? multiblock.structure.fluidStored : null;
    }

    @Override
    public void setFluid(FluidStack stack) {
        if (multiblock.structure != null) {
            multiblock.structure.fluidStored = stack;
        }
    }

    @Override
    public int getCapacity() {
        return multiblock.structure != null ? multiblock.structure.getFluidCapacity() : 0;
    }

    @Override
    protected void updateValveData() {
    }
}