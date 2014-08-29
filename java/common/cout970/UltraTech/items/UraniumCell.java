package common.cout970.UltraTech.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import common.cout970.UltraTech.managers.ItemManager;
import ultratech.api.reactor.IReactorFuel;

public class UraniumCell extends UT_Item implements IReactorFuel{

	public UraniumCell(String name) {
		super(name);
		setMaxStackSize(1);
		this.setHasSubtypes(true);
        this.setMaxDamage(5000);
        }

	@Override
	public int getSteamPerTick(){
		return 40;
	}
	
	public ItemStack getContainerItem(ItemStack itemStack)
    {
        return new ItemStack(ItemManager.ItemName.get("Cell"));
    }
}