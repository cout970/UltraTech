package common.cout970.UltraTech.blocks.models;

import common.cout970.UltraTech.TileEntities.electric.SolarPanelEntity;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.proxy.ClientProxy;
import common.cout970.UltraTech.util.power.BlockConductor;
import common.cout970.UltraTech.util.power.Machine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class SolarPanel extends BlockConductor{

	private IIcon blockIcon1;
	private IIcon blockIcon2;

	public SolarPanel(Material par2Material) {
		super(par2Material);
		setCreativeTab(UT_Tabs.techTab);
		setHardness(1.5f);
		setStepSound(soundTypeMetal);
		setResistance(20);
		setBlockName("SolarPanel");
		setBlockBounds(0f, 0f, 0f, 1f, 0.250f, 1f);
		setLightOpacity(0);
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
		this.blockIcon = iconRegister.registerIcon(Block_Textures.SOLAR_PANEL_UP);
		this.blockIcon1 = iconRegister.registerIcon(Block_Textures.SOLAR_PANEL_DOWN);
		this.blockIcon2 = iconRegister.registerIcon(Block_Textures.SOLAR_PANEL_SIDE);
	}
	
	public IIcon getIcon(int side, int meta){
		if(side == 0)	return blockIcon1;
		if(side == 1)	return blockIcon;
		else 			return blockIcon2;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}

}
