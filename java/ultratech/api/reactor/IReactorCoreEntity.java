package ultratech.api.reactor;

import net.minecraftforge.fluids.IFluidTank;

public interface IReactorCoreEntity {

	public IFluidTank getTank(int t);

	public void setSize(int size);

	public void updateComponents();

	public int getSize();

	public boolean isSlotinSpace(int g, int space);

	public void upadateRedstoneSignal();
}
