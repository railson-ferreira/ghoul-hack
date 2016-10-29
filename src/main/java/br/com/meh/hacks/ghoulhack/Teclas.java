package br.com.meh.hacks.ghoulhack;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import br.com.meh.hacks.ghoulhack.status.Combate;
import br.com.meh.hacks.ghoulhack.status.Jogador;
import br.com.meh.hacks.ghoulhack.status.MiniGames;
import br.com.meh.hacks.ghoulhack.status.Outros;
import br.com.meh.hacks.ghoulhack.status.Render;
import br.com.meh.hacks.ghoulhack.status.Teste;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.gui.GuiKeyBindingList.KeyEntry;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

public class Teclas {

	public static HashMap<String, KeyBinding> Teclas = new HashMap<String, KeyBinding>();
	public static HashMap<String, KeyBinding> OutrasTeclas = new HashMap<String, KeyBinding>();
	public static void inicializar() {
		Teclas.clear();
		Teclas.put(Combate.AimBotID, new KeyBinding("AimBot", Keyboard.KEY_M, "GhoulHack"));
		Teclas.put(Combate.AntiRepulsaoID, new KeyBinding("Knocks", Keyboard.KEY_L, "GhoulHack"));
		Teclas.put(Combate.KillAuraID, new KeyBinding("KillAura", Keyboard.KEY_K, "GhoulHack"));
		Teclas.put(MiniGames.BlockPartyID, new KeyBinding("BlockParty", Keyboard.KEY_O, "GhoulHack"));
		Teclas.put(MiniGames.DetetiveID, new KeyBinding("Detetive", Keyboard.KEY_NUMPAD5, "GhoulHack"));
		Teclas.put(MiniGames.FrogID, new KeyBinding("Frog", Keyboard.KEY_NUMPAD2, "GhoulHack"));
		Teclas.put(MiniGames.SemaforoID, new KeyBinding("Semaforo", Keyboard.KEY_7, "GhoulHack"));
		Teclas.put(Outros.BPsalvarID, new KeyBinding("SalvarBP", Keyboard.KEY_NUMPAD2, "GhoulHack"));
		Teclas.put(Outros.Forca, new KeyBinding("Forca", Keyboard.KEY_NUMPAD6, "GhoulHack"));
		Teclas.put(Outros.info, new KeyBinding("Informacoes", Keyboard.KEY_NUMPAD4, "GhoulHack"));
		Teclas.put(Render.brilho, new KeyBinding("Luz", Keyboard.KEY_NUMPAD6, "GhoulHack"));
		Teclas.put(Render.NaoSegueira, new KeyBinding("Sem Seguera", Keyboard.KEY_NUMPAD6, "GhoulHack"));
		Teclas.put(Jogador.CameraLivreID, new KeyBinding("Camera Livre", Keyboard.KEY_V, "GhoulHack"));
		Teclas.put(Teste.Mover, new KeyBinding("Mover", Keyboard.KEY_NUMPAD3, "GhoulHack"));
		Teclas.put(Teste.Teste1, new KeyBinding("Teste1", Keyboard.KEY_NUMPAD1, "GhoulHack"));
		Teclas.put(Teste.Teste2, new KeyBinding("Teste2", Keyboard.KEY_NUMPAD1, "GhoulHack"));
		Teclas.put(Teste.Teste3, new KeyBinding("Teste3", Keyboard.KEY_NUMPAD1, "GhoulHack"));
		OutrasTeclas.clear();
		OutrasTeclas.put("media", new KeyBinding("media", Keyboard.KEY_NUMPAD3, "GhoulHack"));
		OutrasTeclas.put("sel_area", new KeyBinding("SelArea", Keyboard.KEY_NUMPAD6, "GhoulHack"));
		OutrasTeclas.put("criar_areaBP", new KeyBinding("Criar Area BP", Keyboard.KEY_NUMPAD6, "GhoulHack"));
		OutrasTeclas.put("auto_lobbie", new KeyBinding("Auto entrar BP", Keyboard.KEY_NUMPAD3, "GhoulHack"));
		OutrasTeclas.put("abrir_gui", new KeyBinding("Abrir Gui", Keyboard.KEY_RSHIFT, "GhoulHack"));
		OutrasTeclas.put("abrir_console", new KeyBinding("Abrir CMD", Keyboard.KEY_RCONTROL, "GhoulHack"));
		registrarTelcas();
	}
	public static void registrarTelcas(){
		for(KeyBinding key : Teclas.values()){
			ClientRegistry.registerKeyBinding(key);
		}
		for(KeyBinding key : OutrasTeclas.values()){
	        ClientRegistry.registerKeyBinding(key);
		}
	}
	                                   

}