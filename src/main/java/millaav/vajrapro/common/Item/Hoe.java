package millaav.vajrapro.common.Item;

import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class Hoe {
    public double operationEnergyCost;

    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side) {
        if (!entityPlayer.canPlayerEdit(x, y, z, side, itemStack)) {
            return false;
        } else if (!ElectricItem.manager.canUse(itemStack, this.operationEnergyCost)) {
            return false;
        } else if (MinecraftForge.EVENT_BUS.post(new UseHoeEvent(entityPlayer, itemStack, world, x, y, z))) {
            return false;
        } else {
            Block block = world.getBlock(x, y, z);
            if (!(side == 0 || !world.isAirBlock(x, y + 1, z) || block != Blocks.mycelium && block != Blocks.grass && block != Blocks.dirt)) {
                block = Blocks.farmland;
                world.playSoundEffect((double) x + 0.5, (double) y + 0.5, (double) z + 0.5, block.stepSound.getStepResourcePath(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                if (IC2.platform.isSimulating()) {
                    world.setBlock(x, y, z, block, 0, 3);
                    ElectricItem.manager.use(itemStack, this.operationEnergyCost, entityPlayer);
                }
            }
            return true;
        }
    }
}
