package grimgar.core.fluid;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidBeer extends Fluid {

	public FluidBeer() {
		super("beer", new ResourceLocation("grimgar", "blocks/beer_still"), new ResourceLocation("grimgar", "blocks/beer_flowing"), new ResourceLocation("grimgar", "blocks/beer_overlay"), 0xFFEE34);
		setDensity(1200);
		setEmptySound(SoundEvents.ITEM_BUCKET_EMPTY);
		setFillSound(SoundEvents.ITEM_BUCKET_FILL);
		setViscosity(1200);
	}

}
