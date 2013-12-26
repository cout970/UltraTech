package common.cout970.UltraTech.machines.blocks;

import java.util.Random;

import common.cout970.UltraTech.core.UltraTech;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;

public class CovedGlass extends BlockBreakable{

	public Icon x1;//null
	public Icon x11;
	public Icon x2;
	public Icon x3;
	public Icon x4;
	public Icon x5;
	
	public Icon c1;
	public Icon c2;
	public Icon c3;
	public Icon c4;
	public Icon c5;
	public Icon c6;
	public Icon c7;
	public Icon c8;
	
	public Icon c31;
	public Icon c41;
	public Icon c51;
	public Icon c61;
	
	public Icon b1;
	public Icon b2;
	public Icon b3;
	public Icon b4;
	
	public CovedGlass(int par1, Material par3Material,
			boolean par4) {
		super(par1, "CovedGlass", par3Material, par4);
		setCreativeTab(UltraTech.techTab);
		setHardness(0.2f);
		setStepSound(soundGlassFootstep);
		setResistance(500);
		setUnlocalizedName("CovedGlass");
		
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon("ultratech:coved/dglass");
		
		this.x11 = iconRegister.registerIcon("ultratech:coved/dglassx11");
		this.x1 = iconRegister.registerIcon("ultratech:coved/dglassx1");
		this.x2 = iconRegister.registerIcon("ultratech:coved/dglassx2");
		this.x3 = iconRegister.registerIcon("ultratech:coved/dglassx3");
		this.x4 = iconRegister.registerIcon("ultratech:coved/dglassx4");
		this.x5 = iconRegister.registerIcon("ultratech:coved/dglassx5");
		this.c1 = iconRegister.registerIcon("ultratech:coved/dglassc1");
		this.c2 = iconRegister.registerIcon("ultratech:coved/dglassc2");
		this.c3 = iconRegister.registerIcon("ultratech:coved/dglassc3");
		this.c4 = iconRegister.registerIcon("ultratech:coved/dglassc4");
		this.c5 = iconRegister.registerIcon("ultratech:coved/dglassc5");
		this.c6 = iconRegister.registerIcon("ultratech:coved/dglassc6");
		this.b1 = iconRegister.registerIcon("ultratech:coved/dglassb1");
		this.b2 = iconRegister.registerIcon("ultratech:coved/dglassb2");
		this.b3 = iconRegister.registerIcon("ultratech:coved/dglassb3");
		this.b4 = iconRegister.registerIcon("ultratech:coved/dglassb4");
		
		this.c31 = iconRegister.registerIcon("ultratech:coved/dglassc31");
		this.c41 = iconRegister.registerIcon("ultratech:coved/dglassc41");
		this.c51 = iconRegister.registerIcon("ultratech:coved/dglassc51");
		this.c61 = iconRegister.registerIcon("ultratech:coved/dglassc61");
	}
	
	/**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 0;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

	@Override
	public Icon getBlockTexture(IBlockAccess BA, int x, int y, int z, int side)
    {
		int w = BA.getBlockId(x, y, z-1);
		int s = BA.getBlockId(x, y, z+1);
		int a = BA.getBlockId(x-1, y, z);
		int d = BA.getBlockId(x+1, y, z);
		
		int u = BA.getBlockId(x, y+1, z);
		int dw = BA.getBlockId(x, y-1, z);
		
		int q = BA.getBlockId(x-1, y, z-1);
		int z1 = BA.getBlockId(x-1, y, z+1);
		int e = BA.getBlockId(x+1, y, z-1);
		int c = BA.getBlockId(x+1, y, z+1);

		int t = BA.getBlockId(x-1, y+1, z);
		int r = BA.getBlockId(x+1, y+1, z);
		int f = BA.getBlockId(x-1, y-1, z);
		int g = BA.getBlockId(x+1, y-1, z);
		
		int o = BA.getBlockId(x, y+1, z+1);
		int i = BA.getBlockId(x, y+1, z-1);
		int k = BA.getBlockId(x, y-1, z-1);
		int l = BA.getBlockId(x, y-1, z+1);
		
		
		if(side == 1 || side == 0){
			//4 conect
			if(w != blockID && s != blockID && a != blockID && d != blockID)return this.blockIcon; 
			//1conect
			if(w == blockID && s == blockID && a == blockID && d == blockID)if(q != blockID && e != blockID && z1 != blockID && c != blockID)return x11;else return x1;
			if(w == blockID && s != blockID && a != blockID && d != blockID)return x2;
			if(w != blockID && s == blockID && a != blockID && d != blockID)return x3;
			if(w != blockID && s != blockID && a == blockID && d != blockID)return x4;
			if(w != blockID && s != blockID && a != blockID && d == blockID)return x5;
			//2conect
			if(w == blockID && s == blockID && a != blockID && d != blockID)return c1;
			if(w != blockID && s != blockID && a == blockID && d == blockID)return c2;
			if(w == blockID && s != blockID && a != blockID && d == blockID)if(e == blockID)return c3;else return c31;
			if(w == blockID && s != blockID && a == blockID && d != blockID)if(q == blockID)return c4;else return c41;
			if(w != blockID && s == blockID && a == blockID && d != blockID)if(z1 == blockID)return c5;else return c51;
			if(w != blockID && s == blockID && a != blockID && d == blockID)if(c == blockID)return c6;else return c61;
			//3 conect
			if(w != blockID && s == blockID && a == blockID && d == blockID)return b1;
			if(w == blockID && s != blockID && a == blockID && d == blockID)return b2;
			if(w == blockID && s == blockID && a != blockID && d == blockID)return b3;
			if(w == blockID && s == blockID && a == blockID && d != blockID)return b4;
		}else if(side == 2){
			//4 conect
			if(u != blockID && dw != blockID && d != blockID && a != blockID)return this.blockIcon; 
			//1conect
			if(u == blockID && dw == blockID && d == blockID && a == blockID)return x1;
			if(u == blockID && dw != blockID && d != blockID && a != blockID)return x2;
			if(u != blockID && dw == blockID && d != blockID && a != blockID)return x3;
			if(u != blockID && dw != blockID && d == blockID && a != blockID)return x4;
			if(u != blockID && dw != blockID && d != blockID && a == blockID)return x5;
			//2conect
			if(u == blockID && dw == blockID && d != blockID && a != blockID)return c1;
			if(u != blockID && dw != blockID && d == blockID && a == blockID)return c2;
			if(u == blockID && dw != blockID && d != blockID && a == blockID)if(t == blockID)return c3;else return c31;
			if(u == blockID && dw != blockID && d == blockID && a != blockID)if(r == blockID)return c4;else return c41;
			if(u != blockID && dw == blockID && d == blockID && a != blockID)if(g == blockID)return c5;else return c51;
			if(u != blockID && dw == blockID && d != blockID && a == blockID)if(f == blockID)return c6;else return c61;
			//3 conect
			if(u != blockID && dw == blockID && d == blockID && a == blockID)return b1;
			if(u == blockID && dw != blockID && d == blockID && a == blockID)return b2;
			if(u == blockID && dw == blockID && d != blockID && a == blockID)return b3;
			if(u == blockID && dw == blockID && d == blockID && a != blockID)return b4;
		}else if(side == 3){
			//4 conect
			if(u != blockID && dw != blockID && a != blockID && d != blockID)return this.blockIcon; 
			//1conect
			if(u == blockID && dw == blockID && a == blockID && d == blockID)return x1;
			if(u == blockID && dw != blockID && a != blockID && d != blockID)return x2;
			if(u != blockID && dw == blockID && a != blockID && d != blockID)return x3;
			if(u != blockID && dw != blockID && a == blockID && d != blockID)return x4;
			if(u != blockID && dw != blockID && a != blockID && d == blockID)return x5;
			//2conect
			if(u == blockID && dw == blockID && a != blockID && d != blockID)return c1;
			if(u != blockID && dw != blockID && a == blockID && d == blockID)return c2;
			if(u == blockID && dw != blockID && a != blockID && d == blockID)if(r == blockID)return c3;else return c31;
			if(u == blockID && dw != blockID && a == blockID && d != blockID)if(t == blockID)return c4;else return c41;
			if(u != blockID && dw == blockID && a == blockID && d != blockID)if(f == blockID)return c5;else return c51;
			if(u != blockID && dw == blockID && a != blockID && d == blockID)if(g == blockID)return c6;else return c61;
			//3 conect
			if(u != blockID && dw == blockID && a == blockID && d == blockID)return b1;
			if(u == blockID && dw != blockID && a == blockID && d == blockID)return b2;
			if(u == blockID && dw == blockID && a != blockID && d == blockID)return b3;
			if(u == blockID && dw == blockID && a == blockID && d != blockID)return b4;
		}else if(side == 4){
			//4 conect
			if(u != blockID && dw != blockID && w != blockID && s != blockID)return this.blockIcon; 
			//1conect
			if(u == blockID && dw == blockID && w == blockID && s == blockID)return x1;
			if(u == blockID && dw != blockID && w != blockID && s != blockID)return x2;
			if(u != blockID && dw == blockID && w != blockID && s != blockID)return x3;
			if(u != blockID && dw != blockID && w == blockID && s != blockID)return x4;
			if(u != blockID && dw != blockID && w != blockID && s == blockID)return x5;
			//2conect
			if(u == blockID && dw == blockID && w != blockID && s != blockID)return c1;
			if(u != blockID && dw != blockID && w == blockID && s == blockID)return c2;
			if(u == blockID && dw != blockID && w != blockID && s == blockID)if(o == blockID)return c3;else return c31;
			if(u == blockID && dw != blockID && w == blockID && s != blockID)if(i == blockID)return c4;else return c41;
			if(u != blockID && dw == blockID && w == blockID && s != blockID)if(k == blockID)return c5;else return c51;
			if(u != blockID && dw == blockID && w != blockID && s == blockID)if(l == blockID)return c6;else return c61;
			//3 conect
			if(u != blockID && dw == blockID && w == blockID && s == blockID)return b1;
			if(u == blockID && dw != blockID && w == blockID && s == blockID)return b2;
			if(u == blockID && dw == blockID && w != blockID && s == blockID)return b3;
			if(u == blockID && dw == blockID && w == blockID && s != blockID)return b4;
		}else if(side == 5){
			//4 conect
			if(u != blockID && dw != blockID && s != blockID && w != blockID)return this.blockIcon; 
			//1conect
			if(u == blockID && dw == blockID && s == blockID && w == blockID)return x1;
			if(u == blockID && dw != blockID && s != blockID && w != blockID)return x2;
			if(u != blockID && dw == blockID && s != blockID && w != blockID)return x3;
			if(u != blockID && dw != blockID && s == blockID && w != blockID)return x4;
			if(u != blockID && dw != blockID && s != blockID && w == blockID)return x5;
			//2conect
			if(u == blockID && dw == blockID && s != blockID && w != blockID)return c1;
			if(u != blockID && dw != blockID && s == blockID && w == blockID)return c2;
			if(u == blockID && dw != blockID && s != blockID && w == blockID)if(i == blockID)return c3;else return c31;
			if(u == blockID && dw != blockID && s == blockID && w != blockID)if(o == blockID)return c4;else return c41;
			if(u != blockID && dw == blockID && s == blockID && w != blockID)if(l == blockID)return c5;else return c51;
			if(u != blockID && dw == blockID && s != blockID && w == blockID)if(k == blockID)return c6;else return c61;
			//3 conect
			if(u != blockID && dw == blockID && s == blockID && w == blockID)return b1;
			if(u == blockID && dw != blockID && s == blockID && w == blockID)return b2;
			if(u == blockID && dw == blockID && s != blockID && w == blockID)return b3;
			if(u == blockID && dw == blockID && s == blockID && w != blockID)return b4;
		}
        return x1;
    }
}
