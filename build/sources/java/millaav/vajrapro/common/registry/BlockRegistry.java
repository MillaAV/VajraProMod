package millaav.vajrapro.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import millaav.vajrapro.common.block.BlockToolStation;
import net.minecraft.block.Block;

public class BlockRegistry {
    public static Block toolStation = new BlockToolStation();

    public static void init(){
        GameRegistry.registerBlock(toolStation,"toolstation");
    }
}
