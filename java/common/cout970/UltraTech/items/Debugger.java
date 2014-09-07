package common.cout970.UltraTech.items;

import com.google.common.collect.Multimap;

import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.fluids.FluidUtils;
import common.cout970.UltraTech.util.fluids.IFluidTransport;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import ultratech.api.power.NetworkManagerRegistry;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.IPowerConductor;
import ultratech.api.power.interfaces.IStorageItem;

public class Debugger extends UT_Item{

	public Debugger(String name) {
		super(name);
		this.setMaxDamage(1);
	}
	
	public boolean onItemUse(ItemStack item, EntityPlayer p, World w, int x, int y, int z, int side, float hitx, float hity, float hitz)
    {
		TileEntity t = w.getTileEntity(x, y, z);
		if(t != null){
			for(ICable c : NetworkManagerRegistry.getConnections(t)){
				String s = "";
				s += "Energy: "+c.getPower().getCharge();
				if(!w.isRemote && c.getPower().getNetwork() != null){
					s += " Network: "+c.getPower().getNetwork().getConductors().size();
				}
				p.addChatMessage(new ChatComponentText((w.isRemote ? "Client:  " : "Server:")+" "+s));
			}
			IFluidTransport ft = FluidUtils.getFluidTransport(t);
			if(ft != null){
				String s = "";
				if(ft.getNetwork() != null){
					s+=ft.getNetwork().getPipes().size();
					s += " amount: "+ft.getTank().getFluidAmount();
				}else{
					s += "no Network";
				}
				p.addChatMessage(new ChatComponentText((w.isRemote ? "Client:  " : "Server:")+" "+s));
			}
			
		}

		return false;
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Multimap getAttributeModifiers(ItemStack a)
	{
		Multimap multimap = super.getAttributeModifiers(a);
		if(a.stackSize == 5)
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 9999d, 0));
		return multimap;
	}
}
