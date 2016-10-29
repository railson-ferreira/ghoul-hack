package br.com.meh.hacks.ghoulhack.gui;

import br.com.meh.hacks.ghoulhack.Status;
import br.com.meh.hacks.ghoulhack.arquivos.Arquivos;
import br.com.meh.hacks.ghoulhack.comandos.Comando;
import br.com.meh.hacks.ghoulhack.util.Dados;
import br.com.meh.hacks.ghoulhack.util.Metodos;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class Console extends GuiScreen {
    public static final int GUI_ID = 2;
    
    GuiTextField cmd_textfield;
    Minecraft mc = Minecraft.getMinecraft();

    public Console() {
    }

    @Override
    public void initGui() {
    	cmd_textfield = new GuiTextField(fontRendererObj, 0, 0, width, 10);
    	cmd_textfield.setFocused(true);
    	if((width-10)/6 > cmd_textfield.getMaxStringLength()){
    		cmd_textfield.setMaxStringLength((width-10)/6);
    	}
    	
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
    @Override
    public void updateScreen() {
    	super.updateScreen();
    	cmd_textfield.setFocused(true);
    	mc.fontRenderer.drawString("Teste", 30, 30, 0xBBBBBB, false);
    	while(cmd_textfield.getText().indexOf(" ") == 0){
    		cmd_textfield.setText(cmd_textfield.getText().replaceFirst(" ", ""));
    	}
    }

    @Override
	public void drawScreen(int i, int j, float f) {
    	cmd_textfield.drawTextBox();
    	int y = 20;
    	String[] args = cmd_textfield.getText().split(" ");
    	if(args.length > 0){
    		if(args[0].length() > 0){
    			boolean ok = false;
    			for(Comando cmd : Comando.VerificarComando(args[0])){
    				ok = true;
    				drawString(fontRendererObj, cmd.pegarCmd()+" - "+cmd.pegarDescricao(), 10, y, 0xBBBBBB);
    				y += 15;
    			}
    			if(!ok){
    				drawString(fontRendererObj, "Nenhum comando encontrado para '"+cmd_textfield.getText()+"'", 10, y, 0xFF0000);
    			}
    		}
    	}
	}
    @Override
    public void keyTyped(char c, int i) {
    	super.keyTyped(c, i);
    	cmd_textfield.textboxKeyTyped(c, i);
    }
    @Override
    public void mouseClicked(int i, int j, int k) {
    	super.mouseClicked(i, j, k);
    	cmd_textfield.mouseClicked(i, j, k);
    }
}
