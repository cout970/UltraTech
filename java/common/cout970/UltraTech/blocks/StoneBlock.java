package common.cout970.UltraTech.blocks;

import java.util.List;

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

public class StoneBlock extends Block{

	private IIcon blockIcon1;
	private IIcon blockIcon2;
	private IIcon blockIcon3;

	public StoneBlock(Material par2Material) {
		super(par2Material);
		setCreativeTab(UT_Tabs.DecoTab);
		setHardness(1.0f);
		setStepSound(soundTypeStone);
		setResistance(30);
		setBlockName("StoneBlock");
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:deco_black/stoneblock");
		this.blockIcon1 = iconRegister.registerIcon("ultratech:deco_black/stoneblockbrick");
		this.blockIcon2 = iconRegister.registerIcon("ultratech:deco_white/stoneblock");
		this.blockIcon3 = iconRegister.registerIcon("ultratech:deco_white/stoneblockbrick");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
    {		
        switch(par2){
        case 0:return this.blockIcon;
        case 1:return this.blockIcon1;
        case 2:return this.blockIcon2;
        case 3:return this.blockIcon3;
        default:return this.blockIcon;
        }
    }
	
	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems)
    {
		for (int ix = 0; ix < 4; ix++) {
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
