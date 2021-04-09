package gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import files.guiFile;
import utils.guiItemUtils;

public class border {
	
	public static ItemStack borderItem() {
		
        ItemStack borderI=guiItemUtils.getItem(
				guiFile.get().getString("borderItem.type"),
				guiFile.get().getString("borderItem.value"),(short)
				guiFile.get().getInt("borderItem.id"),
				guiFile.get().getString("borderItem.name"),"",
				guiFile.get().getStringList(("borderItem.lore")),
				"",null);
		
		return borderI;
	}
	public static void enableBorder(int Rows, Inventory inv) {
		if(Rows==6) {
		    for (int i = 0; i < 54; i++)
		        inv.setItem(i, borderItem());
		} else if(Rows==5) {
		    for (int i = 0; i < 45; i++)
		        inv.setItem(i, borderItem());
		} else if(Rows==4) {
		    for (int i = 0; i < 36; i++)
		        inv.setItem(i, borderItem());
		} else if(Rows==3) {
		    for (int i = 0; i < 27; i++)
		        inv.setItem(i, borderItem());
		} else if(Rows==2) {
		    for (int i = 0; i < 18; i++)
		        inv.setItem(i, borderItem());
		} else if(Rows==1) {
		    for (int i = 0; i < 9; i++)
		        inv.setItem(i, borderItem());
		} 	
	}
}
