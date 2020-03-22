package grimgar.core.world.biome;

import grimgar.main.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class GrimgarBiomeBase extends Biome{
	
	private static final String MOD_ID = Reference.MOD_ID;
	private BiomeProperties properties;

	public GrimgarBiomeBase(BiomeProperties properties, IBlockState topBlock, IBlockState fillerBlock, String registryName) {
		super(properties);
		setRegistryName(MOD_ID, registryName);
		
		this.properties = properties;
		this.topBlock = topBlock;
		this.fillerBlock = fillerBlock;
		
		spawnableCaveCreatureList.clear();
		spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableWaterCreatureList.clear();
		modSpawnableLists.clear();
	}
	
	
	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new GrimgarDecorator();
	}

}
