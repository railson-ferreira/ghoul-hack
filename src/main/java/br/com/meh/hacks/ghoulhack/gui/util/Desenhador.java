package br.com.meh.hacks.ghoulhack.gui.util;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;

public class Desenhador {
	public final static Tessellator tessellator = Tessellator.instance;
    public final static FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
	public static void desenharRetangulo(int x, int y, int largura, int altura, int cor){
        GL11.glDisable(GL11.GL_TEXTURE_2D);GL11.glEnable(GL11.GL_BLEND);GL11.glDisable(GL11.GL_ALPHA_TEST);OpenGlHelper.glBlendFunc(770, 771, 1, 0);GL11.glShadeModel(GL11.GL_SMOOTH);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_I(cor, 255);
        tessellator.addVertex(x, y, 0.0D);
        tessellator.addVertex(x, y+altura, 0.0D);
        tessellator.addVertex(x+largura, y+altura, 0.0D);
        tessellator.addVertex(x+largura, y, 0.0D);
        tessellator.draw();
        GL11.glShadeModel(GL11.GL_FLAT);GL11.glDisable(GL11.GL_BLEND);GL11.glEnable(GL11.GL_ALPHA_TEST);GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	public static void desenharRetanguloTransparente(int x, int y, int largura, int altura, int cor, int alpha){
		GL11.glDisable(GL11.GL_TEXTURE_2D);GL11.glEnable(GL11.GL_BLEND);GL11.glDisable(GL11.GL_ALPHA_TEST);OpenGlHelper.glBlendFunc(770, 771, 1, 0);GL11.glShadeModel(GL11.GL_SMOOTH);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_I(cor, alpha);
        tessellator.addVertex(x, y, 0.0D);
        tessellator.addVertex(x, y+altura, 0.0D);
        tessellator.addVertex(x+largura, y+altura, 0.0D);
        tessellator.addVertex(x+largura, y, 0.0D);
        tessellator.draw();
        GL11.glShadeModel(GL11.GL_FLAT);GL11.glDisable(GL11.GL_BLEND);GL11.glEnable(GL11.GL_ALPHA_TEST);GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
	public static void desenharTextoCentralizado(String texto, int x, int y, int color, boolean sombra){
		fr.drawString(texto, x-fr.getStringWidth(texto)/2, y-fr.FONT_HEIGHT/2, color, sombra);
	}
	public static void desenharTextoCentralizadoNoY(String texto, int x, int y, int color, boolean sombra){
		fr.drawString(texto, x, y-fr.FONT_HEIGHT/2, color, sombra);
	}
}
