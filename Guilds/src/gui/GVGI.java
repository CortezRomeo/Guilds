package gui;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import color.color;
import files.guiFile;
import utils.guiItemUtils;

public class GVGI{	
	
public static Inventory main(Player p, String guildName) {
		
		Inventory ivng = Bukkit.createInventory((InventoryHolder)p, guiFile.get().getInt("gui.GuestViewGuildInfo.rows")*9, 
				color.add(guiFile.get().getString("gui.GuestViewGuildInfo.title").replace("%guildName%", guildName)));
        
		if(guiFile.get().getBoolean("gui.GuestViewGuildInfo.enableBorder")==true) {
			border.enableBorder(guiFile.get().getInt("gui.GuestViewGuildInfo.rows"), ivng);		
		}
		
		File folder2 = new File("plugins/Guilds/guilds/"+data.guildData.convertGuildNameToGuildFileName.get(guildName));
		
        ivng.setItem(guiFile.get().getInt("gui.GuestViewGuildInfo.items.info.slot"),
        		guiItemUtils.getItem(
        				guiFile.get().getString("gui.GuestViewGuildInfo.items.info.type"),
        				guiFile.get().getString("gui.GuestViewGuildInfo.items.info.value"),(short)
        				guiFile.get().getInt("gui.GuestViewGuildInfo.items.info.id"),
        				guiFile.get().getString("gui.GuestViewGuildInfo.items.info.name"),"",
        				guiFile.get().getStringList(("gui.GuestViewGuildInfo.items.info.lore")),
        				"guildStats",folder2));    
        ivng.setItem(guiFile.get().getInt("gui.GuestViewGuildInfo.items.members.slot"),
        		guiItemUtils.getItem(
        				guiFile.get().getString("gui.GuestViewGuildInfo.items.members.type"),
        				guiFile.get().getString("gui.GuestViewGuildInfo.items.members.value"),(short)
        				guiFile.get().getInt("gui.GuestViewGuildInfo.items.members.id"),
        				guiFile.get().getString("gui.GuestViewGuildInfo.items.members.name"),"",
        				guiFile.get().getStringList(("gui.GuestViewGuildInfo.items.members.lore")),
        				"guildStats",folder2));
        ivng.setItem(guiFile.get().getInt("gui.GuestViewGuildInfo.items.back.slot"),
        		guiItemUtils.getItem(
        				guiFile.get().getString("gui.GuestViewGuildInfo.items.back.type"),
        				guiFile.get().getString("gui.GuestViewGuildInfo.items.back.value"),(short)
        				guiFile.get().getInt("gui.GuestViewGuildInfo.items.back.id"),
        				guiFile.get().getString("gui.GuestViewGuildInfo.items.back.name"),"",
        				guiFile.get().getStringList(("gui.GuestViewGuildInfo.items.back.lore")),
        				"",null));         
		if(data.playerGuildData.haveRequestJoin(p, guildName)==false) {
		        ivng.setItem(guiFile.get().getInt("gui.GuestViewGuildInfo.items.requestJoinNull.slot"),
		        		guiItemUtils.getItem(
		        				guiFile.get().getString("gui.GuestViewGuildInfo.items.requestJoinNull.type"),
		        				guiFile.get().getString("gui.GuestViewGuildInfo.items.requestJoinNull.value"),(short)
		        				guiFile.get().getInt("gui.GuestViewGuildInfo.items.requestJoinNull.id"),
		        				guiFile.get().getString("gui.GuestViewGuildInfo.items.requestJoinNull.name"),"",
		        				guiFile.get().getStringList(("gui.GuestViewGuildInfo.items.requestJoinNull.lore")),
		        				"",null));   		
		} else {
		        ivng.setItem(guiFile.get().getInt("gui.GuestViewGuildInfo.items.requestJoinNonNull.slot"),
		        		guiItemUtils.getItem(
		        				guiFile.get().getString("gui.GuestViewGuildInfo.items.requestJoinNonNull.type"),
		        				guiFile.get().getString("gui.GuestViewGuildInfo.items.requestJoinNonNull.value"),(short)
		        				guiFile.get().getInt("gui.GuestViewGuildInfo.items.requestJoinNonNull.id"),
		        				guiFile.get().getString("gui.GuestViewGuildInfo.items.requestJoinNonNull.name"),"",
		        				guiFile.get().getStringList(("gui.GuestViewGuildInfo.items.requestJoinNonNull.lore")),
		        				"",null));   			
		}
        
		return ivng;	
	}	
}
