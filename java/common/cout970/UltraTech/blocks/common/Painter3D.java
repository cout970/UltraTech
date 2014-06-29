package common.cout970.UltraTech.blocks.common;

import common.cout970.UltraTech.TileEntities.utility.Painter3DEntity;
import common.cout970.UltraTech.core.UltraTech;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class Painter3D extends BlockContainer{

	private IIcon[] icons;

	public Painter3D(Material m) {
		super(m);
		setCreativeTab(UltraTech.techTab);
		setHardness(2f);
		setStepSound(soundTypeMetal);
		setBlockName("Painter");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new Painter3DEntity();
	}
	
	public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[2];
		icons[0] = IR.registerIcon("ultratech:chasis");
		icons[1] = IR.registerIcon("ultratech:painter");	}
	
	public IIcon getIcon(int side, int meta){
		if(side == 0 || side == 1)return icons[0];
		return icons[1];
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer p, int a, float b, float c, float d){
		if(!p.isSneaking())p.openGui(UltraTech.instance, 1, world, x, y, z);
		return true;
	}
}
