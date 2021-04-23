package utils.guildsUtils;

import org.bukkit.entity.Player;

import color.color;
import files.langFile;
import me.Cortez.guilds.Core;
import utils.dateUtils;

public class statsGuild {
	public static void main(Player p) {
		
		if(data.playerGuildData.hasGuild.get(p.getName())) {
				
			
			String GFoundedTime = dateUtils.main(Integer.valueOf(
					data.guildData.getfoundedTime.get(data.playerGuildData.getGuildName.get(p.getName()))));
			String datePjoin = dateUtils.main(data.playerGuildData.getJoinGuildDate.get(p.getName()));
			String guildName = data.playerGuildData.getGuildName.get(p.getName());
			
			for(String str : langFile.get().getStringList("stats")) {								
				
				str=str.replace("%guildName%", data.playerGuildData.getGuildName.get(p.getName()));
				str=str.replace("%rank%", data.playerGuildData.getRank.get(p.getName()));		
				str=str.replace("%chuSoHuu%", data.guildData.getChuSoHuu.get(guildName));
	        	str=str.replace("%members%", String.valueOf(data.guildData.gettotalMembers.get(guildName)));
	        	str=str.replace("%board%", data.guildData.getboard.get(guildName));
	        	str=str.replace("%customname%", data.guildData.getcustomname.get(guildName));
	        	str=str.replace("%guildFoundedTime%", GFoundedTime);
	        	str=str.replace("%joinDate%", datePjoin);
				
				p.sendMessage(color.add(str));
			}
			
			return;
		}
		p.sendMessage(color.add(Core.getlang("noGuild")));
	}
}
