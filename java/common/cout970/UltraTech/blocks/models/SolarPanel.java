package common.cout970.UltraTech.blocks.models;

import api.cout970.UltraTech.Wpower.BlockConductor;
import api.cout970.UltraTech.Wpower.Machine;
import common.cout970.UltraTech.TileEntities.electric.SolarPanelEntity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SolarPanel extends BlockConductor{

	public SolarPanel(Material par2Material) {
		super(par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundTypeMetal);
		setResistance(20);
		setBlockName("SolarPanel");
		setBlockBounds(0f, 0f, 0f, 1f, 0.250f, 1f);
	}

	@Override
	public TileEntity createNewTileEntity(World world,int a) {
		return new SolarPanelEntity();	
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		Machine m = (Machine) w.getTileEntity(x, y, z);
		if(m.getNetwork() != null)m.getNetwork().refresh();
	}

	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:solarpanel");
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderType() {
		return ClientProxy.solarRenderPass;
	}
}
