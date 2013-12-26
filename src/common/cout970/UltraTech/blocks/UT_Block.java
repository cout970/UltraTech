package common.cout970.UltraTech.blocks;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.proxy.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class UT_Block extends Block{

	public String texture;
	
	public UT_Block(int par1, String name) {
		super(par1, Material.rock);
		setCreativeTab(UltraTech.DecoTab);
		setHardness(0.2f);
		setStepSound(soundStoneFootstep);
		setResistance(30);
		setUnlocalizedName(name);
		texture = name.toLowerCase();

	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:deco/"+texture);
	}

	@Override
	public int getRenderType()
	{
		return ClientProxy.BlockRenderTipe;
	}

	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
    public boolean canRenderInPass(int pass)
{
            ClientProxy.renderPass = pass;
    return true;
}
}
