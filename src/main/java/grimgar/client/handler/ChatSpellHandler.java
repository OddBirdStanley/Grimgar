package grimgar.client.handler;

import java.util.HashMap;

import grimgar.core.handler.NetworkHandler;
import grimgar.core.util.GrimgarModException;
import grimgar.core.util.IChatSpell;
import grimgar.network.ChatSpellMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class ChatSpellHandler {
	
	private static HashMap<String, IChatSpell> SPELLS = new HashMap<String, IChatSpell>();
	
	public static final String PREFIX = "!";
	
	public static void addSpell(IChatSpell spell) {
		if(SPELLS.containsKey(spell.getName())) {
			throw new GrimgarModException("A spell with name '"+spell.getName()+"' is already added.");
		}else {
			for(IChatSpell each : SPELLS.values()) {
				if(each.getSpell().equals(spell.getSpell())) {
					throw new GrimgarModException("A spell '"+spell.getSpell()+"' is already added.");
				}
			}
			SPELLS.put(spell.getName(), spell);
		}
	}
	
	public static void addAllSpells(IChatSpell... spells) {
		for(IChatSpell each : spells) {
			addSpell(each);
		}
	}
	
	public static IChatSpell getSpell(String name) {
		return SPELLS.get(name);
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onChat(ClientChatEvent event) {
		boolean isSpell = false;
		if(event.getMessage().startsWith("!")) {
			for(IChatSpell each : SPELLS.values()) {
				if(each.getSpell().equals(event.getMessage().substring(1))) {
					NetworkHandler.INSTANCE.sendToServer(new ChatSpellMessage(each.getName()));
					isSpell = true;
					break;
				}
			}
			if(!isSpell) {
				Minecraft.getMinecraft().player.sendMessage(new TextComponentString(I18n.format("spell.spellNonexistent")));
			}
			event.setCanceled(true);
		}
	}

}
