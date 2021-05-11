package data;

import java.io.File;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;

public class guildData {
	
	static String guildsPath = "plugins/GuildsS/guilds";
	public static int totalGuild = totalGuild();
	public static HashMap<String, String> convertGuildFileNameToGuildName = new HashMap<String, String>();
	public static HashMap<String, String> convertGuildNameToGuildFileName = new HashMap<String, String>();	
	public static HashMap<String, String> getCustomName = new HashMap<String, String>();
	public static HashMap<String, String> getBoard = new HashMap<String, String>();
	public static HashMap<String, Integer> getTotalMember = new HashMap<String, Integer>();
	public static HashMap<String, String> getChuSoHuu = new HashMap<String, String>();
	public static HashMap<String, String> getFoundedTime = new HashMap<String, String>();
	
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
				  getCustomName.put(guildname, fileGuild.getString("customName"));
				  getBoard.put(guildname, fileGuild.getString("board"));
				  getTotalMember.put(guildname, gettotalMembers(listGuild));
				  getChuSoHuu.put(guildname, getChuSoHuu(listGuild));
				  getFoundedTime.put(guildname, fileGuild.getString("foundedTime"));
				  
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
				  getCustomName.put(guildName, fileGuildCFG.getString("customName"));
				  getBoard.put(guildName, fileGuildCFG.getString("board"));
				  getTotalMember.put(guildName, gettotalMembers(fileGuild));
				  getChuSoHuu.put(guildName, getChuSoHuu(fileGuild));
				  getFoundedTime.put(guildName, fileGuildCFG.getString("foundedTime"));
				  		
	}
	
	public static void removeGuildData(String guildName) {
		  totalGuild=totalGuild();

		  convertGuildFileNameToGuildName.remove(convertGuildNameToGuildFileName.get(guildName));
		  convertGuildNameToGuildFileName.remove(guildName);		
		  getCustomName.remove(guildName);
		  getBoard.remove(guildName);
		  getTotalMember.remove(guildName);
		  getChuSoHuu.remove(guildName);
		  getFoundedTime.remove(guildName);				  		 
		  		
	}	
}
