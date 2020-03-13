package grimgar.core.inventory;

import grimgar.core.item.ItemGoblinPouch;
import grimgar.core.inventory.SlotGoblinPouch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGoblinPouch extends Container{
	
	private InventoryPlayer playerInv;
	private InventoryGoblinPouch goblinPouchInv;
	
	public ContainerGoblinPouch(InventoryPlayer playerInv, InventoryGoblinPouch goblinPouchInv) {
		this.playerInv = playerInv;
		this.goblinPouchInv = goblinPouchInv;
		
		for(int i = 0; i<5; i++) {
			addSlotToContainer(new SlotGoblinPouch(goblinPouchInv, i, 18*i+44, 20));
		}
		
		for(int i = 0; i<3; i++) {
			for(int j =0; j<9; j++) {
				addSlotToContainer(new Slot(playerInv, j+i*9+9, 18*j+8, 18*i+51));
			}
		}
		
		for(int i = 0; i<9; i++) {
			addSlotToContainer(new Slot(playerInv, i, 18*i+8, 109));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack() && !(slot.getStack().getItem() instanceof ItemGoblinPouch))
        {
            ItemStack itemStackNew = slot.getStack();
            itemStack = itemStackNew.copy();
            if (index < 5)
            {
                if (!this.mergeItemStack(itemStackNew, 5, 41, true))
                {
                    return ItemStack.EMPTY;
                }
            }else {
                if (!this.mergeItemStack(itemStackNew, 0, 5, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            if (itemStackNew.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        return itemStack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		playerInv.closeInventory(player);
		goblinPouchInv.closeInventory(player);
	}
	
}