package grimgar.core.handler;

import grimgar.network.ChatSpellClientMessage;
import grimgar.network.ChatSpellMessage;
import grimgar.network.CustomAttackRangeMessage;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
	
	private static final String MOD_ID = grimgar.main.Reference.MOD_ID;
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
	private static int id = 0;
	
	public static void registerMessages() {
		INSTANCE.registerMessage(CustomAttackRangeMessage.CustomAttackRangeMessageHandler.class, CustomAttackRangeMessage.class, id++, Side.SERVER);
		INSTANCE.registerMessage(ChatSpellMessage.ChatSpellMessageHandler.class, ChatSpellMessage.class, id++, Side.SERVER);
		INSTANCE.registerMessage(ChatSpellClientMessage.ChatSpellClientMessageHandler.class, ChatSpellClientMessage.class, id++, Side.CLIENT);
	}

}
