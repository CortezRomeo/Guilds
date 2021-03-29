package commands;

import org.bukkit.entity.Player;

import color.color;
import me.Cortez.guilds.Core;

public class guildAcceptCommand {	
	public static void main(Player p) {
		if(Core.requestGuild.contains(p.getUniqueId())) {
			Core.requestGuild.remove(p.getUniqueId());
			return;
		}
		p.sendMessage(color.add(Core.getlang("noRequest")));
		return;
	}
}
