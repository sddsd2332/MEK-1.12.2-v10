package mekanism.client.gui;

import mekanism.api.gas.GasStack;
import mekanism.client.gui.element.*;
import mekanism.client.gui.element.tab.GuiSecurityTab;
import mekanism.client.gui.element.tab.GuiSideConfigurationTab;
import mekanism.client.gui.element.tab.GuiTransporterConfigTab;
import mekanism.client.gui.element.tab.GuiUpgradeTab;
import mekanism.client.render.MekanismRenderer;
import mekanism.common.inventory.container.ContainerCultivateElectricMachine;
import mekanism.common.recipe.machines.CultivateMachineRecipe;
import mekanism.common.tile.prefab.TileEntityCultivateElectricMachine;
import mekanism.common.util.LangUtils;
import mekanism.common.util.MekanismUtils;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;

public class GuiCultivateElectricMachine<RECIPE extends CultivateMachineRecipe<RECIPE>> extends GuiMekanismTile<TileEntityCultivateElectricMachine<RECIPE>> {

    public GuiCultivateElectricMachine(InventoryPlayer inventory, TileEntityCultivateElectricMachine<RECIPE> tile) {
        super(tile, new ContainerCultivateElectricMachine<>(inventory, tile));
        ResourceLocation resource = getGuiLocation();
        addGuiElement(new GuiRedstoneControl(this, tileEntity, resource));
        addGuiElement(new GuiUpgradeTab(this, tileEntity, resource));
        addGuiElement(new GuiSecurityTab(this, tileEntity, resource));
        addGuiElement(new GuiSideConfigurationTab(this, tileEntity, resource));
        addGuiElement(new GuiTransporterConfigTab(this, 34, tileEntity, resource));
        addGuiElement(new GuiPowerBar(this, tileEntity, resource, 164, 15));
        addGuiElement(new GuiEnergyInfo(() -> {
            String multiplier = MekanismUtils.getEnergyDisplay(tileEntity.energyPerTick);
            return Arrays.asList(LangUtils.localize("gui.using") + ": " + multiplier + "/t",
                    LangUtils.localize("gui.needed") + ": " + MekanismUtils.getEnergyDisplay(tileEntity.getMaxEnergy() - tileEntity.getEnergy()));
        }, this, resource));
        addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT, this, resource, 55, 16));
        addGuiElement(new GuiSlot(GuiSlot.SlotType.POWER, this, resource, 30, 34).with(GuiSlot.SlotOverlay.POWER));
        addGuiElement(new GuiSlot(GuiSlot.SlotType.EXTRA_LOG, this, resource, 55 - 9, 53));
        addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LARGE, this, resource, 111, 30));
        addGuiElement(new GuiProgress(new GuiProgress.IProgressInfoHandler() {
            @Override
            public double getProgress() {
                return tileEntity.getScaledProgress();
            }
        }, getProgressType(), this, resource, 77, 37));
    }

    public GuiProgress.ProgressBar getProgressType() {
        return GuiProgress.ProgressBar.BLUE;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString(tileEntity.getName(), (xSize / 2) - (fontRenderer.getStringWidth(tileEntity.getName()) / 2), 6, 0x404040);
        fontRenderer.drawString(LangUtils.localize("container.inventory"), 8, (ySize - 96) + 2, 0x404040);
        int xAxis = mouseX - guiLeft;
        int yAxis = mouseY - guiTop;
        if (xAxis >= 61 && xAxis <= 67 && yAxis >= 37 && yAxis <= 49) {
            displayTooltip(tileEntity.gasTank.getGas() != null ? tileEntity.gasTank.getGas().getGas().getLocalizedName() + ": " + tileEntity.gasTank.getStored()
                    : LangUtils.localize("gui.none"), xAxis, yAxis);
        }
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(int xAxis, int yAxis) {
        super.drawGuiContainerBackgroundLayer(xAxis, yAxis);
        if (tileEntity.getScaledGasLevel(12) > 0) {
            int displayInt = tileEntity.getScaledGasLevel(12);
            displayGauge(61, 37 + 12 - displayInt, 6, displayInt, tileEntity.gasTank.getGas());
        }
    }

    @Override
    protected ResourceLocation getGuiLocation() {
        return tileEntity.guiLocation;
    }

    public void displayGauge(int xPos, int yPos, int sizeX, int sizeY, GasStack gas) {
        if (gas != null) {
            mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            MekanismRenderer.color(gas);
            drawTexturedModalRect(guiLeft + xPos, guiTop + yPos, gas.getGas().getSprite(), sizeX, sizeY);
            MekanismRenderer.resetColor();
        }
    }
}