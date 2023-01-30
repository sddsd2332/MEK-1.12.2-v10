//IsotopicCentrifuge
//mods.Mekanism.Isotopic_Centrifuge.add(gas('water'), gas('hydrogen'))
//mods.Mekanism.Isotopic_Centrifuge.removeByInput(gas('nuclearwaste'))

//NutritionalLiquifier
//mods.Mekanism.NutritionalLiquifier.add(item('minecraft:cake'),gas('nutritionalpaste')*200)
mods.Mekanism.NutritionalLiquifier.removeByInput(item('minecraft:apple'))

//Antiprotonic Nucleosynthesizer
mods.Mekanism.AntiprotonicNucleosynthesizer.add(item('minecraft:iron_ingot'),gas('antimatter'),item('minecraft:gold_ingot'),0,500)
mods.Mekanism.AntiprotonicNucleosynthesizer.removeByInput(item('minecraft:wool:4'),gas('antimatter'))
mods.Mekanism.AntiprotonicNucleosynthesizer.recipeBuilder().gasInput(gas('water')).input(item('minecraft:clay_ball')).output(item('minecraft:diamond')).durazqtion(1).energy(1).register()

//Organic Farm
mods.Mekanism.OrganicFarm.add(item('minecraft:stone'),gas('water'),item('minecraft:stone:1'),item('minecraft:stone:2'),1)
mods.Mekanism.OrganicFarm.removeByInput(item('minecraft:red_mushroom'),gas('nutrientsolution'))

//Stamping
mods.Mekanism.Stamping.add(item('minecraft:stone'),item('minecraft:dirt'))
mods.Mekanism.Stamping.removeByInput(item('minecraft:dirt'))

//Rolling
mods.Mekanism.Rolling.add(item('minecraft:stone'),item('minecraft:dirt'))
mods.Mekanism.Rolling.removeByInput(item('minecraft:dirt'))

//Brushed
mods.Mekanism.Brushed.add(item('minecraft:stone'),item('minecraft:dirt'))
mods.Mekanism.Brushed.removeByInput(item('minecraft:dirt'))

//Turning
mods.Mekanism.Turning.add(item('minecraft:stone'),item('minecraft:dirt'))
mods.Mekanism.Turning.removeByInput(item('minecraft:dirt'))

//Alloy
mods.mekanism.Alloy.add(item('minecraft:nether_star'),item('minecraft:glass'),item('minecraft:beacon'))
mods.Mekanism.Alloy.removeByInput(item('mekanism:ingot:6'),item('mekanism:ingot:5'))

//CellExtractor
mods.Mekanism.CellExtractor.add(item('minecraft:stone'),item('minecraft:stone:1'),item('minecraft:stone:2'),1)
mods.Mekanism.CellExtractor.removeByInput(item('minecraft:iron_ore'))

//CellSeparator
mods.Mekanism.CellSeparator.add(item('minecraft:stone'),item('minecraft:stone:1'),item('minecraft:stone:2'),1)
mods.Mekanism.CellSeparator.removeByInput(item('minecraft:iron_ore'))

//Energized Smelter
//mods.mekanism.Smelter.removeByInput(item('minecraft:gold_ore'))
mods.mekanism.Smelter.add(item('minecraft:iron_ore'),item('minecraft:iron_ingot'))

//Fusion reactor cooling recipes
mods.Mekanism.FusionReactor.removeByInput(fluid('liquidsodium'))
mods.Mekanism.FusionReactor.add(fluid('liquidsodium'),fluid('water'))
