package common.cout970.UltraTech.managers;


import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CompatibilityManager {
		
	public static void initCompatibilitys(){
		OreDictionary();
		
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
		OreDictionary.registerOre("ingotAlloy_UT", new ItemStack(ItemManager.ItemName.get("Ingot"),1,5));
		//dust
		OreDictionary.registerOre("dustAluminum", new ItemStack(ItemManager.ItemName.get("Dust"),1,0));
		OreDictionary.registerOre("dustAluminium", new ItemStack(ItemManager.ItemName.get("Dust"),1,0));
		OreDictionary.registerOre("dustCopper", new ItemStack(ItemManager.ItemName.get("Dust"),1,1));
		OreDictionary.registerOre("dustTin", new ItemStack(ItemManager.ItemName.get("Dust"),1,2));
		OreDictionary.registerOre("dustLead", new ItemStack(ItemManager.ItemName.get("Dust"),1,3));
		OreDictionary.registerOre("dustSilver", new ItemStack(ItemManager.ItemName.get("Dust"),1,4));
		OreDictionary.registerOre("dustAlloy_UT", new ItemStack(ItemManager.ItemName.get("Dust"),1,5));
		OreDictionary.registerOre("dustIron", new ItemStack(ItemManager.ItemName.get("Dust"),1,6));
		OreDictionary.registerOre("dustGold", new ItemStack(ItemManager.ItemName.get("Dust"),1,7));
		OreDictionary.registerOre("dustDiamond", new ItemStack(ItemManager.ItemName.get("Dust"),1,8));
		//plates
		OreDictionary.registerOre("plateAluminum", new ItemStack(ItemManager.ItemName.get("Plate"),1,0));
		OreDictionary.registerOre("plateAluminium", new ItemStack(ItemManager.ItemName.get("Plate"),1,0));
		OreDictionary.registerOre("plateCopper", new ItemStack(ItemManager.ItemName.get("Plate"),1,1));
		OreDictionary.registerOre("plateTin", new ItemStack(ItemManager.ItemName.get("Plate"),1,2));
		OreDictionary.registerOre("plateLead", new ItemStack(ItemManager.ItemName.get("Plate"),1,3));
		OreDictionary.registerOre("plateSilver", new ItemStack(ItemManager.ItemName.get("Plate"),1,4));
		OreDictionary.registerOre("plateAlloy_UT", new ItemStack(ItemManager.ItemName.get("Plate"),1,5));
		OreDictionary.registerOre("plateIron", new ItemStack(ItemManager.ItemName.get("Plate"),1,6));
		OreDictionary.registerOre("plateGold", new ItemStack(ItemManager.ItemName.get("Plate"),1,7));
		OreDictionary.registerOre("plateDiamond", new ItemStack(ItemManager.ItemName.get("Plate"),1,8));
		OreDictionary.registerOre("plateGrafeno", new ItemStack(ItemManager.ItemName.get("Plate"),1,9));
		OreDictionary.registerOre("plateSilicon", new ItemStack(ItemManager.ItemName.get("Plate"),1,10));
		OreDictionary.registerOre("plateRedstone", new ItemStack(ItemManager.ItemName.get("Plate"),1,11));
		OreDictionary.registerOre("plateRadionite", new ItemStack(ItemManager.ItemName.get("Plate"),1,12));
	}



}
