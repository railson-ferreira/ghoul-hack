package br.com.meh.hacks.ghoulhack.util;

import java.util.Collection;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public class Jogador {
	public static void moverPlayer(EntityPlayer player, double x, double z, boolean pulando){
		Cordenadas cords = new Cordenadas(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
		cords.Olhar(x, 0, z);
		float yaw = cords.rotacaoYaw();
		while(yaw < -360){
			yaw += 360;
		}
		while(yaw > 360){
			yaw -= 360;
		}
		double forcaX;
		double forcaZ;
		if(yaw > 0){
			if(yaw > 270){
				double resto = yaw - 270;
				forcaX = 90/(90-resto);
				forcaZ = 90/resto;
			} else if(yaw < 270){
				if(yaw > 180){
					double resto = yaw - 180;
					forcaX = 90/resto;
					forcaZ = -(90/(90-resto));
				} else if(yaw < 180){
					if(yaw > 90){
						double resto = yaw - 90;
						forcaX = -(90/(90-resto));
						forcaZ = -(90/resto);
					} else if(yaw < 90){
						double resto = yaw;
						forcaX = -(90/resto);
						forcaZ = 90/(90-resto);
					} else {
						forcaX = -1;
						forcaZ = 0;
					}
				} else {
					forcaX = 0;
					forcaZ = -1;
				}
			} else {
				forcaX = 1;
				forcaZ = 0;
			}
		} else if(yaw < 0){
			if(yaw < -270){
				double resto = -(yaw + 270);
				forcaX = -(90/(90-resto));
				forcaZ = 90/resto;
			} else if(yaw > -270){
				if(yaw < -180){
					double resto = -(yaw + 180);
					forcaX = -(90/resto);
					forcaZ = -(90/(90-resto));
				} else if(yaw > -180){
					if(yaw < -90){
						double resto = -(yaw + 90);
						forcaX = 90/(90-resto);
						forcaZ = -(90/resto);
					} else if(yaw > -90){
						double resto = -(yaw);
						forcaX = 90/resto;
						forcaZ = 90/(90-resto);
					} else {
						forcaX = 1;
						forcaZ = 0;
					}
				} else {
					forcaX = 0;
					forcaZ = -1;
				}
			} else {
				forcaX = -1;
				forcaZ = 0;
			}
		} else {
			forcaX = 0;
			forcaZ = 1;
		}
		double ForcaX_P = 1/forcaX;
		double ForcaZ_P = 1/forcaZ;
		double motion_velocidade_normal = 0.11785904744856;
		double motion_velocidade;
		if(player.isSneaking()){
			motion_velocidade = motion_velocidade_normal/3.3333333;
		} else if(player.isSprinting()){
			if(pulando){
				motion_velocidade = motion_velocidade_normal*2.2;
			} else {
				motion_velocidade = motion_velocidade_normal*1.2999999;
			}
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
		double correcao_do_motion = 1.8312062439882055;
		double velocidade = (motion_velocidade * correcao_do_motion)*100000000000000000000000000000000000000000000000000D;
		
		double quadrado_da_velocidade = Math.pow(velocidade, 2);
		double motionX = Math.sqrt(quadrado_da_velocidade*Math.abs(ForcaX_P));
		double motionZ = Math.sqrt(quadrado_da_velocidade*Math.abs(ForcaZ_P));
		
		/*double quadradoX_player = Math.pow(Math.abs(player.motionX), 2);
		double quadradoZ_player = Math.pow(Math.abs(player.motionZ), 2);
		double motion_player = Math.sqrt(quadradoX_player + quadradoZ_player);
		double quadradoX_go = Math.pow(motionX, 2);
		double quadradoZ_go = Math.pow(motionZ, 2);
		double motion_go = Math.sqrt(quadradoX_player + quadradoZ_player);
		if(motion_player <= motion_go){*/
			if(ForcaX_P < 0){
				player.motionX = 0-motionX/100000000000000000000000000000000000000000000000000D;
			} else if (ForcaX_P > 0){
				player.motionX = motionX/100000000000000000000000000000000000000000000000000D;
			}
			if(ForcaZ_P < 0){
				player.motionZ = 0-motionZ/100000000000000000000000000000000000000000000000000D;
			} else if (ForcaZ_P > 0){
				player.motionZ = motionZ/100000000000000000000000000000000000000000000000000D;
			}
		//}
	}
	public static void moverPulo(EntityPlayer player, double x, double z, double qnts_blocks){
		Cordenadas cords = new Cordenadas(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
		cords.Olhar(x, 0, z);
		float yaw = cords.rotacaoYaw();
		while(yaw < -360){
			yaw += 360;
		}
		while(yaw > 360){
			yaw -= 360;
		}
		double forcaX;
		double forcaZ;
		if(yaw > 0){
			if(yaw > 270){
				double resto = yaw - 270;
				forcaX = 90/(90-resto);
				forcaZ = 90/resto;
			} else if(yaw < 270){
				if(yaw > 180){
					double resto = yaw - 180;
					forcaX = 90/resto;
					forcaZ = -(90/(90-resto));
				} else if(yaw < 180){
					if(yaw > 90){
						double resto = yaw - 90;
						forcaX = -(90/(90-resto));
						forcaZ = -(90/resto);
					} else if(yaw < 90){
						double resto = yaw;
						forcaX = -(90/resto);
						forcaZ = 90/(90-resto);
					} else {
						forcaX = -1;
						forcaZ = 0;
					}
				} else {
					forcaX = 0;
					forcaZ = -1;
				}
			} else {
				forcaX = 1;
				forcaZ = 0;
			}
		} else if(yaw < 0){
			if(yaw < -270){
				double resto = -(yaw + 270);
				forcaX = -(90/(90-resto));
				forcaZ = 90/resto;
			} else if(yaw > -270){
				if(yaw < -180){
					double resto = -(yaw + 180);
					forcaX = -(90/resto);
					forcaZ = -(90/(90-resto));
				} else if(yaw > -180){
					if(yaw < -90){
						double resto = -(yaw + 90);
						forcaX = 90/(90-resto);
						forcaZ = -(90/resto);
					} else if(yaw > -90){
						double resto = -(yaw);
						forcaX = 90/resto;
						forcaZ = 90/(90-resto);
					} else {
						forcaX = 1;
						forcaZ = 0;
					}
				} else {
					forcaX = 0;
					forcaZ = -1;
				}
			} else {
				forcaX = -1;
				forcaZ = 0;
			}
		} else {
			forcaX = 0;
			forcaZ = 1;
		}
		double ForcaX_P = 1/forcaX;
		double ForcaZ_P = 1/forcaZ;
		double motion_velocidade_1_block = 0.0458;
		double motion_velocidade;
		motion_velocidade = motion_velocidade_1_block*qnts_blocks;
		double correcao_do_motion = 1.8312062439882055;
		double velocidade = (motion_velocidade * correcao_do_motion)*100000000000000000000000000000000000000000000000000D;
		//223035
		double quadrado_da_velocidade = Math.pow(velocidade, 2);
		double motionX = Math.sqrt(quadrado_da_velocidade*Math.abs(ForcaX_P));
		double motionZ = Math.sqrt(quadrado_da_velocidade*Math.abs(ForcaZ_P));
		
		double quadradoX_player = Math.pow(Math.abs(player.motionX), 2);
		double quadradoZ_player = Math.pow(Math.abs(player.motionZ), 2);
		double motion_player = Math.sqrt(quadradoX_player + quadradoZ_player);
		double quadradoX_go = Math.pow(motionX, 2);
		double quadradoZ_go = Math.pow(motionZ, 2);
		double motion_go = Math.sqrt(quadradoX_player + quadradoZ_player);
		if(motion_player <= motion_go){
			if(ForcaX_P < 0){
				player.motionX = 0-motionX/100000000000000000000000000000000000000000000000000D;
			} else if (ForcaX_P > 0){
				player.motionX = motionX/100000000000000000000000000000000000000000000000000D;
			}
			if(ForcaZ_P < 0){
				player.motionZ = 0-motionZ/100000000000000000000000000000000000000000000000000D;
			} else if (ForcaZ_P > 0){
				player.motionZ = motionZ/100000000000000000000000000000000000000000000000000D;
			}
		}
	}
}
