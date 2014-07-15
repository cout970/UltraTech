package common.cout970.UltraTech.recipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.cout970.UltraTech.TileEntities.electric.MolecularAssemblyEntity;
import common.cout970.UltraTech.util.UT_Utils;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Assembly_Recipes {

	public static List<Assembly_Recipes> recipes = new ArrayList<Assembly_Recipes>();
	protected final ItemStack[] imput;
	protected final ItemStack output;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Assembly_Recipes(ItemStack output,Object... params){
		if(output.stackSize > 1)output.stackSize = 1;
		this.output = output;
		String craft = "";
		int i = 0;

		if(params[i] instanceof String[]){
			String[] astring = (String[])((String[])params[i++]);

			for (int l = 0; l < astring.length; ++l)
			{
				String s1 = astring[l];
				craft = craft + s1;
			}
		}else{
			while (params[i] instanceof String)
			{
				String s2 = (String)params[i++];
				craft = craft + s2;
			}
		}

		HashMap hashmap;
		
		for (hashmap = new HashMap(); i < params.length; i += 2)
        {
            Character character = (Character)params[i];
            ItemStack itemstack1 = null;

            if (params[i + 1] instanceof Item)
            {
                itemstack1 = new ItemStack((Item)params[i + 1]);
            }
            else if (params[i + 1] instanceof Block)
            {
                itemstack1 = new ItemStack((Block)params[i + 1], 1, 32767);
            }
            else if (params[i + 1] instanceof ItemStack)
            {
                itemstack1 = (ItemStack)params[i + 1];
            }

            hashmap.put(character, itemstack1);
        }

        ItemStack[] aitemstack = new ItemStack[9];

        for (int i1 = 0; i1 < 9; ++i1)
        {
            char c0 = craft.charAt(i1);

            if (hashmap.containsKey(Character.valueOf(c0)))
            {
                aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).copy();
            }
            else
            {
                aitemstack[i1] = null;
            }
        }
        imput = aitemstack;

	}
	

	public static boolean matches(MolecularAssemblyEntity e){
		if(e == null)return false;
		for(Assembly_Recipes a : recipes){
			if(equals(e.getStackInSlot(0), a.imput[0])){
				if(equals(e.getStackInSlot(1), a.imput[1])){
					if(equals(e.getStackInSlot(2), a.imput[2])){
						if(equals(e.getStackInSlot(3), a.imput[3])){
							if(equals(e.getStackInSlot(4), a.imput[4])){
								if(equals(e.getStackInSlot(5), a.imput[5])){
									if(equals(e.getStackInSlot(6), a.imput[6])){
										if(equals(e.getStackInSlot(7), a.imput[7])){
											if(equals(e.getStackInSlot(8), a.imput[8])){
												return true;
											}	
										}	
									}	
								}	
							}	
						}	
					}	
				}
			}
		}
		return false;
	}
	
	public static ItemStack getCraftingResult(MolecularAssemblyEntity e){
		if(e == null)return null;
			for(Assembly_Recipes a:recipes){
				if(equals(e.getStackInSlot(0), a.imput[0])){
					if(equals(e.getStackInSlot(1), a.imput[1])){
						if(equals(e.getStackInSlot(2), a.imput[2])){
							if(equals(e.getStackInSlot(3), a.imput[3])){
								if(equals(e.getStackInSlot(4), a.imput[4])){
									if(equals(e.getStackInSlot(5), a.imput[5])){
										if(equals(e.getStackInSlot(6), a.imput[6])){
											if(equals(e.getStackInSlot(7), a.imput[7])){
												if(equals(e.getStackInSlot(8), a.imput[8])){
													return a.output;
												}	
											}	
										}	
									}	
								}	
							}	
						}	
					}
				}
		}
		return null;
	}
	
	public ItemStack getInput(int n) {
		return imput[n];
	}

	public ItemStack getOutput() {
		return output;
	}
	
	public static boolean equals(ItemStack a,ItemStack b){
		if(UT_Utils.areEcuals(a, b, true))return true;
		return false;
	}
	
	public static void addRecipe(Assembly_Recipes a){
		if(!recipes.contains(a))
			recipes.add(a);
	}

	public static ItemStack getResult(ItemStack itemstack) {
		if(itemstack != null)return null;
			for(Assembly_Recipes a:recipes){
				for(int x=0; x<9; x++){
					if(a.imput[x] != null){
						if(OreDictionary.itemMatches(itemstack, a.imput[x], true)){
							return a.getOutput();
						}
					}
				}
		}
		return null;
	}


}
