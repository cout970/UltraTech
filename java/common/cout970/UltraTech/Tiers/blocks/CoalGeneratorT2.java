package common.cout970.UltraTech.Tiers.blocks;

import common.cout970.UltraTech.TileEntities.electric.tiers.CoalGeneratorEntityT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.CoalGeneratorEntityT2_Entity;
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

public class CoalGeneratorT2 extends BlockConductor{

	public IIcon[] icons;
	
	public CoalGeneratorT2(Material m) {
		super(m);
		setCreativeTab(UltraTech.techTab);
		setHardness(2f);
		setStepSound(soundTypeMetal);
		setBlockName("CoalGeneratorT2");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new CoalGeneratorEntityT2_Entity();
	}
	
	public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[3];
		icons[0] = IR.registerIcon("ultratech:chasis1");
		icons[1] = IR.registerIcon("ultratech:machines/generator_off_2");
		icons[2] = IR.registerIcon("ultratech:machines/generator_on_2");
	}
	
	public IIcon getIcon(int side, int meta){
		if(side == 0 || side == 1)return icons[0];
		if(meta == 0)return icons[1];
		return icons[2];
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int a, float b, float c, float d){
		if(!p.isSneaking())p.openGui(UltraTech.instance, 0, world, x, y, z);
		return true;
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, Block block){
		IUpdatedEntity t = (IUpdatedEntity) w.getTileEntity(x, y, z);
		t.onNeigUpdate();
	}
}
