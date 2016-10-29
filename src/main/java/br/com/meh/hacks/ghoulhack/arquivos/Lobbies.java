package br.com.meh.hacks.ghoulhack.arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.meh.hacks.ghoulhack.Status;
import br.com.meh.hacks.ghoulhack.util.AreaSelecao;
import br.com.meh.hacks.ghoulhack.util.Cordenadas;
import br.com.meh.hacks.ghoulhack.util.Lobby;
import br.com.meh.hacks.ghoulhack.util.Metodos;

public class Lobbies {
	private static HashMap<String, Lobby> lobbies = new HashMap<String, Lobby>();
	public static HashMap<String, Lobby> pegarLobbies(){
		return lobbies;
	}
	public static void recarregar(){
		Status.Lobbies.clear();
		lobbies.clear();
		//Carregar ou criar arquivo
		File pasta = new File("GhoulHack/MiniGames");
		if(pasta.exists()){
			if(!pasta.isDirectory()){
				pasta.mkdir();
			}
		} else {
			pasta.mkdir();
		}
		File Lobbies_pasta = new File("GhoulHack/MiniGames/Lobbies");
		if(Lobbies_pasta.exists()){
			if(!Lobbies_pasta.isDirectory()){
				Lobbies_pasta.mkdir();
			}
		} else {
			Lobbies_pasta.mkdir();
		}
		File[] lobbies_arqs = new File("GhoulHack/MiniGames/Lobbies").listFiles();
		for(File lobby : lobbies_arqs){
			if(lobby.isFile()){
				String nome = lobby.getName();
				boolean status = false;
				AreaSelecao area;
				List<Cordenadas> placas = new ArrayList<Cordenadas>();
				
				List<String> linhasLidas = new ArrayList<String>();
				List<String> linhasCorretas = new ArrayList<String>();
				//Ler Arquivo
				FileReader fr = null;
				try {
					fr = new FileReader(lobby);
				} catch (FileNotFoundException e) {e.printStackTrace();}
				BufferedReader br = new BufferedReader(fr);
				try {
					while(br.ready()){
						String linha = br.readLine();
						linhasLidas.add(linha);
					}
				} catch (IOException e) {e.printStackTrace();}
				try {
					br.close();
				} catch (IOException e4) {e4.printStackTrace();}
				try {
					fr.close();
				} catch (IOException e3) {e3.printStackTrace();}
				//Corrigir falhas
				if(linhasLidas.size() < 2){
					lobby.delete();
					continue;
				} else {
					String status_nome = linhasLidas.get(0);
					if(status_nome.equalsIgnoreCase("true")){
						status = true;
						linhasCorretas.add(linhasLidas.get(0));
					} else if(status_nome.equalsIgnoreCase("false")){
						status = false;
						linhasCorretas.add(linhasLidas.get(0));
					} else {
						lobby.delete();
						continue;
					}
					if(linhasLidas.get(1).indexOf(",") >= 0){
						String[] cords = linhasLidas.get(1).split(",");
						if(cords.length == 2){
							String[] cord1 = cords[0].split(" ");
							String[] cord2 = cords[1].split(" ");
							if(cord1.length == 3 && cord2.length == 3){
								String[] numeros = {cord1[0],cord1[1],cord1[2],cord2[0],cord2[1],cord2[2],};
								if(Metodos.VerificarIntNFE(numeros)){
									double x1 = Metodos.converteDouble(cord1[0]);
									double y1 = Metodos.converteDouble(cord1[1]);
									double z1 = Metodos.converteDouble(cord1[2]);
									double x2 = Metodos.converteDouble(cord2[0]);
									double y2 = Metodos.converteDouble(cord2[1]);
									double z2 = Metodos.converteDouble(cord2[2]);
									Cordenadas pos1 = new Cordenadas(x1, y1, z1, 0, 0);
									Cordenadas pos2 = new Cordenadas(x2, y2, z2, 0, 0);
									area = new AreaSelecao(pos1, pos2);
								    linhasCorretas.add(linhasLidas.get(1));
								} else {
									lobby.delete();
									continue;
								}
							} else {
								lobby.delete();
								continue;
							}
						} else {
							lobby.delete();
							continue;
						}
					} else {
						lobby.delete();
						continue;
					}
					int cont = 0;
					for(String linha : linhasLidas){
						if(cont == 2){
							String[] cords = linha.split(" ");
							if(cords.length == 3){
								String[] numeros = {cords[0],cords[1],cords[2]};
								if(Metodos.VerificarIntNFE(numeros)){
									double x = Metodos.converteDouble(cords[0]);
									double y = Metodos.converteDouble(cords[1]);
									double z = Metodos.converteDouble(cords[2]);
									placas.add(new Cordenadas(x, y, z, 0, 0));
								    linhasCorretas.add(linha);
								}
							}				
						} else {
							cont++;
						}
					}
					//Gravar novo arquivo com falhas corrigidas
					FileWriter fw = null;
					try {
						fw = new FileWriter(lobby);
					} catch (IOException e1) {e1.printStackTrace();}
					BufferedWriter bw = new BufferedWriter(fw);
					for(String linha : linhasCorretas){
						try {
							bw.write(linha+"\n");
						} catch (IOException e) {e.printStackTrace();}
					}
					try {
						bw.close();
					} catch (IOException e2) {e2.printStackTrace();}
					try {
						fw.close();
					} catch (IOException e1) {e1.printStackTrace();}
					//Salvas Dados em um HashMap<>
					Lobby Lobby = new Lobby(area, placas);
					lobbies.put(nome, Lobby);
					//Restaurar Status
					Status.Lobbies.put(nome, status);
				}
			}
		}		
	}
}
