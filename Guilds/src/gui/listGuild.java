package gui;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import color.color;
import files.guiFile;
import files.langFile;
import invPage.Button;
import invPage.PagedPane;
import utils.guiItemUtils;

public class listGuild {
	static String guildsPath = "plugins/Guilds/guilds";
    public static void main(Player p) {

        		PagedPane pagedPane = new PagedPane(guiFile.get().getInt("gui.listGuild.rows")-1,
        		guiFile.get().getInt("gui.listGuild.rows"),
        		color.add(guiFile.get().getString("gui.listGuild.title")));


        Set<ItemStack> guildItems = new HashSet<ItemStack>();

		File folder = new File(guildsPath);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {	
				  File folder2= listOfFiles[i];			

				  try {
				  guildItems.add(
			        		guiItemUtils.getItem(
			        				guiFile.get().getString("gui.listGuild.items.guild.type"),
			        				guiFile.get().getString("gui.listGuild.items.guild.value"),(short)
			        				guiFile.get().getInt("gui.listGuild.items.guild.type"),
			        				guiFile.get().getString("gui.listGuild.items.guild.name"),"",
			        				guiFile.get().getStringList(("gui.listGuild.items.guild.lore")),
			        				"guildStats",folder2));
				  } catch (Exception e) {
					  e.printStackTrace();
				}
			  }
		}
        

        for (ItemStack items : guildItems) {
            pagedPane.addButton(new Button(items, new Consumer<InventoryClickEvent>() {
                @Override
                public void accept(InventoryClickEvent e) {
                    HumanEntity whoClicked = e.getWhoClicked();

                    if (whoClicked instanceof Player) {                    	
                        Player player = (Player) whoClicked;
                        
                        if(data.guildData.getChuSoHuu.get(color.reset(items.getItemMeta().getDisplayName()))==null) {
                        	player.closeInventory();
                        	player.sendMessage(color.add(langFile.get().getString("guildDoesntExist").replace("%guildName%", 
                        				color.reset(items.getItemMeta().getDisplayName()))));
                        } else {                            
                            data.playerGuildData.getClickedGuildName.put(player.getName(), color.reset(items.getItemMeta().getDisplayName()));                        
                            player.openInventory(GVGI.main(player, data.playerGuildData.getClickedGuildName.get(player.getName())));
                        }
                        
                    }
                }
            }));
        }
        pagedPane.open(p);
    }
}
