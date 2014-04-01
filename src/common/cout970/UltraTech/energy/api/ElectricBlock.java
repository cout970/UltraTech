package common.cout970.UltraTech.energy.api;

import java.util.Arrays;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class ElectricBlock extends BlockContainer{

	public ElectricBlock(int par1, Material par2Material) {
		super(par1, par2Material);
	}

	public void onBlockAdded(World w, int x, int y, int z) {
		if(w.isRemote)return;
		if(!(w.getBlockTileEntity(x, y, z) instanceof ElectricConductor))return;
		ElectricConductor te = (ElectricConductor) w.getBlockTileEntity(x, y, z);
		boolean hasNetwork = false;
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			TileEntity e = EnergyUtils.getRelative(te, dir);
			if(e instanceof ElectricConductor){
				if(Arrays.asList(((ElectricConductor) e).getConnectableSides()).contains(dir.getOpposite())){
					if(((ElectricConductor) e).getNetwork() != null){
						if(!hasNetwork){
							te.setNetwork(((ElectricConductor) e).getNetwork());
							te.getNetwork().getComponents().add(te);
							hasNetwork = true;
						}else{
							te.getNetwork().mergeWith(((ElectricConductor) e).getNetwork());
						}		
					}
				}
			}
		}
		if(!hasNetwork){
			te.setNetwork(EnergyNetwork.create(te));
		}
		te.getNetwork().refresh();
	}

	public void onBlockPreDestroy(World w, int x, int y, int z, int meta) {
		if(w.isRemote)return;
		ElectricConductor te = (ElectricConductor)w.getBlockTileEntity(x, y, z);
		if(te.getNetwork() != null){
			te.getNetwork().excludeAndRecalculate(te);
		}
	}
}
