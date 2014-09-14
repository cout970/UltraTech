package common.cout970.UltraTech.handlers;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fluids.FluidRegistry;

import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.managers.OreGeneration;

import cpw.mods.fml.common.IWorldGenerator;

public class UltratechWorldGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		switch(world.provider.dimensionId){
		case -1:
		    generateNether(world, random, chunkX * 16, chunkZ * 16);
		    break;
		case 1:
//		    generateEnd(world, random, chunkX * 16, chunkZ * 16);
		    break;
		default:{
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			int prob = OreGeneration.OilBase;
			if(world.getBiomeGenForCoords(chunkX * 16, chunkZ * 16).biomeName.equalsIgnoreCase("Desert"))prob = OreGeneration.OilDesert;
			else if(world.getBiomeGenForCoords(chunkX * 16, chunkZ * 16).biomeName.equalsIgnoreCase("Deep Ocean"))prob = OreGeneration.OilDeepOcean;
			else if(world.getBiomeGenForCoords(chunkX * 16, chunkZ * 16).biomeName.equalsIgnoreCase("Ocean"))prob = OreGeneration.OilOcean;
			if(OreGeneration.GenerateOil && random.nextInt(prob) == 0){
				generateOil(random, chunkX * 16, chunkZ * 16, world);
			}
		}
		}
	}

	private void generateNether(World world, Random random, int i, int j) {
		if(OreGeneration.Radionite){
			for(int k = 0; k < 8; k++) {
				int firstBlockXCoord = i + random.nextInt(16);
				int firstBlockYCoord = random.nextInt(100);
				int firstBlockZCoord = j + random.nextInt(16);
				(new WorldGenMinable(BlockManager.Ores,0,4,Blocks.netherrack)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
			}
		}
		if(OreGeneration.Sulfur){
			for(int k = 0; k < 5; k++) {
				int firstBlockXCoord = i + random.nextInt(16);
				int firstBlockYCoord = random.nextInt(10)+30;
				int firstBlockZCoord = j + random.nextInt(16);
				(new WorldGenMinable(BlockManager.Ores,6,12,Blocks.netherrack)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
			}
			int firstBlockXCoord = i + random.nextInt(16);
			int firstBlockYCoord = random.nextInt(100);
			int firstBlockZCoord = j + random.nextInt(16);
			(new WorldGenMinable(BlockManager.Ores,6,20,Blocks.netherrack)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		}
	}

	private void generateSurface(World world, Random random, int i, int j) {
		Block id = BlockManager.Ores;
		
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
			(new WorldGenMinable(id,4,OreGeneration.amountSilver,Blocks.stone)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
			if(silver)
			(new WorldGenMinable(id,5,OreGeneration.amountLead,Blocks.stone)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		}
	}
	
	public void genOre(World world, Random random, int i, int j, int units, Block id, int meta, int amount, int maxheight){
		
		for(int k = 0; k < units; k++) {
			int firstBlockXCoord = i + random.nextInt(16);
			int firstBlockYCoord = random.nextInt(maxheight);
			int firstBlockZCoord = j + random.nextInt(16);
			(new WorldGenMinable(id,meta,amount,Blocks.stone)).generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
		}
	}
	
	public void generateShip(Random r,int x, int y,World w){
		System.out.println("gen");
		int j = 150;//+ r.nextInt(20);
		for(int l = -2;l<3;l++){
			if(l == -2 || l == 2){
				for(int i=0;i<5;i++)for(int k=0;k<5;k++)w.setBlock(x+i, j+l, y+k,BlockManager.obaltiBlock);
			}else{
				for(int i=0;i<5;i++)for(int k=0;k<5;k++){
					if(i==0 || i==4 || k==0 || k==4)w.setBlock(x+i, j+l, y+k,BlockManager.obaltiBlock);
				}
			}
		}
	}
	

	private void generateOil(Random r, int x, int y, World w) {
		
		int j = 10+ r.nextInt(20);
		boolean gen = false;
		for(int h = j-5;h<j+5;h++){
			Block b = w.getBlock(x, h, y);
			if(b == Blocks.stone){
				gen = true;
				break;
			}
		}
		if(!gen)return;
		int ran = r.nextInt(100);
		int t = 7;
		if(ran%2 == 0)t = 10;
		if(ran%7 == 0)t = 18;
		for(int l =-t/4-4;l<t/4+4;l++){
			for(int s=-t;s<t+1;s++)
				for(int d=-t*4;d<t*4+1;d++){
					double b = Math.sqrt(s*s+d*d*0.25+l*l*4);
					b = Math.abs(b);
					if(b<t)w.setBlock(x+s, j+l, y+d,FluidRegistry.getFluid("oil").getBlock(), 0, 6);
						else if(b < t+5){
						w.setBlock(x+s, j+l, y+d,Blocks.stone, 0, 6);
					}
				}
		}
		int max = w.getHeightValue(x, y)+6;
		for(int h=j;h<max;h++){
			w.setBlock(x, h, y,FluidRegistry.getFluid("oil").getBlock(), 0, 6);
		}
	}
}
