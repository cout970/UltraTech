package common.cout970.UltraTech.managers;

import java.io.File;
import java.util.logging.Level;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import static common.cout970.UltraTech.managers.BlockManager.*;

public class ConfigManager {

	public static Configuration config;
	public static float DECO_LIGHT = 1F;
	
	public static void init(File file) {
		if(config == null){
			config = new Configuration(file);
			LoadConfigs();
		}
	}
	
	@SubscribeEvent
	public void onconfigurationChangeEvent(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.modID.equalsIgnoreCase(InformationManager.MOD_ID)){
			LoadConfigs();
		}
	}
	
	public static void LoadConfigs(){
		config.load();
		
		OreGeneration.readConfigOre(config);
		OreGeneration.readConfigOil(config);
		DECO_LIGHT = config.getFloat("deco_light", "Health", 1f, 0f, 1f, "The light emited for the deco blocks, only if you want to save your eyes!");
		if(config.hasChanged()){
			config.save();
		}
	}

}
