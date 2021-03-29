package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import color.color;
import files.langFile;
import me.Cortez.guilds.Core;

public class guiItemUtils {
	
	
	public static ItemStack getItem(String type,String value,Short id,String name,String target,List<String> lore,String replace, File folderGuild) {
		ItemStack nulls = new ItemStack(Material.BEDROCK);
		ItemMeta nullsmeta = nulls.getItemMeta();
		nullsmeta.setDisplayName("§cLỗi! Vui lòng kiểm tra lại type, value item trong gui.yml");
		nulls.setItemMeta(nullsmeta);
		if(type.equalsIgnoreCase("customhead")) {
			ItemStack head=customHeadUtils.setcustomHead(value);						
			ItemMeta headmeta = head.getItemMeta();
						
			// --------------------NAME---------------------------
				
        	if(replace.equals("guildStats")) {
				name=name.replace("%guildName%", data.guildData.convertGuildFileNameToGuildName.get(folderGuild.getName()));
        	}
        	if(replace.equals("memberStats")) {
				name=name.replace("%memberName%", target);
        	}
				headmeta.setDisplayName(color.add(name));

			
			// ---------------------LORE-----------------------
			
	        List<String> createLore = new ArrayList<String>();
	        for (Object lores : (List<String>) lore) {
	        	createLore.add(ChatColor.translateAlternateColorCodes('&', lores.toString()));
	        }

		        List<String> newList = new ArrayList<String>();
		        for (String string : createLore) {
		        			        	
		        	string=string.replace("%totalguild%", String.valueOf(data.guildData.totalGuild));	
		        	
		        	if(replace.equals("guildStats")) {
			        	YamlConfiguration cfg = YamlConfiguration.loadConfiguration(folderGuild);
						String guildName = cfg.getString("guildName");
						Integer dateInt = Integer.valueOf(data.guildData.getfoundedTime.get(guildName));
						Date date = new Date(((long)dateInt)*1000L);
						SimpleDateFormat formatter = new SimpleDateFormat(Core.getInstance().getConfig().getString("dateFormat"));
						String dateString = formatter.format(date);
			        	string=string.replace("%chuSoHuu%", data.guildData.getChuSoHuu.get(guildName));
			        	string=string.replace("%totalMembers%", String.valueOf(data.guildData.gettotalMembers.get(guildName)));
			        	string=string.replace("%board%", data.guildData.getboard.get(guildName));
			        	string=string.replace("%customname%", data.guildData.getcustomname.get(guildName));	        	
			        	string=string.replace("%foundedTime%", dateString);
		        	}
		        	if(replace.equals("memberStats")) {
						Integer dateInt = Integer.valueOf(data.playerGuildData.getJoinGuildDate.get(target));
						Date date = new Date(((long)dateInt)*1000L);
						SimpleDateFormat formatter = new SimpleDateFormat(Core.getInstance().getConfig().getString("dateFormat"));
						String dateString = formatter.format(date);
						string=string.replace("%rank%", data.playerGuildData.getRank.get(target));
						string=string.replace("%joinDate%", dateString);
						if(Bukkit.getPlayer(target)!=null) {
							string=string.replace("%status%", color.add(langFile.get().getString("status.online")));
						} else 
							string=string.replace("%status%", color.add(langFile.get().getString("status.offline")));
		        	}
		        	
		        newList.add(string);
		        
		        }
		       
		        headmeta.setLore(newList);

	        
			// -----------------------------------------------
	        
	        head.setItemMeta(headmeta);
	        return head;	        
		}
		
				
		if(type.equalsIgnoreCase("material")) {
			ItemStack material=new ItemStack(Material.valueOf(value),1,id);
			ItemMeta mmeta = material.getItemMeta();
			
			// --------------------NAME---------------------------
			
        	if(replace.equals("guildStats")) {
				name=name.replace("%guildName%", data.guildData.convertGuildFileNameToGuildName.get(folderGuild.getName()));
        	}
        	if(replace.equals("memberStats")) {
				name=name.replace("%memberName%", target);
        	}
        	mmeta.setDisplayName(color.add(name));

			
			// ---------------------LORE-----------------------
			
	        List<String> createLore = new ArrayList<String>();
	        for (Object lores : (List<String>) lore) {
	        	createLore.add(ChatColor.translateAlternateColorCodes('&', lores.toString()));
	        }

		        List<String> newList = new ArrayList<String>();
		        for (String string : createLore) {
		        	
		        	if(replace.equals("guildStats")) {
			        	YamlConfiguration cfg = YamlConfiguration.loadConfiguration(folderGuild);
						String guildName = cfg.getString("guildName");
						Integer dateInt = Integer.valueOf(data.guildData.getfoundedTime.get(guildName));
						Date date = new Date(((long)dateInt)*1000L);
						SimpleDateFormat formatter = new SimpleDateFormat(Core.getInstance().getConfig().getString("dateFormat"));
						String dateString = formatter.format(date);
			        	string=string.replace("%chuSoHuu%", data.guildData.getChuSoHuu.get(guildName));
			        	string=string.replace("%totalMembers%", String.valueOf(data.guildData.gettotalMembers.get(guildName)));
			        	string=string.replace("%board%", data.guildData.getboard.get(guildName));
			        	string=string.replace("%customname%", data.guildData.getcustomname.get(guildName));
			        	string=string.replace("%totalguild%", String.valueOf(data.guildData.totalGuild));		        	
			        	string=string.replace("%foundedTime%", dateString);
		        	}
		        	if(replace.equals("memberStats")) {
						Integer dateInt = Integer.valueOf(data.playerGuildData.getJoinGuildDate.get(target));
						Date date = new Date(((long)dateInt)*1000L);
						SimpleDateFormat formatter = new SimpleDateFormat(Core.getInstance().getConfig().getString("dateFormat"));
						String dateString = formatter.format(date);
						string=string.replace("%rank%", data.playerGuildData.getRank.get(target));
						string=string.replace("%joinDate%", dateString);
						if(Bukkit.getPlayer(target)!=null) {
							string=string.replace("%status%", color.add(langFile.get().getString("status.online")));
						} else 
							string=string.replace("%status%", color.add(langFile.get().getString("status.offline")));
		        	}
		        	
		        newList.add(string);
		        
		        }
		       
		        mmeta.setLore(newList);
	        
			// -----------------------------------------------
	        
	        material.setItemMeta(mmeta);
	        return material;				
		}
		
		return nulls;
	}
}
