package commands;

import org.bukkit.entity.Player;

public class guildCreateCommand {
	
	public static void main(Player p,String args1) {						
		utils.guildsUtils.createGuild.main(p, args1);
	}
}
