package millaav.vajrapro.common.handler;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import millaav.vajrapro.ItemVajra;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

public class PacketAutosmeltChangeHandler implements IMessageHandler<PacketAutosmeltChange, IMessage> {

    public PacketAutosmeltChangeHandler() {
    }

    @Override
    public IMessage onMessage(PacketAutosmeltChange message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().playerEntity;
        if (player == null) return null;
        if (player.getHeldItem() == null) return null;
        if (!(player.getHeldItem().getItem() instanceof ItemVajra)) return null;
        NBTTagCompound tag = ItemVajra.getOrCreateTag((player.getHeldItem()));
        tag.setBoolean("autosmeltEnabled", !tag.getBoolean("autosmeltEnabled"));
        player.addChatMessage(new ChatComponentText("Autosmelt is now: "+(tag.getBoolean("autosmeltEnabled")?"enabled":"disabled")));
        return null;
    }
}
