package br.com.meh.hacks.ghoulhack.killaura;

import br.com.meh.hacks.ghoulhack.Status;
import br.com.meh.hacks.ghoulhack.status.Combate;
import br.com.meh.hacks.ghoulhack.util.Metodos;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;

public class AntiRepulsao {
	EntityClientPlayerMP player;
	double motionX = 0;
	double motionY = 0;
	double posY = 0;
	double motionZ = 0;
	
	@SubscribeEvent
	 public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if(!Status.pegar(Combate.AntiRepulsaoID)){
			return;
		}
		player  = Minecraft.getMinecraft().thePlayer;
		if(player.hurtTime > 0 && player.motionY != motionY){
			player.motionX = motionX;
			if(player.posY-posY < 1.25){
				player.motionY = motionY;
			}
			player.motionZ = motionZ;
		} else {
			motionX = player.motionX;
			motionY = player.motionY;
			motionZ = player.motionZ;
			if(player.onGround){
				posY = player.posY;
			}
		}
		
		//Metodos.EnviarMensagemPlayer("Nao Cancelou");
		
	}

}
