package millaav.vajrapro.common.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.Ic2Items;
import ic2.core.init.InternalName;
import ic2.core.item.BaseElectricItem;
import ic2.core.item.ItemBattery;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;


public class ItemBat extends BaseElectricItem {
    public double transferLimit;
    public int tier;

    public ItemBat() {
        super(InternalName.itemBatCrystal, 5000000, 2048, 3);
    }

    public String getTextureName(int index) {
        if (index == 0) {
            return "goldplatedenergycrystal0";
        }
        if (index == 1) {
            return "goldplatedenergycrystal1";
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        if (meta == 1) {
            return this.textures[1];
        } else {
            return this.textures[0];
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        int indexCount = 0;

        while(this.getTextureName(indexCount) != null) {
            ++indexCount;
            if (indexCount > 32767) {
                throw new RuntimeException("More Item Icons than actually possible @ " + this.getUnlocalizedName());
            }
        }

        this.textures = new IIcon[indexCount];
        String textureFolder = this.getTextureFolder() == null ? "" : this.getTextureFolder() + "/";

        for(int index = 0; index < indexCount; ++index) {
            this.textures[index] = iconRegister.registerIcon("vajrapro" + ":" + textureFolder + this.getTextureName(index));
        }

    }

    public boolean canProvideEnergy(ItemStack itemStack) {
        return true;
    }

    public Item getEmptyItem(ItemStack itemStack) {
        return this == Ic2Items.chargedReBattery.getItem() ? Ic2Items.reBattery.getItem() : super.getEmptyItem(itemStack);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityplayer) {
        if (IC2.platform.isSimulating() && itemStack.getItem() == Ic2Items.chargedReBattery.getItem()) {
            boolean transferred = false;

            for(int i = 0; i < 9; ++i) {
                ItemStack stack = entityplayer.inventory.mainInventory[i];
                if (stack != null && !(stack.getItem() instanceof ItemBattery)) {
                    double transfer = ElectricItem.manager.discharge(itemStack, 2.0 * this.transferLimit, Integer.MAX_VALUE, true, true, true);
                    if (!(transfer <= 0.0)) {
                        transfer = ElectricItem.manager.charge(stack, transfer, this.tier, true, false);
                        if (!(transfer <= 0.0)) {
                            ElectricItem.manager.discharge(itemStack, transfer, Integer.MAX_VALUE, true, true, false);
                            transferred = true;
                        }
                    }
                }
            }

            if (transferred && !IC2.platform.isRendering()) {
                entityplayer.openContainer.detectAndSendChanges();
            }
        }
        return itemStack;
    }
}
