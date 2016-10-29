package br.com.meh.hacks.ghoulhack.util;

import java.util.TimerTask;

import br.com.meh.hacks.ghoulhack.GhoulHack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.WorldSettings.GameType;

public class Loop extends TimerTask {
	EntityClientPlayerMP player;
	boolean ok = true;
	double motionX = 0;
	double motionY = 0;
	double motionZ = 0;
	double oldms = 0;

	@Override
	public void run() {
		player  = Minecraft.getMinecraft().thePlayer;
		if(player != null){
			if(player.hurtTime > 0){
				player.motionX = motionX;
				player.motionY = motionY;
				player.motionZ = motionZ;
			}
			motionX = player.motionX;
			motionY = player.motionY;
			motionZ = player.motionZ;
		}
	}

}
