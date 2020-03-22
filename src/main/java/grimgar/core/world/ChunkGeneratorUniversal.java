package grimgar.core.world;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

public class ChunkGeneratorUniversal implements IChunkGenerator {
	
	public World world;
	public long seed;
	public NoiseGeneratorOctaves minLimitPerlinNoise, maxLimitPerlinNoise, mainPerlinNoise, scaleNoise, depthNoise;
	public NoiseGeneratorPerlin surfaceNoise;
	public double[] mainNoiseRegion, minLimitRegion, maxLimitRegion, depthRegion, heightMap;
	public Biome[] biomes;
	public float[] biomeWeights = new float[25];
	public ChunkGeneratorSettings settings;
	public Random rand = new Random(seed);
	
	public static ChunkGeneratorSettings getDefaultSettings() {
		ChunkGeneratorSettings.Factory settings = new ChunkGeneratorSettings.Factory();
		settings.biomeDepthOffset = 4.0F;
		settings.seaLevel = 128;
		return settings.build();
	}

	public ChunkGeneratorUniversal(World world, long seed, ChunkGeneratorSettings settings) {
		this.world = world;
		this.seed = seed;
		this.settings = settings;
		minLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
        maxLimitPerlinNoise = new NoiseGeneratorOctaves(rand, 16);
        mainPerlinNoise = new NoiseGeneratorOctaves(rand, 8);
        surfaceNoise = new NoiseGeneratorPerlin(rand, 4);
        scaleNoise = new NoiseGeneratorOctaves(rand, 10);
        depthNoise = new NoiseGeneratorOctaves(rand, 16);
		for (int i = -2; i <= 2; ++i)
        {
            for (int j = -2; j <= 2; ++j)
            {
                float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
                this.biomeWeights[i+j*5+12] = f;
            }
        }
		heightMap = new double[825];
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		ChunkPrimer primer = new ChunkPrimer();
		//generate(x, 0, z, primer, settings);
		Chunk chunk = new Chunk(world, primer, x, z);
		chunk.generateSkylightMap();
		return chunk;
	}
	
	private void generate(int x, int y, int z, ChunkPrimer primer, ChunkGeneratorSettings settings) {
		biomes = world.getBiomeProvider().getBiomesForGeneration(biomes, x*4-2, z*4-2, 10, 10);
		x *= 4;
		z *= 4;
		depthRegion = depthNoise.generateNoiseOctaves(depthRegion, x, z, 5, 5, (double)settings.depthNoiseScaleX, (double)settings.depthNoiseScaleZ, (double)settings.depthNoiseScaleExponent);
        float f = settings.coordinateScale;
        float f1 = settings.heightScale;
        mainNoiseRegion = mainPerlinNoise.generateNoiseOctaves(mainNoiseRegion, x, y, z, 5, 33, 5, (double)(f / settings.mainNoiseScaleX), (double)(f1 / settings.mainNoiseScaleY), (double)(f / settings.mainNoiseScaleZ));
        minLimitRegion = minLimitPerlinNoise.generateNoiseOctaves(minLimitRegion, x, y, z, 5, 33, 5, (double)f, (double)f1, (double)f);
        maxLimitRegion = maxLimitPerlinNoise.generateNoiseOctaves(maxLimitRegion, x, y, z, 5, 33, 5, (double)f, (double)f1, (double)f);
        int i = 0;
        int j = 0;

        for (int k = 0; k < 5; ++k)
        {
            for (int l = 0; l < 5; ++l)
            {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                int i1 = 2;
                Biome biome = biomes[k + 2 + (l + 2) * 10];

                for (int j1 = -2; j1 <= 2; ++j1)
                {
                    for (int k1 = -2; k1 <= 2; ++k1)
                    {
                        Biome biome1 = biomes[k + j1 + 2 + (l + k1 + 2) * 10];
                        float f5 = settings.biomeDepthOffSet + biome1.getBaseHeight() * settings.biomeDepthWeight;
                        float f6 = settings.biomeScaleOffset + biome1.getHeightVariation() * settings.biomeScaleWeight;

                        float f7 = biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

                        if (biome1.getBaseHeight() > biome.getBaseHeight())
                        {
                            f7 /= 2.0F;
                        }

                        f2 += f6 * f7;
                        f3 += f5 * f7;
                        f4 += f7;
                    }
                }

                f2 = f2 / f4;
                f3 = f3 / f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4.0F - 1.0F) / 8.0F;
                double d7 = depthRegion[j] / 8000.0D;

                if (d7 < 0.0D)
                {
                    d7 = -d7 * 0.3D;
                }

                d7 = d7 * 3.0D - 2.0D;

                if (d7 < 0.0D)
                {
                    d7 = d7 / 2.0D;

                    if (d7 < -1.0D)
                    {
                        d7 = -1.0D;
                    }

                    d7 = d7 / 2.8D;
                }
                else
                {
                    if (d7 > 1.0D)
                    {
                        d7 = 1.0D;
                    }

                    d7 = d7 / 8.0D;
                }

                ++j;
                double d8 = (double)f3;
                double d9 = (double)f2;
                d8 = d8 + d7 * 0.2D;
                d8 = d8 * (double)settings.baseSize / 8.0D;
                double d0 = (double)settings.baseSize + d8 * 4.0D;

                for (int l1 = 0; l1 < 33; ++l1)
                {
                    double d1 = ((double)l1 - d0) * (double)settings.stretchY * 128.0D / 256.0D / d9;

                    if (d1 < 0.0D)
                    {
                        d1 *= 4.0D;
                    }

                    double d2 = minLimitRegion[i] / (double)settings.lowerLimitScale;
                    double d3 = maxLimitRegion[i] / (double)settings.upperLimitScale;
                    double d4 = (mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

                    if (l1 > 29)
                    {
                        double d6 = (double)((float)(l1 - 29) / 3.0F);
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }

                    heightMap[i] = d5;
                    ++i;
                }
            }
        }
        
        for (int m = 0; m < 4; ++m)
        {
            int n = m * 5;
            int k = (m + 1) * 5;

            for (int l = 0; l < 4; ++l)
            {
                int i1 = (n + l) * 33;
                int j1 = (n + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;

                for (int i2 = 0; i2 < 32; ++i2)
                {
                    double d0 = 0.125D;
                    double d1 = heightMap[i1 + i2];
                    double d2 = heightMap[j1 + i2];
                    double d3 = heightMap[k1 + i2];
                    double d4 = heightMap[l1 + i2];
                    double d5 = (heightMap[i1 + i2 + 1] - d1) * 0.125D;
                    double d6 = (heightMap[j1 + i2 + 1] - d2) * 0.125D;
                    double d7 = (heightMap[k1 + i2 + 1] - d3) * 0.125D;
                    double d8 = (heightMap[l1 + i2 + 1] - d4) * 0.125D;

                    for (int j2 = 0; j2 < 8; ++j2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.25D;
                        double d13 = (d4 - d2) * 0.25D;

                        for (int k2 = 0; k2 < 4; ++k2)
                        {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * 0.25D;
                            double lvt_45_1_ = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2)
                            {
                                if ((lvt_45_1_ += d16) > 0.0D)
                                {
                                    primer.setBlockState(m * 4 + k2, i2 * 8 + j2, l * 4 + l2, Blocks.STONE.getDefaultState());
                                }
                                else if (i2 * 8 + j2 < settings.seaLevel)
                                {
                                    primer.setBlockState(m * 4 + k2, i2 * 8 + j2, l * 4 + l2, Blocks.WATER.getDefaultState());
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
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
