package grimgar.core.init;

import grimgar.core.world.biome.GrimgarBiomePlains;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class InitBiomes {
	
	public static final Biome PLAINS = new GrimgarBiomePlains();
	
	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		event.getRegistry().registerAll(PLAINS);
		BiomeDictionary.addTypes(PLAINS, BiomeDictionary.Type.PLAINS);
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(PLAINS, 1));
		BiomeManager.addSpawnBiome(PLAINS);
	}

}
