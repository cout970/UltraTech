package common.cout970.UltraTech.handlers;

import java.util.Arrays;
import java.util.List;

import common.cout970.UltraTech.managers.ConfigManager;
import common.cout970.UltraTech.managers.InformationManager;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

public class ModGuiConfig extends GuiConfig {

	public ModGuiConfig(GuiScreen parentScreen) {
		super(parentScreen,
				new ConfigElement(ConfigManager.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				InformationManager.MOD_ID,
				false,
				false,
				GuiConfig.getAbridgedConfigPath(ConfigManager.config.toString()));
	}

}
