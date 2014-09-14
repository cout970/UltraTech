package common.cout970.UltraTech.items;

import java.util.List;

import common.cout970.UltraTech.handlers.HandlerBottle;
import common.cout970.UltraTech.managers.FluidManager;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.util.HelperNBT;
import common.cout970.UltraTech.util.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;


public class ItemBottle extends ItemUT{

	public ItemBottle() {
		super("Bottle");
		setHasSubtypes(true);
		setContainerItem(this);
	}

	@SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p)
	{
		if(w.isRemote)return i;
		if(i.getItemDamage() == 0){
			MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(w, p, true);
			if(mop == null)return i;
			ItemStack item = HandlerBottle.INSTANCE.fillCustomBottle(w, mop.blockX, mop.blockY, mop.blockZ);
			if(item != null){
				i.splitStack(1);
				if(i.stackSize <= 0)return item;
				if(!p.inventory.addItemStackToInventory(item)){
					p.dropPlayerItemWithRandomChoice(item, true);
				}
				return i;
			}
		}else{
			FluidStack f = HelperNBT.getFluid(i);
			if(f != null){
				MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(w, p, false);
				if(mop == null)return i;
				if(f.getFluid() == null || f.getFluid().getBlock() == null)return i;
				Block b = f.getFluid().getBlock();
				if(mop.sideHit == 0)mop.blockY--;
				if(mop.sideHit == 1)mop.blockY++;
				if(mop.sideHit == 2)mop.blockZ--;
				if(mop.sideHit == 3)mop.blockZ++;
				if(mop.sideHit == 4)mop.blockX--;
				if(mop.sideHit == 5)mop.blockX++;
				if(w.getBlock(mop.blockX, mop.blockY, mop.blockZ) == null || w.getBlock(mop.blockX, mop.blockY, mop.blockZ).isReplaceable(w, mop.blockX, mop.blockY, mop.blockZ)){
					w.setBlock(mop.blockX, mop.blockY, mop.blockZ, b);
					w.notifyBlockOfNeighborChange(mop.blockX, mop.blockY, mop.blockZ, b);
					if(i.stackSize == 1){
						HelperNBT.clear(i);
						i.setItemDamage(0);
					}else{
						i.splitStack(1);
						if(!p.inventory.addItemStackToInventory(new ItemStack(ItemManager.ItemName.get("Bottle"),1))){
							p.dropPlayerItemWithRandomChoice(new ItemStack(ItemManager.ItemName.get("Bottle"),1), true);
						}
					}
				}
			}
		}
		return i;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List subItems)
	{
		subItems.add(new ItemStack(this, 1, 0));
		for(int x=1; x < FluidRegistry.getRegisteredFluidIDs().size(); x++){
			ItemStack i = new ItemStack(this, 1, x);
			HelperNBT.setFluid(i,FluidRegistry.getFluid(x),1000);
			subItems.add(i);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer p, List info, boolean nose) {
		if(item.getItemDamage() != 0){
			FluidStack f = HelperNBT.getFluid(item);
			if(f == null)return;
			info.add("Fluid: "+f.getFluid().getName());
		}
	}
}
