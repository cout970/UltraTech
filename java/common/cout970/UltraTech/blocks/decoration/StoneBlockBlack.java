package common.cout970.UltraTech.blocks.decoration;

import java.util.List;

import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class StoneBlockBlack extends Block{

	public IIcon[] icons;

	public StoneBlockBlack(Material par2Material) {
		super(par2Material);
		setCreativeTab(UT_Tabs.DecoTab);
		setHardness(1.0f);
		setStepSound(soundTypeStone);
		setResistance(30);
		setBlockName("StoneBlockBlack");
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		icons = new IIcon[11];
		String type = "black";
		icons[0] = iconRegister.registerIcon(Block_Textures.DECO_PREFIX_DECO+type+Block_Textures.STONEBLOCK);
		icons[1] = iconRegister.registerIcon(Block_Textures.DECO_PREFIX_DECO+type+Block_Textures.STONEBLOCK_BRICK);
		icons[2] = iconRegister.registerIcon(Block_Textures.DECO_PREFIX_DECO+type+Block_Textures.STONEBLOCK_CARVER);
		icons[3] = iconRegister.registerIcon(Block_Textures.DECO_PREFIX_DECO+type+Block_Textures.STONEBLOCK_CHISELED);
		icons[4] = iconRegister.registerIcon(Block_Textures.DECO_PREFIX_DECO+type+Block_Textures.STONEBLOCK_CIRCLE);
		icons[5] = iconRegister.registerIcon(Block_Textures.DECO_PREFIX_DECO+type+Block_Textures.STONEBLOCK_CLAYBRICK);
		icons[6] = iconRegister.registerIcon(Block_Textures.DECO_PREFIX_DECO+type+Block_Textures.STONEBLOCK_PILAR);
		icons[7] = iconRegister.registerIcon(Block_Textures.DECO_PREFIX_DECO+type+Block_Textures.STONEBLOCK_SANDSTONE);
		icons[8] = iconRegister.registerIcon(Block_Textures.DECO_PREFIX_DECO+type+Block_Textures.STONEBLOCK_SMOOTH);
		icons[9] = iconRegister.registerIcon(Block_Textures.DECO_PREFIX_DECO+type+Block_Textures.STONEBLOCK_PILAR_TOP);
		icons[10] = iconRegister.registerIcon(Block_Textures.DECO_PREFIX_DECO+type+Block_Textures.STONEBLOCK_CHISELED_TOP);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int m)
    {		
		if(m == 8 || m == 7 || m == 2){
			if(side == 0 || side == 1)return icons[0];
		}
		if(m == 6){
			if(side == 0 || side == 1)return icons[9];
		}
		if(m == 3){
			if(side == 0 || side == 1)return icons[10];
		}
		return icons[m];
    }
	
	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems)
    {
		for (int ix = 0; ix < 9; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
	
	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}
	
	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}
}
