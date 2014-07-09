package common.cout970.UltraTech.TileEntities.electric.tiers;

import cofh.api.tileentity.IRedstoneControl;

import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.misc.MachineWithInventory;

public class ConfigurableMachine extends MachineWithInventory implements IUpdatedEntity,IRedstoneControl{

	public boolean isRedstonePower;
	public ControlMode Mode = ControlMode.LOW;
	
	public ConfigurableMachine(int slots, String n, CostData a) {
		super(slots, n, a);
	}

	@Override
	public void onNeigUpdate() {
		setPowered(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord));
	}
	
	public boolean shouldWork(){
		if(Mode == ControlMode.DISABLED)return true;
		if(Mode == ControlMode.LOW && !isRedstonePower)return true;
		if(Mode == ControlMode.HIGH && isRedstonePower)return true;
		return false;
	}

	@Override
	public void setPowered(boolean isPowered) {
		isRedstonePower = isPowered;
	}

	@Override
	public boolean isPowered() {
		return isRedstonePower;
	}

	@Override
	public void setControl(ControlMode control) {
		Mode = control;
	}

	@Override
	public ControlMode getControl() {
		return Mode;
	}

}
