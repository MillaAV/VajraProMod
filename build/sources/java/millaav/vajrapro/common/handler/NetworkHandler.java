package millaav.vajrapro.common.handler;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class NetworkHandler {
    private static final SimpleNetworkWrapper wrapper = new SimpleNetworkWrapper("vajrapro");

    public static void init(){
        wrapper.registerMessage(PacketAutosmeltChangeHandler.class, PacketAutosmeltChange.class,0, Side.SERVER);
        wrapper.registerMessage(PacketDepthChangeHandler.class, PacketDepthChange.class ,0, Side.SERVER);
    }

    public static void sendToPlayer(IMessage message, EntityPlayer player) {
        wrapper.sendTo(message, (EntityPlayerMP) player);
    }

    public static void sendToAll(IMessage message) {
        wrapper.sendToAll(message);
    }

    public static void sendToServer(IMessage message) {
        wrapper.sendToServer(message);
    }
}
