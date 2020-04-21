package grimgar.core.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ThirstProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(IThirst.class)
	public static final Capability<IThirst> THIRST_CAP = null;
	
	public IThirst INSTANCE = THIRST_CAP.getDefaultInstance();
	
	public ThirstProvider() {
		
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability==THIRST_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability==THIRST_CAP ? (T)THIRST_CAP : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return THIRST_CAP.getStorage().writeNBT(THIRST_CAP, INSTANCE, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		THIRST_CAP.getStorage().readNBT(THIRST_CAP, INSTANCE, null, nbt);
	}

}
