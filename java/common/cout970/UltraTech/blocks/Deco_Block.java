package common.cout970.UltraTech.blocks;

import java.util.List;

import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Deco_Block extends Block{

	public static IIcon Base;
	public boolean black = true;
	public IIcon blockIconIn;
	public int number;
	
	public Deco_Block(int number,boolean black) {
		super(new Material(MapColor.stoneColor));
		this.black = black;
		setCreativeTab(UT_Tabs.DecoTab);
		setHardness(0.4f);
		setStepSound(soundTypeStone);
		setResistance(30);
		this.number = number;
		setBlockName("Deco_"+number);
	}
	
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
    	return false;
    }
	
	public void registerBlockIcons(IIconRegister iconRegister){
		Base = iconRegister.registerIcon(Block_Textures.DECO_BASE);
		this.blockIcon = iconRegister.registerIcon(Block_Textures.DECO_PREFIX+((!black) ? "white" : "black")+"/deco"+number);
		this.blockIconIn = iconRegister.registerIcon(Block_Textures.DECO_PREFIX+"white/deco_in_"+number);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
    {		
		if(par1 == -1)return this.blockIconIn;
        return this.blockIcon;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType()
	{
		return ClientProxy.BlockRenderTipe;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderBlockPass()
	{
		return 0;
	}
	
	//meta
	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}
	
	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems)
    {
		for (int ix = 0; ix < 14; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
	
	@Override
	public boolean canRenderInPass(int pass)
	{
		ClientProxy.renderPass = pass;
		return true;
	}
	
	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}
}
