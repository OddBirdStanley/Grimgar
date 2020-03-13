package grimgar.core.item;

import grimgar.main.Grimgar;
import grimgar.main.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;

public class ItemGoblinPouch extends ItemBase{
	
	public ItemGoblinPouch() {
		super("goblin_pouch", "goblinPouch");
		this.setMaxStackSize(1);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote && !player.isSneaking()) {
			player.openGui(Grimgar.instance, Reference.GUI_ID_GOBLIN_POUCH, world, hand==EnumHand.MAIN_HAND ? 1 : -1, 0, 0);
		}
		return super.onItemRightClick(world, player, hand);
	}
	
}
