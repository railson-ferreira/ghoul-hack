package br.com.meh.hacks.ghoulhack;

import br.com.meh.hacks.ghoulhack.util.Metodos;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class ManipuladorDeEventosFML {
	
	
	@EventHandler
	public void teste(PlayerEvent evento){
		Metodos.EnviarMensagemPlayer("player tick event");
	}
}
