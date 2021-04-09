package listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Cortez.guilds.Core;

public class pQuitEvent implements Listener {
	@SuppressWarnings("unused")
	private Core plugin;
	public pQuitEvent(Core plugin) {
		this.plugin=plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onJoin(PlayerQuitEvent e) {
		if(Core.GUIcreateGuilds.contains(e.getPlayer().getUniqueId())) {
			Core.GUIcreateGuilds.remove(e.getPlayer().getUniqueId());
		}
	}
}
