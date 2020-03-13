package grimgar.main;

import grimgar.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, acceptedMinecraftVersions = Reference.ACCEPTED_MINECRAFT_VERSIONS, updateJSON = Reference.UPDATE_JSON)
public class Grimgar {
	
	@Instance
	public static Grimgar instance;
	
	@SidedProxy(modId = Reference.MOD_ID, serverSide = Reference.COMMON_PROXY, clientSide = Reference.CLIENT_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.init(event);
	}
	
	@EventHandler
	
	public static void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
	
}
