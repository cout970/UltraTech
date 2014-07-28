package common.cout970.UltraTech.items;

import ultratech.api.reactor.IReactorFuel;


public class RadioniteCell extends UT_Item implements IReactorFuel{

	public RadioniteCell(String name) {
		super(name);
		setMaxStackSize(1);
		this.setHasSubtypes(true);
        this.setMaxDamage(500);
        }

	@Override
	public int getSteamPerTick() {
		return 40;
	}
}
