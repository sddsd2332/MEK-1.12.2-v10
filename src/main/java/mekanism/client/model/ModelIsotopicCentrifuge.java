package mekanism.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelIsotopicCentrifuge extends ModelBase {

    ModelRenderer Base;
    ModelRenderer decoration;
    ModelRenderer decoration1;
    ModelRenderer decoration2;
    ModelRenderer decoration3;
    ModelRenderer Partition;
    ModelRenderer Partition2;
    ModelRenderer Partition3;
    ModelRenderer Partition4;
    ModelRenderer Connect;
    ModelRenderer Entry;
    ModelRenderer decoration4;
    ModelRenderer decoration5;
    ModelRenderer decoration6;
    ModelRenderer decoration7;
    ModelRenderer inside;
    ModelRenderer inside2;
    ModelRenderer inside3;
    ModelRenderer inside4;
    ModelRenderer Subject;

    public ModelIsotopicCentrifuge() {
        textureWidth = 128;
        textureHeight = 128;

        Base = new ModelRenderer(this,0,0);
        Base.addBox(-8.0F, -2.0F, -8.0F, 16, 2, 16);
        Base.setRotationPoint(0.0F, 24.0F, 0.0F);
        Base.setTextureSize(128,128);
        Base.mirror = true;

        decoration = new ModelRenderer(this,8,13);
        decoration.addBox(14.0F, 5.0F, -15.0F, 1, 1, 1);
        decoration.setRotationPoint(-8.0F, 16.0F, 8.0F);
        decoration.setTextureSize(128,128);
		decoration.mirror = true;

        decoration1 = new ModelRenderer(this,8,13);
        decoration1.setRotationPoint(-8.0F, 16.0F, 8.0F);
        decoration1.addBox(1.0F, 5.0F, -2.0F, 1, 1, 1);
		decoration1.setTextureSize(128,128);
		decoration1.mirror = true;
		
        decoration2 = new ModelRenderer(this,8,13);
        decoration2.setRotationPoint(-8.0F, 16.0F, 8.0F);
        decoration2.addBox(14.0F, 5.0F, -2.0F, 1, 1, 1);
		decoration2.setTextureSize(128,128);
		decoration2.mirror = true;
		
        decoration3 = new ModelRenderer(this,8,13);
        decoration3.setRotationPoint(-8.0F, 16.0F, 8.0F);
        decoration3.addBox(1.0F, 5.0F, -15.0F, 1, 1, 1);
		decoration3.setTextureSize(128,128);
		decoration3.mirror = true;
		
        Partition = new ModelRenderer(this,40,18);
        Partition.setRotationPoint(-8.0F, 16.0F, 8.0F);
        Partition.addBox(2.0F, 4.0F, -14.0F, 12, 1, 12);
		Partition.setTextureSize(128,128);
		Partition.mirror = true;
		
        Partition2 = new ModelRenderer(this,40,18);
        Partition2.setRotationPoint(-8.0F, 16.0F, 8.0F);
        Partition2.addBox(2.0F, 2.0F, -14.0F, 12, 1, 12);
		Partition2.setTextureSize(128,128);
		Partition2.mirror = true;
		
        Partition3 = new ModelRenderer(this,40,18);
        Partition3.setRotationPoint(-8.0F, 16.0F, 8.0F);
        Partition3.addBox(2.0F, 0.0F, -14.0F, 12, 1, 12);
		Partition3.setTextureSize(128,128);
		Partition3.mirror = true;
		
        Partition4 = new ModelRenderer(this,40,18);
        Partition4.setRotationPoint(-8.0F, 16.0F, 8.0F);
        Partition4.addBox(2.0F, -2.0F, -14.0F, 12, 1, 12);
		Partition4.setTextureSize(128,128);
		Partition4.mirror = true;
		
        Connect = new ModelRenderer(this,0,0);
        Connect.setRotationPoint(-8.0F, 16.0F, 8.0F);
        Connect.addBox(5.0F, -3.0F, -15.0F, 6, 6, 2);
		Connect.setTextureSize(128,128);
		Connect.mirror = true;
		
        Entry = new ModelRenderer(this,30,18);
        Entry.setRotationPoint(-8.0F, 16.0F, 8.0F);
        Entry.addBox(4.0F, -4.0F, -16.0F, 8, 8, 1);
		Entry.setTextureSize(128,128);
		Entry.mirror = true;
		
        decoration4 = new ModelRenderer(this,0,8);
        decoration4.setRotationPoint(-8.0F, 16.0F, 8.0F);
        decoration4.addBox(13.0F, -5.0F, -6.0F, 1, 3, 1);
		decoration4.setTextureSize(128,128);
		decoration4.mirror = true;
		
        decoration5 = new ModelRenderer(this,40,31);
        decoration5.setRotationPoint(-8.0F, 16.0F, 8.0F);
        decoration5.addBox(10.0F, -24.0F, -12.0F, 2, 2, 2);
		decoration5.setTextureSize(128,128);
		decoration5.mirror = true;
		
        decoration6 = new ModelRenderer(this,0,8);
        decoration6.setRotationPoint(-8.0F, 16.0F, 8.0F);
        decoration6.addBox(6.0F, -23.0F, -10.0F, 4, 1, 4);
		decoration6.setTextureSize(128,128);
		decoration6.mirror = true;
		
        decoration7 = new ModelRenderer(this,0,13);
        decoration7.setRotationPoint(-8.0F, 16.0F, 8.0F);
        decoration7.addBox(10.0F, -24.0F, -6.0F, 1, 2, 1);
		decoration7.setTextureSize(128,128);
		decoration7.mirror = true;
		
        inside = new ModelRenderer(this,40,31);
        inside.setRotationPoint(-8.0F, 16.0F, 8.0F);
        inside.addBox(4.0F, -7.0F, -12.0F, 8, 1, 8);
		inside.setTextureSize(128,128);
		inside.mirror = true;
		
        inside2 = new ModelRenderer(this,12,8);
        inside2.setRotationPoint(-8.0F, 16.0F, 8.0F);
        inside2.addBox(7.0F, -9.0F, -9.0F, 1, 2, 1);
		inside2.setTextureSize(128,128);
		inside2.mirror = true;
		
        inside3 = new ModelRenderer(this,0,18);
        inside3.setRotationPoint(-8.0F, 16.0F, 8.0F);
        inside3.addBox(9.0F, -16.0F, -9.0F, 1, 8, 1);
		inside3.setTextureSize(128,128);
		inside3.mirror = true;
		
        inside4 = new ModelRenderer(this,4,13);
        inside4.setRotationPoint(-8.0F, 16.0F, 8.0F);
        inside4.addBox(8.0F, -9.0F, -9.0F, 1, 1, 1);
		inside4.setTextureSize(128,128);
		inside4.mirror = true;
		
        Subject = new ModelRenderer(this,0,18);
        Subject.setRotationPoint(-8.0F, 16.0F, 8.0F);
        Subject.addBox(3.0F, -22.0F, -13.0F, 10, 28, 10);
		Subject.setTextureSize(128,128);
		Subject.mirror = true;   
   }

    public void render(float size){
        Base.render(size);
        decoration.render(size);
        decoration1.render(size);
        decoration2.render(size);
        decoration3.render(size);
        Partition.render(size);
        Partition2.render(size);
        Partition3.render(size);
        Partition4.render(size);
        Connect.render(size);
        Entry.render(size);
        decoration4.render(size);
        decoration5.render(size);
        decoration6.render(size);
        decoration7.render(size);
        inside.render(size);
        inside2.render(size);
        inside3.render(size);
        inside4.render(size);
        Subject.render(size);
    }

    public void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}