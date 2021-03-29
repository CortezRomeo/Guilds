package utils.guildsUtils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import color.color;
import me.Cortez.guilds.Core;

public class setBoard {
	static String guildsPath = "plugins/Guilds/guilds";
	public static void main(Player p, String board) {
		if(data.playerGuildData.hasGuild.get(p.getName())) {
			if(data.playerGuildData.getRank.get(p.getName()).equalsIgnoreCase("Chủ Guild") || data.playerGuildData.getRank.get(p.getName()).equalsIgnoreCase("Quản Lý")) {	
				if(board.length()<=Core.getInstance().getConfig().getInt("board.maxLength")) {
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
				String mes = Core.getlang("setBoard.maxLength");
				mes=mes.replace("%k%", String.valueOf(Core.getInstance().getConfig().getInt("board.maxLength")));
				mes=mes.replace("%km%", String.valueOf(board.length()));
				p.sendMessage(color.add(mes));
				return;
			}
			p.sendMessage(color.add(Core.getlang("setBoard.noPer")));
			return;
		}
		p.sendMessage(color.add(Core.getlang("setBoard.noGuild")));
	}
	
}
