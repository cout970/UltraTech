package common.cout970.UltraTech.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
//import net.minecraftforge.common.Configuration;
import common.cout970.UltraTech.items.ItemAutoEjectUpgrade;
import common.cout970.UltraTech.items.ItemBottle;
import common.cout970.UltraTech.items.ItemDebugger;
import common.cout970.UltraTech.items.ItemFlamethrower;
import common.cout970.UltraTech.items.ItemFortuneUpgrade;
import common.cout970.UltraTech.items.ItemBattery;
import common.cout970.UltraTech.items.ItemLaserSword;
import common.cout970.UltraTech.items.ItemLinker;
import common.cout970.UltraTech.items.ItemMetalPlate;
import common.cout970.UltraTech.items.ItemMiningUpgrade;
import common.cout970.UltraTech.items.ItemPlutoniumCell;
import common.cout970.UltraTech.items.ItemProcesedFood;
import common.cout970.UltraTech.items.ItemRadioniteCell;
import common.cout970.UltraTech.items.ItemRangeUpgrade;
import common.cout970.UltraTech.items.ItemSilkTouchUpgrade;
import common.cout970.UltraTech.items.ItemSpeedUpgrade;
import common.cout970.UltraTech.items.ItemTablet;
import common.cout970.UltraTech.items.ItemThoriumCell;
import common.cout970.UltraTech.items.ItemChunkUT;
import common.cout970.UltraTech.items.ItemDustUT;
import common.cout970.UltraTech.items.ItemIngotUT;
import common.cout970.UltraTech.items.ItemUT;
import common.cout970.UltraTech.items.ItemPlateUT;
import common.cout970.UltraTech.items.ItemUnorganicPlate;
import common.cout970.UltraTech.items.ItemUraniumCell;
import common.cout970.UltraTech.misc.ItemInfo;
import common.cout970.UltraTech.misc.ItemInfo.ItemTipe;
import common.cout970.UltraTech.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemManager {

	public static List<ItemInfo> info = new ArrayList<ItemInfo>();
	public static List<Item> UT_Items = new ArrayList<Item>();
	public static Map<String,Item> ItemName = new HashMap<String,Item>();
	
	public static void InitItems(){

		//ingot
		Reg("Ingot", "Ingot", false);
		
		//dust
		Reg("Dust", "Dust", false);
		Reg("Chunk", "Chunk", false);
		
		//plates
		Reg("MetalPlate", "Metal Plate", false);
		Reg("UnorganicPlate", "Unorganic Plate", false);
		Reg("GrafenoPlate", "Grafeno Plates", true);
		
		//pieces
		Reg("HeatCoil", "Heat Coil", true);
		Reg("RawSilicon", "Raw Silicon", true);
		Reg("Radionite", "Radionite", true);
		Reg("RawMeal", "Raw Meal", true);
		Reg("Fan", "Fan", true);
		Reg("Coil", "Coil", true);
		Reg("Motor", "Motor", true);
		
		//upgrades
		Reg("UpgradeBase", "Upgrade Base", true);
		Reg("SpeedUpgrade", "Speed Upgrade", false);
		Reg("MiningUpgrade", "Mining Upgrade", false);
		Reg("RangeUpgrade", "Range Upgrade", false);
		Reg("AutoEjectUpgrade", "AutoEject Upgrade", false);
		Reg("FortuneUpgrade", "Fortune Upgrade", false);
		
		//other
		Reg("Linker", "Linker", false);
		Reg("RadioniteCell", "Radionite Cell", false);
		Reg("Battery", "Battery", false);
		Reg("LaserSword", "Laser Sword", false);
		Reg("ProcesedFood", "Procesed Food", false);
		Reg("Tablet", "Tablet", false);
		
		
		//added
		Reg("SilverCable", "Silver cable",true);
		Reg("SilkTouchUpgrade", "Silk Touch Upgrade", false);
		Reg("Sulfur", "Sulfur", true);
		Reg("SulfuricAcid", "Sulfuric Acid", true);
		Reg("ElectricCell", "Electric Cell", true);
		Reg("MediumElectricCell", "Medium Electric Cell", true);
		Reg("BigElectricCell", "Big Electric Cell", true);
		Reg("Rubber","Rubber",true);
		Reg("Bottle","Bottle",false);
		Reg("Plastic","Plastic",true);
		Reg("Rubber_vulcanized","Vulcanized Rubber",true);
		Reg("Dynamo","Dynamo",true);
		Reg("AluminumGear","Aluminum Gear",true);
		Reg("UraniumCell", "Uranium Cell", false);
		Reg("ThoriumCell", "Thorium Cell", false);
		Reg("PlutoniumCell", "Plutonium Cell", false);
		Reg("Flamethrower", "Flamethrower", false);
		Reg("LogicCircuit", "Logic Circuit", true);
		Reg("ElectricCircuit", "Electric Circuit", true);
		Reg("OpticCircuit", "Optic Circuit", true);
		Reg("OpticCable", "Optic Cable", true);
		Reg("Cell", "Empty Cell", true);
		Reg("Debugger", "Debugger", false);
	}

	public static void RegisterItems(){

		for(ItemInfo i:info){
			Item it;
			if(i.tipe == ItemTipe.DEFAULT){
				it = new ItemUT(i.name);
			}else{
				it = Exception(i);
			}
			if(it == null)LogHelper.info("Found exception with item "+i.name+" in mod Ultratech, please report this to the mod autor (cout970)");
			GameRegistry.registerItem(it, i.name+"_UT");
			LangException(it, i);
			ItemName.put(i.name, it);
		}
	}

	private static void LangException(Item it, ItemInfo i) {
		if(i.name == "Chunk"){
			for(int meta = 0;meta < ItemChunkUT.names.length;meta++)Language.addName(new ItemStack(it,1,meta), "Dirty "+ItemChunkUT.names[meta]);
		}else if(i.name == "MetalPlate"){
			for(int meta = 0;meta < ItemMetalPlate.names.length;meta++)Language.addName(new ItemStack(it,1,meta), ItemMetalPlate.names[meta]+" Plate");
		}else if(i.name == "UnorganicPlate")
			for(int meta = 0;meta < ItemUnorganicPlate.names.length;meta++){Language.addName(new ItemStack(it,1,meta), ItemUnorganicPlate.names[meta]+" Plate");
		}else if(i.name == "Ingot"){
			for(int meta = 0;meta < ItemIngotUT.names.length;meta++)Language.addName(new ItemStack(it,1,meta), ItemIngotUT.names[meta]);
		}else if(i.name == "Dust"){
			for(int meta = 0;meta < ItemDustUT.names.length;meta++)Language.addName(new ItemStack(it,1,meta), ItemDustUT.names[meta]);
		}else if(i.name == "Bottle"){
			Language.addName(new ItemStack(it,1), "Bottle");
		}else{ 
			Language.addName(it, i.GameName);
		}
	}

	private static Item Exception(ItemInfo i) {
		String a = i.name;
		if(a=="Bottle")
			return new ItemBottle();
		if(a=="Ingot")
			return new ItemIngotUT();
		if(a=="Chunk")
			return new ItemChunkUT();
		if(a=="Dust")
			return new ItemDustUT();
		if(a=="MetalPlate")
			return new ItemMetalPlate();
		if(a=="UnorganicPlate")
			return new ItemUnorganicPlate();
		if(a=="SpeedUpgrade")
			return new ItemSpeedUpgrade(i.name);
		if(a=="MiningUpgrade")
			return new ItemMiningUpgrade(i.name);
		if(a=="RangeUpgrade")
			return new ItemRangeUpgrade(i.name);
		if(a=="AutoEjectUpgrade")
			return new ItemAutoEjectUpgrade(i.name);
		if(a=="Linker")
			return new ItemLinker(i.name);
		if(a=="RadioniteCell")
			return new ItemRadioniteCell(i.name);
		if(a=="UraniumCell")
			return new ItemUraniumCell(i.name);
		if(a=="ThoriumCell")
			return new ItemThoriumCell(i.name);
		if(a=="PlutoniumCell")
			return new ItemPlutoniumCell(i.name);
		if(a=="Battery")
			return new ItemBattery(i.name);
		if(a=="LaserSword")
			return new ItemLaserSword(i.name);
		if(a=="FortuneUpgrade")
			return new ItemFortuneUpgrade(i.name);
		if(a=="ProcesedFood")
			return new ItemProcesedFood();
		if(a=="SilkTouchUpgrade")
			return new ItemSilkTouchUpgrade(i.name);
		if(a=="Tablet")
			return new ItemTablet(i.name);
		if(a=="Flamethrower")
			return new ItemFlamethrower();
		if(a=="Debugger")
			return new ItemDebugger("Debugger");
		return null;
	}


	public static void Reg(String name,String lang,boolean regular){
		ItemInfo inf = new ItemInfo();
		inf.name = name;
		inf.GameName = lang;
		if(regular){
			inf.tipe = ItemTipe.DEFAULT;
		}else{
			inf.tipe = ItemTipe.TOOL;
		}
		info.add(inf);
	}

}
