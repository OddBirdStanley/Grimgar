package grimgar.core.fluid;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidMilk extends Fluid{

	public FluidMilk() {
		super("milk", new ResourceLocation("grimgar", "blocks/milk_still"), new ResourceLocation("grimgar", "blocks/milk_flowing"), new ResourceLocation("grimgar", "blocks/milk_overlay"), 0xFFFFFF);
		setDensity(1200);
		setEmptySound(SoundEvents.ITEM_BUCKET_EMPTY);
		setFillSound(SoundEvents.ITEM_BUCKET_FILL);
		setViscosity(1200);
	}

}
