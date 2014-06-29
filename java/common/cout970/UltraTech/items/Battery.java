package common.cout970.UltraTech.items;

import java.util.List;

import common.cout970.UltraTech.TileEntities.electric.StorageTier1;
import common.cout970.UltraTech.TileEntities.electric.StorageTier2;
import common.cout970.UltraTech.TileEntities.electric.StorageTier3;
import common.cout970.UltraTech.core.UltraTech;
import api.cout970.UltraTech.MeVpower.IPowerConductor;
import api.cout970.UltraTech.MeVpower.IStorageItem;
import api.cout970.UltraTech.MeVpower.ItemPower;
import api.cout970.UltraTech.MeVpower.PowerInterface;
import api.cout970.UltraTech.MeVpower.StorageInterface;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Battery extends ItemPower{
	
	private String name;
	
	public Battery(String name) {
		super(12800);
		setUnlocalizedName(name);
		setCreativeTab(UltraTech.ResourceTab);
		setMaxStackSize(64);
		this.name = name;
	}
	
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("ultratech:"+name.toLowerCase());
	}
	
	public String getName(){
		return name;
	}
	
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
		System.out.println("passing charge");
		TileEntity t = world.getTileEntity(x, y, z);
		if(t instanceof StorageTier1 || t instanceof StorageTier2 || t instanceof StorageTier3){
			StorageInterface p = (StorageInterface) ((IPowerConductor) t).getPower();
			int space = (int) (p.maxCharge()-p.getCharge());
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
		return false;
	}
}
