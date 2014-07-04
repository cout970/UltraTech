package common.cout970.UltraTech.blocks.common;

import common.cout970.UltraTech.TileEntities.electric.ChargeStationEntity;
import common.cout970.UltraTech.core.UltraTech;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import api.cout970.UltraTech.MeVpower.BlockConductor;

public class ChargeStation extends BlockConductor{

	public ChargeStation(Material m) {
		super(m);
		setCreativeTab(UltraTech.techTab);
		setHardness(2f);
		setStepSound(soundTypeMetal);
		setBlockName("ChargeStation");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new ChargeStationEntity();
	}

	public void registerBlockIcons(IIconRegister IR){
		blockIcon = IR.registerIcon("ultratech:chargestation");
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int a, float b, float c, float d){
		if(!p.isSneaking())p.openGui(UltraTech.instance, 1, world, x, y, z);
		return true;
	}
}
