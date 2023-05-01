package mekanism.client.gui;

import mekanism.client.gui.element.GuiEnergyInfo;
import mekanism.client.gui.element.GuiPowerBar;
import mekanism.client.gui.element.GuiRedstoneControl;
import mekanism.client.gui.element.GuiSlot;
import mekanism.client.gui.element.gauge.GuiGasGauge;
import mekanism.client.gui.element.gauge.GuiGauge.Type;
import mekanism.client.gui.element.tab.GuiSecurityTab;
import mekanism.client.gui.element.tab.GuiSideConfigurationTab;
import mekanism.client.gui.element.tab.GuiTransporterConfigTab;
import mekanism.client.gui.element.tab.GuiUpgradeTab;
import mekanism.common.inventory.container.ContainerAmbientAccumulatorEnergy;
import mekanism.common.tile.TileEntityAmbientAccumulatorEnergy;
import mekanism.common.util.LangUtils;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

@SideOnly(Side.CLIENT)
public class GuiAmbientAccumulatorEnergy extends GuiMekanismTile<TileEntityAmbientAccumulatorEnergy> {

    public GuiAmbientAccumulatorEnergy(InventoryPlayer inventory, TileEntityAmbientAccumulatorEnergy tile) {
        super(tile, new ContainerAmbientAccumulatorEnergy(inventory, tile));
        ResourceLocation resource = getGuiLocation();
        addGuiElement(new GuiSecurityTab(this, tileEntity, resource));
        addGuiElement(new GuiRedstoneControl(this, tileEntity, resource));
        addGuiElement(new GuiUpgradeTab(this, tileEntity, resource));
        addGuiElement(new GuiSideConfigurationTab(this, tileEntity, resource));
        addGuiElement(new GuiTransporterConfigTab(this, 34, tileEntity, resource));
        addGuiElement(new GuiEnergyInfo(() -> {
            String usage = MekanismUtils.getEnergyDisplay(tileEntity.clientEnergyUsed);
            return Arrays.asList(LangUtils.localize("gui.using") + ": " + usage + "/t",
                    LangUtils.localize("gui.needed") + ": " + MekanismUtils.getEnergyDisplay(tileEntity.getMaxEnergy() - tileEntity.getEnergy()));
        }, this, resource));

        addGuiElement(new GuiGasGauge(() -> tileEntity.outputTank, Type.STANDARD_ORANGE, this, getGuiLocation(), 103, 18));
        addGuiElement(new GuiPowerBar(this, tileEntity, resource, 164, 18));
        addGuiElement(new GuiSlot(GuiSlot.SlotType.POWER, this, resource, 144, 18).with(GuiSlot.SlotOverlay.POWER));
        addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT, this, resource, 144, 55).with(GuiSlot.SlotOverlay.PLUS));
    }


    @Override
    protected ResourceLocation getGuiLocation() {
        return MekanismUtils.getResource(ResourceType.GUI, "GuiAmbientAccumulator.png");
    }



    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString(tileEntity.getName(), (xSize / 2) - (fontRenderer.getStringWidth(tileEntity.getName()) / 2), 6, 0x404040);
        fontRenderer.drawString(LangUtils.localize("container.inventory"), 8, (ySize - 96) + 2, 0x404040);
        fontRenderer.drawString(LangUtils.localize("gui.dimension") + ":" + tileEntity.getWorld().provider.getDimension(), 8, 20, 0x33ff99);
        fontRenderer.drawString(LangUtils.localize("gui.dimensionGas") + ":", 8, 29, 0x33ff99);
        fontRenderer.drawString((tileEntity.outputTank.getGas() != null ? tileEntity.outputTank.getGas().getGas().getLocalizedName() : LangUtils.localize("gui.none")), 8, 38, 0x33ff99);
        String stored = "" + tileEntity.outputTank.getStored() + " / " + tileEntity.outputTank.getMaxGas();
        fontRenderer.drawString(stored, 8, 56, 0x33ff99);
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }


}
