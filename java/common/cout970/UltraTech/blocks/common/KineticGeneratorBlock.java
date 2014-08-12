package common.cout970.UltraTech.blocks.common;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.intermod.KineticGeneratorEntity;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.util.power.BlockConductor;

public class KineticGeneratorBlock extends BlockConductor{

	public KineticGeneratorBlock(Material m) {
		super(m);
		setCreativeTab(UT_Tabs.techTab);
		setStepSound(soundTypeMetal);
		setResistance(50);
		setHardness(2.0f);
		setBlockName("KineticGenerator");
		setBlockTextureName(Block_Textures.KINETIC_GENERATOR);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new KineticGeneratorEntity();
	}

}
