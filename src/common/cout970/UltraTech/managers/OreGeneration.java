package common.cout970.UltraTech.managers;

import net.minecraftforge.common.Configuration;

public class OreGeneration {

	//generate ore or not
	public static boolean Aluminum = true;
	public static boolean Copper = true;
	public static boolean Tin = true;
	public static boolean Silver = true;
	public static boolean Lead = true;
	//n of veins per chunk
	public static int unitsAluminum = 20;
	public static int unitsCopper = 15;
	public static int unitsTin = 10;
	public static int unitsLead = 6;//silver and lead go together
	//ore amount per vein
	public static int amountAluminum = 8;
	public static int amountCopper = 6;
	public static int amountTin = 4;
	public static int amountSilver = 4;
	public static int amountLead = 5;
	//max heigt generate
	public static int heightAluminum = 100;
	public static int heightCopper = 80;
	public static int heightTin = 64;
	public static int heightLead = 20;//silver and lead go together
	
	public static void readConfigOre(Configuration config){
		Aluminum = config.get(Configuration.CATEGORY_GENERAL, "Generate Aluminum Ore", true).getBoolean(true);
		Copper = config.get(Configuration.CATEGORY_GENERAL, "Generate Copper Ore", true).getBoolean(true);
		Tin = config.get(Configuration.CATEGORY_GENERAL, "Generate Tin Ore", true).getBoolean(true);
		Silver = config.get(Configuration.CATEGORY_GENERAL, "Generate Silver Ore", true).getBoolean(true);
		Lead = config.get(Configuration.CATEGORY_GENERAL, "Generate Lead Ore", true).getBoolean(true);
		
		unitsAluminum = config.get(Configuration.CATEGORY_GENERAL, "Number of veins of Aluminum per chunk", 20).getInt();
		unitsCopper = config.get(Configuration.CATEGORY_GENERAL, "Number of veins of Copper per chunk", 15).getInt();
		unitsTin = config.get(Configuration.CATEGORY_GENERAL, "Number of veins of Tin per chunk", 10).getInt();
		unitsLead = config.get(Configuration.CATEGORY_GENERAL, "Number of veins of Lead and Silver per chunk", 6).getInt();
		
		amountAluminum = config.get(Configuration.CATEGORY_GENERAL, "Amount of Aluminum ores per vein", 8).getInt();
		amountCopper = config.get(Configuration.CATEGORY_GENERAL, "Amount of Copper ores per vein", 6).getInt();
		amountTin = config.get(Configuration.CATEGORY_GENERAL, "Amount of Tin ores per vein", 4).getInt();
		amountSilver = config.get(Configuration.CATEGORY_GENERAL, "Amount of Silver ores per vein", 4).getInt();
		amountLead = config.get(Configuration.CATEGORY_GENERAL, "Amount of Lead ores per vein", 5).getInt();

		heightAluminum = config.get(Configuration.CATEGORY_GENERAL, "Generating maximum height Aluminum", 100).getInt();
		heightCopper = config.get(Configuration.CATEGORY_GENERAL, "Generating maximum height Copper", 80).getInt();
		heightTin = config.get(Configuration.CATEGORY_GENERAL, "Generating maximum height Tin", 64).getInt();
		heightAluminum = config.get(Configuration.CATEGORY_GENERAL, "Generating maximum height Lead and Silver", 20).getInt();

	}
}
