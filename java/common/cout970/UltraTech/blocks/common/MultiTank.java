package common.cout970.UltraTech.blocks.common;

import java.util.Random;

import common.cout970.UltraTech.client.textures.Block_ConectedTextures;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.util.render.ConectedTexture;
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
    	return i[ConectedTexture.getConectedTexturesIcon(BA,x,y,z,side)];
    }
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		i = new IIcon[21];
		i[0] = iconRegister.registerIcon(Block_ConectedTextures.TANK_PREFIX+"base");
		i[1] = iconRegister.registerIcon(Block_Textures.VOID);
		for(int u = 2;u< 21;u++){
		i[u] = iconRegister.registerIcon(Block_ConectedTextures.TANK_PREFIX+Block_ConectedTextures.BASE[u]);
		}
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
