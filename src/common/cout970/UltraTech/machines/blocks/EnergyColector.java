package common.cout970.UltraTech.machines.blocks;


import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.machines.tileEntities.EnergyColectorEntity;
import common.cout970.UltraTech.machines.tileEntities.Machine;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class EnergyColector extends BlockContainer{

	private Icon blockIcon3;

	public EnergyColector(int par1, Material par2Material) {
		super(par1, par2Material);
		setCreativeTab(UltraTech.techTab);
		setHardness(1.5f);
		setStepSound(soundMetalFootstep);
		setResistance(30);
		setUnlocalizedName("satelite");		
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new EnergyColectorEntity();
	}

	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:energycolector");
		this.blockIcon3 = iconRegister.registerIcon("ultratech:machinechasis");
	}
	
	@Override
	public Icon getIcon(int side,int a)
    {
		return side != 0 ?  this.blockIcon : this.blockIcon3;
    }
	
	public void onNeighborBlockChange(World w, int x, int y, int z, int side){
		Machine m = (Machine) w.getBlockTileEntity(x, y, z);
		m.updateMachine(w, x, y, z);
	}
	
}
