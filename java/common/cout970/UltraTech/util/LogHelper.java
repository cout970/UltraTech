package common.cout970.UltraTech.util;

import org.apache.logging.log4j.Level;

import common.cout970.UltraTech.managers.InformationManager;

import cpw.mods.fml.common.FMLLog;


public class LogHelper{

	public static void log(Object o){
		FMLLog.log(InformationManager.MOD_ID,Level.INFO,String.valueOf(o));
	}
}
