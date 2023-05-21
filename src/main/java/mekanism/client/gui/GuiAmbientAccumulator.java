package mekanism.client.gui;

import mekanism.client.gui.element.gauge.GuiGasGauge;
import mekanism.client.gui.element.gauge.GuiGauge.Type;
import mekanism.common.inventory.container.ContainerAmbientAccumulator;
import mekanism.common.tile.TileEntityAmbientAccumulator;
import mekanism.common.util.LangUtils;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAmbientAccumulator extends GuiMekanismTile<TileEntityAmbientAccumulator> {

    public GuiAmbientAccumulator(InventoryPlayer inventory, TileEntityAmbientAccumulator tile) {
        super(tile, new ContainerAmbientAccumulator(inventory, tile));
        addGuiElement(new GuiGasGauge(() -> tileEntity.collectedGas, Type.WIDE_ORANGE, this, getGuiLocation(), 103, 18));
        ySize += 5;
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString(tileEntity.getName(), (xSize / 2) - (fontRenderer.getStringWidth(tileEntity.getName()) / 2), 4, 0x404040);
        fontRenderer.drawString(LangUtils.localize("container.inventory"), 8, (ySize - 94) + 2, 0x404040);
        fontRenderer.drawString(LangUtils.localize("gui.dimensionId") + ":" + tileEntity.getWorld().provider.getDimension(), 8, 14, 0x33ff99);
        fontRenderer.drawString(LangUtils.localize("gui.dimensionName") + ":", 8, 23, 0x33ff99);
        fontRenderer.drawString(tileEntity.getWorld().provider.getDimensionType().getName(), 8, 32, 0x33ff99);
        fontRenderer.drawString(LangUtils.localize("gui.dimensionGas") + ":", 8, 41, 0x33ff99);
        fontRenderer.drawString((tileEntity.collectedGas.getGas() != null ? tileEntity.collectedGas.getGas().getGas().getLocalizedName() : LangUtils.localize("gui.none")), 8, 50, 0x33ff99);
        float Chance = 1 / (float) tileEntity.chance;
        fontRenderer.drawString(LangUtils.localize("gui.probability") + ":" + Chance + "%", 8, 59, 0x33ff99);
        String stored = "" + tileEntity.collectedGas.getStored() + " / " + tileEntity.collectedGas.getMaxGas();
        fontRenderer.drawString(stored, 8, 68, 0x33ff99);
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected ResourceLocation getGuiLocation() {
        return MekanismUtils.getResource(ResourceType.GUI, "GuiAmbientAccumulator.png");
    }
}
