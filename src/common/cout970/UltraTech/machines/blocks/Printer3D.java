package common.cout970.UltraTech.machines.blocks;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.Printer3DEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Printer3D extends BlockContainer{

	public Printer3D(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
		setUnlocalizedName("Printer3D");
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if(par5EntityPlayer.isSneaking()){
			return true;
		}else{
			if(!par1World.isRemote){
				Printer3DEntity tile = (Printer3DEntity)par1World.getBlockTileEntity(x, y, z);
				if(tile != null){ 
					par5EntityPlayer.openGui(UltraTech.instance, 10, par1World, x, y, z);
					return true;
				}
			}
		}
		return true;
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:printer");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new Printer3DEntity();
	}
}
