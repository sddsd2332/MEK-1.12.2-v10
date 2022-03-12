/*
package mekanism.common.tile;


import io.netty.buffer.ByteBuf;
import mekanism.api.TileNetworkList;
import mekanism.common.Mekanism;
import mekanism.common.base.IRedstoneControl;
import mekanism.common.block.states.BlockStateMachine.MachineType;
import mekanism.common.entity.EntityRobit;
import mekanism.common.tile.prefab.TileEntityContainerBlock;
import mekanism.common.tile.prefab.TileEntityEffectsBlock;
import mekanism.common.util.ChargeUtils;
import mekanism.common.util.InventoryUtils;
import mekanism.common.util.MekanismUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class TileEntityIndustrialAlarm extends TileEntityEffectsBlock implements IRedstoneControl  {

    public TileEntityIndustrialAlarm() {
        super("machine.chargepad", "IndustrialAlarm", MachineType.CHARGEPAD.getStorage());
        inventory = NonNullList.withSize(0, ItemStack.EMPTY);
    }

    @Nonnull
    @Override
    public int[] getSlotsForFace(@Nonnull EnumFacing side) {
        return InventoryUtils.EMPTY;
    }

    @Override
    public boolean renderUpdate() {
        return false;
    }

    @Override
    public boolean lightUpdate() {
        return true;
    }

    public RedstoneControl controlType = RedstoneControl.DISABLED;

    @Override
    public RedstoneControl getControlType() {
        return controlType;
    }
    @Override
    public boolean canPulse() {
        return false;
    }

    @Override
    public void setControlType(RedstoneControl type) {
        controlType = type;
        MekanismUtils.saveChunk(this);

    }

}
*/