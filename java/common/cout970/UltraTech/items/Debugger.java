package common.cout970.UltraTech.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import ultratech.api.power.NetworkManagerRegistry;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.IPowerConductor;

public class Debugger extends UT_Item{

	public Debugger(String name) {
		super(name);
	}
	
	public boolean onItemUse(ItemStack item, EntityPlayer p, World w, int x, int y, int z, int side, float hitx, float hity, float hitz)
    {
		TileEntity t = w.getTileEntity(x, y, z);
		if(t != null){
			for(ICable c : NetworkManagerRegistry.getConnections(t)){
				String s = "";
				s += "Energy: "+c.getPower().getCharge();
				if(!w.isRemote && c.getPower().getNetwork() != null){
					s += " Network: "+c.getPower().getNetwork().getConductors().size();
				}
				p.addChatMessage(new ChatComponentText((w.isRemote ? "Client:  " : "Server:")+" "+s));
			}
		}

		return false;
    }
}
