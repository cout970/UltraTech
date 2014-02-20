package common.cout970.UltraTech.handlers;

import java.util.Random;

import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.managers.ConfigManager;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		switch(world.provider.dimensionId){
		case -1:
		    generateNether(world, random, chunkX * 16, chunkZ * 16);
		    break;
		case 0:
		    generateSurface(world, random, chunkX * 16, chunkZ * 16);
		    break;
		case 1:
//		    generateEnd(world, random, chunkX * 16, chunkZ * 16);
		    break;
		}
	}

	private void generateNether(World world, Random random, int i, int j) {
		for(int k = 0; k < 8; k++) {
			int firstBlockXCoord = i + random.nextInt(16);
			int firstBlockYCoord = random.nextInt(100);
			int firstBlockZCoord = j + random.nextInt(16);
			(new WorldGenMinable(BlockManager.Ores.blockID,0,4,Block.netherrack.blockID)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		}
	}

	private void generateSurface(World world, Random random, int i, int j) {
		int id = BlockManager.Ores.blockID;
		
		if(ConfigManager.gen.GenAluminum)
		genOre(world, random, i, j, 20, id, 1, 8, 100);
		if(ConfigManager.gen.GenCopper)
		genOre(world, random, i, j, 20, id, 2, 8, 100);
		if(ConfigManager.gen.GenTin)
		genOre(world, random, i, j, 20, id, 3, 6, 100);
		
		boolean lead = false,silver = false;
		if(ConfigManager.gen.GenLead)lead = true;
		if(ConfigManager.gen.GenSilver)silver = true;
		
		for(int k = 0; k < 10; k++) {
			int firstBlockXCoord = i + random.nextInt(16);
			int firstBlockYCoord = random.nextInt(20);
			int firstBlockZCoord = j + random.nextInt(16);
			if(lead)
			(new WorldGenMinable(id,4,4,Block.stone.blockID)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
			if(silver)
			(new WorldGenMinable(id,5,4,Block.stone.blockID)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		}
	}
	
	public void genOre(World world, Random random, int i, int j, int units, int id, int meta, int amount, int maxheight){
		
		for(int k = 0; k < units; k++) {
			int firstBlockXCoord = i + random.nextInt(16);
			int firstBlockYCoord = random.nextInt(maxheight);
			int firstBlockZCoord = j + random.nextInt(16);
			(new WorldGenMinable(id,meta,amount,Block.stone.blockID)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		}
	}
}
