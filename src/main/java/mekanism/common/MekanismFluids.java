package mekanism.common;

import java.util.Locale;
import mekanism.api.gas.Gas;
import mekanism.api.gas.GasRegistry;
import mekanism.api.gas.OreGas;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class MekanismFluids {

    public static final Gas Hydrogen = new Gas("hydrogen", 0xFFFFFF);
    public static final Gas Oxygen = new Gas("oxygen", 0x6CE2FF);
    public static final Gas Water = new Gas("water", "mekanism:blocks/liquid/LiquidSteam");
    public static final Gas Chlorine = new Gas("chlorine", 0xCFE800);
    public static final Gas SulfurDioxide = new Gas("sulfurdioxide", 0xA99D90);
    public static final Gas SulfurTrioxide = new Gas("sulfurtrioxide", 0xCE6C6C);
    public static final Gas SulfuricAcid = new Gas("sulfuricacid", 0x82802B);
    public static final Gas HydrogenChloride = new Gas("hydrogenchloride", 0xA8F1E9);

    public static final Fluid HeavyWater = new Fluid("heavywater", new ResourceLocation(Mekanism.MODID, "blocks/liquid/LiquidHeavyWater"),
          new ResourceLocation(Mekanism.MODID, "blocks/liquid/LiquidHeavyWater"));
    public static final Fluid Steam = new Fluid("steam", new ResourceLocation(Mekanism.MODID, "blocks/liquid/LiquidSteam"),
          new ResourceLocation(Mekanism.MODID, "blocks/liquid/LiquidSteam")).setGaseous(true);
    public static final Fluid BioEthanol = new Fluid("bioethanol", new ResourceLocation(Mekanism.MODID, "blocks/liquid/liquidBioEthanol"),
            new ResourceLocation(Mekanism.MODID, "blocks/liquid/liquidBioEthanol"));

    //Internal gases
    public static final Gas LiquidOsmium = new Gas("liquidosmium", 0x52bdca);
    public static final Gas Ethene = new Gas("ethene", 0xEACCF9);
    public static final Gas Sodium = new Gas("sodium", 0xE9FEF4);
    public static final Gas Brine = new Gas("brine", 0xFEEF9C);
    public static final Gas Deuterium = new Gas("deuterium", 0xFF3232);
    public static final Gas Tritium = new Gas("tritium", 0x64FF70);
    public static final Gas FusionFuel = new Gas("fusionfuel", 0x7E007D);
    public static final Gas Lithium = new Gas("lithium", 0xEBA400);

    //Add new gases in the V10 version
    public static final Gas NutritionalPaste = new Gas("nutritionalpaste", 0XEB6CA3);
    public static final Gas NuclearWaste = new Gas( "nuclearwaste",0x4F412A);
    public static final Gas Plutonium = new Gas("plutonium",0x1F919C);
    public static final Gas Polonium = new Gas("polonium",0x1B9E7B);
    public static final Gas SpentNuclearWaste = new Gas("spentnuclearwaste",0x262015);
    public static final Gas HydrofluoricAcid = new Gas("hydrofluoricacid",0xFFC6C7BD);
    public static final Gas Antimatter = new Gas("antimatter",0xA464B3);
    public static final Gas FissileFule = new Gas("fissilefule",0x2E332F);
    public static final Gas SuperheatedSodium  = new Gas("superheatedsodium",0xFFD19469);
    public static final Gas UraniumHexafluoride  = new Gas("uraniumhexafluoride",0xFF809960);
    public static final Gas URANIUMOXIDE  = new Gas("uraniumoxide", 0xFFE1F573);

    //sddsd2332 add new gas
    public static final Gas NutrientSolution = new Gas("nutrientsolution", 0x1B9E7B);
    public static final Gas OxygenEnrichedWater = new Gas("oxygenenrichedwater", 0x6CE2FF);

    public static void register() {
        GasRegistry.register(Hydrogen).registerFluid("liquidhydrogen");
        GasRegistry.register(Oxygen).registerFluid("liquidoxygen");
        GasRegistry.register(Water).registerFluid();
        GasRegistry.register(Chlorine).registerFluid("liquidchlorine");
        GasRegistry.register(SulfurDioxide).registerFluid("liquidsulfurdioxide");
        GasRegistry.register(SulfurTrioxide).registerFluid("liquidsulfurtrioxide");
        GasRegistry.register(SulfuricAcid).registerFluid();
        GasRegistry.register(HydrogenChloride).registerFluid("liquidhydrogenchloride");
        GasRegistry.register(Ethene).registerFluid("liquidethene");
        GasRegistry.register(Sodium).registerFluid("liquidsodium");
        GasRegistry.register(Brine).registerFluid();
        GasRegistry.register(Deuterium).registerFluid("liquiddeuterium");
        GasRegistry.register(Tritium).registerFluid("liquidtritium");
        GasRegistry.register(FusionFuel).registerFluid("liquidfusionfuel");
        GasRegistry.register(Lithium).registerFluid("liquidlithium");

       //Register a new gas
        GasRegistry.register(NutritionalPaste);
        GasRegistry.register(NuclearWaste);
        GasRegistry.register(Plutonium);
        GasRegistry.register(Polonium);
        GasRegistry.register(SpentNuclearWaste);
        GasRegistry.register(Antimatter);
        GasRegistry.register(FissileFule);
        GasRegistry.register(SuperheatedSodium).registerFluid("liquidsuperheatedsodium");
        GasRegistry.register(UraniumHexafluoride);
        GasRegistry.register(URANIUMOXIDE);

        //Register sddsd2332 add new gas
        GasRegistry.register(NutrientSolution);
        GasRegistry.register(OxygenEnrichedWater);
        //Register a new gas add fluid
        GasRegistry.register(HydrofluoricAcid).registerFluid("liquidhydrofluoricacid");

        GasRegistry.register(LiquidOsmium).setVisible(false);

        FluidRegistry.registerFluid(HeavyWater);
        FluidRegistry.registerFluid(Steam);
        FluidRegistry.registerFluid(BioEthanol);

        for (Resource resource : Resource.values()) {
            String name = resource.getName();
            String nameLower = name.toLowerCase(Locale.ROOT);
            //Clean
            OreGas clean = new OreGas("clean" + name, "oregas." + nameLower, resource.tint);
            GasRegistry.register(clean);
            //Dirty
            GasRegistry.register(new OreGas(nameLower, "oregas." + nameLower, resource.tint, clean));
        }

        FluidRegistry.enableUniversalBucket();

        FluidRegistry.addBucketForFluid(HeavyWater);
        FluidRegistry.addBucketForFluid(Brine.getFluid());
        FluidRegistry.addBucketForFluid(Lithium.getFluid());
        FluidRegistry.addBucketForFluid(Polonium.getFluid());
        FluidRegistry.addBucketForFluid(Antimatter.getFluid());
        FluidRegistry.addBucketForFluid(BioEthanol);
    }
}