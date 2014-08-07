package common.cout970.UltraTech.microparts;

import ultratech.api.power.CableType;
import ultratech.api.power.IPowerConductor;
import ultratech.api.power.PathFinderRegistry;
import ultratech.api.power.PowerInterface;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.util.ForgeDirection;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.NormallyOccludedPart;
import codechicken.multipart.TileMultipart;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.TMultiPart;
import common.cout970.UltraTech.client.renderItems.RenderPumpItem;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.managers.Language;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

public class MicroRegistry{

	//micro
	public static Item PlaneCable;
	public static Item BigCable;

	public void load(){
		MicroRegistry.PlaneCable = new ItemPlaneCableMultipart();
		GameRegistry.registerItem(PlaneCable, "UT_Cable_Plane");
		MultiPartRegistry.registerParts(new PlaneCable(), new String[]{MicroRegistry.PlaneCable.getUnlocalizedName()});
		
		MicroRegistry.BigCable = new ItemBigCableMultipart();
		GameRegistry.registerItem(BigCable, "UT_Cable_Big");
		MultiPartRegistry.registerParts(new BigCable(), new String[]{MicroRegistry.BigCable.getUnlocalizedName()});

		PathFinderRegistry.setPathFinder(new MicroPartPathFinder());
		
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT){
			MinecraftForgeClient.registerItemRenderer(PlaneCable, new RenderCablePlaneItem());
			MinecraftForgeClient.registerItemRenderer(BigCable, new RenderCableBigItem());
		}
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
	
	public static boolean canConect(MicroCablePlane m,
			TileEntity tile, ForgeDirection o) {
		if(tile instanceof IPowerConductor){
			PowerInterface p = ((IPowerConductor) tile).getPower();
			return p.isConnectableSide(o.getOpposite(), CableType.RIBBON_BOTTOM);
		}else if(tile instanceof TileMultipart){
			TileMultipart t = (TileMultipart) tile;
			for(TMultiPart s : t.jPartList()){
				if(s instanceof MicroCablePlane){
					return t.canAddPart(new NormallyOccludedPart(MicroCablePlane.boundingBoxes[o.getOpposite().ordinal()]));
				}
			}
		}
		return false;
	}
	
	public static boolean canConect(MicroCableBig m,
			TileEntity tile, ForgeDirection o) {
		if(tile instanceof IPowerConductor){
			PowerInterface p = ((IPowerConductor) tile).getPower();
			return p.isConnectableSide(o.getOpposite(), CableType.BIG_CENTER);
		}else if(tile instanceof TileMultipart){
			TileMultipart t = (TileMultipart) tile;
			for(TMultiPart s : t.jPartList()){
				if(s instanceof MicroCableBig){
					return t.canAddPart(new NormallyOccludedPart(MicroCableBig.boundingBoxes[o.getOpposite().ordinal()]));
				}
			}
		}
		return false;
	}
}
