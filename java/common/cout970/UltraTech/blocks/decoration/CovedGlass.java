package common.cout970.UltraTech.blocks.decoration;

import java.util.Random;

import common.cout970.UltraTech.client.textures.Block_ConectedTextures;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.util.render.ConectedTexture;
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
		i = new IIcon[21];
		i[0] = iconRegister.registerIcon(Block_ConectedTextures.COVED_PREFIX+"base");
		i[1] = iconRegister.registerIcon(Block_Textures.VOID);
		for(int u = 2;u< 21;u++){
		i[u] = iconRegister.registerIcon(Block_ConectedTextures.COVED_PREFIX+Block_ConectedTextures.BASE[u]);
		}
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
    	return i[ConectedTexture.getConectedTexturesIcon(BA,x,y,z,side)];
    }
}
