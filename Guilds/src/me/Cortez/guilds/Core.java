package me.Cortez.guilds;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
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
	public static List<UUID> GUIcreateGuilds = new ArrayList<UUID>();	
	public static List<UUID> requestGuild = new ArrayList<UUID>();
	public static List<UUID> expired = new ArrayList<UUID>();
	public static List<UUID> refuse = new ArrayList<UUID>();
	
	private static Core instance;
	@Override
	public void onEnable() {
		instance=this;		
		registerFiles();
		loadLangs();
		loadConfig();				
		loadCommands();
		loadListeners();
		loadGUI();
		guiFile.reload();
		langFile.reload();
		loadDatabase();
		playerpointsDepend.setup();
        vaultDepend.setup();
		tokenmanagerDepend.setup();	
		enableMessage();
	}				
	
	private void loadDatabase() {
		
		data.guildData.loadAllGuildData();
		File folder = new File("plugins/Guilds/guilds");
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {	
				  File folder2= listOfFiles[i];			
				  YamlConfiguration foldercfg = YamlConfiguration.loadConfiguration(folder2);
				  for(String member : foldercfg.getConfigurationSection("members").getKeys(false)) {
					  data.playerGuildData.hasGuild.put(member, true);
					  data.playerGuildData.getGuildName.put(member, foldercfg.getString("guildName"));
					  data.playerGuildData.getGuildCustomName.put(member, foldercfg.getString("customName"));
					  data.playerGuildData.getRank.put(member, foldercfg.getString("members."+member+".rank"));
					  data.playerGuildData.getJoinGuildDate.put(member, foldercfg.getInt("members."+member+".joinDate"));
				  }
			  }
		}				
		for(Player p : Bukkit.getOnlinePlayers()) {
			data.playerGuildData.loadPlayerData(p);
		}				
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
