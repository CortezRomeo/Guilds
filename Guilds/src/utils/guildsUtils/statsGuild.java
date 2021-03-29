package utils.guildsUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.entity.Player;

import color.color;
import files.langFile;
import me.Cortez.guilds.Core;

public class statsGuild {
	public static void main(Player p) {
		
		if(data.playerGuildData.hasGuild.get(p.getName())) {
				
			Integer dateInt = Integer.valueOf(data.guildData.getfoundedTime.get(data.playerGuildData.getGuildName.get(p.getName())));
			Date date = new Date(((long)dateInt)*1000L);
			SimpleDateFormat formatter = new SimpleDateFormat(Core.getInstance().getConfig().getString("dateFormat"));
			String dateString = formatter.format(date);
			
			Integer dateInt2 = Integer.valueOf(data.playerGuildData.getJoinGuildDate.get(p.getName()));
			Date date2 = new Date(((long)dateInt2)*1000L);
			SimpleDateFormat formatter2 = new SimpleDateFormat(Core.getInstance().getConfig().getString("dateFormat"));
			String dateString2 = formatter2.format(date2);			
			for(String str : langFile.get().getStringList("stats")) {
				
				str=str.replace("%guildName%", data.playerGuildData.getGuildName.get(p.getName()));
				str=str.replace("%rank%", data.playerGuildData.getRank.get(p.getName()));		
				str=str.replace("%chuSoHuu%", data.guildData.getChuSoHuu.get(data.playerGuildData.getGuildName.get(p.getName())));
	        	str=str.replace("%members%", String.valueOf(String.valueOf(data.guildData.gettotalMembers.get(data.playerGuildData.getGuildName.get(p.getName())))));
	        	str=str.replace("%board%", data.guildData.getboard.get(data.playerGuildData.getGuildName.get(p.getName())));
	        	str=str.replace("%customname%", data.guildData.getcustomname.get(data.playerGuildData.getGuildName.get(p.getName())));
	        	str=str.replace("%guildFoundedTime%", dateString);
	        	str=str.replace("%joinDate%", dateString2);
				
				p.sendMessage(color.add(str));
			}
			
			return;
		}
		p.sendMessage(color.add(Core.getlang("noGuild")));
	}
}
