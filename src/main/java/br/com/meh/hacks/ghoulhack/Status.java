package br.com.meh.hacks.ghoulhack;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.client.Minecraft;
import br.com.meh.hacks.ghoulhack.minigames.BlockParty;
import br.com.meh.hacks.ghoulhack.status.Combate;
import br.com.meh.hacks.ghoulhack.status.Jogador;
import br.com.meh.hacks.ghoulhack.status.MiniGames;
import br.com.meh.hacks.ghoulhack.status.Nomes;
import br.com.meh.hacks.ghoulhack.status.Outros;
import br.com.meh.hacks.ghoulhack.status.Render;
import br.com.meh.hacks.ghoulhack.status.Teste;

public class Status {
	private static HashMap<String, Boolean> Status = new HashMap<String, Boolean>();
	public static HashMap<String, Boolean> Lobbies = new HashMap<String, Boolean>();
	
	public static void inicializar(){
		Status.clear();
		Status.put(Combate.AimBotID, false);
		Status.put(Combate.AntiRepulsaoID, false);
		Status.put(Combate.KillAuraID, false);
		Status.put(MiniGames.AutoLobbyID, false);
		Status.put(MiniGames.BlockPartyID, false);
		Status.put(MiniGames.DetetiveID, false);
		Status.put(MiniGames.EscondeEscondeID, false);
		Status.put(MiniGames.FrogID, false);
		Status.put(MiniGames.SemaforoID, false);
		Status.put(Outros.BPmoverID, false);
		Status.put(Outros.BPsalvarID, false);
		Status.put(Outros.Forca, false);
		Status.put(Outros.info, false);
		Status.put(Outros.PrioridadeMaxID, false);
		Status.put(Render.brilho, false);
		Status.put(Render.NaoSegueira, false);
		Status.put(Jogador.CameraLivreID, false);
		Status.put(Teste.Mover, false);
		Status.put(Teste.Teste1, false);
		Status.put(Teste.Teste2, false);
		Status.put(Teste.Teste3, false);
		Combate.inicializar();
		MiniGames.inicializar();
		Outros.inicializar();
		Render.inicializar();
		Jogador.inicializar();
		Teste.inicializar();
		Nomes.inicializar();
	}
	public static boolean pegar(String ID){
		return Status.get(ID);
	}
	public static Set<String> pegarIDs(){
		return Status.keySet();
	}
	public static boolean alternarStatus(String StatusID){
		boolean status = Status.get(StatusID);
		if(status == true){
			status = false;
			//Execoes
			if(StatusID == Outros.BPsalvarID){
				BlockParty.salvo = true;
			} else if(StatusID == MiniGames.BlockPartyID){
				BlockParty.RestaurarPadroes();
			} else if(StatusID == Render.brilho){
				Minecraft.getMinecraft().gameSettings.gammaSetting = 1;
			}
			//Execoes
		} else {
			status = true;
			//Execoes
			if(StatusID == Render.brilho){
				Minecraft.getMinecraft().gameSettings.gammaSetting = 10;
			}
			//Execoes
		}
		Status.put(StatusID, status);
		return status;
	}
	public static boolean mudarStatus(String StatusID, boolean novoStatus){
		boolean antigostatus = Status.get(StatusID);
		boolean status = Status.put(StatusID, novoStatus);
		return antigostatus;
	}
	/*
	public static boolean alternarBlockParty(){
		if(BlockParty == true){
			BlockParty = false;
		} else {
			BlockParty = true;
		}
		return BlockParty;
	}
	public static boolean alternarKillAura(){
		if(KillAura == true){
			KillAura = false;
		} else {
			KillAura = true;
		}
		return KillAura;
	}
	public static boolean alternarSemaforo(){
		if(Semaforo == true){
			Semaforo = false;
			br.com.meh.hacks.ghoulhack.minigames.BlockParty.RestaurarPadroes();
		} else {
			Semaforo = true;
		}
		return Semaforo;
	}
	public static boolean alternarNoKnocks(){
		if(NoKnocks == true){
			NoKnocks = false;
		} else {
			NoKnocks = true;
		}
		return NoKnocks;
	}
	public static boolean alternarTeste(){
		if(Teste == true){
			Teste = false;
		} else {
			Teste = true;
		}
		return Teste;
	}
	public static boolean alternarTeste1(){
		if(Teste1 == true){
			Teste1 = false;
		} else {
			Teste1 = true;
		}
		return Teste1;
	}
	public static boolean alternarTeste2(){
		if(Teste2 == true){
			Teste2 = false;
		} else {
			Teste2 = true;
		}
		return Teste2;
	}
	public static boolean alternarFrog() {
		if(Frog == true){
			Frog = false;
		} else {
			Frog = true;
		}
		return Frog;
	}
	public static boolean alternarInfo() {
		if(Info == true){
			Info = false;
		} else {
			Info = true;
		}
		return Info;
	}
	public static boolean alternarDetetive() {
		if(Detetive == true){
			Detetive = false;
		} else {
			Detetive = true;
		}
		return Detetive;
	}
	public static boolean alternarNoSeguera() {
		if(NoSeguera == true){
			NoSeguera = false;
		} else {
			NoSeguera = true;
		}
		return NoSeguera;
	}
	public static boolean alternarLuz() {
		if(Luz == true){
			Luz = false;
			Minecraft.getMinecraft().gameSettings.gammaSetting = 1;
		} else {
			Luz = true;
		}
		return Luz;
	}
	public static boolean alternarForca() {
		if(Forca == true){
			Forca = false;
		} else {
			Forca = true;
		}
		return Forca;
	}
	public static boolean alternarMover() {
		if(Mover == true){
			Mover = false;
		} else {
			Mover = true;
		}
		return Mover;
	}
	public static boolean alternarBPsalvar() {
		if(BPsalvar == true){
			BPsalvar = false;
			br.com.meh.hacks.ghoulhack.minigames.BlockParty.salvo = true;
		} else {
			BPsalvar = true;
		}
		return BPsalvar;
	}
	public static boolean alternarBPmover() {
		if(BPmover == true){
			BPmover = false;
		} else {
			BPmover = true;
		}
		return BPmover;
	}
	public static boolean alternarPrioridadeMax() {
		if(PrioridadeMax == true){
			PrioridadeMax = false;
		} else {
			PrioridadeMax = true;
		}
		return PrioridadeMax;
	}*/
	public static boolean alternarLobbies() {
		int verdadeiro = 0;
		int falso = 0;
		for(Entry<String, Boolean> status_lobby : Lobbies.entrySet()){
			if(status_lobby.getValue()){
				verdadeiro++;
			} else {
				falso++;
			}
		}
		if(verdadeiro > falso){
			for(String key_lobby : Lobbies.keySet()){
				Lobbies.put(key_lobby, false);
			}
			Status.put(MiniGames.AutoLobbyID, false);
		} else if(falso > verdadeiro){
			for(String key_lobby : Lobbies.keySet()){
				Lobbies.put(key_lobby, true);
			}
			Status.put(MiniGames.AutoLobbyID, true);
		} else {
			if(Status.get(MiniGames.AutoLobbyID)){
				for(String key_lobby : Lobbies.keySet()){
					Lobbies.put(key_lobby, false);
				}
				Status.put(MiniGames.AutoLobbyID, false);
			} else {
				for(String key_lobby : Lobbies.keySet()){
					Lobbies.put(key_lobby, true);
				}
				Status.put(MiniGames.AutoLobbyID, true);
			}
		}
		return Status.get(MiniGames.AutoLobbyID);
	}
}
