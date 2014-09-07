package common.cout970.UltraTech.multipart;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import ultratech.api.power.NetworkManagerRegistry;
import ultratech.api.power.multipart.MultipartNetworkManager;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.TMultiPart;
import common.cout970.UltraTech.multipart.client.ItemBigCableMultipart;
import common.cout970.UltraTech.multipart.client.ItemCopperPipeMultipart;
import common.cout970.UltraTech.multipart.client.ItemPlaneCableMultipart;
import common.cout970.UltraTech.multipart.client.RenderCableBigItem;
import common.cout970.UltraTech.multipart.client.RenderCablePlaneItem;
import common.cout970.UltraTech.multipart.client.RenderPipeCopperItem;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_Down;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_East;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_North;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_South;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_Up;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_West;
import common.cout970.UltraTech.network.Net_Utils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

public class MultiPartRegistry_UT{

	//micro
	public static Item PlaneCable;
	public static Item BigCable;
	public static Item CopperPipe;

	public void load(){
		//plane
		MultiPartRegistry_UT.PlaneCable = new ItemPlaneCableMultipart();
		GameRegistry.registerItem(PlaneCable, "UT_Cable_Plane");
		MultiPartRegistry.registerParts(new PlaneCable(), new String[]{MultiPartRegistry_UT.PlaneCable.getUnlocalizedName()+".down",MultiPartRegistry_UT.PlaneCable.getUnlocalizedName()+".up",
			MultiPartRegistry_UT.PlaneCable.getUnlocalizedName()+".north",MultiPartRegistry_UT.PlaneCable.getUnlocalizedName()+".south",
			MultiPartRegistry_UT.PlaneCable.getUnlocalizedName()+".east",MultiPartRegistry_UT.PlaneCable.getUnlocalizedName()+".west"});
		//big
		MultiPartRegistry_UT.BigCable = new ItemBigCableMultipart();
		GameRegistry.registerItem(BigCable, "UT_Cable_Big");
		MultiPartRegistry.registerParts(new BigCable(), new String[]{MultiPartRegistry_UT.BigCable.getUnlocalizedName()});

		//pipe
		MultiPartRegistry_UT.CopperPipe = new ItemCopperPipeMultipart();
		GameRegistry.registerItem(CopperPipe, "UT_Pipe_Copper");
		MultiPartRegistry.registerParts(new PipeCopper(), new String[]{MultiPartRegistry_UT.CopperPipe.getUnlocalizedName()});
		
		NetworkManagerRegistry.setPathFinder(new MultipartNetworkManager());
		
		Net_Utils.initMessagesFMP();
		
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT){
			MinecraftForgeClient.registerItemRenderer(PlaneCable, new RenderCablePlaneItem());
			MinecraftForgeClient.registerItemRenderer(BigCable, new RenderCableBigItem());
			MinecraftForgeClient.registerItemRenderer(CopperPipe, new RenderPipeCopperItem());
		}
	}
	
	
	public class PlaneCable implements IPartFactory{
		@Override
		public TMultiPart createPart(String arg0, boolean arg1) {
			if((MultiPartRegistry_UT.PlaneCable.getUnlocalizedName()+".up").equalsIgnoreCase(arg0))
				return new MultiPartCable_Ribbon_Up();
			if((MultiPartRegistry_UT.PlaneCable.getUnlocalizedName()+".north").equalsIgnoreCase(arg0))
				return new MultiPartCable_Ribbon_North();
			if((MultiPartRegistry_UT.PlaneCable.getUnlocalizedName()+".south").equalsIgnoreCase(arg0))
				return new MultiPartCable_Ribbon_South();
			if((MultiPartRegistry_UT.PlaneCable.getUnlocalizedName()+".east").equalsIgnoreCase(arg0))
				return new MultiPartCable_Ribbon_East();
			if((MultiPartRegistry_UT.PlaneCable.getUnlocalizedName()+".west").equalsIgnoreCase(arg0))
				return new MultiPartCable_Ribbon_West();
			return new MultiPartCable_Ribbon_Down();
		}		
	}
	
	public class BigCable implements IPartFactory{
		@Override
		public TMultiPart createPart(String arg0, boolean arg1) {
			return new MultiPartCable_Big();
		}		
	}
	
	public class PipeCopper implements IPartFactory{
		@Override
		public TMultiPart createPart(String arg0, boolean arg1) {
			return new MultiPartPipe_Copper();
		}		
	}
}
