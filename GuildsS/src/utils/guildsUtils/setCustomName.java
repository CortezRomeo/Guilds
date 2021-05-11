package utils.guildsUtils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import api.guildAPI;
import color.color;
import me.Cortez.guilds.Core;

public class setCustomName {
	static String guildsPath = "plugins/GuildsS/guilds";
	
	public static void main(Player p, String customName) {
		
		if(!guildAPI.hasGuild(p.getName())) {
			
			p.sendMessage(color.add(Core.getlang("noGuild")));
			return;
			
		}		

		if(!guildAPI.getPlayerRank(p.getName()).equalsIgnoreCase(Core.ownerName)) {
			
			if(!guildAPI.getPlayerRank(p.getName()).equalsIgnoreCase(Core.managerName)) {
				
				p.sendMessage(color.add(Core.getlang("kick.pIsNotHaveThePower")));
				return;
				
			}
			
		}
		
		if(customName.length()>Core.getInstance().getConfig().getInt("customName.maxLength")) {
			
			String mes = Core.getlang("setCustomName.maxLength");
			mes=mes.replace("%k%", String.valueOf(Core.getInstance().getConfig().getInt("customName.maxLength")));
			mes=mes.replace("%km%", String.valueOf(customName.length()));
			
			p.sendMessage(color.add(mes));
			
			return;
			
		}
		
		succes(p, customName);
		
			
	}	
	
	public static void succes(Player p, String str) {
		
		File folder = new File(guildsPath+"/"+data.guildData.convertGuildNameToGuildFileName.get(guildAPI.getPlayerGuildName(p.getName())));
		YamlConfiguration guild = YamlConfiguration.loadConfiguration(folder);
		
		guild.set("customName", str);
		try {
			guild.save(folder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		p.sendMessage(color.add(Core.getlang("setCustomName.succes").replace("%name%", str)));
		
		data.guildData.loadGuildData(guildAPI.getPlayerGuildName(p.getName()),null);	
        for(String member : guild.getConfigurationSection("members").getKeys(false)) 
        	data.playerGuildData.getGuildCustomName.put(member, guild.getString("customName"));					  
		
		
	}
	
}
