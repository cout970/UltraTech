package common.cout970.UltraTech.blocks.common;

import common.cout970.UltraTech.TileEntities.utility.TileEntityPainter3D;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.managers.UltraTech;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPainter3D extends BlockContainer{

	private IIcon[] icons;

	public BlockPainter3D(Material m) {
		super(m);
		setCreativeTab(UT_Tabs.techTab);
		setHardness(2f);
		setStepSound(soundTypeMetal);
		setBlockName("Painter");
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityPainter3D();
	}
	
	public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[2];
		icons[0] = IR.registerIcon(Block_Textures.CHASIS_T1);
		icons[1] = IR.registerIcon(Block_Textures.PAINTER);
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
