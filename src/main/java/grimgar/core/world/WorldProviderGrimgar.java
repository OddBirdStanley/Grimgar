package grimgar.core.world;

import grimgar.client.renderer.sky.GrimgarSkyRenderer;
import grimgar.core.init.InitBiomes;
import grimgar.main.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;

public class WorldProviderGrimgar extends WorldProvider{

	public WorldProviderGrimgar() {
		
	}
	
	@Override
	protected void init() {
		hasSkyLight = true;
		setDimension(Reference.DIM_ID_GRIMGAR);
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		//For the time being resort to the vanilla generator
		//return new ChunkGeneratorGrimgar(world);
		return new net.minecraft.world.gen.ChunkGeneratorOverworld(world, world.getSeed(), false, "");
	}

	@Override
	public DimensionType getDimensionType() {
		return DimensionType.getById(Reference.DIM_ID_GRIMGAR);
	}
	
	@Override
	public IRenderHandler getSkyRenderer() {
		return new GrimgarSkyRenderer();
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
	public boolean isSurfaceWorld() {
		return true;
	}
	
	@Override
	public BiomeProvider getBiomeProvider() {
		return new BiomeProviderSingle(InitBiomes.PLAINS);
	}
	
	//START TEST GROUND
	
	@Override
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
		return super.calcSunriseSunsetColors(celestialAngle, partialTicks);
	}
	
	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return super.calculateCelestialAngle(worldTime, partialTicks);
	}
	
	@Override
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
		return super.getFogColor(p_76562_1_, p_76562_2_);
	}
	
	@Override
	protected void generateLightBrightnessTable() {
		super.generateLightBrightnessTable();
	}
	
	@Override
	public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
		return super.getSkyColor(cameraEntity, partialTicks);
	}
	
	@Override
	public double getHorizon() {
		return super.getHorizon();
	}
	
}
