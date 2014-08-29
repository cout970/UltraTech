package ultratech.api.power;

import java.util.List;

import ultratech.api.power.interfaces.IPower;
import ultratech.api.power.interfaces.IStorageItem;
import common.cout970.UltraTech.util.LogHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
/**
 *if you want to make a item with QP energy, you only need extend this or create your item like this. 
 * @author Cout970
 *
 */
public class ItemPower extends Item implements IStorageItem{

	public int MaxPower;
	
	public ItemPower(int maxPower) {
		super();
		MaxPower = maxPower;
		setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(100);
	}
	
	public void onCreated(ItemStack i, World par2World, EntityPlayer par3EntityPlayer) {
        if( i.stackTagCompound == null )
                i.setTagCompound( new NBTTagCompound());
        i.stackTagCompound.setInteger("Energy", 0);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack i, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if( i.stackTagCompound == null )
			i.setTagCompound( new NBTTagCompound());
		if(i.stackTagCompound.hasKey("Energy")){
		par3List.add(i.stackTagCompound.getInteger("Energy")+"/"+MaxPower+IPower.POWER_NAME);
		}else{
			par3List.add(0+"/"+MaxPower+IPower.POWER_NAME);
		}
	}
	
	@Override
	public int getDamage(ItemStack stack) {
		if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("Energy"))
			return  getMetadataByPercent(stack.getTagCompound().getInteger("Energy"),MaxPower);
		else
			return  getMetadataByPercent(0,MaxPower);
	}
	
	@SuppressWarnings("unchecked")
	public void getSubItems(Item unknown, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems){
		ItemStack a = new ItemStack(this, 1, this.getMaxDamage());	
		((IStorageItem)a.getItem()).addPower(a, MaxPower);
		subItems.add(a);
		subItems.add(new ItemStack(this, 1, 0));
	}

	//power
	
	@Override
	public int getPower(ItemStack i) {
		if(i.stackTagCompound != null)
			if(i.stackTagCompound.hasKey("Energy")){
			return i.stackTagCompound.getInteger("Energy");
		}
		return 0;
	}
	
	@Override
	public int addPower(ItemStack stack, int energy){
		if(stack.getTagCompound() == null){
			stack.stackTagCompound = new NBTTagCompound();
			stack.getTagCompound().setInteger("Energy", 0);
		}
		
		int buffer = stack.getTagCompound().getInteger("Energy") + energy;
		int aux = 0;
		if(buffer > MaxPower){
			aux = buffer-MaxPower;
			buffer = MaxPower;
		}
		
		stack.getTagCompound().setInteger("Energy", buffer);
		stack.setItemDamage(getMetadataByPercent(buffer,MaxPower));
		return aux;
	}
	
	@Override
	public void removePower(ItemStack stack, int energy) {
		if(stack.getTagCompound() == null){
			stack.stackTagCompound = new NBTTagCompound();
			stack.getTagCompound().setInteger("Energy", 0);
		}
		int buffer = stack.getTagCompound().getInteger("Energy") - energy;
		if(buffer < 0)
			buffer = 0;
		
		stack.getTagCompound().setInteger("Energy", buffer);
		stack.setItemDamage(getMetadataByPercent(buffer,MaxPower));
	}
	
	public int getMetadataByPercent(int energy,int capacity){//inveted for the durability display 
		return 100-(100*energy/capacity);
	}

	@Override
	public int getMaxPower() {
		return MaxPower;
	}
}
