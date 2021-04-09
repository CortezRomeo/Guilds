package depend;

import org.bukkit.plugin.Plugin;

import me.Cortez.guilds.Core;
import me.realized.tokenmanager.api.TokenManager;

public class tokenmanagerDepend {
	public static TokenManager tokenmanager;
	
	public static boolean check() {
	    final Plugin plugin = Core.getInstance().getServer().getPluginManager().getPlugin("TokenManager");
	    if(plugin!=null) {
	    	return true;
	    }
	    return false;
	}	
	public static void setup() {		
	    if(check()==true)
	    	tokenmanager = TokenManager.class.cast(Core.getInstance().getServer().getPluginManager().getPlugin("TokenManager"));	    
	}
}
