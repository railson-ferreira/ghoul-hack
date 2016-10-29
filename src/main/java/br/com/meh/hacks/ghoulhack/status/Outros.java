package br.com.meh.hacks.ghoulhack.status;

import java.util.ArrayList;
import java.util.List;

public class Outros {
	public static final String info = "info";
	public static final String Forca = "forca";
	public static final String Recarregar = "recarregar";

	public static final String BPsalvarID = "bpsalvar";
	public static final String BPmoverID = "bpmover";

	public static final String PrioridadeMaxID = "prioridademaxima";
	
	
	private static List<String> IDs = new ArrayList<String>();
	
	public static List<String> pegarIDs(){
		return IDs;
	}
	
	public static void inicializar(){
		IDs.clear();
		IDs.add(info);
		IDs.add(Forca);
		IDs.add(Recarregar);
		IDs.add(BPsalvarID);
		IDs.add(BPmoverID);
		IDs.add(PrioridadeMaxID);
	}
}
