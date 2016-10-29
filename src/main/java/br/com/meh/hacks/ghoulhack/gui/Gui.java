package br.com.meh.hacks.ghoulhack.gui;

import br.com.meh.hacks.ghoulhack.GhoulHack;
import br.com.meh.hacks.ghoulhack.gui.util.Botao;
import br.com.meh.hacks.ghoulhack.gui.util.BotaoInformacoes;
import br.com.meh.hacks.ghoulhack.util.TextoInformacoes;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class Gui extends GuiScreen {
    public static final int GUI_ID = 1;
	@Override
    public void drawScreen(int i, int j, float f) {
		GhoulHack.telaDoHack.desenhar(i, j, width, height);
    }
    @Override
    public void initGui() {
    	if(GhoulHack.telaDoHack == null){
    		GhoulHack.telaDoHack = new HackDisplay(width, height);
    		GhoulHack.telaDoHack.inicializar();
    	}
    }
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
    public void mouseClicked(int i, int j, int k) {
    	GhoulHack.telaDoHack.mouseClicked(i, j, k);
    }
}
