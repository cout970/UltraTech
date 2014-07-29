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

import ultratech.api.power.multipart.MultipartReference;
import common.cout970.UltraTech.TileEntities.electric.tiers.Tesseract_Entity;
import common.cout970.UltraTech.blocknormal.Deco_Block;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;

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
		}else if(obj instanceof Fluid){
			Language.put(((Fluid) obj).getUnlocalizedName(), name);
		}else{
			System.out.println("Exception: configIO not found in "+obj+" with name "+name);
		}
	}
	
	public static void put(String a, String b){
		unloc.add(a);
		name.add(b);
	}

	public static void setupLangFile(){
		File f = new File("I:/Development/Minecraft Mod 1.7.10/gradle/src/main/resources/assets/ultratech/lang/en_US.lang");
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
		Language.addName(new ItemStack(stoneblockblack,1,0), "Black Stone Block");
		Language.addName(new ItemStack(stoneblockblack,1,1), "Black Stone Bricks");
		Language.addName(new ItemStack(stoneblockblack,1,2), "Black Stone Creeper Bricks");
		Language.addName(new ItemStack(stoneblockblack,1,3), "Black Stone Chiseled Bricks");
		Language.addName(new ItemStack(stoneblockblack,1,4), "Black Stone Circle");
		Language.addName(new ItemStack(stoneblockblack,1,5), "Black Stone Clay Bricks");
		Language.addName(new ItemStack(stoneblockblack,1,6), "Black Stone Pillar");
		Language.addName(new ItemStack(stoneblockblack,1,7), "Black Stone Sandstone");
		Language.addName(new ItemStack(stoneblockblack,1,8), "Black Stone Smooth");
		
		Language.addName(new ItemStack(stoneblockwhite,1,0), "White Stone Block");
		Language.addName(new ItemStack(stoneblockwhite,1,1), "White Stone Bricks");
		Language.addName(new ItemStack(stoneblockwhite,1,2), "White Stone Creeper Bricks");
		Language.addName(new ItemStack(stoneblockwhite,1,3), "White Stone Chiseled Bricks");
		Language.addName(new ItemStack(stoneblockwhite,1,4), "White Stone Circle");
		Language.addName(new ItemStack(stoneblockwhite,1,5), "White Stone Clay Bricks");
		Language.addName(new ItemStack(stoneblockwhite,1,6), "White Stone Pillar");
		Language.addName(new ItemStack(stoneblockwhite,1,7), "White Stone Sandstone");
		Language.addName(new ItemStack(stoneblockwhite,1,8), "White Stone Smooth");
		
		Language.addName(new ItemStack(Reactor_Core,1,0), "Reactor Core");
		Language.addName(new ItemStack(Reactor_Wall,1,0), "Reactor Wall");
		Language.addName(new ItemStack(Reactor_IO,1,0), "Reactor IO");
		Language.addName(new ItemStack(Reactor_Chamber,1,0), "Reactor Chamber");
		Language.addName(new ItemStack(Reactor_Control,1,0), "Reactor Controller");
		Language.addName(new ItemStack(Reactor_Redstone,1,0), "Reactor Redstone Control");
		Language.addName(new ItemStack(Reactor_Tank,1,0), "Reactor Tank");
		Language.addName(new ItemStack(Reactor_Water,1,0), "Reactor Water Provider");

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
		
		Language.addName(new ItemStack(Painter,1,0), "3D Painter");
		Language.addName(new ItemStack(ChargeStation,1,0), "Charge Station");
		Language.addName(new ItemStack(Fermenter,1,0), "Fermenter");
		
		Language.addName(new ItemStack(CVD_T1,1,0), "CVD T1");
		Language.addName(new ItemStack(CVD_T2,1,0), "CVD T2");
		Language.addName(new ItemStack(Purifier_T1,1,0), "Purifier T1");
		Language.addName(new ItemStack(Purifier_T2,1,0), "Purifier T2");
		Language.addName(new ItemStack(Cutter_T1,1,0), "Cutter T1");
		Language.addName(new ItemStack(Cutter_T2,1,0), "Cutter T2");
		Language.addName(new ItemStack(Furnace_T1,1,0), "Furnace T1");
		Language.addName(new ItemStack(Furnace_T2,1,0), "Furnace T2");
		Language.addName(new ItemStack(Laminator_T1,1,0), "Laminator T1");
		Language.addName(new ItemStack(Laminator_T2,1,0), "Laminator T2");
		Language.addName(new ItemStack(Generator_T1,1,0), "Generator T1");
		Language.addName(new ItemStack(Generator_T2,1,0), "Generator T2");
		
		Language.addName(new ItemStack(Tier3,1,0), "Miner");
		Language.addName(new ItemStack(Tier3,1,1), "Hologram Emiter");
		Language.addName(new ItemStack(Tier3,1,2), "Precision Crafter");
		Language.addName(new ItemStack(Tier3,1,3), "Climate Station");
		Language.addName(new ItemStack(Tesseract,1), "Energy Tesseract");
		
		Language.addName(new ItemStack(Storage,1,0), "Battery Tier1");
		Language.addName(new ItemStack(Storage,1,1), "Battery Tier2");
		Language.addName(new ItemStack(Storage,1,2), "Battery Tier3");
		
		Language.addName(new ItemStack(Refinery,1,0), "Refinery Base");
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
		Language.addName(ChemicalPlant, "Chemical Plant");
		Language.addName(Crafter, "Crafter");
		Language.addName(Heater, "Heater");
		Language.addName(AlienBlock, "Alien Block");
		
		if(!MultipartReference.isMicroPartActived)Language.addName(CableBlock, "Cable");
	}

}
