package common.cout970.UltraTech.blocks.decoration;

import java.util.List;

import common.cout970.UltraTech.blocks.tiers.BlocksBattery;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.ConfigManager;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlowstone;
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

public class BlockDeco extends Block{

	public static IIcon Base;
	public boolean black = true;
	public int number;
	
	public BlockDeco(int number,boolean black) {
		super(Material.tnt);
		this.black = black;
		setCreativeTab(UT_Tabs.DecoTab);
		setHardness(0.4f);
		setStepSound(soundTypeStone);
		setResistance(30);
		this.number = number;
		setBlockName("Deco_"+number);
		setLightLevel(ConfigManager.DECO_LIGHT);
	}
	
	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
    	return false;
    }
	
	public void registerBlockIcons(IIconRegister iconRegister){
		Base = iconRegister.registerIcon(Block_Textures.DECO_BASE);
		if(black){
			this.blockIcon = iconRegister.registerIcon(Block_Textures.DECO_PREFIX_BLACK+number);
		}else{
			this.blockIcon = iconRegister.registerIcon(Block_Textures.DECO_PREFIX_WHITE+number);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2)
    {		
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

	public boolean isOpaqueCube()
	{
		return true;
	}

	public boolean renderAsNormalBlock()
	{
		return true;
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
		return pass == getRenderBlockPass();
	}
	
	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}
}
