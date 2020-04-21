package grimgar.core.test;

import grimgar.main.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

//TODO: Is packet syncing necessary? Too many syncings!
public class BlockRubicsCube extends Block{
	
	public static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.75D, 0.875D);
	
	public BlockRubicsCube() {
		super(Material.CIRCUITS);
		setRegistryName(Reference.MOD_ID, "rubics_cube");
		setCreativeTab(Reference.GRIMGAR_BLOCKS);
		setTranslationKey("rubicsCube");
		setHardness(1.0F);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) {
		return false;
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		drops.clear();
		drops.add(new ItemStack(TestRegistry.RUBICS_CUBE_ITEM));
	}
	
	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, SpawnPlacementType type) {
		return false;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDING_BOX;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityRubicsCube();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(world.getTileEntity(pos)!=null && world.getTileEntity(pos) instanceof TileEntityRubicsCube && !player.isSneaking()) {
			TileEntityRubicsCube te = (TileEntityRubicsCube) world.getTileEntity(pos);
			te.switchMode();
			if(world.isRemote) Minecraft.getMinecraft().player.sendMessage(new TextComponentString(I18n.format("misc.rubicsCubeModeSwitch"+String.valueOf(te.mode))));
			if(!world.isRemote) world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
		}
		return true;
	}
	
	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		if(world.getTileEntity(pos)!=null && world.getTileEntity(pos) instanceof TileEntityRubicsCube && !player.isSneaking()) {
			TileEntityRubicsCube te = (TileEntityRubicsCube) world.getTileEntity(pos);
			if(te.mode == 0) {
				te.switchValueA();
				if(world.isRemote) Minecraft.getMinecraft().player.sendMessage(new TextComponentString(I18n.format("misc.rubicsCubeValueSwitchA")+String.valueOf(te.valA)));
				if(!world.isRemote) world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
			}else if(te.mode == 1) {
				te.switchValueB();
				if(world.isRemote) Minecraft.getMinecraft().player.sendMessage(new TextComponentString(I18n.format("misc.rubicsCubeValueSwitchB")+String.valueOf(te.valB)));
				if(!world.isRemote) world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
			}else if(te.mode == 2) {
				te.swap(te.valA, te.valB);
				if(world.isRemote) Minecraft.getMinecraft().player.sendMessage(new TextComponentString(I18n.format("misc.rubicsCubeSwap")));
				if(!world.isRemote) world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
			}
		}
	}

}
