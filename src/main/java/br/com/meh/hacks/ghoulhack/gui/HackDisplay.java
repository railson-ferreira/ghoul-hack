package br.com.meh.hacks.ghoulhack.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.meh.hacks.ghoulhack.Status;
import br.com.meh.hacks.ghoulhack.gui.util.BotaoInformacoes;
import br.com.meh.hacks.ghoulhack.gui.util.Desenhador;
import br.com.meh.hacks.ghoulhack.gui.util.Lista;
import br.com.meh.hacks.ghoulhack.gui.util.ListaBotao;
import br.com.meh.hacks.ghoulhack.gui.util.TabelaBotao;
import br.com.meh.hacks.ghoulhack.killaura.KillAura;
import br.com.meh.hacks.ghoulhack.radar.Radar;
import br.com.meh.hacks.ghoulhack.status.Combate;
import br.com.meh.hacks.ghoulhack.status.MiniGames;
import br.com.meh.hacks.ghoulhack.status.Nomes;
import br.com.meh.hacks.ghoulhack.status.Outros;
import br.com.meh.hacks.ghoulhack.status.Render;
import br.com.meh.hacks.ghoulhack.status.Teste;
import br.com.meh.hacks.ghoulhack.util.Dados;

public class HackDisplay {
	private int telaLargura;
	private int telaAltura;
	
	private Lista amigoRadar = new Lista("AmigoRadar", 0, 0, 0, 0, 0, 0, 0, false, false, new BotaoEventoManipulador());
	private Lista listaRadar = new Lista("Radar", 0, 0, 0, 0, 0, 0, 0, false, false, new BotaoEventoManipulador());
	private ListaBotao listaHacksAtivados = new ListaBotao("Hacks Ativos", 999, 2, 0, 0x7CFC00, 0xFF0000, 0xFFFFFF, (int)(65*2.55), true, true, new BotaoEventoManipulador());
	private TabelaBotao tabelaMiniGames = new TabelaBotao("MiniGames", 2, 2, 0, 0x7CFC00, 0xFF0000, 0xFFFFFF, (int)(65*2.55), false, true, new BotaoEventoManipulador());
	private TabelaBotao tabelaRender = new TabelaBotao("Render", 152, 2, 0, 0x7CFC00, 0xFF0000, 0xFFFFFF, (int)(65*2.55), false, true, new BotaoEventoManipulador());
	private TabelaBotao tabelaTeste = new TabelaBotao("Testes", 302, 2, 0, 0x7CFC00, 0xFF0000, 0xFFFFFF, (int)(65*2.55), false, true, new BotaoEventoManipulador());
	private TabelaBotao tabelaOutros = new TabelaBotao("Outros", 452, 2, 0, 0x7CFC00, 0xFF0000, 0xFFFFFF, (int)(65*2.55), false, true, new BotaoEventoManipulador());

	private HashMap<String,BotaoInformacoes> listaHacks = new HashMap<String,BotaoInformacoes>();
	
	private List<BotaoInformacoes> HacksAtivados = new ArrayList<BotaoInformacoes>();

	private List<BotaoInformacoes> CombateHacks = new ArrayList<BotaoInformacoes>();
	private List<BotaoInformacoes> MiniGamesHacks = new ArrayList<BotaoInformacoes>();
	private List<BotaoInformacoes> OutrosHacks = new ArrayList<BotaoInformacoes>();
	private List<BotaoInformacoes> RenderHacks = new ArrayList<BotaoInformacoes>();
	private List<BotaoInformacoes> TesteHacks = new ArrayList<BotaoInformacoes>();
	
	public HackDisplay(int telaLargura, int telaAltura){
		this.telaLargura = telaLargura;
		this.telaAltura = telaAltura;
	}
	
	public void inicializar(){
		CombateHacks.clear();
		for(String ID : Combate.pegarIDs()){
			BotaoInformacoes botao = new BotaoInformacoes(ID, Nomes.pegar(ID), 0x000000, 0x000000);
			listaHacks.put(ID, botao);
			CombateHacks.add(botao);
		}
		MiniGamesHacks.clear();
		for(String ID : MiniGames.pegarIDs()){
			BotaoInformacoes botao = new BotaoInformacoes(ID, Nomes.pegar(ID), 0x000000, 0x000000);
			listaHacks.put(ID, botao);
			MiniGamesHacks.add(botao);
		}
		OutrosHacks.clear();
		for(String ID : Outros.pegarIDs()){
			BotaoInformacoes botao = new BotaoInformacoes(ID, Nomes.pegar(ID), 0x000000, 0x000000);
			listaHacks.put(ID, botao);
			OutrosHacks.add(botao);
		}
		RenderHacks.clear();
		for(String ID : Render.pegarIDs()){
			BotaoInformacoes botao = new BotaoInformacoes(ID, Nomes.pegar(ID), 0x000000, 0x000000);
			listaHacks.put(ID, botao);
			RenderHacks.add(botao);
		}
		TesteHacks.clear();
		for(String ID : Teste.pegarIDs()){
			BotaoInformacoes botao = new BotaoInformacoes(ID, Nomes.pegar(ID), 0x000000, 0x000000);
			listaHacks.put(ID, botao);
			TesteHacks.add(botao);
		}
	}
	
	public void atualizar(){
		HacksAtivados.clear();
		for(String key : Status.pegarIDs()){
			if(Status.pegar(key)){
				if(key == MiniGames.BlockPartyID){
					int qnts = 0;
					int ativado = 0;
					if(Status.pegar(Outros.BPmoverID)){
						qnts += 1;
						ativado = 1;
					}
					if(Status.pegar(Outros.BPsalvarID)){
						qnts += 1;
						ativado = 2;
					}
					String bp = Nomes.pegar(key);
					if(qnts == 1){
						if(ativado == 1){
							bp += "[mover]";
						} else if(ativado == 2){
							bp += "[salvar]";
						}
					} else if(qnts == 2){
						bp += "[mover,salvar]";
					}
					BotaoInformacoes botao = listaHacks.get(key);
					HacksAtivados.add(new BotaoInformacoes(botao.ID, bp, botao.texto_cor, botao.texto_cor_hover));
				}else if(key == Outros.BPmoverID){} else if(key == Outros.BPsalvarID){					
				} else{
					HacksAtivados.add(listaHacks.get(key));
				}
			}
		}
	}

	public void desenhar(int mouseX, int mouseY, int telaLargura, int telaAltura){
		atualizar();
		int x = 535;
		if(Status.pegar(MiniGames.BlockPartyID)){
			desenharInfosBP(x);
		}
		listaHacksAtivados.desenhar(mouseX, mouseY, telaLargura, telaAltura, HacksAtivados);
		tabelaMiniGames.desenhar(mouseX, mouseY, telaLargura, telaAltura, MiniGamesHacks);
		tabelaOutros.desenhar(mouseX, mouseY, telaLargura, telaAltura, OutrosHacks);
		tabelaRender.desenhar(mouseX, mouseY, telaLargura, telaAltura, RenderHacks);
		tabelaTeste.desenhar(mouseX, mouseY, telaLargura, telaAltura, TesteHacks);
		
		
	}
	public void desenhar(int telaLargura, int telaAltura){
		atualizar();
		int x = 535;
		if(Status.pegar(MiniGames.BlockPartyID)){
			desenharInfosBP(x);
		}
		if(listaHacksAtivados.ativado)
			listaHacksAtivados.desenhar(telaLargura, telaAltura, HacksAtivados);
		if(tabelaMiniGames.ativado)
			tabelaMiniGames.desenhar(telaLargura, telaAltura, MiniGamesHacks);
		if(tabelaOutros.ativado)
			tabelaOutros.desenhar(telaLargura, telaAltura, OutrosHacks);
		if(tabelaRender.ativado)
			tabelaRender.desenhar(telaLargura, telaAltura, RenderHacks);
		if(tabelaTeste.ativado)
			tabelaTeste.desenhar(telaLargura, telaAltura, TesteHacks);
		
	}
	private void desenharInfosBP(int x){
		Desenhador.desenharTextoCentralizadoNoY("Tempo: "+br.com.meh.hacks.ghoulhack.minigames.BlockParty.espera, x, 5+Desenhador.fr.FONT_HEIGHT*0, 0xFFFFFF, false);
		Desenhador.desenharTextoCentralizadoNoY("Delay1: "+br.com.meh.hacks.ghoulhack.minigames.BlockParty.delay+" ("+Dados.delay1+")", x, 5+Desenhador.fr.FONT_HEIGHT*1, 0xFFFFFF, false);
		Desenhador.desenharTextoCentralizadoNoY("Delay2: "+Dados.delay2, x, 5+Desenhador.fr.FONT_HEIGHT*2, 0xFFFFFF, false);
		Desenhador.desenharTextoCentralizadoNoY("Areas Dentro:", x, 5+Desenhador.fr.FONT_HEIGHT*3, 0xFFFFFF, false);
    	int cord = 5+Desenhador.fr.FONT_HEIGHT*4;
    	for(String area : br.com.meh.hacks.ghoulhack.minigames.BlockParty.areasDentro){
    		Desenhador.desenharTextoCentralizadoNoY(area, x, cord, 0xFFFFFF, false);
        	cord += 5+Desenhador.fr.FONT_HEIGHT;
    	}
	}
	public void mouseClicked(int x, int y, int which){
		listaHacksAtivados.mouseClicked(x, y, which);
		tabelaMiniGames.mouseClicked(x, y, which);
		tabelaOutros.mouseClicked(x, y, which);
		tabelaRender.mouseClicked(x, y, which);
		tabelaTeste.mouseClicked(x, y, which);
	}

}
