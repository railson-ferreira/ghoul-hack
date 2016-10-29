package br.com.meh.hacks.ghoulhack.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import br.com.meh.hacks.ghoulhack.Status;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class Metodos {
	public static String introducao(){
		//tempo segundos[000000][000]milesimos
		java.util.Date agora = new java.util.Date();
		String hor = agora.getHours()+"";
		String min = agora.getMinutes()+"";
		String seg = agora.getSeconds()+"";
		if(hor.length() < 2){hor = "0"+hor;}
		if(min.length() < 2){min = "0"+min;}
		if(seg.length() < 2){seg = "0"+seg;}
		String retorno = "["+hor+":"+min+":"+seg+"]"+" [GhoulHack]: ";
		return retorno;
	}
	public static void escreverNoConsole(String texto){
		System.out.println(introducao()+texto);
	}
	public static boolean VerificarIntNFE(String[] numeros){
		for(String numero : numeros){
			int num;
			try {
				num = Integer.parseInt(numero);
			} catch (NumberFormatException e) {return false;}
		}
		return true;
	}
	public static int converteInt(String numero){
		int retorno = 0;
		try {
			retorno = Integer.parseInt(numero);
		} catch (NumberFormatException e) {System.out.println(e);}
		return retorno;
	}
	public static double converteDouble(String numero){
		double retorno = 0;
		try {
			retorno = Double.parseDouble(numero);
		} catch (NumberFormatException e) {System.out.println(e);}
		return retorno;
	}
	public static long converteLong(String numero){
		long retorno = 0;
		try {
			retorno = Long.parseLong(numero);
		} catch (NumberFormatException e) {System.out.println(e);}
		return retorno;
	}
	public static void PularPlayer(EntityPlayer player){
		player.getLookVec();
	}
	public static void EnviarMensagemPlayer(String mensagem){
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(mensagem));
	}
	public static void EnviarMensagemPlayer(EntityPlayer player, String mensagem){
		player.addChatMessage(new ChatComponentText(mensagem));
	}
	public static int arredondarDoublePraCima(double valor){
		if(valor - (int)valor > 0){
			return (int)valor+1;
		}
		return (int)valor;
	}
	public static String pegarFonteHTML(String link){
		String retorno = "";
		URL url = null;
		try {
			url = new URL(link);
		} catch (MalformedURLException e) {e.printStackTrace();}   
		try {
			URLConnection conn = url.openConnection();
		} catch (IOException e) {e.printStackTrace();}   
		  
		InputStream is = null;
		try {
			is = url.openStream();
		} catch (IOException e) {e.printStackTrace();}   
		InputStreamReader isr = new InputStreamReader(is);   
		BufferedReader br = new BufferedReader(isr);  

		try {
			while(br.ready()){
				String linha = br.readLine();
				retorno += linha+"\n";
			}
		} catch (IOException e) {e.printStackTrace();}
		return retorno;
	}
	public static String pegarDemarcacao(String texto, String marcacao1, String marcacao2){
		String retorno = texto.substring(texto.indexOf(marcacao1) + marcacao1.length(), texto.indexOf(marcacao2, texto.indexOf(marcacao1) + marcacao1.length()));
		return retorno;
	}
	public static String pegarJsonRawDeHTML(String htmlJson){
		String retorno = htmlJson;
		String[] tags = {"<br>", "&nbsp;"};
		for(String tag : tags){
			retorno = retorno.replace(tag, "");
		}
		retorno = retorno.substring(retorno.indexOf("{"), retorno.lastIndexOf("}") + 1);
		return retorno;
	}
}
