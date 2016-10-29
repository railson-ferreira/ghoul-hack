package br.com.meh.hacks.ghoulhack;
import java.net.Proxy;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import br.com.meh.hacks.ghoulhack.arquivos.AreasBlockParty;
import br.com.meh.hacks.ghoulhack.comandos.Area;
import br.com.meh.hacks.ghoulhack.gui.Console;
import br.com.meh.hacks.ghoulhack.gui.Gui;
import br.com.meh.hacks.ghoulhack.killaura.KillAura;
import br.com.meh.hacks.ghoulhack.minigames.BlockParty;
import br.com.meh.hacks.ghoulhack.status.Jogador;
import br.com.meh.hacks.ghoulhack.util.AreaSelecao;
import br.com.meh.hacks.ghoulhack.util.Dados;
import br.com.meh.hacks.ghoulhack.util.Metodos;
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class PressionamentoDeTeclas {
	

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent evento) {
    	EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
    	if(Teclas.Teclas.get(Jogador.CameraLivreID).isPressed()){
    	}
        if(Teclas.OutrasTeclas.get("abrir_gui").isPressed()){
        	Minecraft.getMinecraft().displayGuiScreen(new Gui());
        }
        if(Teclas.OutrasTeclas.get("abrir_console").isPressed()){
        	Minecraft.getMinecraft().displayGuiScreen(new Console());
        }
        for(String key : Teclas.Teclas.keySet()){
            if(Teclas.Teclas.get(key).isPressed()){
            	Status.alternarStatus(key);
            }
        }
        if(Teclas.OutrasTeclas.get("media").isPressed()){
        	java.util.Date agora = new java.util.Date();
        	if(Media.status == false){
        		double x = player.posX; Media.x = x;
        		double y = player.posY; Media.y = y;
        		double z = player.posZ; Media.z = z;
        		long tempo = agora.getTime(); Media.Tempo = tempo;
        		Media.status = true;
            	player.addChatMessage(new ChatComponentText("definido primeiro valor da media: "+x+" "+y+" "+z));
        	} else {
        		double x1 = Media.x;
        		double y1 = Media.y;
        		double z1 = Media.z;
        		double x2 = player.posX;
        		double y2 = player.posY;
        		double z2 = player.posZ;
        		long tempo = agora.getTime()-Media.Tempo;
        		ChunkCoordinates cords1 = new ChunkCoordinates((int)x1, (int)y1, (int)z1);
        		ChunkCoordinates cords2 = new ChunkCoordinates((int)x2, (int)y2, (int)z2);
            	player.addChatMessage(new ChatComponentText("Velocidade media é de: "+cords1.getDistanceSquaredToChunkCoordinates(cords2)+"/"+tempo));
            	Media.status = false;
        	}
        	
        }
        if(Teclas.OutrasTeclas.get("sel_area").isPressed()){
        	if(Area.status == true){
				Area.status = false;
				player.addChatMessage(new ChatComponentText("[Area] Modo de Selecao desativado"));
			} else {
				Area.status = true;
				player.addChatMessage(new ChatComponentText("[Area] Modo de Selecao ativado"));
			}
        }
        if(Teclas.OutrasTeclas.get("criar_areaBP").isPressed()){
        	String[] args = {"blockparty", "area", "criar", "craftlandia"};
			if(args.length > 3){
				HashMap<String, AreaSelecao> areas = AreasBlockParty.Pegar();
				boolean ja_existe = false;
				for(String nome_area : areas.keySet()){
					if(nome_area.equalsIgnoreCase(args[3])){
						ja_existe = true;
					}
				}
				if(!ja_existe){
					boolean pos1 = false;
					boolean pos2 = false;
					if(Dados.pos1 != null){
						pos1 = true;
					} else {
						player.addChatMessage(new ChatComponentText("[BlockParty] defina a 'POS1' primeiro"));
					}
					if(Dados.pos2 != null){
						pos2 = true;
					} else {
						player.addChatMessage(new ChatComponentText("[BlockParty] defina a 'POS2' primeiro"));
					}
					if(pos1 && pos2){
						AreaSelecao areaParaSalvar = new AreaSelecao(Dados.pos1, Dados.pos2);
						areas.put(args[3], areaParaSalvar);
						AreasBlockParty.Salvar(areas);
						player.addChatMessage(new ChatComponentText("[BlockParty] Area foi salvada com sucesso"));
					}
				} else {
					player.addChatMessage(new ChatComponentText("[BlockParty] Ja existe uma area com esse nome"));
				}
			} else {
				player.addChatMessage(new ChatComponentText("Parametro <nome> obrigatorio faltando§r\nTente: .blockparty area criar <nome>"));
			}
        }
    }

}