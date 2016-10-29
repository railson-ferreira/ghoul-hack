package br.com.meh.hacks.ghoulhack.minigames;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSign;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.meh.hacks.ghoulhack.GhoulHack;
import br.com.meh.hacks.ghoulhack.Status;
import br.com.meh.hacks.ghoulhack.TestePlaca;
import br.com.meh.hacks.ghoulhack.status.MiniGames;
import br.com.meh.hacks.ghoulhack.status.Outros;
import br.com.meh.hacks.ghoulhack.util.AreaSelecao;
import br.com.meh.hacks.ghoulhack.util.Cordenadas;
import br.com.meh.hacks.ghoulhack.util.Jogador;
import br.com.meh.hacks.ghoulhack.util.Lobby;
import br.com.meh.hacks.ghoulhack.util.Metodos;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Lobbies {
	private static List<String> LobbiesDentro = new ArrayList<String>();
	private static HashMap<String, String[]> estilo_placas = new HashMap<String, String[]>();
	
	String ultimominigame = "";
	int ultimasala = 0;

	long tempo = new Date().getTime();
	long delay = 1000;

	public static boolean alternarStatus(String lobby){
		if(Status.Lobbies.containsKey(lobby)){
			if(Status.Lobbies.get(lobby)){
				Status.Lobbies.put(lobby, false);
			} else {
				Status.Lobbies.put(lobby, true);
			}
			return Status.Lobbies.get(lobby);
		}
		return false;
	}
	public static void mudarStatus(String lobby, boolean status){
		if(Status.Lobbies.containsKey(lobby)){
			Status.Lobbies.put(lobby, status);
		}
	}

    @SubscribeEvent
    public void atualicaoFOV(FOVUpdateEvent evento){
    	long agora = new Date().getTime();
    	//distancia = 4
    	HashMap<String, Lobby> lobbies = br.com.meh.hacks.ghoulhack.arquivos.Lobbies.pegarLobbies();
    	Set<String> lobbies_nome = lobbies.keySet();
    	int distancia = 9999;
    	boolean enviouAtualizacao = false;
		EntityPlayerSP player = evento.entity;
    	for(String lobby_nome : lobbies_nome){
    		if(Status.Lobbies.containsKey(lobby_nome)){
    			if(Status.Lobbies.get(lobby_nome)){
    				double X = player.posX; double Y = player.posY; double Z = player.posZ;
    				Lobby lobby = lobbies.get(lobby_nome);
    				AreaSelecao area = lobby.PegarArea();
    				Cordenadas min = area.pegarMin();
    				Cordenadas max = area.pegarMax();
    				double minX = min.posX(); double minY = min.posY(); double minZ = min.posZ();
    				double maxX = max.posX()+0.999999999; double maxY = max.posY()+0.999999999; double maxZ = max.posZ()+0.999999999;
        			if(minX < X && X < maxX && minY < Y && Y < maxY && minZ < Z && Z < maxZ){
        				if(!LobbiesDentro.contains(lobby_nome)){
        					LobbiesDentro.add(lobby_nome);
        				}
        				World mundo = player.getEntityWorld();
        				boolean ok = false;
        				int cont = 0;
        				int x = 0;
        				int z = 0;
    					Cordenadas cords_player = new Cordenadas(X, Y, Z, 0, 0);
        				for(Cordenadas placa : lobby.PegarPlacas()){
        					if(ok){
        						continue;
        					}
        					int placa_X = (int)placa.posX();
        					int placa_Y = (int)placa.posY();
        					int placa_Z = (int)placa.posZ();
        					x += placa_X;
        					z += placa_Z;
        					cont++;
        					
        					TileEntity block_placa = mundo.getTileEntity(placa_X, placa_Y, placa_Z);
        					if(block_placa instanceof TileEntitySign){} else {continue;}
        					TileEntitySign tile = (TileEntitySign)block_placa;
        					String[] text = tile.signText;
        					String minigame = "";
        					int sala = 0;
        					int prioridade = 1;
        					if(Status.pegar(Outros.PrioridadeMaxID)){
        						prioridade = 999999999;
        					}
        					if(text.length > 3){
            					if(text[3].indexOf("ONLINE") >= 0){
                					if(text[1].indexOf("Dispon") >= 0){
                						ok = true;
                					} 
            					}
            					if(text[0].toLowerCase().indexOf("1") >= 0){
            						sala = 1;
            					} else if(text[0].toLowerCase().indexOf("2") >= 0){
            						sala = 2;
            					} else if(text[0].toLowerCase().indexOf("3") >= 0){
            						sala = 3;
            					} else if(text[0].toLowerCase().indexOf("4") >= 0){
            						sala = 4;
            					} else if(text[0].toLowerCase().indexOf("5") >= 0){
            						sala = 5;
            					} else if(text[0].toLowerCase().indexOf("6") >= 0){
            						sala = 6;
            					} else if(text[0].toLowerCase().indexOf("7") >= 0){
            						sala = 7;
            					} else if(text[0].toLowerCase().indexOf("8") >= 0){
            						sala = 8;
            					} else if(text[0].toLowerCase().indexOf("9") >= 0){
            						sala = 9;
            					} else if(text[0].toLowerCase().indexOf("10") >= 0){
            						sala = 10;
            					}
            					if(text[0].toLowerCase().indexOf("blockparty") >= 0){
            						minigame = "blockparty";
            					} else if(text[0].toLowerCase().indexOf("semaforo") >= 0){
            						minigame = "semaforo";
            					} 
            				}
        					if(ok){
        				    	if(!Status.pegar(Outros.PrioridadeMaxID)){
        							if(agora - tempo > delay){
        								tempo = agora;
        								String html = Metodos.pegarFonteHTML("http://hackpixelmon.servegame.com/HackMiniGames/?senha=123senhaMiniGame123&id=777&modo=pegar&sala="+sala+"&minigame="+minigame);
        								String Jsontexto = Metodos.pegarJsonRawDeHTML(Metodos.pegarDemarcacao(html, "<div id=\"json\">", "</div>"));
        						    	JsonElement elemento = new JsonParser().parse(Jsontexto);
        						    	if(elemento.isJsonObject()){
        						    		JsonObject JsonObj = elemento.getAsJsonObject();
        						    		if(JsonObj.get("contem_players").getAsBoolean()){
        						    			enviouAtualizacao = true;
        										Metodos.pegarFonteHTML("http://hackpixelmon.servegame.com/HackMiniGames/?senha=123senhaMiniGame123&id=777&modo=remover&sala="+ultimasala+"&minigame="+ultimominigame+"&player="+player.getDisplayName());
        						    			continue;
        						    		}
        						    	}
        							}
        				    	}
            					Cordenadas go = new Cordenadas(placa_X+0.5, placa_Y+0.5, placa_Z+0.5, 0, 0);
            					cords_player.Olhar(go.posX(), go.posY(), go.posZ());
            					player.rotationYaw = cords_player.rotacaoYaw();
            					//player.rotationPitch = cords_player.rotacaoPitch();
            					if(go.CalcularDistancia(cords_player) > 3.5){
                					Jogador.moverPlayer(player, placa_X, placa_Z, false);
            					} else {
            						Minecraft.getMinecraft().playerController.onPlayerRightClick(player, mundo, player.getItemInUse(), placa_X, placa_Y, placa_Z, mundo.getBlockMetadata(placa_X, placa_Y, placa_Z), player.getLookVec());
        						
        								tempo = agora;
        								Metodos.pegarFonteHTML("http://hackpixelmon.servegame.com/HackMiniGames/?senha=123senhaMiniGame123&id=777&modo=adicionar&sala="+sala+"&minigame="+minigame+"&player="+player.getDisplayName()+"&prioridade="+prioridade);
        								enviouAtualizacao = true;
        								ultimasala = sala;
        								ultimominigame = minigame;
                    			}
        					}
        				}
        				if(!ok){
            				for(Cordenadas placa : lobby.PegarPlacas()){
            					if(ok){
            						continue;
            					}
            					int placa_X = (int)placa.posX();
            					int placa_Y = (int)placa.posY();
            					int placa_Z = (int)placa.posZ();
            					x += placa_X;
            					z += placa_Z;
            					cont++;
            					
            					TileEntity block_placa = mundo.getTileEntity(placa_X, placa_Y, placa_Z);
            					if(block_placa instanceof TileEntitySign){} else {continue;}
            					TileEntitySign tile = (TileEntitySign)block_placa;
            					String[] text = tile.signText;
            					String minigame = "";
            					int sala = 0;
            					int prioridade = 1;
            					if(Status.pegar(Outros.PrioridadeMaxID)){
            						prioridade = 999999999;
            					}
            					if(text.length > 3){
                					if(text[3].indexOf("ONLINE") >= 0){
                        				if(text[1].indexOf("Carregando") >= 0){
                    						ok = true;
                    					}
                					}
                					if(text[0].toLowerCase().indexOf("1") >= 0){
                						sala = 1;
                					} else if(text[0].toLowerCase().indexOf("2") >= 0){
                						sala = 2;
                					} else if(text[0].toLowerCase().indexOf("3") >= 0){
                						sala = 3;
                					} else if(text[0].toLowerCase().indexOf("4") >= 0){
                						sala = 4;
                					} else if(text[0].toLowerCase().indexOf("5") >= 0){
                						sala = 5;
                					} else if(text[0].toLowerCase().indexOf("6") >= 0){
                						sala = 6;
                					} else if(text[0].toLowerCase().indexOf("7") >= 0){
                						sala = 7;
                					} else if(text[0].toLowerCase().indexOf("8") >= 0){
                						sala = 8;
                					} else if(text[0].toLowerCase().indexOf("9") >= 0){
                						sala = 9;
                					} else if(text[0].toLowerCase().indexOf("10") >= 0){
                						sala = 10;
                					}
                					if(text[0].toLowerCase().indexOf("blockparty") >= 0){
                						minigame = "blockparty";
                					} else if(text[0].toLowerCase().indexOf("semaforo") >= 0){
                						minigame = "semaforo";
                					} 
                				}
            					if(ok){
            				    	if(!Status.pegar(Outros.PrioridadeMaxID)){
            							if(agora - tempo > delay){
            								tempo = agora;
            								String html = Metodos.pegarFonteHTML("http://hackpixelmon.servegame.com/HackMiniGames/?senha=123senhaMiniGame123&id=777&modo=pegar&sala="+sala+"&minigame="+minigame);
            								String Jsontexto = Metodos.pegarJsonRawDeHTML(Metodos.pegarDemarcacao(html, "<div id=\"json\">", "</div>"));
            						    	JsonElement elemento = new JsonParser().parse(Jsontexto);
            						    	if(elemento.isJsonObject()){
            						    		JsonObject JsonObj = elemento.getAsJsonObject();
            						    		if(JsonObj.get("contem_players").getAsBoolean()){
            						    			enviouAtualizacao = true;
            										Metodos.pegarFonteHTML("http://hackpixelmon.servegame.com/HackMiniGames/?senha=123senhaMiniGame123&id=777&modo=remover&sala="+ultimasala+"&minigame="+ultimominigame+"&player="+player.getDisplayName());
            						    			continue;
            						    		}
            						    	}
            							}
            				    	}
                					Cordenadas go = new Cordenadas(placa_X+0.5, placa_Y+0.5, placa_Z+0.5, 0, 0);
                					cords_player.Olhar(go.posX(), go.posY(), go.posZ());
                					player.rotationYaw = cords_player.rotacaoYaw();
                					//player.rotationPitch = cords_player.rotacaoPitch();
                					if(go.CalcularDistancia(cords_player) > 3.5){
                    					Jogador.moverPlayer(player, placa_X, placa_Z, false);
                					} else {
                						Minecraft.getMinecraft().playerController.onPlayerRightClick(player, mundo, player.getItemInUse(), placa_X, placa_Y, placa_Z, mundo.getBlockMetadata(placa_X, placa_Y, placa_Z), player.getLookVec());
            						
            								tempo = agora;
            								Metodos.pegarFonteHTML("http://hackpixelmon.servegame.com/HackMiniGames/?senha=123senhaMiniGame123&id=777&modo=adicionar&sala="+sala+"&minigame="+minigame+"&player="+player.getDisplayName()+"&prioridade="+prioridade);
            								enviouAtualizacao = true;
            								ultimasala = sala;
            								ultimominigame = minigame;
                        			}
            					}
            				}
        				}
        				if(!ok){
        					Cordenadas go = new Cordenadas((x/cont)+0.5, player.posY, (z/cont)+0.5, player.rotationYaw, player.rotationPitch);
        					if(go.CalcularDistancia(cords_player) > 3.5){
            					cords_player.Olhar(go.posX(), go.posY(), go.posZ());
            					player.rotationYaw = cords_player.rotacaoYaw();
            					Jogador.moverPlayer(player, go.posX(), go.posZ(), false);
            				}
            				for(Cordenadas placa : lobby.PegarPlacas()){
            					int placa_X = (int)placa.posX();
            					int placa_Y = (int)placa.posY();
            					int placa_Z = (int)placa.posZ();
            					TileEntity block_placa = mundo.getTileEntity(placa_X, placa_Y, placa_Z);
            					if(block_placa instanceof TileEntitySign){} else {continue;}
            					TileEntitySign tile = (TileEntitySign)block_placa;
            					String[] text = tile.signText;
            					if(text.length > 3){
                					if(text[3].indexOf("ONLINE") >= 0){
                    					if(text[1].indexOf("LOTADO") >= 0){
                    						ok = true;
                    					}
                					} else if(text[3].indexOf("Aguardando") >= 0) {
                						ok = true;
                					}
                				}
            					if(ok){
                					Cordenadas go2 = new Cordenadas(placa_X+0.5, placa_Y+0.5, placa_Z+0.5, 0, 0);
                    				cords_player.Olhar(go2.posX(), go2.posY(), go2.posZ());
                    				player.rotationYaw = cords_player.rotacaoYaw();
                    				//player.rotationPitch = cords_player.rotacaoPitch();
            						Minecraft.getMinecraft().playerController.onPlayerRightClick(player, mundo, player.getItemInUse(), placa_X, placa_Y, placa_Z, mundo.getBlockMetadata(placa_X, placa_Y, placa_Z), player.getLookVec());
            					}
            				}
        				}
        		    	if(!enviouAtualizacao){
								tempo = agora;
								Metodos.pegarFonteHTML("http://hackpixelmon.servegame.com/HackMiniGames/?senha=123senhaMiniGame123&id=777&modo=remover&sala="+ultimasala+"&minigame="+ultimominigame+"&player="+player.getDisplayName());
								enviouAtualizacao = true;
        		    	}
        			} else if(LobbiesDentro.contains(lobby_nome)){
        				LobbiesDentro.remove(lobby_nome);   				
        			}
    			}
    		}
    	}
    	if(!enviouAtualizacao){
        	Metodos.pegarFonteHTML("http://hackpixelmon.servegame.com/HackMiniGames/?senha=123senhaMiniGame123&id=777&modo=adicionar&sala="+ultimasala+"&minigame="+ultimominigame+"&player="+player.getDisplayName());
        	if(!Status.pegar(Outros.PrioridadeMaxID)){
    			if(agora - tempo > delay){
    				tempo = agora;
    				String html = Metodos.pegarFonteHTML("http://hackpixelmon.servegame.com/HackMiniGames/?senha=123senhaMiniGame123&id=777&modo=pegar&sala="+ultimasala+"&minigame="+ultimominigame);
    				String Jsontexto = Metodos.pegarJsonRawDeHTML(Metodos.pegarDemarcacao(html, "<div id=\"json\">", "</div>"));
    		    	JsonElement elemento = new JsonParser().parse(Jsontexto);
    		    	if(elemento.isJsonObject()){
    		    		JsonObject JsonObj = elemento.getAsJsonObject();
    		    		Set<Entry<String, JsonElement>> set = JsonObj.get("lista").getAsJsonObject().entrySet();
    		    		for(Entry<String, JsonElement> infoplayer : set){
    		    			if(!infoplayer.getKey().equalsIgnoreCase(player.getDisplayName())){
    		    				Metodos.EnviarMensagemPlayer("ja tem player aq, saia");
    		    			}
    		    		}
    		    	}
    			}
        	}
    	}
    }
    
	public static void carregar(){
		//Carregar ou criar arquivo
		File pasta = new File("GhoulHack/MiniGames");
		if(pasta.exists()){
			if(!pasta.isDirectory()){
				return;
			}
		} else {
			return;
		}
		File Lobbies_pasta = new File("GhoulHack/MiniGames/Lobbies");
		if(Lobbies_pasta.exists()){
			if(!Lobbies_pasta.isDirectory()){
				return;
			}
		} else {
			return;
		}
		File placas_pasta = new File("GhoulHack/MiniGames/Lobbies/Placas");
		if(Lobbies_pasta.exists()){
			if(!Lobbies_pasta.isDirectory()){
				return;
			}
		} else {
			return;
		}
		File[] placas_arqs = new File("GhoulHack/MiniGames/Lobbies/Placas").listFiles();
		for(File placa : placas_arqs){
			if(placa.isFile()){				
				List<String> linhasLidas = new ArrayList<String>();
				//Ler Arquivo
				FileReader fr = null;
				try {
					fr = new FileReader(placa);
				} catch (FileNotFoundException e) {e.printStackTrace();}
				BufferedReader br = new BufferedReader(fr);
				try {
					while(br.ready()){
						String linha = br.readLine();
						linhasLidas.add(linha);
					}
				} catch (IOException e) {e.printStackTrace();}
				try {
					br.close();
				} catch (IOException e4) {e4.printStackTrace();}
				try {
					fr.close();
				} catch (IOException e3) {e3.printStackTrace();}
				String linha1 = null;
				String linha2 = null;
				String linha3 = null;
				String linha4 = null;
				String linha5 = null;
				if(linhasLidas.size() == 5){
					linha1 = linhasLidas.get(0);
					String[] numero = {linha1};
					if(Metodos.VerificarIntNFE(numero)){
						if(linhasLidas.get(1) != "" || linhasLidas.get(1) != " " || linhasLidas.get(1) != null){
							linha2 = linhasLidas.get(1);
						}
						if(linhasLidas.get(2) != "" || linhasLidas.get(2) != " " || linhasLidas.get(2) != null){
							linha3 = linhasLidas.get(2);
						}
						if(linhasLidas.get(3) != "" || linhasLidas.get(3) != " " || linhasLidas.get(3) != null){
							linha4 = linhasLidas.get(3);
						}
						if(linhasLidas.get(4) != "" || linhasLidas.get(4) != " " || linhasLidas.get(4) != null){
							linha5 = linhasLidas.get(4);
						}
						String[] linhas = {linha1, linha2, linha3, linha4, linha5};
						estilo_placas.put(placa.getName(), linhas);
					} else {
						placa.delete();
						continue;
					}
				} else {
					placa.delete();
					continue;
				}
			}
		}
	}
}
