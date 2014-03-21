package common.cout970.UltraTech.lib;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.TileEntities.Tier1.CrafterEntity;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class UT_Utils {

	public static void sendPacket(TileEntity t){
		if(t == null)return;			
		Packet p =  t.getDescriptionPacket();
		if(p == null)return;
		PacketDispatcher.sendPacketToAllPlayers(p);
	}

	public static void sendPacket(CrafterEntity t, int slot, int inv, int tipe){
		if(t == null)return;
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(bytes);
		try {
			data.writeInt(tipe);
			data.writeInt(t.xCoord);
			data.writeInt(t.yCoord);
			data.writeInt(t.zCoord);
			if(tipe == 0){
				data.writeInt(inv);
				data.writeInt(slot);
				if(inv == 0){
					if(t.craft.getStackInSlot(slot) == null){
						data.writeInt(0);
					}else{
						data.writeInt(t.craft.getStackInSlot(slot).itemID);
						data.writeInt(t.craft.getStackInSlot(slot).stackSize);
						data.writeInt(t.craft.getStackInSlot(slot).getItemDamage());
					}
				}else{
					if(t.saves.getStackInSlot(slot) == null){
						data.writeInt(0);
					}else{
						data.writeInt(t.saves.getStackInSlot(slot).itemID);
						data.writeInt(t.saves.getStackInSlot(slot).stackSize);
						data.writeInt(t.saves.getStackInSlot(slot).getItemDamage());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Packet p = new Packet250CustomPayload("UltraTech", bytes.toByteArray());
		PacketDispatcher.sendPacketToServer(p);
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
