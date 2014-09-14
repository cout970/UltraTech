package common.cout970.UltraTech.nei;

import ultratech.api.util.UT_Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.recipe.IRecipeHandler;
import common.cout970.UltraTech.TileEntities.utility.TileEntityCrafter;
import common.cout970.UltraTech.client.gui.GuiCrafter;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.network.messages.MessageCrafter;

public class CrafterOverlayHandler implements IOverlayHandler {

	@Override
	public void overlayRecipe(GuiContainer firstGui, IRecipeHandler recipe,
			int recipeIndex, boolean shift) {

		if(firstGui instanceof GuiCrafter){
			GuiCrafter crafter = (GuiCrafter) firstGui;
			
			TileEntityCrafter entity = (TileEntityCrafter) crafter.entity.getWorldObj().getTileEntity(crafter.entity.xCoord, crafter.entity.yCoord, crafter.entity.zCoord);
			
			
			for(int z = 0; z < 9; z++){
//				entity.craft.setInventorySlotContents(z, null);
				Net_Utils.INSTANCE.sendToServer(new MessageCrafter(entity, null, z));
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
				ItemStack i = UT_Utils.getFirst(recipe.getIngredientStacks(recipeIndex).get(z).items);
				Net_Utils.INSTANCE.sendToServer(new MessageCrafter(entity, i, slot));
			}
			entity.update();
		}
	}
}
