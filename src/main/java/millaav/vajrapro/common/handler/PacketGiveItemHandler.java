package millaav.vajrapro.common.handler;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketGiveItemHandler implements IMessageHandler<PacketGiveItem,IMessage> {

    public PacketGiveItemHandler() {
    }

    @Override
    public IMessage onMessage(PacketGiveItem message, MessageContext ctx) {
        for(int i=0;i<message.count;i++) {
            ctx.getServerHandler().playerEntity.entityDropItem(message.stack, 1);
        }
        return null;
    }
}
