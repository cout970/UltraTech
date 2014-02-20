package common.cout970.UltraTech.machines.blocks;


import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.Machine;
import common.cout970.UltraTech.machines.tileEntities.SenderEntity;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Sender extends BlockContainer{

	public Sender(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
		setUnlocalizedName("Sender");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new SenderEntity();
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:sender");
	}

	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		TileEntity te = w.getBlockTileEntity(x, y, z);
		if(te != null && !w.isRemote){
			if(te instanceof SenderEntity){
				SenderEntity r = (SenderEntity)te;
				r.onNeighChange();
			}
		}
		Machine m = (Machine) w.getBlockTileEntity(x, y, z);
		m.updateMachine(w, x, y, z);
		}
	
}
