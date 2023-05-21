package mekanism.common.integration.groovyscript;

import com.cleanroommc.groovyscript.compat.mods.ModSupport;
import mekanism.common.Mekanism;
import mekanism.common.integration.MekanismHooks;
import mekanism.common.integration.groovyscript.machinerecipe.*;
import mekanism.generators.common.MekanismGenerators;
import net.minecraftforge.fml.common.Loader;


public class GrSMekanismAdd {

    public static ModSupport.Container<Container> modSupportContainer;
    private static boolean loaded = false;

    private GrSMekanismAdd() {
    }


    public static void init() {
        loaded = Loader.isModLoaded(MekanismHooks.GROOVYSCRIPT_MOD_ID);
        if (!loaded) return;
        modSupportContainer = new ModSupport.Container<>(Mekanism.MODID, Mekanism.MOD_NAME, Container::new, "mek");
    }


    public static boolean isLoaded() {
        return loaded;
    }

    public static Container getInstance() {
        return modSupportContainer.get();
    }


    public static class Container extends com.cleanroommc.groovyscript.compat.mods.mekanism.Mekanism {

        public NutritionalLiquifier nutritionalLiquifier = new NutritionalLiquifier();
        public AntiprotonicNucleosynthesizer antiprotonicNucleosynthesizer = new AntiprotonicNucleosynthesizer();
        public IsotopicCentrifuge isotopicCentrifuge = new IsotopicCentrifuge();
        public OrganicFarm organicFarm = new OrganicFarm();
        public AmbientAccumulator ambientAccumulator = new AmbientAccumulator();
        public Stamping stamping = new Stamping();
        public Rolling rolling = new Rolling();
        public Brushed brushed = new Brushed();
        public Turning turning = new Turning();
        public Alloy alloy = new Alloy();
        public CellExtractor cellExtractor = new CellExtractor();
        public CellSeparator cellSeparator = new CellSeparator();
        public Smelter smelter = new Smelter();
        public FusionCooling fusion = new FusionCooling();
        public Recycler recycler = new Recycler();

        public Container() {
            addRegistry(nutritionalLiquifier);
            addRegistry(antiprotonicNucleosynthesizer);
            addRegistry(isotopicCentrifuge);
            addRegistry(organicFarm);
            addRegistry(ambientAccumulator);
            addRegistry(stamping);
            addRegistry(rolling);
            addRegistry(brushed);
            addRegistry(turning);
            addRegistry(alloy);
            addRegistry(cellExtractor);
            addRegistry(cellSeparator);
            addRegistry(smelter);
            addRegistry(recycler);
            if (Loader.isModLoaded(MekanismGenerators.MODID)) {
                addRegistry(fusion);
            }
        }

    }

}
