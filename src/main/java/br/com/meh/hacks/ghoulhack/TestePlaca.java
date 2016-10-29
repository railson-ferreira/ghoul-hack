package br.com.meh.hacks.ghoulhack;

import br.com.meh.hacks.ghoulhack.arquivos.Arquivos;
import br.com.meh.hacks.ghoulhack.status.MiniGames;
import br.com.meh.hacks.ghoulhack.util.Metodos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class TestePlaca extends GuiScreen{

    GuiButton Lobbies_status;
	String[] text;
	public TestePlaca(String[] text){
		this.text = text;
	}


    @Override
    public void initGui() {

    }
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int i, int j, float f) {
        super.drawScreen(i, j, f);
    	if(Status.pegar(MiniGames.AutoLobbyID)){
    		Lobbies_status = new GuiButton(0, 280, 70, 100, 20, "Lobbies : ligado");
    	} else {
    		Lobbies_status = new GuiButton(0, 280, 70, 120, 20, "Lobbies : desligado");
    	}
        buttonList.add(Lobbies_status);
    	int y = 200;
    	for(String text1 : text){
    		Minecraft.getMinecraft().fontRenderer.drawString(text1, 200, y, 999, false);
    		y+= Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
    	}
    }

    @Override
    public void actionPerformed(GuiButton button) {
    	if(button == Lobbies_status){
    		Status.alternarLobbies();
    	}
    }
}
