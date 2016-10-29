package br.com.meh.hacks.ghoulhack.comandos;

import java.util.ArrayList;
import java.util.List;

public class Comando {
	String cmd;
	String descricao;
	static List<Comando> cmdsList = new ArrayList<Comando>(); 
	
	public Comando(String cmd, String descricao){
		this.cmd = cmd;
		this.descricao = descricao;
	}
	public static void Inicializar(){
		cmdsList.clear();
		cmdsList.add(new Comando("Ajuda", "ajuda description"));
		cmdsList.add(new Comando("Area", "area description"));
		cmdsList.add(new Comando("BlockParty", "blockparty description"));
		cmdsList.add(new Comando("RaioX", "raiox description"));
		cmdsList.add(new Comando("Voar", "voar description"));
	}
	public static List<Comando> VerificarComando(String cmd_verifiq){
		List<Comando> retorno = new ArrayList<Comando>();
		for(Comando objComando : cmdsList){
			String cmd_name = objComando.cmd;
			String cmd_descricao = objComando.descricao;
			if(cmd_name.toLowerCase().indexOf(cmd_verifiq.toLowerCase()) == 0){
				retorno.add(new Comando(cmd_name, cmd_descricao));
			}
		}
		return retorno;
	}
	public String pegarCmd(){
		return cmd;
	}
	public String pegarDescricao(){
		return descricao;
	}
}
