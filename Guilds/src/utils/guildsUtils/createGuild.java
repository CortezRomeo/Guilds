package utils.guildsUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import color.color;
import depend.playerpointsDepend;
import depend.tokenmanagerDepend;
import depend.vaultDepend;
import listener.PchatEvent;
import me.Cortez.guilds.Core;

public class createGuild {
	static String guildsPath = "plugins/Guilds/guilds";
	
	static void addInfo(Player p,String str, String guildName, Integer ft) {	
		YamlConfiguration cfg = new YamlConfiguration();
		cfg.set("guildName", guildName);
		cfg.set("foundedTime", ft);
		cfg.set("customName","§7Chưa có tên custom");
		cfg.set("board","§7Chưa có thông báo");
		cfg.set("members."+p.getName()+".rank","Chủ Guild");
		cfg.set("members."+p.getName()+".joinDate", ft);
		
		try {
			cfg.save(new File(guildsPath + "/" + str + ".yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	    static String getAlphaNumericString(int n) 
	    { 
	  
	        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                    + "0123456789"
	                                    + "abcdefghijklmnopqrstuvxyz"; 
	  
	        StringBuilder sb = new StringBuilder(n); 
	  
	        for (int i = 0; i < n; i++) { 
	  
	            int index 
	                = (int)(AlphaNumericString.length() 
	                        * Math.random()); 
	  
	            sb.append(AlphaNumericString 
	                          .charAt(index)); 
	        } 	  
	        return sb.toString(); 
	    } 
	    	    
		static boolean checkGuildNotNull(String guildName) {		
			File folder = new File("plugins/Guilds/guilds");
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				  if (listOfFiles[i].isFile()) {	
					  File folder2= listOfFiles[i];	
					  YamlConfiguration folderf = YamlConfiguration.loadConfiguration(folder2);
					  if(folderf.getString("guildName").equalsIgnoreCase(guildName)) 
						  return true;
				  }
			}
			return false;
		}
	 
	public static void main(Player p, String guildName) {
		if(!(Core.requestGuild.contains(p.getUniqueId()))) {
			if(!data.playerGuildData.hasGuild.get(p.getName())) {
				if(!(checkGuildNotNull(guildName))) {
					
						for(String str : Core.getInstance().getConfig().getStringList("create.denyCharacters.fullName")) {
							if(guildName.toLowerCase().equalsIgnoreCase(str.toLowerCase())) {
								if(PchatEvent.gChat.contains(p.getUniqueId())) {
									Core.GUIcreateGuilds.add(p.getUniqueId());
									PchatEvent.gChat.remove(p.getUniqueId());
									p.sendMessage(color.add(Core.getlang("create.chat.denyCharacters.fullName").replace("%c%", str.toLowerCase())));
									return;
								}
								p.sendMessage(color.add(Core.getlang("create.denyCharacters.fullName").replace("%c%", str.toLowerCase())));
								return;
							}
						}
						
						for(String str : Core.getInstance().getConfig().getStringList("create.denyCharacters.characters")) {
							if(guildName.toLowerCase().contains(str.toLowerCase())) {
								if(PchatEvent.gChat.contains(p.getUniqueId())) {
									Core.GUIcreateGuilds.add(p.getUniqueId());
									PchatEvent.gChat.remove(p.getUniqueId());
									p.sendMessage(color.add(Core.getlang("create.chat.denyCharacters.Characters").replace("%c%", str.toLowerCase())));
									return;
								}								
								p.sendMessage(color.add(Core.getlang("create.denyCharacters.Characters").replace("%c%", str.toLowerCase())));
								return;
							}
						}
						if(guildName.length()<=Core.getInstance().getConfig().getInt("create.maxLength")) {
							
							if(vaultDepend.check()==true && Core.getInstance().getConfig().getInt("create.request.money")!=0) {
								double money = vaultDepend.econ.getBalance(p);
								if(money<Core.getInstance().getConfig().getInt("create.request.money")) {
									String message = Core.getlang("create.request.money");
									message=message.replace("%p%", String.valueOf(vaultDepend.econ.format(money)));
									message=message.replace("%pq%", String.valueOf(Core.getInstance().getConfig().getInt("create.request.money")));
									p.sendMessage(color.add(message));
									return;
								}	
							}
							
							if(playerpointsDepend.check()==true && Core.getInstance().getConfig().getInt("create.request.point")!=0) {
								final int points = playerpointsDepend.playerpoints.getAPI().look(p.getUniqueId());																				
								if(points<Core.getInstance().getConfig().getInt("create.request.point")) {
									String message = Core.getlang("create.request.point");
									message=message.replace("%p%", String.valueOf(points));
									message=message.replace("%pq%", String.valueOf(Core.getInstance().getConfig().getInt("create.request.point")));
									p.sendMessage(color.add(message));
									return;
								}
							}
							
							if(tokenmanagerDepend.check()==true && Core.getInstance().getConfig().getInt("create.request.token")!=0) {
								final long tokens = tokenmanagerDepend.tokenmanager.getTokens(p).orElse(0);
								if(tokens<Core.getInstance().getConfig().getInt("create.request.token")) {
									String message = Core.getlang("create.request.token");
									message=message.replace("%p%", String.valueOf(tokens));
									message=message.replace("%pq%", String.valueOf(Core.getInstance().getConfig().getInt("create.request.token")));
									p.sendMessage(color.add(message));
									return;
								}								
							}													
							
							String str = getAlphaNumericString(7);
							Date date = new Date();
							int dateint = (int) (date.getTime()/1000);
							try {
								new File(guildsPath+"/"+str+".yml").createNewFile();
								addInfo(p, str, guildName, dateint);
							}
							 catch (Exception e) {
								e.printStackTrace();												 
							 }
							data.guildData.loadGuildData(guildName,str);
							data.playerGuildData.loadPlayerData(p);
							p.sendMessage(color.add(Core.getlang("create.createGuild").replace("%name%", guildName)));	
							if(vaultDepend.check()==true && Core.getInstance().getConfig().getInt("create.request.money")!=0) {
								vaultDepend.econ.withdrawPlayer(p, Core.getInstance().getConfig().getInt("create.request.money"));
							}
							if(playerpointsDepend.check()==true && Core.getInstance().getConfig().getInt("create.request.point")!=0) {
								playerpointsDepend.playerpoints.getAPI().take(p.getUniqueId(), Core.getInstance().getConfig().getInt("create.request.point"));
							}	
							if(tokenmanagerDepend.check()==true && Core.getInstance().getConfig().getInt("create.request.token")!=0) {
								tokenmanagerDepend.tokenmanager.removeTokens(p, Core.getInstance().getConfig().getLong("create.request.token"));
							}								
							return;	
						}
						if(PchatEvent.gChat.contains(p.getUniqueId())) {
							Core.GUIcreateGuilds.add(p.getUniqueId());
							PchatEvent.gChat.remove(p.getUniqueId());
							String mes = Core.getlang("create.chat.maxLength");
							mes=mes.replace("%k%", String.valueOf(Core.getInstance().getConfig().getInt("create.maxLength")));
							mes=mes.replace("%km%", String.valueOf(guildName.length()));
							p.sendMessage(color.add(mes));
							return;
						}
						String mes = Core.getlang("create.maxLength");
						mes=mes.replace("%k%", String.valueOf(Core.getInstance().getConfig().getInt("create.maxLength")));
						mes=mes.replace("%km%", String.valueOf(guildName.length()));
						p.sendMessage(color.add(mes));
						return;
				}
				p.sendMessage(color.add(Core.getlang("create.guildAlreadyExist")));	
				return;
			}
			p.sendMessage(color.add(Core.getlang("create.alreadyInGuild").replace("%guild%", data.playerGuildData.getGuildName.get(p.getName()))));	
			return;
		}
		p.sendMessage(color.add(Core.getlang("create.haveRequest")));
	}
	
}
