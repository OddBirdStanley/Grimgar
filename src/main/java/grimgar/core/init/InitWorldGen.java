package grimgar.core.init;

import grimgar.core.world.WorldProviderDarrengar;
import grimgar.core.world.WorldProviderDusk;
import grimgar.core.world.WorldProviderGrimgar;
import grimgar.core.world.WorldProviderParano;
import grimgar.main.Reference;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class InitWorldGen {

	public InitWorldGen() {
		
	}
	
	public static void register() {
		DimensionType.register("grimgar", "_grimgar", Reference.DIM_ID_GRIMGAR, WorldProviderGrimgar.class, false);
		DimensionType.register("dusk", "_dusk", Reference.DIM_ID_DUSK, WorldProviderDusk.class, false);
		DimensionType.register("darrengar", "_darrengar", Reference.DIM_ID_DARRENGAR, WorldProviderDarrengar.class, false);
		DimensionType.register("parano", "_parano", Reference.DIM_ID_PARANO, WorldProviderParano.class, false);
		
		DimensionManager.registerDimension(Reference.DIM_ID_GRIMGAR, DimensionType.getById(Reference.DIM_ID_GRIMGAR));
		DimensionManager.registerDimension(Reference.DIM_ID_DUSK, DimensionType.getById(Reference.DIM_ID_DUSK));
		DimensionManager.registerDimension(Reference.DIM_ID_DARRENGAR, DimensionType.getById(Reference.DIM_ID_DARRENGAR));
		DimensionManager.registerDimension(Reference.DIM_ID_PARANO, DimensionType.getById(Reference.DIM_ID_PARANO));
	}

}
