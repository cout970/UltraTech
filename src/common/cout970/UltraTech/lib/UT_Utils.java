package common.cout970.UltraTech.lib;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class UT_Utils {

	public static void sendPacket(TileEntity t, boolean server){
		if(t == null)return;
		Packet p =  t.getDescriptionPacket();
		if(p == null)return;
		if(server){
			PacketDispatcher.sendPacketToServer(p);
		}else{
			PacketDispatcher.sendPacketToAllPlayers(p);
		}
	}
	
	public static List<TileEntity> getTiles(World w,int xCoord, int yCoord, int zCoord){
		List<TileEntity> t = new ArrayList<TileEntity>();
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			TileEntity e = w.getBlockTileEntity(xCoord+d.offsetX, yCoord+d.offsetY, zCoord+d.offsetZ);
			if(e != null)t.add(e);
		}
		return t;
	}
}
