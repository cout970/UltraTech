package common.cout970.UltraTech.TileEntities.Tier1;

import api.cout970.UltraTech.network.Net_Utils;

import common.cout970.UltraTech.itemBlock.UT_ItemBlockDeco;
import common.cout970.UltraTech.misc.Inventory;
import common.cout970.UltraTech.packets.PacketPainter;

public class Printer3DEntity extends Inventory{

	public int color = 0;
	public boolean update;
	
	public Printer3DEntity(){
		super(1,"3D Printer");
	}
	
	//needs client - server sync
	
	public void updateEntity(){
		if(update){
			update = false;
			if(inventory[0] != null && inventory[0].getItem() instanceof UT_ItemBlockDeco)
			inventory[0].setItemDamage(color);
			Sync();
		}
	}

	public void sendUpdate() {
		Net_Utils.PipeLine.sendToServer(new PacketPainter(this, color));
	}

}
