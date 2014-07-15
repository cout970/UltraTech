package common.cout970.UltraTech.items;

import java.util.List;

import ultratech.api.power.IPowerConductor;
import ultratech.api.power.IStorageItem;
import ultratech.api.power.ItemPower;
import ultratech.api.power.PowerInterface;
import ultratech.api.power.StorageInterface;
import common.cout970.UltraTech.TileEntities.electric.StorageTier1;
import common.cout970.UltraTech.TileEntities.electric.StorageTier2;
import common.cout970.UltraTech.TileEntities.electric.StorageTier3;
import common.cout970.UltraTech.managers.UT_Tabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Battery extends ItemPower{
	
	private String name;
	
	public Battery(String name) {
		super(48000);
		setUnlocalizedName(name);
		setCreativeTab(UT_Tabs.ResourceTab);
		setMaxStackSize(1);
		this.name = name;
	}
	
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("ultratech:"+name.toLowerCase());
	}
	
	public String getName(){
		return name;
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
		TileEntity t = world.getTileEntity(x, y, z);
		if(t instanceof StorageTier1 || t instanceof StorageTier2 || t instanceof StorageTier3){
			StorageInterface p = (StorageInterface) ((IPowerConductor) t).getPower();
			int space = (int) (p.getCapacity()-p.getCharge());
			if(space > 0){
				if(space < getPower(stack)){
					p.addCharge(space);
					removePower(stack, space);
					return true;
				}else{
					p.addCharge(getPower(stack));
					removePower(stack, getPower(stack));
					return true;
				}
			}
		}
		return super.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World w, EntityPlayer p)
    {
		if(!p.isSneaking()){
			ItemStack[] i = p.inventory.mainInventory;
			for(ItemStack s : i){
				if(s != null){
					Item it = s.getItem();
					if(it instanceof IStorageItem && !(it instanceof Battery)){
						IStorageItem st = (IStorageItem) it;
						int space = (int) (st.getMaxPower()-st.getPower(s));
						int toMove = Math.min(space, getPower(stack));
						if(toMove > 0){
							st.addPower(s, toMove);
							removePower(stack, toMove);
						}
					}
				}
			}
		}
		return super.onItemRightClick(stack, w, p);
    }
	
	public int getMetadata(int par1)
    {
        return par1;
    }
}
