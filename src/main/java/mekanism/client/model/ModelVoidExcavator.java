package mekanism.client.model;

import mekanism.client.render.MekanismRenderer;
import mekanism.client.render.MekanismRenderer.GlowInfo;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.MekanismUtils.ResourceType;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelVoidExcavator extends ModelBase {

    public static ResourceLocation OVERLAY_ON = MekanismUtils.getResource(ResourceType.RENDER, "VoidExcavator_OverlayOn.png");
    public static ResourceLocation OVERLAY_OFF = MekanismUtils.getResource(ResourceType.RENDER, "VoidExcavator_OverlayOff.png");

    ModelRenderer keyboard;
    ModelRenderer keyboardBottom;
    ModelRenderer keyboardSupportExt1;
    ModelRenderer keyboardSupportExt2;
    ModelRenderer keyboardSupport1;
    ModelRenderer keyboardSupport2;
    ModelRenderer monitor1back;
    ModelRenderer monitor2back;
    ModelRenderer monitor3back;
    ModelRenderer monitorBar1;
    ModelRenderer monitorBar2;
    ModelRenderer led1;
    ModelRenderer led2;
    ModelRenderer led3;
    ModelRenderer monitorMount1;
    ModelRenderer monitorMount2;
    ModelRenderer frame1;
    ModelRenderer frame3;
    ModelRenderer bracket1;
    ModelRenderer bracket2;
    ModelRenderer bracket3;
    ModelRenderer bracket4;
    ModelRenderer bracket5;
    ModelRenderer bracket6;
    ModelRenderer bracket7;
    ModelRenderer bracket8;
    ModelRenderer bracketPlate1;
    ModelRenderer bracketPlate2;
    ModelRenderer bracketPlate3;
    ModelRenderer bracketPlate4;
    ModelRenderer supportBeam1;
    ModelRenderer supportBeam2;
    ModelRenderer supportBeam3;
    ModelRenderer supportBeam4;
    ModelRenderer foot1;
    ModelRenderer foot2;
    ModelRenderer foot3;
    ModelRenderer foot4;
    ModelRenderer core;
    ModelRenderer frame2a;
    ModelRenderer frame2b;
    ModelRenderer frame2c;
    ModelRenderer monitor1;
    ModelRenderer monitor2;
    ModelRenderer monitor3;
    ModelRenderer powerCable1b2;
    ModelRenderer powerConnector2;
    ModelRenderer powerCable1b3;
    ModelRenderer powerConnector3;

    public ModelVoidExcavator() {
        textureWidth = 256;
        textureHeight = 128;

        keyboard = new ModelRenderer(this);
        keyboard.setRotationPoint(-5.0F, 14.0F, -5.0F);
        setRotation(keyboard, -1.0821F, 0.0175F, 0.0F);
        keyboard.cubeList.add(new ModelBox(keyboard, 120, 20, 0.0611F, 0.0898F, -2.6429F, 10, 5, 1, 0.0F, true));

        keyboardBottom = new ModelRenderer(this);
        keyboardBottom.setRotationPoint(-4.0F, 14.0F, -5.0F);
        setRotation(keyboardBottom, -0.9076F, 0.0F, 0.0F);
        keyboardBottom.cubeList.add(new ModelBox(keyboardBottom, 120, 26, 0.0F, 0.258F, -2.6548F, 8, 4, 1, 0.0F, true));

        keyboardSupportExt1 = new ModelRenderer(this);
        keyboardSupportExt1.setRotationPoint(2.0F, 14.0F, -5.0F);
        keyboardSupportExt1.cubeList.add(new ModelBox(keyboardSupportExt1, 138, 26, 0.0F, 0.0F, -4.5F, 1, 1, 1, 0.0F, true));

        keyboardSupportExt2 = new ModelRenderer(this);
        keyboardSupportExt2.setRotationPoint(-3.0F, 14.0F, -5.0F);
        keyboardSupportExt2.cubeList.add(new ModelBox(keyboardSupportExt2, 138, 26, 0.0F, 0.0F, -4.5F, 1, 1, 1, 0.0F, true));

        keyboardSupport1 = new ModelRenderer(this);
        keyboardSupport1.setRotationPoint(-3.0F, 14.0F, -5.0F);
        keyboardSupport1.cubeList.add(new ModelBox(keyboardSupport1, 142, 20, 0.0F, -1.0F, -3.5F, 1, 2, 4, 0.0F, true));

        keyboardSupport2 = new ModelRenderer(this);
        keyboardSupport2.setRotationPoint(2.0F, 14.0F, -5.0F);
        keyboardSupport2.cubeList.add(new ModelBox(keyboardSupport2, 142, 20, 0.0F, -1.0F, -3.5F, 1, 2, 4, 0.0F, true));

        monitor1back = new ModelRenderer(this);
        monitor1back.setRotationPoint(-8.0F, 3.0F, -3.0F);
        setRotation(monitor1back, 0.0873F, -0.2094F, 0.0F);
        monitor1back.cubeList.add(new ModelBox(monitor1back, 88, 32, -13.7277F, -3.2984F, -3.4105F, 12, 6, 1, 0.0F, true));

        monitor2back = new ModelRenderer(this);
        monitor2back.setRotationPoint(-6.0F, 4.0F, -3.0F);
        setRotation(monitor2back, 0.0873F, 0.0F, 0.0F);
        monitor2back.cubeList.add(new ModelBox(monitor2back, 88, 32, 0.0F, -4.305F, -3.4867F, 12, 6, 1, 0.0F, true));

        monitor3back = new ModelRenderer(this);
        monitor3back.setRotationPoint(8.0F, 3.0F, -3.0F);
        setRotation(monitor3back, 0.0873F, 0.2094F, 0.0F);
        monitor3back.cubeList.add(new ModelBox(monitor3back, 88, 32, 1.7277F, -3.2984F, -3.4105F, 12, 6, 1, 0.0F, false));

        monitorBar1 = new ModelRenderer(this);
        monitorBar1.setRotationPoint(-6.0F, 4.0F, -3.0F);
        setRotation(monitorBar1, 0.0873F, -0.0524F, 0.0F);
        monitorBar1.cubeList.add(new ModelBox(monitorBar1, 114, 36, -3.6832F, -2.3046F, -3.6819F, 4, 2, 1, 0.0F, true));

        monitorBar2 = new ModelRenderer(this);
        monitorBar2.setRotationPoint(5.0F, 4.0F, -3.0F);
        setRotation(monitorBar2, 0.0873F, 0.0524F, 0.0F);
        monitorBar2.cubeList.add(new ModelBox(monitorBar2, 114, 36, 0.6832F, -2.3046F, -3.6819F, 4, 2, 1, 0.0F, true));

        led1 = new ModelRenderer(this);
        led1.setRotationPoint(-8.0F, 3.0F, -3.0F);
        setRotation(led1, 0.0873F, -0.2094F, 0.0F);
        led1.cubeList.add(new ModelBox(led1, 0, 0, -2.7277F, 4.2016F, -5.3105F, 1, 1, 1, 0.0F, true));

        led2 = new ModelRenderer(this);
        led2.setRotationPoint(-7.0F, 3.0F, -3.0F);
        setRotation(led2, 0.0873F, 0.0F, 0.0F);
        led2.cubeList.add(new ModelBox(led2, 0, 0, 12.0F, 4.1616F, -5.3867F, 1, 1, 1, 0.0F, true));

        led3 = new ModelRenderer(this);
        led3.setRotationPoint(8.0F, 3.0F, -3.0F);
        setRotation(led3, 0.0873F, 0.2094F, 0.0F);
        led3.cubeList.add(new ModelBox(led3, 0, 0, 12.7277F, 4.2016F, -5.3105F, 1, 1, 1, 0.0F, true));

        monitorMount1 = new ModelRenderer(this);
        monitorMount1.setRotationPoint(-4.0F, 3.0F, -3.0F);
        monitorMount1.cubeList.add(new ModelBox(monitorMount1, 114, 32, 0.0F, -1.0F, -3.5F, 2, 2, 2, 0.0F, true));

        monitorMount2 = new ModelRenderer(this);
        monitorMount2.setRotationPoint(2.0F, 3.0F, -3.0F);
        monitorMount2.cubeList.add(new ModelBox(monitorMount2, 114, 32, 0.0F, -1.0F, -3.5F, 2, 2, 2, 0.0F, true));

        frame1 = new ModelRenderer(this);
        frame1.setRotationPoint(-16.0F, -8.0F, -1.0F);
        frame1.cubeList.add(new ModelBox(frame1, 0, 0, 0.0F, 0.0F, -3.5F, 32, 29, 12, 0.0F, true));

        frame3 = new ModelRenderer(this);
        frame3.setRotationPoint(-16.0F, -8.0F, 28.0F);
        frame3.cubeList.add(new ModelBox(frame3, 0, 0, 0.0F, 0.0F, -3.5F, 32, 29, 12, 0.0F, true));

        bracket1 = new ModelRenderer(this);
        bracket1.setRotationPoint(-21.0F, -5.0F, 0.0F);
        bracket1.cubeList.add(new ModelBox(bracket1, 16, 85, 0.0F, 0.0F, -3.5F, 5, 5, 2, 0.0F, true));

        bracket2 = new ModelRenderer(this);
        bracket2.setRotationPoint(-21.0F, -5.0F, 8.0F);
        bracket2.cubeList.add(new ModelBox(bracket2, 16, 85, 0.0F, 0.0F, -3.5F, 5, 5, 2, 0.0F, true));

        bracket3 = new ModelRenderer(this);
        bracket3.setRotationPoint(-21.0F, -5.0F, 29.0F);
        bracket3.cubeList.add(new ModelBox(bracket3, 16, 85, 0.0F, 0.0F, -3.5F, 5, 5, 2, 0.0F, true));

        bracket4 = new ModelRenderer(this);
        bracket4.setRotationPoint(-21.0F, -5.0F, 37.0F);
        bracket4.cubeList.add(new ModelBox(bracket4, 16, 85, 0.0F, 0.0F, -3.5F, 5, 5, 2, 0.0F, true));

        bracket5 = new ModelRenderer(this);
        bracket5.setRotationPoint(16.0F, -5.0F, 0.0F);
        bracket5.cubeList.add(new ModelBox(bracket5, 16, 85, 0.0F, 0.0F, -3.5F, 5, 5, 2, 0.0F, false));

        bracket6 = new ModelRenderer(this);
        bracket6.setRotationPoint(16.0F, -5.0F, 8.0F);
        bracket6.cubeList.add(new ModelBox(bracket6, 16, 85, 0.0F, 0.0F, -3.5F, 5, 5, 2, 0.0F, true));

        bracket7 = new ModelRenderer(this);
        bracket7.setRotationPoint(16.0F, -5.0F, 29.0F);
        bracket7.cubeList.add(new ModelBox(bracket7, 16, 85, 0.0F, 0.0F, -3.5F, 5, 5, 2, 0.0F, true));

        bracket8 = new ModelRenderer(this);
        bracket8.setRotationPoint(16.0F, -5.0F, 37.0F);
        bracket8.cubeList.add(new ModelBox(bracket8, 16, 85, 0.0F, 0.0F, -3.5F, 5, 5, 2, 0.0F, false));

        bracketPlate1 = new ModelRenderer(this);
        bracketPlate1.setRotationPoint(-17.0F, -5.0F, 2.0F);
        bracketPlate1.cubeList.add(new ModelBox(bracketPlate1, 30, 85, 0.0F, 0.0F, -3.5F, 1, 5, 6, 0.0F, true));

        bracketPlate2 = new ModelRenderer(this);
        bracketPlate2.setRotationPoint(-17.0F, -5.0F, 31.0F);
        bracketPlate2.cubeList.add(new ModelBox(bracketPlate2, 30, 85, 0.0F, 0.0F, -3.5F, 1, 5, 6, 0.0F, true));

        bracketPlate3 = new ModelRenderer(this);
        bracketPlate3.setRotationPoint(16.0F, -5.0F, 2.0F);
        bracketPlate3.cubeList.add(new ModelBox(bracketPlate3, 30, 85, 0.0F, 0.0F, -3.5F, 1, 5, 6, 0.0F, true));

        bracketPlate4 = new ModelRenderer(this);
        bracketPlate4.setRotationPoint(16.0F, -5.0F, 31.0F);
        bracketPlate4.cubeList.add(new ModelBox(bracketPlate4, 30, 85, 0.0F, 0.0F, -3.5F, 1, 5, 6, 0.0F, true));

        supportBeam1 = new ModelRenderer(this);
        supportBeam1.setRotationPoint(-22.0F, -6.0F, 1.0F);
        supportBeam1.cubeList.add(new ModelBox(supportBeam1, 0, 85, 0.0F, 0.0F, -3.5F, 4, 28, 8, 0.0F, true));

        supportBeam2 = new ModelRenderer(this);
        supportBeam2.setRotationPoint(-22.0F, -6.0F, 30.0F);
        supportBeam2.cubeList.add(new ModelBox(supportBeam2, 0, 85, 0.0F, 0.0F, -3.5F, 4, 28, 8, 0.0F, true));

        supportBeam3 = new ModelRenderer(this);
        supportBeam3.setRotationPoint(18.0F, -6.0F, 1.0F);
        supportBeam3.cubeList.add(new ModelBox(supportBeam3, 0, 85, 0.0F, 0.0F, -3.5F, 4, 28, 8, 0.0F, true));

        supportBeam4 = new ModelRenderer(this);
        supportBeam4.setRotationPoint(18.0F, -6.0F, 30.0F);
        supportBeam4.cubeList.add(new ModelBox(supportBeam4, 0, 85, 0.0F, 0.0F, -3.5F, 4, 28, 8, 0.0F, false));

        foot1 = new ModelRenderer(this);
        foot1.setRotationPoint(-23.0F, 22.0F, 0.0F);
        foot1.cubeList.add(new ModelBox(foot1, 44, 85, 0.0F, 0.0F, -3.5F, 7, 2, 10, 0.0F, true));

        foot2 = new ModelRenderer(this);
        foot2.setRotationPoint(-23.0F, 22.0F, 29.0F);
        foot2.cubeList.add(new ModelBox(foot2, 44, 85, 0.0F, 0.0F, -3.5F, 7, 2, 10, 0.0F, true));

        foot3 = new ModelRenderer(this);
        foot3.setRotationPoint(16.0F, 22.0F, 29.0F);
        foot3.cubeList.add(new ModelBox(foot3, 44, 85, 0.0F, 0.0F, -3.5F, 7, 2, 10, 0.0F, true));

        foot4 = new ModelRenderer(this);
        foot4.setRotationPoint(16.0F, 22.0F, 0.0F);
        foot4.cubeList.add(new ModelBox(foot4, 44, 85, 0.0F, 0.0F, -3.5F, 7, 2, 10, 0.0F, true));

        core = new ModelRenderer(this);
        core.setRotationPoint(-15.0F, -7.0F, 11.0F);
        core.cubeList.add(new ModelBox(core, 0, 41, 0.0F, 0.0F, -3.5F, 30, 17, 17, 0.0F, true));

        frame2a = new ModelRenderer(this);
        frame2a.setRotationPoint(-16.0F, -8.0F, 12.0F);
        frame2a.cubeList.add(new ModelBox(frame2a, 88, 0, 0.0F, 0.0F, -3.5F, 32, 5, 15, 0.0F, true));

        frame2b = new ModelRenderer(this);
        frame2b.setRotationPoint(-16.0F, -2.0F, 12.0F);
        frame2b.cubeList.add(new ModelBox(frame2b, 126, 50, 0.0F, 0.0F, -3.5F, 32, 5, 15, 0.0F, true));

        frame2c = new ModelRenderer(this);
        frame2c.setRotationPoint(-16.0F, 4.0F, 12.0F);
        frame2c.cubeList.add(new ModelBox(frame2c, 88, 90, 0.0F, 0.0F, -3.5F, 32, 5, 15, 0.0F, true));

        monitor1 = new ModelRenderer(this);
        monitor1.setRotationPoint(-8.0F, 3.0F, -3.0F);
        setRotation(monitor1, 0.0873F, -0.2094F, 0.0F);
        monitor1.cubeList.add(new ModelBox(monitor1, 88, 20, -14.7277F, -5.2984F, -5.4105F, 14, 10, 2, 0.0F, true));

        monitor2 = new ModelRenderer(this);
        monitor2.setRotationPoint(-7.0F, 3.0F, -3.0F);
        setRotation(monitor2, 0.0873F, 0.0F, 0.0F);
        monitor2.cubeList.add(new ModelBox(monitor2, 152, 20, 0.0F, -5.305F, -5.4867F, 14, 10, 2, 0.0F, true));

        monitor3 = new ModelRenderer(this);
        monitor3.setRotationPoint(8.0F, 3.0F, -3.0F);
        setRotation(monitor3, 0.0873F, 0.2094F, 0.0F);
        monitor3.cubeList.add(new ModelBox(monitor3, 152, 32, 0.7277F, -5.2984F, -5.4105F, 14, 10, 2, 0.0F, true));

        powerCable1b2 = new ModelRenderer(this);
        powerCable1b2.setRotationPoint(-3.0F, 20.0F, 13.0F);
        powerCable1b2.cubeList.add(new ModelBox(powerCable1b2, 94, 52, 11.0F, -12.0F, 0.25F, 6, 3, 6, 0.0F, true));

        powerConnector2 = new ModelRenderer(this);
        powerConnector2.setRotationPoint(-4.0F, 23.0F, 12.0F);
        powerConnector2.cubeList.add(new ModelBox(powerConnector2, 94, 61, 11.0F, -12.0F, 0.25F, 8, 1, 8, 0.0F, true));

        powerCable1b3 = new ModelRenderer(this);
        powerCable1b3.setRotationPoint(-3.0F, 20.0F, 13.0F);
        powerCable1b3.cubeList.add(new ModelBox(powerCable1b3, 94, 52, -11.0F, -12.0F, 0.25F, 6, 3, 6, 0.0F, true));

        powerConnector3 = new ModelRenderer(this);
        powerConnector3.setRotationPoint(-4.0F, 23.0F, 12.0F);
        powerConnector3.cubeList.add(new ModelBox(powerConnector3, 94, 61, -11.0F, -12.0F, 0.25F, 8, 1, 8, 0.0F, true));
    }

    public void render(float size, boolean on, TextureManager manager, boolean renderMain) {
        GlStateManager.pushMatrix();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);

        if (renderMain) {
            doRender(size);
        }

        manager.bindTexture(on ? OVERLAY_ON : OVERLAY_OFF);
        GlStateManager.scale(1.001F, 1.001F, 1.001F);
        GlStateManager.translate(-0.0011F, -0.0011F, -0.0011F);
        GlowInfo glowInfo = MekanismRenderer.enableGlow();

        doRender(size);

        MekanismRenderer.disableGlow(glowInfo);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
    }

    private void doRender(float size) {
        keyboard.render(size);
        keyboardBottom.render(size);
        keyboardSupportExt1.render(size);
        keyboardSupportExt2.render(size);
        keyboardSupport1.render(size);
        keyboardSupport2.render(size);
        monitor1back.render(size);
        monitor2back.render(size);
        monitor3back.render(size);
        monitorBar1.render(size);
        monitorBar2.render(size);
        led1.render(size);
        led2.render(size);
        led3.render(size);
        monitor1.render(size);
        monitor2.render(size);
        monitor3.render(size);
        monitorMount1.render(size);
        monitorMount2.render(size);
        frame1.render(size);
        frame3.render(size);
        bracket1.render(size);
        bracket2.render(size);
        bracket3.render(size);
        bracket4.render(size);
        bracket5.render(size);
        bracket6.render(size);
        bracket7.render(size);
        bracket8.render(size);
        bracketPlate1.render(size);
        bracketPlate2.render(size);
        bracketPlate3.render(size);
        bracketPlate4.render(size);
        supportBeam1.render(size);
        supportBeam2.render(size);
        supportBeam3.render(size);
        supportBeam4.render(size);
        foot1.render(size);
        foot2.render(size);
        foot3.render(size);
        foot4.render(size);
        core.render(size);
        frame2a.render(size);
        frame2b.render(size);
        frame2c.render(size);
        powerCable1b2.render(size);
        powerCable1b3.render(size);
        powerConnector2.render(size);
        powerConnector3.render(size);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}