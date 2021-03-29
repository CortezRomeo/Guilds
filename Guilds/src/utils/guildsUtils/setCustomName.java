package utils.guildsUtils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import color.color;
import me.Cortez.guilds.Core;

public class setCustomName {
	static String guildsPath = "plugins/Guilds/guilds";
	public static void main(Player p, String customName) {
		if(data.playerGuildData.hasGuild.get(p.getName())) {
			if(data.playerGuildData.getRank.get(p.getName()).equalsIgnoreCase("Chủ Guild") || data.playerGuildData.getRank.get(p.getName()).equalsIgnoreCase("Quản Lý")) {	
				if(customName.length()<=Core.getInstance().getConfig().getInt("customName.maxLength")) {
					File folder = new File(guildsPath+"/"+data.guildData.convertGuildNameToGuildFileName.get(data.playerGuildData.getGuildName.get(p.getName())));
					YamlConfiguration guild = YamlConfiguration.loadConfiguration(folder);			
					guild.set("customName", customName);
					try {
						guild.save(folder);
					} catch (Exception e) {
						e.printStackTrace();
					}
					p.sendMessage(color.add(Core.getlang("setCustomName.sucess").replace("%name%", customName)));
					data.guildData.loadGuildData(data.playerGuildData.getGuildName.get(p.getName()),null);
					
					  for(String member : guild.getConfigurationSection("members").getKeys(false)) 
						  data.playerGuildData.getGuildCustomName.put(member, guild.getString("customName"));					  
					
					return;	
				}
				String mes = Core.getlang("setCustomName.maxLength");
				mes=mes.replace("%k%", String.valueOf(Core.getInstance().getConfig().getInt("customName.maxLength")));
				mes=mes.replace("%km%", String.valueOf(customName.length()));
				p.sendMessage(color.add(mes));
				return;
			}
			p.sendMessage(color.add(Core.getlang("setCustomName.noPer")));
			return;
		}
		p.sendMessage(color.add(Core.getlang("setCustomName.noGuild")));
	}	
	
}
