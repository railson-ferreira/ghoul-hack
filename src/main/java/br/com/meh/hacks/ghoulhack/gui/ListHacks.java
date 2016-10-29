package br.com.meh.hacks.ghoulhack.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.opengl.GL11;

import br.com.meh.hacks.ghoulhack.Status;
import br.com.meh.hacks.ghoulhack.gui.util.Botao;
import br.com.meh.hacks.ghoulhack.status.MiniGames;
import br.com.meh.hacks.ghoulhack.status.Nomes;
import br.com.meh.hacks.ghoulhack.status.Outros;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class ListHacks extends Gui {
	Minecraft mc = Minecraft.getMinecraft();
	HashMap<String,Botao> botoes = new HashMap<String,Botao>();
	List<String> hacks_para_desenhar = new ArrayList<String>();
	
	private void atualizar(){
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
					String bp = "Block Party";
					if(qnts == 1){
						if(ativado == 1){
							bp += "[mover]";
						} else if(ativado == 2){
							bp += "[salvar]";
						}
					} else if(qnts == 2){
						bp += "[mover,salvar]";
					}
					
					hacks_para_desenhar.add(bp);
				}else {
					hacks_para_desenhar.add(Nomes.pegar(key));
				}
			}
		}
	}
	
	public void desenhar(int x, int y, int cor1, int cor2, int opacidade, int telaLargura, int telaAltura, int mouseX, int mouseY, boolean entradaAtivada){
		FontRenderer fr = mc.fontRenderer;
		int largura_hacks = fr.getStringWidth("Hacks Ativos");

		int largura_tabela = largura_hacks + 10;
		int altura_head = fr.FONT_HEIGHT+6;
		int altura_tabela = 5;
		hacks_para_desenhar.clear();
		atualizar();
		
		for(String hack_para_desenhar : hacks_para_desenhar){
			int largura_hack = fr.getStringWidth(hack_para_desenhar);
			if(largura_tabela < largura_hack+10){largura_tabela = largura_hack+10;}
			altura_tabela += fr.FONT_HEIGHT+5;
		}

		if(telaLargura - x < largura_tabela+5){
			x = telaLargura - (largura_tabela+5);
		}
		if(y < 5){
			y = 5;
		}
		//desenhar tabela

        GL11.glDisable(GL11.GL_TEXTURE_2D);GL11.glEnable(GL11.GL_BLEND);GL11.glDisable(GL11.GL_ALPHA_TEST);OpenGlHelper.glBlendFunc(770, 771, 1, 0);GL11.glShadeModel(GL11.GL_SMOOTH);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(100, 100, 100, opacidade);
        tessellator.addVertex(x, y, 0.0D);
        tessellator.addVertex(x, y+altura_head, 0.0D);
        tessellator.addVertex(x+largura_tabela, y+altura_head, 0.0D);
        tessellator.addVertex(x+largura_tabela, y, 0.0D);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(200, 200, 200, opacidade);
        tessellator.addVertex(x, y+altura_head, 0.0D);
        tessellator.addVertex(x, y+altura_head+altura_tabela, 0.0D);
        tessellator.addVertex(x+largura_tabela, y+altura_head+altura_tabela, 0.0D);
        tessellator.addVertex(x+largura_tabela, y+altura_head, 0.0D);
        tessellator.draw();
        GL11.glShadeModel(GL11.GL_FLAT);GL11.glDisable(GL11.GL_BLEND);GL11.glEnable(GL11.GL_ALPHA_TEST);GL11.glEnable(GL11.GL_TEXTURE_2D);
        fr.drawString("Hacks Ativos", x+5, y+3, 255, true);
        int y_para_desenhar = y+altura_head+5;
        for(String hack_para_desenhar : hacks_para_desenhar){
            fr.drawString(hack_para_desenhar, x+5, y_para_desenhar, 555, false);
            y_para_desenhar += fr.FONT_HEIGHT+5;
        }
       }
}
