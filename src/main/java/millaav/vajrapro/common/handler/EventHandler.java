package millaav.vajrapro.common.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import millaav.vajrapro.ItemVajra;
import millaav.vajrapro.VajraPro;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.event.world.BlockEvent;

import java.util.ArrayList;

public class EventHandler {
    @SubscribeEvent
    public void onBlockDropsEvent(BlockEvent.HarvestDropsEvent event){
        if(event.harvester==null) return;
        if(event.harvester.getHeldItem() == null) return;
        if(!(event.harvester.getHeldItem().getItem() instanceof ItemVajra)) return;
        ArrayList<ItemStack> newDrops = new ArrayList<>();
        for(ItemStack stack : event.drops){
            ItemStack smeltingRes = FurnaceRecipes.smelting().getSmeltingResult(stack);

            if(smeltingRes!=null){
                newDrops.add(smeltingRes.copy());
            }else {
                newDrops.add(stack);
            }
        }
        event.drops.clear();
        event.drops.addAll(newDrops);
    }
}
