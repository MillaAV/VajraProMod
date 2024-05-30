package millaav.vajrapro.common.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.IC2;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import java.util.List;

import static java.awt.SystemColor.info;

public class ItemUpgrade extends Item {
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
    public ItemUpgrade(){
        setCreativeTab(IC2.tabIC2);
        setMaxStackSize(1);
        setHasSubtypes(true);
        setMaxDamage(0);
        setTextureName("vajrapro:upgrade");
        setUnlocalizedName("upgrade");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        int j = MathHelper.clamp_int(meta, 0, EnumUpgradeType.values().length);
        return icons[j];
    }

    public String getUnlocalizedName(ItemStack stack) {
        int i = MathHelper.clamp_int(stack.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + "." + EnumUpgradeType.values()[i].key;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[EnumUpgradeType.values().length];

        for (int i = 0; i < EnumUpgradeType.values().length; ++i) {
            this.icons[i] = iconRegister.registerIcon(this.getIconString() + "_" + EnumUpgradeType.values()[i].key);
        }
    }

    @Override
    public void getSubItems(Item item, CreativeTabs creativeTab, List itemList) {
        for (int i = 0; i < EnumUpgradeType.values().length; ++i) {
            itemList.add(new ItemStack(item, 1, i));
        }
    }
}