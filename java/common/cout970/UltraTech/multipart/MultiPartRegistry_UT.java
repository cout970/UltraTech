package common.cout970.UltraTech.multipart;

import ultratech.api.power.NetworkManagerRegistry;
import ultratech.api.power.multipart.MultipartNetworkManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.TMultiPart;
import common.cout970.UltraTech.client.renderItems.RenderPumpItem;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.managers.Language;
import common.cout970.UltraTech.multipart.client.ItemBigCableMultipart;
import common.cout970.UltraTech.multipart.client.ItemPlaneCableMultipart;
import common.cout970.UltraTech.multipart.client.RenderCableBigItem;
import common.cout970.UltraTech.multipart.client.RenderCablePlaneItem;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

public class MultiPartRegistry_UT{

	//micro
	public static Item PlaneCable;
	public static Item BigCable;

	public void load(){
		MultiPartRegistry_UT.PlaneCable = new ItemPlaneCableMultipart();
		GameRegistry.registerItem(PlaneCable, "UT_Cable_Plane");
		MultiPartRegistry.registerParts(new PlaneCable(), new String[]{MultiPartRegistry_UT.PlaneCable.getUnlocalizedName()});
		
		MultiPartRegistry_UT.BigCable = new ItemBigCableMultipart();
		GameRegistry.registerItem(BigCable, "UT_Cable_Big");
		MultiPartRegistry.registerParts(new BigCable(), new String[]{MultiPartRegistry_UT.BigCable.getUnlocalizedName()});

		NetworkManagerRegistry.setPathFinder(new MultipartNetworkManager());
		
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT){
			MinecraftForgeClient.registerItemRenderer(PlaneCable, new RenderCablePlaneItem());
			MinecraftForgeClient.registerItemRenderer(BigCable, new RenderCableBigItem());
		}
	}
	
	
	public class PlaneCable implements IPartFactory{
		@Override
		public TMultiPart createPart(String arg0, boolean arg1) {
			return new MultiPartCable_Ribbon_Down();
		}		
	}
	
	public class BigCable implements IPartFactory{
		@Override
		public TMultiPart createPart(String arg0, boolean arg1) {
			return new MultiPartCable_Big();
		}		
	}
}
