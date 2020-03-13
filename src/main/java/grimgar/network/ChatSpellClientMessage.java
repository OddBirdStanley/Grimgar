package grimgar.network;

import grimgar.client.handler.ChatSpellHandler;
import grimgar.core.util.IChatSpell;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ChatSpellClientMessage extends ChatSpellMessage{
	
	public ChatSpellClientMessage() {
		
	}
	
	public ChatSpellClientMessage(String spellName) {
		super(spellName);
	}
	
	public static class ChatSpellClientMessageHandler implements IMessageHandler<ChatSpellClientMessage, IMessage>{
		
		public ChatSpellClientMessageHandler() {
			
		}

		@Override
		public IMessage onMessage(ChatSpellClientMessage message, MessageContext ctx) {
			EntityPlayerSP player = Minecraft.getMinecraft().player;
			World world = player.world;
			IChatSpell spell = ChatSpellHandler.getSpell(message.spellName);
			Runnable task = new Runnable() {
				@Override
				public void run() {
					if(spell.isValid(world, player)) {
						if(!spell.accessibleInCreative() && player.capabilities.isCreativeMode) {
							return;
						}
						spell.performAction(world, player);
					}
				}
			};
			Minecraft.getMinecraft().addScheduledTask(task);
			return null;
		}
		
	}

}
