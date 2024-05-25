package millaav.vajrapro.common.handler;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;

public class PacketGiveItem implements IMessage {
    public ItemStack stack;
    public int count;

    public PacketGiveItem() {
    }

    public PacketGiveItem(ItemStack stack, int count) {
        this.stack=stack;
        this.count=count;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(count);
        ByteBufUtils.writeItemStack(buf,stack);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        count = buf.readInt();
        stack = ByteBufUtils.readItemStack(buf);
    }
}
