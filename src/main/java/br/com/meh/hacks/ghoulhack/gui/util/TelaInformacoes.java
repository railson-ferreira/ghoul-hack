package br.com.meh.hacks.ghoulhack.gui.util;

public class TelaInformacoes {
	int telaLargura;
	int telaAltura;
	int mouseX;
	int mouseY;
	boolean mouselocation;
	public TelaInformacoes(int telaLargura, int telaAltura, int mouseX, int mouseY){
		this.telaLargura = telaLargura;
		this.telaAltura = telaAltura;
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		mouselocation = true;
	}
	public TelaInformacoes(int telaLargura, int telaAltura){
		this.telaLargura = telaLargura;
		this.telaAltura = telaAltura;
		mouseX = 999999999;
		mouseY = 999999999;
		mouselocation = false;
	}
	public int pegarMouseX() {
		return mouseX;
	}
	public int pegarMouseY() {
		return mouseY;
	}
	public int pegarTelaAltura() {
		return telaAltura;
	}
	public int pegarTelaLargura() {
		return telaLargura;
	}
	public boolean contemMouselocation() {
		return mouselocation;
	}
}
