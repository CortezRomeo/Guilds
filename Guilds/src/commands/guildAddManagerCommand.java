package commands;

import org.bukkit.entity.Player;

import color.color;
import files.langFile;

public class guildAddManagerCommand {
	
	public static void main(Player power, Player target) {				
		if(target!=null) {
			if(!(target.getName().equalsIgnoreCase(power.getName()))) {
				utils.guildsUtils.addManager.main(power, target);
				return;
			}
			power.sendMessage(color.add(langFile.get().getString("nameSake")));
			return;
		}
		power.sendMessage(color.add(langFile.get().getString("playerNotFound")));
		return;
	}
}
