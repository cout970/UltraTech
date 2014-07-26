package common.cout970.UltraTech.TileEntities.utility;

import common.cout970.UltraTech.itemBlock.ItemBlock_Deco;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.network.messages.MessagePainter;
import common.cout970.UltraTech.util.Inventory;

public class Painter3DEntity extends Inventory{

	public int color = 0;
	public boolean update;
	
	public Painter3DEntity(){
		super(1,"3D Painter");
	}
	
	
	public void updateEntity(){
		if(update){
			update = false;
			if(inventory[0] != null && inventory[0].getItem() instanceof ItemBlock_Deco)
			inventory[0].setItemDamage(color);
			sendNetworkUpdate();
		}
	}

	public void sendUpdate() {
		Net_Utils.INSTANCE.sendToServer(new MessagePainter(this, color));
	}

}
