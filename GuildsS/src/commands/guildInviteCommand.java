package commands;

import org.bukkit.entity.Player;

import color.color;
import files.langFile;
import me.Cortez.guilds.Core;
import utils.cooldownUtils;

public class guildInviteCommand {
	public static void main(Player power, Player target) {	
		
		if(cooldownUtils.coolDown(power,"invite",Core.getInstance().getConfig().getInt("invite.cooldownTime"))) {
			if(target!=null) {
				if(!(target.getName().equalsIgnoreCase(power.getName()))) {
					utils.guildsUtils.invite.main(power, target);
					return;
				}
				power.sendMessage(color.add(langFile.get().getString("nameSake")));
				return;
			}
			power.sendMessage(color.add(langFile.get().getString("playerNotFound")));
			return;
		}			
	}
}
