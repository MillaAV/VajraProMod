package millaav.vajrapro;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import millaav.vajrapro.common.CommonProxy;
import millaav.vajrapro.common.Item.EnumUpgradeType;
import millaav.vajrapro.common.Item.Hoe;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.*;

public class ItemVajra extends ItemTool implements IElectricItem {

    public int maxCharge, tier, energyPerOperation,transferLimit, toolMode;
    public float effPower;

    public ItemVajra(float p_i45333_1_, ToolMaterial p_i45333_2_, Set p_i45333_3_) {
        super(p_i45333_1_, p_i45333_2_, p_i45333_3_);
    }

    public static Integer readToolMode(ItemStack itemstack) {
        NBTTagCompound nbttagcompound = VajraPro.getOrCreateNbtData(itemstack);
        Integer toolMode = nbttagcompound.getInteger("toolMode");
        if (toolMode < 0 || toolMode > 1) {
            toolMode = 0;
        }

        return toolMode;
    }

    public void saveToolMode(ItemStack itemstack, Integer toolMode) {
        NBTTagCompound nbttagcompound = VajraPro.getOrCreateNbtData(itemstack);
        nbttagcompound.setInteger("toolMode", toolMode);
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if(!player.isSneaking()) return itemStack;
        if(world.isRemote) {
            if (!VajraPro.disableVajraAccurate) {
                Integer toolMode = readToolMode(itemStack);
                toolMode = toolMode + 1;
                if (toolMode > 1) {
                    toolMode = 0;
                }

                this.saveToolMode(itemStack, toolMode);
                if (toolMode == 0) {
                    CommonProxy.sendPlayerMessage(player, EnumChatFormatting.GOLD + VajraPro.formatMessage("message.vajra.silkTouchMode") + ": " + EnumChatFormatting.RED + VajraPro.formatMessage("message.text.disabled"));
                }

                if (toolMode == 1) {
                    CommonProxy.sendPlayerMessage(player, EnumChatFormatting.GOLD + VajraPro.formatMessage("message.vajra.silkTouchMode") + ": " + EnumChatFormatting.GREEN + VajraPro.formatMessage("message.text.enabled"));
                }
            } else {
                CommonProxy.sendPlayerMessage(player, EnumChatFormatting.RED + VajraPro.formatMessage("message.vajra.silkTouchDisabled"));
            }
        }

        return itemStack;
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (ItemVajra.hasUpgrade(stack, EnumUpgradeType.HOE)) {
            Hoe hoe = new Hoe(); //Создаем объект и кладем в переменную
            hoe.operationEnergyCost = effPower;
            hoe.onItemUse(stack, player, world, x, y, z, side);
        }
        return false;
    }

//    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float a, float b, float c) {
//        Integer toolMode = readToolMode(itemstack);
//        if (!VajraPro.disableVajraAccurate && toolMode == 1) {
//            try {
//                int metaData = world.getBlockMetadata(i, j, k);
//                Block block = world.getBlock(i, j, k);
//                if (block != Blocks.bedrock && block != Blocks.mob_spawner && block.canHarvestBlock(entityplayer, metaData) && block.getItemDropped(metaData, world.rand, 1) != null) {
//                    boolean var24;
//                    if (!ElectricItem.manager.canUse(itemstack, (double)this.energyPerOperation)) {
//                        var24 = false;
//                        return var24;
//                    }
//
//                    if (VajraPro.isSimulating()) {
//                        Boolean dropFlag = false;
//                        if (block.canSilkHarvest(world, entityplayer, i, j, k, metaData)) {
//                            ArrayList<ItemStack> items = new ArrayList();
//                            ItemStack stack = this.createStackedBlock(block, metaData);
//                            if (stack != null) {
//                                items.add(stack);
//                            }
//
//                            ForgeEventFactory.fireBlockHarvesting(items, world, block, i, j, k, metaData, 0, 1.0F, true, entityplayer);
//                            Iterator i$ = items.iterator();
//
//                            while(i$.hasNext()) {
//                                ItemStack is = (ItemStack)i$.next();
//                                VajraPro.dropAsEntity(world, i, j, k, is);
//                            }
//
//                            dropFlag = true;
//                        } else {
//                            int count = block.quantityDropped(metaData, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, itemstack), world.rand);
//                            if (count > 0) {
//                                int exp = block.getExpDrop(world, metaData, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, itemstack));
//                                block.dropXpOnBlockBreak(world, i, j, k, exp);
//                                block.harvestBlock(world, entityplayer, i, j, k, metaData);
//                                block.onBlockHarvested(world, i, j, k, metaData, entityplayer);
//                                float blockHardness = block.getBlockHardness(world, i, j, k);
//                                if (blockHardness > 0.0F) {
//                                    this.onBlockDestroyed(itemstack, world, block, i, j, k, entityplayer);
//                                }
//
//                                world.func_147479_m(i, j, k);
//                                dropFlag = true;
//                            }
//                        }
//
//                        if (dropFlag) {
//                            world.setBlockToAir(i, j, k);
//                            world.func_147479_m(i, j, k);
//                            world.playAuxSFX(2001, i, j, k, Block.getIdFromBlock(block) + (metaData << 12));
//                            ElectricItem.manager.use(itemstack, (double)this.energyPerOperation, entityplayer);
//                        }
//                    }
//
//                    var24 = true;
//                    return var24;
//                }
//            } catch (Exception var22) {
//                VajraPro.addLog("Vajra: Error in destroy function (" + var22.getLocalizedMessage() + ")");
//            } finally {
//                ;
//            }
//        }
//
//        return false;
//    }

//    protected ItemStack createStackedBlock(Block block, int meta) {
//        int j = 0;
//        Item item = Item.getItemFromBlock(block);
//        if (item != null && item.getHasSubtypes()) {
//            j = meta;
//        }
//
//        return new ItemStack(item, 1, j);
//    }

    public boolean canProvideEnergy(ItemStack itemStack) {
        return false;
    }

    public double getMaxCharge(ItemStack itemStack) {
        return (double)this.maxCharge;
    }

    public int getTier(ItemStack itemStack) {
        return this.tier;
    }

    public double getTransferLimit(ItemStack itemStack) {
        return (double)this.transferLimit;
    }

    public boolean canHarvestBlock(Block block, ItemStack stack) {
        return block != Blocks.bedrock;
    }

    public int getHarvestLevel(ItemStack stack, String toolClass) {
        return this.toolMaterial.getHarvestLevel();
    }

    public float getDigSpeed(ItemStack tool, Block block, int meta) {
        if (!ElectricItem.manager.canUse(tool, (double)this.energyPerOperation)) {
            return 1.0F;
        } else {
            return this.canHarvestBlock(block, tool) ? this.efficiencyOnProperMaterial : 1.0F;
        }
    }

//    public float getStrVsBlock(ItemStack itemstack, Block par2Block) {
//        return ElectricItem.manager.canUse(itemstack, (double)this.energyPerOperation) ? this.effPower : 0.5F;
//    }

    public boolean onBlockDestroyed(ItemStack itemstack, World world, Block block, int xPos, int yPos, int zPos, EntityLivingBase entityliving) {
        if ((double)block.getBlockHardness(world, xPos, yPos, zPos) != 0.0) {
            if (entityliving != null) {
                ElectricItem.manager.use(itemstack, (double)this.energyPerOperation, entityliving);
            } else {
                ElectricItem.manager.discharge(itemstack, (double)this.energyPerOperation, this.tier, true, false, false);
            }
        }

        return true;
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        Integer toolMode = readToolMode(par1ItemStack);
        if (toolMode == 0) {
            par3List.add(EnumChatFormatting.GOLD + VajraPro.formatMessage("message.vajra.silkTouchMode") + ": " + EnumChatFormatting.RED + VajraPro.formatMessage("message.text.disabled"));
        }

        if (toolMode == 1) {
            par3List.add(EnumChatFormatting.GOLD + VajraPro.formatMessage("message.vajra.silkTouchMode") + ": " + EnumChatFormatting.GREEN + VajraPro.formatMessage("message.text.enabled"));
        }
//        par3List.add(StatCollector.translateToLocal("ic2.item.tooltip.Store") + " " + ElectricItem.manager.getCharge(par1ItemStack) + " EU");
    }

//    @SideOnly(Side.CLIENT)
//    public void getSubItems(Item item, CreativeTabs var2, List var3) {
//        ItemStack var4 = new ItemStack(this, 1);
//        ElectricItem.manager.charge(var4, 2.147483647E9, Integer.MAX_VALUE, true, false);
//        var3.add(var4);
//        var3.add(new ItemStack(this, 1, this.getMaxDamage()));
//    }

    public boolean isRepairable() {
        return false;
    }

    public int getItemEnchantability() {
        return 0;
    }

    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }

    public int getVajraTier(){
        return 0;
    }

    public static void addUpgrade(ItemStack vajra, ItemStack upgrade){
        if(vajra.stackTagCompound==null){
            vajra.stackTagCompound = new NBTTagCompound();
        }
        vajra.stackTagCompound.setBoolean(EnumUpgradeType.values()[upgrade.getItemDamage()].key, true);
    }

    public static boolean hasUpgrade(ItemStack vajra, EnumUpgradeType type){
        if(vajra.stackTagCompound==null) return false;
        return vajra.stackTagCompound.hasKey(type.key);
    }

    public static void removeUpgrade(ItemStack vajra, ItemStack upgrade){
        if(vajra.stackTagCompound==null) return;
        vajra.stackTagCompound.removeTag(EnumUpgradeType.values()[upgrade.getItemDamage()].key);
    }
}
