package grimgar.core.test;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.Vec3i;

//TODO: Is packet syncing necessary? Too many syncings!
public class TileEntityRubicsCube extends TileEntity {
	
	private EnumRubicsCubeColor[] colors = new EnumRubicsCubeColor[8];
	public int mode, valA, valB;
	
	public TileEntityRubicsCube() {
		reset();
	}
	
	public void reset() {
		for(int i = 0; i<8; i++) {
			colors[i] = EnumRubicsCubeColor.fromOrdinal(i);
		}
		mode = 0;
		valA = 0;
		valB = 0;
		markDirty();
	}
	
	private void writeToCompound(NBTTagCompound compound) {
		int[] array = new int[8];
		for(int i = 0; i<8; i++) {
			array[i] = colors[i].getOrdinal();
		}
		compound.setIntArray("colors", array);
		compound.setInteger("mode", mode);
		compound.setInteger("valA", valA);
		compound.setInteger("valB", valB);
	}
	
	private void readFromCompound(NBTTagCompound compound) {
		if(compound.hasKey("colors", 11) && compound.getIntArray("colors").length==8) {
			int[] array = compound.getIntArray("colors");
			for(int i = 0; i<8; i++) {
				colors[i] = EnumRubicsCubeColor.fromOrdinal(array[i]);
			}
		}
		if(compound.hasKey("mode", 3)) mode = compound.getInteger("mode");
		if(compound.hasKey("valA", 3)) valA = compound.getInteger("valA");
		if(compound.hasKey("valB", 3)) valB = compound.getInteger("valB");
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound compound = super.getUpdateTag();
		writeToCompound(compound);
		return compound;
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound compound = new NBTTagCompound();
		writeToCompound(compound);
		return new SPacketUpdateTileEntity(pos, -1, compound);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		NBTTagCompound compound = packet.getNbtCompound();
		readFromCompound(compound);
	}
	
	public void switchMode() {
		mode++;
		if(mode>2) mode = 0;
		markDirty();
	}
	
	public void switchValueA() {
		valA++;
		if(valA>7) valA = 0;
		markDirty();
	}
	
	public void switchValueB() {
		valB++;
		if(valB>7) valB = 0;
		markDirty();
	}
	
	public void swap(int valA, int valB) {
		if(valA<0 || valB<0 || valA>7 || valB>7 || valA == valB) return;
		EnumRubicsCubeColor temp = colors[valB];
		colors[valB] = colors[valA];
		colors[valA] = temp;
		markDirty();
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		writeToCompound(compound);
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		readFromCompound(compound);
		super.readFromNBT(compound);
	}
	
	public EnumRubicsCubeColor[] getColors() {
		return colors;
	}
	
	public static enum EnumRubicsCubeColor {
		
		WHITE(255, 255, 255),
		BLACK(0, 0, 0),
		RED(255, 0, 0),
		GREEN(0, 255, 0),
		BLUE(0, 0, 255),
		YELLOW(255, 255, 0),
		ORANGE(255, 165, 0),
		SKYBLUE(0, 255, 255);
		
		public static EnumRubicsCubeColor fromOrdinal(int ordinal) {
			return ordinal>-1 && ordinal<values().length ? values()[ordinal] : null;
		}
		
		public static EnumRubicsCubeColor[] genResetList() {
			EnumRubicsCubeColor[] list = new EnumRubicsCubeColor[8];
			for(int i = 0; i<8; i++) {
				list[i] = EnumRubicsCubeColor.fromOrdinal(i);
			}
			return list;
		}
		
		private int r, g, b;
		
		private EnumRubicsCubeColor(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
		
		public int getRed() {
			return r;
		}
		
		public int getGreen() {
			return g;
		}
		
		public int getBlue() {
			return b;
		}
		
		public int getColor() {
			return r*255*255 + g*255 + b;
		}
		
		public Vec3i getColorVector() {
			return new Vec3i(r, g, b);
		}
		
		public int getOrdinal() {
			for(int i = 0; i<values().length; i++) {
				if(values()[i] == this) return i;
			}
			return -1;
		}

	}

}
