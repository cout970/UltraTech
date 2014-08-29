package common.cout970.UltraTech.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import common.cout970.UltraTech.managers.ItemManager;
import ultratech.api.reactor.IReactorFuel;


public class ThoriumCell extends UT_Item implements IReactorFuel{

	public ThoriumCell(String name) {
		super(name);
		setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(10000);
	}

	@Override
	public int getSteamPerTick() {
		return 80;
	}
	
	public ItemStack getContainerItem(ItemStack itemStack)
    {
        return new ItemStack(ItemManager.ItemName.get("Cell"));
    }
}