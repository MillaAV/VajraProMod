package millaav.vajrapro.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import millaav.vajrapro.common.block.BlockToolStation;
//import millaav.vajrapro.common.block.WirelessChargingStation;
import net.minecraft.block.Block;

public class BlockRegistry {
    public static Block toolStation = new BlockToolStation();
//    public static Block WirelessChargingStation = new WirelessChargingStation();

    public static void init(){
        GameRegistry.registerBlock(toolStation,"toolstation");
//        GameRegistry.registerBlock(WirelessChargingStation,"wirelesschargingstation");
    }
}
