package grimgar.core.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ISpell {
	
	public String getName();
	public boolean performAction(World world, EntityPlayer player);
	public boolean isValid(World world, EntityPlayer player);
	public Item heldItem();
	public boolean accessibleInCreative();
	
	@SideOnly(Side.CLIENT)
	public boolean performClientAction(World world, EntityPlayer player, Object... objects);
	
	default public String getSuccessNotificationKey() { return "spell."+getName()+".success" ; };
	
	@SideOnly(Side.CLIENT)
	default public String getFaliureNotificationKey() { return "spell."+getName()+".faliure" ; };

}
