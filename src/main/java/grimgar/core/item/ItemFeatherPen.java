package grimgar.core.item;

import grimgar.core.init.InitBiomes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class ItemFeatherPen extends ItemBase{

	public ItemFeatherPen() {
		super("feather_pen","featherPen");
		setMaxStackSize(64);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote) {
			//player.changeDimension(340);
		}
		return super.onItemRightClick(world, player, hand);
	}

}
