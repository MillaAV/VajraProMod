package millaav.vajrapro;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;

public class Keyboard {
    private static Map modeKeyState = new HashMap();

    public static boolean isModeKeyDown(EntityPlayer player) {
        return modeKeyState.containsKey(player) ? (Boolean)modeKeyState.get(player) : false;
    }

    public void processKeyUpdate(EntityPlayer player, int i) {
        modeKeyState.put(player, (i & 4) != 0);
    }
}
