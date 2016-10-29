package br.com.meh.hacks.ghoulhack.killaura;

import br.com.meh.hacks.ghoulhack.util.Cordenadas;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class AimBot {
	private static Entity alvo = null;
	private static EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	public static void atualizar() {
		if(alvo == null){
			return;
		}
    	Cordenadas cords = new Cordenadas(player.posX, player.posY, player.posY, player.posZ, player.cameraYaw, player.cameraPitch);
    	double difY = 0;
    	if(alvo.isSneaking()){
    		difY = 1.54;
    	} else {
    		difY = 1.62;
    	}
    	cords.Olhar(alvo.posX, alvo.posY+difY, alvo.posZ);
    	player.posX = cords.posX();
    	player.posY = cords.posY();
    	player.posZ = cords.posZ();
    	player.rotationYaw = cords.rotacaoYaw();
    	player.rotationPitch = cords.rotacaoPitch();
	}
	public static void definirAlvo(Entity alvo){
		AimBot.alvo = alvo;
	}

}
