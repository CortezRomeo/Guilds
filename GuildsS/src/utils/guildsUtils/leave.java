package utils.guildsUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import api.guildAPI;
import color.color;
import files.langFile;
import me.Cortez.guilds.Core;
import utils.clickAbleUtils;

public class leave {
	static String guildsPath = "plugins/GuildsS/guilds";
	public static List<UUID> requestLeave = new ArrayList<UUID>();
	public static HashMap<Player, Integer> LeaveTimeouts = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> LeaveTimeoutsGUI = new HashMap<Player, Integer>();	
	
	public static void CHATform(Player p) {
		
		if(!guildAPI.hasGuild(p.getName())) {
			
			p.sendMessage(color.add(Core.getlang("noGuild")));		
			return;
			
		}
		
		if(guildAPI.getPlayerRank(p.getName()).equals(Core.ownerName)) {			
			
			p.sendMessage(color.add(Core.getlang("leave.PIsOwner")));						
			return;
			
		}
		
		if (requestLeave.contains(p.getUniqueId())) {
			p.sendMessage(color.add(color.add(Core.getlang("leave.haveRequest"))));
			return;
		}

		requestLeave.add(p.getUniqueId());
		LeaveTimeouts.put(p, Core.getInstance().getConfig().getInt("leave.expiresTime.chat"));

		for (String str : langFile.get().getStringList("leave.message")) {
			str = str.replace("%s%", String.valueOf(LeaveTimeouts.get(p)));
			p.sendMessage(color.add(str));
		}

		clickAbleUtils.main(p);

		new BukkitRunnable() {

			@Override
			public void run() {

				if (Core.deny.contains(p)) {
					cancel();
					Core.deny.remove(p);
					requestLeave.remove(p.getUniqueId());
					p.sendMessage(color.add(color.add(Core.getlang("leave.deny"))));
					return;
				}

				if (!requestLeave.contains(p.getUniqueId())) {
					cancel();
					succes(p);
					return;
				}

				if (LeaveTimeouts.get(p) > 0 && requestLeave.contains(p.getUniqueId()))
					LeaveTimeouts.put(p, LeaveTimeouts.get(p) - 1);

				if (LeaveTimeouts.get(p) == 0 && requestLeave.contains(p.getUniqueId())) {
					cancel();
					p.sendMessage(color.add(color.add(Core.getlang("leave.expires"))));
					requestLeave.remove(p.getUniqueId());
				}

			}
		}.runTaskTimerAsynchronously(Core.getInstance(), 0, 20);				
	}							
	
	public static void succes(Player p) {
		
		if(guildAPI.getPlayerGuildName(p.getName())==null) {
			
			p.sendMessage(color.add(Core.getlang("leave.error")));
			return;
			
		}
		
		String guildname = guildAPI.getPlayerGuildName(p.getName());		
		
		File folder = new File(guildsPath+"/"+data.guildData.convertGuildNameToGuildFileName.get(guildname));
		YamlConfiguration guild = YamlConfiguration.loadConfiguration(folder);
		
		guild.set("members."+p.getName(), null);		
		p.sendMessage(color.add(Core.getlang("leave.accept")));
		
		try {
			guild.save(folder);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		data.playerGuildData.loadPlayerData(p.getName());
		data.guildData.loadGuildData(guildname, null);	

	}	
	
}
