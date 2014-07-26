package common.cout970.UltraTech.blocks.common;

import common.cout970.UltraTech.TileEntities.electric.ChargeStationEntity;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.managers.UltraTech;
import common.cout970.UltraTech.util.power.BlockConductor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ChargeStation extends BlockConductor{

	public ChargeStation(Material m) {
		super(m);
		setCreativeTab(UT_Tabs.techTab);
		setHardness(2f);
		setStepSound(soundTypeMetal);
		setBlockName("ChargeStation");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new ChargeStationEntity();
	}

	public void registerBlockIcons(IIconRegister IR){
		blockIcon = IR.registerIcon(Block_Textures.CHARGE_STATION);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int a, float b, float c, float d){
		if(!p.isSneaking())p.openGui(UltraTech.instance, 1, world, x, y, z);
		return true;
	}
}
