package depend;

import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.plugin.Plugin;

import me.Cortez.guilds.Core;

public class playerpointsDepend {
	public static PlayerPoints playerpoints;
	
	public static boolean check() {
		final Plugin plugin = Core.getInstance().getServer().getPluginManager().getPlugin("PlayerPoints");
	    if(plugin!=null) {
	    	return true;
	    }
	    return false;
	}
	public static void setup() {		
	    if(check()==true)
	    	playerpoints = PlayerPoints.class.cast(Core.getInstance().getServer().getPluginManager().getPlugin("PlayerPoints"));	    
	}
}
