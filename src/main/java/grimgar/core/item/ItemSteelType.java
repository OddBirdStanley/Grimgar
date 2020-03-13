package grimgar.core.item;

import grimgar.main.Reference;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ItemSteelType{
	
	private static final String MOD_ID = Reference.MOD_ID;
	
	public static final ToolMaterial STEEL = EnumHelper.addToolMaterial("STEEL", 2, 475, 7.0F, 2.25F, 16);
	
	public static class ItemSteelSword extends ItemSword{

		public ItemSteelSword() {
			super(STEEL);
			this.setTranslationKey("steelSword");
			this.setRegistryName(MOD_ID,"steel_sword");
			this.setCreativeTab(Reference.GRIMGAR_EQUIPMENTS);
		}
		
	}
	
	public static class ItemSteelAxe extends ItemAxe{

		public ItemSteelAxe() {
			super(STEEL,9.0F,-3.3F);
			this.setTranslationKey("steelAxe");
			this.setRegistryName(MOD_ID,"steel_axe");
			this.setCreativeTab(Reference.GRIMGAR_EQUIPMENTS);
		}
		
	}
	
	public static class ItemSteelShovel extends ItemSpade{

		public ItemSteelShovel() {
			super(STEEL);
			this.setTranslationKey("steelShovel");
			this.setRegistryName(MOD_ID,"steel_shovel");
			this.setCreativeTab(Reference.GRIMGAR_EQUIPMENTS);
		}
		
	}
	
	public static class ItemSteelPickaxe extends ItemPickaxe{

		public ItemSteelPickaxe() {
			super(STEEL);
			this.setTranslationKey("steelPickaxe");
			this.setRegistryName(MOD_ID,"steel_pickaxe");
			this.setCreativeTab(Reference.GRIMGAR_EQUIPMENTS);
		}
		
	}
	
	public static class ItemSteelHoe extends ItemHoe{

		public ItemSteelHoe() {
			super(STEEL);
			this.setTranslationKey("steelHoe");
			this.setRegistryName(MOD_ID,"steel_hoe");
			this.setCreativeTab(Reference.GRIMGAR_EQUIPMENTS);
		}
		
	}

}
