package br.com.meh.hacks.ghoulhack.radar;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import br.com.meh.hacks.ghoulhack.Status;
import br.com.meh.hacks.ghoulhack.arquivos.Amigos;
import br.com.meh.hacks.ghoulhack.gui.Gui;
import br.com.meh.hacks.ghoulhack.gui.util.Desenhador;
import br.com.meh.hacks.ghoulhack.killaura.AimBot;
import br.com.meh.hacks.ghoulhack.killaura.KillAura;
import br.com.meh.hacks.ghoulhack.status.Combate;

public class Radar {
	private static List<EntityPlayer> ListaPlayers = new ArrayList<EntityPlayer>();
	private static List<EntityPlayer> ListaOutros = new ArrayList<EntityPlayer>();

	private static Minecraft mc = Minecraft.getMinecraft();
	private static  EntityPlayer player = mc.thePlayer;
	
	public static void atualizar(){
		//if(mc.currentScreen instanceof Gui){return;}
		ListaPlayers = mc.theWorld.playerEntities;
		if(Status.pegar(Combate.KillAuraID) || Status.pegar(Combate.AimBotID)){
			procurarAlvoMaisProximo();
		}		
	}
	public static List<EntityPlayer> PegarListaPlayers(){
		return ListaPlayers;
	}
	
	
	private static void procurarAlvoMaisProximo(){
		List<EntityPlayer> players = ListaPlayers;
		for(EntityPlayer playerAlvo : players){
			if(player == playerAlvo){
				continue;
			} 
			boolean tem_amigo = false;
			for(String amigo : Amigos.pegarAmigos()){
				if(playerAlvo.getDisplayName().equalsIgnoreCase(amigo)){
					tem_amigo = true;
				}
			}
			if(tem_amigo == true){
				continue;
			}
			

			double p1X = player.posX;
			double p1Y = player.posY;
			double p1Z = player.posZ;
			double p1difY = 0;
			
			if(player.isSneaking()){
				p1difY = 0.77;
			} else {
				p1difY = 0.81;
			}
			
			
			double p2X = playerAlvo.posX;
			double p2Y = playerAlvo.posY;
			double p2Z = playerAlvo.posZ;
			double p2difY = 0;
			
			if(playerAlvo.isSneaking()){
				p2difY = 0.77;
			} else {
				p2difY = 0.81;
			}
			
			p1Y += p1difY; p2Y += p2difY;

			double maxX = 0; double maxY = 0; double maxZ = 0;
			double minX = 0; double minY = 0; double minZ = 0;

			if(p1X > p2X){maxX = p1X; minX = p2X;}else{maxX = p2X; minX = p1X;}
			if(p1Y > p2Y){maxY = p1Y; minY = p2Y;}else{maxY = p2Y; minY = p1Y;}
			if(p1Z > p2Z){maxZ = p1Z; minZ = p2Z;}else{maxZ = p2Z; minZ = p1Z;}
			
			if(maxX-minX <= KillAura.AuraRange && maxY-minY <= KillAura.AuraRange+p1difY+p2difY && maxZ-minZ <= KillAura.AuraRange){
				AimBot.definirAlvo(playerAlvo);
				if(Status.pegar(Combate.KillAuraID)){KillAura.atualizar(playerAlvo);}
			} else {
				AimBot.definirAlvo(null);
			}
		}
	}
}
