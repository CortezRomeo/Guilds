package utils;

import org.bukkit.entity.Player;

import color.color;
import files.langFile;
import me.Cortez.guilds.Core;

public class chatUtils {

	public static void createGuild(Player p ) {
		
		if (Core.GUIcreateGuilds.contains(p.getUniqueId())) {
			p.sendMessage(color.add(langFile.get().getString("create.GUI.inProgess")));
			return;
		}
		
		Core.GUIcreateGuilds.add(p.getUniqueId());

		for (String str : langFile.get().getStringList("create.GUI.create")) {

			str = str.replace("%word%",
					Core.getInstance().getConfig().getString("create.cancelCreateWords"));
			p.sendMessage(color.add(str));
			
		}
		
	}
	
}
