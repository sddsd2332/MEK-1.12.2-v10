package mekanism.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelMekTool extends ModelBase {
    ModelRenderer bone;
    ModelRenderer bone_r1;
    ModelRenderer bone2;
    ModelRenderer bone2_r1;
    ModelRenderer bone3;
    ModelRenderer bone3_r1;
    ModelRenderer bone4;
    ModelRenderer bone4_r1;
    ModelRenderer bone5;
    ModelRenderer bone5_r1;
    ModelRenderer bone6;
    ModelRenderer cube_r1;
    ModelRenderer cube_r1_r1;
    ModelRenderer bone7;
    ModelRenderer cube_r2;
    ModelRenderer cube_r2_r1;
    ModelRenderer bone8;
    ModelRenderer cube_r3;
    ModelRenderer cube_r3_r1;
    ModelRenderer bone9;
    ModelRenderer cube_r4;
    ModelRenderer cube_r4_r1;
    ModelRenderer bone10;
    ModelRenderer bone10_r1;
    ModelRenderer bone11;
    ModelRenderer bone11_r1;
    ModelRenderer bone12;
    ModelRenderer cube_r5;
    ModelRenderer cube_r5_r1;
    ModelRenderer bone13;
    ModelRenderer cube_r6;
    ModelRenderer cube_r6_r1;
    ModelRenderer bone14;
    ModelRenderer cube_r7;
    ModelRenderer cube_r7_r1;
    ModelRenderer bone15;
    ModelRenderer cube_r8;
    ModelRenderer cube_r8_r1;
    ModelRenderer bone16;
    ModelRenderer bone16_r1;
    ModelRenderer bone17;
    ModelRenderer bone17_r1;
    ModelRenderer bone18;
    ModelRenderer bone18_r1;

    public ModelMekTool() {
        textureWidth = 64;
        textureHeight = 64;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.setTextureSize(64, 64);
        bone.mirror = true;

        bone_r1 = new ModelRenderer(this, 0, 17);
        bone_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone.addChild(bone_r1);
        setRotationAngle(bone_r1, -3.1416F, 0.0F, 3.1416F);
        bone_r1.addBox(-2.0F, -6.0F, 3.0F, 3, 5, 4);
        bone_r1.setTextureSize(64, 64);
        bone_r1.mirror = true;

        bone2 = new ModelRenderer(this);
        bone2.setRotationPoint(1.0F, 23.0F, 1.0F);
        bone2.setTextureSize(64, 64);
        bone2.mirror = true;


        bone2_r1 = new ModelRenderer(this, 18, 7);
        bone2_r1.setRotationPoint(-1.0F, 1.0F, -1.0F);
        bone2.addChild(bone2_r1);
        setRotationAngle(bone2_r1, -3.1416F, 0.0F, 3.1416F);
        bone2_r1.addBox(-1.0F, -4.0F, 4.0F, 3, 2, 4);
        bone2_r1.setTextureSize(64, 64);
        bone2_r1.mirror = true;

        bone3 = new ModelRenderer(this);
        bone3.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone3.setTextureSize(64, 64);
        bone3.mirror = true;


        bone3_r1 = new ModelRenderer(this, 0, 0);
        bone3_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone3.addChild(bone3_r1);
        setRotationAngle(bone3_r1, -3.1416F, 0.0F, 3.1416F);
        bone3_r1.addBox(0.0F, -7.0F, 2.0F, 2, 3, 1);
        bone3_r1.setTextureSize(64, 64);
        bone3_r1.mirror = true;

        bone4 = new ModelRenderer(this);
        bone4.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone4.setTextureSize(64, 64);
        bone4.mirror = true;


        bone4_r1 = new ModelRenderer(this, 0, 8);
        bone4_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone4.addChild(bone4_r1);
        setRotationAngle(bone4_r1, -3.1416F, 0.0F, 3.1416F);
        bone4_r1.addBox(0.0F, -7.0F, 3.0F, 2, 2, 1);
        bone4_r1.setTextureSize(64, 64);
        bone4_r1.mirror = true;

        bone5 = new ModelRenderer(this);
        bone5.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone5.setTextureSize(64, 64);
        bone5.mirror = true;


        bone5_r1 = new ModelRenderer(this, 10, 17);
        bone5_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone5.addChild(bone5_r1);
        setRotationAngle(bone5_r1, -3.1416F, 0.0F, 3.1416F);
        bone5_r1.addBox(0.0F, -6.0F, 4.0F, 2, 1, 2);
        bone5_r1.setTextureSize(64, 64);
        bone5_r1.mirror = true;

        bone6 = new ModelRenderer(this);
        bone6.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone6.setTextureSize(64, 64);
        bone6.mirror = true;


        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(0.0F, -1.0F, -1.0F);
        bone6.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.2443F, 0.0F, 0.0F);
        cube_r1.setTextureSize(64, 64);
        cube_r1.mirror = true;


        cube_r1_r1 = new ModelRenderer(this, 14, 17);
        cube_r1_r1.setRotationPoint(0.0F, 1.0F, 1.0F);
        cube_r1.addChild(cube_r1_r1);
        setRotationAngle(cube_r1_r1, -2.653F, 0.0F, -3.1416F);
        cube_r1_r1.addBox(-1.0F, -5.0F, 4.0F, 2, 2, 5);
        cube_r1_r1.setTextureSize(64, 64);
        cube_r1_r1.mirror = true;

        bone7 = new ModelRenderer(this);
        bone7.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone7.setTextureSize(64, 64);
        bone7.mirror = true;


        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(0.0F, -1.0F, -1.0F);
        bone7.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.6981F, 0.0F, 0.0F);
        cube_r2.setTextureSize(64, 64);
        cube_r2.mirror = true;


        cube_r2_r1 = new ModelRenderer(this, 12, 24);
        cube_r2_r1.setRotationPoint(0.0F, 1.0F, 1.0F);
        cube_r2.addChild(cube_r2_r1);
        setRotationAngle(cube_r2_r1, -1.7454F, 0.0F, -3.1416F);
        cube_r2_r1.addBox(-2.15F, -4.0F, 4.0F, 2, 4, 2);
        cube_r2_r1.setTextureSize(64, 64);
        cube_r2_r1.mirror = true;

        bone8 = new ModelRenderer(this);
        bone8.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone8.setTextureSize(64, 64);
        ;
        bone8.mirror = true;

        cube_r3 = new ModelRenderer(this);
        cube_r3.setRotationPoint(0.0F, -1.0F, -1.0F);
        bone8.addChild(cube_r3);
        setRotationAngle(cube_r3, 0.6981F, 0.0F, 0.0F);
        bone8.setTextureSize(64, 64);
        bone8.mirror = true;

        cube_r3_r1 = new ModelRenderer(this, 21, 0);
        cube_r3_r1.setRotationPoint(0.0F, 1.0F, 1.0F);
        cube_r3.addChild(cube_r3_r1);
        setRotationAngle(cube_r3_r1, -1.7454F, 0.0F, -3.1416F);
        cube_r3_r1.addBox(-3.15F, -6.0F, 3.5F, 4, 2, 3);
        cube_r3.setTextureSize(64, 64);
        cube_r3.mirror = true;

        bone9 = new ModelRenderer(this);
        bone9.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone9.setTextureSize(64, 64);
        bone9.mirror = true;


        cube_r4 = new ModelRenderer(this);
        cube_r4.setRotationPoint(0.0F, -1.0F, 0.0F);
        bone9.addChild(cube_r4);
        setRotationAngle(cube_r4, -0.2182F, 0.0F, 0.0F);
        cube_r4.setTextureSize(64, 64);
        cube_r4.mirror = true;


        cube_r4_r1 = new ModelRenderer(this, 13, 0);
        cube_r4_r1.setRotationPoint(0.0F, 1.0F, 0.0F);
        cube_r4.addChild(cube_r4_r1);
        setRotationAngle(cube_r4_r1, 2.7052F, 0.0F, 3.1416F);
        cube_r4_r1.addBox(-1.5F, -2.665F, -3.0F, 1, 1, 6);
        cube_r4_r1.setTextureSize(64, 64);
        cube_r4_r1.mirror = true;

        bone10 = new ModelRenderer(this);
        bone10.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone10.setTextureSize(64, 64);
        bone10.mirror = true;


        bone10_r1 = new ModelRenderer(this, 23, 15);
        bone10_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone10.addChild(bone10_r1);
        setRotationAngle(bone10_r1, -3.1416F, 0.0F, 3.1416F);
        bone10_r1.addBox(-3.0F, -6.0F, 1.0F, 4, 4, 2);
        bone10_r1.setTextureSize(64, 64);
        bone10_r1.mirror = true;

        bone11 = new ModelRenderer(this);
        bone11.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone11.setTextureSize(64, 64);
        bone11.mirror = true;


        bone11_r1 = new ModelRenderer(this, 0, 4);
        bone11_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone11.addChild(bone11_r1);
        setRotationAngle(bone11_r1, -3.1416F, 0.0F, 3.1416F);
        bone11_r1.addBox(-1.5F, -4.5F, -1.0F, 1, 1, 2, 0.1F);
        bone11_r1.setTextureSize(64, 64);
        bone11_r1.mirror = true;

        bone12 = new ModelRenderer(this);
        bone12.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone12.setTextureSize(64, 64);
        bone12.mirror = true;


        cube_r5 = new ModelRenderer(this);
        cube_r5.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone12.addChild(cube_r5);
        setRotationAngle(cube_r5, 0.096F, 0.0175F, 0.0F);
        bone12.setTextureSize(64, 64);
        bone12.mirror = true;


        cube_r5_r1 = new ModelRenderer(this, 9, 9);
        cube_r5_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        cube_r5.addChild(cube_r5_r1);
        setRotationAngle(cube_r5_r1, -2.9496F, 0.0F, -3.1416F);
        cube_r5_r1.addBox(0.0F, -5.85F, -5.4F, 1, 1, 7);
        cube_r5_r1.setTextureSize(64, 64);
        cube_r5_r1.mirror = true;

        bone13 = new ModelRenderer(this);
        bone13.setRotationPoint(-3.0F, 24.0F, 0.0F);
        bone13.setTextureSize(64, 64);
        bone13.mirror = true;


        cube_r6 = new ModelRenderer(this);
        cube_r6.setRotationPoint(3.0F, 0.0F, 0.0F);
        bone13.addChild(cube_r6);
        setRotationAngle(cube_r6, 0.096F, -0.0175F, 0.0F);
        bone13.setTextureSize(64, 64);
        bone13.mirror = true;


        cube_r6_r1 = new ModelRenderer(this, 0, 8);
        cube_r6_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        cube_r6.addChild(cube_r6_r1);
        setRotationAngle(cube_r6_r1, -2.9496F, 0.0F, -3.1416F);
        cube_r6_r1.addBox(-3.0F, -5.85F, -5.4F, 1, 1, 7);
        cube_r6_r1.setTextureSize(64, 64);
        cube_r6_r1.mirror = true;

        bone14 = new ModelRenderer(this);
        bone14.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone14.setTextureSize(64, 64);
        bone14.mirror = true;


        cube_r7 = new ModelRenderer(this);
        cube_r7.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone14.addChild(cube_r7);
        setRotationAngle(cube_r7, -0.1004F, 0.0F, 0.0F);
        cube_r7.setTextureSize(64, 64);
        cube_r7.mirror = true;


        cube_r7_r1 = new ModelRenderer(this, 0, 0);
        cube_r7_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
        cube_r7.addChild(cube_r7_r1);
        setRotationAngle(cube_r7_r1, 2.9408F, 0.0F, 3.1416F);
        cube_r7_r1.addBox(-2.5F, -3.11F, -6.0F, 3, 1, 7);
        cube_r7_r1.setTextureSize(64, 64);
        cube_r7_r1.mirror = true;

        bone15 = new ModelRenderer(this);
        bone15.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone15.setTextureSize(64, 64);
        bone15.mirror = true;


        cube_r8 = new ModelRenderer(this);
        cube_r8.setRotationPoint(0.0F, -1.0F, 0.0F);
        bone15.addChild(cube_r8);
        setRotationAngle(cube_r8, -0.2182F, 0.0F, 0.0F);
        cube_r8.setTextureSize(64, 64);
        cube_r8.mirror = true;


        cube_r8_r1 = new ModelRenderer(this, 12, 24);
        cube_r8_r1.setRotationPoint(0.0F, 1.0F, 0.0F);
        cube_r8.addChild(cube_r8_r1);
        setRotationAngle(cube_r8_r1, 2.7052F, 0.0F, 3.1416F);
        cube_r8_r1.addBox(-2.5F, -2.15F, -4.0F, 3, 2, 2);
        cube_r8_r1.setTextureSize(64, 64);
        cube_r8_r1.mirror = true;

        bone16 = new ModelRenderer(this);
        bone16.setRotationPoint(0.0F, 24.0F, -2.0F);
        bone16.setTextureSize(64, 64);
        bone16.mirror = true;


        bone16_r1 = new ModelRenderer(this, 0, 11);
        bone16_r1.setRotationPoint(0.0F, 0.0F, 2.0F);
        bone16.addChild(bone16_r1);
        setRotationAngle(bone16_r1, -3.1416F, 0.0F, 3.1416F);
        bone16_r1.addBox(-1.5F, -4.5F, -3.0F, 1, 1, 1, 0.1F);
        bone16_r1.setTextureSize(64, 64);
        bone16_r1.mirror = true;

        bone17 = new ModelRenderer(this);
        bone17.setRotationPoint(0.0F, 24.0F, -1.0F);
        bone17.setTextureSize(64, 64);
        bone17.mirror = true;


        bone17_r1 = new ModelRenderer(this, 9, 10);
        bone17_r1.setRotationPoint(0.0F, 0.0F, 1.0F);
        bone17.addChild(bone17_r1);
        setRotationAngle(bone17_r1, -3.1416F, 0.0F, 3.1416F);
        bone17_r1.addBox(-1.5F, -4.5F, -2.0F, 1, 1, 1);
        bone17_r1.setTextureSize(64, 64);
        bone17_r1.mirror = true;

        bone18 = new ModelRenderer(this);
        bone18.setRotationPoint(0.0F, 24.0F, -1.0F);
        bone18.setTextureSize(64, 64);
        bone18.mirror = true;


        bone18_r1 = new ModelRenderer(this, 9, 8);
        bone18_r1.setRotationPoint(0.0F, 0.0F, 1.0F);
        bone18.addChild(bone18_r1);
        setRotationAngle(bone18_r1, -3.1416F, 0.0F, 3.1416F);
        bone18_r1.addBox(-1.5F, -4.5F, -4.0F, 1, 1, 1);
        bone18_r1.setTextureSize(64, 64);
        bone18_r1.mirror = true;

    }


    public void render(float size) {
        bone.render(size);
        bone2.render(size);
        bone3.render(size);
        bone4.render(size);
        bone5.render(size);
        bone6.render(size);
        bone7.render(size);
        bone8.render(size);
        bone9.render(size);
        bone10.render(size);
        bone11.render(size);
        bone12.render(size);
        bone13.render(size);
        bone14.render(size);
        bone15.render(size);
        bone16.render(size);
        bone17.render(size);
        bone18.render(size);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
