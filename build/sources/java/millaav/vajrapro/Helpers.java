package millaav.vajrapro;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Helpers {

    public static Integer readToolMode(ItemStack itemstack) {
        NBTTagCompound nbttagcompound = VajraPro.getOrCreateNbtData(itemstack);
        Integer toolMode = nbttagcompound.getInteger("toolMode");
        if (toolMode <= 0 || toolMode > 4) {
            toolMode = 1;
        }

        return toolMode;
    }


    public static void setToolName(ItemStack itemStack) {
        Integer toolMode = readToolMode(itemStack);
        if (toolMode == 1) {
            itemStack.setStackDisplayName(VajraPro.formatMessage("item.vajrapro.name") + " (" + VajraPro.formatMessage("vajrapro.snap.Hoe") + ")");
        }

        if (toolMode == 2) {
            itemStack.setStackDisplayName(VajraPro.formatMessage("item.vajrapro.name") + " (" + VajraPro.formatMessage("vajrapro.snap.TreeTap") + ")");
        }

        if (toolMode == 3) {
            itemStack.setStackDisplayName(VajraPro.formatMessage("item.vajrapro.name") + " (" + VajraPro.formatMessage("vajrapro.snap.Wrench") + ")");
        }

        if (toolMode == 4) {
            itemStack.setStackDisplayName(VajraPro.formatMessage("item.vajrapro.name") + " (" + VajraPro.formatMessage("vajrapro.snap.Screwdriver") + ")");
        }

    }
}
