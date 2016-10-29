package br.com.meh.hacks.ghoulhack.radar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import br.com.meh.hacks.ghoulhack.gui.util.Botao;
import br.com.meh.hacks.ghoulhack.gui.util.BotaoEventListener;
import br.com.meh.hacks.ghoulhack.gui.util.Desenhador;
import br.com.meh.hacks.ghoulhack.killaura.AimBot;

public class RadarInterface {
	String textoDeCima;
	int antigoX;
	int antigoY;
	int x;
	int y;
	int Largura;
	int Altura;
	int Zoom;
	int corDeCima;
	int corDoCorpo;
	int textoDeCima_cor;
	int larguraDosBotoes;
	int alturaDosBotoes;
	int alturaDeCima;
	int alpha;
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
	FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
	public RadarInterface(String textoDeCima, int x, int y, int Largura, int Altura, int Zoom, int corDeCima, int corDoCorpo, int textoDeCima_cor, int alpha, boolean ativado, boolean amostra){
		this.textoDeCima = textoDeCima;
		this.x = x;
		this.y = y;
		this.Largura = Largura;
		this.Altura = Altura;
		this.Zoom = Zoom;
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
	public RadarInterface(String textoDeCima, int x, int y, int Largura, int Altura, int Zoom, int corDeCima, int corDoCorpo, int textoDeCima_cor, int alpha, boolean ativado, boolean amostra, BotaoEventListener BEL){
		this.textoDeCima = textoDeCima;
		this.x = x;
		this.y = y;
		this.Largura = Largura;
		this.Altura = Altura;
		this.Zoom = Zoom;
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
		this.BEL = BEL;
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
