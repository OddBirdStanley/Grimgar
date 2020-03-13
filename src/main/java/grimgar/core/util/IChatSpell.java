package grimgar.core.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IChatSpell {
	
	public String getName();
	public String getSpell();
	public boolean performAction(World world, EntityPlayer player);
	public String getSuccessNotification();
	public boolean isValid(World world, EntityPlayer player);
	public Item heldItem();
	public boolean accessibleInCreative();
	
	@SideOnly(Side.CLIENT)
	public boolean performClientAction(World world, EntityPlayer player, Object... objects);
	
	@SideOnly(Side.CLIENT)
	public String getFaliureNotification();

}
