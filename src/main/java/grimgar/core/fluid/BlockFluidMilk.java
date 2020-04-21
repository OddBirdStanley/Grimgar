package grimgar.core.fluid;

import grimgar.core.init.InitFluids;
import grimgar.main.Reference;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFluidMilk extends BlockFluidClassic{

	public BlockFluidMilk() {
		super(InitFluids.MILK, new MaterialLiquid(MapColor.getBlockColor(EnumDyeColor.WHITE)));
		setTranslationKey("milk");
		setRegistryName(Reference.MOD_ID, "milk");
	}

}
