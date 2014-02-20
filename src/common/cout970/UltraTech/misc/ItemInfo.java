package common.cout970.UltraTech.misc;

public class ItemInfo {

	public int id;
	public String name;
	public String GameName;
	public ItemTipe tipe;
	
	public ItemInfo(){
		
	}
	
	public enum ItemTipe{
		DEFAULT,TOOL
	}
}
