package br.com.meh.hacks.ghoulhack.minigames;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import br.com.meh.hacks.ghoulhack.Status;
import br.com.meh.hacks.ghoulhack.Teclas;
import br.com.meh.hacks.ghoulhack.status.MiniGames;
import br.com.meh.hacks.ghoulhack.status.Outros;
import br.com.meh.hacks.ghoulhack.util.Jogador;
import br.com.meh.hacks.ghoulhack.util.Metodos;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Semaforo {
	double ping = 100;
	boolean cont_iniciado = false;
	long tempo = 0;
	long espera1 = 2000;
	long espera2 = 2233;
	boolean teste = true;
	boolean isJumping = false;
	@SubscribeEvent
	public void atualicaoFOV(FOVUpdateEvent evento){
		if(Status.pegar(MiniGames.SemaforoID)){
			EntityPlayerSP player = evento.entity;
			InventoryPlayer inventario = player.inventory;
			ItemStack item = inventario.getCurrentItem();
			//Metodos.EnviarMensagemPlayer(item.getUnlocalizedName());
			long agora = new java.util.Date().getTime();
			if(player.onGround){
				if(!cont_iniciado){
					tempo = new java.util.Date().getTime();
					cont_iniciado = true;
				} else {
					if(agora-tempo < 200){
						isJumping = true;
					} else {
						isJumping = false;
					}
				}
			} else {
				cont_iniciado = false;
			}
			if(item == null){
				cont_iniciado = false;
				return;
			}
			if(item.getUnlocalizedName().equalsIgnoreCase("tile.clayHardenedStained.red")){
				player.setVelocity(0, -10, 0);
				player.setInWeb();
				//if(teste){
				//	Metodos.EnviarMensagemPlayer(agora-tempo+"ms");
				//	teste = false;
				//}
				cont_iniciado = false;
			} else if(item.getUnlocalizedName().equalsIgnoreCase("tile.clayHardenedStained.lime")){
				player.setSprinting(true);
				if(isJumping){
					Jogador.moverPlayer(player, player.posX, player.posZ, true);
				} else {
					Jogador.moverPlayer(player, player.posX, player.posZ, false);
				}
				if(player.onGround){
					player.jump();
				}
				//teste = true;
				cont_iniciado = false;
			} else if(item.getUnlocalizedName().equalsIgnoreCase("tile.clayHardenedStained.yellow")) {
				//teste = true;
				/*
				if(!cont_iniciado){
					tempo = new java.util.Date().getTime();
					cont_iniciado = true;
				} else if(agora - tempo >= espera2-ping){
					if(isJumping){
						Jogador.moverPlayer(player, player.posX, player.posZ, true);
					} else {
						Jogador.moverPlayer(player, player.posX, player.posZ, false);
					}
					if(player.onGround){
						player.jump();
					}
				} else if(agora - tempo >= espera1-ping){
					player.setVelocity(0, -10, 0);
					player.setInWeb();
				} else {
					if(isJumping){
						Jogador.moverPlayer(player, player.posX, player.posZ, true);
					} else {
						Jogador.moverPlayer(player, player.posX, player.posZ, false);
					}
					if(player.onGround){
						player.jump();
					}
				}
				*/
				player.setSprinting(true);
				if(isJumping){
					Jogador.moverPlayer(player, player.posX, player.posZ, true);
				} else {
					Jogador.moverPlayer(player, player.posX, player.posZ, false);
				}
				if(player.onGround){
					player.jump();
				}
			}
		}
	}
}
