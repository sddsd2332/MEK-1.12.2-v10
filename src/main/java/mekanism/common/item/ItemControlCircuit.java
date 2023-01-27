package mekanism.common.item;

import javax.annotation.Nonnull;

import mekanism.api.EnumColor;
import mekanism.common.base.IMetaItem;
import mekanism.common.tier.BaseTier;
import mekanism.common.util.LangUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.Locale;

public class ItemControlCircuit extends ItemMekanism implements IMetaItem {

    public ItemControlCircuit() {
        super();
        setHasSubtypes(true);
    }

    @Override
    public String getTexture(int meta) {
        return BaseTier.values()[meta].getSimpleName() + "ControlCircuit";
    }

    @Override
    public int getVariants() {
        return BaseTier.values().length ;
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tabs, @Nonnull NonNullList<ItemStack> itemList) {
        if (isInCreativeTab(tabs)) {
            for (BaseTier tier : BaseTier.values()) {
                    itemList.add(new ItemStack(this, 1, tier.ordinal()));
            }
        }
    }

    @Nonnull
    @Override
    public String getTranslationKey(ItemStack item) {
        return "item." + BaseTier.values()[item.getItemDamage()].getSimpleName() + "ControlCircuit";
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack itemstack) {
        BaseTier tier =  BaseTier.values()[itemstack.getItemDamage()];
        if (tier == BaseTier.BASIC){
            return  EnumColor.BRIGHT_GREEN  + LangUtils.localize( "item." + BaseTier.values()[itemstack.getItemDamage()].getSimpleName() + "ControlCircuit.name");
        }else if (tier == BaseTier.ADVANCED){
            return  EnumColor.RED  + LangUtils.localize( "item." + BaseTier.values()[itemstack.getItemDamage()].getSimpleName() + "ControlCircuit.name");
        }else if (tier == BaseTier.ELITE){
            return  EnumColor.AQUA  + LangUtils.localize( "item." + BaseTier.values()[itemstack.getItemDamage()].getSimpleName() + "ControlCircuit.name");
        }else if (tier == BaseTier.ULTIMATE){
            return  EnumColor.PURPLE  + LangUtils.localize( "item." + BaseTier.values()[itemstack.getItemDamage()].getSimpleName() + "ControlCircuit.name");
        }else return  EnumColor.ORANGE  + LangUtils.localize( "item." + BaseTier.values()[itemstack.getItemDamage()].getSimpleName() + "ControlCircuit.name");
    }

}