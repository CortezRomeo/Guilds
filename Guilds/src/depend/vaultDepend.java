package depend;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import me.Cortez.guilds.Core;
import net.milkbowl.vault.economy.Economy;

public class vaultDepend {
    public static Economy econ = null;
	
	public static boolean check() {
		final Plugin plugin = Core.getInstance().getServer().getPluginManager().getPlugin("Vault");
	    if(plugin!=null) {
	    	return true;
	    }
	    return false;
	}
	
    public static boolean setup() {
        if (check()==false) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Core.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
