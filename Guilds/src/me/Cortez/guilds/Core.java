package me.Cortez.guilds;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import commands.guildCommand;
import depend.guildsPAPI;
import depend.playerpointsDepend;
import depend.tokenmanagerDepend;
import depend.vaultDepend;
import files.guiFile;
import files.langFile;
import lang.guiLang;
import lang.messageLang;
import listener.InvClickEvent;
import listener.PchatEvent;
import listener.pJoinEvent;
import listener.pQuitEvent;


public class Core extends JavaPlugin{
	public static double versionPlugin = 0;
	public static List<UUID> GUIcreateGuilds = new ArrayList<UUID>();	
	public static List<Player> deny = new ArrayList<Player>();		
	
	private static Core instance;
	@Override
	public void onEnable() {
		instance=this;		
		versionPlugin=1.0;
		registerFiles();
		loadLangs();
		loadConfig();				
		loadCommands();
		loadListeners();
		loadGUI();
		loadDatabase();
		setupDepend();
		enableMessage();
	}				
	
	private void loadDatabase() {
		
		data.guildData.loadAllGuildData();
		data.playerGuildData.enableLoadDatabase();
		
	}

	private void registerFiles() {
		
		File o1=new File("plugins/Guilds");
		if(!(o1.exists())) {
			o1.mkdir();
		}
		File o2=new File("plugins/Guilds/guilds");
		if(!(o2.exists())) {
			o2.mkdir();
		}						
		
	}
		
	private void loadGUI() {
		guiFile.setup();
	}	
	
	private void loadLangs() {
		langFile.setup();
		messageLang.register();
		guiFile.setup();
		guiLang.register();
		guiFile.reload();
		langFile.reload();		
	}
	
	private void setupDepend() {		
		playerpointsDepend.setup();
        vaultDepend.setup();
		tokenmanagerDepend.setup();		
	}
	
	public void loadCommands() {
		new guildCommand(this);
	}
	
	private void loadListeners() {
		new InvClickEvent(this);
		new PchatEvent(this);
		new pJoinEvent(this);
		new pQuitEvent(this);
	}
	
	private void loadConfig() {	        
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();	
	}	
			
	public static Core getInstance(){
	    return instance;
	}	
		
	public static String getlang(String str) {
		return langFile.get().getString(str);
	}
	
	public static void bukkitCSD(String str) {
		Bukkit.getConsoleSender().sendMessage(str);		
	}
	
	public static void enableMessage() {
		bukkitCSD("§2§l==================================");
		bukkitCSD("            §b§mGUILD");
		bukkitCSD("");	
		bukkitCSD("§fPhiên bản: §e"+getInstance().getDescription().getVersion());
		bukkitCSD("§fTác giả: §e"+getInstance().getDescription().getAuthors());
		bukkitCSD("");
		bukkitCSD("§2● §aLiên kết");
		if(papicheck()==true)  {
			bukkitCSD("   §aPlaceholderAPI");
			new guildsPAPI().register();
		} else
			bukkitCSD("   §cPlaceholderAPI");		
		if(vaultDepend.check()==true) 
			bukkitCSD("   §aVault");
		else
			bukkitCSD("   §cVault");
		if(playerpointsDepend.check()==true) 
			bukkitCSD("   §aPlayerPoints");
		else
			bukkitCSD("   §cPlayerPoints");		
		if(tokenmanagerDepend.check()==true) 
			bukkitCSD("   §aTokenManager");
		else
			bukkitCSD("   §cTokenManager");		
		bukkitCSD("");
		bukkitCSD("§2§l==================================");		
	}	
	
	public static boolean papicheck() {
		final Plugin plugin = Core.getInstance().getServer().getPluginManager().getPlugin("PlaceholderAPI");
	    if(plugin!=null) {
	    	return true;
	    }
	    return false;
	}
	
}
