package br.com.meh.hacks.ghoulhack.gui.util;

public class BotaoInformacoes {
	public final String ID;
	public String texto;
	public int texto_cor;
	public int texto_cor_hover;
	public String texto_hover;
	public int texto_hover_cor;
	public int background_cor;
	public int background_cor_hover;

	public BotaoInformacoes(String ID, String texto, int texto_cor, int texto_cor_hover, String texto_hover, int texto_hover_cor, int backgroud_cor, int backgroud_cor_hover){
		this.ID = ID;
		this.texto = texto;
		this.texto_cor = texto_cor;
		this.texto_cor_hover = texto_cor_hover;
		this.texto_hover = texto_hover;
		this.texto_hover_cor = texto_hover_cor;
		this.background_cor = backgroud_cor;
		this.background_cor_hover = backgroud_cor_hover;
	}
	public BotaoInformacoes(String ID, String texto, int texto_cor, int texto_cor_hover){
		this.ID = ID;
		this.texto = texto;
		this.texto_cor = texto_cor;
		this.texto_cor_hover = texto_cor_hover;
		this.texto_hover = "";
		this.texto_hover_cor = 0xFFF;
		this.background_cor = 0X696969;
		this.background_cor_hover = 0X363636;
	}
	
	
}
