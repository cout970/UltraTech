package common.cout970.UltraTech.handlers;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import common.cout970.UltraTech.managers.FluidManager;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.fluids.HelperNBT;

public class BottleHandler {

	public static BottleHandler INSTANCE = new BottleHandler();
	public Map<String, ItemStack> bottles = new HashMap<String, ItemStack>();

	public ItemStack fillCustomBottle(World world, int x, int y,int z) {
		Block block = world.getBlock(x, y, z);
		Fluid f = FluidRegistry.lookupFluidForBlock(block);
		if(f == null)return null;
		ItemStack bottle = bottles.get(f.getName());

		if (bottle != null && world.getBlockMetadata(x, y, z) == 0) {
			world.setBlockToAir(x, y, z);
			if(bottle.stackSize == 0)bottle.stackSize = 1;
			return bottle;
		} else
			return null;
	}

	public static void RegisterBuckets(){
		FluidContainerRegistry.registerFluidContainer(FluidManager.Acid, new ItemStack(ItemManager.ItemName.get("SulfuricAcid"),1), new ItemStack(Items.potionitem,1,0));
		
		for(int x=1;x<FluidRegistry.getRegisteredFluidIDs().size();x++){
			ItemStack bot = new ItemStack(ItemManager.ItemName.get("Bottle"),1,x);
			Fluid f = FluidRegistry.getFluid(x);
			
			if(f == null)continue;
			
			HelperNBT.setFluid(bot,f,1000);
			FluidContainerRegistry.registerFluidContainer(f, bot, new ItemStack(ItemManager.ItemName.get("Bottle"),1,0));
			if(f.getBlock() != null){
				INSTANCE.bottles.put(f.getName(),bot);
			}
		}
	}
}
