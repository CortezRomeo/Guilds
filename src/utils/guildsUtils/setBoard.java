package utils.guildsUtils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import color.color;
import me.Cortez.guilds.Core;

public class setBoard {
	static String guildsPath = "plugins/Guilds/guilds";
	
	public static void main(Player p, String board) {
		
		if(!data.playerGuildData.hasGuild.get(p.getName())) {
			
			p.sendMessage(color.add(Core.getlang("noGuild")));
			return;
			
		}
		
		if(!data.playerGuildData.getRank.get(p.getName()).equalsIgnoreCase("Chủ Guild")) {
			
			if(!data.playerGuildData.getRank.get(p.getName()).equalsIgnoreCase("Quản Lý")) {
				
				p.sendMessage(color.add(Core.getlang("kick.pIsNotHaveThePower")));
				return;
				
			}
			
		}
		
		if(board.length()>Core.getInstance().getConfig().getInt("board.maxLength")) {
			
			String mes = Core.getlang("setBoard.maxLength");
			mes=mes.replace("%k%", String.valueOf(Core.getInstance().getConfig().getInt("board.maxLength")));
			mes=mes.replace("%km%", String.valueOf(board.length()));
			
			p.sendMessage(color.add(mes));
			
			return;
			
		}
		
		succes(p, board);
		
	}
	
	public static void succes(Player p,String board) {
		
		File folder = new File(guildsPath+"/"+data.guildData.convertGuildNameToGuildFileName.get(data.playerGuildData.getGuildName.get(p.getName())));
		YamlConfiguration guild = YamlConfiguration.loadConfiguration(folder);
		
		guild.set("board", board);
		try {
			guild.save(folder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		p.sendMessage(color.add(Core.getlang("setBoard.sucess").replace("%board%", board)));
		
		data.guildData.loadGuildData(data.playerGuildData.getGuildName.get(p.getName()),null);
		return;
		
	}
	
}
