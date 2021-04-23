package utils.guildsUtils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import color.color;
import me.Cortez.guilds.Core;

public class setCustomName {
	static String guildsPath = "plugins/Guilds/guilds";
	
	public static void main(Player p, String customName) {
		
		if(!data.playerGuildData.hasGuild.get(p.getName())) {
			
			p.sendMessage(color.add(Core.getlang("noGuild")));
			return;
			
		}		

		if(!data.playerGuildData.getRank.get(p.getName()).equalsIgnoreCase("Chủ Guild")) {
			
			if(!data.playerGuildData.getRank.get(p.getName()).equalsIgnoreCase("Quản Lý")) {
				
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
		
		File folder = new File(guildsPath+"/"+data.guildData.convertGuildNameToGuildFileName.get(data.playerGuildData.getGuildName.get(p.getName())));
		YamlConfiguration guild = YamlConfiguration.loadConfiguration(folder);
		
		guild.set("customName", str);
		try {
			guild.save(folder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		p.sendMessage(color.add(Core.getlang("setCustomName.sucess").replace("%name%", str)));
		
		data.guildData.loadGuildData(data.playerGuildData.getGuildName.get(p.getName()),null);	
        for(String member : guild.getConfigurationSection("members").getKeys(false)) 
        	data.playerGuildData.getGuildCustomName.put(member, guild.getString("customName"));					  
		
		
	}
	
}
