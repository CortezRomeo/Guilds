package utils.guildsUtils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import color.color;
import me.Cortez.guilds.Core;

public class kick {
	static String guildsPath = "plugins/Guilds/guilds";
	
	public static void main(Player power, Player target) {
		
		if(!data.playerGuildData.hasGuild.get(power.getName()) && !data.playerGuildData.hasGuild.get(target.getName())) {
			
			if(!(data.playerGuildData.hasGuild.get(power.getName()))) {
				power.sendMessage(color.add(Core.getlang("kick.noGuild.playerUseCmd")));
			} else
				if (!(data.playerGuildData.hasGuild.get(target.getName())))
					power.sendMessage(color.add(Core.getlang("kick.noGuild.target").replace("%player%", target.getName())));				
			
		}
		
		if(!data.playerGuildData.getGuildName.get(power.getName()).equalsIgnoreCase(data.playerGuildData.getGuildName.get(target.getName()))) {
			
			power.sendMessage(color.add(Core.getlang("kick.targetIsNotInPGuild").replace("%player%", target.getName())));
			return;
			
		}
		
		if(!data.playerGuildData.getRank.get(power.getName()).equalsIgnoreCase("Chủ Guild")) {
			
			if(!data.playerGuildData.getRank.get(power.getName()).equalsIgnoreCase("Quản Lý")) {
				
				power.sendMessage(color.add(Core.getlang("kick.pIsNotHaveThePower")));
				return;
				
			}
			
		}
		
		if(data.playerGuildData.getRank.get(target.getName()).equalsIgnoreCase("Chủ Guild")) {
			
			power.sendMessage(color.add(Core.getlang("kick.pIsCOandTisO")));
			return;	
			
		}
		
		succes(power, target);
		
	
	}
	public static void succes(Player power, Player target) {
		
		File folder = new File(guildsPath+"/"+data.guildData.convertGuildNameToGuildFileName.get(data.playerGuildData.getGuildName.get(power.getName())));
		YamlConfiguration guild = YamlConfiguration.loadConfiguration(folder);
		
		guild.set("members."+target.getName(), null);
		
		power.sendMessage(color.add(Core.getlang("kick.sucess.P").replace("%player%", target.getName())));
		
		String targetmessage = Core.getlang("kick.sucess.T");
		targetmessage=targetmessage.replace("%player%", power.getName());
		targetmessage=targetmessage.replace("%rank%", data.playerGuildData.getRank.get(power.getName()));
		
		target.sendMessage(color.add(targetmessage));
		
		try {
			guild.save(folder);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		data.playerGuildData.loadPlayerData(target);
		data.guildData.loadGuildData(data.playerGuildData.getGuildName.get(power.getName()),null);					
		
	}
	
}
