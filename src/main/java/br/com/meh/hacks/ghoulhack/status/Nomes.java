package br.com.meh.hacks.ghoulhack.status;

import java.util.HashMap;

public class Nomes {
	private static HashMap<String, String> Nomes = new HashMap<String, String>();
	public static String pegar(String ID){
		return Nomes.get(ID);
	}
	public static void inicializar() {
		Nomes.clear();
		Nomes.put(Combate.AimBotID, "Mira Cabeca");
		Nomes.put(Combate.AntiRepulsaoID, "Anti Repulsao");
		Nomes.put(Combate.KillAuraID, "Aura de Matador");
		Nomes.put(MiniGames.AutoLobbyID, "Auto Lobby");
		Nomes.put(MiniGames.BlockPartyID, "BlockParty");
		Nomes.put(MiniGames.DetetiveID, "Detetive");
		Nomes.put(MiniGames.EscondeEscondeID, "Esconde Esconde");
		Nomes.put(MiniGames.FrogID, "Frog");
		Nomes.put(MiniGames.SemaforoID, "Semaforo");
		Nomes.put(Outros.BPmoverID, "BP mover");
		Nomes.put(Outros.BPsalvarID, "BP salvar");
		Nomes.put(Outros.Forca, "Forca");
		Nomes.put(Outros.info, "Informacoes");
		Nomes.put(Outros.PrioridadeMaxID, "Prioridade Maxima");
		Nomes.put(Outros.Recarregar, "Recarregar");
		Nomes.put(Render.brilho, "Brilho");
		Nomes.put(Render.NaoSegueira, "Nao Segueira");
		Nomes.put(Teste.Mover, "Mover");
		Nomes.put(Teste.Teste1, "Teste1");
		Nomes.put(Teste.Teste2, "Teste2");
		Nomes.put(Teste.Teste3, "Teste3");
	}
}
