package grimgar.core.handler;

import grimgar.client.gui.GuiGoblinPouch;
import grimgar.core.inventory.ContainerGoblinPouch;
import grimgar.core.inventory.InventoryGoblinPouch;
import grimgar.main.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID==Reference.GUI_ID_GOBLIN_POUCH) {
			return new ContainerGoblinPouch(player.inventory, new InventoryGoblinPouch(x==1 ? player.getHeldItemMainhand() : player.getHeldItemOffhand()));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID==Reference.GUI_ID_GOBLIN_POUCH) {
			return new GuiGoblinPouch(new ContainerGoblinPouch(player.inventory, new InventoryGoblinPouch(x==1 ? player.getHeldItemMainhand() : player.getHeldItemOffhand())));
		}
		return null;
	}

}
