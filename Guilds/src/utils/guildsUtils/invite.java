package utils.guildsUtils;

import java.io.File;
import java.util.Date;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import color.color;
import files.langFile;
import me.Cortez.guilds.Core;

public class invite {
	static String guildsPath = "plugins/Guilds/guilds";
	public static void main(Player power, Player target) {
		if(data.playerGuildData.getRank.get(power.getName()).equalsIgnoreCase("Chủ Guild") || data.playerGuildData.getRank.get(power.getName()).equalsIgnoreCase("Quản Lý")) {
			if(!(data.playerGuildData.hasGuild.get(target.getName()))) {
					if(!Core.requestGuild.contains(target.getUniqueId())) {
					power.sendMessage(color.add(Core.getlang("invite.invitationSender.invite").replace("%player%", target.getName())));
					Core.requestGuild.add(target.getUniqueId());
					for (String str : langFile.get().getStringList("invite.invitedRecipients.invite")) {
						str = str.replace("%player%", power.getName());
						str = str.replace("%guild%", data.playerGuildData.getGuildName.get(power.getName()));
						str = str.replace("%s%", String.valueOf(Core.getInstance().getConfig().getInt("invite.expiresTime")));
						target.sendMessage(color.add(str));
					}
				
					new BukkitRunnable() {
						
						@Override
						public void run() {						
							if(Core.requestGuild.contains(target.getUniqueId())) {							
								target.sendMessage(color.add(Core.getlang("invite.invitedRecipients.expires")));
								power.sendMessage(color.add(Core.getlang("invite.invitationSender.expires").replace("%player%", target.getName())));
								Core.requestGuild.remove(target.getUniqueId());
								Core.expired.add(target.getUniqueId());
							}						
						}
					}.runTaskLaterAsynchronously(Core.getInstance(),20*Core.getInstance().getConfig().getInt("invite.expiresTime"));
					
					new BukkitRunnable() {					
						@Override
						public void run() {						
							if(target.isOnline()==false) {
								Core.requestGuild.remove(target.getUniqueId());
								power.sendMessage(color.add(Core.getlang("invite.invitationSender.targetQuit").replace("%player%", target.getName())));
								cancel();						
									return;
							}
							if(Core.expired.contains(target.getUniqueId())) {
								Core.expired.remove(target.getUniqueId());
								cancel();
									return;
							}
							if(!Core.requestGuild.contains(target.getUniqueId())) {
								if(!(Core.refuse.contains(target.getUniqueId()))) {
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
									cancel();
								} else {
								Core.refuse.remove(target.getUniqueId());
								target.sendMessage(color.add(Core.getlang("invite.invitedRecipients.refuse")));
								power.sendMessage(color.add(Core.getlang("invite.invitationSender.refuse").replace("%player%", target.getName())));
								cancel();
								}
							}												
						}	
					}.runTaskTimerAsynchronously(Core.getInstance(), 0, 5);
					return;
				}
				power.sendMessage(color.add(Core.getlang("invite.invitationSender.requested").replace("%player%", target.getName())));
				return;
			}
			power.sendMessage(color.add(Core.getlang("invite.invitationSender.alreadyInGuild").replace("%player%", target.getName())));
			return;
		}
		power.sendMessage(color.add(Core.getlang("invite.invitationSender.noper")));
	}
	
}
