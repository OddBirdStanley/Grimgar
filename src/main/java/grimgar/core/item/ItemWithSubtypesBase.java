package grimgar.core.item;

import grimgar.core.util.IItemHasSubtypes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemWithSubtypesBase extends ItemBase implements IItemHasSubtypes{
	
	private String registryName, unlocalizedName;
	private String[] registrySuffixes, unlocalizedSuffixes;
	private int metadataStart;
	
	private ItemWithSubtypesBase(String registryName, String unlocalizedName, String[] registrySuffixes, String[] unlocalizedSuffixes) {
		this(registryName, unlocalizedName, registrySuffixes, unlocalizedSuffixes, 0);
	}
	
	private ItemWithSubtypesBase(String registryName, String unlocalizedName, String[] registrySuffixes, String[] unlocalizedSuffixes, int metadataStart) {
		super(registryName, unlocalizedName);
		setHasSubtypes(true);
		this.registryName = registryName;
		this.unlocalizedName = unlocalizedName;
		this.registrySuffixes = registrySuffixes;
		this.unlocalizedSuffixes = unlocalizedSuffixes;
		this.metadataStart = metadataStart;
	}
	
	@Override
	public String getTranslationKey(ItemStack stack) {
		if(stack.getMetadata()<unlocalizedSuffixes.length && stack.getMetadata()>-1) {
			return unlocalizedName+unlocalizedSuffixes[stack.getMetadata()];
		}else {
			return unlocalizedName;
		}
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(isInCreativeTab(tab)) {
			for(int i = 0; i<unlocalizedSuffixes.length; i++) {
				items.add(new ItemStack(this, 1, i));
			}
		}
	}
	
	@Override
	public String[] getRegistrySuffixes() {
		return registrySuffixes;
	}

	@Override
	public String[] getUnlocalizedSuffixes() {
		return unlocalizedSuffixes;
	}

	@Override
	public int getMetadataStart() {
		return metadataStart;
	}
	
	public static class Factory{
		
		public static ItemWithSubtypesBase create(String registryName, String unlocalizedName, String[] registrySuffixes, String[] unlocalizedSuffixes) {
			if(registrySuffixes.length==unlocalizedSuffixes.length && registrySuffixes.length>1) {
				return new ItemWithSubtypesBase(registryName, unlocalizedName, registrySuffixes, unlocalizedSuffixes);
			}else {
				return null;
			}
		}
		
		public static ItemWithSubtypesBase create(String registryName, String unlocalizedName, String[] registrySuffixes, String[] unlocalizedSuffixes, int metadataStart) {
			if(registrySuffixes.length==unlocalizedSuffixes.length && registrySuffixes.length>1) {
				return new ItemWithSubtypesBase(registryName, unlocalizedName, registrySuffixes, unlocalizedSuffixes, metadataStart);
			}else {
				return null;
			}
		}
		
	}
	
}
