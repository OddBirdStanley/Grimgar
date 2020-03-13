package grimgar.core.creativetab;

import grimgar.core.init.InitItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TabGrimgarBlocks extends CreativeTabs{

	public TabGrimgarBlocks() {
		super("grimgarBlocks");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemStack createIcon() {
		return new ItemStack(InitItems.RECRUIT_BADGE);
	}

}
