package br.com.meh.hacks.ghoulhack.comandos;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.ServerChatEvent;

public class Area {
	public static boolean status = false;
	public static void executar(ServerChatEvent evento){
		String ajuda = ".area sel - Para alternar o status de selecão de area";
		String[] args = evento.message.split(" ");
		EntityPlayerMP player = evento.player;
		if(args.length > 1){
			if(args[1].equalsIgnoreCase("sel")){
				if(status == true){
					status = false;
					player.addChatMessage(new ChatComponentText("[Area] Modo de Selecao desativado"));
				} else {
					status = true;
					player.addChatMessage(new ChatComponentText("[Area] Modo de Selecao ativado"));
				}
			} else {
				player.addChatMessage(new ChatComponentText("§4Comando Invalido\n§r"+ajuda));
			}
		} else {
			player.addChatMessage(new ChatComponentText(ajuda));
		}
	}
}
