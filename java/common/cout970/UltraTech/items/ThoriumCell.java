package common.cout970.UltraTech.items;

import ultratech.api.reactor.IReactorFuel;


public class ThoriumCell extends UT_Item implements IReactorFuel{

	public ThoriumCell(String name) {
		super(name);
		setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(2000);
	}

	@Override
	public int getSteamPerTick() {
		return 40;
	}
}