package common.cout970.UltraTech.misc;

import common.cout970.UltraTech.TileEntities.electric.BoilerEntity;
import common.cout970.UltraTech.TileEntities.electric.tiers.Heater_Entity;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.FluidRegistry;

public class HeaterInteraction {

	public Heater_Entity src;
	public ForgeDirection side;
	public Object target;
	public Interaction type;
	public float own = 25;
	
	public HeaterInteraction(Heater_Entity h,ForgeDirection s,Interaction t){
		src = h;
		side = s;
		type = t;
		if(type == Interaction.Block || type == Interaction.Fluid){
			target = src.getWorldObj().getBlock(src.xCoord+side.offsetX, src.yCoord+side.offsetY, src.zCoord+side.offsetZ);
		}else if(type == Interaction.Boiler || type == Interaction.Furnace){
			target = UT_Utils.getRelative(src, side);
		}
	}
	
	public float apply(float heat){
		System.out.println("Current Heat: "+heat+" Own Heat: "+own);
		float dif = Math.abs(heat - own);
		float change = dif/2;
		own += change;
		if(type == Interaction.Block){
			if(own > 800){
				if(target instanceof Block){
					if(target == Blocks.cobblestone){
						src.getWorldObj().setBlock(src.xCoord+side.offsetX, src.yCoord+side.offsetY, src.zCoord+side.offsetZ, Blocks.lava);
					}else if(target == Blocks.sand){
						src.getWorldObj().setBlock(src.xCoord+side.offsetX, src.yCoord+side.offsetY, src.zCoord+side.offsetZ, Blocks.glass);
					}
				}
			}
		}else if(type == Interaction.Fluid){
			if(own > 100){
				if(target instanceof Block){
					if(target == Blocks.water){
						src.getWorldObj().setBlock(src.xCoord+side.offsetX, src.yCoord+side.offsetY, src.zCoord+side.offsetZ, FluidRegistry.getFluid("steam").getBlock());
					}
				}
			}
		}else if(type == Interaction.Boiler){
			return 0;
		}else if(type == Interaction.Furnace){
			if(target instanceof TileEntityFurnace){
				TileEntityFurnace tf = (TileEntityFurnace) target;
				if(heat >= 100){
//					tf.furnaceBurnTime = (int) h;
//					tf.currentItemBurnTime = (int) h;
//					heat -= h;
				}
			}
		}
		return change;
	}

	public static Interaction isInteresting(Heater_Entity h, ForgeDirection d) {
		TileEntity t = UT_Utils.getRelative(h, d);
		if(t instanceof BoilerEntity)return Interaction.Boiler;
		if(t instanceof TileEntityFurnace)return Interaction.Furnace;
		Block b = h.getWorldObj().getBlock(h.xCoord+d.offsetX, h.yCoord+d.offsetY, h.zCoord+d.offsetZ);
		if(b == null)return Interaction.Nothing;
		if(b == Blocks.cobblestone)return Interaction.Block;
		if(b == Blocks.sand)return Interaction.Block;
		if(b == Blocks.water)return Interaction.Fluid;
		return Interaction.Nothing;
	}
	
	public enum Interaction{
		Nothing,Block,Fluid,Furnace,Boiler;
	}
}
