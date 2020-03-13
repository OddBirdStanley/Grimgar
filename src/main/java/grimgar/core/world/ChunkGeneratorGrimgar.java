package grimgar.core.world;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

public class ChunkGeneratorGrimgar implements IChunkGenerator {
	
	public World world;
	private long seed;

	//Not used right now
	public ChunkGeneratorGrimgar(World world) {
		this.world = world;
		seed = this.world.getSeed();
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		ChunkPrimer cp = new ChunkPrimer();
		for(int a = 0; a<16; a++) {
			for(int b = 0; b<16; b++) {
				cp.setBlockState(a,1,b,Block.getBlockFromName("stone").getDefaultState());
			}
		}
		Chunk chunk = new Chunk(world, cp, x, z);
		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(int x, int z) {

	}

	@Override
	public boolean generateStructures(Chunk chunk, int x, int z) {
		return false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return null;
	}

	@Override
	public BlockPos getNearestStructurePos(World world, String structureName, BlockPos position,
			boolean findUnexplored) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunk, int x, int z) {

	}

	@Override
	public boolean isInsideStructure(World world, String structureName, BlockPos pos) {
		return false;
	}

}
