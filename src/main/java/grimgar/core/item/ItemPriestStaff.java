package grimgar.core.item;

import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemPriestStaff extends ItemBase {
	
	public static final float HEAL = 5.0F;
	public static final double DISTANCE = 10.0D;
	public static final float DAMAGE = 5.0F;
	public static final int COST = 5;

	public ItemPriestStaff() {
		super("priest_staff", "priestStaff");
		this.setMaxStackSize(1);
		this.setMaxDamage(20);
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState block) {
		return false;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return false;
	}
	
	@Override
	public boolean isRepairable() {
		return false;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(world.rand.nextInt(600)==0 && stack.getItemDamage()>0) {
			stack.setItemDamage(stack.getItemDamage()-1);
		}
	}
	
}
