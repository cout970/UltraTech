package common.cout970.UltraTech.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import common.cout970.UltraTech.TileEntities.electric.tiers.Tesseract_Entity;
import common.cout970.UltraTech.network.AbstractPacket;

public class PacketTesseract extends AbstractPacket{

	int x,y,z;
	int freq;
	
	public PacketTesseract(TileEntity t,int mode, int freq){
		x = t.xCoord;
		y = t.yCoord;
		z = t.zCoord;
		this.freq = freq;
	}
	
	public PacketTesseract(){}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {		
		PacketBuffer pb = new PacketBuffer(buffer);
		pb.writeInt(x);
		pb.writeShort(y);
		pb.writeInt(z);
		pb.writeInt(freq);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {		
		PacketBuffer pb = new PacketBuffer(buffer);
		x = pb.readInt();
		y = pb.readShort();
		z = pb.readInt();
		freq = pb.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		Tesseract_Entity a = (Tesseract_Entity) player.worldObj.getTileEntity(x, y, z);
		a.setFrequency(freq);
	}

}
