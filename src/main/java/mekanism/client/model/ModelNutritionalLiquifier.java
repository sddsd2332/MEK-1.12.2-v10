package mekanism.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelNutritionalLiquifier extends ModelBase {

    ModelRenderer base;
    ModelRenderer glass1;
    ModelRenderer glass2;
    ModelRenderer glass3;
    ModelRenderer glass4;
    ModelRenderer top;
    ModelRenderer support;
    ModelRenderer support1;
    ModelRenderer support2;
    ModelRenderer support3;
    ModelRenderer support4;
    ModelRenderer blade4;
    ModelRenderer blade3;
    ModelRenderer blade2;
    ModelRenderer blade1;
    ModelRenderer pillar;
    ModelRenderer pillar2;
    ModelRenderer pillar3;
    ModelRenderer pillar4;


    public ModelNutritionalLiquifier() {
        textureWidth = 128;
        textureHeight = 128;

        base = new ModelRenderer(this, 0, 0);
        base.addBox( -8.0F, -5.0F, -8.0F, 16, 5, 16);
        base.setRotationPoint(0.0F, 24.0F, 0.0F);
        base.setTextureSize(128, 128);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);

        glass1 = new ModelRenderer(this, 31, 40);
        glass1.addBox(7.0F, -15.0F, -7.0F, 1, 10, 14);
        glass1.setRotationPoint(0.0F, 24.0F, 0.0F);
        glass1.setTextureSize(128, 128);
        glass1.mirror = true;
        setRotation(glass1, 0F, 0F, 0F);

        glass2 = new ModelRenderer(this, 49, 0);
        glass2.addBox(-6.0F, -15.0F, -7.0F, 14, 10, 1);
        glass2.setRotationPoint(-1.0F, 24.0F, -1.0F);
        glass2.setTextureSize(128, 128);
        glass2.mirror = true;
        setRotation(glass2, 0F, 0F, 0F);

        glass3 = new ModelRenderer(this, 48, 40);
        glass3.addBox(-6.0F, -15.0F, -7.0F, 14, 10, 1);
        glass3.setRotationPoint(-1.0F, 24.0F, 14.0F);
        glass3.setTextureSize(128, 128);
        glass3.mirror = true;
        setRotation(glass3, 0F, 0F, 0F);

        glass4 = new ModelRenderer(this,  0, 40);
        glass4.addBox(7.0F, -15.0F, -7.0F, 1, 10, 14);
        glass4.setRotationPoint(-15.0F, 24.0F, 0.0F);
        glass4.setTextureSize(128, 128);
        glass4.mirror = true;
        setRotation(glass4, 0F, 0F, 0F);

        top = new ModelRenderer(this, 0, 22);
        top.addBox(-8.0F, -16.0F, -8.0F, 16, 1, 16);
        top.setRotationPoint(0.0F, 24.0F, 0.0F);
        top.setTextureSize(128, 128);
        top.mirror = true;
        setRotation(top, 0F, 0F, 0F);

        support = new ModelRenderer(this, 5, 22);
        support.addBox(-1.0F, -7.0F, -1.0F, 2, 2, 2);
        support.setRotationPoint(0.0F, 24.0F, 0.0F);
        support.setTextureSize(128, 128);
        support.mirror = true;
        setRotation(support, 0F, 0F, 0F);

        support1 = new ModelRenderer(this, 26, 40);
        support1.addBox(1.0F, -7.0F, -1.0F, 2, 0, 2);
        support1.setRotationPoint(0.0F, 24.0F, 0.0F);
        support1.setTextureSize(128, 128);
        support1.mirror = true;
        setRotation(support1, 0F, 0F, 0F);

        support2 = new ModelRenderer(this, 17, 40);
        support2.addBox(-3.0F, -7.0F, -1.0F, 2, 0, 2);
        support2.setRotationPoint(0.0F, 24.0F, 0.0F);
        support2.setTextureSize(128, 128);
        support2.mirror = true;
        setRotation(support2, 0F, 0F, 0F);

        support3 = new ModelRenderer(this, 0, 40);
        support3.addBox(-1.0F, -7.0F, -3.0F, 2, 0, 2);
        support3.setRotationPoint(0.0F, 24.0F, 0.0F);
        support3.setTextureSize(128, 128);
        support3.mirror = true;
        setRotation(support3, 0F, 0F, 0F);

        support4 = new ModelRenderer(this, 3, 33);
        support4.addBox(-1.0F, -7.0F, 1.0F, 2, 0, 2);
        support4.setRotationPoint(0.0F, 24.0F, 0.0F);
        support4.setTextureSize(128, 128);
        support4.mirror = true;
        setRotation(support4, 0F, 0F, 0F);

        blade4 = new ModelRenderer(this, 5, 30);
        blade4.addBox(7.075F, -2.825F, -1.0F, 2, 0, 2);
        blade4.setRotationPoint(0.0F, 24.0F, 0.0F);
        blade4.setTextureSize(128, 128);
        blade4.mirror = true;
        setRotation(blade4, 0.0F, 0.0F, -0.7854F);

        blade3 = new ModelRenderer(this, 5, 27);
        blade3.addBox(-9.075F, -2.825F, -1.0F, 2, 0, 2, 0.0F);
        blade3.setRotationPoint(0.0F, 24.0F, 0.0F);
        blade3.setTextureSize(128, 128);
        blade3.mirror = true;
        setRotation(blade3, 0.0F, 0.0F, 0.7854F);

        blade2 = new ModelRenderer(this, 7, 13);
        blade2.addBox(-1.0F, -7.075F, 0.825F, 2, 0, 2);
        blade2.setRotationPoint(0.0F, 24.0F, 0.0F);
        blade2.setTextureSize(128, 128);
        blade2.mirror = true;
        setRotation(blade2,0.7854F, 0.0F, 0.0F);

        blade1 = new ModelRenderer(this, 0, 12);
        blade1.addBox(-1.0F, -7.075F, -2.825F, 2, 0, 2);
        blade1.setRotationPoint(0.0F, 24.0F, 0.0F);
        blade1.setTextureSize(128, 128);
        blade1.mirror = true;
        setRotation(blade1,-0.7854F, 0.0F, 0.0F);



        pillar = new ModelRenderer(this, 0, 22);
        pillar.addBox(-8.0F, -15.0F, 7.0F, 1, 10, 1, 0.0F);
        pillar.setRotationPoint(15.0F, 24.0F, 0.0F);
        pillar.setTextureSize(128, 128);
        pillar.mirror = true;
        setRotation(pillar, 0F, 0F, 0F);




        pillar2 = new ModelRenderer(this, 10, 0);
        pillar2.addBox(22.0F, -15.0F, 7.0F, 1, 10, 1);
        pillar2.setRotationPoint(-30.0F, 24.0F, 0.0F);
        pillar2.setTextureSize(128, 128);
        pillar2.mirror = true;
        setRotation(pillar2, 0F, 0F, 0F);

        pillar3 = new ModelRenderer(this, 5, 0);
        pillar3.addBox(-8.0F, -15.0F, 7.0F, 1, 10, 1);
        pillar3.setRotationPoint(0.0F, 24.0F, -15.0F);
        pillar3.setTextureSize(128, 128);
        pillar3.mirror = true;
        setRotation(pillar3, 0F, 0F, 0F);

        pillar4 = new ModelRenderer(this, 0, 0);
        pillar4.addBox(-8.0F, -15.0F, 7.0F, 1, 10, 1);
        pillar4.setRotationPoint(15.0F, 24.0F, -15.0F);
        pillar4.setTextureSize(128, 128);
        pillar4.mirror = true;
        setRotation(pillar4, 0F, 0F, 0F);

    }

    public void render(float size) {

        base.render(size);
        top.render(size);
        support.render(size);
        support1.render(size);
        support2.render(size);
        support3.render(size);
        support4.render(size);
        blade4.render(size);
        blade3.render(size);
        blade2.render(size);
        blade1.render(size);
        pillar.render(size);
        pillar2.render(size);
        pillar3.render(size);
        pillar4.render(size);

        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        glass1.render(size);
        glass2.render(size);
        glass3.render(size);
        glass4.render(size);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
    }

    /*
    public void renderWithblade(double angle, float size) {
        setRotation(blade, 0F, 0F, getRotation(getAbsoluteAngle(angle)));
        render(size);
    }


    public float getRotation(double angle) {
        return ((float) angle / (float) 180) * (float) Math.PI;
    }

    public double getAbsoluteAngle(double angle) {
        return angle % 360;
    }
     */

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}