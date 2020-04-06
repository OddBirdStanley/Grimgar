package grimgar.main;

import javax.annotation.concurrent.Immutable;

import grimgar.core.creativetab.TabGrimgarBlocks;
import grimgar.core.creativetab.TabGrimgarEquipments;
import grimgar.core.creativetab.TabGrimgarFoods;
import grimgar.core.creativetab.TabGrimgarItems;
import net.minecraft.creativetab.CreativeTabs;

@Immutable 
public final class Reference {
	
	public static final String MOD_ID = "grimgar";
	public static final String NAME = "Grimgar";
	public static final String VERSION = "0.1.0";
	public static final String DEPENDENCIES = "required-after:forge@[0.0.0.0,);";
	public static final String ACCEPTED_MINECRAFT_VERSIONS = "[1.12,1.12.2]";
	public static final String UPDATE_JSON = "";
	
	public static final String API_VERSION = "0.1.0";
	public static final String API_OWNER = "grimgar";
	public static final String API_PROVIDES = "none";
	
	public static final String COMMON_PROXY = "grimgar.proxy.CommonProxy";
	public static final String CLIENT_PROXY = "grimgar.proxy.ClientProxy";
	
	public static final CreativeTabs GRIMGAR_BLOCKS = new TabGrimgarBlocks();
	public static final CreativeTabs GRIMGAR_ITEMS = new TabGrimgarItems();
	public static final CreativeTabs GRIMGAR_EQUIPMENTS = new TabGrimgarEquipments();
	public static final CreativeTabs GRIMGAR_FOODS = new TabGrimgarFoods();
	
	public static final int DIM_ID_GRIMGAR = 340;
	public static final int DIM_ID_DUSK = 341;
	public static final int DIM_ID_DARRENGAR = 342;
	public static final int DIM_ID_PARANO = 343;
	
	public static final int GUI_ID_GOBLIN_POUCH = 0;
	
	public static final int ENTITY_ID_GOBLIN = 0;
	public static final int ENTITY_ID_DEER = 1;
	public static final int ENTITY_ID_FOX = 2;
	public static final int ENTITY_ID_CHIMO = 3;
	public static final int ENTITY_ID_PIT_RAT = 4;
	public static final int ENTITY_ID_CROW = 5;
	public static final int ENTITY_ID_GNOME = 6;
	public static final int ENTITY_ID_DEAD_SPOTS = 7;
	public static final int ENTITY_ID_ORC = 8;
	public static final int ENTITY_ID_BEAR = 9;
	public static final int ENTITY_ID_HOGRAT = 10;
	public static final int ENTITY_ID_HOGWORM = 11;
	
	public static final String[] RS_METAL_FRAGMENT = new String[] {"iron","copper","tin","bronze","nickel","gold","silver","platnium","alloy","rust"};
	public static final String[] US_METAL_FRAGMENT = new String[] {"Iron","Copper","Tin","Bronze","Nickel","Gold","Silver","Platnium","Alloy","Rust"};
	public static final String[] RS_GEM = null;
	public static final String[] US_GEM = null;
	public static final String[] RS_FANG = new String[] {"small","goblin","wolf"};
	public static final String[] US_FANG = new String[] {"Small","Goblin","Wolf"};
	public static final String[] RS_STEAMED_BUN = new String[] {"plain","custard","sweet_bean","meat"};
	public static final String[] US_STEAMED_BUN = new String[] {"Plain","Custard","SweetBean","Meat"};
	public static final String[] RS_KEBAB = new String[] {"veggies","pork","chicken","beef","mutton","venison","exotic"};
	public static final String[] US_KEBAB = new String[] {"Veggies","Pork","Chicken","Beef","Mutton","Venison","Exotic"};
	public static final String[] RS_SANDWICH = null;
	public static final String[] US_SANDWICH = null;
	
	public static final int[] HA_STEAMED_BUN = new int[] {3,4,4,6};
	public static final int[] HA_KEBAB = new int[] {2,4,4,4,4,4,3};
	public static final int[] HA_SANDWICH = null;
	
	public static final float[] SA_STEAMED_BUN = new float[] {6.0F,6.0F,6.0F,6.0F};
	public static final float[] SA_KEBAB = new float[] {5.0F,7.0F,7.0F,7.0F,7.0F,7.0F,3.0F};
	public static final float[] SA_SANDWICH = null;
	
}
