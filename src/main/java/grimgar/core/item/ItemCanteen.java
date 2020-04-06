package grimgar.core.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCanteen extends ItemBase {
	
	public ItemCanteen() {
		super("canteen", "canteen");
		setMaxStackSize(1);
		setMaxDamage(20);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving) {
		stack.setItemDamage(stack.getItemDamage()-1);
		return stack;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 20;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

}
