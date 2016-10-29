package br.com.meh.hacks.ghoulhack.comandos;

import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.ServerChatEvent;

public class Ajuda {
	public static void executar(ServerChatEvent evento){
		String ajuda = "";
		evento.player.addChatMessage(new ChatComponentText(ajuda));
	}
}
