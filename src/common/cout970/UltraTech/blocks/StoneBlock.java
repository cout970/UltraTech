package common.cout970.UltraTech.blocks;

import common.cout970.UltraTech.core.UltraTech;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class StoneBlock extends Block{

	public StoneBlock(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.DecoTab);
		setHardness(1.0f);
		setStepSound(soundStoneFootstep);
		setResistance(20);
		setUnlocalizedName("StoneBlock");
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:deco/stoneblock");
	}
}
