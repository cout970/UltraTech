package common.cout970.UltraTech.machines.blocks;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.ReciverEntity;
import common.cout970.UltraTech.machines.tileEntities.SateliteEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class Reciver extends BlockContainer{

	private Icon blockIcon2;

	public Reciver(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
		setUnlocalizedName("Reciver");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new ReciverEntity();
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon2 = iconRegister.registerIcon("ultratech:ids");
		this.blockIcon = iconRegister.registerIcon("ultratech:machinechasis");
	}
	
	@Override
	public Icon getIcon(int side,int a)
    {
		return side != 1 ? this.blockIcon : this.blockIcon2;
    }
	
	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		TileEntity t = w.getBlockTileEntity(x, y, z);
		if(t != null && t instanceof ReciverEntity){
			((ReciverEntity)t).check();
		}
		for(int yCoord=y;yCoord<256;yCoord++){
			if(w.getBlockId(x, yCoord, z)==UltraTech.Satelite.blockID){
				TileEntity te = w.getBlockTileEntity(x, yCoord, z);
				if(te != null && !w.isRemote){
					if(te instanceof SateliteEntity){
						SateliteEntity r = (SateliteEntity)te;
						r.checkBase();
					}
				}
			}
		}
	}
	
	@Override
	public void onBlockAdded(World w, int x, int y, int z)
	{
		super.onBlockAdded(w, x, y, z);
		for(int yCoord=y;yCoord<256;yCoord++){
			if(w.getBlockId(x, yCoord, z)==UltraTech.Satelite.blockID){
				TileEntity te = w.getBlockTileEntity(x, yCoord, z);
				if(te != null && !w.isRemote){
					if(te instanceof SateliteEntity){
						SateliteEntity r = (SateliteEntity)te;
						r.checkBase();
					}
				}
			}
		}
	}
}
