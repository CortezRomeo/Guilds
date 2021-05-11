package invPage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

import color.color;
import data.playerGuildData;
import files.guiFile;
import gui.GuestViewGuildStatsGUI;
import gui.noGuildGUI;
import me.Cortez.guilds.Core;

public class InvClickEvent implements Listener {
	@SuppressWarnings("unused")
	private Core plugin;

	public InvClickEvent(Core plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {

		if (e.getInventory().getTitle().equals(color.add(guiFile.get().getString("gui.listGuild.title")))) {
			InventoryHolder holder = e.getInventory().getHolder();

			if (holder instanceof PagedPane) 
				((PagedPane) holder).onClick(e);
			
			
			if(e.getSlot()==guiFile.get().getInt("gui.listGuild.items.back.slot")) 
				
				if(!playerGuildData.hasGuild.get(e.getWhoClicked().getName())) {
					e.getWhoClicked().openInventory(noGuildGUI.main((Player)e.getWhoClicked()));
				} else {
					
					// open main menu co guild
					
				}
				
		}

		if (e.getInventory().getTitle().equals(color.add(guiFile.get().getString("gui.viewMembers.title")))) {
			InventoryHolder holder = e.getInventory().getHolder();

			if (holder instanceof PagedPane) 
				((PagedPane) holder).onClick(e);
			
			
			Player p =(Player) e.getWhoClicked();
			
			if(e.getSlot()==guiFile.get().getInt("gui.viewMembers.items.back.slot")) 
				p.openInventory(GuestViewGuildStatsGUI.main(p,
						data.playerGuildData.getClickedGuildName.get(p.getName())));
			
		}
	}

}
