package mekanism.common.integration.groovyscript;

import com.cleanroommc.groovyscript.compat.mods.mekanism.Mekanism;
import mekanism.common.integration.groovyscript.machinerecipe.*;
import mekanism.generators.common.MekanismGenerators;
import net.minecraftforge.fml.common.Loader;


public class GrSMekanismAdd extends Mekanism {


    public NutritionalLiquifier nutritionalLiquifier = new NutritionalLiquifier();
    public AntiprotonicNucleosynthesizer antiprotonicNucleosynthesizer = new AntiprotonicNucleosynthesizer();
    public IsotopicCentrifuge isotopicCentrifuge =new IsotopicCentrifuge();
    public OrganicFarm organicFarm = new OrganicFarm();
    // TODO: To be repaired
    //public AmbientAccumulator ambientAccumulator = new AmbientAccumulator();
    public Stamping stamping =new Stamping();
    public Rolling rolling = new Rolling();
    public Brushed brushed = new Brushed();
    public Turning turning = new Turning();
    public Alloy alloy = new Alloy();
    public CellExtractor cellExtractor = new CellExtractor();
    public CellSeparator cellSeparator = new CellSeparator();
    public Smelter smelter = new Smelter();
    public FusionCooling fusion = new FusionCooling();
    public Recycler recycler = new Recycler();


    public GrSMekanismAdd(){
        addRegistry(nutritionalLiquifier);
        addRegistry(antiprotonicNucleosynthesizer);
        addRegistry(isotopicCentrifuge);
        addRegistry(organicFarm);
        // TODO: To be repaired
       //addRegistry(ambientAccumulator);
        addRegistry(stamping);
        addRegistry(rolling);
        addRegistry(brushed);
        addRegistry(turning);
        addRegistry(alloy);
        addRegistry(cellExtractor);
        addRegistry(cellSeparator);
        addRegistry(smelter);
        addRegistry(recycler);
        if (Loader.isModLoaded(MekanismGenerators.MODID)){
            addRegistry(fusion);
       }
    }


    // TODO: Need to replace
    //public static boolean isEmpty(int input) { return false;}
}
