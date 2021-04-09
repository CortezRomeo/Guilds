package gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import color.color;
import files.guiFile;
import utils.guiItemUtils;

public class noGuild{
	
public static Inventory main(Player p) {
		
		Inventory ivng = Bukkit.createInventory((InventoryHolder)p, guiFile.get().getInt("gui.noGuild.rows")*9, color.add(guiFile.get().getString("gui.noGuild.title")));
        
		if(guiFile.get().getBoolean("gui.noGuild.enableBorder")==true) {
			border.enableBorder(guiFile.get().getInt("gui.noGuild.rows"), ivng);		
		}
		
        ivng.setItem(guiFile.get().getInt("gui.noGuild.items.create.slot"),
        		guiItemUtils.getItem(
        			guiFile.get().getString("gui.noGuild.items.create.type"),
        			guiFile.get().getString("gui.noGuild.items.create.value"),(short)
        			guiFile.get().getInt("gui.noGuild.items.create.id"),
        			guiFile.get().getString("gui.noGuild.items.create.name"),"",
        			guiFile.get().getStringList(("gui.noGuild.items.create.lore")),
        			"",null));              
              		
        ivng.setItem(guiFile.get().getInt("gui.noGuild.items.listGuild.slot"),
        		guiItemUtils.getItem(
	        		guiFile.get().getString("gui.noGuild.items.listGuild.type"),
	        		guiFile.get().getString("gui.noGuild.items.listGuild.value"),(short)
					guiFile.get().getInt("gui.noGuild.items.listGuild.id"),
					guiFile.get().getString("gui.noGuild.items.listGuild.name"),"",
					guiFile.get().getStringList(("gui.noGuild.items.listGuild.lore")),
					"",null));      
        
        ivng.setItem(guiFile.get().getInt("gui.noGuild.items.close.slot"), 
        		guiItemUtils.getItem(
	        		guiFile.get().getString("gui.noGuild.items.close.type"),
	        		guiFile.get().getString("gui.noGuild.items.close.value"),(short)
					guiFile.get().getInt("gui.noGuild.items.close.id"),
					guiFile.get().getString("gui.noGuild.items.close.name"),"",
					guiFile.get().getStringList(("gui.noGuild.items.close.lore")),
					"",null));          
		
		return ivng;
	}			
}
