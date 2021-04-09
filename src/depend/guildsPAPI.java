package depend;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.entity.Player;

import color.color;
import data.playerGuildData;
import files.langFile;
import me.Cortez.guilds.Core;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

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
        
		if(!playerGuildData.hasGuild.get(player.getName())) 
        	return color.add(langFile.get().getString("papiNoGuild"));

        if(identifier.equals("guildname"))         	
            return data.playerGuildData.getGuildName.get(player.getName());     	        	
                
        if(identifier.equals("guildcustomname"))
            return data.playerGuildData.getGuildCustomName.get(player.getName());
                
        if(identifier.equals("joindate")) {			
			Integer dateInt = Integer.valueOf(data.guildData.getfoundedTime.get(data.playerGuildData.getGuildName.get(player.getName())));
			Date date = new Date(((long)dateInt)*1000L);
			SimpleDateFormat formatter = new SimpleDateFormat(Core.getInstance().getConfig().getString("dateFormat"));
			String dateString = formatter.format(date);        	
            return dateString;			
		} 
        
        if(identifier.equals("rank"))
            return data.playerGuildData.getRank.get(player.getName());       

        return null;
    }	
}
