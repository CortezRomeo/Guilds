package data;

import java.io.File;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;

public class guildData {
	
	static String guildsPath = "plugins/Guilds/guilds";
	public static int totalGuild = totalGuild();
	public static HashMap<String, String> convertGuildFileNameToGuildName = new HashMap<String, String>();
	public static HashMap<String, String> convertGuildNameToGuildFileName = new HashMap<String, String>();	
	public static HashMap<String, String> getcustomname = new HashMap<String, String>();
	public static HashMap<String, String> getboard = new HashMap<String, String>();
	public static HashMap<String, Integer> gettotalMembers = new HashMap<String, Integer>();
	public static HashMap<String, String> getChuSoHuu = new HashMap<String, String>();
	public static HashMap<String, String> getfoundedTime = new HashMap<String, String>();
	
	static int totalGuild() {
	    File directory=new File(guildsPath);
	    int guilds =directory.list().length;
	    return guilds;
	}
	
	static int gettotalMembers(File folderGuild) {
		
		YamlConfiguration guild = YamlConfiguration.loadConfiguration(folderGuild);		
		return guild.getConfigurationSection("members").getKeys(false).size();
		
	}
	
	static String getChuSoHuu(File folderGuild) {
		
		YamlConfiguration guild = YamlConfiguration.loadConfiguration(folderGuild);
		for(String members : guild.getConfigurationSection("members.").getKeys(false)) {								
			if(guild.getString(("members."+members+".rank")).equalsIgnoreCase("Chủ Guild")) {
				return members;
			}		
		}
		return null;
		
	}	
	
	public static void loadAllGuildData() {
		totalGuild=totalGuild();
		
		File folder = new File(guildsPath);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {	
				  
				  File listGuild = listOfFiles[i];		
				  YamlConfiguration fileGuild = YamlConfiguration.loadConfiguration(listGuild);
				  String guildname = fileGuild.getString("guildName");				  
				  
				  convertGuildFileNameToGuildName.put(listGuild.getName(), fileGuild.getString("guildName"));
				  convertGuildNameToGuildFileName.put(fileGuild.getString("guildName"), listGuild.getName());		
				  getcustomname.put(guildname, fileGuild.getString("customName"));
				  getboard.put(guildname, fileGuild.getString("board"));
				  gettotalMembers.put(guildname, gettotalMembers(listGuild));
				  getChuSoHuu.put(guildname, getChuSoHuu(listGuild));
				  getfoundedTime.put(guildname, fileGuild.getString("foundedTime"));
				  
			  }
		}				
	}
	
	public static void loadGuildData(String guildName, String guildFileName) {
		  totalGuild=totalGuild();
				File fileGuild = new File("");	
		
				if(guildFileName==null) {
					fileGuild = new File(guildsPath+"/"+convertGuildNameToGuildFileName.get(guildName));	
				} else
					fileGuild = new File(guildsPath+"/"+guildFileName+".yml");	
				YamlConfiguration fileGuildCFG = YamlConfiguration.loadConfiguration(fileGuild);
		
				  convertGuildFileNameToGuildName.put(fileGuild.getName(), fileGuildCFG.getString("guildName"));
				  convertGuildNameToGuildFileName.put(fileGuildCFG.getString("guildName"), fileGuild.getName());		
				  getcustomname.put(guildName, fileGuildCFG.getString("customName"));
				  getboard.put(guildName, fileGuildCFG.getString("board"));
				  gettotalMembers.put(guildName, gettotalMembers(fileGuild));
				  getChuSoHuu.put(guildName, getChuSoHuu(fileGuild));
				  getfoundedTime.put(guildName, fileGuildCFG.getString("foundedTime"));
				  		
	}
	
	public static void removeGuildData(String guildName) {
		  totalGuild=totalGuild();

		  convertGuildFileNameToGuildName.remove(convertGuildNameToGuildFileName.get(guildName));
		  convertGuildNameToGuildFileName.remove(guildName);		
		  getcustomname.remove(guildName);
		  getboard.remove(guildName);
		  gettotalMembers.remove(guildName);
		  getChuSoHuu.remove(guildName);
		  getfoundedTime.remove(guildName);				  		 
		  		
}
	
}
