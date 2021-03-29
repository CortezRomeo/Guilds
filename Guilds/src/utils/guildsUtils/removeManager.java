package utils.guildsUtils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import color.color;
import me.Cortez.guilds.Core;

public class removeManager {
	static String guildsPath = "plugins/Guilds/guilds";
	public static void main(Player owner, Player target) {
			if(data.playerGuildData.hasGuild.get(owner.getName()) && data.playerGuildData.hasGuild.get(target.getName())) {
				if(data.playerGuildData.getGuildName.get(owner.getName()).equalsIgnoreCase(data.playerGuildData.getGuildName.get(target.getName()))) {
					if(data.playerGuildData.getRank.get(owner.getName()).equalsIgnoreCase("Chủ Guild")) {
						if(data.playerGuildData.getRank.get(target.getName()).equalsIgnoreCase("Quản Lý")) {							
							File folder = new File(guildsPath+"/"+data.guildData.convertGuildNameToGuildFileName.get(data.playerGuildData.getGuildName.get(owner.getName())));
							YamlConfiguration guild = YamlConfiguration.loadConfiguration(folder);			
							guild.set("members."+target.getName()+".rank", "Thành Viên");
							owner.sendMessage(color.add(Core.getlang("manager.remove.sucess.P").replace("%player%", target.getName())));
							target.sendMessage(color.add(Core.getlang("manager.remove.sucess.T").replace("%player%", owner.getName())));
							data.playerGuildData.loadPlayerData(target);
							try {
								guild.save(folder);
							} catch (Exception e) {
								e.printStackTrace();
							}
							return;
						}
						owner.sendMessage(color.add(Core.getlang("manager.targetAlreadyisCoOwner").replace("%player%", target.getName())));
						return;
					}
					owner.sendMessage(color.add(Core.getlang("manager.pIsNotTheOwner")));
					return;
				}
				owner.sendMessage(color.add(Core.getlang("manager.targetIsNotInPGuild").replace("%player%", target.getName())));
				return;
			}
			if(!(data.playerGuildData.hasGuild.get(owner.getName()))) {
				owner.sendMessage(color.add(Core.getlang("manager.noGuild.playerUseCmd")));
			} else
			if (!(data.playerGuildData.hasGuild.get(target.getName()))){
			owner.sendMessage(color.add(Core.getlang("manager.noGuild.target").replace("%player%", target.getName())));
			}
	}
	
}
