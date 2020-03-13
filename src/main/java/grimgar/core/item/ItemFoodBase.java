package grimgar.core.item;

import grimgar.main.Reference;
import net.minecraft.item.ItemFood;

public class ItemFoodBase extends ItemFood {

	public ItemFoodBase(String registryName, String unlocalizedName, int amount, float saturation, boolean isSnack) {
		super(amount, saturation, false);
		if(isSnack) {
			setAlwaysEdible();
		}
		setTranslationKey(unlocalizedName);
		setRegistryName(Reference.MOD_ID,registryName);
		setCreativeTab(Reference.GRIMGAR_FOODS);
		setMaxStackSize(64);
	}

}
