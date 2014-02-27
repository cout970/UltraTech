package common.cout970.UltraTech.core;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import common.cout970.UltraTech.blocks.DecoBlocks;
import common.cout970.UltraTech.handlers.GuiHandler;
import common.cout970.UltraTech.handlers.PacketHandler;
import common.cout970.UltraTech.handlers.WorldGen;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.managers.CompatibilityManager;
import common.cout970.UltraTech.managers.ConfigManager;
import common.cout970.UltraTech.managers.CraftManager;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.proxy.CommonProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;



@Mod(modid = "UltraTech", name = "UltraTech",version = "0.8")

@NetworkMod(clientSideRequired=true, serverSideRequired=true, channels={"UltraTech","UltraTech1","UltraTech2","UltraTech3"}, packetHandler = PacketHandler.class)

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
		};
	};

	//creative tabs
	public static final CreativeTabs techTab = new CreativeTabs("UltraTech"){

		public ItemStack getIconItemStack() {
			return new ItemStack(BlockManager.IDS, 1, 0);
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
	};

	public static final CreativeTabs DecoTab = new CreativeTabs("UltraTech"){

		public ItemStack getIconItemStack() {
			return new ItemStack(DecoBlocks.d.get(5), 1, 0);
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
	};


	//init
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		ConfigManager.LoadConfigs(config);
	}

	@EventHandler
	public void load(FMLInitializationEvent event){

		proxy.registerRenders();
		NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());
		ItemManager.RegisterItems();
		BlockManager.InitBlocks();
		BlockManager.RegisterBlocks();
		DecoBlocks.initBlocks();
		CompatibilityManager.initCompatibilitys();
		CraftManager.registerCraft();
		GameRegistry.registerWorldGenerator(new WorldGen());
	}



	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		CompatibilityManager.OreGenOptions();
	}



}
