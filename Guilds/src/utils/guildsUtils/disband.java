package utils.guildsUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import color.color;
import files.langFile;
import me.Cortez.guilds.Core;
import utils.clickAbleUtils;

public class disband {
	static String guildsPath = "plugins/Guilds/guilds";
	public static List<UUID> requestDisband = new ArrayList<UUID>();
	public static HashMap<Player, Integer> timeouts = new HashMap<Player, Integer>();
	
	public static void CHATform(Player p) {
		
		if(data.playerGuildData.hasGuild.get(p.getName())) {
			if(data.playerGuildData.getRank.get(p.getName()).equalsIgnoreCase("Chủ Guild")) {
								
				if(requestDisband.contains(p.getUniqueId())) {
					p.sendMessage(color.add(color.add(Core.getlang("disband.haveRequest"))));
					return;
				}
								
				requestDisband.add(p.getUniqueId());				
				timeouts.put(p, Core.getInstance().getConfig().getInt("disband.expiresTime"));

				for(String str : langFile.get().getStringList("disband.message")) {
					str=str.replace("%s%", String.valueOf(timeouts.get(p)));
					p.sendMessage(color.add(str));
				}				
				if(Core.getInstance().getConfig().getBoolean("clickAbleChat.showButton"))
					clickAbleUtils.main(p);		
				if(Core.getInstance().getConfig().getBoolean("clickAbleChat.endlineEnable"))
					p.sendMessage(color.add(Core.getInstance().getConfig().getString("clickAbleChat.endline")));
				
				new BukkitRunnable() {
					
					@Override
					public void run() {
						
						if(Core.deny.contains(p)) {
							cancel();
							Core.deny.remove(p);
							requestDisband.remove(p.getUniqueId());
							p.sendMessage(color.add(color.add(Core.getlang("disband.deny"))));
							return;
						}
						
						if(!requestDisband.contains(p.getUniqueId())) {
							cancel();
							delete(p);							
							return;
						}							
						
						if(timeouts.get(p)>0 && requestDisband.contains(p.getUniqueId()))
							timeouts.put(p, timeouts.get(p)-1);
						
						if(timeouts.get(p)==0 && requestDisband.contains(p.getUniqueId())) {							
							cancel();
							p.sendMessage(color.add(color.add(Core.getlang("disband.expires"))));
							requestDisband.remove(p.getUniqueId());							
						}			
						
					}
				}.runTaskTimerAsynchronously(Core.getInstance(), 0, 20);	
				return;
			}
	        p.sendMessage(color.add(Core.getlang("disband.pIsNotTheO")));
			return;
		}
        p.sendMessage(color.add(Core.getlang("disband.PisNotinTheG")));
	}
	
	public static void GUIform(Player p) {
	}
	
	static void delete(Player p) {
		
		String guildName = data.playerGuildData.getGuildName.get(p.getName());							
		
		File guild = new File(guildsPath+"/"+data.guildData.convertGuildNameToGuildFileName.get(guildName));
	    if (guild.delete()) { 
	        p.sendMessage(color.add(Core.getlang("disband.accept")));
	      } else {
	        p.sendMessage(color.add(Core.getlang("disband.error")));
	      } 
	    data.guildData.removeGuildData(guildName);
	    data.playerGuildData.loadPlayerData(p);
	    
	}
	
}
