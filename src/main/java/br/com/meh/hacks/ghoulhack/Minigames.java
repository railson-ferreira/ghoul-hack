package br.com.meh.hacks.ghoulhack;

import br.com.meh.hacks.ghoulhack.minigames.BlockParty;
import br.com.meh.hacks.ghoulhack.minigames.Lobbies;
import br.com.meh.hacks.ghoulhack.minigames.Semaforo;

import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Minigames {
	public static void registrar(){
    	MinecraftForge.EVENT_BUS.register(new BlockParty());
    	MinecraftForge.EVENT_BUS.register(new Semaforo());
    	MinecraftForge.EVENT_BUS.register(new Lobbies());
	}
}
