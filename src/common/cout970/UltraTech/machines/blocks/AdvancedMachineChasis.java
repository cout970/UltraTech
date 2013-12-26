package common.cout970.UltraTech.machines.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

import common.cout970.UltraTech.core.UltraTech;

public class AdvancedMachineChasis extends Block{

	public AdvancedMachineChasis(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(5000);
		setUnlocalizedName("AdvMachineChasis");
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:advmachinechasis");
	}
}