package br.com.meh.hacks.ghoulhack.gui.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class ListaBotao{
	String textoDeCima;
	int antigoX;
	int antigoY;
	int x;
	int y;
	int minLargura;
	int corDeCima;
	int corDoCorpo;
	int textoDeCima_cor;
	int alpha;
	int larguraDosBotoes;
	int alturaDosBotoes;
	int alturaDeCima;
	Botao liga_desliga;
	Botao mostra_esconde;
	int minX;
	int minY;
	int maxX;
	int maxY;
	BotaoEventListener BEL;

	public boolean doisBotoes = false;
	Botao liga_desliga2;
	
	public boolean ativado = false;
	public boolean amostra = true;
	
	List<Botao> botoes = new ArrayList<Botao>();
	
	
	FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
	public ListaBotao(String textoDeCima, int x, int y, int minLargura, int corDeCima, int corDoCorpo, int textoDeCima_cor, int alpha, boolean ativado, boolean amostra, BotaoEventListener BEL){
		this.textoDeCima = textoDeCima;
		this.x = x;
		this.y = y;
		this.antigoX = x;
		this.antigoY = y;
		this.minLargura = minLargura;
		this.corDeCima = corDeCima;
		this.corDoCorpo = corDoCorpo;
		this.textoDeCima_cor = textoDeCima_cor;
		this.alpha = alpha;
		this.ativado = ativado;
		this.amostra = amostra;
		alturaDeCima = fr.FONT_HEIGHT+4;
		alturaDosBotoes = alturaDeCima-2;
		larguraDosBotoes = alturaDeCima/2;
		criarBotoes();
		minX = x;
		minY = y;
		maxX = x;
		maxY = y;
		this.BEL = BEL;
	}
	public ListaBotao(String textoDeCima, int x, int y, int minLargura, int corDeCima, int corDoCorpo, int textoDeCima_cor, int alpha, boolean ativado, boolean amostra){
		this.textoDeCima = textoDeCima;
		this.x = x;
		this.y = y;
		this.minLargura = minLargura;
		this.corDeCima = corDeCima;
		this.corDoCorpo = corDoCorpo;
		this.textoDeCima_cor = textoDeCima_cor;
		this.alpha = alpha;
		this.ativado = ativado;
		this.amostra = amostra;
		alturaDeCima = fr.FONT_HEIGHT+4;
		alturaDosBotoes = alturaDeCima-2;
		larguraDosBotoes = alturaDeCima/2;
		criarBotoes();
		maxX = x;
		maxY = y;
		this.BEL = null;
	}

	private void criarBotoes() {
		liga_desliga = new Botao(x+1, y+1, larguraDosBotoes-2, alturaDosBotoes, "liga_desliga");
		liga_desliga.defineApenasAlpha(alpha);
		liga_desliga.defineApenasAlphaHover(255);
		liga_desliga.addManipuladorDeEvento(BEL);
		mostra_esconde = new Botao(x+larguraDosBotoes+1, y+1/*(y+alturaDeCima-1-ladoDoBotao)*/, larguraDosBotoes-2, alturaDosBotoes, "mostra_esconde");
		mostra_esconde.defineApenasAlpha(alpha);
		mostra_esconde.defineApenasAlphaHover(255);
		mostra_esconde.addManipuladorDeEvento(BEL);
		liga_desliga2 = new Botao(x+1, y+1, larguraDosBotoes-2, alturaDosBotoes, "liga_desliga");
		liga_desliga2.defineApenasAlpha(alpha);
		liga_desliga2.defineApenasAlphaHover(255);
		liga_desliga2.addManipuladorDeEvento(BEL);
		atualizaBotoes();
	}

	public void desenhar(int mouseX, int mouseY, int telaLargura, int telaAltura, Collection<BotaoInformacoes> collection){
		if(fr.getStringWidth(textoDeCima)+4+larguraDosBotoes*2 > minLargura){
			minLargura = fr.getStringWidth(textoDeCima)+4+larguraDosBotoes*2;
		}
		int larguraMax = minLargura;
		int AlturaMax = alturaDeCima;
		if(amostra)
			AlturaMax += 2;
		for(BotaoInformacoes btn_info : collection){
			if(fr.getStringWidth(btn_info.texto)+4 > larguraMax){
				larguraMax = fr.getStringWidth(btn_info.texto)+4;
			}
			AlturaMax += fr.FONT_HEIGHT+6;
		}
		if(x+larguraMax > telaLargura-2)
			x -= (x+larguraMax)-(telaLargura-2);
		if(y+AlturaMax > telaAltura-2)
			y -= (y+AlturaMax)-(telaAltura-2);
		int y = this.y;
		liga_desliga.x = x+1;
		liga_desliga.y = y+1;
		mostra_esconde.x = x+larguraDosBotoes+1;
		mostra_esconde.y = y+1;
		liga_desliga2.x = x+1;
		liga_desliga2.y = y+1;
		if(doisBotoes){
			larguraDosBotoes = alturaDeCima/2;
		} else {
			larguraDosBotoes = alturaDeCima/4;
		}
		// Desenhar a parte de cima
		Desenhador.desenharRetanguloTransparente(x+larguraDosBotoes*2, y, larguraMax-larguraDosBotoes*2, alturaDeCima, corDeCima, alpha);
		fr.drawStringWithShadow(textoDeCima, x+larguraDosBotoes*2+2, y+2, textoDeCima_cor);
		desenharBotoes(x, y, mouseX, mouseY);
		y += alturaDeCima;
		// Desenhar a parte de baixo
		if(!amostra)
			return;
		Desenhador.desenharRetanguloTransparente(x, y, larguraMax, 2, corDoCorpo, alpha);
		y += 2;
		botoes.clear();
		for(BotaoInformacoes btn_info : collection){
			int btnX = x+2;
			int btnY = y;
			int btnLargura = larguraMax-4;
			int btnAltura = fr.FONT_HEIGHT+4;
			Desenhador.desenharRetanguloTransparente(x, y, larguraMax, fr.FONT_HEIGHT+4+2, corDoCorpo, alpha);
			Botao botao = new Botao(btnX, btnY, btnLargura, btnAltura, btn_info.ID, btn_info.texto, true, false);
			botao.defineApenasAlpha(0);
			botao.defineApenasAlphaHover((int)(10*2.55));
			botao.defineCor(corDoCorpo);
			botao.defineCorHover(corDoCorpo);
			botao.defineTextoCor(btn_info.texto_cor);
			botao.defineTextoCorHover(btn_info.texto_cor_hover);
			botao.addManipuladorDeEvento(BEL);
			botao.desenhar(mouseX, mouseY);
			y += fr.FONT_HEIGHT+6;
			botoes.add(botao);
		}
		minX = x;
		minY = y;
		x = antigoX;
		y = antigoY;
		maxX = x+larguraMax;
		maxY = y;
		
	}
	public void desenhar(int telaLargura, int telaAltura, Collection<BotaoInformacoes> btns_info){
		if(fr.getStringWidth(textoDeCima)+4+larguraDosBotoes*2 > minLargura){
			minLargura = fr.getStringWidth(textoDeCima)+4+larguraDosBotoes*2;
		}
		int larguraMax = minLargura;
		int AlturaMax = alturaDeCima;
		if(amostra)
			AlturaMax += 2;
		for(BotaoInformacoes btn_info : btns_info){
			if(fr.getStringWidth(btn_info.texto)+4 > larguraMax){
				larguraMax = fr.getStringWidth(btn_info.texto)+4;
			}
			AlturaMax += fr.FONT_HEIGHT+6;
		}
		if(x+larguraMax > telaLargura-2)
			x -= (x+larguraMax)-(telaLargura-2);
		if(y+AlturaMax > telaAltura-2)
			y -= (y+AlturaMax)-(telaAltura-2);
		int y = this.y;
		liga_desliga.x = x+1;
		liga_desliga.y = y+1;
		mostra_esconde.x = x+larguraDosBotoes+1;
		mostra_esconde.y = y+1;
		liga_desliga2.x = x+1;
		liga_desliga2.y = y+1;
		if(doisBotoes){
			larguraDosBotoes = alturaDeCima/2;
		} else {
			larguraDosBotoes = alturaDeCima/4;
		}
		// Desenhar a parte de cima
		Desenhador.desenharRetanguloTransparente(x+larguraDosBotoes*2, y, larguraMax-larguraDosBotoes*2, alturaDeCima, corDeCima, alpha);
		fr.drawStringWithShadow(textoDeCima, x+larguraDosBotoes*2+2, y+2, textoDeCima_cor);
		desenharBotoes(x, y);
		y += alturaDeCima;
		// Desenhar a parte de baixo
		if(!amostra)
			return;
		Desenhador.desenharRetanguloTransparente(x, y, larguraMax, 2, corDoCorpo, alpha);
		y += 2;
		botoes.clear();
		for(BotaoInformacoes btn_info : btns_info){
			int btnX = x+2;
			int btnY = y;
			int btnLargura = larguraMax-4;
			int btnAltura = fr.FONT_HEIGHT+4;
			Desenhador.desenharRetanguloTransparente(x, y, larguraMax, fr.FONT_HEIGHT+4+2, corDoCorpo, alpha);
			Botao botao = new Botao(btnX, btnY, btnLargura, btnAltura, btn_info.ID, btn_info.texto, true, false);
			botao.defineApenasAlpha(0);
			botao.defineApenasAlphaHover((int)(10*2.55));
			botao.defineCor(corDoCorpo);
			botao.defineCorHover(corDoCorpo);
			botao.defineTextoCor(btn_info.texto_cor);
			botao.defineTextoCorHover(btn_info.texto_cor_hover);
			botao.addManipuladorDeEvento(BEL);
			botao.desenhar();
			y += fr.FONT_HEIGHT+6;
			botoes.add(botao);
		}
		minX = x;
		minY = y;
		x = antigoX;
		y = antigoY;
		maxX = x+larguraMax;
		maxY = y;
		
	}
	private void desenharBotoes(int x, int y, int mouseX, int mouseY){
		if(doisBotoes){
			//Desenhar primeiro o acabamento
			Desenhador.desenharRetanguloTransparente(x, y, larguraDosBotoes*2, (alturaDeCima-alturaDosBotoes)/2, corDeCima, alpha);
			Desenhador.desenharRetanguloTransparente(x, y+alturaDeCima-1, larguraDosBotoes*2, (alturaDeCima-alturaDosBotoes)/2, corDeCima, alpha);
			Desenhador.desenharRetanguloTransparente(x, y+1, 1, alturaDeCima-2, corDeCima, alpha);
			Desenhador.desenharRetanguloTransparente(x+larguraDosBotoes-1, y+1, 2, alturaDeCima-2, corDeCima, alpha);
			Desenhador.desenharRetanguloTransparente(x+larguraDosBotoes*2-1, y+1, 1, alturaDeCima-2, corDeCima, alpha);
			//Depois os botoes
			atualizaBotoes();
			liga_desliga.desenhar(mouseX, mouseY);
			mostra_esconde.desenhar(mouseX, mouseY);
		} else {
			//Desenhar primeiro o acabamento
			Desenhador.desenharRetanguloTransparente(x, y, larguraDosBotoes*2, (alturaDeCima-alturaDosBotoes)/2, corDeCima, alpha);
			Desenhador.desenharRetanguloTransparente(x, y+alturaDeCima-1, larguraDosBotoes*2, (alturaDeCima-alturaDosBotoes)/2, corDeCima, alpha);
			Desenhador.desenharRetanguloTransparente(x, y+1, 1, alturaDeCima-2, corDeCima, alpha);
			Desenhador.desenharRetanguloTransparente(x+larguraDosBotoes*2-1, y+1, 1, alturaDeCima-2, corDeCima, alpha);
			//Depois o botao
			atualizaBotoes();
			liga_desliga2.desenhar(mouseX, mouseY);
		}
	}
	private void desenharBotoes(int x, int y){
		if(doisBotoes){
			//Desenhar primeiro o acabamento
			Desenhador.desenharRetanguloTransparente(x, y, larguraDosBotoes*2, (alturaDeCima-alturaDosBotoes)/2, corDeCima, alpha);
			Desenhador.desenharRetanguloTransparente(x, y+alturaDeCima-1, larguraDosBotoes*2, (alturaDeCima-alturaDosBotoes)/2, corDeCima, alpha);
			Desenhador.desenharRetanguloTransparente(x, y+1, 1, alturaDeCima-2, corDeCima, alpha);
			Desenhador.desenharRetanguloTransparente(x+larguraDosBotoes-1, y+1, 2, alturaDeCima-2, corDeCima, alpha);
			Desenhador.desenharRetanguloTransparente(x+larguraDosBotoes*2-1, y+1, 1, alturaDeCima-2, corDeCima, alpha);
			//Depois os botoes
			atualizaBotoes();
			liga_desliga.desenhar();
			mostra_esconde.desenhar();
		} else {
			//Desenhar primeiro o acabamento
			Desenhador.desenharRetanguloTransparente(x, y, larguraDosBotoes*2, (alturaDeCima-alturaDosBotoes)/2, corDeCima, alpha);
			Desenhador.desenharRetanguloTransparente(x, y+alturaDeCima-1, larguraDosBotoes*2, (alturaDeCima-alturaDosBotoes)/2, corDeCima, alpha);
			Desenhador.desenharRetanguloTransparente(x, y+1, 1, alturaDeCima-2, corDeCima, alpha);
			Desenhador.desenharRetanguloTransparente(x+larguraDosBotoes*2-1, y+1, 1, alturaDeCima-2, corDeCima, alpha);
			//Depois o botao
			atualizaBotoes();
			liga_desliga2.desenhar();
		}
	}
	public List<Botao> pegarBotoes(){
		return botoes;
	}
	private void atualizaBotoes(){
		if(ativado){
			liga_desliga.defineCor(0x00FF00);
			liga_desliga.defineCorHover(0x00FF00);
			liga_desliga.defineMsgNoHover("desligar", 0xFFFFFF);
			liga_desliga2.defineCor(0x00FF00);
			liga_desliga2.defineCorHover(0x00FF00);
			liga_desliga2.defineMsgNoHover("desligar", 0xFFFFFF);
		} else{
			liga_desliga.defineCor(0xFF0000);
			liga_desliga.defineCorHover(0xFF0000);
			liga_desliga.defineMsgNoHover("ligar", 0xFFFFFF);
			
			liga_desliga2.defineCor(0xFF0000);
			liga_desliga2.defineCorHover(0xFF0000);
			liga_desliga2.defineMsgNoHover("ligar", 0xFFFFFF);
		}
		if(amostra){
			mostra_esconde.defineCor(0x00FF00);
			mostra_esconde.defineCorHover(0x00FF00);
			mostra_esconde.defineMsgNoHover("esconder", 0xFFFFFF);
		} else{
			mostra_esconde.defineCor(0xFF0000);
			mostra_esconde.defineCorHover(0xFF0000);
			mostra_esconde.defineMsgNoHover("mostrar", 0xFFFFFF);
		}
	}
    public void mouseClicked(int x, int y, int which) {
    	if(doisBotoes){
        	if(liga_desliga.mouseClicked(x, y, which))
        		alternarLigaDesliga();
        	if(mostra_esconde.mouseClicked(x, y, which))
        		alternarMostraEsconde();
        } else {
        	if(liga_desliga2.mouseClicked(x, y, which))
        		alternarLigaDesliga();
        }
    	for(Botao botao : botoes){
    		botao.mouseClicked(x, y, which);
    	}
    }
    private boolean alternarLigaDesliga(){
    	if(ativado)
    		return ativado = false;
    	else
    		return ativado = true;
    }
    private boolean alternarMostraEsconde(){
    	if(amostra)
    		return amostra = false;
    	else
    		return amostra = true;
    }
}
