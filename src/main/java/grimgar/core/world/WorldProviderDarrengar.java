package grimgar.core.world;

import grimgar.client.renderer.sky.DarrengarSkyRenderer;
import grimgar.client.renderer.sky.GrimgarSkyRenderer;
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

public class WorldProviderDarrengar extends WorldProvider {
	
	public WorldProviderDarrengar() {
		
	}
	
	@Override
	protected void init() {
		setDimension(Reference.DIM_ID_DARRENGAR);
	}
	
	@Override
	public boolean canDropChunk(int x, int z) {
		return !this.world.isSpawnChunk(x, z);
	}
	
	@Override
	public IRenderHandler getSkyRenderer() {
		return new DarrengarSkyRenderer();
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
	public IRenderHandler getCloudRenderer() {
		return new IRenderHandler() {
			@Override
			public void render(float partialTicks, WorldClient world, Minecraft mc) {
			}
		};
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
	public DimensionType getDimensionType() {
		return DimensionType.getById(Reference.DIM_ID_DARRENGAR);
	}
	
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return 0.5F;
	}
	
	@Override
	protected void generateLightBrightnessTable() {
		super.generateLightBrightnessTable();
		for(int i = 0; i<lightBrightnessTable.length; i++) {
			lightBrightnessTable[i] *= 0.15F;
		}
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorUniversal(world, world.getSeed(), ChunkGeneratorUniversal.getDefaultSettings());
	}
	
}
