package common.cout970.UltraTech.managers;


import common.cout970.UltraTech.util.LogHelper;

import buildcraft.api.fuels.IronEngineFuel;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CompatibilityManager {
		
	public static void initCompatibilitys(){
		OreDictionary();
		BuildcraftFuel();
	}
	
	private static void BuildcraftFuel() {
		IronEngineFuel.addFuel("gasoline", 10, 25000);
		IronEngineFuel.addFuel("bioethanol", 4, 15000);
		if(!Loader.isModLoaded("BuildCraft")){
			LogHelper.log("Buildcraft not loaded");
			IronEngineFuel.addFuel("oil", 3, 5000);
			IronEngineFuel.addFuel("fuel", 6, 25000);
		}
	}

	public static void OreDictionary(){
		//ores
		OreDictionary.registerOre("oreRadionite", new ItemStack(BlockManager.Ores,1,0));
		OreDictionary.registerOre("oreAluminum", new ItemStack(BlockManager.Ores,1,1));
		OreDictionary.registerOre("oreAluminium", new ItemStack(BlockManager.Ores,1,1));
		OreDictionary.registerOre("oreCopper", new ItemStack(BlockManager.Ores,1,2));
		OreDictionary.registerOre("oreTin", new ItemStack(BlockManager.Ores,1,3));
		OreDictionary.registerOre("oreLead", new ItemStack(BlockManager.Ores,1,4));
		OreDictionary.registerOre("oreSilver", new ItemStack(BlockManager.Ores,1,5));
		//ingots
		OreDictionary.registerOre("ingotAluminum", new ItemStack(ItemManager.ItemName.get("Ingot"),1,0));
		OreDictionary.registerOre("ingotAluminium", new ItemStack(ItemManager.ItemName.get("Ingot"),1,0));
		OreDictionary.registerOre("ingotCopper", new ItemStack(ItemManager.ItemName.get("Ingot"),1,1));
		OreDictionary.registerOre("ingotTin", new ItemStack(ItemManager.ItemName.get("Ingot"),1,2));
		OreDictionary.registerOre("ingotLead", new ItemStack(ItemManager.ItemName.get("Ingot"),1,3));
		OreDictionary.registerOre("ingotSilver", new ItemStack(ItemManager.ItemName.get("Ingot"),1,4));
		//dust
		OreDictionary.registerOre("dustAluminum", new ItemStack(ItemManager.ItemName.get("Dust"),1,0));
		OreDictionary.registerOre("dustAluminium", new ItemStack(ItemManager.ItemName.get("Dust"),1,0));
		OreDictionary.registerOre("dustCopper", new ItemStack(ItemManager.ItemName.get("Dust"),1,1));
		OreDictionary.registerOre("dustTin", new ItemStack(ItemManager.ItemName.get("Dust"),1,2));
		OreDictionary.registerOre("dustLead", new ItemStack(ItemManager.ItemName.get("Dust"),1,3));
		OreDictionary.registerOre("dustSilver", new ItemStack(ItemManager.ItemName.get("Dust"),1,4));
		OreDictionary.registerOre("dustObalti", new ItemStack(ItemManager.ItemName.get("Dust"),1,5));
		OreDictionary.registerOre("dustIron", new ItemStack(ItemManager.ItemName.get("Dust"),1,6));
		OreDictionary.registerOre("dustGold", new ItemStack(ItemManager.ItemName.get("Dust"),1,7));
		OreDictionary.registerOre("dustDiamond", new ItemStack(ItemManager.ItemName.get("Dust"),1,8));
		OreDictionary.registerOre("dustObsidian", new ItemStack(ItemManager.ItemName.get("Dust"),1,9));
		//plates
		OreDictionary.registerOre("plateAluminum", new ItemStack(ItemManager.ItemName.get("MetalPlate"),1,0));
		OreDictionary.registerOre("plateAluminium", new ItemStack(ItemManager.ItemName.get("MetalPlate"),1,0));
		OreDictionary.registerOre("plateCopper", new ItemStack(ItemManager.ItemName.get("MetalPlate"),1,1));
		OreDictionary.registerOre("plateTin", new ItemStack(ItemManager.ItemName.get("MetalPlate"),1,2));
		OreDictionary.registerOre("plateLead", new ItemStack(ItemManager.ItemName.get("MetalPlate"),1,3));
		OreDictionary.registerOre("plateSilver", new ItemStack(ItemManager.ItemName.get("MetalPlate"),1,4));
		OreDictionary.registerOre("plateIron", new ItemStack(ItemManager.ItemName.get("MetalPlate"),1,5));
		OreDictionary.registerOre("plateGold", new ItemStack(ItemManager.ItemName.get("MetalPlate"),1,6));
		OreDictionary.registerOre("plateObalti", new ItemStack(ItemManager.ItemName.get("MetalPlate"),1,7));
		
		OreDictionary.registerOre("plateDiamond", new ItemStack(ItemManager.ItemName.get("UnorganicPlate"),1,0));
		OreDictionary.registerOre("plateGrafeno", new ItemStack(ItemManager.ItemName.get("UnorganicPlate"),1,1));
		OreDictionary.registerOre("plateSilicon", new ItemStack(ItemManager.ItemName.get("UnorganicPlate"),1,2));
		OreDictionary.registerOre("plateRedstone", new ItemStack(ItemManager.ItemName.get("UnorganicPlate"),1,3));
		OreDictionary.registerOre("plateRadionite", new ItemStack(ItemManager.ItemName.get("UnorganicPlate"),1,4));
		OreDictionary.registerOre("plateObsidian", new ItemStack(ItemManager.ItemName.get("UnorganicPlate"),1,5));
		
		OreDictionary.registerOre("dustSulfur", new ItemStack(ItemManager.ItemName.get("Sulfur")));
		OreDictionary.registerOre("rawRubber", new ItemStack(ItemManager.ItemName.get("Rubber")));
		OreDictionary.registerOre("itemPlastic", new ItemStack(ItemManager.ItemName.get("Plastic")));
		OreDictionary.registerOre("itemRubber", new ItemStack(ItemManager.ItemName.get("Rubber_bulcanized")));
	}



}
