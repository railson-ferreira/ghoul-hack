package br.com.meh.hacks.ghoulhack;

import br.com.meh.hacks.ghoulhack.gui.Gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Gui.GUI_ID) {
            System.out.print("create gui.\n");
            return new Gui();
        }
        return null;
    }
}