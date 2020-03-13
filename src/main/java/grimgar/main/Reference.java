package grimgar.main;

import grimgar.core.creativetab.*;
import net.minecraft.creativetab.CreativeTabs;

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

	public static final String[] COLORED_PEBBLE_NAMES = new String[] {"Red","Yellow","Green","Blue","Grey"};
	
	public static final CreativeTabs GRIMGAR_BLOCKS = new TabGrimgarBlocks();
	public static final CreativeTabs GRIMGAR_ITEMS = new TabGrimgarItems();
	public static final CreativeTabs GRIMGAR_EQUIPMENTS = new TabGrimgarEquipments();
	public static final CreativeTabs GRIMGAR_FOODS = new TabGrimgarFoods();
	
	public static final int DIM_ID_GRIMGAR = 340;
	
	public static final int GUI_ID_GOBLIN_POUCH = 0;
	
	public static final int ENTITY_ID_GOBLIN = 0;
	
}
