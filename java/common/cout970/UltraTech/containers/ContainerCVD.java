package common.cout970.UltraTech.containers;

import ultratech.api.recipes.CVD_Recipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityChemicalVaporDesintegrationT1;


public class ContainerCVD extends ContainerUT{

	public TileEntityChemicalVaporDesintegrationT1 tileEntity;
	
	public ContainerCVD(InventoryPlayer inventoryPlayer, TileEntityChemicalVaporDesintegrationT1 tileEntity2){
		super(inventoryPlayer,tileEntity2);
		tileEntity = tileEntity2;
		
		addSlotToContainer(new Slot(tileEntity, 0, 53, 33));
		addSlotToContainer(new Slot(tileEntity, 1, 89, 33));
		addSlotToContainer(new Slot(tileEntity, 2, 143, 33){
			@Override
			public boolean isItemValid(ItemStack a){
				return false;
			}
		});
		bindPlayerInventory(inventoryPlayer);
	}
	
//	public void addCraftingToCrafters(ICrafting par1ICrafting)
//    {
//        super.addCraftingToCrafters(par1ICrafting);
//        par1ICrafting.sendProgressBarUpdate(this, 2, (int) tileEntity.Progres);
//        par1ICrafting.sendProgressBarUpdate(this, 1, (int)tileEntity.getEnergy());
//    }
	
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
	
	
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return transfer(player, slot, 3);
	}
	
	public ItemStack transfer(EntityPlayer player, int slot,int inv) {
		ItemStack aux = null;
		Slot current = (Slot)this.inventorySlots.get(slot);
		
		if (current != null && current.getHasStack())
		{
			ItemStack itemstack = current.getStack();
			aux = itemstack.copy();
			if(slot < inv){
				if(!mergeItemStack(itemstack, inv, 36+inv, true)){
					return null;
				}
				current.onSlotChange(itemstack, aux);
			}else{
				if(CVD_Recipe.isIngredient(itemstack)){
					if(!mergeItemStack(itemstack, 0, inv-1, false)){
						return null;
					}
				}else if (slot >= inv && slot < 27+inv)
                {
                    if (!this.mergeItemStack(itemstack, 27+inv, 36+inv, false))
                    {
                        return null;
                    }
                }
                else if (slot >= 27+inv && slot < 36+inv){
                	if(!this.mergeItemStack(itemstack, inv, 27+inv, false))
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
