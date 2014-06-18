package common.cout970.UltraTech.blocks.models;

import common.cout970.UltraTech.TileEntities.electric.LavaGeneratorEntity;
import common.cout970.UltraTech.core.UltraTech;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import api.cout970.UltraTech.Wpower.BlockConductor;

public class LavaGenerator extends BlockConductor{

	public LavaGenerator(Material m) {
		super(m);
		setCreativeTab(UltraTech.techTab);
		setStepSound(soundTypeMetal);
		setResistance(50);
		setHardness(2.5f);
		setBlockName("LavaGenerator");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new LavaGeneratorEntity();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister IR){
		blockIcon = IR.registerIcon("ultratech:lavagen");
	}

}
