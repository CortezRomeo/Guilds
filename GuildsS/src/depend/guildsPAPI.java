package depend;

import org.bukkit.entity.Player;

import api.guildAPI;
import color.color;
import files.langFile;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import utils.dateUtils;

public class guildsPAPI extends PlaceholderExpansion{

	@Override
	public String getAuthor() {
		return "Cortez Romeo";
	}

	@Override
	public String getIdentifier() {
		return "guilds";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}
	
    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null)
        	return "";
        
		if(!guildAPI.hasGuild(player.getName())) 
        	return color.add(langFile.get().getString("papiNoGuild"));

        if(identifier.equals("guildname"))         	
            return guildAPI.getPlayerGuildName(player.getName());     	        	
                
        if(identifier.equals("guildcustomname"))
            return guildAPI.getPlayerGuildCustomName(player.getName());
                
        if(identifier.equals("joindate")) {			
			
			Integer dateInt = Integer.valueOf(guildAPI.getGuildFoundedTime(guildAPI.getPlayerGuildName(player.getName())));			
            return dateUtils.main(dateInt);			
            
		} 
        
        if(identifier.equals("rank"))
            return guildAPI.getPlayerRank(player.getName());       

        return null;
    }	
}
