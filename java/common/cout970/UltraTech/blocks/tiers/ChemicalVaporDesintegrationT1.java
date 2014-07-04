package common.cout970.UltraTech.blocks.tiers;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import api.cout970.UltraTech.MeVpower.BlockConductor;
import common.cout970.UltraTech.TileEntities.electric.tiers.ChemicalVaporDesintegrationT1_Entity;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.misc.IUpdatedEntity;

public class ChemicalVaporDesintegrationT1 extends BlockConductor{

	public IIcon[] icons;
	
	public ChemicalVaporDesintegrationT1(Material m) {
		super(m);
		setCreativeTab(UltraTech.techTab);
		setHardness(2f);
		setStepSound(soundTypeMetal);
		setBlockName("ChemicalVaporDesintegrationT1");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new ChemicalVaporDesintegrationT1_Entity();
	}
	
	public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[2];
		icons[0] = IR.registerIcon("ultratech:chasis");
		icons[1] = IR.registerIcon("ultratech:machines/CVD_off");
	}
	
	public IIcon getIcon(int side, int meta){
		if(side == 0 || side == 1)return icons[0];
		return icons[1];
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int a, float b, float c, float d){
		if(!p.isSneaking())p.openGui(UltraTech.instance, 1, world, x, y, z);
		return true;
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, Block block){
		IUpdatedEntity t = (IUpdatedEntity) w.getTileEntity(x, y, z);
		t.onNeigUpdate();
	}

}
