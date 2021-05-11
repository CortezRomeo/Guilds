package api;

import data.guildData;
import data.playerGuildData;

public class guildAPI {

	public static boolean hasGuild(String playerName) {
		
		if(playerGuildData.hasGuild.get(playerName)!=null && playerGuildData.hasGuild.get(playerName)) 
			return true;
		
		return false;
		
	}
	
	public static String getPlayerGuildName(String playerName) {
		
		if(hasGuild(playerName)) 
			return playerGuildData.getGuildName.get(playerName);					
		
		return null;
		
	}
	
	public static String getPlayerGuildCustomName(String playerName) {
		
		if(hasGuild(playerName)) 
			return playerGuildData.getGuildCustomName.get(playerName);					
		
		return null;
		
	}
	
	public static Integer getPlayerJoinDate(String playerName) {
		
		if(hasGuild(playerName)) 
			return playerGuildData.getJoinGuildDate.get(playerName);					
		
		return null;
		
	}
	
	public static String getPlayerRank(String playerName) {
		
		if(hasGuild(playerName)) 
			return playerGuildData.getRank.get(playerName);					
		
		return null;
		
	}	
	
	public static Integer getTotalGuild() {
		return guildData.totalGuild;
	}
	
	public static boolean checkGuildNotNull(String guildName) {		
		
		if(data.guildData.convertGuildNameToGuildFileName.containsKey(guildName))
			return true;
		
		return false;
		
	}	
	
	public static String getGuildCustomName(String guildName) {
		
		if(checkGuildNotNull(guildName)) 
			return guildData.getCustomName.get(guildName);
		
		return null;	
		
	}
	
	public static String getGuildBoard(String guildName) {
		
		if(checkGuildNotNull(guildName)) 
			return guildData.getBoard.get(guildName);
		
		return null;		
		
	}	
	
	public static String getGuildFoundedTime(String guildName) {
		
		if(checkGuildNotNull(guildName)) 
			return guildData.getFoundedTime.get(guildName);
		
		return null;		
		
	}	
	
	public static Integer getGuildTotalMember(String guildName) {
		
		if(checkGuildNotNull(guildName)) 
			return guildData.getTotalMember.get(guildName);
		
		return null;		
		
	}		
	
	public static String getChuGuild(String guildName) {
		
		if(checkGuildNotNull(guildName)) 
			return guildData.getChuSoHuu.get(guildName);
		
		return null;		
		
	}			
	
}
