package mekanism.client.gui;

import mekanism.client.gui.element.GuiEnergyInfo;
import mekanism.client.gui.element.GuiProgress;
import mekanism.client.gui.element.GuiProgress.IProgressInfoHandler;
import mekanism.client.gui.element.GuiProgress.ProgressBar;
import mekanism.client.gui.element.GuiRedstoneControl4;
import mekanism.client.gui.element.GuiSlot;
import mekanism.client.gui.element.GuiSlot.SlotOverlay;
import mekanism.client.gui.element.GuiSlot.SlotType;
import mekanism.client.gui.element.gauge.GuiFluidGauge;
import mekanism.client.gui.element.gauge.GuiGasGauge;
import mekanism.client.gui.element.gauge.GuiGauge;
import mekanism.client.gui.element.tab.GuiSecurityTab4;
import mekanism.client.gui.element.tab.GuiSideConfigurationTab;
import mekanism.client.gui.element.tab.GuiTransporterConfigTab;
import mekanism.client.gui.element.tab.GuiUpgradeTab4;
import mekanism.common.config.MekanismConfig;
import mekanism.common.inventory.container.ContainerVoid;
import mekanism.common.tile.TileEntityVoid;
import mekanism.common.util.LangUtils;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

@SideOnly(Side.CLIENT)
public class GuiVoid extends GuiMekanismTile<TileEntityVoid> {


    public GuiVoid(InventoryPlayer inventory, TileEntityVoid tile) {
        super(tile, new ContainerVoid(inventory, tile));
        ResourceLocation resource = getGuiLocation();

        addGuiElement(new GuiRedstoneControl4(this, tileEntity, resource));
        addGuiElement(new GuiSecurityTab4(this, tileEntity, resource));
        addGuiElement(new GuiUpgradeTab4(this, tileEntity, resource));

        addGuiElement(new GuiSideConfigurationTab(this, tileEntity, resource));
        addGuiElement(new GuiTransporterConfigTab(this, 34, tileEntity, resource));
        addGuiElement(new GuiEnergyInfo(() -> {
            double extra = tileEntity.getRecipe() != null ? tileEntity.getRecipe().extraEnergy : 0;
            String multiplier = MekanismUtils.getEnergyDisplay(MekanismUtils.getEnergyPerTick(tileEntity, tileEntity.BASE_ENERGY_PER_TICK + extra));
            return Arrays.asList(LangUtils.localize("gui.using") + ": " + multiplier + "/t",
                    LangUtils.localize("gui.needed") + ": " + MekanismUtils.getEnergyDisplay(tileEntity.getMaxEnergy() - tileEntity.getEnergy()));
        }, this, resource));
        addGuiElement(new GuiFluidGauge(() -> tileEntity.inputFluidTank, GuiGauge.Type.STANDARD_RED, this, resource, 7, 13));
        addGuiElement(new GuiGasGauge(() -> tileEntity.inputGasTank, GuiGauge.Type.STANDARD_YELLOW, this, resource, 28, 13));
        addGuiElement(new GuiGasGauge(() -> tileEntity.outputGasTank, GuiGauge.Type.STANDARD_BLUE, this, resource, 174, 13));
        addGuiElement(new GuiFluidGauge(() -> tileEntity.outputFluidTank, GuiGauge.Type.STANDARD_ORANGE, this, resource, 195, 13));
        addGuiElement(new GuiSlot(SlotType.INPUT, this, resource, 49, 65));
        addGuiElement(new GuiSlot(SlotType.POWER, this, resource, 195, 93).with(SlotOverlay.POWER));
        addGuiElement(new GuiSlot(SlotType.OUTPUT, this, resource, 153, 65));
        addGuiElement(new GuiProgress(new IProgressInfoHandler() {
            @Override
            public double getProgress() {
                return tileEntity.getScaledProgress();
            }
        }, getProgressType(), this, resource, 84, 69));
        ySize += 18;
        xSize += 43;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(int xAxis, int yAxis) {
        super.drawGuiContainerBackgroundLayer(xAxis, yAxis);
        drawTexturedModalRect(guiLeft + 9, guiTop + 86, 0, 185, tileEntity.getScaledEnergyLevel(203), 4);
        drawTexturedModalRect(guiLeft + 11, guiTop + 96, 207, tileEntity.getActive() ? 185 : 197, 12, 12);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString(tileEntity.getName(), (xSize / 2) - (fontRenderer.getStringWidth(tileEntity.getName()) / 2), 4, 0x404040);
        fontRenderer.drawString(LangUtils.localize("container.inventory"), 30, (ySize - 94) + 2, 0x404040);
        int xAxis = mouseX - guiLeft;
        int yAxis = mouseY - guiTop;
        if (xAxis >= 9 && xAxis <= 212 && yAxis >= 86 && yAxis <= 90) {
            displayTooltip(MekanismUtils.getEnergyDisplay(tileEntity.getEnergy(), tileEntity.getMaxEnergy()), xAxis, yAxis);
        }
        renderText(LangUtils.localize("gui.dimensionId") + ":" + tileEntity.getWorld().provider.getDimension(), 51, 14, MekanismConfig.current().client.VoidExcavatordimensiondescriptiondescription.val(),true);
        renderText(LangUtils.localize("gui.dimensionName") + ":" + tileEntity.getWorld().provider.getDimensionType().getName(), 51, 23, MekanismConfig.current().client.VoidExcavatordimensiondescriptiondescription.val(),true);
        if (tileEntity.getRecipe() != null) {
            renderText(LangUtils.localize("gui.itemInputoutprobability") + ":" + Math.round(tileEntity.getRecipe().getInput().itemStackChance * 100) + "%" + "/" + Math.round(tileEntity.getRecipe().getOutput().ItemStackOutputChance * 100) + "%", 51, 32, MekanismConfig.current().client.VoidExcavatordimensiondescriptiondescription.val(),true);
            renderText(LangUtils.localize("gui.fluidInputoutprobability") + ":" + Math.round(tileEntity.getRecipe().getInput().theGasChance * 100) + "%" + "/" + Math.round(tileEntity.getRecipe().getOutput().fluidOutputChance * 100) + "%", 51, 41, MekanismConfig.current().client.VoidExcavatordimensiondescriptiondescription.val(),true);
            renderText(LangUtils.localize("gui.gasInputoutprobability") + ":" + Math.round(tileEntity.getRecipe().getInput().theFluidChance * 100) + "%" + "/" + Math.round(tileEntity.getRecipe().getOutput().gasOutputChance * 100) + "%", 51, 50, MekanismConfig.current().client.VoidExcavatordimensiondescriptiondescription.val(),true);
        }
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    private void renderText(String text, int x, int y, float size, boolean scale) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(size, size, size);
        fontRenderer.drawString(text, scale ? (int) ((1F / size) * x) : x, scale ? (int) ((1F / size) * y) : y, 0x33ff99);
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getGuiLocation() {
        return MekanismUtils.getResource(ResourceType.GUI, "GuiVoidExcavator.png");
    }

    public ProgressBar getProgressType() {
        return ProgressBar.LARGE_RIGHT;
    }
}