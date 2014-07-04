package common.cout970.UltraTech.blocks.common;

import common.cout970.UltraTech.TileEntities.electric.tiers.ChemicalPlant_Entity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import api.cout970.UltraTech.MeVpower.BlockConductor;

public class ChemicalPlant extends BlockConductor {

	private IIcon up;

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
		return new ChemicalPlant_Entity();
	}
	
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:chemical");
		up = iconRegister.registerIcon("ultratech:chasis1");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(side == 1 || side == 0)return up;
		return blockIcon;
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, Block block){
		IUpdatedEntity t = (IUpdatedEntity) w.getTileEntity(x, y, z);
		t.onNeigUpdate();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int a, float b, float c, float d){
		if(!p.isSneaking())p.openGui(UltraTech.instance, 1, world, x, y, z);
		return true;
	}
}
