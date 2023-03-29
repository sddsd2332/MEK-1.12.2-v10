package mekanism.client.model;


import mekanism.client.render.MekanismRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelMekaSuitChest extends ModelBase {
    ModelRenderer chest_body_brace1;
    ModelRenderer chest_body_brace1_r1;
    ModelRenderer chest_body_brace2;
    ModelRenderer chest_body_brace2_r1;
    ModelRenderer chest_body_brace3;
    ModelRenderer chest_body_waist;
    ModelRenderer chest_body_led1;
    ModelRenderer chest_body_led1_r1;
    ModelRenderer chest_body_led2;
    ModelRenderer chest_body_led2_r1;
    ModelRenderer chest_body_plate1;
    ModelRenderer chest_body_plate1_r1;
    ModelRenderer chest_body_plate2;
    ModelRenderer chest_body_plate2_r1;
    ModelRenderer chest_body_plate3;
    ModelRenderer chest_body_plate3_r1;
    ModelRenderer chest_body_plate4;
    ModelRenderer chest_body_plate4_r1;
    ModelRenderer chest_body_plate5;
    ModelRenderer chest_body_plate5_r1;
    ModelRenderer chest_body_satchel1;
    ModelRenderer chest_body_satchel1_r1;
    ModelRenderer chest_body_satchel2;
    ModelRenderer chest_body_satchel2_r1;
    ModelRenderer chest_body_satchel3;
    ModelRenderer chest_body_satchel3_r1;
    ModelRenderer chest_body_lower_plate1;
    ModelRenderer chest_body_lower_plate1_r1;
    ModelRenderer chest_body_lower_plate2;
    ModelRenderer chest_body_lower_plate2_r1;
    ModelRenderer chest_body_neck;
    ModelRenderer chest_body_shoulder_exo_brace_left;
    ModelRenderer chest_body_shoulder_exo_brace_left_r1;
    ModelRenderer chest_body_shoulder_exo_brace_right;
    ModelRenderer chest_body_shoulder_exo_brace_right_r1;
    ModelRenderer chest_body_back_exo_brace_spine;
    ModelRenderer chest_body_back_exo_brace_mid;
    ModelRenderer chest_body_back_exo_brace_mid_r1;
    ModelRenderer chest_body_back_exo_brace_upper;
    ModelRenderer chest_body_back_exo_brace_pack;
    ModelRenderer shared_chest_leggings_body_brace1;
    ModelRenderer shared_chest_leggings_body_brace2;
    ModelRenderer chest_left_arm_led1;
    ModelRenderer chest_left_arm_upper_plate;
    ModelRenderer chest_left_arm_lower_plate;
    ModelRenderer chest_left_arm_middle_plate1;
    ModelRenderer chest_left_arm_middle_plate2;
    ModelRenderer chest_left_arm_brace1;
    ModelRenderer chest_left_arm_brace2;
    ModelRenderer chest_left_arm_finger1;
    ModelRenderer chest_left_arm_finger2;
    ModelRenderer chest_left_arm_finger3;
    ModelRenderer chest_left_arm_finger4;
    ModelRenderer chest_left_arm_finger5;
    ModelRenderer chest_left_arm_finger6;
    ModelRenderer chest_left_arm_exo_brace3;
    ModelRenderer chest_left_arm_exo_brace3_r1;
    ModelRenderer chest_left_arm_exo_brace2;
    ModelRenderer chest_left_arm_exo_brace2_r1;
    ModelRenderer chest_left_arm_exo_brace1;
    ModelRenderer chest_left_arm_exo_brace1_r1;
    ModelRenderer chest_right_arm_led1;
    ModelRenderer chest_right_arm_upper_plate;
    ModelRenderer chest_right_arm_lower_plate;
    ModelRenderer chest_right_arm_middle_plate1;
    ModelRenderer chest_right_arm_middle_plate2;
    ModelRenderer chest_right_arm_brace1;
    ModelRenderer chest_right_arm_brace2;
    ModelRenderer chest_right_arm_finger1;
    ModelRenderer chest_right_arm_finger2;
    ModelRenderer chest_right_arm_finger3;
    ModelRenderer chest_right_arm_finger4;
    ModelRenderer chest_right_arm_finger5;
    ModelRenderer chest_right_arm_finger6;
    ModelRenderer chest_right_arm_exo_brace3;
    ModelRenderer chest_right_arm_exo_brace3_r1;
    ModelRenderer chest_right_arm_exo_brace2;
    ModelRenderer chest_right_arm_exo_brace2_r1;
    ModelRenderer chest_right_arm_exo_brace1;
    ModelRenderer chest_right_arm_exo_brace1_r1;
    ModelRenderer chest_body;
    ModelRenderer chest_left_arm;
    ModelRenderer chest_right_arm;

    public ModelMekaSuitChest() {
        textureWidth = 128;
        textureHeight = 128;

        chest_body_brace1 = new ModelRenderer(this);
        chest_body_brace1.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_brace1.setTextureSize(128, 128);
        chest_body_brace1.mirror = true;

        chest_body_brace1_r1 = new ModelRenderer(this);
        chest_body_brace1_r1.setRotationPoint(1.0F, -24.694F, -0.0018F);
        chest_body_brace1.addChild(chest_body_brace1_r1);
        setRotationAngle(chest_body_brace1_r1, -0.1745F, 0.0F, 0.0F);
        chest_body_brace1_r1.setTextureOffset(23, 11).addBox(-5.5F, 2.994F, -2.5982F, 9, 1, 5);
        chest_body_brace1_r1.setTextureSize(128, 128);
        chest_body_brace1_r1.mirror = true;

        chest_body_brace2 = new ModelRenderer(this);
        chest_body_brace2.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_brace2.setTextureSize(128, 128);
        chest_body_brace2.mirror = true;


        chest_body_brace2_r1 = new ModelRenderer(this);
        chest_body_brace2_r1.setRotationPoint(1.0F, -22.7452F, -1.6053F);
        chest_body_brace2.addChild(chest_body_brace2_r1);
        setRotationAngle(chest_body_brace2_r1, 0.2618F, 0.0F, 0.0F);
        chest_body_brace2_r1.setTextureOffset(0, 23).addBox(-5.5F, 3.0952F, -2.5F, 9, 1, 5);
        chest_body_brace2_r1.setTextureSize(128, 128);
        chest_body_brace2_r1.mirror = true;

        chest_body_brace3 = new ModelRenderer(this);
        chest_body_brace3.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_brace3.setTextureOffset(24, 8).addBox(-4.5F, -21.4F, 1.25F, 9, 2, 1);
        chest_body_brace3.setTextureSize(128, 128);
        chest_body_brace3.mirror = true;

        chest_body_waist = new ModelRenderer(this);
        chest_body_waist.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_waist.setTextureOffset(0, 16).addBox(-4.5F, -16.5F, -2.5F, 9, 2, 5);
        chest_body_waist.setTextureSize(128, 128);
        chest_body_waist.mirror = true;

        chest_body_led1 = new ModelRenderer(this);
        chest_body_led1.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_led1.setTextureSize(128, 128);
        chest_body_led1.mirror = true;


        chest_body_led1_r1 = new ModelRenderer(this);
        chest_body_led1_r1.setRotationPoint(1.0F, -20.6F, -3.6F);
        chest_body_led1.addChild(chest_body_led1_r1);
        setRotationAngle(chest_body_led1_r1, -0.0873F, 0.0F, 0.0F);
        chest_body_led1_r1.setTextureOffset(0, 29).addBox(-0.5F, -0.5F, -0.47F, 1, 1, 1);
        chest_body_led1_r1.setTextureSize(128, 128);
        chest_body_led1_r1.mirror = true;


        chest_body_led2 = new ModelRenderer(this);
        chest_body_led2.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_led2.setTextureSize(128, 128);
        chest_body_led2.mirror = true;


        chest_body_led2_r1 = new ModelRenderer(this);
        chest_body_led2_r1.setRotationPoint(-1.0F, -20.6F, -3.6F);
        chest_body_led2.addChild(chest_body_led2_r1);
        setRotationAngle(chest_body_led2_r1, -0.0873F, 0.0F, 0.0F);
        chest_body_led2_r1.setTextureOffset(24, 13).addBox(-0.5F, -0.5F, -0.47F, 1, 1, 1);
        chest_body_led2_r1.setTextureSize(128, 128);
        chest_body_led2_r1.mirror = true;


        chest_body_plate1 = new ModelRenderer(this);
        chest_body_plate1.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_plate1.setTextureSize(128, 128);
        chest_body_plate1.mirror = true;


        chest_body_plate1_r1 = new ModelRenderer(this);
        chest_body_plate1_r1.setRotationPoint(-1.0F, -21.0F, -3.0F);
        chest_body_plate1.addChild(chest_body_plate1_r1);
        setRotationAngle(chest_body_plate1_r1, 0.0873F, 0.0F, 0.0F);
        chest_body_plate1_r1.setTextureOffset(55, 11).addBox(-1.5F, -1.2F, -1.0F, 5, 3, 2);
        chest_body_plate1_r1.setTextureSize(128, 128);
        chest_body_plate1_r1.mirror = true;


        chest_body_plate2 = new ModelRenderer(this);
        chest_body_plate2.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_plate2.setTextureSize(128, 128);
        chest_body_plate2.mirror = true;


        chest_body_plate2_r1 = new ModelRenderer(this);
        chest_body_plate2_r1.setRotationPoint(0.0F, -22.0F, -2.0F);
        chest_body_plate2.addChild(chest_body_plate2_r1);
        setRotationAngle(chest_body_plate2_r1, -0.1745F, 0.0F, 0.0F);
        chest_body_plate2_r1.setTextureOffset(50, 0).addBox(-3.5F, -1.0F, -1.0F, 7, 3, 1);
        chest_body_plate2_r1.setTextureSize(128, 128);
        chest_body_plate2_r1.mirror = true;


        chest_body_plate3 = new ModelRenderer(this);
        chest_body_plate3.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_plate3.setTextureSize(128, 128);
        chest_body_plate3.mirror = true;


        chest_body_plate3_r1 = new ModelRenderer(this);
        chest_body_plate3_r1.setRotationPoint(0.0F, -20.3777F, -2.3473F);
        chest_body_plate3.addChild(chest_body_plate3_r1);
        setRotationAngle(chest_body_plate3_r1, 0.1745F, 0.0F, 0.0F);
        chest_body_plate3_r1.setTextureOffset(49, 27).addBox(-3.5F, 0.0F, -1.0F, 7, 2, 1);
        chest_body_plate3_r1.setTextureSize(128, 128);
        chest_body_plate3_r1.mirror = true;


        chest_body_plate4 = new ModelRenderer(this);
        chest_body_plate4.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_plate4.setTextureSize(128, 128);
        chest_body_plate4.mirror = true;


        chest_body_plate4_r1 = new ModelRenderer(this);
        chest_body_plate4_r1.setRotationPoint(-2.0F, -19.0F, -2.0F);
        chest_body_plate4.addChild(chest_body_plate4_r1);
        setRotationAngle(chest_body_plate4_r1, 0.1745F, 0.0F, 0.0F);
        chest_body_plate4_r1.setTextureOffset(59, 37).addBox(-0.15F, -0.5F, -1.5F, 2, 3, 1);
        chest_body_plate4_r1.setTextureSize(128, 128);
        chest_body_plate4_r1.mirror = true;


        chest_body_plate5 = new ModelRenderer(this);
        chest_body_plate5.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_plate5.setTextureSize(128, 128);
        chest_body_plate5.mirror = true;


        chest_body_plate5_r1 = new ModelRenderer(this);
        chest_body_plate5_r1.setRotationPoint(-2.0F, -19.0F, -2.0F);
        chest_body_plate5.addChild(chest_body_plate5_r1);
        setRotationAngle(chest_body_plate5_r1, 0.1745F, 0.0F, 0.0F);
        chest_body_plate5_r1.setTextureOffset(20, 59).addBox(2.15F, -0.5F, -1.5F, 2, 3, 1);
        chest_body_plate5_r1.setTextureSize(128, 128);
        chest_body_plate5_r1.mirror = true;


        chest_body_satchel1 = new ModelRenderer(this);
        chest_body_satchel1.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_satchel1.setTextureSize(128, 128);
        chest_body_satchel1.mirror = true;


        chest_body_satchel1_r1 = new ModelRenderer(this);
        chest_body_satchel1_r1.setRotationPoint(-2.0F, -16.0F, -4.0F);
        chest_body_satchel1.addChild(chest_body_satchel1_r1);
        setRotationAngle(chest_body_satchel1_r1, 0.0873F, 0.0F, 0.0F);
        chest_body_satchel1_r1.setTextureOffset(55, 4).addBox(1.0F, -1.0F, 0.5F, 2, 3, 1);
        chest_body_satchel1_r1.setTextureSize(128, 128);
        chest_body_satchel1_r1.mirror = true;


        chest_body_satchel2 = new ModelRenderer(this);
        chest_body_satchel2.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_satchel2.setTextureSize(128, 128);
        chest_body_satchel2.mirror = true;


        chest_body_satchel2_r1 = new ModelRenderer(this);
        chest_body_satchel2_r1.setRotationPoint(0.5377F, -16.0F, -4.042F);
        chest_body_satchel2.addChild(chest_body_satchel2_r1);
        setRotationAngle(chest_body_satchel2_r1, 0.0873F, -0.0873F, 0.0F);
        chest_body_satchel2_r1.setTextureOffset(48, 21).addBox(0.9623F, -1.0F, 0.5F, 2, 3, 1);
        chest_body_satchel2_r1.setTextureSize(128, 128);
        chest_body_satchel2_r1.mirror = true;


        chest_body_satchel3 = new ModelRenderer(this);
        chest_body_satchel3.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_satchel3.setTextureSize(128, 128);
        chest_body_satchel3.mirror = true;


        chest_body_satchel3_r1 = new ModelRenderer(this);
        chest_body_satchel3_r1.setRotationPoint(-1.9414F, -15.5891F, -2.8755F);
        chest_body_satchel3.addChild(chest_body_satchel3_r1);
        setRotationAngle(chest_body_satchel3_r1, 0.0873F, 0.0873F, 0.0F);
        chest_body_satchel3_r1.setTextureOffset(46, 11).addBox(-1.5586F, -1.5F, -0.5F, 2, 3, 1);
        chest_body_satchel3_r1.setTextureSize(128, 128);
        chest_body_satchel3_r1.mirror = true;


        chest_body_lower_plate1 = new ModelRenderer(this);
        chest_body_lower_plate1.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_lower_plate1.setTextureSize(128, 128);
        chest_body_lower_plate1.mirror = true;


        chest_body_lower_plate1_r1 = new ModelRenderer(this);
        chest_body_lower_plate1_r1.setRotationPoint(-2.0F, -17.0F, -4.0F);
        chest_body_lower_plate1.addChild(chest_body_lower_plate1_r1);
        setRotationAngle(chest_body_lower_plate1_r1, 0.1745F, 0.0F, 0.0F);
        chest_body_lower_plate1_r1.setTextureOffset(12, 29).addBox(1.0F, 4.0F, 0.5F, 2, 2, 1);
        chest_body_lower_plate1_r1.setTextureSize(128, 128);
        chest_body_lower_plate1_r1.mirror = true;


        chest_body_lower_plate2 = new ModelRenderer(this);
        chest_body_lower_plate2.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body_lower_plate2.setTextureSize(128, 128);
        chest_body_lower_plate2.mirror = true;


        chest_body_lower_plate2_r1 = new ModelRenderer(this);
        chest_body_lower_plate2_r1.setRotationPoint(-1.0F, -15.0F, -2.0F);
        chest_body_lower_plate2.addChild(chest_body_lower_plate2_r1);
        setRotationAngle(chest_body_lower_plate2_r1, -0.1745F, 0.0F, 0.0F);
        chest_body_lower_plate2_r1.setTextureOffset(54, 30).addBox(-1.5F, -1.0F, -1.0F, 5, 3, 1);
        chest_body_lower_plate2_r1.setTextureSize(128, 128);
        chest_body_lower_plate2_r1.mirror = true;


        chest_body_neck = new ModelRenderer(this);
        chest_body_neck.setRotationPoint(-2.0F, 5.0F, -2.0F);
        chest_body_neck.setTextureOffset(39, 44).addBox(0.0F, -5.55F, 0.0F, 4, 2, 4);
        chest_body_neck.setTextureSize(128, 128);
        chest_body_neck.mirror = true;


        chest_body_shoulder_exo_brace_left = new ModelRenderer(this);
        chest_body_shoulder_exo_brace_left.setRotationPoint(0.0F, 0.588F, 2.699F);
        chest_body_shoulder_exo_brace_left.setTextureSize(128, 128);
        chest_body_shoulder_exo_brace_left.mirror = true;


        chest_body_shoulder_exo_brace_left_r1 = new ModelRenderer(this);
        chest_body_shoulder_exo_brace_left_r1.setRotationPoint(4.0541F, 0.0F, 0.0F);
        chest_body_shoulder_exo_brace_left.addChild(chest_body_shoulder_exo_brace_left_r1);
        setRotationAngle(chest_body_shoulder_exo_brace_left_r1, 0.0F, 0.2618F, -0.0873F);
        chest_body_shoulder_exo_brace_left_r1.setTextureOffset(46, 34).addBox(-4.0F, -1.0F, -0.5F, 8, 2, 1);
        chest_body_shoulder_exo_brace_left_r1.setTextureSize(128, 128);
        chest_body_shoulder_exo_brace_left_r1.mirror = true;


        chest_body_shoulder_exo_brace_right = new ModelRenderer(this);
        chest_body_shoulder_exo_brace_right.setRotationPoint(0.0F, 0.588F, 2.699F);
        chest_body_shoulder_exo_brace_right.setTextureSize(128, 128);
        chest_body_shoulder_exo_brace_right.mirror = true;


        chest_body_shoulder_exo_brace_right_r1 = new ModelRenderer(this);
        chest_body_shoulder_exo_brace_right_r1.setRotationPoint(-4.076F, 0.0F, 0.0F);
        chest_body_shoulder_exo_brace_right.addChild(chest_body_shoulder_exo_brace_right_r1);
        setRotationAngle(chest_body_shoulder_exo_brace_right_r1, 0.0F, -0.2618F, 0.0873F);
        chest_body_shoulder_exo_brace_right_r1.setTextureOffset(44, 8).addBox(-4.0F, -1.0F, -0.5F, 8, 2, 1);
        chest_body_shoulder_exo_brace_right_r1.setTextureSize(128, 128);
        chest_body_shoulder_exo_brace_right_r1.mirror = true;


        chest_body_back_exo_brace_spine = new ModelRenderer(this);
        chest_body_back_exo_brace_spine.setRotationPoint(0.0F, 5.8299F, 2.683F);
        chest_body_back_exo_brace_spine.setTextureOffset(39, 50).addBox(-1.0F, -6.3299F, -1.683F, 2, 11, 2);
        chest_body_back_exo_brace_spine.setTextureSize(128, 128);
        chest_body_back_exo_brace_spine.mirror = true;


        chest_body_back_exo_brace_mid = new ModelRenderer(this);
        chest_body_back_exo_brace_mid.setRotationPoint(0.0F, 5.8299F, 2.683F);
        chest_body_back_exo_brace_mid.setTextureSize(128, 128);
        chest_body_back_exo_brace_mid.mirror = true;


        chest_body_back_exo_brace_mid_r1 = new ModelRenderer(this);
        chest_body_back_exo_brace_mid_r1.setRotationPoint(0.0F, -0.8782F, -0.1155F);
        chest_body_back_exo_brace_mid.addChild(chest_body_back_exo_brace_mid_r1);
        setRotationAngle(chest_body_back_exo_brace_mid_r1, -0.0873F, 0.0F, 0.0F);
        chest_body_back_exo_brace_mid_r1.setTextureOffset(25, 44).addBox(-2.5F, -3.5F, -1.0F, 5, 7, 2);
        chest_body_back_exo_brace_mid_r1.setTextureSize(128, 128);
        chest_body_back_exo_brace_mid_r1.mirror = true;


        chest_body_back_exo_brace_upper = new ModelRenderer(this);
        chest_body_back_exo_brace_upper.setRotationPoint(0.0F, 5.8299F, 2.683F);
        chest_body_back_exo_brace_upper.setTextureOffset(28, 17).addBox(-3.5F, -5.3249F, -1.1792F, 7, 5, 3);
        chest_body_back_exo_brace_upper.setTextureSize(128, 128);
        chest_body_back_exo_brace_upper.mirror = true;


        chest_body_back_exo_brace_pack = new ModelRenderer(this);
        chest_body_back_exo_brace_pack.setRotationPoint(0.0F, 5.8299F, 2.683F);
        chest_body_back_exo_brace_pack.setTextureOffset(12, 49).addBox(-2.0F, -9.3299F, 0.817F, 4, 7, 2);
        chest_body_back_exo_brace_pack.setTextureSize(128, 128);
        chest_body_back_exo_brace_pack.mirror = true;


        shared_chest_leggings_body_brace1 = new ModelRenderer(this);
        shared_chest_leggings_body_brace1.setRotationPoint(0.0F, 11.5228F, 2.5229F);
        shared_chest_leggings_body_brace1.setTextureOffset(20, 0).addBox(-5.0F, -2.0228F, -2.0191F, 10, 2, 2);
        shared_chest_leggings_body_brace1.setTextureSize(128, 128);
        shared_chest_leggings_body_brace1.mirror = true;


        shared_chest_leggings_body_brace2 = new ModelRenderer(this);
        shared_chest_leggings_body_brace2.setRotationPoint(0.0F, 11.5228F, 2.5229F);
        shared_chest_leggings_body_brace2.setTextureOffset(24, 4).addBox(-2.0F, -1.0F, -1.0F, 4, 2, 2);
        shared_chest_leggings_body_brace2.setTextureSize(128, 128);
        shared_chest_leggings_body_brace2.mirror = true;

        chest_left_arm_led1 = new ModelRenderer(this);
        chest_left_arm_led1.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_led1.setTextureOffset(24, 11).addBox(1.05F, 3.0F, -2.05F, 1, 1, 1);
        chest_left_arm_led1.setTextureSize(128, 128);
        chest_left_arm_led1.mirror = true;


        chest_left_arm_upper_plate = new ModelRenderer(this);
        chest_left_arm_upper_plate.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_upper_plate.setTextureOffset(39, 0).addBox(-0.3454F, -2.3145F, -2.5F, 3, 3, 5);
        chest_left_arm_upper_plate.setTextureSize(128, 128);
        chest_left_arm_upper_plate.mirror = true;

        chest_left_arm_lower_plate = new ModelRenderer(this);
        chest_left_arm_lower_plate.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_lower_plate.setTextureOffset(0, 54).addBox(0.5F, 5.5F, -2.5F, 2, 4, 4);
        chest_left_arm_lower_plate.setTextureSize(128, 128);
        chest_left_arm_lower_plate.mirror = true;

        chest_left_arm_middle_plate1 = new ModelRenderer(this);
        chest_left_arm_middle_plate1.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_middle_plate1.setTextureOffset(40, 25).addBox(-2.5F, 1.5F, -2.505F, 2, 4, 5);
        chest_left_arm_middle_plate1.setTextureSize(128, 128);
        chest_left_arm_middle_plate1.mirror = true;

        chest_left_arm_middle_plate2 = new ModelRenderer(this);
        chest_left_arm_middle_plate2.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_middle_plate2.setTextureOffset(48, 12).addBox(-0.5F, 1.5F, -2.75F, 1, 4, 5);
        chest_left_arm_middle_plate2.setTextureSize(128, 128);
        chest_left_arm_middle_plate2.mirror = true;

        chest_left_arm_brace1 = new ModelRenderer(this);
        chest_left_arm_brace1.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_brace1.setTextureOffset(19, 53).addBox(0.5F, 2.25F, -2.5F, 2, 1, 5);
        chest_left_arm_brace1.setTextureSize(128, 128);
        chest_left_arm_brace1.mirror = true;

        chest_left_arm_brace2 = new ModelRenderer(this);
        chest_left_arm_brace2.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_brace2.setTextureOffset(47, 51).addBox(0.5F, 3.75F, -2.5F, 2, 1, 5);
        chest_left_arm_brace2.setTextureSize(128, 128);
        chest_left_arm_brace2.mirror = true;

        chest_left_arm_finger1 = new ModelRenderer(this);
        chest_left_arm_finger1.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_finger1.setTextureOffset(46, 39).addBox(-0.5F, 9.5F, -2.5F, 3, 1, 1);
        chest_left_arm_finger1.setTextureSize(128, 128);
        chest_left_arm_finger1.mirror = true;

        chest_left_arm_finger2 = new ModelRenderer(this);
        chest_left_arm_finger2.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_finger2.setTextureOffset(46, 37).addBox(-0.5F, 9.5F, -0.5F, 3, 1, 1);
        chest_left_arm_finger2.setTextureSize(128, 128);
        chest_left_arm_finger2.mirror = true;

        chest_left_arm_finger3 = new ModelRenderer(this);
        chest_left_arm_finger3.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_finger3.setTextureOffset(27, 42).addBox(-0.5F, 9.5F, -1.5F, 3, 1, 1);
        chest_left_arm_finger3.setTextureSize(128, 128);
        chest_left_arm_finger3.mirror = true;

        chest_left_arm_finger4 = new ModelRenderer(this);
        chest_left_arm_finger4.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_finger4.setTextureOffset(20, 41).addBox(-0.5F, 9.5F, 0.5F, 3, 1, 1);
        chest_left_arm_finger4.setTextureSize(128, 128);
        chest_left_arm_finger4.mirror = true;

        chest_left_arm_finger5 = new ModelRenderer(this);
        chest_left_arm_finger5.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_finger5.setTextureOffset(23, 23).addBox(-2.5F, 7.5F, -1.5F, 1, 3, 1)
                .setTextureSize(128, 128)
                .mirror = true;

        chest_left_arm_finger6 = new ModelRenderer(this);
        chest_left_arm_finger6.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_finger6.setTextureOffset(0, 23).addBox(-2.5F, 7.5F, -0.5F, 1, 3, 1)
                .setTextureSize(128, 128)
                .mirror = true;

        chest_left_arm_exo_brace3 = new ModelRenderer(this);
        chest_left_arm_exo_brace3.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_exo_brace3.setTextureSize(128, 128);
        chest_left_arm_exo_brace3.mirror = true;


        chest_left_arm_exo_brace3_r1 = new ModelRenderer(this);
        chest_left_arm_exo_brace3_r1.setRotationPoint(1.55F, 7.5669F, 1.8402F);
        chest_left_arm_exo_brace3.addChild(chest_left_arm_exo_brace3_r1);
        setRotationAngle(chest_left_arm_exo_brace3_r1, -0.2618F, 0.0F, 0.0F);
        chest_left_arm_exo_brace3_r1.setTextureOffset(58, 19).addBox(-1.0F, -2.5F, -1.0F, 2, 5, 2)
                .setTextureSize(128, 128)
                .mirror = true;

        chest_left_arm_exo_brace2 = new ModelRenderer(this);
        chest_left_arm_exo_brace2.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_exo_brace2.setTextureSize(128, 128)
                .mirror = true;


        chest_left_arm_exo_brace2_r1 = new ModelRenderer(this);
        chest_left_arm_exo_brace2_r1.setRotationPoint(1.5F, 4.6772F, 2.1713F);
        chest_left_arm_exo_brace2.addChild(chest_left_arm_exo_brace2_r1);
        setRotationAngle(chest_left_arm_exo_brace2_r1, 0.0873F, 0.0F, 0.0F);
        chest_left_arm_exo_brace2_r1.setTextureOffset(47, 57).addBox(-1.5F, -1.0F, -1.5F, 3, 2, 3)
                .setTextureSize(128, 128)
                .mirror = true;

        chest_left_arm_exo_brace1 = new ModelRenderer(this);
        chest_left_arm_exo_brace1.setRotationPoint(6.0F, 2.5F, 0.0F);
        chest_left_arm_exo_brace1.setTextureSize(128, 128);
        chest_left_arm_exo_brace1.mirror = true;


        chest_left_arm_exo_brace1_r1 = new ModelRenderer(this);
        chest_left_arm_exo_brace1_r1.setRotationPoint(2.418F, 0.7177F, 0.995F);
        chest_left_arm_exo_brace1.addChild(chest_left_arm_exo_brace1_r1);
        setRotationAngle(chest_left_arm_exo_brace1_r1, 0.0F, 0.0F, -0.0873F);
        chest_left_arm_exo_brace1_r1.setTextureOffset(30, 56).addBox(-0.5F, -3.0F, -1.5F, 1, 6, 3);
        chest_left_arm_exo_brace1_r1.setTextureSize(128, 128);
        chest_left_arm_exo_brace1_r1.mirror = true;

        chest_right_arm_led1 = new ModelRenderer(this);
        chest_right_arm_led1.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_led1.setTextureOffset(23, 19).addBox(-2.05F, 3.0F, -2.05F, 1, 1, 1);
        chest_right_arm_led1.setTextureSize(128, 128);
        chest_right_arm_led1.mirror = true;

        chest_right_arm_upper_plate = new ModelRenderer(this);
        chest_right_arm_upper_plate.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_upper_plate.setTextureOffset(35, 36).addBox(-2.7449F, -2.308F, -2.5F, 3, 3, 5);
        chest_right_arm_upper_plate.setTextureSize(128, 128);
        chest_right_arm_upper_plate.mirror = true;

        chest_right_arm_lower_plate = new ModelRenderer(this);
        chest_right_arm_lower_plate.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_lower_plate.setTextureOffset(51, 37).addBox(-2.5F, 5.5F, -2.5F, 2, 4, 4);
        chest_right_arm_lower_plate.setTextureSize(128, 128);
        chest_right_arm_lower_plate.mirror = true;

        chest_right_arm_middle_plate1 = new ModelRenderer(this);
        chest_right_arm_middle_plate1.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_middle_plate1.setTextureOffset(11, 40).addBox(0.5F, 1.5F, -2.505F, 2, 4, 5);
        chest_right_arm_middle_plate1.setTextureSize(128, 128);
        chest_right_arm_middle_plate1.mirror = true;

        chest_right_arm_middle_plate2 = new ModelRenderer(this);
        chest_right_arm_middle_plate2.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_middle_plate2.setTextureOffset(0, 45).addBox(-0.5F, 1.5F, -2.75F, 1, 4, 5);
        chest_right_arm_middle_plate2.setTextureSize(128, 128);
        chest_right_arm_middle_plate2.mirror = true;

        chest_right_arm_brace1 = new ModelRenderer(this);
        chest_right_arm_brace1.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_brace1.setTextureOffset(50, 45).addBox(-2.5F, 2.25F, -2.5F, 2, 1, 5);
        chest_right_arm_brace1.setTextureSize(128, 128);
        chest_right_arm_brace1.mirror = true;

        chest_right_arm_brace2 = new ModelRenderer(this);
        chest_right_arm_brace2.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_brace2.setTextureOffset(49, 21).addBox(-2.5F, 3.75F, -2.5F, 2, 1, 5);
        chest_right_arm_brace2.setTextureSize(128, 128);
        chest_right_arm_brace2.mirror = true;

        chest_right_arm_finger1 = new ModelRenderer(this);
        chest_right_arm_finger1.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_finger1.setTextureOffset(16, 38).addBox(-2.5F, 9.5F, -2.5F, 3, 1, 1);
        chest_right_arm_finger1.setTextureSize(128, 128);
        chest_right_arm_finger1.mirror = true;

        chest_right_arm_finger2 = new ModelRenderer(this);
        chest_right_arm_finger2.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_finger2.setTextureOffset(36, 27).addBox(-2.5F, 9.5F, -0.5F, 3, 1, 1);
        chest_right_arm_finger2.setTextureSize(128, 128);
        chest_right_arm_finger2.mirror = true;

        chest_right_arm_finger3 = new ModelRenderer(this);
        chest_right_arm_finger3.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_finger3.setTextureOffset(36, 25).addBox(-2.5F, 9.5F, -1.5F, 3, 1, 1);
        chest_right_arm_finger3.setTextureSize(128, 128);
        chest_right_arm_finger3.mirror = true;

        chest_right_arm_finger4 = new ModelRenderer(this);
        chest_right_arm_finger4.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_finger4.setTextureOffset(23, 17).addBox(-2.5F, 9.5F, 0.5F, 3, 1, 1);
        chest_right_arm_finger4.setTextureSize(128, 128);
        chest_right_arm_finger4.mirror = true;

        chest_right_arm_finger5 = new ModelRenderer(this);
        chest_right_arm_finger5.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_finger5.setTextureOffset(0, 16).addBox(1.5F, 7.5F, -1.5F, 1, 3, 1);
        chest_right_arm_finger5.setTextureSize(128, 128);
        chest_right_arm_finger5.mirror = true;

        chest_right_arm_finger6 = new ModelRenderer(this);
        chest_right_arm_finger6.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_finger6.setTextureOffset(0, 0).addBox(1.5F, 7.5F, -0.5F, 1, 3, 1);
        chest_right_arm_finger6.setTextureSize(128, 128);
        chest_right_arm_finger6.mirror = true;

        chest_right_arm_exo_brace3 = new ModelRenderer(this);
        chest_right_arm_exo_brace3.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_exo_brace3.setTextureSize(128, 128);
        chest_right_arm_exo_brace3.mirror = true;


        chest_right_arm_exo_brace3_r1 = new ModelRenderer(this);
        chest_right_arm_exo_brace3_r1.setRotationPoint(-1.45F, 7.5669F, 1.8402F);
        chest_right_arm_exo_brace3.addChild(chest_right_arm_exo_brace3_r1);
        setRotationAngle(chest_right_arm_exo_brace3_r1, -0.2618F, 0.0F, 0.0F);
        chest_right_arm_exo_brace3_r1.setTextureOffset(12, 58).addBox(-1.0F, -2.5F, -1.0F, 2, 5, 2);
        chest_right_arm_exo_brace3_r1.setTextureSize(128, 128);
        chest_right_arm_exo_brace3_r1.mirror = true;

        chest_right_arm_exo_brace2 = new ModelRenderer(this);
        chest_right_arm_exo_brace2.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_exo_brace2.setTextureSize(128, 128);
        chest_right_arm_exo_brace2.mirror = true;


        chest_right_arm_exo_brace2_r1 = new ModelRenderer(this);
        chest_right_arm_exo_brace2_r1.setRotationPoint(-1.5F, 4.6772F, 2.1713F);
        chest_right_arm_exo_brace2.addChild(chest_right_arm_exo_brace2_r1);
        setRotationAngle(chest_right_arm_exo_brace2_r1, 0.0873F, 0.0F, 0.0F);
        chest_right_arm_exo_brace2_r1.setTextureOffset(56, 51).addBox(-1.5F, -1.0F, -1.5F, 3, 2, 3);
        chest_right_arm_exo_brace2_r1.setTextureSize(128, 128);
        chest_right_arm_exo_brace2_r1.mirror = true;

        chest_right_arm_exo_brace1 = new ModelRenderer(this);
        chest_right_arm_exo_brace1.setRotationPoint(-6.0F, 2.5F, 0.0F);
        chest_right_arm_exo_brace1.setTextureSize(128, 128);
        chest_right_arm_exo_brace1.mirror = true;


        chest_right_arm_exo_brace1_r1 = new ModelRenderer(this);
        chest_right_arm_exo_brace1_r1.setRotationPoint(-2.582F, 0.7177F, 0.995F);
        chest_right_arm_exo_brace1.addChild(chest_right_arm_exo_brace1_r1);
        setRotationAngle(chest_right_arm_exo_brace1_r1, 0.0F, 0.0F, 0.0873F);
        chest_right_arm_exo_brace1_r1.setTextureOffset(16, 29).addBox(-0.426F, -3.0F, -1.5F, 1, 6, 3);
        chest_right_arm_exo_brace1_r1.setTextureSize(128, 128);
        chest_right_arm_exo_brace1_r1.mirror = true;

        chest_body = new ModelRenderer(this);
        chest_body.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_body.setTextureOffset(0, 0).addBox(-4.0F, -23.5F, -2.005F, 8, 12, 4);
        chest_body.setTextureSize(128, 128);
        chest_body.mirror = true;

        chest_left_arm = new ModelRenderer(this);
        chest_left_arm.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_left_arm.setTextureOffset(0, 29).addBox(4.0F, -23.5F, -2.0F, 4, 12, 4);
        chest_left_arm.setTextureSize(128, 128);
        chest_left_arm.mirror = true;

        chest_right_arm = new ModelRenderer(this);
        chest_right_arm.setRotationPoint(0.0F, 24.0F, 0.0F);
        chest_right_arm.setTextureOffset(24, 25).addBox(-8.0F, -23.5F, -2.0F, 4, 12, 4);
        chest_right_arm.setTextureSize(128, 128);
        chest_right_arm.mirror = true;

    }

    public void render(float size) {
        chest_body_brace1.render(size);
        chest_body_brace2.render(size);
        chest_body_brace3.render(size);
        chest_body_waist.render(size);
        chest_body_plate1.render(size);
        chest_body_plate2.render(size);
        chest_body_plate3.render(size);
        chest_body_plate4.render(size);
        chest_body_plate5.render(size);
        chest_body_satchel1.render(size);
        chest_body_satchel2.render(size);
        chest_body_satchel3.render(size);
        chest_body_lower_plate1.render(size);
        chest_body_lower_plate2.render(size);
        chest_body_neck.render(size);
        chest_body_shoulder_exo_brace_left.render(size);
        chest_body_shoulder_exo_brace_right.render(size);
        chest_body_back_exo_brace_spine.render(size);
        chest_body_back_exo_brace_mid.render(size);
        chest_body_back_exo_brace_upper.render(size);
        chest_body_back_exo_brace_pack.render(size);
        shared_chest_leggings_body_brace1.render(size);
        shared_chest_leggings_body_brace2.render(size);
        chest_left_arm_upper_plate.render(size);
        chest_left_arm_lower_plate.render(size);
        chest_left_arm_middle_plate1.render(size);
        chest_left_arm_middle_plate2.render(size);
        chest_left_arm_brace1.render(size);
        chest_left_arm_brace2.render(size);
        chest_left_arm_finger1.render(size);
        chest_left_arm_finger2.render(size);
        chest_left_arm_finger3.render(size);
        chest_left_arm_finger4.render(size);
        chest_left_arm_finger5.render(size);
        chest_left_arm_finger6.render(size);
        chest_left_arm_exo_brace3.render(size);
        chest_left_arm_exo_brace2.render(size);
        chest_left_arm_exo_brace1.render(size);
        chest_right_arm_upper_plate.render(size);
        chest_right_arm_lower_plate.render(size);
        chest_right_arm_middle_plate1.render(size);
        chest_right_arm_middle_plate2.render(size);
        chest_right_arm_brace1.render(size);
        chest_right_arm_brace2.render(size);
        chest_right_arm_finger1.render(size);
        chest_right_arm_finger2.render(size);
        chest_right_arm_finger3.render(size);
        chest_right_arm_finger4.render(size);
        chest_right_arm_finger5.render(size);
        chest_right_arm_finger6.render(size);
        chest_right_arm_exo_brace3.render(size);
        chest_right_arm_exo_brace2.render(size);
        chest_right_arm_exo_brace1.render(size);
        chest_body.render(size);
        chest_left_arm.render(size);
        chest_right_arm.render(size);

        MekanismRenderer.GlowInfo glowInfo = MekanismRenderer.enableGlow();
        chest_body_led1.render(size);
        chest_body_led2.render(size);
        chest_left_arm_led1.render(size);
        chest_right_arm_led1.render(size);
        MekanismRenderer.disableGlow(glowInfo);


    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}