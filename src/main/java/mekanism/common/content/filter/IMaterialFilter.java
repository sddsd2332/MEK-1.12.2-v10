package mekanism.common.content.filter;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public interface IMaterialFilter extends IFilter {

    @Nonnull
    ItemStack getMaterialItem();

    void setMaterialItem(@Nonnull ItemStack stack);
}