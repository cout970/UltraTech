package common.cout970.UltraTech.managers;

import java.io.File;
import java.util.logging.Level;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import static common.cout970.UltraTech.managers.BlockManager.*;

public class ConfigManager {

	public static Configuration config;
	
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
		
		if(config.hasChanged()){
			config.save();
		}
	}

}
