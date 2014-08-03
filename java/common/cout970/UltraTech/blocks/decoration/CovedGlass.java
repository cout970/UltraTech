package common.cout970.UltraTech.blocks.decoration;

import java.util.Random;

import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.util.render.RenderUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class CovedGlass extends BlockBreakable{

	public IIcon[] i;
	
	public CovedGlass(Material par3Material,
			boolean par4) {
		super("CovedGlass", par3Material, par4);
		setCreativeTab(UT_Tabs.DecoTab);
		setHardness(0.2f);
		setStepSound(soundTypeGlass);
		setResistance(200);
		setBlockName("CovedGlass");		
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		String d = "coved/base";
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
		i[20] = iconRegister.registerIcon("ultratech:"+d+20);
		this.blockIcon = i[0];
	}
	
	/**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 0;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess BA, int x, int y, int z, int side)
    {
    	return i[RenderUtil.getConectedTexturesIcon(BA,x,y,z,side)];
    }
}
