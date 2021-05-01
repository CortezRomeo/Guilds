package utils.guildsUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import api.guildAPI;
import color.color;
import depend.playerpointsDepend;
import depend.tokenmanagerDepend;
import depend.vaultDepend;
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
	 
	public static void main(Player p, String guildName) {
				
		if(guildAPI.hasGuild(p.getName())) {
			
			p.sendMessage(color.add(Core.getlang("create.alreadyInGuild").replace("%guild%", data.playerGuildData.getGuildName.get(p.getName()))));	
			return;
			
		}
		
		if(invite.requestInvite.contains(p.getUniqueId())) {
			
			p.sendMessage(color.add(Core.getlang("create.haveRequest")));
			return;
			
		}
		
		if(guildAPI.checkGuildNotNull(guildName)) {
			
			p.sendMessage(color.add(Core.getlang("create.guildAlreadyExist")));	
			return;
			
		}
		
		for(String str : Core.getInstance().getConfig().getStringList("create.denyCharacters.fullName")) {			
			if(guildName.toLowerCase().equalsIgnoreCase(str.toLowerCase())) {
				
				p.sendMessage(color.add(Core.getlang("create.denyCharacters.fullName").replace("%c%", str.toLowerCase())));
				return;
				
			}
		}
		
		for(String str : Core.getInstance().getConfig().getStringList("create.denyCharacters.characters")) {			
			if(guildName.toLowerCase().contains(str.toLowerCase())) {
											
				p.sendMessage(color.add(Core.getlang("create.denyCharacters.Characters").replace("%c%", str.toLowerCase())));
				return;
				
			}
		}	
		
		if(guildName.length()>Core.getInstance().getConfig().getInt("create.maxLength")) {
		
			String mes = Core.getlang("create.maxLength");
			
			mes=mes.replace("%k%", String.valueOf(Core.getInstance().getConfig().getInt("create.maxLength")));
			mes=mes.replace("%km%", String.valueOf(guildName.length()));
			
			p.sendMessage(color.add(mes));
			
			return;
			
		}
		
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

		sucess(p, guildName);
		
		}
	
	public static void sucess(Player p,String guildName) {
		
		String random = getAlphaNumericString(7);
		
		Date date = new Date();
		int dateint = (int) (date.getTime()/1000);
		
		try {
			new File(guildsPath+"/"+random+".yml").createNewFile();
			addInfo(p, random, guildName, dateint);
		}
		 catch (Exception e) {
			e.printStackTrace();												 
		 }
		
		data.guildData.loadGuildData(guildName,random);
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
		
	}
	
}
