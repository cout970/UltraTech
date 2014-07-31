package common.cout970.UltraTech.blocks.tiers;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.electric.tiers.CutterT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.FluidGeneratorT1_Entity;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.managers.UltraTech;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.util.power.BlockConductor;

public class FluidGeneratorT1 extends BlockConductor{

public IIcon[] icons;
	
	public FluidGeneratorT1(Material m) {
		super(m);
		setCreativeTab(UT_Tabs.techTab);
		setHardness(2f);
		setStepSound(soundTypeMetal);
		setBlockName("FluidGeneratorT1");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new FluidGeneratorT1_Entity();
	}
	
	public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[3];
		icons[0] = IR.registerIcon(Block_Textures.CHASIS_T1);
		icons[1] = IR.registerIcon("ultratech:machines/fluidgenerator_off");
		icons[2] = IR.registerIcon("ultratech:machines/fluidgenerator_on");
	}
	
	public IIcon getIcon(int side, int meta){
		if(side == 0 || side == 1)return icons[0];
		if(meta == 0)return icons[1];
		return icons[2];
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
