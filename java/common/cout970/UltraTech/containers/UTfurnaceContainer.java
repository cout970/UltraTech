package common.cout970.UltraTech.containers;


import common.cout970.UltraTech.TileEntities.Tier2.FurnaceEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class UTfurnaceContainer extends Container {

	public FurnaceEntity tileEntity;
	

	public UTfurnaceContainer(InventoryPlayer inventoryPlayer, FurnaceEntity te){
		super();
		tileEntity = te;
		
		addSlotToContainer(new Slot(te, 0, 61, 32));
		addSlotToContainer(new Slot(te, 1, 117, 32){
			@Override
			public boolean isItemValid(ItemStack par1ItemStack)
		    {
		        return false;
		    }
		});
		bindPlayerInventory(inventoryPlayer);
	}
	public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, tileEntity.progres);
        par1ICrafting.sendProgressBarUpdate(this, 1, (int)tileEntity.getPower().getCharge());
        par1ICrafting.sendProgressBarUpdate(this, 2, tileEntity.speed);
    }
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < crafters.size(); i++) {
			tileEntity.sendGUINetworkData(this, (ICrafting) crafters.get(i));
		}
	}

	@Override
	public void updateProgressBar(int i, int j) {
		tileEntity.getGUINetworkData(i, j);
	}

	private void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				 addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                         8 + j * 18, 84 + i * 18));
			}
		}
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack aux = null;
		Slot current = (Slot)this.inventorySlots.get(slot);
		
		if (current != null && current.getHasStack())
		{
			ItemStack itemstack = current.getStack();
			aux = itemstack.copy();
			
			if(slot == 0){//slot 0 smelt
				if(!mergeItemStack(itemstack, 2, 38, false)){
					return null;
				}
				current.onSlotChange(itemstack, aux);
				
			}else if(slot == 1){//slot 1 result
				if(!mergeItemStack(itemstack, 2, 38, false)){
					return null;
				}
				current.onSlotChange(itemstack, aux);
			}else{
				if (FurnaceRecipes.smelting().getSmeltingResult(itemstack) != null)
                {
                    if (!this.mergeItemStack(itemstack, 0, 1, false))
                    {
                        return null;
                    }
                }else if (slot >= 2 && slot < 29)
                {
                    if (!this.mergeItemStack(itemstack, 29, 38, false))
                    {
                        return null;
                    }
                }
                else if (slot >= 29 && slot < 38){
                	if(!this.mergeItemStack(itemstack, 2, 29, false))
                	{
                		return null;
                	}
                }

				current.onSlotChanged();
			}
			if (itemstack.stackSize == 0)
			{
				current.putStack((ItemStack)null);
			}
			if (itemstack.stackSize == aux.stackSize)
			{
				return null;
			}
			current.onPickupFromSlot(player, itemstack);
		}
		return null;
	}

}
