package mekanism.client.gui.filter;

import mekanism.api.Coord4D;
import mekanism.common.Mekanism;
import mekanism.common.network.PacketLogisticalSorterGui.LogisticalSorterGuiMessage;
import mekanism.common.network.PacketLogisticalSorterGui.SorterGuiPacket;
import mekanism.common.tile.TileEntityLogisticalSorter;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class GuiTFilterSelect extends GuiFilterSelect<TileEntityLogisticalSorter> {

    public GuiTFilterSelect(EntityPlayer player, TileEntityLogisticalSorter tile) {
        super(player, tile);
    }

    @Override
    protected void sendPacketToServer(int guiID) {
        Mekanism.packetHandler.sendToServer(new LogisticalSorterGuiMessage(SorterGuiPacket.SERVER, Coord4D.get(tileEntity), guiID, 0, 0));
    }

    @Override
    protected void actionPerformed(GuiButton guibutton) throws IOException {
        super.actionPerformed(guibutton);
        if (guibutton.id == modIDButton.id) {
            sendPacketToServer(5);
        }
    }

    @Override
    protected ResourceLocation getGuiLocation() {
        return MekanismUtils.getResource(ResourceType.GUI, "GuiFilterSelect.png");
    }
}