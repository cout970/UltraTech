package common.cout970.UltraTech.misc;

import common.cout970.UltraTech.machines.tileEntities.CrafterEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotPhantom extends Slot{

	private boolean pickup;

	public SlotPhantom(IInventory par1iInventory, int par2, int par3, int par4, boolean pick) {
		super(par1iInventory, par2, par3, par4);
		pickup = pick;
	}
	
	
	public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
		if(!pickup){
		CrafterEntity e = (CrafterEntity) this.inventory;
		e.craft();
		}
		this.onSlotChanged();
    }

	public boolean canTakeStack(EntityPlayer par1EntityPlayer)
	{
		if(!pickup){
			CrafterEntity e = (CrafterEntity) this.inventory;
			return e.allFound(-1);
		}
		return true;
	}

	public boolean isItemValid(ItemStack par1ItemStack)
    {
        return pickup;
    }
	
	public int getSlotStackLimit()
    {
        return 0;
    }
	
	
	public boolean getHasStack()
    {
        return false;
    }

}
