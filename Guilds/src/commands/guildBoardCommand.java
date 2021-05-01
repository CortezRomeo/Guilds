package commands;

import org.bukkit.entity.Player;

public class guildBoardCommand {
	
	public static void main(Player p,String board) {
		utils.guildsUtils.setBoard.main(p, board);
	}
}
