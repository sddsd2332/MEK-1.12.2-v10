/*package mekanism.client.gui;

import mekanism.api.TileNetworkList;
import mekanism.api.gas.GasStack;
import mekanism.api.infuse.InfuseType;
import mekanism.client.gui.element.*;
import mekanism.client.gui.element.tab.*;
import mekanism.client.render.MekanismRenderer;
import mekanism.client.sound.SoundHandler;
import mekanism.common.Mekanism;
import mekanism.common.base.IFactory.MachineFuelType;
import mekanism.common.base.IFactory.RecipeType;
import mekanism.common.inventory.container.ContainerFactory;
import mekanism.common.item.ItemGaugeDropper;
import mekanism.common.network.PacketTileEntity.TileEntityMessage;
import mekanism.common.tier.FactoryTier;
import mekanism.common.tile.TileEntityFactory;
import mekanism.common.util.LangUtils;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.Arrays;

@SideOnly(Side.CLIENT)
public class GuiFactoryback extends GuiMekanismTile<TileEntityFactory> {

    private GuiButton infuserDumpButton = null;

    public GuiFactoryback(InventoryPlayer inventory, TileEntityFactory tile) {
        super(tile, new ContainerFactory(inventory, tile));
        ySize += 11+18 ;
        if (tile.tier == FactoryTier.ULTIMATE) {
            xSize += 34;
        }


        ResourceLocation resource = tileEntity.tier.guiLocation;
        if (tile.tier == FactoryTier.ULTIMATE) {
        addGuiElement(new GuiSecurityTab2(this, tileEntity, resource));
        addGuiElement(new GuiUpgradeTab2(this, tileEntity, resource));
        addGuiElement(new GuiRedstoneControl2(this, tileEntity, resource));
        addGuiElement(new GuiRecipeType2(this, tileEntity, resource));
        }else if (tile.tier == FactoryTier.BASIC || tile.tier == FactoryTier.ADVANCED||tile.tier == FactoryTier.ELITE) {
            addGuiElement(new GuiSecurityTab(this, tileEntity, resource));
            addGuiElement(new GuiUpgradeTab(this, tileEntity, resource));
            addGuiElement(new GuiRedstoneControl(this, tileEntity, resource));
            addGuiElement(new GuiRecipeType(this, tileEntity, resource));
        }

//隐藏气体，流体显示开始
        if(tile.tier == FactoryTier.BASIC) {//基础工厂
            if (GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.FARM) {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.BASIC_FACTORY, this, resource, 7, 77));
            } else if(GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.SAWING) {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.BASIC_FACTORY, this, resource, 7, 77));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 95));
            } else if (GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.COMPRESSING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.PURIFYING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.INJECTING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.INFUSING) {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 77));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 86));
            } else {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 77));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 86));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 95));
            }
        }else if(tile.tier == FactoryTier.ADVANCED){//高级工厂
            if (GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.FARM) {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.ADVANCED_FACTORY, this, resource, 7, 77));
            } else if(GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.SAWING) {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.ADVANCED_FACTORY, this, resource, 7, 77));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 95));
            } else if (GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.COMPRESSING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.PURIFYING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.INJECTING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.INFUSING) {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 77));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 86));
            } else {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 77));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 86));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 95));
            }
        }else if(tile.tier == FactoryTier.ELITE) {//精英工厂
            if (GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.FARM) {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.ELITE_FACTORY, this, resource, 7, 77));
            } else if(GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.SAWING) {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.ELITE_FACTORY, this, resource, 7, 77));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 95));
            } else if (GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.COMPRESSING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.PURIFYING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.INJECTING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.INFUSING) {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 77));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 86));
            } else {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 77));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 86));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY, this, resource, 7, 95));
            }
        }else if(tile.tier == FactoryTier.ULTIMATE){//终极工厂
            if (GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.FARM) {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.ULTIMATE_FACTORY, this, resource, 7, 77));
            } else if(GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.SAWING) {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.ULTIMATE_FACTORY, this, resource, 7, 77));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY_LOG, this, resource, 7, 95));
            } else if (GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.COMPRESSING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.PURIFYING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.INJECTING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.INFUSING) {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY_LOG, this, resource, 7, 77));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY_LOG, this, resource, 7, 86));
            } else {
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY_LOG, this, resource, 7, 77));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY_LOG, this, resource, 7, 86));
                addGuiElement(new GuiStorage(GuiStorage.StorageType.OVERLAY_LOG, this, resource, 7, 95));
            }
        }
//隐藏气体，流体显示结束

//输入输出插槽开始
        if(tile.tier == FactoryTier.BASIC){//基础
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,54,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,92,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,130,12));
            if (GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.SAWING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.FARM) {
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,54,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,92,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,130,56));
            }else {
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,54,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,92,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,130,56));
            }
        } else if (tile.tier == FactoryTier.ADVANCED){//高级
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,34,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,60,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,86,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,112,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,138,12));
            if (GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.SAWING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.FARM) {
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,34,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,60,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,86,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,112,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,138,56));
            }else {
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,34,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,60,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,86,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,112,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,138,56));
            }
        }else if (tile.tier == FactoryTier.ELITE){//精英
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,28,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,47,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,66,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,85,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,104,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,123,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,142,12));
            if (GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.SAWING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.FARM) {
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,28,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,47,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,66,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,85,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,104,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,123,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,142,56));
            }else {
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,28,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,47,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,66,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,85,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,104,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,123,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,142,56));
            }
        }else if (tile.tier == FactoryTier.ULTIMATE){//终极
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,26,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,45,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,64,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,83,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,102,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,121,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,140,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,159,12));
            addGuiElement(new GuiSlot(GuiSlot.SlotType.INPUT ,this, resource,178,12));
            if (GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.SAWING || GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.FARM) {
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,26,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,45,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,64,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,83,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,102,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,121,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,140,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,159,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT_LOG ,this, resource,178,56));
            }else {
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,26,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,45,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,64,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,83,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,102,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,121,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,140,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,159,56));
                addGuiElement(new GuiSlot(GuiSlot.SlotType.OUTPUT,this, resource,178,56));
            }
        }
//输入输出插槽结束

        addGuiElement(new GuiSlot(GuiSlot.SlotType.POWER ,this, resource,6,12).with(GuiSlot.SlotOverlay.POWER));
        addGuiElement(new GuiSlot(GuiSlot.SlotType.EXTRA ,this, resource,6,56));


        addGuiElement(new GuiSideConfigurationTab(this, tileEntity, resource));
        addGuiElement(new GuiTransporterConfigTab(this, 34, tileEntity, resource));
        addGuiElement(new GuiSortingTab(this, tileEntity, resource));
        addGuiElement(new GuiEnergyInfo(() -> {
            String multiplier = MekanismUtils.getEnergyDisplay(tileEntity.lastUsage);
            return Arrays.asList(LangUtils.localize("gui.using") + ": " + multiplier + "/t",
                    LangUtils.localize("gui.needed") + ": " + MekanismUtils.getEnergyDisplay(tileEntity.getMaxEnergy() - tileEntity.getEnergy()));
        }, this, resource));
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(this.infuserDumpButton = new GuiButtonImage(1, this.guiLeft+6, this.guiTop+44, 21, 10, 147, 72, 0, MekanismUtils.getResource(ResourceType.GUI, "GuiMetallurgicInfuser.png")){
            @Override
            public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
                if (GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.INFUSING) {
                    super.drawButton(mc, mouseX, mouseY, partialTicks);
                }
            }

            @Override
            public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                return GuiFactoryback.this.tileEntity.getRecipeType() == RecipeType.INFUSING && super.mousePressed(mc, mouseX, mouseY);
            }
        });
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.drawString(tileEntity.getName(), (xSize / 2) - (fontRenderer.getStringWidth(tileEntity.getName()) / 2), 4, 0x404040);
        fontRenderer.drawString(LangUtils.localize("container.inventory"), 8, (ySize - 93) + 2, 0x404040);
        int xAxis = mouseX - guiLeft;
        int yAxis = mouseY - guiTop;
        if (tileEntity.tier == FactoryTier.BASIC || tileEntity.tier == FactoryTier.ADVANCED || tileEntity.tier == FactoryTier.ELITE) {
        if (xAxis >= 165 && xAxis <= 169 && yAxis >= 17 && yAxis <= 69) {
            displayTooltip(MekanismUtils.getEnergyDisplay(tileEntity.getEnergy(), tileEntity.getMaxEnergy()), xAxis, yAxis);
        } else if (xAxis >= 8 && xAxis <= 168 && yAxis >= 96 && yAxis <= 101) {
            if (tileEntity.getRecipeType().getFuelType() == MachineFuelType.ADVANCED||tileEntity.getRecipeType().getFuelType() == MachineFuelType.FARM) {
                GasStack gasStack = tileEntity.gasTank.getGas();
                displayTooltip(gasStack != null ? gasStack.getGas().getLocalizedName() + ": " + tileEntity.gasTank.getStored() : LangUtils.localize("gui.none"), xAxis, yAxis);
            } else if (tileEntity.getRecipeType() == RecipeType.INFUSING) {
                InfuseType type = tileEntity.infuseStored.getType();
                displayTooltip(type != null ? type.getLocalizedName() + ": " + tileEntity.infuseStored.getAmount() : LangUtils.localize("gui.empty"), xAxis, yAxis);
            }
        }
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        }
        if (tileEntity.tier == FactoryTier.ULTIMATE) {
            if (xAxis >= 199 && xAxis <= 203 && yAxis >= 17 && yAxis <= 69) {
                displayTooltip(MekanismUtils.getEnergyDisplay(tileEntity.getEnergy(), tileEntity.getMaxEnergy()), xAxis, yAxis);
            } else if (xAxis >= 8 && xAxis <= 202 && yAxis >= 96 && yAxis <= 101) {
                if (tileEntity.getRecipeType().getFuelType() == MachineFuelType.ADVANCED||tileEntity.getRecipeType().getFuelType() == MachineFuelType.FARM) {
                    GasStack gasStack = tileEntity.gasTank.getGas();
                    displayTooltip(gasStack != null ? gasStack.getGas().getLocalizedName() + ": " + tileEntity.gasTank.getStored() : LangUtils.localize("gui.none"), xAxis, yAxis);
                } else if (tileEntity.getRecipeType() == RecipeType.INFUSING) {
                    InfuseType type = tileEntity.infuseStored.getType();
                    displayTooltip(type != null ? type.getLocalizedName() + ": " + tileEntity.infuseStored.getAmount() : LangUtils.localize("gui.empty"), xAxis, yAxis);
                }
            }
            super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(int xAxis, int yAxis) {
        super.drawGuiContainerBackgroundLayer(xAxis, yAxis);
        int displayInt = tileEntity.getScaledEnergyLevel(52);
        if (tileEntity.tier == FactoryTier.ULTIMATE) {
            drawTexturedModalRect(guiLeft + 199, guiTop + 17 + 52 - displayInt, 210, 52 - displayInt, 4, displayInt);
        }else if (tileEntity.tier == FactoryTier.BASIC || tileEntity.tier == FactoryTier.ADVANCED || tileEntity.tier == FactoryTier.ELITE) {
            drawTexturedModalRect(guiLeft + 165, guiTop + 17 + 52 - displayInt, 176, 52 - displayInt, 4, displayInt);
        }
        int xOffset = tileEntity.tier == FactoryTier.BASIC ? 59 : tileEntity.tier == FactoryTier.ADVANCED ? 39 : tileEntity.tier == FactoryTier.ULTIMATE ? 31 : 33;
        int xDistance = tileEntity.tier == FactoryTier.BASIC ? 38 : tileEntity.tier == FactoryTier.ADVANCED ? 26 : tileEntity.tier == FactoryTier.ULTIMATE ? 19 : 19;

        for (int i = 0; i < tileEntity.tier.processes; i++) {
            int xPos = xOffset + (i * xDistance);
            displayInt = tileEntity.getScaledProgress(20, i);
        if (tileEntity.tier == FactoryTier.BASIC || tileEntity.tier == FactoryTier.ADVANCED ||tileEntity.tier == FactoryTier.ELITE) {
            drawTexturedModalRect(guiLeft + xPos, guiTop + 33, 176, 52, 8, displayInt);
        }else if (tileEntity.tier == FactoryTier.ULTIMATE) {
            drawTexturedModalRect(guiLeft + xPos, guiTop + 33, 210, 52, 8, displayInt);
        }
        }

        if (tileEntity.getRecipeType().getFuelType() == MachineFuelType.ADVANCED||tileEntity.getRecipeType().getFuelType() == MachineFuelType.FARM) {
            if (tileEntity.getScaledGasLevel(160) > 0) {
                GasStack gas = tileEntity.gasTank.getGas();
                if (gas != null) {
                    MekanismRenderer.color(gas);
                    if (tileEntity.tier == FactoryTier.ULTIMATE) {
                        displayGauge(8, 96, tileEntity.getScaledGasLevel(194), 5, gas.getGas().getSprite());
                    }else if (tileEntity.tier == FactoryTier.BASIC || tileEntity.tier == FactoryTier.ADVANCED ||tileEntity.tier == FactoryTier.ELITE) {
                        displayGauge(8, 96, tileEntity.getScaledGasLevel(160), 5, gas.getGas().getSprite());
                    }
                    MekanismRenderer.resetColor();
                }
            }
        } else if (tileEntity.getRecipeType() == RecipeType.INFUSING) {
            if (tileEntity.getScaledInfuseLevel(160) > 0) {
                if (tileEntity.tier == FactoryTier.ULTIMATE) {
                    displayGauge(8, 96, tileEntity.getScaledInfuseLevel(194), 5, tileEntity.infuseStored.getType().sprite);
                }else if (tileEntity.tier == FactoryTier.BASIC || tileEntity.tier == FactoryTier.ADVANCED ||tileEntity.tier == FactoryTier.ELITE ) {
                    displayGauge(8, 96, tileEntity.getScaledInfuseLevel(160), 5, tileEntity.infuseStored.getType().sprite);
                }

            }
        }
    }

    public void displayGauge(int xPos, int yPos, int sizeX, int sizeY, TextureAtlasSprite icon) {
        if (icon != null) {
            mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            drawTexturedModalRect(guiLeft + xPos, guiTop + yPos, icon, sizeX, sizeY);
        }
    }

    @Override
    protected void mouseClicked(int x, int y, int button) throws IOException {
        super.mouseClicked(x, y, button);
        if (button == 0 || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            int xAxis = x - guiLeft;
            int yAxis = y - guiTop;
            if (tileEntity.tier == FactoryTier.BASIC || tileEntity.tier == FactoryTier.ADVANCED ||tileEntity.tier == FactoryTier.ELITE) {
                if (xAxis > 8 && xAxis < 168 && yAxis > 96 && yAxis < 101) {
                    ItemStack stack = mc.player.inventory.getItemStack();
                    if (!stack.isEmpty() && stack.getItem() instanceof ItemGaugeDropper) {
                    TileNetworkList data = TileNetworkList.withContents(1);
                    Mekanism.packetHandler.sendToServer(new TileEntityMessage(tileEntity, data));
                    SoundHandler.playSound(SoundEvents.UI_BUTTON_CLICK);
                    }
                }
            }else if(tileEntity.tier == FactoryTier.ULTIMATE) {
                if (xAxis > 8 && xAxis < 202 && yAxis > 96 && yAxis < 101) {
                    ItemStack stack = mc.player.inventory.getItemStack();
                    if (!stack.isEmpty() && stack.getItem() instanceof ItemGaugeDropper) {
                        TileNetworkList data = TileNetworkList.withContents(1);
                        Mekanism.packetHandler.sendToServer(new TileEntityMessage(tileEntity, data));
                        SoundHandler.playSound(SoundEvents.UI_BUTTON_CLICK);
                    }
                }
            }

        }
    }

    @Override
    protected ResourceLocation getGuiLocation() {
        return tileEntity.tier.guiLocation;
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        if (button == this.infuserDumpButton) {
            TileNetworkList data = TileNetworkList.withContents(1);
            Mekanism.packetHandler.sendToServer(new TileEntityMessage(tileEntity, data));
            SoundHandler.playSound(SoundEvents.UI_BUTTON_CLICK);
        }
    }
}

 */