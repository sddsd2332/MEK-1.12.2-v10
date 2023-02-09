package mekanism.common.block;

import mekanism.common.Mekanism;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockEnergyCubeCraft extends Block {
    public BlockEnergyCubeCraft(){
        super(Material.IRON);
        setHardness(2F);
        setResistance(4F);
        setCreativeTab(Mekanism.tabMekanism);
    }
}
