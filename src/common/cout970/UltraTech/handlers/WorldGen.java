package common.cout970.UltraTech.handlers;

import java.util.Random;

import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.managers.OreGeneration;
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
		
		if(OreGeneration.Aluminum)
		genOre(world, random, i, j, OreGeneration.unitsAluminum, id, 1, OreGeneration.amountAluminum, OreGeneration.heightAluminum);
		if(OreGeneration.Copper)
		genOre(world, random, i, j, OreGeneration.unitsCopper, id, 2, OreGeneration.amountCopper, OreGeneration.heightCopper);
		if(OreGeneration.Tin)
		genOre(world, random, i, j, OreGeneration.unitsTin, id, 3, OreGeneration.amountTin, OreGeneration.heightTin);
		
		boolean lead = false,silver = false;
		if(OreGeneration.Lead)lead = true;
		if(OreGeneration.Silver)silver = true;
		
		for(int k = 0; k < OreGeneration.unitsLead; k++) {
			int firstBlockXCoord = i + random.nextInt(16);
			int firstBlockYCoord = random.nextInt(OreGeneration.heightLead);
			int firstBlockZCoord = j + random.nextInt(16);
			if(lead)
			(new WorldGenMinable(id,4,OreGeneration.amountSilver,Block.stone.blockID)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
			if(silver)
			(new WorldGenMinable(id,5,OreGeneration.amountLead,Block.stone.blockID)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
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
