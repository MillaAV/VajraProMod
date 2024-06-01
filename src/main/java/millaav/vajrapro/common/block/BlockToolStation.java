package millaav.vajrapro.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.IC2;
import millaav.vajrapro.VajraPro;
import millaav.vajrapro.common.tile.TileToolStation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import static millaav.vajrapro.ItemVajra.tabVajraPro;

public class BlockToolStation extends Block {
    @SideOnly(Side.CLIENT)
    private IIcon front;
    @SideOnly(Side.CLIENT)
    private  IIcon back;
    @SideOnly(Side.CLIENT)
    private IIcon side;
    @SideOnly(Side.CLIENT)
    private IIcon topBottom;
    public BlockToolStation() {
        super(Material.iron);
        this.setCreativeTab(tabVajraPro);
        this.setBlockName("toolStantion");
        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypeMetal);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
        int meta = MathHelper.floor_double((double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D) % 4;
        world.setBlockMetadataWithNotify(x,y,z, meta, 2);
    }
    private static final int[] sideOrder = {2,5, 3, 4};

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        meta = meta%4;
        if(side == 0) return back;
        if(side == 1) return topBottom;
        if(sideOrder[meta] == side) return front;
        if(sideOrder[(meta+2)%4] == side) return this.side;
        return this.side;
    }
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register){
        this.front = register.registerIcon("vajrapro:toolstantion_front");
        this.back = register.registerIcon("vajrapro:toolstantion_back");
        this.side = register.registerIcon("vajrapro:toolstantion_side");
        this.topBottom = register.registerIcon("vajrapro:toolstantion_topbottom");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float x0ffset, float y0ffset, float z0ffset) {
        if(!world.isRemote){
            player.openGui(VajraPro.instance,0,world,x,y,z);
        }
        return true;
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileToolStation();
    }
}
