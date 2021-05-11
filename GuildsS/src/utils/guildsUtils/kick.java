package utils.guildsUtils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import api.guildAPI;
import color.color;
import me.Cortez.guilds.Core;

public class kick {
	static String guildsPath = "plugins/GuildsS/guilds";
	
	public static void main(Player power, Player target) {
		
		if(!guildAPI.hasGuild(power.getName())) {
			
			power.sendMessage(color.add(Core.getlang("noGuild")));	
			return;
			
		}	
		
		if(!guildAPI.hasGuild(target.getName())) {
			
			power.sendMessage(color.add(Core.getlang("kick.targetNoGuildYet").replace("%player%", target.getName())));			
			return;	
			
		}
		
		if(!guildAPI.getPlayerGuildName(power.getName()).equalsIgnoreCase(guildAPI.getPlayerGuildName(target.getName()))) {
			
			power.sendMessage(color.add(Core.getlang("kick.targetIsNotInPGuild").replace("%player%", target.getName())));
			return;
			
		}
		
		if(!guildAPI.getPlayerRank(power.getName()).equalsIgnoreCase(Core.ownerName)) {
			
			if(!guildAPI.getPlayerRank(power.getName()).equalsIgnoreCase(Core.managerName)) {
				
				power.sendMessage(color.add(Core.getlang("kick.pIsNotHaveThePower")));
				return;
				
			}
			
		}
		
		if(guildAPI.getPlayerRank(target.getName()).equalsIgnoreCase(Core.ownerName)) {
			
			power.sendMessage(color.add(Core.getlang("kick.pIsCOandTisO")));
			return;	
			
		}
		
		succes(power, target);
		
	
	}
	public static void succes(Player power, Player target) {
		
		File folder = new File(guildsPath+"/"+data.guildData.convertGuildNameToGuildFileName.get(guildAPI.getPlayerGuildName(power.getName())));
		YamlConfiguration guild = YamlConfiguration.loadConfiguration(folder);
		
		guild.set("members."+target.getName(), null);
		
		power.sendMessage(color.add(Core.getlang("kick.succes.P").replace("%player%", target.getName())));
		
		String targetmessage = Core.getlang("kick.succes.T");
		targetmessage=targetmessage.replace("%player%", power.getName());
		targetmessage=targetmessage.replace("%rank%", guildAPI.getPlayerRank(power.getName()));
		
		target.sendMessage(color.add(targetmessage));
		
		try {
			guild.save(folder);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		data.playerGuildData.loadPlayerData(target.getName());
		data.guildData.loadGuildData(guildAPI.getPlayerGuildName(power.getName()),null);					
		
	}
	
}
