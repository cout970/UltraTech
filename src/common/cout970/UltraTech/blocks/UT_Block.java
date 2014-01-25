package common.cout970.UltraTech.blocks;

import java.util.List;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class UT_Block extends Block{

	public String texture;
	public boolean black = true;
	public static Icon blockIconIn;
	
	public UT_Block(int par1, String name,boolean black) {
		super(par1, Material.rock);
		this.black = black;
		setCreativeTab(UltraTech.DecoTab);
		setHardness(0.4f);
		setStepSound(soundStoneFootstep);
		setResistance(30);
		setUnlocalizedName(name);
		texture = name.toLowerCase();
	}
	
	@SuppressWarnings("static-access")
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:deco_"+((!black) ? "white" : "black")+"/"+texture);
		this.blockIconIn = iconRegister.registerIcon("ultratech:deco_white/base");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2)
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
	
	//meta
	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}
	
	@SuppressWarnings("unchecked")
	public void getSubBlocks(int unknown, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems){
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
