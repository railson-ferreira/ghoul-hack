package br.com.meh.hacks.ghoulhack.gui;

import br.com.meh.hacks.ghoulhack.Status;
import br.com.meh.hacks.ghoulhack.arquivos.Arquivos;
import br.com.meh.hacks.ghoulhack.gui.util.Botao;
import br.com.meh.hacks.ghoulhack.gui.util.BotaoEventListener;
import br.com.meh.hacks.ghoulhack.status.MiniGames;
import br.com.meh.hacks.ghoulhack.status.Outros;
import br.com.meh.hacks.ghoulhack.status.Render;
import br.com.meh.hacks.ghoulhack.status.Teste;
import br.com.meh.hacks.ghoulhack.util.Metodos;

public class BotaoEventoManipulador extends BotaoEventListener {
	public void clickEvent(Botao botao) {
		if(botao.ID == MiniGames.AutoLobbyID){
			Status.alternarLobbies();
			return;
		}
		if(botao.ID == Outros.Recarregar){
    		Arquivos.recarregar();
    		Metodos.EnviarMensagemPlayer("Arquivos recarregados");
    		return;
		}
		Status.alternarStatus(botao.ID);
	}
}
