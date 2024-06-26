package millaav.vajrapro;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import millaav.vajrapro.common.CommonProxy;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mod(
        modid = "vajrapro",
        name = "Vajra Pro",
        dependencies = "required-after:IC2; after:RedPowerCore",
        version = "1.1.1"
)
public class VajraPro {

    @SidedProxy(clientSide = "millaav.vajrapro.client.ClientProxy",
                serverSide = "millaav.vajrapro.common.CommonProxy")
    public static CommonProxy proxy;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
    }

    @Mod.EventHandler
    public void init(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }

    @Mod.Instance("vajrapro")
    public static VajraPro instance;

    public static boolean isSimulating() {
        return !FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    public static void addLog(String logString) {
        System.out.println("[VajraPro] " + logString);
    }

    public static void dropAsEntity(World var0, int var1, int var2, int var3, ItemStack var4) {
        if (var4 != null) {
            double var5 = 0.7;
            double var7 = (double)var0.rand.nextFloat() * var5 + (1.0 - var5) * 0.5;
            double var9 = (double)var0.rand.nextFloat() * var5 + (1.0 - var5) * 0.5;
            double var11 = (double)var0.rand.nextFloat() * var5 + (1.0 - var5) * 0.5;
            EntityItem var13 = new EntityItem(var0, (double)var1 + var7, (double)var2 + var9, (double)var3 + var11, var4.copy());
            var13.delayBeforeCanPickup = 10;
            var0.spawnEntityInWorld(var13);
        }

    }
}
