package utils.guildsUtils;

import org.bukkit.entity.Player;

import api.guildAPI;
import color.color;
import me.Cortez.guilds.Core;

public class leave {
	static String guildsPath = "plugins/Guilds/guilds";
	
	public static void main(Player p) {
		
		if(!guildAPI.hasGuild(p.getName())) {
			
			if(!(guildAPI.hasGuild(p.getName()))) {
				p.sendMessage(color.add(Core.getlang("noGuild")));
			}								
		
		}		
		// chưa hoàn thiện		
	}			
}
