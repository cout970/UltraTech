package api.cout970.UltraTech.microparts;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.TMultiPart;
import common.cout970.UltraTech.machines.renders.items.RenderPumpItem;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.proxy.Language;
import cpw.mods.fml.common.registry.GameRegistry;

public class MicroRegistry{

	//micro
	public static Item PlaneCable;
	public static Item BigCable;

	public void load(){
		MicroRegistry.PlaneCable = new ItemPlaneCableMultipart();
		GameRegistry.registerItem(PlaneCable, "UT_Cable_Plane");
		MultiPartRegistry.registerParts(new PlaneCable(), new String[]{MicroRegistry.PlaneCable.getUnlocalizedName()});
		MinecraftForgeClient.registerItemRenderer(PlaneCable, new RenderCablePlaneItem());
		
		MicroRegistry.BigCable = new ItemBigCableMultipart();
		GameRegistry.registerItem(BigCable, "UT_Cable_Big");
		MultiPartRegistry.registerParts(new BigCable(), new String[]{MicroRegistry.BigCable.getUnlocalizedName()});
		MinecraftForgeClient.registerItemRenderer(BigCable, new RenderCableBigItem());
		
		Language.addName(PlaneCable, "Ribbon Cable");
		Language.addName(BigCable, "Big Cable");
	}
	
	
	public class PlaneCable implements IPartFactory{
		@Override
		public TMultiPart createPart(String arg0, boolean arg1) {
			return new MicroCablePlane();
		}		
	}
	
	public class BigCable implements IPartFactory{
		@Override
		public TMultiPart createPart(String arg0, boolean arg1) {
			return new MicroCableBig();
		}		
	}
}
