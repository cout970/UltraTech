package common.cout970.UltraTech.managers;


import ultratech.api.power.multipart.MultipartReference;
import common.cout970.UltraTech.handlers.FuelHandler;
import common.cout970.UltraTech.handlers.GuiHandler;
import common.cout970.UltraTech.handlers.WorldGen;
import common.cout970.UltraTech.microparts.MicroRegistry;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.proxy.CommonProxy;
import common.cout970.UltraTech.util.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;



@Mod(modid = InformationManager.MOD_ID, name = InformationManager.MOD_ID,version = "0.9.0.6",guiFactory = InformationManager.GUI_FACTORY)


public class UltraTech {

	@Instance("UltraTech")
	public static UltraTech instance;
	
	@SidedProxy(clientSide="common.cout970.UltraTech.proxy.ClientProxy",serverSide="common.cout970.UltraTech.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		LogHelper.log("Starting PreInit");
		ConfigManager.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigManager());
		
		if (Loader.isModLoaded("ForgeMultipart") && Loader.isModLoaded("CodeChickenCore")){
			MultipartReference.isMicroPartActived = true;
			LogHelper.log("Activating Forge Multipart Compatibility");
		}
		ItemManager.InitItems();
		ItemManager.RegisterItems();
		
		BlockManager.InitBlocks();
		BlockManager.RegisterBlocks();
		
		FluidManager.InitFluids();
		FluidManager.RegisterFluids();
		
		LogHelper.log("Finishing PreInit");
		if(InformationManager.debug)Language.setupLangFile(); //for lag file only in debug
	}

	@EventHandler
	public void load(FMLInitializationEvent event){
		LogHelper.log("Starting Init");
		Net_Utils.initMessages();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		if(MultipartReference.isMicroPartActived) new MicroRegistry().load();
		CompatibilityManager.initCompatibilitys();
		GameRegistry.registerFuelHandler(new FuelHandler());
		CraftManager.registerCraft();
		GameRegistry.registerWorldGenerator(new WorldGen(), 10);
		proxy.registerRenders();
		LogHelper.log("Finishing Init");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event){}
}
