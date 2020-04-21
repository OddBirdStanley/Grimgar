package grimgar.core.fluid;

import grimgar.core.init.InitFluids;
import grimgar.main.Reference;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFluidBeer extends BlockFluidClassic {

	public BlockFluidBeer() {
		super(InitFluids.BEER, new MaterialLiquid(MapColor.getBlockColor(EnumDyeColor.YELLOW)));
		setTranslationKey("beer");
		setRegistryName(Reference.MOD_ID, "beer");
	}

}
