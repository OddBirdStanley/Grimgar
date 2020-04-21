package grimgar.core.tileentity;

import grimgar.core.block.BlockWoodenMug.EnumMugContent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWoodenMug extends TileEntity {
	
	private int level;
	private EnumMugContent content;
	
	public TileEntityWoodenMug() {
		this(0, EnumMugContent.EMPTY);
	}
	
	public TileEntityWoodenMug(int level, EnumMugContent content) {
		this.level = level;
		if(this.level>8) this.level = 8;
		if(this.level<0) this.level = 0;
		this.content = content;
	}
	
	public void setLevel(int level) {
		if(level>-1 && level<9) {
			this.level = level;
		}
	}
	
	public void setContent(EnumMugContent content) {
		this.content = content;
	}
	
	public int getLevel() {
		return level;
	}
	
	public EnumMugContent getContent() {
		return content;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		level = compound.hasKey("level", 3) ? compound.getInteger("level") : 0;
		content = compound.hasKey("content", 3) ? EnumMugContent.getContentFromOrdinal(compound.getInteger("content")) : EnumMugContent.EMPTY;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("level", level);
		compound.setInteger("content", content.getOrdinal());
		return compound;
	}

}
