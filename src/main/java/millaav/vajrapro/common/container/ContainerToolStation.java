package millaav.vajrapro.common.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import millaav.vajrapro.ItemVajra;
import millaav.vajrapro.common.Item.EnumUpgradeType;
import millaav.vajrapro.common.Item.ItemUpgrade;
import millaav.vajrapro.common.registry.ItemRegistry;
import millaav.vajrapro.common.tile.TileToolStation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class ContainerToolStation extends Container {
    public InventoryBasic inv;
    public Slot vajraSlot;
    public Slot upgradeSlot;
    public TileToolStation tile;
    public int lastUpgradeProgress;

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }
    public ContainerToolStation(TileToolStation tile, InventoryPlayer playerInv){
        this.tile = tile;
        int invX=8, invY=106;

        inv = new InventoryBasic("toolstationinventory", false,8);
        //10 slots. 0 - vajra, 1 - upgrade, 2 - autosmelting, 3 - silktouch, 4-9 - others
        vajraSlot = this.addSlotToContainer(new Slot(tile.inv, 0, 80, 21){
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                return p_75214_1_==null || p_75214_1_.getItem() instanceof ItemVajra;
            }

            @Override
            public void onSlotChanged() {
                super.onSlotChanged();
                setSlots();
            }
        });
        upgradeSlot = this.addSlotToContainer(new Slot(tile.inv, 1, 80, 71){
            @Override
            public boolean isItemValid(ItemStack p_75214_1_) {
                return p_75214_1_==null || p_75214_1_.getItem() == ItemRegistry.tier1upgrade || p_75214_1_.getItem() == ItemRegistry.tier2upgrade;
            }
        });

        for(int i = 0; i < 3; ++i){
            for(int j = 0; j < 9; ++j){
                this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, invX + j * 18, invY + i * 18));
            }
        }
        for(int i = 0; i < 9; ++i){
            this.addSlotToContainer(new Slot(playerInv,i,invX + i * 18, invY + 58));
        }

        setSlots();
    }

    public void setSlots(){
        for(int i=0;i<inventorySlots.size();i++){
            if(inventorySlots.get(i) instanceof UpgradeSlot){
                inventorySlots.remove(i);
                i--;
            }
        }
        if(vajraSlot.getStack()!=null) {
            inv = new InventoryBasic("toolstationinventory", false,8);

            if(ItemVajra.hasUpgrade(vajraSlot.getStack(), EnumUpgradeType.AUTOMELTING)){
                inv.setInventorySlotContents(0, new ItemStack(ItemRegistry.upgrade, 1, 0));
            }
            if(ItemVajra.hasUpgrade(vajraSlot.getStack(), EnumUpgradeType.SILKTOUCH)){
                inv.setInventorySlotContents(1, new ItemStack(ItemRegistry.upgrade, 1, 1));
            }

            int slotNum = 2;
            for(int upgrNum = 2; upgrNum<EnumUpgradeType.values().length;upgrNum++){
                if(ItemVajra.hasUpgrade(vajraSlot.getStack(), EnumUpgradeType.values()[upgrNum])){
                    inv.setInventorySlotContents(slotNum, new ItemStack(ItemRegistry.upgrade, 1, upgrNum));
                    slotNum++;
                }
            }

            this.addSlotToContainer(new UpgradeSlot(inv, 0, 53, 71) {
                @Override
                public boolean isItemValid(ItemStack p_75214_1_) {
                    return super.isItemValid(p_75214_1_) && p_75214_1_.getItemDamage() == EnumUpgradeType.AUTOMELTING.ordinal();
                }
            });
            this.addSlotToContainer(new UpgradeSlot(inv, 1, 107, 71) {
                @Override
                public boolean isItemValid(ItemStack p_75214_1_) {
                    return super.isItemValid(p_75214_1_) && p_75214_1_.getItemDamage() == EnumUpgradeType.SILKTOUCH.ordinal();
                }
            });


            int tier = ((ItemVajra)vajraSlot.getStack().getItem()).getVajraTier();
            if(tier>0) {
                this.addSlotToContainer(new UpgradeSlot(inv, 2, 26, 21));
                this.addSlotToContainer(new UpgradeSlot(inv, 3, 134, 21));
            }
            if(tier>1) {
                this.addSlotToContainer(new UpgradeSlot(inv, 4, 26, 46));
                this.addSlotToContainer(new UpgradeSlot(inv, 5, 134, 46));
            }
            if(tier>2) {
                this.addSlotToContainer(new UpgradeSlot(inv, 6, 26, 71));
                this.addSlotToContainer(new UpgradeSlot(inv, 7, 134, 71));
            }
        }
    }

    public class UpgradeSlot extends Slot{
        public UpgradeSlot(IInventory inv, int id, int x, int y) {
            super(inv, id, x, y);
        }

        @Override
        public boolean isItemValid(ItemStack stack) {
            return stack == null || stack.getItem() instanceof ItemUpgrade;
        }

        @Override
        public void putStack(ItemStack stack) {
            if(inv.getStackInSlot(getSlotIndex())!=null){
                ItemVajra.removeUpgrade(vajraSlot.getStack(), inv.getStackInSlot(getSlotIndex()));
            }
            super.putStack(stack);
            if(stack!=null && stack.getItem()!=null){
                ItemVajra.addUpgrade(vajraSlot.getStack(), stack);
            }
        }


        @Override
        public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
            super.onPickupFromSlot(player, stack);
            if(stack!=null && stack.getItem()!=null){
                ItemVajra.removeUpgrade(vajraSlot.getStack(), stack);
            }
        }
    }

    public void addCraftingToCrafters(ICrafting crafting)
    {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, tile.upgradingProgress);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (lastUpgradeProgress != tile.upgradingProgress)
            {
                icrafting.sendProgressBarUpdate(this, 0, tile.upgradingProgress);
            }
        }

        lastUpgradeProgress = tile.upgradingProgress;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int varNum, int varValue)
    {
        if (varNum == 0)
        {
            tile.upgradingProgress = varValue;
        }
    }

    protected void retrySlotClick(int p_75133_1_, int p_75133_2_, boolean p_75133_3_, EntityPlayer p_75133_4_){

    }
}
