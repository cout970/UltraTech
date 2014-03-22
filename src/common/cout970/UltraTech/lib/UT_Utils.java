package common.cout970.UltraTech.lib;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.TileEntities.Tier1.CrafterEntity;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;

public class UT_Utils {

	public static void sendPacket(TileEntity t){
		if(t == null)return;			
		Packet p =  t.getDescriptionPacket();
		if(p == null)return;
		PacketDispatcher.sendPacketToAllPlayers(p);
	}

	public static void sendPacket(CrafterEntity t, int slot, int tipe){
		if(t == null)return;
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(bytes);
		try {
			data.writeInt(tipe);//0 = slot change / 1 = craft / 2 = save
			data.writeInt(t.xCoord);
			data.writeInt(t.yCoord);
			data.writeInt(t.zCoord);
			if(tipe == 0){//change craft slot info
				data.writeInt(slot);
				if(t.craft.getStackInSlot(slot) == null){
					data.writeInt(0);
				}else{
					data.writeInt(t.craft.getStackInSlot(slot).itemID);
					data.writeInt(t.craft.getStackInSlot(slot).stackSize);
					data.writeInt(t.craft.getStackInSlot(slot).getItemDamage());
				}
			}else if(tipe == 3){///send slot to get save
				data.writeInt(slot);
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

	/**
	 * 
	 * @param a itemstack 1
	 * @param b itemstack 2
	 * @param meta if want detect metadata
	 * @return if are equals
	 */
	public static boolean areEcuals(ItemStack a, ItemStack b, boolean meta){
		if(a == null && b == null)return true;
		if(a != null && b != null){
			if(a.itemID == b.itemID && (!meta || (a.getItemDamage() == b.getItemDamage())))return true;
			if(OreDictionary.getOreID(a) != -1 && OreDictionary.getOreID(b) != -1){
				if(OreDictionary.getOreID(a) == OreDictionary.getOreID(b))return true;
			}
		}
		return false;
	}

	public static int RGBtoInt(int r,int g,int b){
		int color = 0;
		color += r*65536;
		color += g * 256;
		color += b;
		return color;
	}
}
