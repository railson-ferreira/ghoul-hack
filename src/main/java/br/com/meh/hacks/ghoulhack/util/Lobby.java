package br.com.meh.hacks.ghoulhack.util;

import java.util.List;

public class Lobby {
	private AreaSelecao area;
	private List<Cordenadas> placas;
	public Lobby(AreaSelecao area, List<Cordenadas> placas){
		this.area = area;
		this.placas = placas;
	}
	public AreaSelecao PegarArea(){
		return area;
	}
	public void DefinirArea(AreaSelecao novaArea){
		AreaSelecao retorno = area;
		area = novaArea;
	}
	public List<Cordenadas> PegarPlacas(){
		return placas;
	}
	public void DefinirPlacas(List<Cordenadas> novaPlacas){
		List<Cordenadas> retorno = placas;
		placas = novaPlacas;
	}
	public void addPlacas(List<Cordenadas> placas){
		placas.addAll(placas);
	}
	public void addPlaca(Cordenadas placa){
		placas.add(placa);
	}
	
}
