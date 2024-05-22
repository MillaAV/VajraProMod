package millaav.vajrapro.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import millaav.vajrapro.common.container.ContainerToolStation;
import millaav.vajrapro.common.tile.TileToolStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        if(ID == 0)
            return new GuiToolStation(new ContainerToolStation((TileToolStation) world.getTileEntity(x,y,z), player.inventory));
        return null;
    }
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 0)
            return new ContainerToolStation((TileToolStation) world.getTileEntity(x,y,z), player.inventory);
        return null;
    }
}
