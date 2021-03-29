package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import color.color;
import files.langFile;
import gui.noGuild;
import me.Cortez.guilds.Core;

public class guildCommand implements CommandExecutor{
	private Core plugin;
	public guildCommand(Core plugin) {
		this.plugin=plugin;
		plugin.getCommand("guild").setExecutor(this);
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(sender instanceof Player) {									
			Player p = (Player)sender;
			
			if(data.playerGuildData.hasGuild.get(p.getName())==null) {
				p.sendMessage("§cĐã xảy ra lỗi trong quá trình lấy dữ liệu!");				
				return false;
			}
			
			if(args.length==0) {
				
				if(plugin.getConfig().getBoolean("enableGUI")) {
					if(!(data.playerGuildData.hasGuild.get(p.getName()))) {
							p.openInventory(noGuild.main(p));
							return false;
						}
					// open guild menu
					// code
					return false;
				}		
				
				for(String word : langFile.get().getStringList(("help"))) {
					p.sendMessage(color.add(word));
				}				
				return false;
				
			} else if(args.length==1) {
				
				if(args[0].equalsIgnoreCase("accept")) {
					
					guildAcceptCommand.main(p);
					return false;
					
				} else if(args[0].equalsIgnoreCase("disband")) {
					
					guildDisbandCommand.main(p);
					return false;
					
				} else if(args[0].equalsIgnoreCase("refuse")) {
					
					guildRefuseCommand.main(p);
					return false;
					
				} else if(args[0].equalsIgnoreCase("stats")) {
					
					guildStatsCommand.main(p);
					return false;
					
				} else if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {
					
					for(String word : langFile.get().getStringList(("help"))) {
						p.sendMessage(color.add(word));
					}			
					
				}
				return false;
				
			} else {
				
				if(args[0].equalsIgnoreCase("setboard")) {
					
	                StringBuilder sb = new StringBuilder();
	                for(int i = 1; i < args.length; i++){
	                    sb.append(args[i] + " ");
	                }
	                String board = color.add(sb.toString().trim());
					
					guildBoardCommand.main(p, board);
					return false;
				} else if(args[0].equalsIgnoreCase("setcustomname")) {
					
	                StringBuilder sb = new StringBuilder();
	                for(int i = 1; i < args.length; i++){
	                    sb.append(args[i] + " ");
	                }
	                String board = color.add(sb.toString().trim());
					
					guildCustomNameCommand.main(p, board);
					return false;										
					
				} else if(args[0].equalsIgnoreCase("create")) {
					
	                StringBuilder sb = new StringBuilder();
	                for(int i = 1; i < args.length; i++){
	                    sb.append(args[i] + " ");
	                }
	                String guildname = color.add(sb.toString().trim());
					
					guildCreateCommand.main(p, guildname);
					return false;				
				
				} else if(args[0].equalsIgnoreCase("invite")) {								
									
					guildInviteCommand.main(p,Bukkit.getPlayer(args[1]));
					return false;
					
				} else if(args[0].equalsIgnoreCase("addmanager")) {								
					
					guildAddManagerCommand.main(p,Bukkit.getPlayer(args[1]));
					return false;
					
				}  else if(args[0].equalsIgnoreCase("removemanager")) {								
					
					guildRemoveManagerCommand.main(p,Bukkit.getPlayer(args[1]));
					return false;
					
				}  else if(args[0].equalsIgnoreCase("kick")) {								
					
					guildKickCommand.main(p,Bukkit.getPlayer(args[1]));
					return false;	
					
				}
			}			
		}
		return false;
		
	}

}
