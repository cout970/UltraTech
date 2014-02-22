package common.cout970.UltraTech.machines.renders.holograms;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class HologramEntity extends Entity{

	public Map map;

	public HologramEntity(World par1World) {
		super(par1World);
	}

	@Override
	protected void entityInit() {
	this.setSize(2f, 1f);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		
	}

}
