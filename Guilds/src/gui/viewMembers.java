package gui;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import color.color;
import files.guiFile;
import invPage.Button;
import invPage.PagedPane;
import utils.guiItemUtils;

public class viewMembers {
	static String guildsPath = "plugins/Guilds/guilds";
    public static void main(Player p) {

        		PagedPane pagedPane = new PagedPane(guiFile.get().getInt("gui.viewMembers.rows")-1,
        		guiFile.get().getInt("gui.viewMembers.rows"),
        		color.add(guiFile.get().getString("gui.viewMembers.title")));


        Set<ItemStack> guildItems = new HashSet<ItemStack>();

		File folder2 = new File("plugins/Guilds/guilds/"+data.guildData.convertGuildNameToGuildFileName.get(data.playerGuildData.getClickedGuildName.get(p.getName())));
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(folder2);
		
		for(String members : cfg.getConfigurationSection("members").getKeys(false)) {
			  try {
			  guildItems.add(
		        		guiItemUtils.getItem(
		        				guiFile.get().getString("gui.viewMembers.items.member.type"),
		        				guiFile.get().getString("gui.viewMembers.items.member.value"),(short)
		        				guiFile.get().getInt("gui.viewMembers.items.member.type"),
		        				guiFile.get().getString("gui.viewMembers.items.member.name"),members,
		        				guiFile.get().getStringList(("gui.viewMembers.items.member.lore")),
		        				"memberStats",null));
			  } catch (Exception e) {
				  e.printStackTrace();
			}
		}
        

        for (ItemStack items : guildItems) {
            pagedPane.addButton(new Button(items, new Consumer<InventoryClickEvent>() {
                @Override
                public void accept(InventoryClickEvent e) {
                    HumanEntity whoClicked = e.getWhoClicked();

                    if (whoClicked instanceof Player) {                    	
                        //Player player = (Player) whoClicked;
                    }
                }
            }));
        }
        pagedPane.open(p);
    }
}
