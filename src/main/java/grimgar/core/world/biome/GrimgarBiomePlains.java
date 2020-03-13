package grimgar.core.world.biome;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeDecorator;

public class GrimgarBiomePlains extends GrimgarBiomeBase{

	public GrimgarBiomePlains() {
		super(new BiomeProperties("plains")
				.setBaseHeight(0.1F)
				.setHeightVariation(0.005F)
				.setRainfall(0.5F)
				.setTemperature(0.8F),
				Block.getBlockFromName("grass").getDefaultState(),
				Block.getBlockFromName("dirt").getDefaultState(),
				"plains");

		decorator = new BiomeDecorator();
		decorator.treesPerChunk = 1;
	}

}
