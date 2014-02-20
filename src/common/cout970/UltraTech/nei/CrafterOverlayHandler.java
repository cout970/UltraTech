package common.cout970.UltraTech.nei;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import common.cout970.UltraTech.machines.gui.CrafterGui;
import common.cout970.UltraTech.machines.tileEntities.CrafterEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.recipe.IRecipeHandler;
import cpw.mods.fml.common.network.PacketDispatcher;

public class CrafterOverlayHandler implements IOverlayHandler {

	@Override
	public void overlayRecipe(GuiContainer firstGui, IRecipeHandler recipe,
			int recipeIndex, boolean shift) {

		if(firstGui instanceof CrafterGui){
			CrafterGui crafter = (CrafterGui) firstGui;
			
			CrafterEntity entity = (CrafterEntity) crafter.entity.worldObj.getBlockTileEntity(crafter.entity.xCoord, crafter.entity.yCoord, crafter.entity.zCoord);
			
			
			for(int z = 0; z < 9; z++){
				entity.craft.setInventorySlotContents(z, null);
				sendPacket(entity, z, 0, 0);
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
				entity.craft.setInventorySlotContents(slot, i);
				sendPacket(entity, slot, i.itemID, i.getItemDamage());
			}
		}
	}

	private void sendPacket(CrafterEntity tile, int slot, int id, int meta) {
		
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(bytes);
		
		try {
			data.writeInt(tile.xCoord);
			data.writeInt(tile.yCoord);
			data.writeInt(tile.zCoord);
			
			data.writeInt(slot);
			data.writeInt(id);
			data.writeInt(meta);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "UltraTech3";
		packet.data = bytes.toByteArray();
		packet.length = packet.data.length;
		
		PacketDispatcher.sendPacketToServer(packet);
		
	}
}
