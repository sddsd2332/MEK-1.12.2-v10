package mekanism.common.item;

import mekanism.api.EnumColor;
import mekanism.common.Mekanism;
import mekanism.common.MekanismItems;
import mekanism.common.util.LangUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemMekanism extends Item {

    public ItemMekanism() {
        super();
        setCreativeTab(Mekanism.tabMekanism);
    }

    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack itemstack) {
        if (this == MekanismItems.ElectrolyticCore){
            return EnumColor.YELLOW + LangUtils.localize("item.ElectrolyticCore.name");
        }else if (this == MekanismItems.TeleportationCore){
            return EnumColor.AQUA + LangUtils.localize("item.TeleportationCore.name");
        }else if (this == MekanismItems.AntimatterPellet){
            return EnumColor.PURPLE + LangUtils.localize("item.AntimatterPellet.name");
        }else if (this == MekanismItems.PlutoniumPellet){
            return  EnumColor.GREY + LangUtils.localize("item.PlutoniumPellet.name");
        }else if (this == MekanismItems.ReprocessedFissileFragment){
            return  EnumColor.AQUA + LangUtils.localize("item.ReprocessedFissileFragment.name");
        }else if (this == MekanismItems.PoloniumPellet){
            return EnumColor.INDIGO +LangUtils.localize("item.PoloniumPellet.name");
        }else if (this == MekanismItems.YellowCakeUranium){
            return EnumColor.YELLOW + LangUtils.localize("item.YellowCakeUranium.name");
        }else if (this == MekanismItems.EnrichedAlloy){
            return EnumColor.RED +  LangUtils.localize("item.EnrichedAlloy.name");
        }else if (this == MekanismItems.ReinforcedAlloy){
            return EnumColor.AQUA + LangUtils.localize("item.ReinforcedAlloy.name");
        }else if (this == MekanismItems.AtomicAlloy){
            return EnumColor.PURPLE + LangUtils.localize("item.AtomicAlloy.name");
        }else if (this == MekanismItems.CosmicAlloy){
            return EnumColor.ORANGE +  LangUtils.localize("item.CosmicAlloy.name");
        }else if (this == MekanismItems.AtomicDisassembler){
            return EnumColor.AQUA +  LangUtils.localize("item.AtomicDisassembler.name");
        }else if (this == MekanismItems.Canteen){
            return EnumColor.YELLOW +  LangUtils.localize("item.Canteen.name");
        }else if (this == MekanismItems.ConfigurationCard){
            return EnumColor.YELLOW +  LangUtils.localize("item.ConfigurationCard.name");
        }else if (this == MekanismItems.Configurator){
            return EnumColor.AQUA +  LangUtils.localize("item.Configurator.name");
        }else if (this == MekanismItems.Dictionary){
            return EnumColor.YELLOW +  LangUtils.localize("item.Dictionary.name");
        }else if (this == MekanismItems.ElectricBow){
            return EnumColor.AQUA +  LangUtils.localize("item.ElectricBow.name");
        }else if (this == MekanismItems.EnergyTablet){
            return EnumColor.YELLOW +  LangUtils.localize("item.EnergyTablet.name");
        }else if (this == MekanismItems.Flamethrower){
            return EnumColor.AQUA +  LangUtils.localize("item.Flamethrower.name");
        }else if (this == MekanismItems.SpeedUpgrade){
            return EnumColor.YELLOW +  LangUtils.localize("item.SpeedUpgrade.name");
        }else if (this == MekanismItems.EnergyUpgrade){
            return EnumColor.YELLOW +  LangUtils.localize("item.EnergyUpgrade.name");
        }else if (this == MekanismItems.FilterUpgrade){
            return EnumColor.YELLOW +  LangUtils.localize("item.FilterUpgrade.name");
        }else if (this == MekanismItems.GasUpgrade){
            return EnumColor.YELLOW +  LangUtils.localize("item.GasUpgrade.name");
        }else if (this == MekanismItems.MufflingUpgrade){
            return EnumColor.YELLOW +  LangUtils.localize("item.MufflingUpgrade.name");
        }else if (this == MekanismItems.AnchorUpgrade){
            return EnumColor.YELLOW +  LangUtils.localize("item.AnchorUpgrade.name");
        }else
            return super.getItemStackDisplayName(itemstack);
    }
}