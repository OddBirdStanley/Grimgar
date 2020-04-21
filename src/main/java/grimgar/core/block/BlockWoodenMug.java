package grimgar.core.block;

import com.google.common.base.Predicate;

import grimgar.core.capability.IThirst;
import grimgar.core.capability.ThirstProvider;
import grimgar.core.init.InitFluids;
import grimgar.core.tileentity.TileEntityWoodenMug;
import grimgar.main.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockWoodenMug extends Block{
	
	public static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.25F, 0, 0.1875F, 0.75F, 0.5625F, 0.8125F);
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>() {
		@Override
		public boolean apply(EnumFacing input) {
			return input != EnumFacing.UP && input != EnumFacing.DOWN;
		}
	});
	
	public BlockWoodenMug() {
		super(Material.CIRCUITS);
		setCreativeTab(Reference.GRIMGAR_BLOCKS);
		setTranslationKey("wooden_mug");
		setSoundType(SoundType.WOOD);
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDING_BOX;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(meta<2) meta += 4;
		meta -= 2;
		return this.blockState.getBaseState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityWoodenMug();
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = state.getValue(FACING).getHorizontalIndex()+2;
		if(meta>3) meta -= 4;
		return state.getValue(FACING).getHorizontalIndex();
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(facing==EnumFacing.UP) {
			ItemStack stack = player.getHeldItem(hand);
			EnumMugContent content = EnumMugContent.getContentFromFluid(getFluid(stack));
			if(content!=null) {
				if(getLevel(world, pos)==0) {
					setContent(world, pos, content);
					setLevel(world, pos, getLevel(world, pos)+1);
					return checkValidity(true, world, pos, state);
				}
				if(getLevel(world, pos)<8 && content==getContent(world, pos)) {
					setLevel(world, pos, getLevel(world, pos)+1);
					return checkValidity(true, world, pos, state);
				}
			}
			IThirst thirst = player.getCapability(ThirstProvider.THIRST_CAP, null);
			if(getLevel(world, pos)>0 && thirst.getThirst()<thirst.getMaxThirst()) {
				if(world.isRemote) {
					world.playSound(player, player.getPosition(), SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1.0F, 1.0F);
					thirst.addThirst(content.getThirstAddition());
					if(thirst.getThirst()>thirst.getMaxThirst()) thirst.setThirst(thirst.getMaxThirst());
					setLevel(world, pos, getLevel(world, pos)-1);
				}
				return checkValidity(true, world, pos, state);
			}else {
				return checkValidity(false, world, pos, state);
			}
		}else {
			return checkValidity(false, world, pos, state);
		}
	}
	
	private int getLevel(World world, BlockPos pos) {
		return (world.getTileEntity(pos) instanceof TileEntityWoodenMug) ? ((TileEntityWoodenMug)world.getTileEntity(pos)).getLevel() : 0;
	}
	
	private EnumMugContent getContent(World world, BlockPos pos) {
		return (world.getTileEntity(pos) instanceof TileEntityWoodenMug) ? ((TileEntityWoodenMug)world.getTileEntity(pos)).getContent() : EnumMugContent.EMPTY;
	}
	
	private void setLevel(World world, BlockPos pos, int level) {
		if(world.getTileEntity(pos) instanceof TileEntityWoodenMug) ((TileEntityWoodenMug)world.getTileEntity(pos)).setLevel(level);
	}
	
	private void setContent(World world, BlockPos pos, EnumMugContent content) {
		if(world.getTileEntity(pos) instanceof TileEntityWoodenMug) ((TileEntityWoodenMug)world.getTileEntity(pos)).setContent(content);
	}
	
	private Fluid getFluid(ItemStack itemStack) {
		if(itemStack.getItem()==Items.WATER_BUCKET) return FluidRegistry.WATER;
		else if(itemStack.getItem()==Items.LAVA_BUCKET) return FluidRegistry.LAVA;
		else if(itemStack.getItem()==ForgeModContainer.getInstance().universalBucket) return ForgeModContainer.getInstance().universalBucket.getFluid(itemStack).getFluid();
		else return null;
	}
	
	private boolean checkValidity(boolean returnValue, World world, BlockPos pos, IBlockState state) {
		if(getLevel(world, pos)==0 && getContent(world, pos)!=EnumMugContent.EMPTY) {
			setContent(world, pos, EnumMugContent.EMPTY);
		}
		if(getLevel(world, pos)>0 && getContent(world, pos)==EnumMugContent.EMPTY) {
			setLevel(world, pos, 0);
		}
		return returnValue;
	}
	
	public static enum EnumMugContent implements IStringSerializable{
		EMPTY(null, 0.0F),
		WATER(FluidRegistry.WATER, 2.0F),
		//BOILING_WATER(null),
		MILK(InitFluids.MILK, 3.0F),
		BEER(InitFluids.BEER, 3.0F);
		//LEMONADE(null),
		//RASPBERRY_HONEY_TEA(null);
		
		public static EnumMugContent getContentFromFluid(Fluid fluid) {
			if(fluid==null) return EMPTY;
			for(EnumMugContent each : values()) {
				if(each.getFluid()==fluid) return each;
			}
			return EMPTY;
		}
		
		public static EnumMugContent getContentFromOrdinal(int ordinal) {
			return (ordinal<0 || ordinal>=values().length) ? EMPTY : values()[ordinal];
		}
		
		private Fluid fluid;
		private float thirstAddition;
		
		private EnumMugContent(Fluid fluid, float thirstAddition) {
			this.fluid = fluid;
			this.thirstAddition = thirstAddition;
		}
		
		public Fluid getFluid() {
			return fluid;
		}
		
		public float getThirstAddition() {
			return thirstAddition;
		}

		@Override
		public String getName() {
			return fluid==null ? "empty" : (fluid.getName()==null ? "empty" : fluid.getName());
		}
		
		public int getOrdinal() {
			int i = 0;
			for(EnumMugContent each : values()) {
				if(this==each) return i;
				i++;
			}
			return -1;
		}
		
	}

}
