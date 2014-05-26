package common.cout970.UltraTech.blocks;

import java.util.List;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockOre;
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

public class OreBlock extends Block{

	private IIcon blockIcon1;
	private IIcon blockIcon2;
	private IIcon blockIcon3;
	private IIcon blockIcon4;
	private IIcon blockIcon5;

	public OreBlock(Material par2Material) {
		super(par2Material);
		setCreativeTab(UltraTech.ResourceTab);
		setHardness(1.0f);
		setStepSound(soundTypeStone);
		setResistance(30);
		setBlockName("OreBlock");
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:metal/radionite");
		this.blockIcon1 = iconRegister.registerIcon("ultratech:metal/aluminum");
		this.blockIcon2 = iconRegister.registerIcon("ultratech:metal/copper");
		this.blockIcon3 = iconRegister.registerIcon("ultratech:metal/tin");
		this.blockIcon4 = iconRegister.registerIcon("ultratech:metal/lead");
		this.blockIcon5 = iconRegister.registerIcon("ultratech:metal/silver");
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
        case 4:return this.blockIcon4;
        case 5:return this.blockIcon5;
        default:return this.blockIcon;
        }
    }
	
	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems)
    {
		for (int ix = 0; ix < UT_ItemBlockOre.subNames.length; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
	
	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}
	
	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}
}
