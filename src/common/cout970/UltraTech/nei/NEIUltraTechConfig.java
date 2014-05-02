package common.cout970.UltraTech.nei;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import common.cout970.UltraTech.lib.Reference;
import common.cout970.UltraTech.machines.gui.CrafterGui;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIUltraTechConfig implements IConfigureNEI{

	@Override
	public void loadConfig() {
		API.registerRecipeHandler(new PurifierCrafting());
		API.registerRecipeHandler(new CuterCrafting());
		API.registerRecipeHandler(new CVD_Crafting());
		API.registerRecipeHandler(new AssemblyCrafting());
		API.registerUsageHandler(new PurifierCrafting());
		API.registerUsageHandler(new CuterCrafting());
		API.registerUsageHandler(new CVD_Crafting());
		API.registerUsageHandler(new AssemblyCrafting());
		API.registerUsageHandler(new FermenterCrafting());
		API.registerGuiOverlayHandler(CrafterGui.class, new CrafterOverlayHandler(), "crafting");
	}

	@Override
	public String getName() {
		return "UltraTech plugin";
	}

	@Override
	public String getVersion() {
		return Reference.NeiPluginVersion;
	}
	
	public static boolean matches(ItemStack a,ItemStack b){
		if(a == null && b == null)return true;
		if(a == null || b == null)return false;
		if(a.itemID == b.itemID){
			if(a.getItemDamage() == b.getItemDamage()){
				return true;
			}
		}else{
			if(OreDictionary.itemMatches(a, b, true))return true;
		}
		return false;
	}

}
