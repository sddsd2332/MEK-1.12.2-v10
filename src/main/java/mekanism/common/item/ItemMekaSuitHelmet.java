/*package mekanism.common.item;

import mekanism.client.render.ModelCustomArmor;
import mekanism.client.render.ModelCustomArmor.ArmorModel;
import mekanism.common.Mekanism;
import mekanism.common.config.MekanismConfig;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class ItemMekaSuitHelmet extends ItemArmor implements ISpecialArmor {

    public ItemMekaSuitHelmet() {
        super(EnumHelper.addArmorMaterial("MEKASUITHELMET", "mekasuithelmet", 0, new int[]{0, 0, 0, 0}, 100, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
              100), 0, EntityEquipmentSlot.HEAD);
        setCreativeTab(Mekanism.tabMekanism);
    }

    @Override
    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) {
        return armorType == EntityEquipmentSlot.HEAD;
    }



    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "mekanism:render/NullArmor.png";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        ModelCustomArmor model = ModelCustomArmor.INSTANCE;
        model.modelType = ArmorModel.MEKASUITHELMET;
        return model;
    }


    @Override
    public ArmorProperties getProperties(EntityLivingBase entityLivingBase, @NotNull ItemStack itemStack, DamageSource damageSource, double v, int i) {
        return new ArmorProperties(1, MekanismConfig.current().general.armoredMekaSuitHelmetDamageRatio.val(),
                MekanismConfig.current().general.armoredMekaSuitHelmetDamageMax.val());
    }

    public int getArmorDisplay(EntityPlayer player, @Nonnull ItemStack armor, int slot) {
        return 20;
    }

    @Override
    public void damageArmor(EntityLivingBase entityLivingBase, @NotNull ItemStack itemStack, DamageSource damageSource, int i, int i1) {
    }




}

 */