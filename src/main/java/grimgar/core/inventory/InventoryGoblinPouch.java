package grimgar.core.inventory;

import grimgar.core.item.ItemGoblinPouch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryGoblinPouch extends InventoryBasic{
	
	private ItemStack stack;

	public InventoryGoblinPouch(ItemStack stack) {
		super("OOPS", true, 5);
		this.stack = stack;
		fromNBT();
	}
	
	@Override
	public void closeInventory(EntityPlayer player) {
		toNBT();
		super.closeInventory(player);
	}
	
	private void toNBT() {
		NBTTagCompound tag = stack.getTagCompound();
		NBTTagList list = new NBTTagList();
		for(int i = 0; i<5; i++) {
			NBTTagCompound each = new NBTTagCompound();
			getStackInSlot(i).writeToNBT(each);
			list.appendTag(each);
		}
		tag.setTag("inv", list);
		stack.setTagCompound(tag);
	}
	
	private void fromNBT() {
		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		NBTTagCompound tag = stack.getTagCompound();
		if(tag.hasKey("inv") && tag.getTagList("inv", 10).tagCount()==5) {
			NBTTagList list = tag.getTagList("inv", 10);
			for(int i = 0; i<5; i++) {
				setInventorySlotContents(i, new ItemStack(list.getCompoundTagAt(i)));
			}
		}else {
			NBTTagCompound empty = new NBTTagCompound();
			NBTTagList list = new NBTTagList();
			for(int i = 0; i<5; i++) {
				NBTTagCompound each = new NBTTagCompound();
				ItemStack.EMPTY.writeToNBT(each);
				list.appendTag(each);
				setInventorySlotContents(i, ItemStack.EMPTY);
			}
			stack.setTagCompound(empty);
		}
	}
	
}