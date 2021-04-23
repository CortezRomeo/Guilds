package utils.guildsUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import color.color;
import files.langFile;
import me.Cortez.guilds.Core;
import utils.clickAbleUtils;

public class invite {
	static String guildsPath = "plugins/Guilds/guilds";
	public static List<UUID> requestInvite = new ArrayList<UUID>();
	public static HashMap<Player, Integer> timeouts = new HashMap<Player, Integer>();	
	
	public static void main(Player power, Player target) {
		
		if(!data.playerGuildData.hasGuild.get(power.getName())) {
			
			power.sendMessage(color.add(Core.getlang("noGuild")));
			return;
			
		}
				
		if(!data.playerGuildData.getRank.get(power.getName()).equalsIgnoreCase("Chủ Guild")) {
			
			if(!data.playerGuildData.getRank.get(power.getName()).equalsIgnoreCase("Quản Lý")) {
				
				power.sendMessage(color.add(Core.getlang("kick.pIsNotHaveThePower")));
				return;
				
			}
			
		}
		
		if(data.playerGuildData.hasGuild.get(target.getName())) {
			
			power.sendMessage(color.add(Core.getlang("invite.invitationSender.alreadyInGuild").replace("%player%", target.getName())));
			return;
			
		}

		if(requestInvite.contains(target.getUniqueId())) {
			
			power.sendMessage(color.add(Core.getlang("invite.invitationSender.requested").replace("%player%", target.getName())));
			return;
			
		}
		
		requestInvite.add(target.getUniqueId());
		timeouts.put(target, Core.getInstance().getConfig().getInt("invite.expiresTime"));
				
		power.sendMessage(color.add(Core.getlang("invite.invitationSender.invite").replace("%player%", target.getName())));
		for(String str : langFile.get().getStringList("invite.invitedRecipients.invite")) {
			str=str.replace("%s%", String.valueOf(timeouts.get(target)));
			str=str.replace("%player%", power.getName());
			str=str.replace("%guild%", data.playerGuildData.getGuildName.get(power.getName()));
			str=str.replace("%customname%", data.playerGuildData.getGuildCustomName.get(power.getName()));			
			target.sendMessage(color.add(str));
		}

		clickAbleUtils.main(target);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				if(Core.deny.contains(target)) {
					cancel();
					Core.deny.remove(target);
					requestInvite.remove(target.getUniqueId());
					target.sendMessage(color.add(Core.getlang("invite.invitedRecipients.deny")));
					power.sendMessage(color.add(Core.getlang("invite.invitationSender.deny").replace("%player%", target.getName())));
					return;
				}
				
				if(!requestInvite.contains(target.getUniqueId())) {
					cancel();
					inviteSucces(power, target);			
					return;
				}							
				
				if(timeouts.get(target)>0 && requestInvite.contains(target.getUniqueId()))
					timeouts.put(target, timeouts.get(target)-1);
				
				if(timeouts.get(target)==0 && requestInvite.contains(target.getUniqueId())) {							
					cancel();
					target.sendMessage(color.add(Core.getlang("invite.invitedRecipients.expires")));
					power.sendMessage(color.add(Core.getlang("invite.invitationSender.expires").replace("%player%", target.getName())));
					requestInvite.remove(target.getUniqueId());							
				}			
				
			}
		}.runTaskTimerAsynchronously(Core.getInstance(), 0, 20);	
		return;
		
	}
		
	static void inviteSucces(Player power, Player target) {
		
		File folder = new File(guildsPath+"/"+data.guildData.convertGuildNameToGuildFileName.get(data.playerGuildData.getGuildName.get(power.getName())));
		YamlConfiguration guild = YamlConfiguration.loadConfiguration(folder);
		Date date = new Date();
		int dateint = (int) (date.getTime()/1000);
		
		guild.set("members."+target.getName()+".rank", "Thành Viên");
		guild.set("members."+target.getName()+".joinDate", dateint);
		
		try {
			guild.save(folder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		power.sendMessage(color.add(Core.getlang("invite.invitationSender.accept").replace("%player%", target.getName())));
		target.sendMessage(color.add(Core.getlang("invite.invitedRecipients.accept").replace("%guild%", data.playerGuildData.getGuildName.get(power.getName()))));
		
		data.playerGuildData.loadPlayerData(target);
		data.guildData.loadGuildData(data.playerGuildData.getGuildName.get(power.getName()),null);
		
	}
	
}
