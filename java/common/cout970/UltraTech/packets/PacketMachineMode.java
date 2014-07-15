package common.cout970.UltraTech.packets;

import ultratech.api.power.StorageInterface.PowerIO;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import common.cout970.UltraTech.TileEntities.electric.tiers.Tesseract_Entity;
import common.cout970.UltraTech.network.AbstractPacket;
import common.cout970.UltraTech.util.power.Machine;

public class PacketMachineMode extends AbstractPacket{

	int x,y,z;
	int m;
	
	public PacketMachineMode(TileEntity t, int m){
		x = t.xCoord;
		y = t.yCoord;
		z = t.zCoord;
		this.m = m;
	}
	
	public PacketMachineMode(){}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {		
		PacketBuffer pb = new PacketBuffer(buffer);
		pb.writeInt(x);
		pb.writeShort(y);
		pb.writeInt(z);
		pb.writeInt(m);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {		
		PacketBuffer pb = new PacketBuffer(buffer);
		x = pb.readInt();
		y = pb.readShort();
		z = pb.readInt();
		m = pb.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		Machine a = (Machine) player.worldObj.getTileEntity(x, y, z);
		a.cond.configIO = PowerIO.values()[m];
		a.sendNetworkUpdate();
	}
}
