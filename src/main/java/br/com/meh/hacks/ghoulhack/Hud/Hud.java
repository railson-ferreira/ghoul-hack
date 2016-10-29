package br.com.meh.hacks.ghoulhack.Hud;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Collection;
import java.util.Iterator;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import br.com.meh.hacks.ghoulhack.GhoulHack;
import br.com.meh.hacks.ghoulhack.gui.HackDisplay;
import br.com.meh.hacks.ghoulhack.gui.util.TelaInformacoes;
import br.com.meh.hacks.ghoulhack.util.Metodos;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.gameevent.InputEvent.MouseInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.event.ClickEvent;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;

public class Hud extends Gui{
	private Minecraft mc;
	
	public Hud(Minecraft mc) {
	    super();
	    this.mc = mc;
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRender(RenderGameOverlayEvent evento){
		if(evento.isCancelable() || evento.type != ElementType.EXPERIENCE){
			return;
		}
		int telaLargura = evento.resolution.getScaledWidth();
		int telaAltura = evento.resolution.getScaledHeight();
		int mouseX = evento.mouseX;
		int mouseY = evento.mouseY;
		if(mc.currentScreen instanceof br.com.meh.hacks.ghoulhack.gui.Gui)
			return;
    	if(GhoulHack.telaDoHack == null){
    		GhoulHack.telaDoHack = new HackDisplay(telaLargura, telaAltura);
    		GhoulHack.telaDoHack.inicializar();
    	}
    	GhoulHack.telaDoHack.desenhar(telaLargura, telaAltura);
	}

}
