package mekanism.client.render.item;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public abstract class SubTypeItemRenderer<TYPE> extends MekanismItemStackRenderer {

    @Nullable
    protected abstract ItemLayerWrapper getModel(TYPE type);

    @Nullable
    protected abstract TYPE getType(@Nonnull ItemStack stack);

    @Nonnull
    @Override
    protected TransformType getTransform(@Nonnull ItemStack stack) {
        TYPE type = getType(stack);
        if (type == null) {
            return TransformType.NONE;
        }
        ItemLayerWrapper model = getModel(type);
        if (model == null) {
            return TransformType.NONE;
        }
        return model.getTransform();
    }
}