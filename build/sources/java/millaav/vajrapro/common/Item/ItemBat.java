package millaav.vajrapro.common.Item;

import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import ic2.core.Ic2Items;
import ic2.core.init.InternalName;
import ic2.core.item.BaseElectricItem;
import ic2.core.item.ItemBattery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ItemBat extends BaseElectricItem {
    public double transferLimit;
    public int tier;
    public ItemBat() {
        super(InternalName.itemBatCrystal, 5000000, 2048,3);
    }

//    public String getTextureName(int index) {
//        return index < 5 ? this.getUnlocalizedName().substring(4) + "." + index : null;
//    }
//
//    @SideOnly(Side.CLIENT)
//    public IIcon getIconFromDamage(int meta) {
//        if (meta <= 1) {
//            return this.textures[4];
//        } else {
//            return meta >= this.getMaxDamage() - 1 ? this.textures[0] : this.textures[3 - 3 * (meta - 2) / (this.getMaxDamage() - 4 + 1)];
//        }
//    }

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
