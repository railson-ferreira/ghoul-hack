package br.com.meh.hacks.ghoulhack.status;

import java.util.ArrayList;
import java.util.List;

public class Combate {
	public static final String KillAuraID = "killaura";
	public static final String AimBotID = "aimbot";
	public static final String AntiRepulsaoID = "antirepulsao";
	
	private static List<String> IDs = new ArrayList<String>();
	
	public static List<String> pegarIDs(){
		return IDs;
	}
	
	public static void inicializar(){
		IDs.clear();
		IDs.add(KillAuraID);
		IDs.add(AimBotID);
		IDs.add(AntiRepulsaoID);
	}
	
}
