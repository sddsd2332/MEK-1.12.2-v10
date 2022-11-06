package mekanism.common.integration.groovyscript;

import com.cleanroommc.groovyscript.compat.mods.ModSupport;

public class mekgrs extends ModSupport.Container {

    public static final ModSupport.Container<GrSMekanismAdd> MEKANISM = new ModSupport.Container("mekanism", "Mekanism", GrSMekanismAdd::new);


    public mekgrs() {
        super("mekanism", "Mekanism", GrSMekanismAdd::new, "Mekanism");
    }

}
