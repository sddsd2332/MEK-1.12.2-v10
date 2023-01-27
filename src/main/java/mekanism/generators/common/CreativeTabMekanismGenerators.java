package mekanism.generators.common;

import mekanism.common.MekanismItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;


public class CreativeTabMekanismGenerators extends CreativeTabs {

    public  CreativeTabMekanismGenerators() {
        super("tabMekanismGenerators");
    }
    @Nonnull
    @Override
    public ItemStack createIcon() {
        return new ItemStack(GeneratorsBlocks.Generator, 1,6);
    }
}
