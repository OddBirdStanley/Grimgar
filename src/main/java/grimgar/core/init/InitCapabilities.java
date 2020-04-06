package grimgar.core.init;

import grimgar.core.capability.IThirst;
import grimgar.core.capability.Thirst;
import grimgar.core.capability.ThirstStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class InitCapabilities {
	
	public static void registerCapabilities() {
		CapabilityManager.INSTANCE.register(IThirst.class, new ThirstStorage(), Thirst::new);
	}
	
}
