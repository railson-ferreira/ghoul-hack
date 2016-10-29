package br.com.meh.hacks.ghoulhack.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MiniGames {
	public static final String BlockPartyID = "blockparty";
	public static final String SemaforoID = "semaforo";
	public static final String FrogID = "frog";
	public static final String DetetiveID = "detetive";
	public static final String EscondeEscondeID = "escondeesconde";
	public static final String AutoLobbyID = "autolobby";
	
	private static List<String> IDs = new ArrayList<String>();
	
	public static List<String> pegarIDs(){
		return IDs;
	}
	
	public static void inicializar(){
		IDs.clear();
		IDs.add(BlockPartyID);
		IDs.add(SemaforoID);
		IDs.add(FrogID);
		IDs.add(DetetiveID);
		IDs.add(EscondeEscondeID);
		IDs.add(AutoLobbyID);
	}
}
