package br.com.meh.hacks.ghoulhack.minigames;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.core.config.ConfigurationFactory.ConfigurationSource;
import org.apache.logging.log4j.core.config.JSONConfiguration;

import scala.collection.immutable.Map;
import scala.util.parsing.json.JSON;
import scala.util.parsing.json.JSONArray;
import scala.util.parsing.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import br.com.meh.hacks.ghoulhack.Status;
import br.com.meh.hacks.ghoulhack.Teclas;
import br.com.meh.hacks.ghoulhack.arquivos.AreasBlockParty;
import br.com.meh.hacks.ghoulhack.comandos.Area;
import br.com.meh.hacks.ghoulhack.status.MiniGames;
import br.com.meh.hacks.ghoulhack.status.Outros;
import br.com.meh.hacks.ghoulhack.util.AntiLag;
import br.com.meh.hacks.ghoulhack.util.AreaSelecao;
import br.com.meh.hacks.ghoulhack.util.Cordenadas;
import br.com.meh.hacks.ghoulhack.util.Dados;
import br.com.meh.hacks.ghoulhack.util.Jogador;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;

public class BlockParty {
	public static List<String> areasDentro = new ArrayList<String>();
	static boolean congelado = false;
	static boolean congelado1 = false;
	public static long delay = Dados.delay1+0;
	public static long espera = 8000;
	static long inic_cont_tempo = 0;
	static boolean imprimir_espera = true;
	static boolean delay_ok = false;
	static long tempo = 0;
	static boolean dentro_do_bloco = false;
	static boolean dentro_do_bloco1 = false;
	public static boolean salvo = false;
	public static boolean salvo1 = false;
	static int contPulo = 0;
	static boolean ja_ta_dentro = false;
	static boolean ja_cont_inic = false;
	static boolean contTempo = true;
	static List<String> nome_blocos_desiguais = new ArrayList<String>();
	public static boolean alternarStatus(){
		return Status.alternarStatus(MiniGames.BlockPartyID);
	}
	public static void mudarStatus(boolean status){
		Status.mudarStatus(MiniGames.BlockPartyID, status);
	}
	
    @SubscribeEvent
    public void atualicaoFOV(FOVUpdateEvent evento){
        if(Status.pegar(MiniGames.BlockPartyID)){
    		EntityPlayer player = evento.entity;
			//player.addChatMessage(new ChatComponentText("blckparty ligado = 1"));
    		HashMap<String, AreaSelecao> areas = AreasBlockParty.Pegar();
    		Set<String> nome_areas = areas.keySet();
    		ChunkCoordinates cords = player.getPlayerCoordinates();
			double X = cords.posX; double Y = cords.posY; double Z = cords.posZ;

    		//Procurar uma area de cada vez
    		for(String nome_area : nome_areas){
    			//player.addChatMessage(new ChatComponentText("scaner area = 2"));
    			AreaSelecao sel = areas.get(nome_area);
    			Cordenadas min = sel.pegarMin();
    			Cordenadas max = sel.pegarMax();
    			double minX = min.posX(); double minY = min.posY(); double minZ = min.posZ();
    			double maxX = max.posX()+0.999999999; double maxY = max.posY()+0.999999999; double maxZ = max.posZ()+0.999999999;
    			//Verifica se o player esta dentro de uma area
    			if(minX < X && X < maxX && minY < Y && Y < maxY+5 && minZ < Z && Z < maxZ){
        			//player.addChatMessage(new ChatComponentText("dentro area = 3.1"));
    				if(!areasDentro.contains(nome_area)){
    					areasDentro.add(nome_area);
    				}
                	java.util.Date agora = new java.util.Date();
            		ItemStack item_mao = player.inventory.getCurrentItem();
            		if(item_mao == null){
            			//player.addChatMessage(new ChatComponentText("nada na mao  = 4.1"));
            			tempo = agora.getTime();
            			delay_ok = false;
            			salvo = false;
            			contPulo = 0;
            			ja_ta_dentro = true;
            			ja_cont_inic = false;
            			contTempo = true;
            			if(Status.pegar(Outros.BPmoverID)){
            				player.rotationPitch = 60;
            				MoverLocalEstrategico(player, sel, evento);
            			}
            		} else {
            			if(!ja_cont_inic){
            				inic_cont_tempo = new java.util.Date().getTime();
            				ja_cont_inic = true;
            				//imprimir_espera = true;
            			}
            			salvo1 = false;
            			//player.addChatMessage(new ChatComponentText("Item na mao  = 4.2"));
                		World mundo = player.getEntityWorld();
        				List<ChunkCoordinates> blocos_iguais = new ArrayList<ChunkCoordinates>();
        				String nome_item_igual = item_mao.getUnlocalizedName();
        				int scanerX = (int) minX; int scanerY = (int) minY; int scanerZ = (int) minZ;
        				//Scaner a procura de cordenados de blocos iguais
        				nome_blocos_desiguais.clear();
        				while(scanerX <= maxX){
        					List<ChunkCoordinates> cordenadas_iguais = ScanerZ(scanerX, scanerY, scanerZ, maxZ, mundo, nome_item_igual, player, minX, minZ, maxX);
        					if(cordenadas_iguais != null){
            					blocos_iguais.addAll(cordenadas_iguais);
        					}
        					scanerX++;
        				}
        				//Caucular Tempo
        				int air = 0;
        				int outro = 0;
        				for(String nome_bloco : nome_blocos_desiguais){
        					if(nome_bloco.equalsIgnoreCase("tile.air")){
        						air++;
        					} else {
        						outro++;
        					}
        				}
        				if(air > outro && contTempo){
                			espera = agora.getTime() - inic_cont_tempo;
                			if(espera*Dados.delay2 < Dados.delay1){
                				double delay = espera*Dados.delay2;
                				this.delay = (long) delay;
                			}
                			if(imprimir_espera){
                				player.addChatMessage(new ChatComponentText("o tempo foi: "+espera+" com espera de: "+delay));
                				imprimir_espera = false;
                			}
                			contTempo = false;
        				}
        				ChunkCoordinates cordenada_mais_proxima = new ChunkCoordinates();
        				double distancia = 999;
        				boolean ok = false;
    					double posXplayer = player.posX;
    					double posYplayer = player.posY;
    					double posZplayer = player.posZ;
    					boolean dentro_de_algum_bloco = false;
        				for(ChunkCoordinates bloco_igual : blocos_iguais){
        					Cordenadas cordenadas = new Cordenadas(player.posX, player.posY, player.posZ, 0, 0);
        					int x = bloco_igual.posX; int y = bloco_igual.posY; int z = bloco_igual.posZ;
        					double dist = cordenadas.CalcularDistancia(new Cordenadas(x, y, z, 0, 0));
        					if(dist < distancia){
        						distancia = dist;
        						cordenada_mais_proxima = bloco_igual;
        						ok = true;
        					}
        					if(x-0.2 < posXplayer && posXplayer < x+1+0.2 && z-0.2 < posZplayer && posZplayer < z+1+0.2){
        						dentro_de_algum_bloco = true;
        					}
        				}        				
        				if(ok){
                			//player.addChatMessage(new ChatComponentText("'ok' pq achou block  = 5.1"));
        					double proxX = cordenada_mais_proxima.posX+0.5;
        					double proxY = cordenada_mais_proxima.posY+1;
        					double proxZ = cordenada_mais_proxima.posZ+0.5;
        					Cordenadas go = new Cordenadas(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
    						go.Olhar(proxX, proxY, proxZ);
        					if(delay_ok){
                    			//player.addChatMessage(new ChatComponentText("tempo tabem ta ok  = 6.1"));
        						if(!salvo){
                        			//player.addchatMessage(new ChatComponentText("indo para o block  = 7"));
                					//Movimento Teste//
                					if(dentro_do_bloco && dentro_de_algum_bloco){
                            			//player.addchatMessage(new ChatComponentText("dentro do block ja  = 8.1"));
                    					if(!Status.pegar(Outros.BPsalvarID)){
                    						salvo = true;
                    					}
                					} else if(dentro_do_bloco == false && proxX-0.5 < posXplayer && posXplayer < proxX+0.5 && proxZ-0.5 < posZplayer && posZplayer < proxZ+0.5){
                            			//player.addchatMessage(new ChatComponentText("centro do block ja  = 8.2"));
                						if(congelado == false){
                    						congelado = true;
                						}
                						dentro_do_bloco = true;
                					} else{
                            			//player.addchatMessage(new ChatComponentText("fora do block = 8.3"));
                						congelado = false;
                						dentro_do_bloco = false;
                						if(ja_ta_dentro){
                							ja_ta_dentro = false;
                							if(dentro_do_bloco && proxX-0.70 < posXplayer && posXplayer < proxX+0.70 && proxZ-0.70 < posZplayer && posZplayer < proxZ+0.70){
                        						if(congelado == false){
                            						congelado = true;
                        						}
                        						dentro_do_bloco = true;
                        						continue;
                        					}
                						}
                    					Cordenadas posprox = new Cordenadas(proxX, proxY, proxZ, 0, 0);
                    					if(posprox.CalcularDistanciaXZ(go) < 1.2){
                    						Jogador.moverPlayer(player, proxX, proxZ, false);
                    					} else {
                        					player.rotationYaw = go.rotacaoYaw();
                    						if(!player.isSneaking()){
                    							player.setSprinting(true);
                    						}
                    						if(player.isSprinting()){
                    							Jogador.moverPlayer(player, proxX, proxZ, false);
                    							if(3.5 < posprox.CalcularDistanciaXZ(go) && posprox.CalcularDistanciaXZ(go) < 3.8){
                        							if(evento.entity.onGround){
                        								evento.entity.jump();
                        							}
                            					} else if (posprox.CalcularDistanciaXZ(go) > 7.2){
                            						if(contPulo > 4){
                            							if(evento.entity.onGround){
                            								evento.entity.jump();
                            							}
                            						} else {
                            							contPulo++;
                            						}
                            					}
                    						} else {
                    							Jogador.moverPlayer(player, proxX, proxZ, false);
                    						}
                    					}
                					}
        						}
        					} else {
            					Cordenadas posprox = new Cordenadas(proxX, proxY, proxZ, 0, 0);
        						if(agora.getTime() - tempo >= delay){
                    			//player.addchatMessage(new ChatComponentText("permitindo dalay  = 6.2"));
        						delay_ok = true;
        						} else if(go.rotacaoYaw() != player.rotationYaw && posprox.CalcularDistanciaXZ(go) >= 1.2){
            						float diferenca_olhar = go.rotacaoYaw() - player.rotationYaw;
            						boolean pass = false;
            						if(diferenca_olhar > 360){
            							while(diferenca_olhar < 360){
            								diferenca_olhar -= 360;
            							}
            						}
            						if(diferenca_olhar < -360){
            							while(diferenca_olhar > -360){
            								diferenca_olhar += 360;
            							}
            						}
            						double math1 = delay/(agora.getTime() - tempo);
            						double math2 = diferenca_olhar/math1;
            						float girar = (float) math2;
            						player.rotationYaw += girar;
            					}
        					}
        				} else {
                			//player.addchatMessage(new ChatComponentText("dnao achou block = 5.2"));
        					player.addChatMessage(new ChatComponentText("nenhum local igual"));
        				}
                	}
        		} else{
        			delay = Dados.delay1+0;
        			if(areasDentro.contains(nome_area)){
        				//player.addchatMessage(new ChatComponentText("fora da area = 3.2"));
        				areasDentro.remove(nome_area);
        			}
        			RestaurarPadroes();
    			}
        	}
        }
    }
	public static void RestaurarPadroes() {
		areasDentro = new ArrayList<String>();
		congelado = false;
		congelado1 = false;
		delay = Dados.delay1+0;
		espera = 8000;
		inic_cont_tempo = 0;
		imprimir_espera = true;
		delay_ok = false;
		tempo = 0;
		dentro_do_bloco = false;
		dentro_do_bloco1 = false;
		salvo = false;
		salvo1 = false;
		contPulo = 0;
		ja_ta_dentro = false;
		ja_cont_inic = false;
		contTempo = true;
		nome_blocos_desiguais = new ArrayList<String>();		
	}
	private List<ChunkCoordinates> ScanerZ(int scanerX, int scanerY,int scanerZ, double maxZ, World mundo,String nome_item_igual, EntityPlayer player, double minX, double minZ, double maxX) {
		List<ChunkCoordinates> retorno = new ArrayList<ChunkCoordinates>();
		while(scanerZ <= maxZ){
			Block bloco = mundo.getBlock(scanerX, scanerY, scanerZ);
			ArrayList<ItemStack> drops = bloco.getDrops(mundo, scanerX, scanerY, scanerZ, mundo.getBlockMetadata(scanerX, scanerY, scanerZ), 999);
			String nome_bloco = bloco.getUnlocalizedName();
			if(drops.size() > 0){
				nome_bloco = drops.get(0).getUnlocalizedName();
			}
			if(nome_bloco.equalsIgnoreCase(nome_item_igual)){
				ChunkCoordinates cordenada_igual = new ChunkCoordinates(scanerX, scanerY, scanerZ);
				retorno.add(cordenada_igual);
			} else if(minZ < scanerZ && scanerZ < maxZ && minX < scanerX && scanerX < maxX ) {
				nome_blocos_desiguais.add(nome_bloco);
			}
			scanerZ++;
		}
		return retorno;		
	}
	
	private void MoverLocalEstrategico(EntityPlayer player, AreaSelecao area, FOVUpdateEvent evento) {
		//mover pro centro da area
		Cordenadas max = area.pegarMax();
		Cordenadas min = area.pegarMin();
		double minX = min.posX(); double minY = min.posY(); double minZ = min.posZ();
		double maxX = max.posX(); double maxY = max.posY(); double maxZ = max.posZ();
		double X = minX+((maxX - minX)/2);
		double Y = minX+((maxY - minY)/2);
		double Z = minZ+((maxZ - minZ)/2);
		Cordenadas cords = new Cordenadas(X, Y, Z, 0, 0);
		double proxX = cords.posX();
		double proxY = cords.posY();
		double proxZ = cords.posZ();
		double posXplayer = player.posX;
		double posYplayer = player.posY;
		double posZplayer = player.posZ;
		Cordenadas go = new Cordenadas(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
		go.Olhar(proxX, proxY, proxZ);
		if(!salvo1){
			if(dentro_do_bloco1 && proxX-0.5 < posXplayer && posXplayer < proxX+0.5 && proxZ-0.5 < posZplayer && posZplayer < proxZ+0.5){
				if(!Status.pegar(Outros.BPsalvarID)){
					salvo1 = true;
				}
			} else if(dentro_do_bloco1 == false && proxX-0.5 < posXplayer && posXplayer < proxX+0.5 && proxZ-0.5 < posZplayer && posZplayer < proxZ+0.5){
				if(congelado1 == false){
					congelado1 = true;
				}
				dentro_do_bloco1 = true;
			} else{
				congelado1 = false;
				dentro_do_bloco1 = false;
				Cordenadas posprox = new Cordenadas(proxX, proxY, proxZ, 0, 0);
				if(posprox.CalcularDistanciaXZ(go) < 1.2){
					Jogador.moverPlayer(player, proxX, proxZ, false);
				} else {
    				player.rotationYaw = go.rotacaoYaw();
					if(!player.isSneaking()){
						player.setSprinting(true);
					}
					if(player.isSprinting()){
						Jogador.moverPlayer(player, proxX, proxZ, false);
						if(3.5 < posprox.CalcularDistanciaXZ(go) && posprox.CalcularDistanciaXZ(go) < 3.8){
    						if(evento.entity.onGround){
    							evento.entity.jump();
    						}
        				} else if (posprox.CalcularDistanciaXZ(go) > 7.2){
        					if(contPulo > 4){
        						if(evento.entity.onGround){
        							evento.entity.jump();
        						}
        					} else {
        						contPulo++;
        					}
        				}
					} else {
						Jogador.moverPlayer(player, proxX, proxZ, false);
					}
				}
			}
		}
	}
	
	private void RastrearProximoBloco(){
    	int range = 10;
    	int cont = 0;
    	while(cont < 10){
    		cont++;
    	}
	}
}
