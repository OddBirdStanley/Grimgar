package grimgar.core.block;

import grimgar.main.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMud extends Block {
	
	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
	
	public BlockMud() {
		super(Material.SAND);
		setSoundType(SoundType.SAND);
		setTranslationKey("mud");
		setHardness(1.0F);
		setResistance(3.0F);
		setCreativeTab(Reference.GRIMGAR_BLOCKS);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return BOUNDING_BOX;
	}
	
	@Override
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
		entity.motionX *= 0.25D;
		entity.motionZ *= 0.25D;
	}

}
