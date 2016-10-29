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

import net.minecraft.util.ChunkCoordinates;

import br.com.meh.hacks.ghoulhack.GhoulHack;
import br.com.meh.hacks.ghoulhack.util.AreaSelecao;
import br.com.meh.hacks.ghoulhack.util.Cordenadas;
import br.com.meh.hacks.ghoulhack.util.Metodos;

public class AreasBlockParty{
	private static File arquivo;
	private static HashMap<String, AreaSelecao> areas = new HashMap<String, AreaSelecao>();
	public static void recarregar(){
		//Carregar ou criar arquivo
		File pasta = new File("GhoulHack/MiniGames");
		if(pasta.exists()){
			if(!pasta.isDirectory()){
				pasta.mkdir();
				//return;
			}
		} else {
			pasta.mkdir();
			//return;
		}
		File arq = new File("GhoulHack/MiniGames/AreasBlockParty.ghoul");
		if(arq.exists()){
			arquivo = arq;
		} else {
			System.out.println("Arquivo 'GhoulHack/MiniGames/AreasBlockParty.ghoul' nao existe, Criando Arquivo...");
			try {
				arq.createNewFile();
				System.out.println("Arquivo 'GhoulHack/MiniGames/AreasBlockParty.ghoul' Criado Com Sucesso");
			} catch (IOException e) {e.printStackTrace();}
			arquivo = arq;
			//return;
		}
		//Ler Arquivo
		FileReader fr = null;
		try {
			fr = new FileReader(arquivo);
		} catch (FileNotFoundException e) {e.printStackTrace();}
		BufferedReader br = new BufferedReader(fr);
		List<String> linhasLidas = new ArrayList<String>();
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
		List<String> linhasCorretas = new ArrayList<String>();
		for(String linha : linhasLidas){
			if(linha.indexOf(":") >= 0){
				if(linha.indexOf(",") >= 0){
					String[] areaPartes = linha.split(":");
					if(areaPartes.length == 2){
						String[] cords = areaPartes[1].split(",");
						if(cords.length == 2){
							String[] cord1 = cords[0].split(" ");
							String[] cord2 = cords[1].split(" ");
							if(cord1.length == 3 && cord2.length == 3){
								String[] numeros = {cord1[0],cord1[1],cord1[2],cord2[0],cord2[1],cord2[2],};
								if(Metodos.VerificarIntNFE(numeros)){
								    linhasCorretas.add(linha);
								}
							}
						}
					}
				}				
			}
		}
		//Gravar novo arquivo com falhas corrigidas
		FileWriter fw = null;
		try {
			fw = new FileWriter(arquivo);
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
		for(String linha : linhasCorretas){
			String[] areaPartes = linha.split(":");
			if(areaPartes.length == 2){
				String[] cords = areaPartes[1].split(",");
				if(cords.length == 2){
					String[] cord1 = cords[0].split(" ");
					String[] cord2 = cords[1].split(" ");
					if(cord1.length == 3 && cord2.length == 3){
						String[] numeros = {cord1[0],cord1[1],cord1[2],cord2[0],cord2[1],cord2[2]};
						if(Metodos.VerificarIntNFE(numeros)){
							int X1 = Metodos.converteInt(cord1[0]);
							int Y1 = Metodos.converteInt(cord1[1]);
							int Z1 = Metodos.converteInt(cord1[2]);
							int X2 = Metodos.converteInt(cord2[0]);
							int Y2 = Metodos.converteInt(cord2[1]);
							int Z2 = Metodos.converteInt(cord2[2]);
							Cordenadas pos1 = new Cordenadas(X1, Y1, Z1,0,0);
							Cordenadas pos2 = new Cordenadas(X2, Y2, Z2,0,0);
							areas.put(areaPartes[0], new AreaSelecao(pos1, pos2));
							Metodos.escreverNoConsole("Area "+areaPartes[0]+" carregada com sucesso.");
						} else {
							Metodos.escreverNoConsole("Algo parece errado no arquivo 'GhoulHack/MiniGames/AreasBlockParty.ghoul'");
						}
					} else {
						Metodos.escreverNoConsole("Algo parece errado no arquivo 'GhoulHack/MiniGames/AreasBlockParty.ghoul'");
					}
				} else {
					Metodos.escreverNoConsole("Algo parece errado no arquivo 'GhoulHack/MiniGames/AreasBlockParty.ghoul'");
				}
			} else {
				Metodos.escreverNoConsole("Algo parece errado no arquivo 'GhoulHack/MiniGames/AreasBlockParty.ghoul'");
			}
		}
		
	}
	public static HashMap<String, AreaSelecao> Pegar(){
		return areas;
	}
	public static void Salvar(){
		for(String nome_area : areas.keySet()){
			AreaSelecao cords = areas.get(nome_area);
			Cordenadas posMin = cords.pegarMin();
			Cordenadas posMax = cords.pegarMax();
			int minX = (int)posMin.posX(); int minY = (int)posMin.posY(); int minZ = (int)posMin.posZ();
			int maxX = (int)posMax.posX(); int maxY = (int)posMax.posY(); int maxZ = (int)posMax.posZ();
			FileWriter fw = null;
			try {
				fw = new FileWriter(arquivo);
			} catch (IOException e1) {e1.printStackTrace();}
			BufferedWriter bw = new BufferedWriter(fw);
			try {
				bw.write(nome_area+":"+minX+" "+minY+" "+minZ+","+maxX+" "+maxY+" "+maxZ+"\n");
			} catch (IOException e) {e.printStackTrace();}
			try {
				bw.close();
			} catch (IOException e) {e.printStackTrace();}
			try {
				fw.close();
			} catch (IOException e) {e.printStackTrace();}
		}
	}
	public static void Salvar(HashMap<String, AreaSelecao> areasParaSalvar){
		for(String nome_area : areasParaSalvar.keySet()){
			AreaSelecao cords = areasParaSalvar.get(nome_area);
			Cordenadas posMin = cords.pegarMin();
			Cordenadas posMax = cords.pegarMax();
			int minX = (int)posMin.posX(); int minY = (int)posMin.posY(); int minZ = (int)posMin.posZ();
			int maxX = (int)posMax.posX(); int maxY = (int)posMax.posY(); int maxZ = (int)posMax.posZ();
			FileWriter fw = null;
			try {
				fw = new FileWriter(arquivo);
			} catch (IOException e1) {e1.printStackTrace();}
			BufferedWriter bw = new BufferedWriter(fw);
			try {
				bw.write(nome_area+":"+minX+" "+minY+" "+minZ+","+maxX+" "+maxY+" "+maxZ+"\n");
			} catch (IOException e) {e.printStackTrace();}
			try {
				bw.close();
			} catch (IOException e) {e.printStackTrace();}
			try {
				fw.close();
			} catch (IOException e) {e.printStackTrace();}
		}
	}
}
