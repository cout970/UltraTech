package common.cout970.UltraTech.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import common.cout970.UltraTech.items.AutoEjectUpgrade;
import common.cout970.UltraTech.items.FortuneUpgrade;
import common.cout970.UltraTech.items.HidrogenBattery;
import common.cout970.UltraTech.items.LasserSword;
import common.cout970.UltraTech.items.Linker;
import common.cout970.UltraTech.items.MiningUpgrade;
import common.cout970.UltraTech.items.ProcesedFood;
import common.cout970.UltraTech.items.RadioniteCell;
import common.cout970.UltraTech.items.RangeUpgrade;
import common.cout970.UltraTech.items.SpeedUpgrade;
import common.cout970.UltraTech.items.UT_Dust;
import common.cout970.UltraTech.items.UT_Ingot;
import common.cout970.UltraTech.items.UT_Item;
import common.cout970.UltraTech.items.UT_Plate;
import common.cout970.UltraTech.misc.ItemInfo;
import common.cout970.UltraTech.misc.ItemInfo.ItemTipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemManager {

	public static List<ItemInfo> info = new ArrayList<ItemInfo>();
	public static List<Item> Items = new ArrayList<Item>();
	public static Map<String,Item> ItemName = new HashMap<String,Item>();

	public static void Registry(){

		//ingot
		Reg("Ingot", "Ingot", false);
		
		//dust
		Reg("Dust", "Dust", false);

		//plates
		Reg("Plate", "Plate", false);
		
		Reg("GrafenoPlate", "Grafeno Plate", true);
		
		//pieces
		Reg("HeatCoil", "Heat Coil", true);
		Reg("RawSilicon", "Raw Silicon", true);
		Reg("Radionite", "Radionite", true);
		Reg("RawMeal", "Raw Meal", true);
		Reg("SolarPanel", "Solar Panel", true);
		Reg("Fan", "Fan", true);
		Reg("Coil", "Coil", true);
		Reg("AdvCircuit", "Advanced Circuit", true);
		Reg("Circuit", "Circuit", true);
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
		Reg("HidrogenBattery", "Hidrogen Battery", false);
		Reg("LasserSword", "Lasser Sword", false);
		Reg("ProcesedFood", "Procesed Food", false);
		
	}

	public static void LoadConfigItems(Configuration c){

		Registry();

		int n = 5020;
		for(ItemInfo i:info){
			i.id = c.getItem(i.GameName, n).getInt();
			n++;
		}
	}

	public static void RegisterItems(){

		for(ItemInfo i:info){
			Item it;
			if(i.tipe == ItemTipe.DEFAULT){
				it = new UT_Item(i.id, i.name);
			}else{
				it = Exception(i);
			}
			if(it == null)System.out.println(i.name);
			GameRegistry.registerItem(it, i.name+"_UT");
			LanguageRegistry.addName(it, i.GameName);
			ItemName.put(i.name, it);
		}
	}

	private static Item Exception(ItemInfo i) {
		String a = i.name;
		if(a=="Ingot")
			return new UT_Ingot(i.id);
		if(a=="Dust")
			return new UT_Dust(i.id);
		if(a=="Plate")
			return new UT_Plate(i.id);
		if(a=="SpeedUpgrade")
			return new SpeedUpgrade(i.id, i.name);
		if(a=="MiningUpgrade")
			return new MiningUpgrade(i.id, i.name);
		if(a=="RangeUpgrade")
			return new RangeUpgrade(i.id, i.name);
		if(a=="AutoEjectUpgrade")
			return new AutoEjectUpgrade(i.id, i.name);
		if(a=="Linker")
			return new Linker(i.id, i.name);
		if(a=="RadioniteCell")
			return new RadioniteCell(i.id, i.name);
		if(a=="HidrogenBattery")
			return new HidrogenBattery(i.id, i.name);
		if(a=="LasserSword")
			return new LasserSword(i.id, i.name);
		if(a=="FortuneUpgrade")
			return new FortuneUpgrade(i.id, i.name);
		if(a=="ProcesedFood")
			return new ProcesedFood(i.id);
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
