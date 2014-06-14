package api.cout970.UltraTech.Vpower;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemPower extends Item implements IStorageItem{

	public double MaxPower;
	
	public ItemPower(double maxPower) {
		super();
		MaxPower = maxPower;
		setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage((int) MaxPower);
	}
	
	public void onCreated(ItemStack i, World par2World, EntityPlayer par3EntityPlayer) {
        if( i.stackTagCompound == null )
                i.setTagCompound( new NBTTagCompound());
        i.stackTagCompound.setDouble("Energy", 0);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack i, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if( i.stackTagCompound == null )
			i.setTagCompound( new NBTTagCompound());
		if(i.stackTagCompound.hasKey("Energy")){
		par3List.add(((int)i.stackTagCompound.getDouble("Energy"))+"/"+((int)MaxPower)+"V");
		}else{
			par3List.add(0+"/"+((int)MaxPower)+"V");
		}
	}
	
	@Override
	public int getDisplayDamage(ItemStack stack) {
		if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("Energy"))
			return (int) (MaxPower - stack.getTagCompound().getDouble("Energy"));
		else
			return (int) MaxPower;
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
	public double getPower(ItemStack i) {
		if(i.stackTagCompound != null)
			if(i.stackTagCompound.hasKey("Energy")){
			return i.stackTagCompound.getDouble("Energy");
		}
		return 0;
	}
	
	@Override
	public double addPower(ItemStack stack, double energy){
		if(stack.getTagCompound() == null){
			stack.stackTagCompound = new NBTTagCompound();
			stack.getTagCompound().setDouble("Energy", 0);
		}
		
		double buffer = stack.getTagCompound().getDouble("Energy") + energy;
		double aux = 0;
		if(buffer > MaxPower)
			buffer = MaxPower;
		aux = buffer-MaxPower;
		
		stack.getTagCompound().setDouble("Energy", buffer);
		stack.setItemDamage((int)buffer);
		return aux;
	}
	
	@Override
	public void removePower(ItemStack stack, double energy) {
		if(stack.getTagCompound() == null){
			stack.stackTagCompound = new NBTTagCompound();
			stack.getTagCompound().setDouble("Energy", 0);
		}
		double buffer = stack.getTagCompound().getDouble("Energy") - energy;
		if(buffer < 0)
			buffer = 0f;
		
		stack.getTagCompound().setDouble("Energy", buffer);
		stack.setItemDamage((int) buffer);
	}

	@Override
	public double getMaxPower() {
		return MaxPower;
	}
}
