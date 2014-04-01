package common.cout970.UltraTech.blocks.models;

import common.cout970.UltraTech.TileEntities.Tier1.BoilerEntity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.energy.api.EnergyUtils;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.proxy.ClientProxy;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Boiler extends BlockContainer{

	public Boiler(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(20);
		setUnlocalizedName("Boiler");
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9)
	{
		if(p.isSneaking())return true;
		TileEntity e = w.getBlockTileEntity(x, y, z);
		if(e != null){
			p.openGui(UltraTech.instance, 13, w, x, y, z);
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new BoilerEntity();
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		Machine m = (Machine) w.getBlockTileEntity(x, y, z);
		if(m.getNetwork() != null)m.getNetwork().refresh();
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:boiler");
	}
	
	public void onBlockPreDestroy(World w, int x, int y, int z, int meta) {
		EnergyUtils.onBlockPreDestroy(w, x, y, z, meta);
	}
	
	@Override
	public void onBlockAdded(World par1World, int xCoord, int yCoord, int zCoord)
	{
		EnergyUtils.onBlockAdded( par1World, xCoord, yCoord, zCoord);
		super.onBlockAdded(par1World, xCoord, yCoord, zCoord);
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType() {
		return ClientProxy.boilerRenderPass;
	}

}
