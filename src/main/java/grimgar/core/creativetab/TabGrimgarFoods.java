package grimgar.core.creativetab;

import grimgar.core.init.InitItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabGrimgarFoods extends CreativeTabs {
	
	public TabGrimgarFoods() {
		super("grimgarFoods");
	}
	
	@Override
	public ItemStack createIcon() {
		return new ItemStack(InitItems.RECRUIT_BADGE);
	}

}
