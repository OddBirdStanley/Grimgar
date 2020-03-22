package grimgar.core.world;

import grimgar.client.renderer.sky.DarrengarSkyRenderer;
import grimgar.client.renderer.sky.ParanoSkyRenderer;
import grimgar.core.init.InitBiomes;
import grimgar.main.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;

public class WorldProviderParano extends WorldProvider {
	
	public WorldProviderParano() {
		
	}
	
	@Override
	protected void init() {
		hasSkyLight = true;
		setDimension(Reference.DIM_ID_PARANO);
	}
	
	@Override
	public boolean canDropChunk(int x, int z) {
		return !this.world.isSpawnChunk(x, z);
	}
	
	@Override
	public IRenderHandler getSkyRenderer() {
		return new ParanoSkyRenderer();
	}
	
	@Override
	public boolean canRespawnHere() {
		return true;
	}
	
	@Override
	public String getSaveFolder() {
		return "DIM"+this.getDimension();
	}
	
	@Override
	public int getAverageGroundLevel() {
		return 128;
	}
	
	@Override
	public float getCloudHeight() {
		return 192.0F;
	}
	
	@Override
	public boolean isSurfaceWorld() {
		return true;
	}
	
	@Override
	public BiomeProvider getBiomeProvider() {
		return new BiomeProviderSingle(InitBiomes.PLAINS);
	}
	
	@Override
	public double getHorizon() {
		return 128.0D;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorUniversal(world, world.getSeed(), ChunkGeneratorUniversal.getDefaultSettings());
	}

	@Override
	public DimensionType getDimensionType() {
		return DimensionType.getById(Reference.DIM_ID_PARANO);
	}
	
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return 0.0F;
	}

}
