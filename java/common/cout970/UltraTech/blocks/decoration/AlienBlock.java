package common.cout970.UltraTech.blocks.decoration;

import java.util.Random;

import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.proxy.ClientProxy;
import common.cout970.UltraTech.util.render.RenderUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class AlienBlock extends Block{

	private IIcon[] i;

	public AlienBlock(Material p_i45394_1_) {
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
    	return i[RenderUtil.getConectedTexturesIcon(BA,x,y,z,side)];
    }
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		String d = "alien/base";
		i = new IIcon[21];
		i[0] = iconRegister.registerIcon("ultratech:"+d);
		i[1] = iconRegister.registerIcon("ultratech:"+d+"0");
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
}
