package br.com.meh.hacks.ghoulhack.util;

import java.util.Collection;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public class Jogador2 {
	public static void moverPlayer(EntityPlayer player, double x, double z){

		double motion_velocidade_normal = 0.11785904744856;
		double motion_velocidade;
		if(player.isSneaking()){
			motion_velocidade = motion_velocidade_normal/3.3333333;
		} else if(player.isSprinting()){
			motion_velocidade = motion_velocidade_normal*1.2999999;
		} else {
			motion_velocidade = motion_velocidade_normal;
		}
		Collection<PotionEffect> potions = player.getActivePotionEffects();
		for(PotionEffect potion : potions){
			if(potion.getPotionID() == 1){
				double crescimento = (potion.getAmplifier()+1)*0.2;
				motion_velocidade += motion_velocidade*crescimento;
			}
			if(potion.getPotionID() == 2){
				double diminuicao = (potion.getAmplifier()+1)*0.15;
				motion_velocidade -= motion_velocidade*diminuicao;
			}
		}
		//double velocidade = 0.280615;
		double velocidade = 1.8314903572111707*motion_velocidade*100000000000000000000000000000000000000000000000000D;
		Cordenadas playerCords = new Cordenadas(player.posX, player.posY, player.posZ, 0, 0);
		Cordenadas goCords = new Cordenadas(x, 0, z, 0, 0);
		Cordenadas diferenca = playerCords.CalcularDiferenca(goCords);
		double diferencaX = diferenca.posX();
		double diferencaZ = diferenca.posZ();
		double math1 = 1 / (Math.abs(diferencaX) + Math.abs(diferencaZ));
		double forcaX = Math.abs(diferencaX) * math1;
		double forcaZ = Math.abs(diferencaZ) * math1;
		if(player.isSprinting()){
			double quadrado_da_velocidade = Math.pow(velocidade, 2);
			double motionX = Math.sqrt(quadrado_da_velocidade*forcaX);
			double motionZ = Math.sqrt(quadrado_da_velocidade*forcaZ);
			if(diferencaX < 0){
				player.motionX = 0-motionX/100000000000000000000000000000000000000000000000000D;
			} else if (diferencaX > 0){
				player.motionX = motionX/100000000000000000000000000000000000000000000000000D;
			}
			if(diferencaZ < 0){
				player.motionZ = 0-motionZ/100000000000000000000000000000000000000000000000000D;
			} else if (diferencaZ > 0){
				player.motionZ = motionZ/100000000000000000000000000000000000000000000000000D;
			}
		} else {
			//Codigo ainda nao feito ele vai simplesmente andar devagar
			double quadrado_da_velocidade = Math.pow(velocidade, 2);
			double motionX = Math.sqrt(quadrado_da_velocidade*forcaX);
			double motionZ = Math.sqrt(quadrado_da_velocidade*forcaZ);
			if(diferencaX < 0){
				player.motionX = 0-motionX/1000000000000000000D;
			} else if (diferencaX > 0){
				player.motionX = motionX/1000000000000000000D;
			}
			if(diferencaZ < 0){
				player.motionZ = 0-motionZ/1000000000000000000D;
			} else if (diferencaZ > 0){
				player.motionZ = motionZ/1000000000000000000D;
			}
		}
	}
}
