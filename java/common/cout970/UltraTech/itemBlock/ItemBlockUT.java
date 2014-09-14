package common.cout970.UltraTech.itemBlock;


import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockUT extends ItemBlock{

	private String name;

	public ItemBlockUT(Block b,String name) {
		super(b);
		setHasSubtypes(true);
		this.name = name;
	}

	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." +name+itemstack.getItemDamage();
	}

}
