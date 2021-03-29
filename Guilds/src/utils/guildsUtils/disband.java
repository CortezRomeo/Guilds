package utils.guildsUtils;

import java.io.File;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import color.color;
import files.langFile;
import me.Cortez.guilds.Core;

public class disband {
	static String guildsPath = "plugins/Guilds/guilds";
	public static void main(Player p) {
		if(data.playerGuildData.hasGuild.get(p.getName())) {
			if(data.playerGuildData.getRank.get(p.getName()).equalsIgnoreCase("Chủ Guild")) {
				if(!Core.requestGuild.contains(p.getUniqueId())) {
					Core.requestGuild.add(p.getUniqueId());
					for(String str : langFile.get().getStringList("disband.message")) {
						str=str.replace("%s%", String.valueOf(Core.getInstance().getConfig().getInt("disband.expiresTime")));
						p.sendMessage(color.add(str));
					}
												
					new BukkitRunnable() {
						
						@Override
						public void run() {						
							if(Core.requestGuild.contains(p.getUniqueId())) {							
								p.sendMessage(color.add(Core.getlang("disband.expires")));
								Core.requestGuild.remove(p.getUniqueId());
								Core.expired.add(p.getUniqueId());
							}						
						}
					}.runTaskLater(Core.getInstance(),20*Core.getInstance().getConfig().getInt("disband.expiresTime"));
					
					new BukkitRunnable() {					
						@Override
						public void run() {						
							if(p.isOnline()==false) {
								Core.requestGuild.remove(p.getUniqueId());
								Core.expired.remove(p.getUniqueId());
								cancel();						
									return;
							}
							
							if(Core.refuse.contains(p.getUniqueId())) {
								Core.refuse.remove(p.getUniqueId());
								Core.requestGuild.remove(p.getUniqueId());
								p.sendMessage(color.add(Core.getlang("disband.refuse")));
								cancel();
								return;
								
							}
							if(Core.expired.contains(p.getUniqueId())) {
								Core.requestGuild.remove(p.getUniqueId());
								Core.expired.remove(p.getUniqueId());
								cancel();
								return;
							}
							if(!Core.requestGuild.contains(p.getUniqueId())) {
								String guildName = data.playerGuildData.getGuildName.get(p.getName());							
								
									File guild = new File(guildsPath+"/"+data.guildData.convertGuildNameToGuildFileName.get(guildName));
								    if (guild.delete()) { 
								        p.sendMessage(color.add(Core.getlang("disband.accept")));
								      } else {
								        p.sendMessage(color.add(Core.getlang("disband.error")));
								      } 
								    data.guildData.removeGuildData(guildName);
								    data.playerGuildData.loadPlayerData(p);
								    cancel();
								    return;

							}												
						}
					}.runTaskTimer(Core.getInstance(), 0, 5);	
					return;
				}
			p.sendMessage(color.add(Core.getlang("disband.haveRequest")));
			return;
			}
	        p.sendMessage(color.add(Core.getlang("disband.pIsNotTheO")));
			return;
		}
        p.sendMessage(color.add(Core.getlang("disband.PisNotinTheG")));
	}
	
}
