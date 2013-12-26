package common.cout970.UltraTech.machines.blocks;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.PCentity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PresureChamber extends BlockContainer{

	public PresureChamber(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(2.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
		setUnlocalizedName("PC");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new PCentity();
	}

	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if(par5EntityPlayer.isSneaking()){
			return true;
		}else{
			if(!par1World.isRemote){
				PCentity tile = (PCentity)par1World.getBlockTileEntity(x, y, z);
				if(tile != null){ 
					par5EntityPlayer.openGui(UltraTech.instance, 9, par1World, x, y, z);
					return true;
				}
			}
		}
		return true;
	}

//	@Override
//	public boolean isOpaqueCube(){
//		return false;
//	}
//	@Override
//	public int getRenderType() {
//		return -1;
//	}
//	public boolean renderAsNormalBlock() {
//		return false;
//	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:presurechamber");
	}
}
