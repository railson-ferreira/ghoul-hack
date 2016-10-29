package br.com.meh.hacks.ghoulhack.arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.meh.hacks.ghoulhack.util.Dados;
import br.com.meh.hacks.ghoulhack.util.Metodos;

public class Amigos {
	static List<String> Amigos = new ArrayList<String>();
	public static void recarregar(){
		File amigos_arq = new File("GhoulHack/amigos.txt");
		if(amigos_arq.exists()){
		} else {
			try {
				amigos_arq.createNewFile();
			} catch (IOException e) {e.printStackTrace();}
		}
		//Ler Arquivo
		FileReader fr = null;
		try {
			fr = new FileReader(amigos_arq);
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
		Amigos.clear();
		for(String amigo : linhasLidas){
			Amigos.add(amigo);
		}
	}
	public static List<String> pegarAmigos() {
		return Amigos;
	}
}
