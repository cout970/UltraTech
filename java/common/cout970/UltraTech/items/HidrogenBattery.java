package common.cout970.UltraTech.items;

import java.util.List;

import common.cout970.UltraTech.core.UltraTech;
import api.cout970.UltraTech.Vpower.IStorageItem;
import api.cout970.UltraTech.Vpower.ItemPower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class HidrogenBattery extends ItemPower{
	
	private String name;
	
	public HidrogenBattery(String name) {
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
}
