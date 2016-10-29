package br.com.meh.hacks.ghoulhack.status;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
	public static final String CameraLivreID = "cameralivre";
	
	private static List<String> IDs = new ArrayList<String>();
	
	public static List<String> pegarIDs(){
		return IDs;
	}
	
	public static void inicializar(){
		IDs.clear();
		IDs.add(CameraLivreID);
	}
	
}
