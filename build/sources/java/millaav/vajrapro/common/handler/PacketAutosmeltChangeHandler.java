package millaav.vajrapro.common.handler;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import millaav.vajrapro.ItemVajra;
import net.minecraft.nbt.NBTTagCompound;

public class PacketAutosmeltChangeHandler implements IMessageHandler<PacketAutosmeltChange, IMessage> {

    public PacketAutosmeltChangeHandler() {
    }

    @Override
    public IMessage onMessage(PacketAutosmeltChange message, MessageContext ctx) {
        if (ctx.getServerHandler().playerEntity == null) return null;
        if (ctx.getServerHandler().playerEntity.getHeldItem() == null) return null;
        if (!(ctx.getServerHandler().playerEntity.getHeldItem().getItem() instanceof ItemVajra)) return null;
        NBTTagCompound tag = ItemVajra.getOrCreateTag((ctx.getServerHandler().playerEntity.getHeldItem()));
        tag.setBoolean("autosmeltEnabled", !tag.getBoolean("autosmeltEnabled"));
        return null;
    }
}
