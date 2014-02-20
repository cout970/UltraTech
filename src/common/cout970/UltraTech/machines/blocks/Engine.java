package common.cout970.UltraTech.machines.blocks;

import buildcraft.api.tools.IToolWrench;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.EngineEntity;
import common.cout970.UltraTech.machines.tileEntities.Machine;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Engine extends BlockContainer{

	public Engine(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
		setUnlocalizedName("Engine_UT");
		
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if(par5EntityPlayer.isSneaking()){
			return true;
		}else{
			if(par5EntityPlayer.getCurrentEquippedItem() != null && par5EntityPlayer.getCurrentEquippedItem().getItem() instanceof IToolWrench){
				TileEntity tile = par1World.getBlockTileEntity(x, y, z);
				if(tile != null){
					EngineEntity e = (EngineEntity) tile;
					e.Rotate();
					par1World.markBlockForUpdate(x, y+1, z);
					par1World.markBlockForUpdate(x, y-1, z);
					par1World.markBlockForUpdate(x, y, z+1);
					par1World.markBlockForUpdate(x, y, z-1);
					par1World.markBlockForUpdate(x+1, y, z);
					par1World.markBlockForUpdate(x-1, y, z);
					par1World.markBlockForUpdate(x, y, z);
					return true;
				}
			}
			if(!par1World.isRemote){

				TileEntity tile = par1World.getBlockTileEntity(x, y, z);
				if(tile != null){ 
					par5EntityPlayer.openGui(UltraTech.instance, 11, par1World, x, y, z);
					return true;
				}
			}
		}
		return true;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:engine");
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new EngineEntity();
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		Machine m = (Machine) w.getBlockTileEntity(x, y, z);
		m.updateMachine(w, x, y, z);
		((EngineEntity) m).canFound = true;
		((EngineEntity) m).c = null;
	}
	
	
}
