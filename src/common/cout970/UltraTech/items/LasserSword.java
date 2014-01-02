package common.cout970.UltraTech.items;

import java.util.List;

import com.google.common.collect.Multimap;
import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.misc.IItemEnergyEstorage;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class LasserSword extends UT_Item implements IItemEnergyEstorage{
	
	public int MaxEnergy = 10000;

	public LasserSword(int par1,String name){
		super(par1,name);
		setCreativeTab(UltraTech.techTab);
		setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(MaxEnergy);
		setUnlocalizedName("LasserSword");
		setTextureName("LasserSword");
	}


	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("ultratech:lassersword");
	}

	@SuppressWarnings("unchecked")
	public void getSubBlocks(int unknown, CreativeTabs tab, @SuppressWarnings("rawtypes") List subItems){
			subItems.add(new ItemStack(this, 1, this.MaxEnergy));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Multimap getItemAttributeModifiers()
	{
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 10d, 0));
		return multimap;
	}
//energy
	
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
		par3List.add( par1ItemStack.stackTagCompound.getInteger("Energy")+"/"+this.MaxEnergy);
		}else{
			par3List.add( 0+"/"+this.MaxEnergy);
		}
	}
	
	public int gainEnergy(ItemStack stack, int energy){
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
	
	public void loseEnergy(ItemStack stack, int energy){
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



	@Override
	public int getMaxEnergy() {
		return this.MaxEnergy;
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
	{
		if(((IItemEnergyEstorage)par1ItemStack.getItem()).getEnergy(par1ItemStack) >= 100){
			((IItemEnergyEstorage)par1ItemStack.getItem()).gainEnergy(par1ItemStack, -100);
		}else{
			return false;
		}
		return true;
	}

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	{
		if(((IItemEnergyEstorage)par1ItemStack.getItem()).getEnergy(par1ItemStack) > 0){
			if (par2Block.blockID == Block.web.blockID)
			{
				return 15.0F;
			}
			else
			{
				Material material = par2Block.blockMaterial;
				return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.pumpkin ? 1.0F : 1.5F;
			}
		}return 1f;
	}

	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, int par3, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase)
	{
		if ((double)Block.blocksList[par3].getBlockHardness(par2World, par4, par5, par6) != 0.0D)
		{
			if(((IItemEnergyEstorage)par1ItemStack.getItem()).getEnergy(par1ItemStack) >= 100){
				((IItemEnergyEstorage)par1ItemStack.getItem()).gainEnergy(par1ItemStack, -100);
			}else{
				return false;
			}
		}

		return true;
	}
}
