package mekanism.common.integration.mysticalagriculture;

import mekanism.common.register.MekanismFluids;
import mekanism.common.config.MekanismConfig;
import mekanism.common.recipe.RecipeHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static mekanism.common.integration.MekanismHooks.MYSTICALAGRICULTURE_MOD_ID;


/**
 * This code is obtained through CofhCore.
 */
public class MysticalAgricultureSeed {

    public static void seed() {
        String[] names = {
                "stone",
                "dirt",
                "nature",
                "wood",
                "water",
                "ice",
                "fire",
                "dye",
                "nether",
                "coal",
                "iron",
                "nether_quartz",
                "glowstone",
                "redstone",
                "obsidian",
                "gold",
                "lapis_lazuli",
                "end",
                "experience",
                "diamond",
                "emerald",
                "zombie",
                "pig",
                "chicken",
                "cow",
                "sheep",
                "slime",
                "skeleton",
                "creeper",
                "spider",
                "rabbit",
                "guardian",
                "blaze",
                "ghast",
                "enderman",
                "wither_skeleton",
                "rubber",
                "silicon",
                "sulfur",
                "aluminum",
                "copper",
                "saltpeter",
                "tin",
                "bronze",
                "zinc",
                "brass",
                "silver",
                "lead",
                "graphite",
                "steel",
                "nickel",
                "constantan",
                "electrum",
                "invar",
                "mithril",
                "tungsten",
                "titanium",
                "uranium",
                "chrome",
                "platinum",
                "iridium",
                "ruby",
                "sapphire",
                "peridot",
                "amber",
                "topaz",
                "malachite",
                "tanzanite",
                "blizz",
                "blitz",
                "basalz",
                "signalum",
                "lumium",
                "enderium",
                "fluxed_electrum",
                "hop_graphite",
                "aluminum_brass",
                "knightslime",
                "ardite",
                "cobalt",
                "manyullyn",
                "grains_of_infinity",
                "electrical_steel",
                "redstone_alloy",
                "conductive_iron",
                "soularium",
                "dark_steel",
                "pulsating_iron",
                "energetic_alloy",
                "vibrant_alloy",
                "end_steel",
                "mystical_flower",
                "manasteel",
                "elementium",
                "terrasteel",
                "quicksilver",
                "thaumium",
                "void_metal",
                "dawnstone",
                "uranium_238",
                "iridium_ore",
                "osmium",
                "glowstone_ingot",
                "refined_obsidian",
                "aquarium",
                "cold_iron",
                "star_steel",
                "adamantine",
                "marble",
                "limestone",
                "basalt",
                "apatite",
                "electrotine",
                "alumite",
                "steeleaf",
                "ironwood",
                "knightmetal",
                "fiery_ingot",
                "meteoric_iron",
                "desh",
                "coralium",
                "abyssalnite",
                "dreadium",
                "slimy_bone",
                "syrmorite",
                "octine",
                "valonite",
                "thorium",
                "boron",
                "lithium",
                "magnesium",
                "black_quartz",
                "menril",
                "vinteum",
                "chimerite",
                "blue_topaz",
                "moonstone",
                "sunstone",
                "aquamarine",
                "starmetal",
                "rock_crystal",
                "ender_biotite",
                "slate",
                "dark_gem",
                "compressed_iron",
                "ender_amethyst",
                "draconium",
                "yellorium",
                "sky_stone",
                "certus_quartz",
                "fluix",
                "press",
                "quartz_enriched_iron"
        };


        for (String name : names) {
            ItemStack seeds = getSeeds(name);
            ItemStack essence = getEssence(name);
            if (seeds != ItemStack.EMPTY){
                if (!RecipeHandler.Recipe.ORGANIC_FARM.containsRecipe(seeds) && !RecipeHandler.Recipe.ORGANIC_FARM.containsRecipe(essence)) {
                    RecipeHandler.addOrganicFarmRecipe(seeds, MekanismFluids.NutrientSolution, essence, seeds, MekanismConfig.current().general.seed.val());
                }
            }
        }
        for (int i = 1; i <= 5; i++) {
            ItemStack seeds = getSeeds("tier" + i + "_inferium");
            if (seeds != ItemStack.EMPTY) {
                if (!RecipeHandler.Recipe.ORGANIC_FARM.containsRecipe(seeds)) {
                    RecipeHandler.addOrganicFarmRecipe(seeds, MekanismFluids.NutrientSolution, getItemStack("crafting", i * 4, 0), seeds, MekanismConfig.current().general.seed.val());
                }
            }
        }


        if (Loader.isModLoaded("mysticalagradditions")) {
            String[] names2 = {
                    "nether_star",
                    "dragon_egg",
                    "awakened_draconium",
                    "neutronium"
            };

            for (String name : names2) {
                ItemStack seeds = getSeeds2(name);
                ItemStack essence = getEssence2(name);
                if (seeds != ItemStack.EMPTY) {
                    if (!RecipeHandler.Recipe.ORGANIC_FARM.containsRecipe(seeds) && !RecipeHandler.Recipe.ORGANIC_FARM.containsRecipe(essence)) {
                        RecipeHandler.addOrganicFarmRecipe(seeds, MekanismFluids.NutrientSolution, essence, seeds, MekanismConfig.current().general.seed.val());
                    }
                }
            }

            ItemStack tier6seeds = getSeeds2("tier6_inferium");
            if (tier6seeds != ItemStack.EMPTY) {
                if (!RecipeHandler.Recipe.ORGANIC_FARM.containsRecipe(tier6seeds)) {
                    RecipeHandler.addOrganicFarmRecipe(tier6seeds, MekanismFluids.NutrientSolution, getItemStack("crafting", 6 * 4, 0), tier6seeds, MekanismConfig.current().general.seed.val());
                }
            }
        }
    }


    protected static ItemStack getItemStack(String id, String name, int amount, int meta) {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(id + ":" + name));
        return item != null ? new ItemStack(item, amount, meta) : ItemStack.EMPTY;
    }

    protected static ItemStack getSeeds(String name) {
        return getItemStack(MYSTICALAGRICULTURE_MOD_ID, name + "_seeds", 1, 0);
    }

    protected static ItemStack getSeeds2(String name) {
        return getItemStack("mysticalagradditions", name + "_seeds", 1, 0);
    }

    protected static ItemStack getEssence(String name) {
        return getItemStack(MYSTICALAGRICULTURE_MOD_ID, name + "_essence", 4, 0);
    }

    protected static ItemStack getEssence2(String name) {
        return getItemStack("mysticalagradditions", name + "_essence", 4, 0);
    }

    protected static ItemStack getItemStack(String name, int amount, int meta) {
        return getItemStack(MYSTICALAGRICULTURE_MOD_ID, name, amount, meta);
    }
}
