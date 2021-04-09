package utils;

import org.bukkit.entity.Player;

import color.color;
import me.Cortez.guilds.Core;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class clickAbleUtils {

	public static void main(Player p) {
		
		if(p==null)
			return;
					
		String accept = Core.getInstance().getConfig().getString("clickAbleChat.accept");
		String deny = Core.getInstance().getConfig().getString("clickAbleChat.deny");				
		
		TextComponent acceptMessage = new TextComponent(color.add(accept));			
		acceptMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/guild accept"));
		
		TextComponent denyMesasge = new TextComponent(color.add(deny));				
		denyMesasge.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/guild deny"));
		
		p.spigot().sendMessage(acceptMessage);
		p.spigot().sendMessage(denyMesasge);
		
	}
	
}
