package grimgar.core.test;

import grimgar.main.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class TestRegistry {
	
	public static final Block RUBICS_CUBE = new BlockRubicsCube();
	public static final Item RUBICS_CUBE_ITEM = new ItemBlock(RUBICS_CUBE).setRegistryName(Reference.MOD_ID, "rubics_cube").setMaxStackSize(1);
	
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityRubicsCube.class, new ResourceLocation("grimgar", "rubics_cube"));
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRubicsCube.class, new TileEntityRubicsCubeRenderer());
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerRenderers(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(RUBICS_CUBE_ITEM, 0, new ModelResourceLocation(RUBICS_CUBE_ITEM.getRegistryName(), "inventory"));
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(RUBICS_CUBE);
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(RUBICS_CUBE_ITEM);
	}

}
