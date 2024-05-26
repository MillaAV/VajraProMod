package millaav.vajrapro;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import millaav.vajrapro.common.Item.ConfigTest;
import millaav.vajrapro.common.Item.EnumUpgradeType;
import millaav.vajrapro.common.Item.Hoe;
import net.minecraft.block.Block;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.world.BlockEvent;

import java.util.*;

public class ItemVajra extends ItemTool implements IElectricItem {

    public int maxCharge, transferLimit, tier, epo;
    public float effPower;
    int attackDamage = 3;
    public int st = 0;

    public ItemVajra(float p_i45333_1_, ToolMaterial p_i45333_2_, Set p_i45333_3_) {
        super(p_i45333_1_, p_i45333_2_, p_i45333_3_);
    }


    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!player.isSneaking()) return stack;
        if (world.isRemote) return stack;
        NBTTagCompound tag = getOrCreateTag(stack);
        int curRad = tag.getInteger("radius");
        int limit = 0;
        if(getVajraTier()==1){
            limit = 1;
        }else if(getVajraTier()==2){
            limit = 3;
        }else if(getVajraTier()==3) {
            limit = 4;
        }
        curRad = (curRad+1)%limit;
        tag.setInteger("radius",curRad);
        tag.setInteger("charge", getCutEnergy(stack));

        int d = 1+curRad*2;
        player.addChatMessage(new ChatComponentText(String.format("Mode: %dx%d",d,d)));
        return super.onItemRightClick(stack, world, player);
    }

    public static int getCutEnergy(ItemStack stack) {
        NBTTagCompound tag = getOrCreateTag(stack);
        if(ItemVajra.hasUpgrade(stack, EnumUpgradeType.FORTUNA)){
            Map<Integer, Integer> enchantmentMap = new HashMap();
            enchantmentMap.put(Enchantment.fortune.effectId, 3);
            EnchantmentHelper.setEnchantments(enchantmentMap, stack);
        }
        if(ItemVajra.hasUpgrade(stack, EnumUpgradeType.FORTUNA1)){
            Map<Integer, Integer> enchantmentMap = new HashMap();
            enchantmentMap.put(Enchantment.fortune.effectId, 5);
            EnchantmentHelper.setEnchantments(enchantmentMap, stack);
        }
        if(ItemVajra.hasUpgrade(stack, EnumUpgradeType.FORTUNA2)) {
            Map<Integer, Integer> enchantmentMap = new HashMap();
            enchantmentMap.put(Enchantment.fortune.effectId, 10);
            EnchantmentHelper.setEnchantments(enchantmentMap, stack);
        }
        return tag.getInteger("charge");
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
        NBTTagCompound tag = getOrCreateTag(stack);
        tag.setFloat("attackradius",1.0F);
        if(ItemVajra.hasUpgrade(stack,EnumUpgradeType.RANGE)){tag.setFloat("attackradius",5.0F);}
        if(ItemVajra.hasUpgrade(stack,EnumUpgradeType.SILKTOUCH)){tag.setBoolean("silktouch", true);}
        if(ItemVajra.hasUpgrade(stack,EnumUpgradeType.DAMAGE)){attackDamage=21;}
        if(ItemVajra.hasUpgrade(stack,EnumUpgradeType.DAMAGE1)){attackDamage=61;}
        if(ItemVajra.hasUpgrade(stack,EnumUpgradeType.DAMAGE2)){attackDamage=121;}
        if(ItemVajra.hasUpgrade(stack,EnumUpgradeType.DEPTH)){tag.setInteger("depth", 2);}
        if(ItemVajra.hasUpgrade(stack,EnumUpgradeType.DEPTH1)){tag.setInteger("depth", 4);}
        if(ItemVajra.hasUpgrade(stack,EnumUpgradeType.DEPTH2)){tag.setInteger("depth", 6);}
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (ItemVajra.hasUpgrade(stack, EnumUpgradeType.HOE)) {
            player.addChatMessage(new ChatComponentText("Mode: Hoe"));
            Hoe hoe = new Hoe();
            hoe.operationEnergyCost = effPower;
            hoe.onItemUse(stack, player, world, x, y, z, side);
        }
        return false;
    }


    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        NBTTagCompound tag = getOrCreateTag(stack);
        int radius = tag.getInteger("radius");
        int depth = tag.getInteger("depth");
        return getCutEnergy(stack) >= this.epo && radius > 0 ? this.breakAOEBlocks(stack, x, y, z, radius, depth, player) : super.onBlockStartBreak(stack, x, y, z, player);

    }


    public static NBTTagCompound getOrCreateTag(ItemStack stack) {

        if (stack.stackTagCompound == null) {
            stack.stackTagCompound = new NBTTagCompound();
        }
        return stack.stackTagCompound;
    }

//    public Multimap getAttributeModifiers(ItemStack stack) {
//        Multimap<String, AttributeModifier> ret = HashMultimap.create();
//        ret.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Tool modifier", attackDamage, 0));
//        return ret;
//    }


    public Map<Block, Integer> getObliterationList(ItemStack stack) {
        Map<Block, Integer> blockMap = new HashMap<>();
        NBTTagCompound compound = ConfigTest.getCompound(stack);
        if (compound.hasNoTags()) {
            return blockMap;
        } else {
            for (int i = 0; i < 9; ++i) {
                NBTTagCompound tag = new NBTTagCompound();
                if (compound.hasKey("Item" + i)) {
                    tag = compound.getCompoundTag("Item" + i);
                }

                if (!tag.hasNoTags()) {
                    ItemStack stack1 = ItemStack.loadItemStackFromNBT(tag);
                    if (stack1 != null && stack1.getItem() instanceof ItemBlock) {
                        blockMap.put(Block.getBlockFromItem(stack1.getItem()), stack1.getItemDamage());
                    }
                }
            }

            return blockMap;
        }
    }

    public boolean breakAOEBlocks(ItemStack stack, int x, int y, int z, int breakRadius, int breakDepth, EntityPlayer player) {
        Map<Block, Integer> blockMap = ConfigTest.getBoolean(stack, "ToolVoidJunk", false) ? this.getObliterationList(stack) : new HashMap<>();
        Block block = player.worldObj.getBlock(x, y, z);
        int meta = player.worldObj.getBlockMetadata(x, y, z);
        boolean effective = true;


        if (effective) {
            float refStrength = ForgeHooks.blockStrength(block, player, player.worldObj, x, y, z);
            MovingObjectPosition mop = ConfigTest.raytraceFromEntity(player.worldObj, player, 4.5);
            if (mop == null) {
                ConfigTest.updateGhostBlocks(player, player.worldObj);
            } else {
                int sideHit = mop.sideHit;
                int xMax = breakRadius;
                int xMin = breakRadius;
                int yMax = breakRadius;
                int yMin = breakRadius;
                int zMax = breakRadius;
                int zMin = breakRadius;
                int yOffset = 0;
                switch (sideHit) {
                    case 0:
                        yMax = breakDepth;
                        yMin = 0;
                        zMax = breakRadius;
                        break;
                    case 1:
                        yMin = breakDepth;
                        yMax = 0;
                        zMax = breakRadius;
                        break;
                    case 2:
                        xMax = breakRadius;
                        zMin = 0;
                        zMax = breakDepth;
                        yOffset = breakRadius - 1;
                        break;
                    case 3:
                        xMax = breakRadius;
                        zMax = 0;
                        zMin = breakDepth;
                        yOffset = breakRadius - 1;
                        break;
                    case 4:
                        xMax = breakDepth;
                        xMin = 0;
                        zMax = breakRadius;
                        yOffset = breakRadius - 1;
                        break;
                    case 5:
                        xMin = breakDepth;
                        xMax = 0;
                        zMax = breakRadius;
                        yOffset = breakRadius - 1;
                }

                int xPos;
                int yPos;
                int zPos;
                if (ConfigTest.getBoolean(stack, "BaseSafeAOE", false)) {
                    for (xPos = x - xMin; xPos <= x + xMax; ++xPos) {
                        for (yPos = y + yOffset - yMin; yPos <= y + yOffset + yMax; ++yPos) {
                            for (zPos = z - zMin; zPos <= z + zMax; ++zPos) {
                                if (player.worldObj.getTileEntity(xPos, yPos, zPos) != null) {
                                    if (player.worldObj.isRemote) {
                                        player.addChatComponentMessage(new ChatComponentTranslation("msg.de.baseSafeAOW.txt"));
                                    } else {
                                        ((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, player.worldObj));
                                    }

                                    return true;
                                }
                            }
                        }
                    }
                }

                for (xPos = x - xMin; xPos <= x + xMax; ++xPos) {
                    for (yPos = y + yOffset - yMin; yPos <= y + yOffset + yMax; ++yPos) {
                        for (zPos = z - zMin; zPos <= z + zMax; ++zPos)
                            this.breakExtraBlock(stack, player.worldObj, xPos, yPos, zPos, breakRadius * (breakDepth / 2 + 1), player, refStrength, Math.abs(x - xPos) <= 1 && Math.abs(y - yPos) <= 1 && Math.abs(z - zPos) <= 1, (Map) blockMap);
                    }
                }

                List items = player.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(x - xMin, y + yOffset - yMin, z - zMin, x + xMax + 1, y + yOffset + yMax + 1, z + zMax + 1));

                for (Object o : items) {
                    EntityItem item = (EntityItem) o;
                    if (!player.worldObj.isRemote) {
                        item.setLocationAndAngles(player.posX, player.posY, player.posZ, 0.0F, 0.0F);
                        ((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S18PacketEntityTeleport(item));
                        item.delayBeforeCanPickup = 0;
                        if (ConfigTest.rapidlyDespawnMinedItems) {
                            item.lifespan = 100;

                        }
                    }
                }

            }
        }
        return true;
    }

    protected void breakExtraBlock(ItemStack stack, World world, int x, int y, int z, int totalSize, EntityPlayer player, float refStrength, boolean breakSound, Map<Block, Integer> blockMap) {
        if (world.isAirBlock(x, y, z)) return;

        Block block = world.getBlock(x, y, z);
        if (block.getMaterial() instanceof MaterialLiquid || (block.getBlockHardness(world, x, y, x) == -1 && !player.capabilities.isCreativeMode))
            return;

        int meta = world.getBlockMetadata(x, y, z);

        boolean effective = true;

        if (!effective) return;

        float strength = ForgeHooks.blockStrength(block, player, world, x, y, z);

        if (!player.canHarvestBlock(block) || !ForgeHooks.canHarvestBlock(block, player, meta) || refStrength / strength > 10f && !player.capabilities.isCreativeMode)
            return;

        if (!world.isRemote) {
            BlockEvent.BreakEvent event = ForgeHooks.onBlockBreakEvent(world, world.getWorldInfo().getGameType(), (EntityPlayerMP) player, x, y, z);
            if (event.isCanceled()) {
                ((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
                return;
            }
        }
        int scaledPower = (int) (totalSize * (ElectricItem.manager.discharge(stack, (double)this.epo / 10, this.tier, true, false, false)));
        if (player.capabilities.isCreativeMode || (blockMap.containsKey(block) && blockMap.get(block) == meta)) {

            block.onBlockHarvested(world, x, y, z, meta, player);
            if (block.removedByPlayer(world, player, x, y, z, false))
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);

            if (!world.isRemote) {
                ((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
            }
            if ((blockMap.containsKey(block) && blockMap.get(block) == meta))
                ElectricItem.manager.use(stack, scaledPower, player);
            if (breakSound) world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (meta << 12));
            return;
        }

        ElectricItem.manager.use(stack, scaledPower, player);

        if (!world.isRemote) {

            block.onBlockHarvested(world, x, y, z, meta, player);

            if (block.removedByPlayer(world, player, x, y, z, true)) {
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);
                block.harvestBlock(world, player, x, y, z, meta);
                player.addExhaustion(-0.025F);
                if (block.getExpDrop(world, meta, EnchantmentHelper.getFortuneModifier(player)) > 0)
                    player.addExperience(block.getExpDrop(world, meta, EnchantmentHelper.getFortuneModifier(player)));
            }

            EntityPlayerMP mpPlayer = (EntityPlayerMP) player;
            mpPlayer.playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
        } else {
            if (breakSound) world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (meta << 12));
            if (block.removedByPlayer(world, player, x, y, z, true)) {
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);
            }

            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging(2, x, y, z, Minecraft.getMinecraft().objectMouseOver.sideHit));
        }
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        NBTTagCompound tag = getOrCreateTag(stack);
        float radiusa = tag.getFloat("attackradius");
        if (entity.isEntityAlive()) {
            List targets = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, entity.boundingBox.expand(radiusa, radiusa, radiusa));
            int count = 0;
            if (targets.size() > 1) {
                for(int var9 = 0; var9 < targets.size(); ++var9) {
                    Entity ent = (Entity)targets.get(var9);
                    if(ent.isDead) continue;
                    if((ent instanceof EntityTameable)  && ((EntityTameable)ent).func_152113_b().equals(player.getCommandSenderName())) continue;
                    if(!(ent instanceof EntityLivingBase)) continue;
                    if(ent.getEntityId() == entity.getEntityId()) continue;
                    if((ent instanceof EntityPlayer) && ent.getCommandSenderName() == player.getCommandSenderName()) continue;
                    if(!ent.isEntityAlive()) continue;

                    ent.attackEntityFrom(DamageSource.causePlayerDamage(player), attackDamage);
                    ++count;
                }

//                if (count > 0 && !player.worldObj.isRemote) {
//                    player.worldObj.playSoundAtEntity(entity, "thaumcraft:swing", 1.0F, 0.9F + player.worldObj.rand.nextFloat() * 0.2F);
//                }
            }
        }

        return super.onLeftClickEntity(stack, player, entity);
    }

    protected ItemStack createStackedBlock(Block block, int meta) {
        int j = 0;
        Item item = Item.getItemFromBlock(block);
        if (item != null && item.getHasSubtypes()) {
            j = meta;
        }

        return new ItemStack(item, 1, j);
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float a, float b, float c) {
        NBTTagCompound tag = getOrCreateTag(itemstack);
        if (tag.getBoolean("silktouch")) {
            try {
                int metaData = world.getBlockMetadata(i, j, k);
                Block block = world.getBlock(i, j, k);
                if (block != Blocks.bedrock && block != Blocks.mob_spawner && block.canHarvestBlock(entityplayer, metaData) && block.getItemDropped(metaData, world.rand, 1) != null) {
                    boolean var24;
                    if (!ElectricItem.manager.canUse(itemstack, (double)this.epo)) {
                        var24 = false;
                        return var24;
                    }

                    if (VajraPro.isSimulating()) {
                        Boolean dropFlag = false;
                        if (block.canSilkHarvest(world, entityplayer, i, j, k, metaData)) {
                            ArrayList<ItemStack> items = new ArrayList();
                            ItemStack stack = this.createStackedBlock(block, metaData);
                            if (stack != null) {
                                items.add(stack);
                            }

                            ForgeEventFactory.fireBlockHarvesting(items, world, block, i, j, k, metaData, 0, 1.0F, true, entityplayer);
                            Iterator i$ = items.iterator();

                            while(i$.hasNext()) {
                                ItemStack is = (ItemStack)i$.next();
                                VajraPro.dropAsEntity(world, i, j, k, is);
                            }

                            dropFlag = true;
                        } else {
                            int count = block.quantityDropped(metaData, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, itemstack), world.rand);
                            if (count > 0) {
                                int exp = block.getExpDrop(world, metaData, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, itemstack));
                                block.dropXpOnBlockBreak(world, i, j, k, exp);
                                block.harvestBlock(world, entityplayer, i, j, k, metaData);
                                block.onBlockHarvested(world, i, j, k, metaData, entityplayer);
                                float blockHardness = block.getBlockHardness(world, i, j, k);
                                if (blockHardness > 0.0F) {
                                    this.onBlockDestroyed(itemstack, world, block, i, j, k, entityplayer);
                                }

                                world.func_147479_m(i, j, k);
                                dropFlag = true;
                            }
                        }

                        if (dropFlag) {
                            world.setBlockToAir(i, j, k);
                            world.func_147479_m(i, j, k);
                            world.playAuxSFX(2001, i, j, k, Block.getIdFromBlock(block) + (metaData << 12));
                            ElectricItem.manager.use(itemstack, (double)this.epo, entityplayer);
                        }
                    }

                    var24 = true;
                    return var24;
                }
            } catch (Exception var22) {
                VajraPro.addLog("Vajra: Error in destroy function (" + var22.getLocalizedMessage() + ")");
            }
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

    public float getDigSpeed(ItemStack tool, Block block, int meta) {
        if (!ElectricItem.manager.canUse(tool, (double) this.epo / 10)) {
            return 1.0F;
        } else {
            return this.canHarvestBlock(block, tool) ? this.efficiencyOnProperMaterial : 1.0F;
        }
    }

    public boolean onBlockDestroyed(ItemStack itemstack, World world, Block block, int xPos, int yPos, int zPos, EntityLivingBase entityliving) {
        if ((double)block.getBlockHardness(world, xPos, yPos, zPos) != 0.0) {
            if (entityliving != null) {
                ElectricItem.manager.use(itemstack, this.epo, entityliving);
            } else {
                ElectricItem.manager.discharge(itemstack, this.epo, this.tier, true, false, false);
            }
        }

        return true;
    }

    public void addInformation(ItemStack itemStack, EntityPlayer player, List info, boolean b) {
        super.addInformation(itemStack, player, info, b);
        NBTTagCompound tag = getOrCreateTag(itemStack);
        info.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("item.test.r") + " " + EnumChatFormatting.GREEN + (1 + (tag.getInteger("radius") * 2)) +
                "x" + (1 + (tag.getInteger("radius") * 2)));
        if (tag.getBoolean("isHoe")) {
            info.add(EnumChatFormatting.LIGHT_PURPLE + StatCollector.translateToLocal("item.test.hoe") + " " + EnumChatFormatting.GREEN + StatCollector.translateToLocal("item.test.hoe1"));
        } else
            info.add(EnumChatFormatting.LIGHT_PURPLE + StatCollector.translateToLocal("item.test.hoe") + " " + EnumChatFormatting.RED + StatCollector.translateToLocal("item.test.hoe2"));
    }

//    @SideOnly(Side.CLIENT)
//    public void getSubItems(Item item, CreativeTabs var2, List var3) {
//        ItemStack var4 = new ItemStack(this, 1);
//        ElectricItem.manager.charge(var4, 2.147483647E9, Integer.MAX_VALUE, true, false);
//        var3.add(var4);
//        var3.add(new ItemStack(this, 1, this.getMaxDamage()));
//    }

    public int getItemEnchantability() {return 0;}
    @Override
    public int getTier(ItemStack itemStack) {
        return 0;
    }
    @SideOnly(Side.CLIENT)
    @Deprecated
    public  boolean hasEffect(ItemStack p_77636_1_){
        return false;
    }
    public boolean canProvideEnergy(ItemStack itemStack) {
        return false;
    }
    public EnumRarity getRarity(ItemStack var1) {
        return EnumRarity.epic;
    }
    public double getMaxCharge(ItemStack itemStack) {
        return this.maxCharge;
    }
    public double getTransferLimit(ItemStack itemStack) {
        return this.transferLimit;
    }
    public boolean canHarvestBlock(Block block, ItemStack stack) {
        return block != Blocks.bedrock;
    }
    public boolean isRepairable() {
        return false;
    }
    public int getHarvestLevel(ItemStack stack, String toolClass) {
        return this.toolMaterial.getHarvestLevel();
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
