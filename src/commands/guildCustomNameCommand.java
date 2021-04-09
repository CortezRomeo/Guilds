package commands;

import org.bukkit.entity.Player;

public class guildCustomNameCommand {
	
	public static void main(Player p,String board) {						
		utils.guildsUtils.setCustomName.main(p, board);
	}
}
