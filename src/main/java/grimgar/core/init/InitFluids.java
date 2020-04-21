package grimgar.core.init;

import java.util.Map;

import grimgar.core.fluid.BlockFluidBeer;
import grimgar.core.fluid.BlockFluidMilk;
import grimgar.core.fluid.FluidBeer;
import grimgar.core.fluid.FluidMilk;
import grimgar.main.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class InitFluids {
	
	public static final Fluid MILK = new FluidMilk();
	public static final Fluid BEER = new FluidBeer();
	
	public static Block MILK_BLOCK;
	public static Block BEER_BLOCK;
	
	public static Item MILK_ITEM;
	public static Item BEER_ITEM;
	
	public static ModelResourceLocation modelMilk = new ModelResourceLocation("grimgar:milk","normal");
	public static ModelResourceLocation modelBeer = new ModelResourceLocation("grimgar:beer","normal");
	
	public static void registerFluids() {
		FluidRegistry.registerFluid(MILK);
		FluidRegistry.registerFluid(BEER);
		
		if(FluidRegistry.addBucketForFluid(MILK)) grimgar.main.Logger.info("registered milk bucket");
		if(FluidRegistry.addBucketForFluid(BEER)) grimgar.main.Logger.info("registered beer bucket");
		
		MILK_BLOCK = new BlockFluidMilk();
		BEER_BLOCK = new BlockFluidBeer();
		
		MILK_ITEM = new ItemBlock(MILK_BLOCK).setRegistryName(Reference.MOD_ID, "milk").setMaxStackSize(64);
		BEER_ITEM = new ItemBlock(BEER_BLOCK).setRegistryName(Reference.MOD_ID, "beer").setMaxStackSize(64);
	}
	
	@SubscribeEvent
	public static void registerFluidBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(MILK_BLOCK, BEER_BLOCK);
	}
	
	@SubscribeEvent
	public static void registerFluidItemBlocks(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(MILK_ITEM, BEER_ITEM);
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void registerStateMappers(ModelRegistryEvent event) {
		ModelLoader.setCustomMeshDefinition(MILK_ITEM, new  ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return modelMilk;
			}
		});
		ModelLoader.setCustomMeshDefinition(BEER_ITEM, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				return modelBeer;
			}
		});
		ModelLoader.setCustomStateMapper(MILK_BLOCK, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return modelMilk;
			}
		});
		ModelLoader.setCustomStateMapper(BEER_BLOCK, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return modelBeer;
			}
		});
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRenderers() {
		ModelResourceLocation modelMilk = new ModelResourceLocation("grimgar:milk");
		ModelResourceLocation modelBeer = new ModelResourceLocation("grimgar:beer");
		ModelBakery.registerItemVariants(MILK_ITEM, modelMilk);
		ModelBakery.registerItemVariants(BEER_ITEM, modelBeer);
	}

}
