package mekanism.common.content.filter;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public interface IItemStackFilter extends IFilter {

    @Nonnull
    ItemStack getItemStack();

    void setItemStack(@Nonnull ItemStack stack);
}