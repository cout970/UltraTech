package common.cout970.UltraTech.packets;

import api.cout970.UltraTech.network.PacketBase;
import common.cout970.UltraTech.TileEntities.Tier1.Printer3DEntity;
import common.cout970.UltraTech.TileEntities.Tier3.ClimateEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

public class PacketClimateStation extends PacketBase{

	public PacketClimateStation(){}
	
	public int x,y,z;
	public int type;
	
	public PacketClimateStation(TileEntity t,int c){
		x = t.xCoord;
		y = t.yCoord;
		z = t.zCoord;
		type = c;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {		
		PacketBuffer pb = new PacketBuffer(buffer);
		pb.writeInt(x);
		pb.writeShort(y);
		pb.writeInt(z);
		pb.writeInt(type);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {		
		PacketBuffer pb = new PacketBuffer(buffer);
		x = pb.readInt();
		y = pb.readShort();
		z = pb.readInt();
		type = pb.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		ClimateEntity t = (ClimateEntity) player.worldObj.getTileEntity(x, y, z);
		t.setClimate(type);
	}

}
