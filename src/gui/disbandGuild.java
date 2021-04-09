package gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import color.color;
import files.guiFile;
import utils.guiItemUtils;

public class disbandGuild{
	
public static Inventory main(Player p) {
		
		Inventory ivng = Bukkit.createInventory((InventoryHolder)p,
				guiFile.get().getInt("gui.disband.rows")*9,
				color.add(guiFile.get().getString("gui.disband.title")));
        
		if(guiFile.get().getBoolean("gui.disband.enableBorder")==true) {
			border.enableBorder(guiFile.get().getInt("gui.disband.rows"), ivng);		
		}
		
        ivng.setItem(guiFile.get().getInt("gui.disband.items.seconds!=0.slot"),
        		guiItemUtils.getItem(
        			guiFile.get().getString("gui.disband.items.seconds!=0.type"),
        			guiFile.get().getString("gui.disband.items.seconds!=0.value"),(short)
        			guiFile.get().getInt("gui.disband.items.seconds!=0.id"),
        			guiFile.get().getString("gui.disband.items.seconds!=0.name"),"",
        			guiFile.get().getStringList(("gui.disband.items.seconds!=0.lore")),
        			"",null));              
              		     
		
		return ivng;
	}			
}
