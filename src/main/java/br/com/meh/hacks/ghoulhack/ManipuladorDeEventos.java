package br.com.meh.hacks.ghoulhack;

import ibxm.Player;
import io.netty.channel.embedded.EmbeddedChannel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lwjgl.opengl.GL11;

import br.com.meh.hacks.ghoulhack.arquivos.Amigos;
import br.com.meh.hacks.ghoulhack.comandos.Ajuda;
import br.com.meh.hacks.ghoulhack.comandos.Area;
import br.com.meh.hacks.ghoulhack.comandos.BlockParty;
import br.com.meh.hacks.ghoulhack.comandos.RaioX;
import br.com.meh.hacks.ghoulhack.comandos.Voar;
import br.com.meh.hacks.ghoulhack.killaura.AimBot;
import br.com.meh.hacks.ghoulhack.killaura.KillAura;
import br.com.meh.hacks.ghoulhack.radar.Radar;
import br.com.meh.hacks.ghoulhack.status.Combate;
import br.com.meh.hacks.ghoulhack.status.MiniGames;
import br.com.meh.hacks.ghoulhack.status.Outros;
import br.com.meh.hacks.ghoulhack.status.Render;
import br.com.meh.hacks.ghoulhack.status.Teste;
import br.com.meh.hacks.ghoulhack.util.Cordenadas;
import br.com.meh.hacks.ghoulhack.util.Dados;
import br.com.meh.hacks.ghoulhack.util.Jogador;
import br.com.meh.hacks.ghoulhack.util.Metodos;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.gui.MinecraftServerGui;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Chat;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent.Tick;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.IEventListener;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import cpw.mods.fml.common.network.FMLOutboundHandler.OutboundTarget;
import cpw.mods.fml.common.network.internal.FMLMessage;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.server.FMLServerHandler;

public class ManipuladorDeEventos{
	EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	@SubscribeEvent
	public void SelecaoDeArea(PlayerInteractEvent evento)
	{
		if(Area.status){
			EntityPlayer player = evento.entityPlayer;
			if(evento.action.equals(evento.action.RIGHT_CLICK_AIR)){
				ChunkCoordinates cords = player.getPlayerCoordinates();
				cords.posY = cords.posY - 1;
				if(Dados.pos1 == null){
					Dados.pos1 = new Cordenadas(cords.posX, cords.posY, cords.posZ, 0, 0);
					player.addChatMessage(new ChatComponentText("[POS1] O bloco abaixo do seu pe foi selecionado"));
				} else {
					int X1 = cords.posX; int Y1 = cords.posY; int Z1 = cords.posZ;
					int X2 = (int)Dados.pos1.posX(); int Y2 = (int)Dados.pos1.posY(); int Z2 = (int)Dados.pos1.posZ();
					if(X1 == X2 && Y1 == Y2 && Z1 == Z2){
						if(Dados.pos1_span == false){
							Dados.pos1_span = true;
							player.addChatMessage(new ChatComponentText("[POS1] Esse bloco ja esta selecionado"));
						}
					} else {
						Dados.pos1_span = false;
						Dados.pos1 = new Cordenadas(cords.posX, cords.posY, cords.posZ, 0, 0);
						player.addChatMessage(new ChatComponentText("[POS1] O bloco abaixo do seu pe foi selecionado"));
					}
					
				}
			}
			else if(evento.action.equals(evento.action.RIGHT_CLICK_BLOCK)){
				ChunkCoordinates cords = player.getPlayerCoordinates();
				cords.posY = cords.posY - 1;
				if(Dados.pos2 == null){
					Dados.pos2 = new Cordenadas(cords.posX, cords.posY, cords.posZ, 0, 0);
					player.addChatMessage(new ChatComponentText("[POS2] O bloco abaixo do seu pe foi selecionado"));
				} else {
					int X1 = cords.posX; int Y1 = cords.posY; int Z1 = cords.posZ;
					int X2 = (int)Dados.pos2.posX(); int Y2 = (int)Dados.pos2.posY(); int Z2 = (int)Dados.pos2.posZ();
					if(X1 == X2 && Y1 == Y2 && Z1 == Z2){
						if(Dados.pos2_span == false){
							Dados.pos2_span = true;
							player.addChatMessage(new ChatComponentText("[POS2] Esse bloco ja esta selecionado"));
						}
					} else {
						Dados.pos2_span = false;
						Dados.pos2 = new Cordenadas(cords.posX, cords.posY, cords.posZ, 0, 0);
						player.addChatMessage(new ChatComponentText("[POS2] O bloco abaixo do seu pe foi selecionado"));
					}
					
				}
			}
		}
		//evento.entityPlayer.addChatMessage(new ChatComponentText(evento.x+" "+evento.y+" "+evento.z+" "+evento.face));
	}
	@SubscribeEvent
	public void ComandosDoHack(ServerChatEvent evento)
	{
		String mensagem = evento.message;
		if(mensagem.indexOf(".") == 0){
			if(evento.isCancelable()){
				evento.setCanceled(true);
				String[] args = mensagem.split(" ");
				if(args[0].equalsIgnoreCase(".ajuda")){
					Ajuda.executar(evento);
				} else if(args[0].equalsIgnoreCase(".blockparty")){
					BlockParty.executar(evento);
				} else if(args[0].equalsIgnoreCase(".raioX")){
					RaioX.executar(evento);
				} else if(args[0].equalsIgnoreCase(".voar")){
					Voar.executar(evento);
				} else if(args[0].equalsIgnoreCase(".area")){
					Area.executar(evento);
				} else {
					Ajuda.executar(evento);
				}
			}  else {
				evento.player.addChatMessage(new ChatComponentText("Disculpa, vc se fudeu.O hack não conseguiu processar o comando"));
			}
		}
	}
	@SubscribeEvent
	public void ObterInfo(AttackEntityEvent evento)
	{
		if(Status.pegar(Outros.info)){
			Entity alvo = evento.target;
			if(alvo instanceof EntityPlayer){
				EntityPlayer player_alvo = (EntityPlayer) alvo;
				Collection<PotionEffect> pocoes = player_alvo.getActivePotionEffects();
				float sangue = player_alvo.getHealth();
				int carne = player_alvo.getFoodStats().getFoodLevel();
				evento.entityPlayer.addChatMessage(new ChatComponentText("sangue="+sangue+" comida="+carne));
				if(pocoes.size() > 0){
					evento.entityPlayer.addChatMessage(new ChatComponentText("pocoes ativadas:"));
				}
				for(PotionEffect pocao : pocoes){
					String efeito = pocao.getEffectName();
					int duracao = pocao.getDuration();
					evento.entityPlayer.addChatMessage(new ChatComponentText(efeito+":"+duracao));
				}
			}
		}
	}
	
	
	@SubscribeEvent
	public void UpdateEvent(FOVUpdateEvent evento)
	{
		if(Status.pegar(MiniGames.FrogID)){}
		if(Status.pegar(Render.NaoSegueira)){
			 evento.entity.removePotionEffect(15);
		}
		Radar.atualizar();
	}
	@SubscribeEvent
	public void RenderEvent(RenderGameOverlayEvent evento)
	{
		if(Status.pegar(Combate.AimBotID)){AimBot.atualizar();}
	}
	
	@SubscribeEvent
	public void Detetive(PlayerInteractEvent evento)
	{
	}

	@SubscribeEvent
	public void calcelHitAmigo(TickEvent.PlayerTickEvent evento){
		//Metodos.EnviarMensagemPlayer(evento.side.name()+"");
	}
	@SubscribeEvent
	public void AntiKnockBack(LivingHurtEvent evento){
		evento.entity.setVelocity(0, 0, 0);
		Metodos.EnviarMensagemPlayer("Velocidade Setada para 0");
		if(evento.entityLiving instanceof EntityPlayer && evento.source.getEntity() instanceof EntityPlayer){
			
			Metodos.EnviarMensagemPlayer("hit player");
		}
	}

}
