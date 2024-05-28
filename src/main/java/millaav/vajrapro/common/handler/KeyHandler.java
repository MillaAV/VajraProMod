package millaav.vajrapro.common.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyHandler{

    public static final KeyBinding changeautosmelt = new KeyBinding("key.changeautosmelt", Keyboard.KEY_I, "key.vajrapro.category");
    public static final KeyBinding changedepths = new KeyBinding("key.changedepths", Keyboard.KEY_O, "key.vajrapro.category");

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onEvent(InputEvent.KeyInputEvent event){
        if (changeautosmelt.isPressed()){
            NetworkHandler.sendToServer(new PacketAutosmeltChange());
        }
        if (changedepths.isPressed()){
            NetworkHandler.sendToServer(new PacketDepthChange());
        }
    }
}
