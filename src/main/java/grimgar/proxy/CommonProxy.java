package grimgar.proxy;

import grimgar.client.handler.ChatSpellHandler;
import grimgar.core.handler.GuiHandler;
import grimgar.core.handler.NetworkHandler;
import grimgar.core.init.InitCapabilities;
import grimgar.core.init.InitEntities;
import grimgar.core.init.InitFluids;
import grimgar.core.init.InitTileEntities;
import grimgar.core.init.InitWorldGen;
import grimgar.core.spell.SpellHeal;
import grimgar.core.test.TestRegistry;
import grimgar.main.Grimgar;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy{
	
	public CommonProxy() {
		
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		InitCapabilities.registerCapabilities();
		InitFluids.registerFluids();
		NetworkHandler.registerMessages();
		NetworkRegistry.INSTANCE.registerGuiHandler(Grimgar.instance, new GuiHandler());
		InitEntities.registerEntities();
		InitWorldGen.register();
		InitTileEntities.registerTileEntities();
		TestRegistry.registerTileEntities();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		ChatSpellHandler.addSpell(new SpellHeal());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}

}
