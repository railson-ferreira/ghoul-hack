package br.com.meh.hacks.ghoulhack.killaura;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.common.eventhandler.Event;
import br.com.meh.hacks.ghoulhack.GhoulHack;
import br.com.meh.hacks.ghoulhack.Status;
import br.com.meh.hacks.ghoulhack.Teclas;
import br.com.meh.hacks.ghoulhack.arquivos.Amigos;
import br.com.meh.hacks.ghoulhack.gui.util.Desenhador;
import br.com.meh.hacks.ghoulhack.status.Combate;
import br.com.meh.hacks.ghoulhack.util.Cordenadas;
import br.com.meh.hacks.ghoulhack.util.Metodos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

public class KillAura {
	private static EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	
	public static final double AuraRange = 3.5;
	static double HPS = 5;
	static long ultimoHit = 0;

	public static void atualizar(EntityPlayer playerAlvo) {
			long agora = new java.util.Date().getTime();
			double delay = (1/HPS)*1000;
			if(agora-ultimoHit > delay){
				Minecraft.getMinecraft().playerController.attackEntity(player, playerAlvo);
				Minecraft.getMinecraft().thePlayer.swingItem();
				ultimoHit = agora;
			}
	}

}
