package common.cout970.UltraTech.core;


import api.cout970.UltraTech.network.Net_Utils;
import api.cout970.UltraTech.network.PacketUpdate;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
//import net.minecraftforge.common.Configuration;
import common.cout970.UltraTech.handlers.GuiHandler;
import common.cout970.UltraTech.handlers.WorldGen;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.managers.CompatibilityManager;
import common.cout970.UltraTech.managers.ConfigManager;
import common.cout970.UltraTech.managers.CraftManager;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.microparts.MicroRegistry;
import common.cout970.UltraTech.packets.PacketClimateStation;
import common.cout970.UltraTech.packets.PacketController;
import common.cout970.UltraTech.packets.PacketCrafter;
import common.cout970.UltraTech.packets.PacketPainter;
import common.cout970.UltraTech.packets.PacketTesseract;
import common.cout970.UltraTech.proxy.CommonProxy;
import common.cout970.UltraTech.proxy.Language;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
//import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;



@Mod(modid = "UltraTech", name = "UltraTech",version = "0.9.0")


public class UltraTech {

	//Mod instances
	@Instance("UltraTech")
	public static UltraTech instance;
	
	//Proxy
	@SidedProxy(clientSide="common.cout970.UltraTech.proxy.ClientProxy",serverSide="common.cout970.UltraTech.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final CreativeTabs ResourceTab = new CreativeTabs("UltraTech Resources"){

		public ItemStack getIconItemStack() {
			return new ItemStack(ItemManager.ItemName.get("Plate"), 1, 7);
		}

		@SideOnly(Side.CLIENT)
		@Override
		public String getTranslatedTabLabel()
		{
			return "UltraTech Resources";
		}

		@Override
		public Item getTabIconItem() {
			return null;
		};
	};

	//creative tabs
	public static final CreativeTabs techTab = new CreativeTabs("UltraTech"){

		public ItemStack getIconItemStack() {
			return new ItemStack(BlockManager.Chasis, 1, 2);
		}

		@SideOnly(Side.CLIENT)
		@Override
		public String getTranslatedTabLabel()
		{
			return "UltraTech";
		}

		@SideOnly(Side.CLIENT)
		@Override
		public String getTabLabel()
		{
			return "UltraTech";
		}

		@Override
		public Item getTabIconItem() {
			return null;
		}
	};

	public static final CreativeTabs DecoTab = new CreativeTabs("UltraTech"){

		public ItemStack getIconItemStack() {
			return new ItemStack(BlockManager.deco.get(5), 1, 0);
		}

		@SideOnly(Side.CLIENT)
		@Override
		public String getTranslatedTabLabel()
		{
			return "UltraTech Decoration";
		}

		@SideOnly(Side.CLIENT)
		@Override
		public String getTabLabel()
		{
			return "UltraTech Decoration";
		}

		@Override
		public Item getTabIconItem() {
			return null;
		}
	};


	//init
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		ConfigManager.LoadConfigs(config);
	}

	@EventHandler
	public void load(FMLInitializationEvent event){

		Net_Utils.PipeLine.channels = NetworkRegistry.INSTANCE.newChannel("UltraTech", Net_Utils.PipeLine);
		Net_Utils.PipeLine.registerPacket(PacketUpdate.class);
		Net_Utils.PipeLine.registerPacket(PacketCrafter.class);
		Net_Utils.PipeLine.registerPacket(PacketController.class);
		Net_Utils.PipeLine.registerPacket(PacketTesseract.class);
		Net_Utils.PipeLine.registerPacket(PacketPainter.class);
		Net_Utils.PipeLine.registerPacket(PacketClimateStation.class);
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		ItemManager.RegisterItems();
		BlockManager.InitBlocks();
		BlockManager.RegisterBlocks();
		if (Loader.isModLoaded("ForgeMultipart")) {
            new MicroRegistry().load();
        }
//		Language.setupLangFile(); for lag file only in debug
		CompatibilityManager.initCompatibilitys();
		CraftManager.registerCraft();
		GameRegistry.registerWorldGenerator(new WorldGen(), 10);
		proxy.registerRenders();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		Net_Utils.PipeLine.postInitialise();
	}
}
