package common.cout970.UltraTech.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import common.cout970.UltraTech.machines.tileEntities.ReactorTankEntity;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		if(packet.channel == "UltraTech"){
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
		}
	}


	}
