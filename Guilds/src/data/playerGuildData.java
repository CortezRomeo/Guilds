package data;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class playerGuildData {
	
	static String guildsPath = "plugins/Guilds/guilds";
	public static HashMap<String, Boolean> hasGuild = new HashMap<String, Boolean>();
	public static HashMap<String, String> getGuildName = new HashMap<String, String>();
	public static HashMap<String, String> getGuildCustomName = new HashMap<String, String>();
	public static HashMap<String, String> getRank = new HashMap<String, String>();
	public static HashMap<String, Integer> getJoinGuildDate = new HashMap<String, Integer>();
	public static HashMap<String, String> getClickedGuildName = new HashMap<String, String>();
	
	static boolean hasGuild(Player p) {
		
		if(getGuildName(p)!=null) 	
				return true;
		
		return false;
	}
	
	static String getGuildName(Player p) {
		
		File folder = new File(guildsPath);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {		    
			  YamlConfiguration guilds = YamlConfiguration.loadConfiguration(listOfFiles[i]);			  
				for(String getstring : guilds.getConfigurationSection("members.").getKeys(false)) {				
					if(getstring.equalsIgnoreCase(p.getName())) {						
						return guilds.getString("guildName");
					}					
				}			  
		  }
		}		
		return null;
	}
	
	static String getGuildCustomName(Player p) {
		
		File folder = new File(guildsPath);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {		    
			  YamlConfiguration guilds = YamlConfiguration.loadConfiguration(listOfFiles[i]);			  
				for(String getstring : guilds.getConfigurationSection("members.").getKeys(false)) {				
					if(getstring.equalsIgnoreCase(p.getName())) {						
						return guilds.getString("customName");
					}					
				}			  
		  }
		}		
		return null;
	}
	
	static String getRank(Player p) {
			
						
			File folder = new File(guildsPath+"/"+guildData.convertGuildNameToGuildFileName.get(getGuildName.get(p.getName())));
			YamlConfiguration guild = YamlConfiguration.loadConfiguration(folder);		
			String rank = guild.getString("members."+p.getName()+".rank");
			
			return rank;
						
	}	
	
	public static Boolean haveRequestJoin(Player p, String guildName) {
		
		File file = new File(guildsPath+"/"+guildData.convertGuildNameToGuildFileName.get(guildName));
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		if(cfg.getString("requestJoin."+p.getName())!=null)
			return true;
		
		return false;
	}
	
	public static Integer getJoinGuildDate(Player p) {
		
		File folder = new File(guildsPath+"/"+guildData.convertGuildNameToGuildFileName.get(getGuildName.get(p.getName())));
		YamlConfiguration guild = YamlConfiguration.loadConfiguration(folder);
		
		int dateInt = guild.getInt("members."+p.getName()+".joinDate");

		
		return dateInt;
}	
	
	
	public static void loadPlayerData(Player p) {
		
		hasGuild.put(p.getName(), hasGuild(p));
		getGuildName.put(p.getName(), getGuildName(p));		
		getGuildCustomName.put(p.getName(), getGuildCustomName(p));
		getRank.put(p.getName(), getRank(p));
		getJoinGuildDate.put(p.getName(), getJoinGuildDate(p));
		
	}
	
	public static void enableLoadDatabase() {
		
		data.guildData.loadAllGuildData();
		File folder = new File("plugins/Guilds/guilds");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {	
				  File folder2= listOfFiles[i];			
				  YamlConfiguration foldercfg = YamlConfiguration.loadConfiguration(folder2);
				  for(String member : foldercfg.getConfigurationSection("members").getKeys(false)) {
					  data.playerGuildData.hasGuild.put(member, true);
					  data.playerGuildData.getGuildName.put(member, foldercfg.getString("guildName"));
					  data.playerGuildData.getGuildCustomName.put(member, foldercfg.getString("customName"));
					  data.playerGuildData.getRank.put(member, foldercfg.getString("members."+member+".rank"));
					  data.playerGuildData.getJoinGuildDate.put(member, foldercfg.getInt("members."+member+".joinDate"));
				  }
			  }
		}				
		for(Player p : Bukkit.getOnlinePlayers()) {
			data.playerGuildData.loadPlayerData(p);
		}				
	}	
	
}
