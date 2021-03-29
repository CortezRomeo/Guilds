package listener;

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
import org.bukkit.inventory.InventoryHolder;

import color.color;
import files.guiFile;
import files.langFile;
import gui.GVGI;
import gui.listGuild;
import gui.viewMembers;
import invPage.PagedPane;
import me.Cortez.guilds.Core;

public class InvClickEvent implements Listener{
	@SuppressWarnings("unused")
	private Core plugin;
	public InvClickEvent(Core plugin) {
		this.plugin=plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}

	private static String getLang(String str) {
		return guiFile.get().getString(str);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e){						
		
		if(e.getInventory().getTitle().equals(color.add(getLang("gui.noGuild.title")))) {		
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
			
				if(e.getSlot()==guiFile.get().getInt("gui.noGuild.items.create.slot")) {				
					if(!Core.GUIcreateGuilds.contains(p.getUniqueId())) {
						Core.GUIcreateGuilds.add(p.getUniqueId());

						for(String str : langFile.get().getStringList("create.GUI.create")) {

							str=str.replace("%word%", Core.getInstance().getConfig().getString("create.cancelCreateWords"));
							p.sendMessage(color.add(str));
						}				
						p.closeInventory();			
						e.setCancelled(true);
							return;
					}
				p.sendMessage(color.add(langFile.get().getString("create.GUI.inProgess")));
				p.closeInventory();		
			} else
				if(e.getSlot()==guiFile.get().getInt("gui.noGuild.items.listGuild.slot")) {
					listGuild.main(p);						
			} else
				if(e.getSlot()==guiFile.get().getInt("gui.noGuild.items.close.slot")) {
					p.closeInventory();
			}
		} 
		if(e.getInventory().getTitle().equals(color.add(getLang("gui.listGuild.title")))){	 			
	 	        InventoryHolder holder = e.getInventory().getHolder();
	 	        
	 	        if (holder instanceof PagedPane) {
	 	            ((PagedPane) holder).onClick(e);	 	            
	 	        }
	 	}
		if(data.playerGuildData.getClickedGuildName.get(((Player)e.getWhoClicked()).getName())!=null) {
		 	if(e.getInventory().getTitle().equals(color.add(getLang("gui.GuestViewGuildInfo.title").replace("%guildName%",
					data.playerGuildData.getClickedGuildName.get(((Player)e.getWhoClicked()).getName()))))){
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
	 			
				e.setCancelled(true);	
						
				if(e.getSlot()==guiFile.get().getInt("gui.GuestViewGuildInfo.items.members.slot")) {
					viewMembers.main(p);
				}
				
				if(!(data.playerGuildData.haveRequestJoin(p, data.playerGuildData.getClickedGuildName.get(p.getName())))) {
					if(e.getSlot()==guiFile.get().getInt("gui.GuestViewGuildInfo.items.requestJoinNull.slot")) {
						
						String guildName = data.playerGuildData.getClickedGuildName.get(p.getName());
						String guildFile = data.guildData.convertGuildNameToGuildFileName.get(guildName);
						String guildpath = "plugins/Guilds/guilds/";
						
						File file = new File(guildpath+guildFile);
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
						Date date = new Date();
						cfg.set("requestJoin."+p.getName(), (int) (date.getTime()/1000));
						try {
							cfg.save(new File(guildpath+guildFile));
						} catch (IOException io) {
							io.printStackTrace();
						}
						p.sendMessage("Xin gia nhap thanh cong!");
						p.openInventory(GVGI.main(p, data.playerGuildData.getClickedGuildName.get(p.getName())));
					}
				} 
				
				if(e.getSlot()==guiFile.get().getInt("gui.GuestViewGuildInfo.items.back.slot")) {
					data.playerGuildData.getClickedGuildName.remove(p.getName());
					listGuild.main(p);
				}
				
				
		}	
		}
		if(e.getInventory().getTitle().equals(color.add(getLang("gui.viewMembers.title")))){	 			
	 	        InventoryHolder holder = e.getInventory().getHolder();
	 	        
	 	        if (holder instanceof PagedPane) {
	 	            ((PagedPane) holder).onClick(e);	 	            
	 	        }
	 	} 
		
	}
	
}
