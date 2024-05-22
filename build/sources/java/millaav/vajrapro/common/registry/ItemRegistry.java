package millaav.vajrapro.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import ic2.core.Ic2Items;
import ic2.core.init.InternalName;
import millaav.vajrapro.common.Item.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;

public class ItemRegistry {
    public static Item vajraС;
    public static Item vajraM;
    public static Item vajraP;
    public static Item upgrade;
    public static Item test;
    public static Item tier1upgrade;
    public static Item tier2upgrade;
    public static Item coolingcore;

    public static Item magnetron;
    public static Item superconductor;
    public static Item superconductorcover;
    public static Item vajracore;
    public static Item itemBatCrystal;
    public static Item energyCrystal;

    public static void init(){
//        energyCrystal = new Item().setCreativeTab(CreativeTabs.tabTools).setTextureName("vajrapro:energyCrystal").setUnlocalizedName("energyCrystal");
//        GameRegistry.registerItem(energyCrystal,"energyCrystal");
//        ItemBattery.energyCrystal = new ItemStack(new ItemBattery(InternalName.itemBatCrystal, 2000000.0, 2048.0, 3));

        energyCrystal = (new ItemBattery(InternalName.itemBatCrystal, 1000000.0, 2048.0, 3)).setUnlocalizedName("energyCrystal").setTextureName("vajrapro:energyCrystal").setCreativeTab(CreativeTabs.tabTools);
        GameRegistry.registerItem(energyCrystal,"energyCrystal");


        vajraС = (new ItemVajraC(Item.ToolMaterial.EMERALD)).setUnlocalizedName("vajra");
        GameRegistry.registerItem(vajraС,"vajra");
        vajraM = (new ItemVajraM(Item.ToolMaterial.EMERALD)).setUnlocalizedName("vajram");
        GameRegistry.registerItem(vajraM,"vajram");
        vajraP = (new ItemVajraP(Item.ToolMaterial.EMERALD)).setUnlocalizedName("vajrap");
        GameRegistry.registerItem(vajraP, "vajrap");
        upgrade = new ItemUpgrade();
        GameRegistry.registerItem(upgrade,"upgrade");
        test = new Test(Item.ToolMaterial.EMERALD);
        GameRegistry.registerItem(test, "test");
        tier1upgrade = new Item().setCreativeTab(CreativeTabs.tabTools).setTextureName("vajrapro:tier1upgrade").setUnlocalizedName("tier1upgrade");
        GameRegistry.registerItem(tier1upgrade,"tier1upgrade");
        tier2upgrade = new Item().setCreativeTab(CreativeTabs.tabTools).setTextureName("vajrapro:tier2upgrade").setUnlocalizedName("tier2upgrade");
        GameRegistry.registerItem(tier2upgrade,"tier2upgrade");
        coolingcore = new Item().setCreativeTab(CreativeTabs.tabTools).setTextureName("vajrapro:coolingcore").setUnlocalizedName("coolingcore");
        GameRegistry.registerItem(coolingcore,"coolingcore");

        magnetron = new Item().setCreativeTab(CreativeTabs.tabTools).setTextureName("vajrapro:magnetron").setUnlocalizedName("magnetron");
        GameRegistry.registerItem(magnetron,"magnetron");
        superconductor = new Item().setCreativeTab(CreativeTabs.tabTools).setTextureName("vajrapro:superconductor").setUnlocalizedName("superconductor");
        GameRegistry.registerItem(superconductor,"superconductor");
        superconductorcover = new Item().setCreativeTab(CreativeTabs.tabTools).setTextureName("vajrapro:superconductorcover").setUnlocalizedName("superconductorcover");
        GameRegistry.registerItem(superconductorcover,"superconductorcover");
        vajracore = new Item().setCreativeTab(CreativeTabs.tabTools).setTextureName("vajrapro:vajracore").setUnlocalizedName("vajracore");
        GameRegistry.registerItem(vajracore,"vajracore");
    }
}
