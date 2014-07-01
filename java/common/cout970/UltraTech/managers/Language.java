package common.cout970.UltraTech.managers;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.cout970.UltraTech.managers.BlockManager.*;

import org.apache.commons.io.output.WriterOutputStream;

import common.cout970.UltraTech.blocks.Deco_Block;
import common.cout970.UltraTech.lib.Control;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Language {
	
	public static List<String> unloc = new ArrayList<String>();
	public static List<String> name = new ArrayList<String>();
	private final static String[] colors = {"White","Black","Blue","Steal Blue","Cian","Sea Green","Green","Light Green","Yellow","Orange","Red","Purple","Pink","Blue Violet"};
	
	public static void addName(Object obj,String name){
		if(obj == null)return;
		if(obj instanceof ItemStack){
			Language.put(((ItemStack) obj).getUnlocalizedName(), name);
		}else if(obj instanceof Block){
			Language.put(((Block) obj).getUnlocalizedName(), name);
		}else if(obj instanceof Item){
			Language.put(((Item) obj).getUnlocalizedName(), name);
		}
	}
	
	public static void put(String a, String b){
		unloc.add(a);
		name.add(b);
	}

	public static void setupLangFile(){
		File f = new File("I:/Development/Minecraft Mod 1.7.2/gradle/src/main/resources/assets/ultratech/lang/en_US.lang");
		Writer w;
		try {
			w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
			for(String s : unloc){
				w.write(s+".name="+name.get(unloc.indexOf(s))+"\n");
			}
			w.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void AddBlockNames(){
		
		for(Deco_Block b : deco){
			for(int meta=0;meta<14;meta++)Language.addName(new ItemStack(b,1,meta), "Deco Block "+colors[meta]);
		}
		Language.addName(new ItemStack(stoneblock,1,0), "Stone Block Black");
		Language.addName(new ItemStack(stoneblock,1,1), "Stone Bricks Black");
		Language.addName(new ItemStack(stoneblock,1,2), "Stone Block White");
		Language.addName(new ItemStack(stoneblock,1,3), "Stone Bricks White");
		
		Language.addName(new ItemStack(Reactor,1,0), "Reactor Core");
		Language.addName(new ItemStack(Reactor,1,1), "Reactor Wall");
		Language.addName(new ItemStack(Reactor,1,2), "Reactor Tank");
		Language.addName(new ItemStack(Reactor,1,3), "Reactor Controller");
		Language.addName(new ItemStack(Reactor,1,4), "Reactor Water Provider");
		Language.addName(new ItemStack(Reactor,1,5), "Reactor Steam Extractor");

		Language.addName(new ItemStack(Ores,1,0), "Radionite Ore");
		Language.addName(new ItemStack(Ores,1,1), "Aluminum Ore");
		Language.addName(new ItemStack(Ores,1,2), "Copper Ore");
		Language.addName(new ItemStack(Ores,1,3), "Tin Ore");
		Language.addName(new ItemStack(Ores,1,4), "Lead Ore");
		Language.addName(new ItemStack(Ores,1,5), "Silver Ore");
		Language.addName(new ItemStack(Ores,1,6), "Sulfur Ore");
		
		Language.addName(new ItemStack(Chasis,1,0), "Machine Chasis MK1");
		Language.addName(new ItemStack(Chasis,1,1), "Machine Chasis MK2");
		Language.addName(new ItemStack(Chasis,1,2), "Machine Chasis MK3");
		
//		Language.addName(new ItemStack(Tier1,1,0), "Crafter");
//		Language.addName(new ItemStack(Tier1,1,1), "Generator");
//		Language.addName(new ItemStack(Tier1,1,2), "CVD");
//		Language.addName(new ItemStack(Tier1,1,3), "3D Printer");
//		Language.addName(new ItemStack(Tier1,1,4), "Charge Station");
//		Language.addName(new ItemStack(Tier1,1,5), "Fermenter");
//		Language.addName(new ItemStack(Tier1,1,6), "Cooling block");
//		
//		Language.addName(new ItemStack(Tier2,1,0), "Furnace");
//		Language.addName(new ItemStack(Tier2,1,1), "Purifier");
//		Language.addName(new ItemStack(Tier2,1,2), "Cutter");
//		Language.addName(new ItemStack(Tier2,1,3), "Pressurizer WIP");
//		Language.addName(new ItemStack(Tier2,1,4), "Fluid Firebox");
		
		Language.addName(new ItemStack(Tier3,1,0), "Miner");
		Language.addName(new ItemStack(Tier3,1,1), "Hologram Emiter");
		Language.addName(new ItemStack(Tier3,1,2), "Precision Crafter");
		Language.addName(new ItemStack(Tier3,1,3), "Climate Station");
		Language.addName(new ItemStack(Tier3,1,4), "Tesseract");
		
		Language.addName(new ItemStack(Storage,1,0), "Battery Tier1");
		Language.addName(new ItemStack(Storage,1,1), "Battery Tier2");
		Language.addName(new ItemStack(Storage,1,2), "Battery Tier3");
		
		Language.addName(new ItemStack(Refinery,1,0), "Refinery Base WIP");
		Language.addName(new ItemStack(Refinery,1,1), "Refinery Structure Block");
		Language.addName(new ItemStack(Refinery,1,2), "Refinery Core");
		Language.addName(new ItemStack(Refinery,1,3), "Refinery Structure Block");
		Language.addName(new ItemStack(Refinery,1,4), "Refinery Output");
		Language.addName(new ItemStack(Refinery,1,5), "Refinery Base");
		
		Language.addName(new ItemStack(Misc,1,0), "Radionite Block");
		Language.addName(new ItemStack(Misc,1,1), "Grafeno Block");

		Language.addName(DiamondGlass, "Diamond Glass");
		Language.addName(CovedGlass, "Coved Glass");
		
		Language.addName(CopperPipe, "Copper Pipe");
		
		Language.addName(SolarPanel, "Solar Panel");
		Language.addName(WindMill, "Wind Mill");
		Language.addName(Engine, "Engine");

		Language.addName(Boiler, "Boiler");
		Language.addName(Tank, "Tank");
		Language.addName(Turbine, "Steam Turbine");
		Language.addName(Pump, "Pump");
		Language.addName(Dynamo, "FT Dynamo");
		Language.addName(MultiTank, "MultiTank WIP");
		Language.addName(LavaGenerator, "Lava Generator");
//		Language.addName(Laminator, "Laminator");
		
		if(!Control.isMicroPartActived)Language.addName(CableBlock, "Cable");
	}

}
