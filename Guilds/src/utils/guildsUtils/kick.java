package utils.guildsUtils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import api.guildAPI;
import color.color;
import me.Cortez.guilds.Core;

public class kick {
	static String guildsPath = "plugins/Guilds/guilds";
	
	public static void main(Player power, Player target) {
		
		if(!guildAPI.hasGuild(power.getName()) && !guildAPI.hasGuild(target.getName())) {
			
			if(!(guildAPI.hasGuild(power.getName()))) {
				power.sendMessage(color.add(Core.getlang("kick.noGuild.playerUseCmd")));
			} else
				if (!(guildAPI.hasGuild(target.getName())))
					power.sendMessage(color.add(Core.getlang("kick.noGuild.target").replace("%player%", target.getName())));				
			
		}
		
		if(!guildAPI.getPlayerGuildName(power.getName()).equalsIgnoreCase(guildAPI.getPlayerGuildName(target.getName()))) {
			
			power.sendMessage(color.add(Core.getlang("kick.targetIsNotInPGuild").replace("%player%", target.getName())));
			return;
			
		}
		
		if(!guildAPI.getPlayerRank(power.getName()).equalsIgnoreCase("Chủ Guild")) {
			
			if(!guildAPI.getPlayerRank(power.getName()).equalsIgnoreCase("Quản Lý")) {
				
				power.sendMessage(color.add(Core.getlang("kick.pIsNotHaveThePower")));
				return;
				
			}
			
		}
		
		if(guildAPI.getPlayerRank(target.getName()).equalsIgnoreCase("Chủ Guild")) {
			
			power.sendMessage(color.add(Core.getlang("kick.pIsCOandTisO")));
			return;	
			
		}
		
		succes(power, target);
		
	
	}
	public static void succes(Player power, Player target) {
		
		File folder = new File(guildsPath+"/"+data.guildData.convertGuildNameToGuildFileName.get(guildAPI.getPlayerGuildName(power.getName())));
		YamlConfiguration guild = YamlConfiguration.loadConfiguration(folder);
		
		guild.set("members."+target.getName(), null);
		
		power.sendMessage(color.add(Core.getlang("kick.sucess.P").replace("%player%", target.getName())));
		
		String targetmessage = Core.getlang("kick.sucess.T");
		targetmessage=targetmessage.replace("%player%", power.getName());
		targetmessage=targetmessage.replace("%rank%", guildAPI.getPlayerRank(power.getName()));
		
		target.sendMessage(color.add(targetmessage));
		
		try {
			guild.save(folder);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		data.playerGuildData.loadPlayerData(target);
		data.guildData.loadGuildData(guildAPI.getPlayerGuildName(power.getName()),null);					
		
	}
	
}
