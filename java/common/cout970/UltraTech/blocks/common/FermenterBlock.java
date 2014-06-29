package common.cout970.UltraTech.blocks.common;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import common.cout970.UltraTech.TileEntities.electric.FermenterEntity;
import common.cout970.UltraTech.core.UltraTech;

public class FermenterBlock extends BlockContainer{


	private IIcon[] icons;

	public FermenterBlock(Material m) {
		super(m);
		setCreativeTab(UltraTech.techTab);
		setHardness(2f);
		setStepSound(soundTypeMetal);
		setBlockName("Fermenter");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new FermenterEntity();
	}
	
	public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[2];
		icons[0] = IR.registerIcon("ultratech:chasis");
		icons[1] = IR.registerIcon("ultratech:fermenter");	
		}
	
	public IIcon getIcon(int side, int meta){
		if(side == 0 || side == 1)return icons[0];
		return icons[1];
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int a, float b, float c, float d){
		if(!p.isSneaking())p.openGui(UltraTech.instance, 1, world, x, y, z);
		return true;
	}
}
