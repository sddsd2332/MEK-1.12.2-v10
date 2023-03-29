package mekanism.common.tier;

import mekanism.common.ColourRGBA;
import mekanism.common.config.MekanismConfig;

public enum CompoundTier implements ITier {
    BASIC(3200, 100, 5, 1, 10, new ColourRGBA(0.2, 0.2, 0.2, 1)),
    ADVANCED(12800, 400, 5, 1, 400, new ColourRGBA(0.2, 0.2, 0.2, 1)),
    ELITE(64000, 1600, 5, 1, 8000, new ColourRGBA(0.2, 0.2, 0.2, 1)),
    ULTIMATE(320000, 6400, 5, 1, 100000, new ColourRGBA(0.2, 0.2, 0.2, 1));

    private final int baseCapacity;
    private final int basePull;
    private final ColourRGBA baseColour;
    private final double baseConduction;
    private final double baseHeatCapacity;
    private final double baseConductionInsulation;
    private final BaseTier baseTier;

    CompoundTier(int capacity, int pullAmount, double inversek, double inverseC, double insulationInversek, ColourRGBA colour) {
        baseCapacity = capacity;
        basePull = pullAmount;
        baseConduction = inversek;
        baseHeatCapacity = inverseC;
        baseConductionInsulation = insulationInversek;
        baseColour = colour;

        baseTier = BaseTier.values()[ordinal()];
    }


    public static CompoundTier get(BaseTier tier) {
        for (CompoundTier transmitter : values()) {
            if (transmitter.getBaseTier() == tier) {
                return transmitter;
            }
        }
        return BASIC;
    }

    @Override
    public BaseTier getBaseTier() {
        return baseTier;
    }

    public int getCableCapacity() {
        return MekanismConfig.current().general.tiers.get(baseTier).CableCapacity.val();
    }

    public int getPipeCapacity() {
        return MekanismConfig.current().general.tiers.get(baseTier).PipeCapacity.val();
    }

    public int getPipePullAmount() {
        return MekanismConfig.current().general.tiers.get(baseTier).PipePullAmount.val();
    }

    public int getTubeCapacity() {
        return MekanismConfig.current().general.tiers.get(baseTier).TubeCapacity.val();
    }

    public int getTubePullAmount() {
        return MekanismConfig.current().general.tiers.get(baseTier).TubePullAmount.val();
    }

    public double getInverseConduction() {
        return MekanismConfig.current().general.tiers.get(baseTier).ConductorInverseConduction.val();
    }

    public double getInverseConductionInsulation() {
        return MekanismConfig.current().general.tiers.get(baseTier).ConductorConductionInsulation.val();
    }

    public double getInverseHeatCapacity() {
        return MekanismConfig.current().general.tiers.get(baseTier).ConductorHeatCapacity.val();
    }

    public int getBaseCapacity() {
        return baseCapacity;
    }

    public int getBasePull() {
        return basePull;
    }

    public ColourRGBA getBaseColour() {
        return baseColour;
    }

    public double getBaseConduction() {
        return baseConduction;
    }

    public double getBaseHeatCapacity() {
        return baseHeatCapacity;
    }

    public double getBaseConductionInsulation() {
        return baseConductionInsulation;
    }
}