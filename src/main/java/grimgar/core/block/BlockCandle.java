package grimgar.core.block;

import java.util.Random;

import com.google.common.base.Predicate;

import grimgar.core.init.InitBlocks;
import grimgar.main.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCandle extends Block{
	
	private static final AxisAlignedBB BOUNDING_BOX_DOWN = new AxisAlignedBB(0.375, 0, 0.312, 0.688, 1, 0.625);
	private static final AxisAlignedBB BOUNDING_BOX_NORTH = new AxisAlignedBB(0.375, 0.125, 0, 0.688, 0.938, 0.625);
	private static final AxisAlignedBB BOUNDING_BOX_SOUTH = new AxisAlignedBB(0.312, 0.125, 0.375, 0.625, 0.938, 1);
	private static final AxisAlignedBB BOUNDING_BOX_WEST = new AxisAlignedBB(0, 0.125, 0.312, 0.625, 0.938, 0.625);
	private static final AxisAlignedBB BOUNDING_BOX_EAST = new AxisAlignedBB(0.375, 0.125, 0.375, 1, 0.938, 0.688);
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>() {
		@Override
		public boolean apply(EnumFacing input) {
			return input != EnumFacing.UP;
		}
	});
	
	public BlockCandle() {
		super(Material.CIRCUITS);
		setSoundType(SoundType.STONE);
		setTickRandomly(true);
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.DOWN));
		setTranslationKey("candle");
		setHardness(0.75F);
		setResistance(0.0F);
		setLightLevel(0.0F);
		setCreativeTab(Reference.GRIMGAR_BLOCKS);
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
		IBlockState neighbor;
		if(side==EnumFacing.DOWN) return false;
		else {
			EnumFacing opposite = side.getOpposite();
			return world.getBlockState(pos.offset(opposite)).isSideSolid(world, pos.offset(opposite), opposite);
		}
	}

	@Override
	public boolean isBurning(IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return false;
	}
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		EnumFacing facing = world.getBlockState(pos).getValue(FACING);
		pos = pos.offset(facing);
		if(pos.equals(fromPos)) {
			facing = facing.getOpposite();
			if(world.isSideSolid(pos, facing) || world.getBlockState(pos).getBlock() instanceof BlockAir) {
				pos = pos.offset(facing);
				dropBlockAsItem(world, pos, state, 0);
				world.setBlockToAir(pos);
			}
		}
	}
	
	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		if(this.getRegistryName().getPath().equals("lit_candle")) {
			world.setBlockState(pos, InitBlocks.CANDLE.getDefaultState().withProperty(FACING, world.getBlockState(pos).getValue(FACING)));
			world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, super.RANDOM.nextFloat()*0.25F+0.875F, super.RANDOM.nextFloat()*0.5F+1.5F);
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(player.getHeldItem(hand)!=null && player.getHeldItem(hand).getItem() instanceof ItemFlintAndSteel && world.getBlockState(pos).getBlock() instanceof BlockCandle && this.getRegistryName().getPath().equals("candle")) {
			world.setBlockState(pos, InitBlocks.LIT_CANDLE.getDefaultState().withProperty(FACING, world.getBlockState(pos).getValue(FACING)));
			world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, super.RANDOM.nextFloat()*0.25F+0.875F, super.RANDOM.nextFloat()*0.25F+0.875F);
			return true;
		}
		return false;
	}

	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	@Override
	public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		if(facing==EnumFacing.UP) return this.getDefaultState();
		return getDefaultState().withProperty(FACING, facing.getOpposite());
	}
	
	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if(!this.getRegistryName().getPath().equals("lit_candle")) {
			return;
		}
		boolean hasSmoke;
		switch(rand.nextInt(6)) {
			case 0:
				hasSmoke = true;
				break;
			case 1:
				hasSmoke = false;
				break;
			default:
				return;
		}
		if(state.getValue(FACING)==EnumFacing.DOWN) {
			world.spawnParticle(EnumParticleTypes.FLAME, (6.5+rand.nextDouble()*6)/16+pos.getX(), (15.5+rand.nextDouble()*2)/16+pos.getY(), (4.5+rand.nextDouble()*6)/16+pos.getZ(), rand.nextDouble()/100, rand.nextDouble()/50, rand.nextDouble()/100);
			if(rand.nextInt(2)==0){
				world.spawnParticle(EnumParticleTypes.FLAME, (6.5+rand.nextDouble()*6)/16+pos.getX(), (15.5+rand.nextDouble()*2)/16+pos.getY(), (4.5+rand.nextDouble()*6)/16+pos.getZ(), rand.nextDouble()/100, rand.nextDouble()/50, rand.nextDouble()/100);
			}
			if(hasSmoke) {
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (6.5+rand.nextDouble()*6)/16+pos.getX(), (15.5+rand.nextDouble()*2)/16+pos.getY(), (4.5+rand.nextDouble()*6)/16+pos.getZ(), rand.nextDouble()/100, rand.nextDouble()/50, rand.nextDouble()/100);
				if(rand.nextInt(2)==0){
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (6.5+rand.nextDouble()*6)/16+pos.getX(), (15.5+rand.nextDouble()*2)/16+pos.getY(), (4.5+rand.nextDouble()*6)/16+pos.getZ(), rand.nextDouble()/100, rand.nextDouble()/50, rand.nextDouble()/100);
				}
			}
			return;
		}else {
			double x,z;
			switch(state.getValue(FACING)) {
				case NORTH:
					x = 6.5;
					z = 4.5;
					break;
				case SOUTH:
					x = 3.5;
					z = 5.5;
				case EAST:
					x = 5.5;
					z = 6.5;
					break;
				case WEST:
					x = 4.5;
					z = 3.5;
					break;
				default:
					throw new IllegalArgumentException("Cannot find the state.");
			}
			world.spawnParticle(EnumParticleTypes.FLAME, (6.5+rand.nextDouble()*6)/16+pos.getX(), (15.5+rand.nextDouble()*2)/16+pos.getY(), (4.5+rand.nextDouble()*6)/16+pos.getZ(), rand.nextDouble()/100, rand.nextDouble()/50, rand.nextDouble()/100);
			if(rand.nextInt(2)==0){
				world.spawnParticle(EnumParticleTypes.FLAME, (6.5+rand.nextDouble()*6)/16+pos.getX(), (15.5+rand.nextDouble()*2)/16+pos.getY(), (4.5+rand.nextDouble()*6)/16+pos.getZ(), rand.nextDouble()/100, rand.nextDouble()/50, rand.nextDouble()/100);
			}
			if(hasSmoke) {
				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (6.5+rand.nextDouble()*6)/16+pos.getX(), (15.5+rand.nextDouble()*2)/16+pos.getY(), (4.5+rand.nextDouble()*6)/16+pos.getZ(), rand.nextDouble()/100, rand.nextDouble()/50, rand.nextDouble()/100);
				if(rand.nextInt(2)==0){
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (6.5+rand.nextDouble()*6)/16+pos.getX(), (15.5+rand.nextDouble()*2)/16+pos.getY(), (4.5+rand.nextDouble()*6)/16+pos.getZ(), rand.nextDouble()/100, rand.nextDouble()/50, rand.nextDouble()/100);
				}
			}
			return;
		}
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(InitBlocks.CANDLE);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(InitBlocks.CANDLE);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch(state.getValue(FACING)) {
			case DOWN:
				return BOUNDING_BOX_DOWN;
			case NORTH:
				return BOUNDING_BOX_NORTH;
			case SOUTH:
				return BOUNDING_BOX_SOUTH;
			case EAST:
				return BOUNDING_BOX_EAST;
			case WEST:
				return BOUNDING_BOX_WEST;
			default:
				throw new IllegalArgumentException("Cannot find the state.");
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return getBoundingBox(blockState, worldIn, pos);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		switch(state.getValue(FACING)) {
			case DOWN:
				return 0;
			case NORTH:
				return 1;
			case SOUTH:
				return 2;
			case EAST:
				return 3;
			case WEST:
				return 4;
			default:
				throw new IllegalArgumentException("Cannot find the meta.");
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		switch(meta) {
			case 0:
				return getDefaultState().withProperty(FACING, EnumFacing.DOWN);
			case 1:
				return getDefaultState().withProperty(FACING, EnumFacing.NORTH);
			case 2:
				return getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
			case 3:
				return getDefaultState().withProperty(FACING, EnumFacing.EAST);
			case 4:
				return getDefaultState().withProperty(FACING, EnumFacing.WEST);	
			default:
				throw new IllegalArgumentException("Cannot find the state.");
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}

}
