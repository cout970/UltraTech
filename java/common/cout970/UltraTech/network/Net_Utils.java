package common.cout970.UltraTech.network;

import net.minecraft.tileentity.TileEntity;
import ultratech.api.power.multipart.MultipartReference;
import common.cout970.UltraTech.network.messages.MessageClimateStation;
import common.cout970.UltraTech.network.messages.MessageCrafter;
import common.cout970.UltraTech.network.messages.MessageHologram;
import common.cout970.UltraTech.network.messages.MessageMachineMode;
import common.cout970.UltraTech.network.messages.MessageMicroPartUpdate;
import common.cout970.UltraTech.network.messages.MessagePainter;
import common.cout970.UltraTech.network.messages.MessageReactorConfig;
import common.cout970.UltraTech.network.messages.MessageTesseract;
import common.cout970.UltraTech.network.messages.MessageUpdate;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class Net_Utils {
	
	public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("ultratech");
	
	public static void initMessages(){
		INSTANCE.registerMessage(MessageUpdate.class, MessageUpdate.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(MessageClimateStation.class, MessageClimateStation.class, 1, Side.SERVER);
		INSTANCE.registerMessage(MessageCrafter.class, MessageCrafter.class, 2, Side.SERVER);
		INSTANCE.registerMessage(MessageMachineMode.class, MessageMachineMode.class, 3, Side.SERVER);
		INSTANCE.registerMessage(MessagePainter.class, MessagePainter.class, 4, Side.SERVER);
		INSTANCE.registerMessage(MessageTesseract.class, MessageTesseract.class, 5, Side.SERVER);
		if(MultipartReference.isMicroPartActived)
		INSTANCE.registerMessage(MessageMicroPartUpdate.class, MessageMicroPartUpdate.class, 6, Side.CLIENT);
		INSTANCE.registerMessage(MessageReactorConfig.class, MessageReactorConfig.class, 7, Side.SERVER);
		INSTANCE.registerMessage(MessageHologram.class, MessageHologram.class, 8, Side.SERVER);
	}
	
	public static void sendUpdate(TileEntity t){
		if(t == null)return;
		MessageUpdate m = new MessageUpdate((SyncTile) t);
		INSTANCE.sendToAll(m);
	}	

}
