package br.com.meh.hacks.ghoulhack.arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import br.com.meh.hacks.ghoulhack.util.Dados;
import br.com.meh.hacks.ghoulhack.util.Metodos;

public class Delay {
	public static void recarregar(){
		File delay_arq = new File("GhoulHack/delay.txt");
		if(delay_arq.exists()){
		} else {
			try {
				delay_arq.createNewFile();
			} catch (IOException e) {e.printStackTrace();}
		}
		//Ler Arquivo
		FileReader fr = null;
		try {
			fr = new FileReader(delay_arq);
		} catch (FileNotFoundException e) {e.printStackTrace();}
		BufferedReader br = new BufferedReader(fr);
		String linha1 = "1000";
		String linha2 = "0.05";
		try {
			if(br.ready()){
				linha1 = br.readLine();
			} else {
				//criar linha padrao1 '-'
			}
			if(br.ready()){
				linha2 = br.readLine();
			} else {
				//criar linha padrao2 '-'
			}
		} catch (IOException e) {e.printStackTrace();}
		try {
			br.close();
		} catch (IOException e4) {e4.printStackTrace();}
		try {
			fr.close();
		} catch (IOException e3) {e3.printStackTrace();}
		Dados.delay1 = Metodos.converteLong(linha1);
		Dados.delay2 = Metodos.converteDouble(linha2);
	}
}
