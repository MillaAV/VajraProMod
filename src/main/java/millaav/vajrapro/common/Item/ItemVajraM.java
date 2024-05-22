package millaav.vajrapro.common.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import millaav.vajrapro.ItemVajra;
import millaav.vajrapro.VajraPro;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ItemVajraM extends ItemVajra implements IElectricItem {

    public ItemVajraM(Item.ToolMaterial toolMaterial) {
        super(0.0F, toolMaterial, new HashSet());;
        setCreativeTab(CreativeTabs.tabTools);
        setUnlocalizedName("test");
        setMaxStackSize(1);
        setTextureName("vajrapro:test");
        this.tier = 2;
        this.maxCharge = 18000000;
        this.transferLimit = 600000;
        this.effPower = 2000.0F;
        this.efficiencyOnProperMaterial = effPower;
        this.energyPerOperation = 3333;
        this.setHarvestLevel("pickaxe", 10);
        this.setHarvestLevel("axe", 10);
        this.setHarvestLevel("shovel", 10);
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack var1) {
        return EnumRarity.epic;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("vajrapro:itemVajraM");
    }

    public int getVajraTier(){
        return 3;
    }
}
