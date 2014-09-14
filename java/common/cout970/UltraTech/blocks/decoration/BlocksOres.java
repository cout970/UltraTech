package common.cout970.UltraTech.blocks.decoration;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.itemBlock.ItemBlock_Ores;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.managers.UT_Tabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlocksOres extends Block{

	private IIcon[] blockIcons;
	
	public BlocksOres(Material par2Material) {
		super(par2Material);
		setCreativeTab(UT_Tabs.ResourceTab);
		setHardness(1.0f);
		setStepSound(soundTypeStone);
		setResistance(30);
		setBlockName("OreBlock");
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		blockIcons = new IIcon[7];
		blockIcons[0] = iconRegister.registerIcon(Block_Textures.RADIONITE_ORE);
		blockIcons[1] = iconRegister.registerIcon(Block_Textures.ALUMINUM_ORE);
		blockIcons[2] = iconRegister.registerIcon(Block_Textures.COPPER_ORE);
		blockIcons[3] = iconRegister.registerIcon(Block_Textures.TIN_ORE);
		blockIcons[4] = iconRegister.registerIcon(Block_Textures.LEAD_ORE);
		blockIcons[5] = iconRegister.registerIcon(Block_Textures.SILVER_ORE);
		blockIcons[6] = iconRegister.registerIcon(Block_Textures.SULFUR_ORE);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2){		
		if(par2 >= blockIcons.length)return blockIcons[0];
        return blockIcons[par2];
    }

	public Item getItemDropped(int a, Random b, int c){
		if(a != 6)return super.getItemDropped(a, b, c);
		return ItemManager.ItemName.get("Sulfur");
	}
	
	public int quantityDropped(Random r){
        return 1;
    }
	
	 public int quantityDroppedWithBonus(int a, Random r)
	    {
	        if (a > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, r, a)){
	            int j = r.nextInt(a + 2) - 1;
	            if (j < 0){
	                j = 0;
	            }
	            return this.quantityDropped(r) * (j + 1);
	        }else{
	            return this.quantityDropped(r);
	        }
	    }

	    public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_)
	    {
	        super.dropBlockAsItemWithChance(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, p_149690_5_, p_149690_6_, p_149690_7_);
	    }

	    private Random rand = new Random();
	    @Override
	    public int getExpDrop(IBlockAccess a, int b, int c)
	    {
	    	if (this.getItemDropped(b, rand, c) != Item.getItemFromBlock(this)){
	    		int j1 = 0;
	    		j1 = MathHelper.getRandomIntegerInRange(rand, 2, 5);
	    		return j1;
	    	}
	    	return 0;
	    }
	
	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems)
    {
		for (int ix = 0; ix < ItemBlock_Ores.subNames.length; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
	
	@Override
	public int damageDropped (int metadata) {
		if(metadata == 6)return 0;
		return metadata;
	}
	
	@Override
	public int getDamageValue(World par1World, int par2, int par3, int par4) {
		return par1World.getBlockMetadata(par2, par3, par4);
	}
}
