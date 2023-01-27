package mekanism.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class CreativeTabMekanismAddition extends CreativeTabs {

    public CreativeTabMekanismAddition() {
        super("tabMekanismAddition");
    }

    @Nonnull
    @Override
    public ItemStack createIcon() {
        return new ItemStack(MekanismItems.WalkieTalkie);
    }
}