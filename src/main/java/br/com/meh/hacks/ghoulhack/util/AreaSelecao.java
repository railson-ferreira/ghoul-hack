package br.com.meh.hacks.ghoulhack.util;

import br.com.meh.hacks.ghoulhack.arquivos.AreasBlockParty;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;

public class AreaSelecao {
	private Cordenadas min_pos;
	private Cordenadas max_pos;
	public AreaSelecao(Cordenadas pos1, Cordenadas pos2){
		int X1 = (int)pos1.posX(); int Y1 = (int)pos1.posY(); int Z1 = (int)pos1.posZ();
		int X2 = (int)pos2.posX(); int Y2 = (int)pos2.posY(); int Z2 = (int)pos2.posZ();
		int maxX; int maxY; int maxZ;
		int minX; int minY; int minZ;
		//
		if(X1 > X2){
			maxX = X1;
			minX = X2;
		} else {
			maxX = X2;
			minX = X1;
		}
		//
		if(Y1 > Y2){
			maxY = Y1;
			minY = Y2;
		} else {
			maxY = Y2;
			minY = Y1;
		}
		//
		if(Z1 > Z2){
			maxZ = Z1;
			minZ = Z2;
		} else {
			maxZ = Z2;
			minZ = Z1;
		}
		min_pos = new Cordenadas(minX, minY, minZ, 0, 0);
		max_pos = new Cordenadas(maxX, maxY, maxZ, 0, 0);
	}
	public Cordenadas pegarMin(){
		return min_pos;
	}
	public Cordenadas pegarMax(){
		return max_pos;		
	}
}
