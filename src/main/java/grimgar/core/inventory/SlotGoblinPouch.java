package grimgar.core.inventory;

import grimgar.core.item.ItemGoblinPouch;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotGoblinPouch extends Slot{

	public SlotGoblinPouch(IInventory inventory, int index, int xPosition, int yPosition) {
		super(inventory, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return super.isItemValid(stack) && !(stack.getItem() instanceof ItemGoblinPouch);
	}

}
