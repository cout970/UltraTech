package common.cout970.UltraTech.blocks.refinery;

import common.cout970.UltraTech.TileEntities.multiblocks.refinery.Refinery_Entity_Base;
import common.cout970.UltraTech.TileEntities.multiblocks.refinery.Refinery_Structure_Entity;
import common.cout970.UltraTech.client.textures.Block_Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Refinery_Structural_Block extends RefineryPartBaseBlock{

	public Refinery_Structural_Block(Material m) {
		super(m, "refinery_structure");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int m) {
		if(m == 1)return new Refinery_Structure_Entity();
		return null;
	}
	
	public void registerBlockIcons(IIconRegister IR){
		icons = new IIcon[2];
		icons[0] = IR.registerIcon(Block_Textures.REFINERY_PREFIX+name.toLowerCase()+"_off");
		icons[1] = IR.registerIcon(Block_Textures.VOID);
	}

	@Override
	public int getLayer() {
		return 2;
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }

}
