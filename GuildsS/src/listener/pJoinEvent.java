package listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Cortez.guilds.Core;

public class pJoinEvent implements Listener {
	@SuppressWarnings("unused")
	private Core plugin;
	public pJoinEvent(Core plugin) {
		this.plugin=plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		data.playerGuildData.loadPlayerData(e.getPlayer().getName());
	}
	
}
