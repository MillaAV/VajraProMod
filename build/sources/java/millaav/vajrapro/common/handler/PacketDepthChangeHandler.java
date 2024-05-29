package millaav.vajrapro.common.handler;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import millaav.vajrapro.ItemVajra;
import millaav.vajrapro.common.Item.EnumUpgradeType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

public class PacketDepthChangeHandler implements IMessageHandler<PacketDepthChange, IMessage> {

    public PacketDepthChangeHandler() {
    }
    int maxDepth = 1;
    @Override
    public IMessage onMessage(PacketDepthChange message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().playerEntity;
        if (player == null) return null;
        if (player.getHeldItem() == null) return null;
        if (!(player.getHeldItem().getItem() instanceof ItemVajra)) return null;
        NBTTagCompound tag = ItemVajra.getOrCreateTag((player.getHeldItem()));
        maxDepth = 1;
        int depth = tag.getInteger("depth");
        if (ItemVajra.hasUpgrade(player.getHeldItem(), EnumUpgradeType.DEPTH)) {
            maxDepth = 3;
        } else if (ItemVajra.hasUpgrade(player.getHeldItem(), EnumUpgradeType.DEPTH1)) {
            maxDepth = 5;
        } else if (ItemVajra.hasUpgrade(player.getHeldItem(), EnumUpgradeType.DEPTH2)) {
            maxDepth = 7;
        }
        depth = (depth + 2)%(maxDepth+1);
        tag.setInteger("depth", depth);
        player.addChatMessage(new ChatComponentText("Depth is now: "+(1+tag.getInteger("depth"))));
        return null;
    }
}
