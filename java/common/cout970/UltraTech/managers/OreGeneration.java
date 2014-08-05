package common.cout970.UltraTech.managers;

import net.minecraftforge.common.config.Configuration;


public class OreGeneration {

	public static String Ore = "Ore Generation";
	
	//generate ore or not
	public static boolean Aluminum = true;
	public static boolean Copper = true;
	public static boolean Tin = true;
	public static boolean Silver = true;
	public static boolean Lead = true;
	public static boolean Radionite = true;
	public static boolean Sulfur = true;
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
		Aluminum = config.get(Ore, "Generate Aluminum Ore", true).getBoolean(true);
		Copper = config.get(Ore, "Generate Copper Ore", true).getBoolean(true);
		Tin = config.get(Ore, "Generate Tin Ore", true).getBoolean(true);
		Silver = config.get(Ore, "Generate Silver Ore", true).getBoolean(true);
		Lead = config.get(Ore, "Generate Lead Ore", true).getBoolean(true);
		Radionite = config.get(Ore, "Generate Radionite Ore", true).getBoolean(true);
		Sulfur = config.get(Ore, "Generate Sulfur Ore", true).getBoolean(true);
		
		unitsAluminum = config.get(Ore, "Number of veins of Aluminum per chunk", 20).getInt();
		unitsCopper = config.get(Ore, "Number of veins of Copper per chunk", 15).getInt();
		unitsTin = config.get(Ore, "Number of veins of Tin per chunk", 10).getInt();
		unitsLead = config.get(Ore, "Number of veins of Lead and Silver per chunk", 6).getInt();
		
		amountAluminum = config.get(Ore, "Amount of Aluminum ores per vein", 8).getInt();
		amountCopper = config.get(Ore, "Amount of Copper ores per vein", 6).getInt();
		amountTin = config.get(Ore, "Amount of Tin ores per vein", 4).getInt();
		amountSilver = config.get(Ore, "Amount of Silver ores per vein", 4).getInt();
		amountLead = config.get(Ore, "Amount of Lead ores per vein", 5).getInt();

		heightAluminum = config.get(Ore, "Generating maximum height Aluminum", 100).getInt();
		heightCopper = config.get(Ore, "Generating maximum height Copper", 80).getInt();
		heightTin = config.get(Ore, "Generating maximum height Tin", 64).getInt();
		heightAluminum = config.get(Ore, "Generating maximum height Lead and Silver", 20).getInt();
	}
	
	public static int OilBase;
	public static int OilDesert;
	public static int OilOcean;
	public static int OilDeepOcean;

	public static void readConfigOil(Configuration config){
		String Oil = "Oil Generation";
		OilBase = config.get(Oil, "Probability of found oil in a normal biome, low values imply higher probability", 1200).getInt();
		OilDesert = config.get(Oil, "Probability of found oil in a desert biome, low values imply higher probability", 200).getInt();
		OilOcean = config.get(Oil, "Probability of found oil in a ocean biome, low values imply higher probability", 300).getInt();
		OilDeepOcean = config.get(Oil, "Probability of found oil in a deep ocean biome, low values imply higher probability", 150).getInt();
	}
}
