package grimgar.core.test;

import grimgar.main.Reference;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class ItemLevitationBase extends Item {

	public ItemLevitationBase(String registryName, String unlocalizedName) {
		this.setTranslationKey(unlocalizedName);
		this.setRegistryName(Reference.MOD_ID,registryName);
		this.setCreativeTab(Reference.GRIMGAR_ITEMS);
	}
	
	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		if (!entityItem.hasNoGravity()){
			IBlockState current = entityItem.world.getBlockState(new BlockPos(MathHelper.floor(entityItem.posX),MathHelper.floor(entityItem.posY),MathHelper.floor(entityItem.posZ)));
			if(current.getMaterial()==Material.LAVA || current.getMaterial()==Material.WATER) {
				entityItem.motionY += 0.04499999910593033D;
			}else if(current.getBlock() instanceof BlockAir) {
				entityItem.motionY += 0.04999999910593033D;
			}else {
				entityItem.motionY += 0.03999999910593033D;
				//TODO: Prevent from being stuck? ...
			}
			if(entityItem.motionY>0.15D) { 
				entityItem.motionY = 0.15D;
			}
        }
		return false;
	}

}
