package millaav.vajrapro.common.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import millaav.vajrapro.ItemVajra;
import millaav.vajrapro.common.Item.EnumUpgradeType;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.HashSet;

import static millaav.vajrapro.ItemVajra.getOrCreateTag;

public class KeyHandler{

    public static final KeyBinding changedepths = new KeyBinding("key.changedepths", Keyboard.KEY_I, "key.vajrapro.category");
    public static final KeyBinding changedepths1 = new KeyBinding("key.changedepths", Keyboard.KEY_O, "key.vajrapro.category");
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onEvent(InputEvent.KeyInputEvent event){
        if (changedepths.isPressed()){
        }
    }
}
