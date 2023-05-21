package mekanism.common.chunkloading;

import mekanism.common.tile.component.TileComponentChunkLoader;
import net.minecraft.util.math.ChunkPos;

import java.util.Set;

public interface IChunkLoader {

    TileComponentChunkLoader getChunkLoader();

    Set<ChunkPos> getChunkSet();
}