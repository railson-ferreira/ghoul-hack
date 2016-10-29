package br.com.meh.hacks.ghoulhack.arquivos;

import java.io.File;

import br.com.meh.hacks.ghoulhack.GhoulHack;

public class Arquivos {
	public static void recarregar(){
		File pasta = new File("GhoulHack");
		if(pasta.exists()){
			if(!pasta.isDirectory()){
				pasta.mkdir();
			}
		} else {
			pasta.mkdir();
		}
		Delay.recarregar();
		AreasBlockParty.recarregar();
		Lobbies.recarregar();
		Amigos.recarregar();
	}
}
