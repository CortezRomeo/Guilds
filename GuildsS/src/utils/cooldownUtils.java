package utils;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import color.color;
import files.langFile;

public class cooldownUtils {
	private static HashMap<UUID, Long> cooldownInvite = new HashMap<UUID, Long>();
	private static HashMap<UUID, Long> cooldownDisband = new HashMap<UUID, Long>();	
	private static HashMap<UUID, Long> cooldownLeave = new HashMap<UUID, Long>();		
	public static boolean coolDown(Player p, String type , Integer timeLeft) {
		
		if(type.equals("invite")) {
			int cooldowntime = timeLeft;
			if(cooldownInvite.containsKey(p.getUniqueId())) {
				long secondsLeft = ((cooldownInvite.get(p.getUniqueId())/1000)+cooldowntime) - (System.currentTimeMillis()/1000);				            
				if(secondsLeft>0) {				            	
					p.sendMessage(color.add(langFile.get().getString("timeLeft.invite").replace("%second%", String.valueOf(secondsLeft))));
					return false;
				}				         
			}					
			cooldownInvite.put(p.getUniqueId(), System.currentTimeMillis());	
		} else
		if(type.equals("disband")) {
			int cooldowntime = timeLeft;
			if(cooldownDisband.containsKey(p.getUniqueId())) {
				long secondsLeft = ((cooldownDisband.get(p.getUniqueId())/1000)+cooldowntime) - (System.currentTimeMillis()/1000);				            
				if(secondsLeft>0) {				            	
					p.sendMessage(color.add(langFile.get().getString("timeLeft.disband").replace("%second%", String.valueOf(secondsLeft))));
					return false;
				}				         
			}					
			cooldownDisband.put(p.getUniqueId(), System.currentTimeMillis());	
		} else
			if(type.equals("leave")) {
				int cooldowntime = timeLeft;
				if(cooldownLeave.containsKey(p.getUniqueId())) {
					long secondsLeft = ((cooldownLeave.get(p.getUniqueId())/1000)+cooldowntime) - (System.currentTimeMillis()/1000);				            
					if(secondsLeft>0) {				            	
						p.sendMessage(color.add(langFile.get().getString("timeLeft.leave").replace("%second%", String.valueOf(secondsLeft))));
						return false;
					}				         
				}					
				cooldownLeave.put(p.getUniqueId(), System.currentTimeMillis());	
			}	
	
		return true;
	}
	
}
