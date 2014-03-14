package common.cout970.UltraTech.items;



public class RadioniteCell extends UT_Item{

	public RadioniteCell(int id,String name) {
		super(id, name);
		setMaxStackSize(1);
		this.setHasSubtypes(true);
        this.setMaxDamage(500);
        }
		
}
