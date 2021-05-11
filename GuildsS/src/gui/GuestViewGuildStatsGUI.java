package gui;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import color.color;
import files.guiFile;
import me.Cortez.guilds.Core;
import utils.guiItemUtils;

public class GuestViewGuildStatsGUI implements Listener {	
	@SuppressWarnings("unused")
	private Core plugin;

	public GuestViewGuildStatsGUI(Core plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}	
	
	static String guildsPath = "plugins/GuildsS/guilds";
	
public static Inventory main(Player p, String guildName) {
		
		Inventory ivng = Bukkit.createInventory((InventoryHolder)p, guiFile.get().getInt("gui.GuestViewGuildInfo.rows")*9, 
				color.add(guiFile.get().getString("gui.GuestViewGuildInfo.title").replace("%guildName%", guildName)));
        
		if(guiFile.get().getBoolean("gui.GuestViewGuildInfo.enableBorder")==true) {
			border.enableBorder(guiFile.get().getInt("gui.GuestViewGuildInfo.rows"), ivng);		
		}
		
		File folder2 = new File(guildsPath+"/"+data.guildData.convertGuildNameToGuildFileName.get(guildName));
		
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
        				guiFile.get().getString("back.type"),
        				guiFile.get().getString("back.value"),(short)
        				guiFile.get().getInt("back.id"),
        				guiFile.get().getString("back.name"),"",
        				guiFile.get().getStringList(("back.lore")),
        				"",null));         
		if(data.playerGuildData.haveRequestJoin(p.getName(), guildName)==false) 
		        ivng.setItem(guiFile.get().getInt("gui.GuestViewGuildInfo.items.requestJoinNull.slot"),
		        		guiItemUtils.getItem(
		        				guiFile.get().getString("gui.GuestViewGuildInfo.items.requestJoinNull.type"),
		        				guiFile.get().getString("gui.GuestViewGuildInfo.items.requestJoinNull.value"),(short)
		        				guiFile.get().getInt("gui.GuestViewGuildInfo.items.requestJoinNull.id"),
		        				guiFile.get().getString("gui.GuestViewGuildInfo.items.requestJoinNull.name"),"",
		        				guiFile.get().getStringList(("gui.GuestViewGuildInfo.items.requestJoinNull.lore")),
		        				"",null));   		
		 else 
		        ivng.setItem(guiFile.get().getInt("gui.GuestViewGuildInfo.items.requestJoinNonNull.slot"),
		        		guiItemUtils.getItem(
		        				guiFile.get().getString("gui.GuestViewGuildInfo.items.requestJoinNonNull.type"),
		        				guiFile.get().getString("gui.GuestViewGuildInfo.items.requestJoinNonNull.value"),(short)
		        				guiFile.get().getInt("gui.GuestViewGuildInfo.items.requestJoinNonNull.id"),
		        				guiFile.get().getString("gui.GuestViewGuildInfo.items.requestJoinNonNull.name"),"",
		        				guiFile.get().getStringList(("gui.GuestViewGuildInfo.items.requestJoinNonNull.lore")),
		        				"",null));   					
        
		return ivng;	
	}	

	@EventHandler
	public void clickEvent(InventoryClickEvent e) {
		
		if (data.playerGuildData.getClickedGuildName.get(((Player) e.getWhoClicked()).getName()) != null) {
			
			if (e.getInventory().getTitle()
					.equals(color.add(guiFile.get().getString("gui.GuestViewGuildInfo.title").replace("%guildName%",
							data.playerGuildData.getClickedGuildName.get(((Player) e.getWhoClicked()).getName()))))) {
				
				e.setCancelled(true);
				Player p = (Player) e.getWhoClicked();
				
				InventoryAction action = e.getAction();
				switch (action) {
				case MOVE_TO_OTHER_INVENTORY:
					e.setCancelled(true);
					return;
				case HOTBAR_MOVE_AND_READD:
					e.setCancelled(true);
					return;
				case HOTBAR_SWAP:
					e.setCancelled(true);
					return;
				default:
					break;
				}

				if (e.getSlot() == guiFile.get().getInt("gui.GuestViewGuildInfo.items.members.slot")) 
					viewMembersGUI.main(p);
				

				if (!(data.playerGuildData.haveRequestJoin(p.getName(),
						data.playerGuildData.getClickedGuildName.get(p.getName())))) {
					
					if (e.getSlot() == guiFile.get().getInt("gui.GuestViewGuildInfo.items.requestJoinNull.slot")) {

						String guildName = data.playerGuildData.getClickedGuildName.get(p.getName());
						String guildFile = data.guildData.convertGuildNameToGuildFileName.get(guildName);

						File file = new File(guildsPath + "/" + guildFile);
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
						Date date = new Date();
						cfg.set("requestJoin." + p.getName(), (int) (date.getTime() / 1000));
						try {
							cfg.save(new File(guildsPath + "/" + guildFile));
						} catch (IOException io) {
							io.printStackTrace();
						}
						p.sendMessage(color.add(Core.getlang("submitSuccessfullyJoin")));
						p.openInventory(GuestViewGuildStatsGUI.main(p,
								data.playerGuildData.getClickedGuildName.get(p.getName())));
						
					}
				}

				if (e.getSlot() == guiFile.get().getInt("gui.GuestViewGuildInfo.items.back.slot")) {
					
					data.playerGuildData.getClickedGuildName.remove(p.getName());
					listGuildGUI.main(p);
					
				}

			}
		}
		
	}

}
