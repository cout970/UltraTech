package common.cout970.UltraTech.items;

import java.util.List;

import ultratech.api.power.IStorageItem;
import ultratech.api.power.ItemPower;

import com.google.common.collect.Multimap;

import common.cout970.UltraTech.managers.UT_Tabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class LaserSword extends ItemPower{
	

	public LaserSword(String name){
		super(16000);
		setCreativeTab(UT_Tabs.ResourceTab);
		setUnlocalizedName("LaserSword");
	}

	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("ultratech:cable");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Multimap getAttributeModifiers(ItemStack a)
	{
		Multimap multimap = super.getAttributeModifiers(a);
		if(((IStorageItem)a.getItem()).getPower(a) > 0)
			multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 20d, 0));
		return multimap;
	}


	public boolean hitEntity(ItemStack i, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
	{
		if(((IStorageItem)i.getItem()).getPower(i) >= 500){
			((IStorageItem)i.getItem()).removePower(i, 500);
		}else{
			return false;
		}
		return true;
	}

	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	{
		if(((IStorageItem)par1ItemStack.getItem()).getPower(par1ItemStack) > 0){
			if (Block.isEqualTo(par2Block, Blocks.web)){
				return 15.0F;
			}else{
				Material material = par2Block.getMaterial();
				return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves  ? 1.0F : 1.5F;
			}
		}return 1f;
	}

	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, Block par3, int par4, int par5, int par6, EntityLivingBase par7EntityLivingBase)
	{
		if ((double)par3.getBlockHardness(par2World, par4, par5, par6) != 0.0D)
		{
			if(((IStorageItem)par1ItemStack.getItem()).getPower(par1ItemStack) >= 100){
				((IStorageItem)par1ItemStack.getItem()).removePower(par1ItemStack, 100);
			}else{
				return false;
			}
		}

		return true;
	}
	
	public int getItemEnchantability()
    {
        return 22;
    }
}
