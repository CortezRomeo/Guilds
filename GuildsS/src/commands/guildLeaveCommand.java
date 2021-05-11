package commands;

import org.bukkit.entity.Player;

import me.Cortez.guilds.Core;
import utils.cooldownUtils;
import utils.guildsUtils.leave;

public class guildLeaveCommand {
	
	public static void main(Player p) {	
		
		if(cooldownUtils.coolDown(p,"leave",Core.getInstance().getConfig().getInt("leave.cooldownTime"))) {
			
			/*
			 * String form = Core.getInstance().getConfig().getString("leave.form");
			 * 
			 * if(form.equalsIgnoreCase("gui") &&
			 * Core.getInstance().getConfig().getBoolean("enableGUI")) { disband.GUIform(p);
			 * return; }
			 */
			
			leave.CHATform(p);
		}
	}
}
