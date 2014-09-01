package common.cout970.UltraTech.managers;


import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import ultratech.api.power.NetworkManagerRegistry;
import ultratech.api.power.multipart.MultipartReference;

import com.google.common.collect.Lists;
import common.cout970.UltraTech.TileEntities.electric.MinerEntity;
import common.cout970.UltraTech.handlers.BottleHandler;
import common.cout970.UltraTech.handlers.FuelHandler;
import common.cout970.UltraTech.handlers.GuiHandler;
import common.cout970.UltraTech.handlers.UltratechWorldGenerator;
import common.cout970.UltraTech.multipart.MultiPartRegistry_UT;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.proxy.CommonProxy;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.waila.WailaRegister;

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



@Mod(modid = InformationManager.MOD_ID, name = InformationManager.MOD_ID,version = "1.7.10-0.9.5.3",guiFactory = InformationManager.GUI_FACTORY)


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
		
		if(Loader.isModLoaded("Waila")){
			LogHelper.log("init waila compatibility");
			WailaRegister.init();
		}
		
		LogHelper.log("Finishing PreInit");
		if(InformationManager.debug)Language.setupLangFile(); //for lag file only in debug
	}

	@EventHandler
	public void load(FMLInitializationEvent event){
		LogHelper.log("Starting Init");
		Net_Utils.initMessages();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());	//gui
		if(MultipartReference.isMicroPartActived){
			new MultiPartRegistry_UT().load();		//microblocks
		}else{
			NetworkManagerRegistry.loadDefaultPathFinder();
		}
		CompatibilityManager.initCompatibilitys();									//oredict and buildcraft engine fuel
		GameRegistry.registerFuelHandler(new FuelHandler());						//fuel
		CraftManager.registerCraft();												//craft and smelting
		RecipeCreator.initRecipes();												//cutter for machines
		GameRegistry.registerWorldGenerator(new UltratechWorldGenerator(), 10);					//world generation
		proxy.registerRenders();													//renders
		LogHelper.log("Finishing Init");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		BottleHandler.RegisterBuckets();
		ForgeChunkManager.setForcedChunkLoadingCallback(instance, new MinerChunkCallBack());
	}

	public class MinerChunkCallBack implements ForgeChunkManager.OrderedLoadingCallback {

		@Override
		public void ticketsLoaded(List<Ticket> tickets, World world) {
			for (Ticket ticket : tickets) {
				int x = ticket.getModData().getInteger("quarryX");
				int y = ticket.getModData().getInteger("quarryY");
				int z = ticket.getModData().getInteger("quarryZ");
				MinerEntity tq = (MinerEntity) world.getTileEntity(x, y, z);
				tq.forceChunkLoading(ticket);
			}
		}

		@Override
		public List<Ticket> ticketsLoaded(List<Ticket> tickets, World world, int maxTicketCount) {
			List<Ticket> validTickets = Lists.newArrayList();
			for (Ticket ticket : tickets) {
				int x = ticket.getModData().getInteger("quarryX");
				int y = ticket.getModData().getInteger("quarryY");
				int z = ticket.getModData().getInteger("quarryZ");

				Block block = world.getBlock(x, y, z);
				if (block == BlockManager.Tier3) {
					validTickets.add(ticket);
				}
			}
			return validTickets;
		}
	}
}
