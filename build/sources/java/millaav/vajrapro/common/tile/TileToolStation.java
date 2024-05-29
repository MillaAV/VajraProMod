package millaav.vajrapro.common.tile;

import millaav.vajrapro.ItemVajra;
import millaav.vajrapro.common.Item.ItemVajraC;
import millaav.vajrapro.common.Item.ItemVajraP;
import millaav.vajrapro.common.registry.ItemRegistry;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileToolStation extends TileEntity {
    public InventoryBasic inv;
    public int upgradingProgress;

    public TileToolStation(){
        inv = new InventoryBasic("", false, 2);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("invSize", inv.getSizeInventory());
        NBTTagList invContents = new NBTTagList();
        for(int i=0;i<inv.getSizeInventory();i++){
            NBTTagCompound itemTag = new NBTTagCompound();
            if(inv.getStackInSlot(i)!=null){
                inv.getStackInSlot(i).writeToNBT(itemTag);
            }
            invContents.appendTag(itemTag);
        }
        tag.setTag("invContents", invContents);
        tag.setInteger("upgradingProgress", upgradingProgress);
    }


    public boolean canUpgrade() {
        ItemStack vajra = inv.getStackInSlot(0);
        ItemStack upgrade = inv.getStackInSlot(1);
        if (vajra == null || !(vajra.getItem() instanceof ItemVajra)) return false;
        if (upgrade == null) return false;
        if ((vajra.getItem() instanceof ItemVajraC && upgrade.getItem() == ItemRegistry.tier1upgrade) || (vajra.getItem() instanceof ItemVajraP && upgrade.getItem() == ItemRegistry.tier2upgrade)) return true;
        return false;
    }

    public void performUpgrade() {
        ItemStack vajra = inv.getStackInSlot(0);
        inv.setInventorySlotContents(1, null);
        ItemStack resStack = null;
        if (vajra.getItem() instanceof ItemVajraC) {
            resStack = new ItemStack(ItemRegistry.vajraP);
        }
        if (vajra.getItem() instanceof ItemVajraP) {
            resStack = new ItemStack(ItemRegistry.vajraM);
        }
        resStack.stackTagCompound = vajra.stackTagCompound;
        inv.setInventorySlotContents(0, resStack);
    }

    public void updateEntity() {
        if (!this.worldObj.isRemote) {
            if (canUpgrade()) {
                ++upgradingProgress;
                if (upgradingProgress == 200) {
                    upgradingProgress = 0;
                    performUpgrade();
                    markDirty();
                }
            } else {
                this.upgradingProgress = 0;
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        int size = tag.getInteger("invSize");
        inv = new InventoryBasic("", false, size);
        NBTTagList invContents = tag.getTagList("invContents", 10);
        for(int i=0;i<size;i++){
            NBTTagCompound itemTag = invContents.getCompoundTagAt(i);
            if(!itemTag.hasNoTags()){
                inv.setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(itemTag));
            }
        }
    }
}
