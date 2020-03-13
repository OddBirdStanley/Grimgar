package grimgar.core.item;

import grimgar.main.Reference;
import net.minecraft.item.Item;

public class ItemBase extends Item{
	
	public ItemBase(String registryName, String unlocalizedName){
		
		setTranslationKey(unlocalizedName);
		setRegistryName(Reference.MOD_ID,registryName);
		setCreativeTab(Reference.GRIMGAR_ITEMS);
		setMaxStackSize(64);
		
	}
	
}
