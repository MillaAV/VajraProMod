package millaav.vajrapro.common.Item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ModulesInfo {
    public static void addInfoToUpgrade(ItemStack stack, List info) {
        if(stack.getItemDamage()==0) {
            info.add("Melts blocks. Compatible with the Fortune module.");
            info.add("Can turn off (Button changes in game settings)");
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==1){
            info.add("Allows to get a block without destroying it");
            info.add("Activated with right mouse button");
            info.add("Not compatible with the \"Hoe\" module");
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==2){
            info.add("Increases attack radius to 5 blocks");
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==3){
            info.add("Increases fortuna to 3");
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==4){
            info.add("Increases digging depth to 3 blocks");
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==5){
            info.add("Increases damage to 21");
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==6){
            info.add("Hoe. It's just hoe...");
            info.add("Activated with right mouse button");
            info.add("Not compatible with the \"Silk Touch\" module");
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==7) {
            info.add("Increases damage to 61");
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==8){
            info.add("Increases damage to 121");
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==9){
            info.add(EnumChatFormatting.GRAY + ("Increases digging depth to 5 blocks"));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==10){
            info.add(EnumChatFormatting.GRAY + ("Increases digging depth to 7 blocks"));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==11){
            info.add("Increases fortuna to 5");
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==12){
            info.add("Increases fortuna to 10");
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
    }
}
