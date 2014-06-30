package common.cout970.UltraTech.nei;

import api.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.TileEntities.utility.CrafterEntity;
import common.cout970.UltraTech.gui.Crafter_Gui;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.packets.PacketCrafter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.recipe.IRecipeHandler;

public class CrafterOverlayHandler implements IOverlayHandler {

	@Override
	public void overlayRecipe(GuiContainer firstGui, IRecipeHandler recipe,
			int recipeIndex, boolean shift) {

		if(firstGui instanceof Crafter_Gui){
			Crafter_Gui crafter = (Crafter_Gui) firstGui;
			
			CrafterEntity entity = (CrafterEntity) crafter.entity.getWorldObj().getTileEntity(crafter.entity.xCoord, crafter.entity.yCoord, crafter.entity.zCoord);
			
			
			for(int z = 0; z < 9; z++){
//				entity.craft.setInventorySlotContents(z, null);
				Net_Utils.PipeLine.sendToServer(new PacketCrafter(entity, null, z));
			}
			
			for(int z = 0; z < recipe.getIngredientStacks(recipeIndex).size(); z++){
				
				int x,y;
				x = recipe.getIngredientStacks(recipeIndex).get(z).relx;
				y = recipe.getIngredientStacks(recipeIndex).get(z).rely;
				
				int slot = 0;
				if(x == 25){
					if(y == 6){
						slot = 0;
					}
					if(y == 24){
						slot = 3;
					}
					if(y == 42){
						slot = 6;
					}
				}
				if(x == 43){
					if(y == 6){
						slot = 1;
					}
					if(y == 24){
						slot = 4;
					}
					if(y == 42){
						slot = 7;
					}
				}
				if(x == 61){
					if(y == 6){
						slot = 2;
					}
					if(y == 24){
						slot = 5;
					}
					if(y == 42){
						slot = 8;
					}
				}
				ItemStack i = recipe.getIngredientStacks(recipeIndex).get(z).item;
				Net_Utils.PipeLine.sendToServer(new PacketCrafter(entity, i, slot));
			}
			entity.update();
		}
	}
}
