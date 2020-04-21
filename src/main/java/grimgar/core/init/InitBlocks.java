package grimgar.core.init;

import grimgar.core.block.BlockCandle;
import grimgar.core.block.BlockWoodenMug;
import grimgar.main.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class InitBlocks {
	
	public static final Block CANDLE = new BlockCandle().setRegistryName(Reference.MOD_ID,"candle");
	public static final Block LIT_CANDLE = new BlockCandle().setRegistryName(Reference.MOD_ID,"lit_candle").setLightLevel(0.75F);
	public static final Block MUD = new BlockCandle().setRegistryName(Reference.MOD_ID,"mud");
	
	public static final Item CANDLE_ITEM = new ItemBlock(CANDLE).setRegistryName(Reference.MOD_ID,"candle").setMaxStackSize(16);
	public static final Item MUD_ITEM = new ItemBlock(MUD).setRegistryName(Reference.MOD_ID,"mud").setMaxStackSize(64);
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		
		event.getRegistry().registerAll(CANDLE,LIT_CANDLE);
		
	}
	
	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {

		event.getRegistry().registerAll(CANDLE_ITEM);
		
		
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerRenderers(ModelRegistryEvent event) {

		ModelLoader.setCustomModelResourceLocation(CANDLE_ITEM, 0, new ModelResourceLocation(CANDLE_ITEM.getRegistryName(), "inventory"));
		
	}
	
}
