package common.cout970.UltraTech.managers;

import common.cout970.UltraTech.lib.Reference;
import net.minecraftforge.common.Configuration;
import static common.cout970.UltraTech.managers.BlockManager.*;

public class ConfigManager {

	public static void LoadConfigs(Configuration config) {

		config.load();
		//blocks
		int x = 2050;
		ids.put("Chasis", config.getBlock("Chasis", x++).getInt());
		ids.put("Ores",config.getBlock("Ores", x++).getInt());
		ids.put("Reactor",config.getBlock("Reactor", x++).getInt());
		ids.put("SolarPanel",config.getBlock("SolarPanel", x++).getInt());
		ids.put("WindMill",config.getBlock("WindMill", x++).getInt());
		ids.put("Engine",config.getBlock("Engine", x++).getInt());		
		ids.put("Transformer",config.getBlock("Transformer", x++).getInt());		
		ids.put("Tier1",config.getBlock("Tier1", x++).getInt());
		ids.put("Tier2",config.getBlock("Tier2", x++).getInt());
		ids.put("Tier3",config.getBlock("Tier3", x++).getInt());
		ids.put("Misc",config.getBlock("Misc", x++).getInt());
		ids.put("Storage",config.getBlock("Storage", x++).getInt());
		ids.put("Cable",config.getBlock("Cable", x++).getInt());
		ids.put("DiamondGlass",config.getBlock("DiamondGlass", x++).getInt());
		ids.put("CovedGlass",config.getBlock("CovedGlass", x++).getInt());
		ids.put("AluminumPipe",config.getBlock("AluminumPipe", x++).getInt());
		ids.put("CopperPipe",config.getBlock("CopperPipe", x++).getInt());
		ids.put("LeadPipe",config.getBlock("LeadPipe", x++).getInt());

		//deco
		Reference.StoneBlock = config.getBlock("StoneBlock", 2099).getInt();
		Reference.Deco = config.getBlock("Deco", 2100).getInt();
		Reference.Deco2 = config.getBlock("Deco2", 2101).getInt();
		Reference.Deco3 = config.getBlock("Deco3", 2102).getInt();
		Reference.Deco4 = config.getBlock("Deco4", 2103).getInt();
		Reference.Deco5 = config.getBlock("Deco5", 2104).getInt();
		Reference.Deco6 = config.getBlock("Deco6", 2105).getInt();
		Reference.Deco7 = config.getBlock("Deco7", 2106).getInt();
		Reference.Deco8 = config.getBlock("Deco8", 2107).getInt();

		Reference.Decow = config.getBlock("Decow", 2200).getInt();
		Reference.Deco2w = config.getBlock("Deco2w", 2201).getInt();
		Reference.Deco3w = config.getBlock("Deco3w", 2202).getInt();
		Reference.Deco4w = config.getBlock("Deco4w", 2203).getInt();
		Reference.Deco5w = config.getBlock("Deco5w", 2204).getInt();
		Reference.Deco6w = config.getBlock("Deco6w", 2205).getInt();
		Reference.Deco7w = config.getBlock("Deco7w", 2206).getInt();
		Reference.Deco8w = config.getBlock("Deco8w", 2207).getInt();

		//items
		ItemManager.LoadConfigItems(config);
		if(config.hasChanged())config.save();
	}

}
