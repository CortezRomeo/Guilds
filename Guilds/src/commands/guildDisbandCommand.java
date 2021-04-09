package commands;

import org.bukkit.entity.Player;

import me.Cortez.guilds.Core;
import utils.cooldownUtils;
import utils.guildsUtils.disband;

public class guildDisbandCommand {
	
	public static void main(Player p) {	
		
		if(cooldownUtils.coolDown(p,"disband",Core.getInstance().getConfig().getInt("disband.cooldownTime"))) {
			
			String form = Core.getInstance().getConfig().getString("disband.form");
			
			if(form.equalsIgnoreCase("gui") && Core.getInstance().getConfig().getBoolean("enableGUI"))
				disband.GUIform(p);
			disband.CHATform(p);
		}
	}
}
