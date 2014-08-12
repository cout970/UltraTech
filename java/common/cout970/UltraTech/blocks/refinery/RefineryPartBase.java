package common.cout970.UltraTech.blocks.refinery;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import ultratech.api.refinery.IRefineryBlock;

import common.cout970.UltraTech.TileEntities.multiblocks.refinery.RefineryMultiblockTweak;
import common.cout970.UltraTech.client.textures.Block_Textures;
import common.cout970.UltraTech.managers.UT_Tabs;
import common.cout970.UltraTech.misc.IUpdatedEntity;

public abstract class RefineryPartBase extends BlockContainer implements IRefineryBlock{

	public String name;
	public IIcon[] icons;
	
	protected RefineryPartBase(Material m,String name) {
		super(m);
		setCreativeTab(UT_Tabs.techTab);
		setHardness(2f);
		setStepSound(soundTypeMetal);
		setBlockName(name);
		this.name = name;
	}
	
	public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[2];
		icons[0] = IR.registerIcon(Block_Textures.REFINERY_PREFIX+name.toLowerCase()+"_off");
		icons[1] = IR.registerIcon(Block_Textures.REFINERY_PREFIX+name.toLowerCase()+"_on");
	}
	
	public IIcon getIcon(int side, int meta){
		if(meta == 0)	return icons[0];
		else 			return icons[1];
	}
	
	public void onNeighborBlockChange(World w, int x, int y, int z, Block block){
		TileEntity t = w.getTileEntity(x, y, z);
		if(t instanceof IUpdatedEntity)((IUpdatedEntity) t).onNeigUpdate();
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int a, float b, float c, float d){
		if(p.isSneaking())RefineryMultiblockTweak.onStructureActivate(w,x,y,z,this);
		return false;
	}
	
	public void onBlockAdded(World w, int x, int y, int z)
    {
		RefineryMultiblockTweak.onStructureActivate(w,x,y,z,this);
        super.onBlockAdded(w, x, y, z);
    }

	public void breakBlock(World w, int x, int y, int z, Block b, int meta)
    {
		if(meta != 0)
			RefineryMultiblockTweak.onStructureDesactivate(w,x,y,z,this);
        super.breakBlock(w, x, y, z, b, meta);
    }
}
