package common.cout970.UltraTech.blocks.models;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import common.cout970.UltraTech.TileEntities.intermod.KineticGeneratorEntity;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.util.power.BlockConductor;

public class KineticGenerator extends BlockConductor{

	public KineticGenerator(Material m) {
		super(m);
		setCreativeTab(UT_Tabs.techTab);
		setHardness(2.5f);
		setStepSound(soundTypeMetal);
		setResistance(20);
		setBlockName("KineticGenerator");
		setBlockTextureName(Block_Textures.KINETIC_GENERATOR);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new KineticGeneratorEntity();
	}

}
