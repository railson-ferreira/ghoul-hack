package br.com.meh.hacks.ghoulhack.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Render {

	public static final String NaoSegueira = "naosegueira";
	public static final String brilho = "brilho";
	
	private static List<String> IDs = new ArrayList<String>();
	
	public static List<String> pegarIDs(){
		return IDs;
	}
	
	public static void inicializar(){
		IDs.clear();
		IDs.add(NaoSegueira);
		IDs.add(brilho);
	}
}
