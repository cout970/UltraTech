package common.cout970.UltraTech.machines.blocks;

import common.cout970.UltraTech.core.UltraTech;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class RadioniteOre extends Block{

	public RadioniteOre(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundStoneFootstep);
		setResistance(30);
		setUnlocalizedName("RadioniteOre");	
		}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:radioniteore");
	}
}
