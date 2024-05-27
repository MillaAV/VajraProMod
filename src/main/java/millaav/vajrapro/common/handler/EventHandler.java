package millaav.vajrapro.common.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import millaav.vajrapro.ItemVajra;

import millaav.vajrapro.common.Item.EnumUpgradeType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.world.BlockEvent;

import java.util.ArrayList;

public class EventHandler {
    @SubscribeEvent
    public void onBlockDropsEvent(BlockEvent.HarvestDropsEvent event) {
        if (event.harvester == null) return;
        ItemStack heldStack = event.harvester.getHeldItem();
        if (heldStack == null) return;
        if (!(heldStack.getItem() instanceof ItemVajra)) return;
        if(!ItemVajra.hasUpgrade(heldStack, EnumUpgradeType.AUTOMELTING)) return;
        ArrayList<ItemStack> newDrops = new ArrayList<>();
        for (ItemStack stack : event.drops) {
            ItemStack smeltingRes = FurnaceRecipes.smelting().getSmeltingResult(stack);

            if (smeltingRes != null){
                int fortuneLevel = 0;
                if(ItemVajra.hasUpgrade(stack,EnumUpgradeType.FORTUNA)){fortuneLevel=3;}
                if(ItemVajra.hasUpgrade(stack,EnumUpgradeType.FORTUNA1)){fortuneLevel=5;}
                if(ItemVajra.hasUpgrade(stack,EnumUpgradeType.FORTUNA2)){fortuneLevel=10;}
                ItemStack smeltingResCopied = smeltingRes.copy();
                smeltingResCopied.stackSize = event.harvester.worldObj.rand.nextInt(fortuneLevel+1);
                newDrops.add(smeltingResCopied);
            } else {
                newDrops.add(stack);
            }
        }
        event.drops.clear();
        event.drops.addAll(newDrops);
    }
}
