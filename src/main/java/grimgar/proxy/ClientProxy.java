package grimgar.proxy;

import grimgar.core.init.InitEntities;
import grimgar.core.init.InitFluids;
import grimgar.core.init.InitTileEntities;
import grimgar.core.test.TestRegistry;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
	
	public ClientProxy() {
		
	}
	
	@Override
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		InitFluids.registerRenderers();
		InitEntities.registerRenderers();
		InitTileEntities.registerRenderers();
		TestRegistry.registerRenderers();
	}
	
	@Override
	@EventHandler
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}
	
	@Override
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

}
