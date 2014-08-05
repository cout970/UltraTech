package common.cout970.UltraTech.managers;

import org.apache.logging.log4j.Level;

import common.cout970.UltraTech.block.fluids.BlockFluidBase;
import common.cout970.UltraTech.block.fluids.BlockFluidFin;
import common.cout970.UltraTech.util.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidManager{
	
	
	//fluid
	public static Fluid Steam;
	public static Fluid Juice;
	public static Fluid Gas_Ethanol;
	public static Fluid Ethanol;
	public static Fluid Gasoline;
	public static Fluid Gas_Oil;
	public static Fluid Oil;
	public static Fluid Plastic;
	public static Fluid Fuel;
	public static Fluid Acid;
	
	public static Fluid[] fluids;
	
	public static Block SteamBlock;
	public static Block JuiceBlock;
	public static Block Gas_EthanolBlock;
	public static Block EthanolBlock;
	public static Block Gas_OilBlock;
	public static Block GasolineBlock;
	public static Block OilBlock;
	public static Block PlasticBlock;
	public static Block FuelBlock;
	public static Block AcidBlock;
	

	public static void InitFluids() {
		Steam = new Fluid("steam");
		Juice = new Fluid("juice");
		Gas_Ethanol = new Fluid("gas_ethanol");
		Ethanol = new Fluid("bioEthanol");
		Gas_Oil = new Fluid("gas_oil");
		Gasoline = new Fluid("gasoline");
		Oil = new Fluid("oil").setDensity(2000).setViscosity(2000);
		Plastic = new Fluid("plastic");
		Fuel = new Fluid("fuel");
		Acid = new Fluid("sulfuric_acid");
		
		Fluid[] fluid = {Steam,Juice,Gas_Ethanol,Ethanol,Gasoline,Gas_Oil,Plastic,Fuel,Acid,Oil};
		fluids = fluid;
		
		setupFluids();
		
		
	}
	
	private static void setupFluids(){
		//liquid
			if(!FluidRegistry.registerFluid(Juice)){Juice = FluidRegistry.getFluid("juice");}
			if(!FluidRegistry.registerFluid(Ethanol)){Ethanol = FluidRegistry.getFluid("bioethanol");}
			if(!FluidRegistry.registerFluid(Gasoline)){Gasoline = FluidRegistry.getFluid("gasoline");}
			if(!FluidRegistry.registerFluid(Oil)){Oil = FluidRegistry.getFluid("oil");}
			if(!FluidRegistry.registerFluid(Plastic)){Plastic = FluidRegistry.getFluid("plastic");}
			if(!FluidRegistry.registerFluid(Fuel)){Fuel = FluidRegistry.getFluid("fuel");}
			if(!FluidRegistry.registerFluid(Acid)){Acid = FluidRegistry.getFluid("sulfuric_acid");}
			
			//gas
			Steam.setDensity(-5000);
			Steam.setViscosity(1000);
			Steam.setTemperature(373);
			Steam.setGaseous(true);
			if(!FluidRegistry.registerFluid(Steam)){Steam = FluidRegistry.getFluid("steam");}
			Gas_Ethanol.setDensity(-5000);
			Gas_Ethanol.setViscosity(1000);
			Gas_Ethanol.setGaseous(true);
			if(!FluidRegistry.registerFluid(Gas_Ethanol)){Gas_Ethanol = FluidRegistry.getFluid("gas_ethanol");}
			Gas_Oil.setDensity(-5000);
			Gas_Oil.setTemperature(524);
			Gas_Oil.setViscosity(1000);
			Gas_Oil.setGaseous(true);
			if(!FluidRegistry.registerFluid(Gas_Oil)){Gas_Oil = FluidRegistry.getFluid("gas_oil");}
			
			//blocks
			if(FluidRegistry.getFluid("steam").getBlock() == null){
				SteamBlock = new BlockFluidFin(Steam).setBlockName("steam");
				GameRegistry.registerBlock(SteamBlock, "ultratech_"+"steam");
			}else{SteamBlock = FluidRegistry.getFluid("steam").getBlock();}
			
			if(FluidRegistry.getFluid("juice").getBlock() == null){
				JuiceBlock = new BlockFluidBase(Juice).setBlockName("juice");
				GameRegistry.registerBlock(JuiceBlock, "ultratech_"+"juice");
			}
			else{JuiceBlock = FluidRegistry.getFluid("juice").getBlock();}
			
			if(FluidRegistry.getFluid("bioethanol").getBlock() == null){
				EthanolBlock = new BlockFluidBase(Ethanol).setBlockName("bioethanol");
				GameRegistry.registerBlock(EthanolBlock, "ultratech_"+"ethanol");
			}
			else{EthanolBlock = FluidRegistry.getFluid("bioethanol").getBlock();}
			
			if(FluidRegistry.getFluid("gas_ethanol").getBlock() == null){
				Gas_EthanolBlock = new BlockFluidFin(Gas_Ethanol).setBlockName("gas_ethanol");
				GameRegistry.registerBlock(Gas_EthanolBlock, "ultratech_"+"gas_ethanol");
			}
			else{Gas_EthanolBlock = FluidRegistry.getFluid("gas_ethanol").getBlock();}
			
			if(FluidRegistry.getFluid("gas_oil").getBlock() == null){
				Gas_OilBlock = new BlockFluidFin(Gas_Oil).setBlockName("gas_oil");
				GameRegistry.registerBlock(Gas_OilBlock, "ultratech_"+"gas_oil");
			}
			else{Gas_OilBlock = FluidRegistry.getFluid("gas_oil").getBlock();}
			
			if(FluidRegistry.getFluid("gasoline").getBlock() == null){
				GasolineBlock = new BlockFluidBase(Gasoline).setBlockName("gasoline");
				GameRegistry.registerBlock(GasolineBlock, "ultratech_"+"gasoline");
			}
			else{GasolineBlock = FluidRegistry.getFluid("gasoline").getBlock();}
			
			if(FluidRegistry.getFluid("oil").getBlock() == null){
				OilBlock = new BlockFluidBase(Oil).setBlockName("oil");
				GameRegistry.registerBlock(OilBlock, "ultratech_"+"oil");
			}
			else{OilBlock = FluidRegistry.getFluid("oil").getBlock();}
			
			if(FluidRegistry.getFluid("plastic").getBlock() == null){
				PlasticBlock = new BlockFluidBase(Plastic).setBlockName("plastic");
				GameRegistry.registerBlock(PlasticBlock, "ultratech_"+"plastic");
			}
			else{PlasticBlock = FluidRegistry.getFluid("plastic").getBlock();}
			
			if(FluidRegistry.getFluid("fuel").getBlock() == null){
				FuelBlock = new BlockFluidBase(Fuel).setBlockName("fuel");
				GameRegistry.registerBlock(FuelBlock, "ultratech_"+"fuel");
			}
			else{FuelBlock = FluidRegistry.getFluid("fuel").getBlock();}
			
			if(FluidRegistry.getFluid("sulfuric_acid").getBlock() == null){
				AcidBlock = new BlockFluidBase(Acid).setBlockName("sulfuric_acid");
				GameRegistry.registerBlock(AcidBlock, "ultratech_"+"sulfuric_acid");
			}
			else{AcidBlock = FluidRegistry.getFluid("sulfuric_acid").getBlock();}
	}

	public static void RegisterFluids() {
		
		FluidContainerRegistry.registerFluidContainer(Acid, new ItemStack(ItemManager.ItemName.get("SulfuricAcid"),1), new ItemStack(Items.potionitem,1,0));
		for(int x=0;x<fluids.length;x++){
			FluidContainerRegistry.registerFluidContainer(fluids[x], new ItemStack(ItemManager.ItemName.get("Bottle"),1,x+1), new ItemStack(ItemManager.ItemName.get("Bottle"),1,0));
		}
		
		Language.addName(Ethanol, "Ethanol");
		Language.addName(Oil, "Oil");
		Language.addName(Juice, "Juice");
		Language.addName(Steam, "Steam");
		Language.addName(Gas_Oil, "Oil Gas");
		Language.addName(Gas_Ethanol, "Ethanol Gas");
		Language.addName(Gasoline, "Gasoline");
		Language.addName(Plastic, "Plastic");
		Language.addName(Fuel, "Fuel");
		Language.addName(Acid, "Sulfuric Acid");
		
		Language.addName(EthanolBlock, "Ethanol");
		Language.addName(OilBlock, "Oil");
		Language.addName(JuiceBlock, "Juice");
		Language.addName(SteamBlock, "Steam");
		Language.addName(Gas_OilBlock, "Oil Gas");
		Language.addName(Gas_EthanolBlock, "Ethanol Gas");
		Language.addName(GasolineBlock, "Gasoline");
		Language.addName(PlasticBlock, "Plastic");
		Language.addName(FuelBlock, "Fuel");
		Language.addName(AcidBlock, "Sulfuric Acid");
	}
}
