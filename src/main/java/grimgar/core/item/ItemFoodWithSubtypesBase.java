package grimgar.core.item;

import grimgar.core.util.IItemHasSubtypes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemFoodWithSubtypesBase extends ItemFoodBase implements IItemHasSubtypes{
	
	private String registryName, unlocalizedName;
	private String[] registrySuffixes, unlocalizedSuffixes;
	private int[] amount;
	private float[] saturation;
	private int metadataStart;
	
	private ItemFoodWithSubtypesBase(String registryName, String unlocalizedName, String[] registrySuffixes, String[] unlocalizedSuffixes, int[] amount, float[] saturation, boolean isSnack) {
		this(registryName, unlocalizedName, registrySuffixes, unlocalizedSuffixes, amount, saturation, isSnack, 0);
	}
	
	private ItemFoodWithSubtypesBase(String registryName, String unlocalizedName, String[] registrySuffixes, String[] unlocalizedSuffixes, int[] amount, float[] saturation, boolean isSnack, int metadataStart) {
		super(registryName, unlocalizedName, amount[0], saturation[0], isSnack);
		setHasSubtypes(true);
		this.registryName = registryName;
		this.unlocalizedName = unlocalizedName;
		this.registrySuffixes = registrySuffixes;
		this.unlocalizedSuffixes = unlocalizedSuffixes;
		this.metadataStart = metadataStart;
		this.amount = amount;
		this.saturation = saturation;
	}
	
	@Override
	public int getHealAmount(ItemStack stack) {
		return (stack.getMetadata()<unlocalizedSuffixes.length && stack.getMetadata()>-1) ? amount[stack.getMetadata()] : 0;
	}
	
	@Override
	public float getSaturationModifier(ItemStack stack) {
		return (stack.getMetadata()<unlocalizedSuffixes.length && stack.getMetadata()>-1) ? saturation[stack.getMetadata()] : 0.0F;
	}
	
	@Override
	public String getTranslationKey(ItemStack stack) {
		return (stack.getMetadata()<unlocalizedSuffixes.length && stack.getMetadata()>-1) ? "item."+unlocalizedName+unlocalizedSuffixes[stack.getMetadata()] : "item."+unlocalizedName;
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
		
		public static ItemFoodWithSubtypesBase create(String registryName, String unlocalizedName, String[] registrySuffixes, String[] unlocalizedSuffixes, int[] amount, float[] saturation, boolean isSnack) {
			return (registrySuffixes.length==unlocalizedSuffixes.length && registrySuffixes.length>1 && registrySuffixes.length==amount.length && registrySuffixes.length==saturation.length) ? new ItemFoodWithSubtypesBase(registryName, unlocalizedName, registrySuffixes, unlocalizedSuffixes, amount, saturation, isSnack, 0) : null;
		}
		
		public static ItemFoodWithSubtypesBase create(String registryName, String unlocalizedName, String[] registrySuffixes, String[] unlocalizedSuffixes, int[] amount, float saturation[], boolean isSnack, int metadataStart) {
			return (registrySuffixes.length==unlocalizedSuffixes.length && registrySuffixes.length>1 && registrySuffixes.length==amount.length && registrySuffixes.length==saturation.length) ? new ItemFoodWithSubtypesBase(registryName, unlocalizedName, registrySuffixes, unlocalizedSuffixes, amount, saturation, isSnack, metadataStart) : null;
		}
		
	}

}
