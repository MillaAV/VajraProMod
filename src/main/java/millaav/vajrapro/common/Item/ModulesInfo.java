package millaav.vajrapro.common.Item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ModulesInfo {
    public static void addInfoToUpgrade(ItemStack stack, List info) {
        if(stack.getItemDamage()==0) {
            info.add(StatCollector.translateToLocal("info.about.automelting.modules"));
            info.add(StatCollector.translateToLocal("info.about.automelting.modules1"));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==1){
            info.add(StatCollector.translateToLocal("info.about.silktouch.modules"));
            info.add(StatCollector.translateToLocal("info.about.silktouch.modules1"));
            info.add(StatCollector.translateToLocal("info.about.silktouch.modules2"));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==2){
            info.add(StatCollector.translateToLocal("info.about.range.modules"));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==3){
            info.add(StatCollector.translateToLocal("info.about.fortuna.modules"));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==4){
            info.add(StatCollector.translateToLocal("info.about.depths.modules"));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==5){
            info.add(StatCollector.translateToLocal("info.about.damage.modules"));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==6){
            info.add(StatCollector.translateToLocal("info.about.hoe.modules"));
            info.add(StatCollector.translateToLocal("info.about.hoe.modules1"));
            info.add(StatCollector.translateToLocal("info.about.hoe.modules2"));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==7) {
            info.add(StatCollector.translateToLocal("info.about.damage1.modules"));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==8){
            info.add(StatCollector.translateToLocal("info.about.damage2.modules"));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==9){
            info.add(EnumChatFormatting.GRAY + (StatCollector.translateToLocal("info.about.depth1.modules")));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==10){
            info.add(EnumChatFormatting.GRAY + (StatCollector.translateToLocal("info.about.depth2.modules")));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==11){
            info.add(StatCollector.translateToLocal("info.about.fortuna1.modules"));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
        if(stack.getItemDamage()==12){
            info.add(StatCollector.translateToLocal("info.about.fortuna2.modules"));
            info.add(EnumChatFormatting.GREEN + StatCollector.translateToLocal("info.about.modules"));
        }
    }
}
