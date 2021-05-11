package utils.guildsUtils;

import org.bukkit.entity.Player;

import color.color;
import me.Cortez.guilds.Core;

public class deny {
	
	public static void main(Player p) {
		
		if(Core.deny.contains(p)) {
			
			p.sendMessage("Đừng tương tác quá nhanh!");
			return;
			
		}
		
		if(disband.requestDisband.contains(p.getUniqueId()) || 
				invite.requestInvite.contains(p.getUniqueId()) ||
						leave.requestLeave.contains(p.getUniqueId())) {
			
			p.sendMessage(color.add(Core.getlang("waiting")));
			Core.deny.add(p);
			return;
			
		}
		
		p.sendMessage(color.add(Core.getlang("noRequest.deny")));
		return;
	}
	
}
