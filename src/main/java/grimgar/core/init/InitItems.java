package grimgar.core.init;

import grimgar.core.item.*;
import grimgar.core.util.GrimgarModException;
import grimgar.core.util.IItemHasSubtypes;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class InitItems{
	
	//Experimental
	public static final Item LEVITATION_CORE = new grimgar.core.test.ItemLevitation("levitation_core", "levitationCore");
	
	//Basic items
	public static final Item COPPER_COIN = new ItemBase("copper_coin", "copperCoin");
	public static final Item SILVER_COIN = new ItemBase("silver_coin", "silverCoin");
	public static final Item GOLD_COIN = new ItemBase("gold_coin", "gold_coin");
	public static final Item SKEWER = new ItemBase("skewer", "skewer");
	public static final Item PERFORATE_COIN = new ItemBase("perforate_coin", "perforateCoin");
	
	//Basic foods
	public static final Item LEMONADE = new ItemFoodBase("lemonade", "lemonade", 2, 10.0F, true);
	public static final Item DRIED_MEAT = new ItemFoodBase("dried_meat", "driedMeat", 4, 4.0F, false);
	
	//Basic melee weapons
	public static final Item LONG_SWORD = new ItemWeaponMeleeBase("long_sword", "longSword", ItemSteelType.STEEL, 7.0D, 10.0F, 0.6F);
	public static final Item MACHETE = new ItemWeaponMeleeBase("machete", "machete", ItemSteelType.STEEL, 3.0D, 5.0F, 1.4F);
	public static final Item DAGGER = new ItemWeaponMeleeBase("dagger", "dagger", ItemSteelType.STEEL, 1.5D, 1.0F, 1.6F);
	public static final Item SPEAR = new ItemWeaponMeleeBase("spear", "spear", ItemSteelType.STEEL, 4.5D, 5.0F, 0.8F);
	
	//Items with subtypes
	public static final Item COLORED_PEBBLE = null;
	public static final Item METAL_FRAGMENT = null;
	public static final Item FANG = null;

	//Foods with subtypes
	public static final Item KEBAB = null;
	public static final Item STEAMED_BUN = null;
	public static final Item SANDWICH = null;
	
	//General
	public static final Item RECRUIT_BADGE = new ItemRecruitBadge();
	public static final Item FEATHER_PEN = new ItemFeatherPen();
	public static final Item STEEL_INGOT = new ItemSteelIngot();
	public static final Item STEEL_AXE = new ItemSteelType.ItemSteelAxe();
	public static final Item STEEL_HOE = new ItemSteelType.ItemSteelHoe();
	public static final Item STEEL_PICKAXE = new ItemSteelType.ItemSteelPickaxe();
	public static final Item STEEL_SHOVEL = new ItemSteelType.ItemSteelShovel();
	public static final Item STEEL_SWORD = new ItemSteelType.ItemSteelSword();
	public static final Item PRIEST_STAFF = new ItemPriestStaff();
	public static final Item GOBLIN_POUCH = new ItemGoblinPouch();
	
	public static Item[] items = new Item[] {LEVITATION_CORE,COPPER_COIN,SILVER_COIN,GOLD_COIN,SKEWER,PERFORATE_COIN,LEMONADE,DRIED_MEAT,LONG_SWORD,MACHETE,DAGGER,SPEAR,RECRUIT_BADGE,FEATHER_PEN,STEEL_INGOT,STEEL_AXE,STEEL_HOE,STEEL_PICKAXE,STEEL_SHOVEL,STEEL_SWORD,PRIEST_STAFF,GOBLIN_POUCH};
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event){
		
		for(Item each : items) {
			event.getRegistry().register(each);
		}
		
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerRenderers(ModelRegistryEvent event) {

		for(Item each : items) {
			if(each instanceof IItemHasSubtypes) {
				IItemHasSubtypes iihs = (IItemHasSubtypes) each;
				for(int i = iihs.getMetadataStart(); i<iihs.getRegistrySuffixes().length; i++) {
					ModelLoader.setCustomModelResourceLocation(each, i, new ModelResourceLocation(each.getRegistryName()+"_"+iihs.getRegistrySuffixes()[i], "inventory"));
				}
			}else {
				ModelLoader.setCustomModelResourceLocation(each, 0, new ModelResourceLocation(each.getRegistryName(), "inventory"));
			}
		}
		
	}
	
}
