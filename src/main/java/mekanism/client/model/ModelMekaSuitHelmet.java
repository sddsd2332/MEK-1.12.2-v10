package mekanism.client.model;



import mekanism.client.render.MekanismRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class ModelMekaSuitHelmet extends ModelBiped {
	ModelRenderer bone;
	ModelRenderer helmet_head_center1_r1;
	ModelRenderer bone2;
	ModelRenderer bone3;
	ModelRenderer helmet_head_center3_r1;
	ModelRenderer bone4;
	ModelRenderer bone5;
	ModelRenderer bone6;
	ModelRenderer bone7;
	ModelRenderer bone8;
	ModelRenderer bone9;
	ModelRenderer bone10;
	ModelRenderer bone11;
	ModelRenderer bone12;
	ModelRenderer helmet_head_chin2_r1;
	ModelRenderer bone13;
	ModelRenderer bone14;
	ModelRenderer bone15;
	ModelRenderer bone16;
	ModelRenderer bone17;
	ModelRenderer bone18;
	ModelRenderer bone19;
	ModelRenderer bone20;
	ModelRenderer bone21;
	ModelRenderer bone22;
	ModelRenderer bone23;
	ModelRenderer helmet_head_visor_lower_r1;
	ModelRenderer bone24;
	ModelRenderer helmet_head_visor_upper_r1;
	ModelRenderer bone25;
	ModelRenderer bone26;

	public ModelMekaSuitHelmet() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.setTextureSize(64, 64);
		bone.mirror = true;

		helmet_head_center1_r1 = new ModelRenderer(this,0,30);
		helmet_head_center1_r1.setRotationPoint(-3.0F, -23.4654F, -3.6669F);
		bone.addChild(helmet_head_center1_r1);
		setRotationAngle(helmet_head_center1_r1, 0.4363F, 0.0F, 0.0F);
		helmet_head_center1_r1.addBox(2.0F, -7.0F, 2.0F, 2, 1, 2);
		helmet_head_center1_r1.setTextureSize(64, 64);
		helmet_head_center1_r1.mirror = true;

		bone2 = new ModelRenderer(this,26,24);
		bone2.setRotationPoint(0.0F, 1.5F, -1.0F);
		bone2.addBox(-1.0F, -9.0F, -2.0F, 2, 1, 5);
		bone2.setTextureSize(64, 64);
		bone2.mirror = true;

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone3.setTextureSize(64, 64);
		bone3.mirror = true;


		helmet_head_center3_r1 = new ModelRenderer(this,11,24);
		helmet_head_center3_r1.setRotationPoint(-3.0F, -25.1924F, -1.1959F);
		bone3.addChild(helmet_head_center3_r1);
		setRotationAngle(helmet_head_center3_r1, -0.6109F, 0.0F, 0.0F);
		helmet_head_center3_r1.addBox(2.0F, -7.0F, -1.0F, 2, 1, 2);
		helmet_head_center3_r1.setTextureSize(64, 64);
		helmet_head_center3_r1.mirror = true;

		bone4 = new ModelRenderer(this,0,12);
		bone4.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone4.addBox(-1.0F, -30.5F, 3.0F, 2, 1, 1);
		bone4.setTextureSize(64, 64);
		bone4.mirror = true;

		bone5 = new ModelRenderer(this,19,14);
		bone5.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone5.addBox(1.0F, -31.0F, -3.5F, 1, 2, 7);
		bone5.setTextureSize(64, 64);
		bone5.mirror = true;

		bone6 = new ModelRenderer(this,19,5);
		bone6.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone6.addBox(-2.0F, -31.0F, -3.5F, 1, 2, 7);
		bone6.setTextureSize(64, 64);
		bone6.mirror = true;

		bone7 = new ModelRenderer(this,19,14);
		bone7.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone7.addBox(-2.0F, -26.5F, 2.5F, 1, 1, 1);
		bone7.setTextureSize(64, 64);
		bone7.mirror = true;

		bone8 = new ModelRenderer(this,19,5);
		bone8.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone8.addBox(1.0F, -26.5F, 2.5F, 1, 1, 1);
		bone8.setTextureSize(64, 64);
		bone8.mirror = true;

		bone9 = new ModelRenderer(this,21,29);
		bone9.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone9.addBox(3.0F, -27.5F, -1.0F, 1, 2, 3);
		bone9.setTextureSize(64, 64);
		bone9.mirror = true;

		bone10 = new ModelRenderer(this,13,29);
		bone10.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone10.addBox(-4.0F, -27.5F, -1.0F, 1, 2, 3);
		bone10.setTextureSize(64, 64);
		bone10.mirror = true;

		bone11 = new ModelRenderer(this,19,0);
		bone11.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone11.addBox(-3.5F, -24.5F, -5.0F, 7, 1, 4);
		bone11.setTextureSize(64, 64);
		bone11.mirror = true;

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone12.setTextureSize(64, 64);
		bone12.mirror = true;


		helmet_head_chin2_r1 = new ModelRenderer(this,0,20);
		helmet_head_chin2_r1.setRotationPoint(-1.0F, -23.8536F, 5.0104F);
		bone12.addChild(helmet_head_chin2_r1);
		setRotationAngle(helmet_head_chin2_r1, 0.7854F, 0.0F, 0.0F);
		helmet_head_chin2_r1.addBox(-2.5F, -5.0F, -4.5F, 7, 1, 3);
		helmet_head_chin2_r1.setTextureSize(64, 64);
		helmet_head_chin2_r1.mirror = true;

		bone13 = new ModelRenderer(this,0,0);
		bone13.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone13.addBox(-1.0F, -25.25F, -5.5F, 2, 2, 1);
		bone13.setTextureSize(64, 64);
		bone13.mirror = true;

		bone14 = new ModelRenderer(this,0,12);
		bone14.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone14.addBox(-3.0F, -30.5F, -4.0F, 6, 1, 7);
		bone14.setTextureSize(64, 64);
		bone14.mirror = true;

		bone15 = new ModelRenderer(this,0,5);
		bone15.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone15.addBox(1.0F, -30.5F, -5.0F, 2, 1, 1);
		bone15.setTextureSize(64, 64);
		bone15.mirror = true;

		bone16 = new ModelRenderer(this,0,3);
		bone16.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone16.addBox(-3.0F, -30.5F, -5.0F, 2, 1, 1);
		bone16.setTextureSize(64, 64);
		bone16.mirror = true;

		bone17 = new ModelRenderer(this,0,24);
		bone17.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone17.addBox(1.0F, -29.5F, -2.0F, 3, 1, 5);
		bone17.setTextureSize(64, 64);
		bone17.mirror = true;

		bone18 = new ModelRenderer(this,3,16);
		bone18.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone18.addBox(3.0F, -28.5F, 2.0F, 1, 2, 1);
		bone18.setTextureSize(64, 64);
		bone18.mirror = true;

		bone19 = new ModelRenderer(this,15,23);
		bone19.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone19.addBox(-4.0F, -29.5F, -2.0F, 3, 1, 5);
		bone19.setTextureSize(64, 64);
		bone19.mirror = true;

		bone20 = new ModelRenderer(this,0,14);
		bone20.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone20.addBox(-4.0F, -28.5F, 2.0F, 1, 2, 1);
		bone20.setTextureSize(64, 64);
		bone20.mirror = true;

		bone21 = new ModelRenderer(this,28,14);
		bone21.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone21.addBox(-3.0F, -29.5F, 3.0F, 6, 3, 1);
		bone21.setTextureSize(64, 64);
		bone21.mirror = true;

		bone22 = new ModelRenderer(this,0,0);
		bone22.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone22.addBox(-3.0F, -29.5F, -4.0F, 6, 5, 7);
		bone22.setTextureSize(64, 64);
		bone22.mirror = true;

		bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone23.setTextureSize(64, 64);
		bone23.mirror = true;


		helmet_head_visor_lower_r1 = new ModelRenderer(this,28,5);
		helmet_head_visor_lower_r1.setRotationPoint(-1.0F, -24.5F, -1.0F);
		bone23.addChild(helmet_head_visor_lower_r1);
		setRotationAngle(helmet_head_visor_lower_r1, 0.1745F, 0.0F, 0.0F);
		helmet_head_visor_lower_r1.addBox(-1.995F, -4.0F, -3.5F, 6, 4, 2);
		helmet_head_visor_lower_r1.setTextureSize(64, 64);
		helmet_head_visor_lower_r1.mirror = true;

		bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone24.setTextureSize(64, 64);
		bone24.mirror = true;


		helmet_head_visor_upper_r1 = new ModelRenderer(this,28,18);
		helmet_head_visor_upper_r1.setRotationPoint(-1.0F, -24.751F, -3.2677F);
		bone24.addChild(helmet_head_visor_upper_r1);
		setRotationAngle(helmet_head_visor_upper_r1, -0.4363F, 0.0F, 0.0F);
		helmet_head_visor_upper_r1.addBox(-1.975F, -4.0F, -3.0F, 6, 2, 1);
		helmet_head_visor_upper_r1.setTextureSize(64, 64);
		helmet_head_visor_upper_r1.mirror = true;

		bone25 = new ModelRenderer(this,19,2);
		bone25.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone25.addBox(3.005F, -28.5F, -0.005F, 1, 1, 1);
		bone25.setTextureSize(64, 64);
		bone25.mirror = true;

		bone26 = new ModelRenderer(this,19,0);
		bone26.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone26.addBox(-3.995F, -28.5F, -0.005F, 1, 1, 1);
		bone26.setTextureSize(64, 64);
		bone26.mirror = true;

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
		bone19.render(size);
		bone20.render(size);
		bone21.render(size);
		bone22.render(size);
		bone23.render(size);
		bone24.render(size);


		MekanismRenderer.GlowInfo glowInfo = MekanismRenderer.enableGlow();
		bone25.render(size);
		bone26.render(size);
		MekanismRenderer.disableGlow(glowInfo);


	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}