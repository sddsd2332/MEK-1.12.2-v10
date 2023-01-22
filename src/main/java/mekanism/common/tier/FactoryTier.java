package mekanism.common.tier;

import java.util.function.Consumer;
import mekanism.common.Mekanism;
import mekanism.common.block.states.BlockStateMachine.MachineType;
import net.minecraft.util.ResourceLocation;


public enum FactoryTier implements ITier {

    //TODO:这里只需要processes,把GUI资源移除
    /*
    BASIC(3),
    ADVANCED(5),
    ELITE(7),
    ULTIMATE(9),
    CREATIVE(11);
    */

    BASIC(3, new ResourceLocation(Mekanism.MODID, "gui/factory/GuiBasicFactory.png")),
    ADVANCED(5, new ResourceLocation(Mekanism.MODID, "gui/factory/GuiAdvancedFactory.png")),
    ELITE(7, new ResourceLocation(Mekanism.MODID, "gui/factory/GuiEliteFactory.png")),
    ULTIMATE(9, new ResourceLocation(Mekanism.MODID, "gui/factory/GuiUltimateFactory.png")),
    CREATIVE(11,new ResourceLocation(Mekanism.MODID, "gui/factory/GuiCreativeFactory.png"));


    public final int processes;

    //TODO:guiLocation移除
    public final ResourceLocation guiLocation;
    private final BaseTier baseTier;

    //TODO:ResourceLocation移除
    FactoryTier(int process, ResourceLocation gui) {
        processes = process;
        guiLocation = gui;
        baseTier = BaseTier.values()[ordinal()];
    }

    @Override
    public BaseTier getBaseTier() {
        return baseTier;
    }

    public static void forEnabled(Consumer<FactoryTier> consumer) {
        if (MachineType.BASIC_FACTORY.isEnabled()) {
            consumer.accept(FactoryTier.BASIC);
        }
        if (MachineType.ADVANCED_FACTORY.isEnabled()) {
            consumer.accept(FactoryTier.ADVANCED);
        }
        if (MachineType.ELITE_FACTORY.isEnabled()) {
            consumer.accept(FactoryTier.ELITE);
        }
        if (MachineType.ULTIMATE_FACTORY.isEnabled()) {
            consumer.accept(FactoryTier.ULTIMATE);
        }
        if (MachineType.CREATIVE_FACTORY.isEnabled()) {
            consumer.accept(FactoryTier.CREATIVE);
        }
    }
}