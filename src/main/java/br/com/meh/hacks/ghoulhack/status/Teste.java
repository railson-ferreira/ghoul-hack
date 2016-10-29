package br.com.meh.hacks.ghoulhack.status;

import java.util.ArrayList;
import java.util.List;

public class Teste {
	public static final String Teste1 = "teste1";
	public static final String Teste2 = "teste2";
	public static final String Teste3 = "teste3";
	public static final String Mover = "mover";
	
	private static List<String> IDs = new ArrayList<String>();
	
	public static List<String> pegarIDs(){
		return IDs;
	}
	
	public static void inicializar(){
		IDs.clear();
		IDs.add(Teste1);
		IDs.add(Teste2);
		IDs.add(Teste3);
		IDs.add(Mover);
	}
}
