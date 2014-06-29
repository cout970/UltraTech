package common.cout970.UltraTech.blocks.common;

import common.cout970.UltraTech.TileEntities.electric.ChemicalPlantEntity;
import common.cout970.UltraTech.core.UltraTech;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import api.cout970.UltraTech.MeVpower.BlockConductor;

public class ChemicalPlant extends BlockConductor {

	public ChemicalPlant(Material m) {
		super(m);
		setCreativeTab(UltraTech.techTab);
		setHardness(2.5f);
		setStepSound(soundTypeMetal);
		setResistance(20);
		setBlockName("ChemicalPlant");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new ChemicalPlantEntity();
	}
	
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:chemical");
	}

}
