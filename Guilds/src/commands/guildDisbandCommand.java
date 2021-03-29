package commands;

import org.bukkit.entity.Player;

import me.Cortez.guilds.Core;
import utils.cooldownUtils;

public class guildDisbandCommand {
	
	public static void main(Player p) {	
		
		if(cooldownUtils.coolDown(p,"disband",Core.getInstance().getConfig().getInt("disband.cooldownTime"))) {			
			utils.guildsUtils.disband.main(p);
		}
	}
}
