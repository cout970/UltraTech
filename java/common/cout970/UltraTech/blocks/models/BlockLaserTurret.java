package common.cout970.UltraTech.blocks.models;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.utility.TileEntityLaserTurret;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.util.power.BlockConductor;

public class BlockLaserTurret extends BlockConductor{

	public BlockLaserTurret() {
		super(Material.iron);
		setCreativeTab(UT_Tabs.techTab);
		setHardness(2.5f);
		setStepSound(soundTypeMetal);
		setResistance(20);
		setBlockName("LaserTurret");
	}
	
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Block_Textures.VOID);
	}
	
	public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ){
		IUpdatedEntity i = (IUpdatedEntity) world.getTileEntity(x, y, z);
		if(i != null)i.onNeigUpdate();
	}
	
	@Override
	public TileEntity createNewTileEntity(World w, int m) {
		return new TileEntityLaserTurret();
	}
	
	public boolean isOpaqueCube(){
		return false;
	}

	public boolean renderAsNormalBlock(){
		return false;
	}

}
