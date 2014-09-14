package common.cout970.UltraTech.items;

import common.cout970.UltraTech.managers.ItemManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ultratech.api.reactor.IReactorFuel;

public class ItemPlutoniumCell extends ItemUT implements IReactorFuel{

	public ItemPlutoniumCell(String name) {
		super(name);
		setMaxStackSize(1);
		this.setHasSubtypes(true);
        this.setMaxDamage(2000);
        }

	@Override
	public int getSteamPerTick() {
		return 120;
	}

	public ItemStack getContainerItem(ItemStack itemStack)
    {
        return new ItemStack(ItemManager.ItemName.get("Cell"));
    }
}
