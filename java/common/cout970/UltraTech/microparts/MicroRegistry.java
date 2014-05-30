package common.cout970.UltraTech.microparts;

import net.minecraft.item.Item;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.TMultiPart;
import cpw.mods.fml.common.registry.GameRegistry;

public class MicroRegistry implements IPartFactory{

	//micro
	public static Item Cable;

	public void load(){
		MicroRegistry.Cable = new ItemCableMultipart();
		GameRegistry.registerItem(Cable, "UT_Cable");
		MultiPartRegistry.registerParts(this, new String[]{MicroRegistry.Cable.getUnlocalizedName()});
	}
	
	@Override
	public TMultiPart createPart(String arg0, boolean arg1) {
		return new MicroCable();
	}

}
