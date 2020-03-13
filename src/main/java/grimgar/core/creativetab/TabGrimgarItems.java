package grimgar.core.creativetab;

import grimgar.core.init.InitItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TabGrimgarItems extends CreativeTabs{

	public TabGrimgarItems() {
		super("grimgarItems");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack createIcon() {
		return new ItemStack(InitItems.RECRUIT_BADGE);
	}

}
