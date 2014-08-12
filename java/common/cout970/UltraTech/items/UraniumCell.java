package common.cout970.UltraTech.items;

import ultratech.api.reactor.IReactorFuel;

public class UraniumCell extends UT_Item implements IReactorFuel{

	public UraniumCell(String name) {
		super(name);
		setMaxStackSize(1);
		this.setHasSubtypes(true);
        this.setMaxDamage(1000);
        }

	@Override
	public int getSteamPerTick() {
		return 40;
	}
}