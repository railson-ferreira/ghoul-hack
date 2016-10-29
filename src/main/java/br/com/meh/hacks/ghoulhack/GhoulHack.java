package br.com.meh.hacks.ghoulhack;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.EventObject;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.JsonObject;
import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.util.GregorianCalendar;

import br.com.meh.hacks.ghoulhack.Hud.Hud;
import br.com.meh.hacks.ghoulhack.arquivos.Arquivos;
import br.com.meh.hacks.ghoulhack.comandos.Comando;
import br.com.meh.hacks.ghoulhack.gui.HackDisplay;
import br.com.meh.hacks.ghoulhack.minigames.BlockParty;
import br.com.meh.hacks.ghoulhack.minigames.Lobbies;
import br.com.meh.hacks.ghoulhack.util.Loop;
import br.com.meh.hacks.ghoulhack.util.Metodos;
import br.com.meh.hacks.ghoulhack.util.TickHandler;
import net.java.games.input.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.JsonBlendingMode;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkCheckHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = GhoulHack.MODID, name = "GhoulHack", version = GhoulHack.VERSION)
public class GhoulHack {
	public static final String MODID = "ghoulhack";
	public static final String VERSION = "1.0";
	
	public static HackDisplay telaDoHack = null;
	
	@Instance(GhoulHack.MODID) public static GhoulHack instance;
	
	public GhoulHack() {
		instance = this;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		
		Metodos.escreverNoConsole("Carregando Arquivos...");
		Arquivos.recarregar();
		Metodos.escreverNoConsole("Arquivos Carregados.");
		FMLCommonHandler.instance().bus().register(new PressionamentoDeTeclas());
		
    	MinecraftForge.EVENT_BUS.register(new ManipuladorDeEventos());
		Minigames.registrar();
		Teclas.inicializar();
		Comando.Inicializar();
		Status.inicializar();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new Hud(Minecraft.getMinecraft()));
		Timer oi = new Timer();
		oi.schedule(new Loop(), 1000, 1);
	}
}
