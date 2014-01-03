package common.cout970.UltraTech.machines.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class hitBox extends Block{

	public hitBox(int par1, Material par2Material) {
		super(par1, par2Material);
		setUnlocalizedName("satelite2");
		setBlockUnbreakable();
	}
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:void");
	}
	public boolean isOpaqueCube()
	{
		return false;
	}


	public boolean renderAsNormalBlock()
	{
		return false;
	}

}
