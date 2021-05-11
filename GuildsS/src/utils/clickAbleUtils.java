package utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import color.color;
import me.Cortez.guilds.Core;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class clickAbleUtils {

	public static void main(Player p) {
		
		if(p==null)
			return;
					
		FileConfiguration config = Core.getInstance().getConfig();
		
		String accept = config.getString("clickAbleChat.button.acceptButton");
		String deny = config.getString("clickAbleChat.button.denyButton");
		String acceptHover = config.getString("clickAbleChat.button.hoverText.acceptButton");
		String denyHover = config.getString("clickAbleChat.button.hoverText.denyButton");
		String spacingHover = config.getString("clickAbleChat.button.hoverText.spacing");
		
		String spacing = config.getString("clickAbleChat.button.spacing.down");
		
		if(config.getString("clickAbleChat.button.style").equalsIgnoreCase("thwart")) {
			
			spacing=Core.getInstance().getConfig().getString("clickAbleChat.button.spacing.thwart");
			
		}
		
		TextComponent acceptMessage = new TextComponent(color.add(accept));			
		acceptMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/guild accept"));
		acceptMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(color.add(acceptHover)).create()));
		
		TextComponent denyMesasge = new TextComponent(color.add(deny));				
		denyMesasge.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/guild deny"));
		denyMesasge.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(color.add(denyHover)).create()));		
		
		TextComponent spacingText = new TextComponent(spacing);
		spacingText.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/guild donothing"));
		spacingText.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(color.add(spacingHover)).create()));
		
		if(config.getBoolean("clickAbleChat.button.showButton")) {
			
			if(config.getString("clickAbleChat.button.style").equalsIgnoreCase("thwart")) {				
				
				acceptMessage.addExtra(spacingText);
				acceptMessage.addExtra(denyMesasge);
				
				p.spigot().sendMessage(acceptMessage);
				
			} else {
								
				acceptMessage.addExtra(spacingText);
				denyMesasge.addExtra(spacingText);
				
				p.spigot().sendMessage(new ComponentBuilder(spacing).append(acceptMessage).create());
				p.spigot().sendMessage(new ComponentBuilder(spacing).append(denyMesasge).create());
				
			}
		}
		
		if(Core.getInstance().getConfig().getBoolean("clickAbleChat.endlineEnable"))
			p.sendMessage(color.add(Core.getInstance().getConfig().getString("clickAbleChat.endline")));
		
	}
	
}
