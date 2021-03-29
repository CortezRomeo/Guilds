package commands;

import org.bukkit.entity.Player;

import color.color;
import files.langFile;
import me.Cortez.guilds.Core;

public class guildRefuseCommand {	
	private static String getlang(String str) {
		return langFile.get().getString(str);
	}
	public static void main(Player p) {
		if(Core.requestGuild.contains(p.getUniqueId())) {
			Core.requestGuild.remove(p.getUniqueId());
			Core.refuse.add(p.getUniqueId());
			return;
		}
		p.sendMessage(color.add(getlang("noRequest")));
		return;
	}
}
