package millaav.vajrapro.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import millaav.vajrapro.common.Item.*;
import net.minecraft.item.Item;

import static millaav.vajrapro.ItemVajra.tabVajraPro;

public class ItemRegistry {
    public static Item vajraС;
    public static Item vajraP;
    public static Item vajraM;
    public static Item upgrade;
    public static Item tier1upgrade;
    public static Item tier2upgrade;
    public static Item coolingcore;
    public static Item magnetron;
    public static Item magnetron1;
    public static Item magnetron2;
    public static Item vajracore;
    public static Item vajracore1;
    public static Item vajracore2;
    public static Item superconductorcover;
    public static Item superconductor;
    public static Item goldplatedenergycrystal;

    public static void init(){

        goldplatedenergycrystal = new ItemBat().setCreativeTab(tabVajraPro).setTextureName("vajrapro:goldplatedenergycrystal").setUnlocalizedName("goldplatedenergycrystal");

        vajraС = (new ItemVajraC(Item.ToolMaterial.EMERALD)).setUnlocalizedName("vajra");
        GameRegistry.registerItem(vajraС,"vajra");
        vajraP = (new ItemVajraP(Item.ToolMaterial.EMERALD)).setUnlocalizedName("vajrap");
        GameRegistry.registerItem(vajraP, "vajrap");
        vajraM = (new ItemVajraM(Item.ToolMaterial.EMERALD)).setUnlocalizedName("vajram");
        GameRegistry.registerItem(vajraM,"vajram");

        upgrade = new ItemUpgrade();
        GameRegistry.registerItem(upgrade,"upgrade");
        tier1upgrade = new Item().setCreativeTab(tabVajraPro).setTextureName("vajrapro:tier1upgrade").setUnlocalizedName("tier1upgrade");
        GameRegistry.registerItem(tier1upgrade,"tier1upgrade");
        tier2upgrade = new Item().setCreativeTab(tabVajraPro).setTextureName("vajrapro:tier2upgrade").setUnlocalizedName("tier2upgrade");
        GameRegistry.registerItem(tier2upgrade,"tier2upgrade");
        coolingcore = new Item().setCreativeTab(tabVajraPro).setTextureName("vajrapro:coolingcore").setUnlocalizedName("coolingcore");
        GameRegistry.registerItem(coolingcore,"coolingcore");

        magnetron = new Item().setCreativeTab(tabVajraPro).setTextureName("vajrapro:magnetron").setUnlocalizedName("magnetron");
        GameRegistry.registerItem(magnetron,"magnetron");
        magnetron1 = new Item().setCreativeTab(tabVajraPro).setTextureName("vajrapro:magnetron1").setUnlocalizedName("magnetron1");
        GameRegistry.registerItem(magnetron1,"magnetron1");
        magnetron2 = new Item().setCreativeTab(tabVajraPro).setTextureName("vajrapro:magnetron2").setUnlocalizedName("magnetron2");
        GameRegistry.registerItem(magnetron2,"magnetron2");
        superconductor = new Item().setCreativeTab(tabVajraPro).setTextureName("vajrapro:superconductor").setUnlocalizedName("superconductor");
        GameRegistry.registerItem(superconductor,"superconductor");
        superconductorcover = new Item().setCreativeTab(tabVajraPro).setTextureName("vajrapro:superconductorcover").setUnlocalizedName("superconductorcover");
        GameRegistry.registerItem(superconductorcover,"superconductorcover");
        vajracore = new Item().setCreativeTab(tabVajraPro).setTextureName("vajrapro:vajracore").setUnlocalizedName("vajracore");
        GameRegistry.registerItem(vajracore,"vajracore");
        vajracore1 = new Item().setCreativeTab(tabVajraPro).setTextureName("vajrapro:vajracore1").setUnlocalizedName("vajracore1");
        GameRegistry.registerItem(vajracore1,"vajracore1");
        vajracore2 = new Item().setCreativeTab(tabVajraPro).setTextureName("vajrapro:vajracore2").setUnlocalizedName("vajracore2");
        GameRegistry.registerItem(vajracore2,"vajracore2");
    }
}
