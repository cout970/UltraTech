package common.cout970.UltraTech.blocks.decoration;

import java.util.Random;

import common.cout970.UltraTech.client.textures.Block_ConectedTextures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.proxy.ClientProxy;
import common.cout970.UltraTech.util.render.ConectedTexture;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockObalti extends Block{

	private IIcon[] i;

	public BlockObalti(Material p_i45394_1_) {
		super(p_i45394_1_);
		setCreativeTab(UT_Tabs.DecoTab);
		setHardness(2f);
		setStepSound(soundTypeMetal);
		setResistance(5000);
		setBlockName("AlienBlock");
		}

	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess BA, int x, int y, int z, int side)
    {
    	return i[ConectedTexture.getConectedTexturesIcon(BA,x,y,z,side)];
    }
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		i = new IIcon[21];
		i[0] = iconRegister.registerIcon(Block_ConectedTextures.ALIEN_PREFIX+"base");
		for(int u = 1;u< 21;u++){
		i[u] = iconRegister.registerIcon(Block_ConectedTextures.ALIEN_PREFIX+Block_ConectedTextures.BASE[u]);
		}
		this.blockIcon = i[0];
	}
}
