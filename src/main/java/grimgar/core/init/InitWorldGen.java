package grimgar.core.init;

import grimgar.core.world.WorldProviderGrimgar;
import grimgar.main.Reference;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class InitWorldGen {

	public InitWorldGen() {
		
	}
	
	public static void register() {
		DimensionType.register("grimgar", "_grimgar", Reference.DIM_ID_GRIMGAR, WorldProviderGrimgar.class, false);
		DimensionManager.registerDimension(Reference.DIM_ID_GRIMGAR, DimensionType.getById(Reference.DIM_ID_GRIMGAR));
	}

}
