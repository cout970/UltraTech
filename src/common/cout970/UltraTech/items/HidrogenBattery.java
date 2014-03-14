package common.cout970.UltraTech.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import common.cout970.UltraTech.energy.api.IItemEnergyEstorage;

public class HidrogenBattery extends UT_Item implements IItemEnergyEstorage{

	public int MaxEnergy;
	
	public HidrogenBattery(int id, String name) {
		super(id, name);
		setMaxStackSize(1);
		MaxEnergy = 100000;
		this.setHasSubtypes(true);
		this.setMaxDamage(MaxEnergy);
	}

	@Override
	public int getEnergy(ItemStack i) {
		if(i.stackTagCompound.hasKey("Energy")){
			return i.stackTagCompound.getInteger("Energy");
		}
		return 0;
	}

	@Override
	public int getDisplayDamage(ItemStack stack) {
		if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("Energy"))
			return MaxEnergy - stack.getTagCompound().getInteger("Energy");
		else
			return MaxEnergy;
	}
	
	@Override
	public int getDamage(ItemStack stack) {
		if(stack.getTagCompound() != null && stack.getTagCompound().hasKey("Energy"))
			return MaxEnergy - stack.getTagCompound().getInteger("Energy");
		else
			return MaxEnergy;
	}
	
	@Override
	public int getMaxDamage() {
		return MaxEnergy;
	}
	
	
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if( par1ItemStack.stackTagCompound == null )
                par1ItemStack.setTagCompound( new NBTTagCompound());
        par1ItemStack.stackTagCompound.setInteger("Energy", 0);
        par1ItemStack.stackTagCompound.setInteger("MaxEnergy", MaxEnergy );
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if( par1ItemStack.stackTagCompound == null )
			par1ItemStack.setTagCompound( new NBTTagCompound( ) );
		if(par1ItemStack.stackTagCompound.hasKey("Energy")){
		par3List.add( par1ItemStack.stackTagCompound.getInteger("Energy")/10+"/"+this.MaxEnergy/10);
		}else{
			par3List.add( 0+"/"+this.MaxEnergy);
		}
	}
	
	public int addEnergy(ItemStack stack, int energy){
		if(stack.getTagCompound() == null){
			stack.stackTagCompound = new NBTTagCompound();
			stack.getTagCompound().setInteger("Energy", 0);
		}
		
		int buffer = stack.getTagCompound().getInteger("Energy") + energy;
		int aux = 0;
		if(buffer > MaxEnergy)
			buffer = MaxEnergy;
		aux = buffer-MaxEnergy;
		
		stack.getTagCompound().setInteger("Energy", buffer);
		stack.setItemDamage(buffer);
		return aux;
	}
	
	public void removeEnergy(ItemStack stack, int energy){
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
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
		return false;
	}

	@SuppressWarnings("unchecked")
	public void getSubItems(int unknown, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems){
		ItemStack a = new ItemStack(this, 1, 0);	
		((IItemEnergyEstorage)a.getItem()).addEnergy(a, MaxEnergy);
		subItems.add(a);
			subItems.add(new ItemStack(this, 1, this.getMaxDamage()));
	}

	@Override
	public int getMaxEnergy() {
		return this.MaxEnergy;
	}
}
