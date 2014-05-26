package common.cout970.UltraTech.proxy;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.output.WriterOutputStream;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Language {
	
	public static Map<String,String> lang = new HashMap<String,String>();
	
	public static void addName(Object obj,String name){
		if(obj == null)return;
		if(obj instanceof ItemStack){
			Language.lang.put(((ItemStack) obj).getUnlocalizedName(), name);
		}else if(obj instanceof Block){
			Language.lang.put(((Block) obj).getUnlocalizedName(), name);
		}else if(obj instanceof Item){
			Language.lang.put(((Item) obj).getUnlocalizedName(), name);
		}
	}
	
	public static void setupLangFile(){
		File f = new File("./assets/lang/en_US.lang");
		if(f.exists())return;
	}

}
