package common.cout970.UltraTech.managers;

import net.minecraftforge.common.config.Configuration;
import static common.cout970.UltraTech.managers.BlockManager.*;

public class ConfigManager {

	public static void LoadConfigs(Configuration config) {

		config.load();
		
		//items
		ItemManager.LoadConfigItems(config);
		OreGeneration.readConfigOre(config);
		if(config.hasChanged())config.save();
	}

}
