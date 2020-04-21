package grimgar.core.init;

import grimgar.core.item.ItemBase;
import grimgar.core.item.ItemCanteen;
import grimgar.core.item.ItemFeatherPen;
import grimgar.core.item.ItemFoodBase;
import grimgar.core.item.ItemFoodWithSubtypesBase;
import grimgar.core.item.ItemGoblinPouch;
import grimgar.core.item.ItemPriestStaff;
import grimgar.core.item.ItemRecruitBadge;
import grimgar.core.item.ItemSteelIngot;
import grimgar.core.item.ItemSteelType;
import grimgar.core.item.ItemWeaponMeleeBase;
import grimgar.core.item.ItemWithSubtypesBase;
import grimgar.core.util.IItemHasSubtypes;
import grimgar.main.Reference;
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
	
	//Basic items
	public static final Item COPPER_COIN = new ItemBase("copper_coin", "copperCoin");
	public static final Item SILVER_COIN = new ItemBase("silver_coin", "silverCoin");
	public static final Item GOLD_COIN = new ItemBase("gold_coin", "gold_coin");
	public static final Item SKEWER = new ItemBase("skewer", "skewer");
	public static final Item PERFORATE_COIN = new ItemBase("perforate_coin", "perforateCoin");
	
	//Basic foods
	public static final Item DRIED_MEAT = new ItemFoodBase("dried_meat", "driedMeat", 4, 3.0F, false);
	
	//Basic melee weapons
	public static final Item LONG_SWORD = new ItemWeaponMeleeBase("long_sword", "longSword", ItemSteelType.STEEL, 7.0D, 10.0F, 0.6F);
	public static final Item MACHETE = new ItemWeaponMeleeBase("machete", "machete", ItemSteelType.STEEL, 3.0D, 5.0F, 1.4F);
	public static final Item DAGGER = new ItemWeaponMeleeBase("dagger", "dagger", ItemSteelType.STEEL, 1.5D, 1.0F, 1.6F);
	public static final Item SPEAR = new ItemWeaponMeleeBase("spear", "spear", ItemSteelType.STEEL, 4.5D, 5.0F, 0.8F);
	
	//Items with subtypes
	public static final Item COLORED_PEBBLE = null;
	public static final Item METAL_FRAGMENT = new ItemWithSubtypesBase("metal_fragment", "metalFragment", Reference.RS_METAL_FRAGMENT, Reference.US_METAL_FRAGMENT);
	public static final Item FANG = new ItemWithSubtypesBase("fang", "fang", Reference.RS_FANG, Reference.US_FANG);

	//Foods with subtypes
	public static final Item KEBAB = new ItemFoodWithSubtypesBase("kebab", "kebab", Reference.RS_KEBAB, Reference.US_KEBAB, Reference.HA_KEBAB, Reference.SA_KEBAB, false);
	public static final Item STEAMED_BUN = new ItemFoodWithSubtypesBase("steamed_bun", "steamedBun", Reference.RS_STEAMED_BUN, Reference.US_STEAMED_BUN, Reference.HA_STEAMED_BUN, Reference.SA_STEAMED_BUN, false);
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
	public static final Item CANTEEN = new ItemCanteen();
	
	public static Item[] items = new Item[] {METAL_FRAGMENT,FANG,KEBAB,STEAMED_BUN,COPPER_COIN,SILVER_COIN,GOLD_COIN,SKEWER,PERFORATE_COIN,DRIED_MEAT,LONG_SWORD,MACHETE,DAGGER,SPEAR,RECRUIT_BADGE,FEATHER_PEN,STEEL_INGOT,STEEL_AXE,STEEL_HOE,STEEL_PICKAXE,STEEL_SHOVEL,STEEL_SWORD,PRIEST_STAFF,GOBLIN_POUCH,CANTEEN};
	
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
