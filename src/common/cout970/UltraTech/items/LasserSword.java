package common.cout970.UltraTech.items;

import com.google.common.collect.Multimap;
import common.cout970.UltraTech.core.UltraTech;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;

public class LasserSword extends ItemSword{


	public LasserSword(int par1) {
		super(par1,EnumToolMaterial.EMERALD);
		setCreativeTab(UltraTech.techTab);
		setMaxStackSize(1);
		setUnlocalizedName("LasserSword");
		setTextureName("LasserSword");
	}

	public String getToolMaterialName()
	{
		return "Lasser";
	}

	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("ultratech:lassersword");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Multimap getItemAttributeModifiers()
	{
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 6d, 0));
		return multimap;
	}



}
