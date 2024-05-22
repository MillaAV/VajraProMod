package millaav.vajrapro.common.Item;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConfigTest {
    private static final String CATEGORY_BALANCE = "balance";
    public static boolean useNewToolsStats = false;

    public ConfigTest() {
    }

    public static void load() {
        try {
            Configuration cfg = new Configuration(new File(Loader.instance().getConfigDir(), "Events/DraconicEvolution.cfg"));
            useNewToolsStats = cfg.getBoolean("useNewToolsStats", "balance", useNewToolsStats, "[Pa4ok] Использовать новые параметры для настройки инструментов (3x3x1, 5x5x1, 9x9x2)");
            cfg.save();
        } catch (Throwable var1) {
            System.err.println("Failed load config. Use default values.");
            var1.printStackTrace();
        }

    }


    static {
        load();
    }

    public static int getInteger(ItemStack stack) {
        NBTTagCompound tag = getCompound(stack);
        return tag.getInteger("radius");
    }
    public static NBTTagCompound getCompound(ItemStack stack) {
        if(stack.stackTagCompound == null){
            stack.stackTagCompound = new NBTTagCompound();
        }
        return stack.stackTagCompound;
    }


    public static NBTTagCompound getProfileCompound(ItemStack stack) {
        int profile = ConfigTest.getInteger(stack);
        NBTTagCompound stackCompound = ConfigTest.getCompound(stack);
        if (!stackCompound.hasKey("ConfigProfiles") && stackCompound.getTagList("ConfigProfiles", 10).tagCount() < 5) {
            NBTTagList profileList = new NBTTagList();

            for(int i = 0; i < 5; ++i) {
                profileList.appendTag(new NBTTagCompound());
            }

            stackCompound.setTag("ConfigProfiles", profileList);
        }

        return stackCompound.getTagList("ConfigProfiles", 10).getCompoundTagAt(profile);
    }

    public static MovingObjectPosition raytraceFromEntity(World world, Entity player, double range) {
        float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f;
        if (!world.isRemote && player instanceof EntityPlayer) {
            ++d1;
        }

        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = range;
        if (player instanceof EntityPlayerMP && range < 10.0) {
            d3 = ((EntityPlayerMP)player).theItemInWorldManager.getBlockReachDistance();
        }

        Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        return world.rayTraceBlocks(vec3, vec31);
    }

    public static void updateGhostBlocks(EntityPlayer player, World world) {
        if (!world.isRemote) {
            int xPos = (int)player.posX;
            int yPos = (int)player.posY;
            int zPos = (int)player.posZ;

            for(int x = xPos - 6; x < xPos + 6; ++x) {
                for(int y = yPos - 6; y < yPos + 6; ++y) {
                    for(int z = zPos - 6; z < zPos + 6; ++z) {
                        ((EntityPlayerMP)player).playerNetServerHandler.sendPacket(new S23PacketBlockChange(x, y, z, world));
                    }
                }
            }

        }
    }
    public static Configuration config;
    public static boolean rapidlyDespawnMinedItems;
    public static boolean disableLog;
    public static void log(Level logLevel, Object object) {
        if (!ConfigTest.disableLog) {
            FMLLog.log("Draconic Evolution", logLevel, String.valueOf(object), new Object[0]);
        }
    }
    public static void error(Object object) {
        log(Level.ERROR, object);
    }
    public static void syncConfig() {
        try {
            rapidlyDespawnMinedItems = config.get("general", "Rapidly despawn aoe mined items", false, "If true items dropped by a tool in aoe mode will despawn after 5 seconds").getBoolean(false);
        } catch (Exception var7) {
            ConfigTest.error("Unable to load Config");
            var7.printStackTrace();
        } finally {
            if (config.hasChanged()) {
                config.save();
            }

        }

    }
    public static boolean getBoolean(ItemStack stack, String tag, boolean defaultExpected) {
        return getProfileCompound(stack).hasKey(tag) ? getProfileCompound(stack).getBoolean(tag) : defaultExpected;
    }


}
