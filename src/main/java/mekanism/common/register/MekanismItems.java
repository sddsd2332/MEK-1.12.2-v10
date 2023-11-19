package mekanism.common.register;

import mekanism.common.Mekanism;
import mekanism.common.Upgrade;
import mekanism.common.item.*;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(Mekanism.MODID)
public class MekanismItems {

    public static final Item EnrichedAlloy = new ItemAlloy();
    public static final Item ReinforcedAlloy = new ItemAlloy();
    public static final Item AtomicAlloy = new ItemAlloy();
    public static final Item CosmicAlloy = new ItemAlloy();
    public static final Item TeleportationCore = new ItemMekanism();
    public static final Item ElectrolyticCore = new ItemMekanism();
    public static final Item Substrate = new ItemMekanism();
    public static final Item Polyethene = new ItemHDPE();
    public static final Item BioFuel = new ItemMekanism();
    public static final Item ItemProxy = new ItemProxy();
    public static final Item EnrichedIron = new ItemMekanism();
    public static final Item CompressedCarbon = new ItemMekanism();
    public static final Item CompressedRedstone = new ItemMekanism();
    public static final Item CompressedDiamond = new ItemMekanism();
    public static final Item CompressedObsidian = new ItemMekanism();
    public static final Item SpeedUpgrade = new ItemUpgrade(Upgrade.SPEED);
    public static final Item EnergyUpgrade = new ItemUpgrade(Upgrade.ENERGY);
    public static final Item FilterUpgrade = new ItemUpgrade(Upgrade.FILTER);
    public static final Item MufflingUpgrade = new ItemUpgrade(Upgrade.MUFFLING);
    public static final Item GasUpgrade = new ItemUpgrade(Upgrade.GAS);
    public static final Item AnchorUpgrade = new ItemUpgrade(Upgrade.ANCHOR);
    public static final Item StoneGeneratorUpgrade = new ItemUpgrade(Upgrade.STONE_GENERATOR);
    public static final Item TierInstaller = new ItemTierInstaller();
    public static final ItemEnergized EnergyTablet = new ItemEnergized(1000000);
    public static final ItemCanteen Canteen = new ItemCanteen();
    public static final ItemRobit Robit = new ItemRobit();
    public static final ItemAtomicDisassembler AtomicDisassembler = new ItemAtomicDisassembler();
    public static final ItemPortableTeleporter PortableTeleporter = new ItemPortableTeleporter();
    public static final ItemConfigurator Configurator = new ItemConfigurator();
    public static final ItemNetworkReader NetworkReader = new ItemNetworkReader();
    public static final Item WalkieTalkie = new ItemWalkieTalkie();
    public static final ItemElectricBow ElectricBow = new ItemElectricBow();
    public static final ItemFlamethrower Flamethrower = new ItemFlamethrower();
    public static final ItemSeismicReader SeismicReader = new ItemSeismicReader();
    public static final Item Dictionary = new ItemDictionary();
    public static final ItemGaugeDropper GaugeDropper = new ItemGaugeDropper();
    public static final Item ConfigurationCard = new ItemConfigurationCard();
    public static final Item CraftingFormula = new ItemCraftingFormula();
    public static final ItemScubaTank ScubaTank = new ItemScubaTank();
    public static final ItemGasMask GasMask = new ItemGasMask();
    public static final ItemJetpack Jetpack = new ItemJetpack();
    public static final ItemJetpack ArmoredJetpack = new ItemJetpack();
    public static final ItemFreeRunners FreeRunners = new ItemFreeRunners();
    public static final Item Balloon = new ItemBalloon();
    public static final Item PlutoniumPellet = new ItemMekanism();
    ;
    public static final Item AntimatterPellet = new ItemMekanism();
    ;
    public static final Item ReprocessedFissileFragment = new ItemMekanism();
    ;
    public static final Item YellowCakeUranium = new ItemMekanism();
    ;
    public static final Item PoloniumPellet = new ItemMekanism();
    ;

    public static final ItemMekTool MekTool = new ItemMekTool();
    //  public static final ItemMekaSuitHelmet MekaSuitHelmet = new ItemMekaSuitHelmet();
    //   public static final ItemMekaSuitChest MekaSuitChest = new ItemMekaSuitChest();

    public static final Item EnergyTabletCraft = new ItemMekanism();
    public static final Item CosmicMatter = new ItemMekanism();
    public static final Item Scrap = new ItemMekanism();
    public static final Item ScrapBox = new ItemMekanism();
    public static final Item EmptyCrystals = new ItemMekanism();
    /*
    public static final Item EnergyCubeBasicCraft = new ItemMekanism();
    public static final Item EnergyCubeAdvancedCraft = new ItemMekanism();
    public static final Item EnergyCubeEliteCraft = new ItemMekanism();
    public static final Item EnergyCubeUltimateCraft = new ItemMekanism();
    public static final Item EnergyCubeCreativeCraft = new ItemMekanism();
    */

    //Multi-ID Items
    public static final Item OtherDust = new ItemOtherDust();
    public static final Item Dust = new ItemDust();
    public static final Item Sawdust = new ItemMekanism();
    public static final Item Salt = new ItemMekanism();
    public static final Item Ingot = new ItemIngot();
    public static final Item Nugget = new ItemNugget();
    public static final Item Clump = new ItemClump();
    public static final Item DirtyDust = new ItemDirtyDust();
    public static final Item Shard = new ItemShard();
    public static final Item Crystal = new ItemCrystal();
    public static final Item ControlCircuit = new ItemControlCircuit();

    /**
     * Adds and registers all items.
     *
     * @param registry Forge registry to add the items to
     */
    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.register(init(ElectricBow, "ElectricBow"));
        registry.register(init(Canteen, "Canteen"));
        registry.register(init(Dust, "Dust"));
        registry.register(init(Ingot, "Ingot"));
        registry.register(init(Nugget, "Nugget"));
        registry.register(init(EnergyTablet, "EnergyTablet"));
        registry.register(init(SpeedUpgrade, "SpeedUpgrade"));
        registry.register(init(EnergyUpgrade, "EnergyUpgrade"));
        registry.register(init(FilterUpgrade, "FilterUpgrade"));
        registry.register(init(MufflingUpgrade, "MufflingUpgrade"));
        registry.register(init(GasUpgrade, "GasUpgrade"));
        registry.register(init(AnchorUpgrade, "AnchorUpgrade"));
        registry.register(init(StoneGeneratorUpgrade,"StoneGeneratorUpgrade"));
        registry.register(init(Robit, "Robit"));
        registry.register(init(AtomicDisassembler, "AtomicDisassembler"));
        registry.register(init(MekTool, "MekTool"));
        registry.register(init(EnrichedAlloy, "EnrichedAlloy"));
        registry.register(init(ReinforcedAlloy, "ReinforcedAlloy"));
        registry.register(init(AtomicAlloy, "AtomicAlloy"));
        registry.register(init(CosmicAlloy, "CosmicAlloy"));
        registry.register(init(ItemProxy, "ItemProxy"));
        registry.register(init(ControlCircuit, "ControlCircuit"));
        registry.register(init(EnrichedIron, "EnrichedIron"));
        registry.register(init(CompressedCarbon, "CompressedCarbon"));
        registry.register(init(CompressedRedstone, "CompressedRedstone"));
        registry.register(init(CompressedDiamond, "CompressedDiamond"));
        registry.register(init(CompressedObsidian, "CompressedObsidian"));
        registry.register(init(PortableTeleporter, "PortableTeleporter"));
        registry.register(init(TeleportationCore, "TeleportationCore"));
        registry.register(init(Clump, "Clump"));
        registry.register(init(DirtyDust, "DirtyDust"));
        registry.register(init(Configurator, "Configurator"));
        registry.register(init(NetworkReader, "NetworkReader"));
        registry.register(init(WalkieTalkie, "WalkieTalkie"));
        registry.register(init(Jetpack, "Jetpack"));
        registry.register(init(Dictionary, "Dictionary"));
        registry.register(init(GasMask, "GasMask"));
        registry.register(init(ScubaTank, "ScubaTank"));
        registry.register(init(Balloon, "Balloon"));
        registry.register(init(Shard, "Shard"));
        registry.register(init(ElectrolyticCore, "ElectrolyticCore"));
        registry.register(init(Sawdust, "Sawdust"));
        registry.register(init(Salt, "Salt"));
        registry.register(init(Crystal, "Crystal"));
        registry.register(init(FreeRunners, "FreeRunners"));
        registry.register(init(ArmoredJetpack, "ArmoredJetpack"));
        registry.register(init(ConfigurationCard, "ConfigurationCard"));
        registry.register(init(CraftingFormula, "CraftingFormula"));
        registry.register(init(SeismicReader, "SeismicReader"));
        registry.register(init(Substrate, "Substrate"));
        registry.register(init(Polyethene, "Polyethene"));
        registry.register(init(BioFuel, "BioFuel"));
        registry.register(init(Flamethrower, "Flamethrower"));
        registry.register(init(GaugeDropper, "GaugeDropper"));
        registry.register(init(TierInstaller, "TierInstaller"));
        registry.register(init(OtherDust, "OtherDust"));

        //    registry.register(init(MekaSuitHelmet, "MekaSuitHelmet"));
        //    registry.register(init(MekaSuitChest, "MekaSuitChest"));

        registry.register(init(EnergyTabletCraft, "EnergyTabletCraft"));
        registry.register(init(CosmicMatter, "CosmicMatter"));
        registry.register(init(Scrap, "Scrap"));
        registry.register(init(ScrapBox, "ScrapBox"));
        registry.register(init(EmptyCrystals, "EmptyCrystals"));
        /*
        registry.register(init(EnergyCubeBasicCraft,"EnergyCubeBasicCraft"));
        registry.register(init(EnergyCubeAdvancedCraft,"EnergyCubeAdvancedCraft"));
        registry.register(init(EnergyCubeEliteCraft,"EnergyCubeEliteCraft"));
        registry.register(init(EnergyCubeUltimateCraft,"EnergyCubeUltimateCraft"));
        registry.register(init(EnergyCubeCreativeCraft,"EnergyCubeCreativeCraft"));
        */
        registry.register(init(PlutoniumPellet, "PlutoniumPellet"));
        registry.register(init(AntimatterPellet, "AntimatterPellet"));
        registry.register(init(ReprocessedFissileFragment, "ReprocessedFissileFragment"));
        registry.register(init(YellowCakeUranium, "YellowCakeUranium"));
        registry.register(init(PoloniumPellet, "PoloniumPellet"));
    }

    public static Item init(Item item, String name) {
        return item.setTranslationKey(name).setRegistryName(new ResourceLocation(Mekanism.MODID, name));
    }
}