package grimgar.core.item;

import grimgar.core.init.InitBiomes;
import grimgar.core.world.teleporter.UniversalTeleporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.util.ITeleporter;

public class ItemFeatherPen extends ItemBase{
	
	public static UniversalTeleporter teleporter = null;

	public ItemFeatherPen() {
		super("feather_pen","featherPen");
		setMaxStackSize(64);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote) {
			WorldServer worldServer = world.getMinecraftServer().getWorld(340);
			if(teleporter==null) {
				teleporter = new UniversalTeleporter(worldServer);
			}
			player.changeDimension(340, teleporter);
		}
		return super.onItemRightClick(world, player, hand);
	}

}
