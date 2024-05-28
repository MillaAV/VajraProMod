package millaav.vajrapro.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import millaav.vajrapro.common.CommonProxy;
import millaav.vajrapro.common.handler.KeyHandler;

public class ClientProxy extends CommonProxy {
    public static final Side side = FMLCommonHandler.instance().getEffectiveSide();
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
    }

    public void init(FMLInitializationEvent event){
        super.init(event);
        ClientRegistry.registerKeyBinding(KeyHandler.changeautosmelt);
        ClientRegistry.registerKeyBinding(KeyHandler.changedepths);
        FMLCommonHandler.instance().bus().register(new KeyHandler());
    }

    public void postInit(FMLPostInitializationEvent event){
        super.postInit(event);
    }

}
