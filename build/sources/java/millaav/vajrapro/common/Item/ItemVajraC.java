package millaav.vajrapro.common.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import millaav.vajrapro.ItemVajra;
import millaav.vajrapro.VajraPro;
import millaav.vajrapro.client.ClientProxy;
import millaav.vajrapro.common.CommonProxy;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.HashSet;
import java.util.List;

public class ItemVajraC extends ItemVajra implements IElectricItem {
        public ItemVajraC(Item.ToolMaterial toolMaterial) {
        super(0.0F, toolMaterial, new HashSet());
        this.setMaxDamage(27);
        this.maxCharge = 3000000;
        this.tier = 2;
        this.transferLimit = 60000;
        this.effPower = 2000.0F;
        this.efficiencyOnProperMaterial = this.effPower;
        this.energyPerOperation = 3333;
        this.setCreativeTab(CreativeTabs.tabTools);
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack var1) {
        return EnumRarity.common;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("vajrapro:itemVajra");
    }

    public int getVajraTier(){
        return 1;
    }
}
