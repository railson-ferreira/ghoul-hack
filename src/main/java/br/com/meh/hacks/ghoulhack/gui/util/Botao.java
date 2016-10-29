package br.com.meh.hacks.ghoulhack.gui.util;

import br.com.meh.hacks.ghoulhack.util.Metodos;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class Botao extends Gui {
	public final String ID;
	String texto = "";
	String texto_hover = "";
	boolean centralizarTexto = false;
	boolean sombrearTexto = true;
	int x;
	int y;
	int largura;
	int altura;
	int background_cor = 0x696969;
	int background_cor_hover = 0x363636;
	int texto_cor = 0xFFFFFF;
	int texto_cor_hover = 0x000000;
	int texto_hover_cor = 0xFFF;
	int alpha = (int)(50*2.55D);
	int alpha_hover = (int)(50*2.55D);
	int margem = 0;
	FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
	BotaoEventListener manipulador = null;
	public Botao(int x, int y, int largura, int altura, String ID){
		this.ID = ID;
		this.x = x;
		this.y = y;
		this.largura = largura;
		this.altura = altura;
	}
	public Botao(int x, int y, int largura, int altura, String ID, String texto, boolean centralizarTexto, boolean sombrearTexto){
		this.ID = ID;
		this.texto = texto;
		this.centralizarTexto = centralizarTexto;
		this.sombrearTexto = sombrearTexto;
		this.x = x;
		this.y = y;
		this.largura = largura;
		this.altura = altura;
	}
	public Botao(int x, int y, int largura, int altura, BotaoInformacoes botaoInformacoes, boolean centralizarTexto, boolean sombrearTexto){
		this.centralizarTexto = centralizarTexto;
		this.sombrearTexto = sombrearTexto;
		this.x = x;
		this.y = y;
		this.largura = largura;
		this.altura = altura;
		this.ID = botaoInformacoes.ID;
		this.texto = botaoInformacoes.texto;
		this.texto_cor = botaoInformacoes.texto_cor;
		this.texto_cor_hover = botaoInformacoes.texto_cor_hover;
		this.texto_hover = botaoInformacoes.texto_hover;
		this.texto_hover_cor = botaoInformacoes.texto_hover_cor;
		this.background_cor = botaoInformacoes.background_cor;
		this.background_cor_hover = botaoInformacoes.background_cor_hover;
	}

	public void defineAlpha(int valor){alpha = valor; alpha_hover = valor;}
	public void defineApenasAlpha(int valor){alpha = valor;}
	public void defineApenasAlphaHover(int valor){alpha_hover = valor;}
	public void defineCor(int valor){background_cor = valor;}
	public void defineCorHover(int valor){background_cor_hover = valor;}
	public void defineTextoCor(int valor){texto_cor = valor;}
	public void defineTextoCorHover(int valor){texto_cor_hover = valor;}
	public void defineMargem(int valor){margem = valor;}
	public void defineMsgNoHover(String valor, int cor){texto_hover = valor;texto_hover_cor = cor;}

	public int pegarAlpha(){return alpha;}
	public int pegarAlphaHover(){return alpha_hover;}
	public int pegarCor(){return background_cor;}
	public int pegarCorHover(){return background_cor_hover;}
	public int pegarTextoCor(){return texto_cor;}
	public int pegarTextoCorHover(){return texto_cor_hover;}
	public int pegarMargem(){return margem;}
	public String pegarMsgNoHover(){return texto_hover;}
	public int pegarMsgNoHoverCor(){return texto_hover_cor;}
	

	public void addManipuladorDeEvento(BotaoEventListener manipulador){
		this.manipulador = manipulador;
	}
	

	public void desenhar(int mousex, int mousey){
		if(x <= mousex && mousex <= x+largura && y <= mousey && mousey <= y+altura){
			Desenhador.desenharRetanguloTransparente(x, y, largura, altura, background_cor_hover, alpha_hover);
			if(centralizarTexto){
				Desenhador.desenharTextoCentralizado(texto, x+largura/2, y+altura/2, texto_cor, sombrearTexto);
			} else {
				Desenhador.desenharTextoCentralizadoNoY(texto, x+margem, y+altura/2, texto_cor, sombrearTexto);
			}
			fr.drawString(texto_hover, mousex, mousey-fr.FONT_HEIGHT, 0xFFF);
		} else {
			Desenhador.desenharRetanguloTransparente(x, y, largura, altura, background_cor, alpha);
			if(centralizarTexto){
				Desenhador.desenharTextoCentralizado(texto, x+largura/2, y+altura/2, texto_cor, sombrearTexto);
			} else {
				Desenhador.desenharTextoCentralizadoNoY(texto, x+margem, y+altura/2, texto_cor, sombrearTexto);
			}
		}
	}
	public void desenhar(){
		Desenhador.desenharRetanguloTransparente(x, y, largura, altura, background_cor, alpha);
		if(centralizarTexto){
			Desenhador.desenharTextoCentralizado(texto, x+largura/2, y+altura/2, texto_cor, sombrearTexto);
		} else {
			Desenhador.desenharTextoCentralizadoNoY(texto, x+margem, y+altura/2, texto_cor, sombrearTexto);
		}
	}
	
	public boolean mouseClicked(int x, int y, int which){
		if(this.x <= x && x <= this.x+largura && this.y <= y && y <= this.y+altura){
			if(manipulador != null){
				manipulador.clickEvent(this);
			}
			new BotaoEventListener().clickEvent(this);
			return true;
		}
		return false;
	}
	
	public void restaurarValores(){
		background_cor = 0x000000;
		background_cor_hover = 0xFFFFFF;
		texto_cor = 0xFFFFFF;
		texto_cor_hover = 0x000000;
		alpha = (int)(50+2.55);
	}
	
}
