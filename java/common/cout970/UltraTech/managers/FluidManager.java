package common.cout970.UltraTech.managers;

import common.cout970.UltraTech.block.fluids.BlockFluidBase;
import common.cout970.UltraTech.block.fluids.BlockFluidFin;
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
		Oil = new Fluid("oil");
		Plastic = new Fluid("plastic");
		Fuel = new Fluid("fuel");
		Acid = new Fluid("sulfuric_acid");
		
		Fluid[] fluid = {Steam,Juice,Gas_Ethanol,Ethanol,Gasoline,Gas_Oil,Plastic,Fuel,Acid,Oil};
		fluids = fluid;
		
		setupFluids();
		
		SteamBlock = new BlockFluidFin(Steam).setBlockName("steam");
		JuiceBlock = new BlockFluidBase(Juice).setBlockName("juice");
		EthanolBlock = new BlockFluidBase(Ethanol).setBlockName("bioEthanol");
		Gas_EthanolBlock = new BlockFluidFin(Gas_Ethanol).setBlockName("gas_ethanol");
		Gas_OilBlock = new BlockFluidFin(Gas_Oil).setBlockName("gas_oil");
		GasolineBlock = new BlockFluidBase(Gasoline).setBlockName("gasoline");
		OilBlock = new BlockFluidBase(Oil).setBlockName("oil");
		PlasticBlock = new BlockFluidBase(Plastic).setBlockName("plastic");
		FuelBlock = new BlockFluidBase(Fuel).setBlockName("fuel");
		AcidBlock = new BlockFluidBase(Acid).setBlockName("sulfuric_acid");
	}
	
	private static void setupFluids(){
		//liquid
			FluidRegistry.registerFluid(FluidManager.Juice);
			FluidRegistry.registerFluid(FluidManager.Ethanol);
			FluidRegistry.registerFluid(FluidManager.Gasoline);
			FluidRegistry.registerFluid(FluidManager.Oil);
			FluidRegistry.registerFluid(Plastic);
			FluidRegistry.registerFluid(Fuel);
			FluidRegistry.registerFluid(Acid);
			
		//gas
			Steam.setDensity(-5000);
			Steam.setViscosity(1000);
			Steam.setTemperature(373);
			Steam.setGaseous(true);
			FluidRegistry.registerFluid(Steam);
			Gas_Ethanol.setDensity(-5000);
			Gas_Ethanol.setViscosity(1000);
			Gas_Ethanol.setGaseous(true);
			FluidRegistry.registerFluid(Gas_Ethanol);
			Gas_Oil.setDensity(-5000);
			Gas_Oil.setTemperature(524);
			Gas_Oil.setViscosity(1000);
			Gas_Oil.setGaseous(true);
			FluidRegistry.registerFluid(Gas_Oil);
	}

	public static void RegisterFluids() {
		
		//blocks
		GameRegistry.registerBlock(SteamBlock, "ultratech_"+"steam");
		GameRegistry.registerBlock(JuiceBlock, "ultratech_"+"juice");
		GameRegistry.registerBlock(Gas_EthanolBlock, "ultratech_"+"gas_ethanol");
		GameRegistry.registerBlock(Gas_OilBlock, "ultratech_"+"gas_oil");
		GameRegistry.registerBlock(EthanolBlock, "ultratech_"+"ethanol");
		GameRegistry.registerBlock(GasolineBlock, "ultratech_"+"gasoline");
		GameRegistry.registerBlock(OilBlock, "ultratech_"+"oil");
		GameRegistry.registerBlock(PlasticBlock, "ultratech_"+"plastic");
		GameRegistry.registerBlock(FuelBlock, "ultratech_"+"fuel");
		GameRegistry.registerBlock(AcidBlock, "ultratech_"+"sulfuric_acid");
		
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
