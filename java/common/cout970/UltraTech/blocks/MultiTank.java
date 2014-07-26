package common.cout970.UltraTech.blocks;

import java.util.Random;

import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.util.render.RenderUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MultiTank extends BlockContainer{

	public MultiTank(Material p_i45386_1_) {
		super(p_i45386_1_);
		setCreativeTab(UT_Tabs.techTab);
		setHardness(0.5f);
		setStepSound(soundTypeGlass);
		setResistance(50);
		setBlockName("MultiTank");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return null;
	}

	public IIcon[] i;
	
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess BA, int x, int y, int z, int side)
    {
    	return i[RenderUtil.getConectedTexturesIcon(BA,x,y,z,side)];
    }
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		String d = "tank/base";
		i = new IIcon[21];
		i[0] = iconRegister.registerIcon("ultratech:"+d);
		i[1] = iconRegister.registerIcon("ultratech:void");
		i[2] = iconRegister.registerIcon("ultratech:"+d+2);
		i[3] = iconRegister.registerIcon("ultratech:"+d+3);
		i[4] = iconRegister.registerIcon("ultratech:"+d+4);
		i[5] = iconRegister.registerIcon("ultratech:"+d+5);
		i[6] = iconRegister.registerIcon("ultratech:"+d+6);
		i[7] = iconRegister.registerIcon("ultratech:"+d+7);
		i[8] = iconRegister.registerIcon("ultratech:"+d+8);
		i[9] = iconRegister.registerIcon("ultratech:"+d+9);
		i[10] = iconRegister.registerIcon("ultratech:"+d+10);
		i[11] = iconRegister.registerIcon("ultratech:"+d+11);
		i[12] = iconRegister.registerIcon("ultratech:"+d+12);
		i[13] = iconRegister.registerIcon("ultratech:"+d+13);
		i[14] = iconRegister.registerIcon("ultratech:"+d+14);
		i[15] = iconRegister.registerIcon("ultratech:"+d+15);
		i[16] = iconRegister.registerIcon("ultratech:"+d+16);
		i[17] = iconRegister.registerIcon("ultratech:"+d+17);
		i[18] = iconRegister.registerIcon("ultratech:"+d+18);
		i[19] = iconRegister.registerIcon("ultratech:"+d+19);
		i[20] = iconRegister.registerIcon("ultratech:void");
		this.blockIcon = i[0];
	}
	
	@SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        Block block = p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_);

        return true && block == this ? false : super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
    }
	
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass(){
        return 1;
    }

    public boolean isOpaqueCube(){
        return false;
    }
    public boolean renderAsNormalBlock()
    {
        return false;
    }


}
