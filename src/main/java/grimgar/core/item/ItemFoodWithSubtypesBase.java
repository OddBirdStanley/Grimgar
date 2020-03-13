package grimgar.core.item;

import grimgar.core.util.IItemHasSubtypes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemFoodWithSubtypesBase extends ItemFoodBase implements IItemHasSubtypes{
	
	private String registryName, unlocalizedName;
	private String[] registrySuffixes, unlocalizedSuffixes;
	private int metadataStart;
	
	private ItemFoodWithSubtypesBase(String registryName, String unlocalizedName, String[] registrySuffixes, String[] unlocalizedSuffixes, int amount, float saturation, boolean isSnack) {
		this(registryName, unlocalizedName, registrySuffixes, unlocalizedSuffixes, amount, saturation, isSnack, 0);
	}
	
	private ItemFoodWithSubtypesBase(String registryName, String unlocalizedName, String[] registrySuffixes, String[] unlocalizedSuffixes, int amount, float saturation, boolean isSnack, int metadataStart) {
		super(registryName, unlocalizedName, amount, saturation, isSnack);
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
		
		public static ItemFoodWithSubtypesBase create(String registryName, String unlocalizedName, String[] registrySuffixes, String[] unlocalizedSuffixes, int amount, float saturation, boolean isSnack) {
			if(registrySuffixes.length==unlocalizedSuffixes.length && registrySuffixes.length>1) {
				return new ItemFoodWithSubtypesBase(registryName, unlocalizedName, registrySuffixes, unlocalizedSuffixes, amount, saturation, isSnack);
			}else {
				return null;
			}
		}
		
		public static ItemFoodWithSubtypesBase create(String registryName, String unlocalizedName, String[] registrySuffixes, String[] unlocalizedSuffixes, int amount, float saturation, boolean isSnack, int metadataStart) {
			if(registrySuffixes.length==unlocalizedSuffixes.length && registrySuffixes.length>1) {
				return new ItemFoodWithSubtypesBase(registryName, unlocalizedName, registrySuffixes, unlocalizedSuffixes, amount, saturation, isSnack, metadataStart);
			}else {
				return null;
			}
		}
		
	}

}
