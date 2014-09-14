package common.cout970.UltraTech.blocks.tiers;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityGrindingMillT2;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.managers.UltraTech;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.util.power.BlockConductor;

public class BlockGrindingMillT2 extends BlockConductor{

	private IIcon up;

	public BlockGrindingMillT2(Material m) {
		super(m);
		setCreativeTab(UT_Tabs.techTab);
		setHardness(2.5f);
		setStepSound(soundTypeMetal);
		setResistance(20);
		setBlockName("GrindingMillT2");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityGrindingMillT2();
	}
	
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(Block_Textures.GRINDING_MILL_T2);
		up = iconRegister.registerIcon(Block_Textures.CHASIS_T2);
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
