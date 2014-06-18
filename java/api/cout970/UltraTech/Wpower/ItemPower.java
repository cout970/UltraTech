package api.cout970.UltraTech.Wpower;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemPower extends Item implements IStorageItem{

	public int MaxPower;
	
	public ItemPower(int maxPower) {
		super();
		MaxPower = maxPower;
		setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(MaxPower);
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
		par3List.add(i.stackTagCompound.getInteger("Energy")+"/"+MaxPower+" W");
		}else{
			par3List.add(0+"/"+MaxPower+" W");
		}
	}
	
	@Override
	public int getDisplayDamage(ItemStack stack) {
		if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("Energy"))
			return  MaxPower - stack.getTagCompound().getInteger("Energy");
		else
			return  MaxPower;
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
		stack.setItemDamage(buffer);
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
		stack.setItemDamage(buffer);
	}

	@Override
	public int getMaxPower() {
		return MaxPower;
	}
}
