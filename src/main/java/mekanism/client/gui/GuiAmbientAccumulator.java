package mekanism.client.gui;

import mekanism.client.gui.element.gauge.GuiGasGauge;
import mekanism.client.gui.element.gauge.GuiGauge.Type;
import mekanism.common.inventory.container.ContainerNull;
import mekanism.common.tile.TileEntityAmbientAccumulator;
import mekanism.common.util.LangUtils;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAmbientAccumulator extends GuiMekanismTile<TileEntityAmbientAccumulator> {

    public GuiAmbientAccumulator(EntityPlayer player, TileEntityAmbientAccumulator tile) {
        super(tile, new ContainerNull(player, tile));
        addGuiElement(new GuiGasGauge(() -> tileEntity.collectedGas, Type.WIDE_ORANGE, this, getGuiLocation(), 103, 18));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString(tileEntity.getName(), (xSize / 2) - (fontRenderer.getStringWidth(tileEntity.getName()) / 2), 6, 0x404040);
        fontRenderer.drawString(LangUtils.localize("container.inventory"), 8, (ySize - 96) + 2, 0x404040);
        fontRenderer.drawString(LangUtils.localize("gui.dimension") + ":" + tileEntity.getWorld().provider.getDimension(), 8, 20, 0x33ff99);
        fontRenderer.drawString(LangUtils.localize("gui.dimensionGas") + ":", 8, 29, 0x33ff99);
        fontRenderer.drawString((tileEntity.collectedGas.getGas() != null ? tileEntity.collectedGas.getGas().getGas().getLocalizedName() : LangUtils.localize("gui.none")), 8, 38, 0x33ff99);
        float Chance = 1 / (float) tileEntity.chance;
        fontRenderer.drawString(LangUtils.localize("gui.probability") + ":" + Chance + "%", 8, 47, 0x33ff99);
        String stored = "" + tileEntity.collectedGas.getStored() + " / " + tileEntity.collectedGas.getMaxGas();
        fontRenderer.drawString(stored, 8, 56, 0x33ff99);
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected ResourceLocation getGuiLocation() {
        return MekanismUtils.getResource(ResourceType.GUI, "GuiAmbientAccumulator.png");
    }
}
