package common.cout970.UltraTech.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import common.cout970.UltraTech.machines.tileEntities.CrafterEntity;
import common.cout970.UltraTech.machines.tileEntities.Printer3DEntity;
import common.cout970.UltraTech.machines.tileEntities.ReactorTankEntity;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		
		if(packet.channel.equals("UltraTech")){
			DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
			int x,y,z;
			int color;
			try{
				x = inputStream.readInt();
				y = inputStream.readInt();
				z = inputStream.readInt();
				color = inputStream.readInt();
				TileEntity te;
				
				if(player instanceof EntityPlayerMP){
				EntityPlayerMP playerSP = (EntityPlayerMP)player;
				te = playerSP.worldObj.getBlockTileEntity(x, y, z);
				}else{
				EntityPlayerSP playerSP = (EntityPlayerSP)player;
				te = playerSP.worldObj.getBlockTileEntity(x, y, z);
				}
				((Printer3DEntity)te).color = color;
				((Printer3DEntity)te).update = true;
				
			}catch(Exception e){
				e.printStackTrace();}	
		}else if(packet.channel == "UltraTech1"){
			DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

			int x;int y;int z;
			int value;
			int id;
			try {
				x = inputStream.readInt();
				y = inputStream.readInt();
				z = inputStream.readInt();
				id = inputStream.readInt();
				value = inputStream.readInt();
				TileEntity te = null;
				
				if(player instanceof EntityPlayerMP){
					EntityPlayerMP playerMP = (EntityPlayerMP)player;
					te = playerMP.worldObj.getBlockTileEntity(x, y, z);
				
				}else if(player instanceof EntityPlayerSP){
					EntityPlayerSP playerSP = (EntityPlayerSP)player;
					te = playerSP.worldObj.getBlockTileEntity(x, y, z);
				}
				
				if(te != null)te.receiveClientEvent(id, value);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(packet.channel == "UltraTech2"){
			DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

			int x;int y;int z;
			int text = 0;
			int id;
			try {
				x = inputStream.readInt();
				y = inputStream.readInt();
				z = inputStream.readInt();
				text = inputStream.readInt();
				id = inputStream.readInt();
				EntityPlayerSP playerMP = (EntityPlayerSP)player;
				ReactorTankEntity te = (ReactorTankEntity) playerMP.worldObj.getBlockTileEntity(x, y, z);
				if(te != null){
					if(te.liquid != null && text != 0){
					te.liquid.amount = text;				
					}else if(id != 0){
						te.liquid = new FluidStack(id, text);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(packet.channel.equals("UltraTech3")){
			DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
			int x,y,z;
			int slot;
			int id;
			int meta;
			try{
				x = inputStream.readInt();
				y = inputStream.readInt();
				z = inputStream.readInt();
				slot = inputStream.readInt();
				id = inputStream.readInt();
				meta = inputStream.readInt();
				
				TileEntity te = null;

				if(player instanceof EntityPlayerMP){
					
					EntityPlayerMP playerMP = (EntityPlayerMP)player;
					te = playerMP.worldObj.getBlockTileEntity(x, y, z);

				}else if(player instanceof EntityPlayerSP){

					EntityPlayerSP playerSP = (EntityPlayerSP)player;
					te = playerSP.worldObj.getBlockTileEntity(x, y, z);
				}

				CrafterEntity crafter = (CrafterEntity) te;
				if(id == 0){
					crafter.craft.setInventorySlotContents(slot, null);
				}else{
					crafter.craft.setInventorySlotContents(slot, new ItemStack(id, 0, meta));
				}
				crafter.onInventoryChanged();
			}catch(Exception e){
				e.printStackTrace();}			
		}
	}


	}
