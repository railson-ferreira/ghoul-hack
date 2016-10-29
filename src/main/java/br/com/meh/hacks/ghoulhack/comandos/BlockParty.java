package br.com.meh.hacks.ghoulhack.comandos;

import java.util.HashMap;
import java.util.Set;

import br.com.meh.hacks.ghoulhack.Status;
import br.com.meh.hacks.ghoulhack.arquivos.AreasBlockParty;
import br.com.meh.hacks.ghoulhack.status.MiniGames;
import br.com.meh.hacks.ghoulhack.status.Teste;
import br.com.meh.hacks.ghoulhack.util.AreaSelecao;
import br.com.meh.hacks.ghoulhack.util.Cordenadas;
import br.com.meh.hacks.ghoulhack.util.Dados;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.event.ServerChatEvent;

public class BlockParty {
	public static void executar(ServerChatEvent evento){
		String ajuda_area = ".blockparty area criar <nome> - Criar Uma area BlockParty<br>"
				+ ".blockparty area remover <nome> - Remover uma area BlockParty<br>"
				+ ".blockparty area editar <nome> - Editar uma area BlockParty<br>"
				+ ".blockparty area info - Mostrar informações da Area<br>"
				+ ".blockparty area lista - Listar todas as areas";
		String ajuda = ajuda_area+"\n"
				+ ".blockparty alternar - Ligar ou desligar o hack<br>";
		EntityPlayer player = evento.player;
		String[] args = evento.message.split(" ");
		if(args.length > 1){
			if(args[1].equalsIgnoreCase("area")){
				if(args.length > 2){
					if(args[2].equalsIgnoreCase("criar")){
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
					} else if(args[2].equalsIgnoreCase("remover")){
						if(args.length > 3){
							
						} else {
							player.addChatMessage(new ChatComponentText("Parametro <nome> obrigatorio faltando§r\nTente: .blockparty area remover <nome>"));
						}
					} else if(args[2].equalsIgnoreCase("editar")){
						if(args.length > 3){
							
						} else {
							player.addChatMessage(new ChatComponentText("Parametro <nome> obrigatorio faltando\nTente: .blockparty area editar <nome>"));
						}
					} else if(args[2].equalsIgnoreCase("info")){
						
					} else if(args[2].equalsIgnoreCase("lista")){
						HashMap<String, AreaSelecao> areas = AreasBlockParty.Pegar();
						Set<String> nome_areas = areas.keySet();
						player.addChatMessage(new ChatComponentText("===Lista de Areas==="));
						for(String nome_area : nome_areas){
							AreaSelecao area_selecao = areas.get(nome_area);
							Cordenadas max = area_selecao.pegarMax();
							Cordenadas min = area_selecao.pegarMin();
							int minX = (int)min.posX(); int minY = (int)min.posY(); int minZ = (int)min.posZ();
							int maxX = (int)max.posX(); int maxY = (int)max.posY(); int maxZ = (int)max.posZ();
							player.addChatMessage(new ChatComponentText(nome_area+":"+"min="+minX+","+minY+","+minZ+" / "+"max="+maxX+","+maxY+","+maxZ));
						}
						player.addChatMessage(new ChatComponentText("===================="));
					} else {
						player.addChatMessage(new ChatComponentText("Comando Invalido\n"+ajuda_area));
					}
				} else {
					player.addChatMessage(new ChatComponentText(ajuda_area));
				}
			} else if(args[1].equalsIgnoreCase("alternar")){
				br.com.meh.hacks.ghoulhack.minigames.BlockParty.alternarStatus();
				if(Status.pegar(MiniGames.BlockPartyID)){
					player.addChatMessage(new ChatComponentText("[BlockParty] BlockParty foi desativado"));
				} else {
					player.addChatMessage(new ChatComponentText("[BlockParty] BlockParty foi ativado"));
				}
			} else {
				player.addChatMessage(new ChatComponentText("Comando Invalido\n"+ajuda));
			}
		} else {
			player.addChatMessage(new ChatComponentText(ajuda));
		}
	}
}
