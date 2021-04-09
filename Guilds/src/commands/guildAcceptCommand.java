package commands;

import org.bukkit.entity.Player;

import color.color;
import me.Cortez.guilds.Core;
import utils.guildsUtils.disband;
import utils.guildsUtils.invite;

public class guildAcceptCommand {	
	public static void main(Player p) {

		if(disband.requestDisband.contains(p.getUniqueId())) {
			p.sendMessage(color.add(Core.getlang("waiting")));
			disband.requestDisband.remove(p.getUniqueId());
			return;
		}	
		
		if(invite.requestInvite.contains(p.getUniqueId())) {
			p.sendMessage(color.add(Core.getlang("waiting")));
			invite.requestInvite.remove(p.getUniqueId());
			return;
		}			
		
		p.sendMessage(color.add(Core.getlang("noRequest.accept")));
		return;
	}
}
