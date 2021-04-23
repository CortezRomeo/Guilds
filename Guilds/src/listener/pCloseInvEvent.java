package listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import color.color;
import files.guiFile;
import files.langFile;
import gui.disbandGUI;
import me.Cortez.guilds.Core;

public class pCloseInvEvent implements Listener {
	@SuppressWarnings("unused")
	private Core plugin;

	public pCloseInvEvent(Core plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {

		if (e.getInventory().getTitle().equals(color.add(guiFile.get().getString("gui.disband.title")))) {
			if (!disbandGUI.accept.contains(e.getPlayer().getName())) {

				disbandGUI.cancel.add(e.getPlayer().getName());
				e.getPlayer().sendMessage(color.add(langFile.get().getString("disband.deny")));

			} else
				disbandGUI.accept.remove(e.getPlayer().getName());
		}

	}

}
