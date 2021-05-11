package listener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import color.color;
import files.langFile;
import me.Cortez.guilds.Core;

public class PchatEvent implements Listener {
	@SuppressWarnings("unused")
	private Core plugin;

	public PchatEvent(Core plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}

	public static List<UUID> gChat = new ArrayList<UUID>();

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (Core.GUIcreateGuilds.contains(e.getPlayer().getUniqueId())) {

			if (e.getMessage().toLowerCase().equalsIgnoreCase(
					Core.getInstance().getConfig().getString("create.cancelCreateWords").toLowerCase())) {

				e.getPlayer().sendMessage(color.add(langFile.get().getString("create.cancel")));
				Core.GUIcreateGuilds.remove(e.getPlayer().getUniqueId());
				e.setCancelled(true);
				return;
			}
			gChat.add(e.getPlayer().getUniqueId());
			utils.guildsUtils.createGuild.main(e.getPlayer(), e.getMessage());
			Core.GUIcreateGuilds.remove(e.getPlayer().getUniqueId());
			e.setCancelled(true);
			return;
		}
	}

}
