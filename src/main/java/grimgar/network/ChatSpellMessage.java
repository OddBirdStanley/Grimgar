package grimgar.network;

import java.io.UnsupportedEncodingException;

import grimgar.client.handler.ChatSpellHandler;
import grimgar.core.handler.NetworkHandler;
import grimgar.core.util.IChatSpell;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ChatSpellMessage implements IMessage{
	
	public String spellName;
	
	public ChatSpellMessage() {
		
	}
	
	public ChatSpellMessage(String spellName) {
		this.spellName = spellName;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		try {
			spellName = new String(bytes,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		try {
			buf.writeBytes(spellName.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static class ChatSpellMessageHandler implements IMessageHandler<ChatSpellMessage, IMessage>{

		public ChatSpellMessageHandler()	{
			
		}
		
		@Override
		public IMessage onMessage(ChatSpellMessage message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			World world = player.getServerWorld();
			IChatSpell spell = ChatSpellHandler.getSpell(message.spellName);
			Runnable task = new Runnable() {
				@Override
				public void run() {
					if(spell.isValid(world, player)) {
						if(!spell.accessibleInCreative() && player.capabilities.isCreativeMode) {
							return;
						}
						if(spell.performAction(world, player)) {
							player.getServer().getPlayerList().sendMessage(new TextComponentString(spell.getSuccessNotification()));
						}else {
							player.sendMessage(new TextComponentString(spell.getFaliureNotification()));
						}
						NetworkHandler.INSTANCE.sendToAll(new ChatSpellClientMessage(spell.getName()));
					}
				}
			};
			player.getServerWorld().addScheduledTask(task);
			return null;
		}
		
	}

}
