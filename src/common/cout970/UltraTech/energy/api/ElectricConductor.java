package common.cout970.UltraTech.energy.api;

import java.util.Arrays;

import common.cout970.UltraTech.lib.GraficCost.MachineTier;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class ElectricConductor extends TileEntity{

	private EnergyNetwork net = null;
	public MachineTier tier = MachineTier.Tier1;

	public void setNetwork(EnergyNetwork energyNetwork) {
		net = energyNetwork;
	}

	public EnergyNetwork getNetwork() {
			return net;
	}
	
	public void onNetworkUpdate() {
		
	}
	
	public ForgeDirection[] getConnectableSides(){
		return ForgeDirection.VALID_DIRECTIONS;
	}

	@Override
	public void updateEntity() {
		if(worldObj.isRemote)return;
		if(net == null){
			boolean hasNetwork = false;
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
				TileEntity e = EnergyUtils.getRelative(this, dir);
				if(e instanceof ElectricConductor){
					if(Arrays.asList(((ElectricConductor) e).getConnectableSides()).contains(dir.getOpposite())){
						if(((ElectricConductor) e).getNetwork() != null){
							if(!hasNetwork){
								setNetwork(((ElectricConductor) e).getNetwork());
								hasNetwork = true;
							}else{
								net.mergeWith(((ElectricConductor) e).getNetwork());
							}
						}
					}
				}
			}
			if(!hasNetwork){
				setNetwork(EnergyNetwork.create(this));
			}
			net.refresh();
		}
	}
}
