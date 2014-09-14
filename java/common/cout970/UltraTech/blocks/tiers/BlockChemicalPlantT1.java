package common.cout970.UltraTech.blocks.tiers;

import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityChemicalPlantT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityChemicalPlantT2;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.managers.UltraTech;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.util.power.BlockConductor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockChemicalPlantT1 extends BlockConductor {

	private IIcon up;

	public BlockChemicalPlantT1(Material m) {
		super(m);
		setCreativeTab(UT_Tabs.techTab);
		setHardness(2.5f);
		setStepSound(soundTypeMetal);
		setResistance(20);
		setBlockName("ChemicalPlantT1");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityChemicalPlantT1();
	}
	
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Block_Textures.CHEMICAL_PLANT_T1);
		up = iconRegister.registerIcon(Block_Textures.CHASIS_T1);
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
