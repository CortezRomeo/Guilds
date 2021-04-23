package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import color.color;
import files.guiFile;
import me.Cortez.guilds.Core;
import utils.guiItemUtils;
import utils.guildsUtils.disband;

public class disbandGUI implements Listener{
	@SuppressWarnings("unused")
	private Core plugin;

	public disbandGUI(Core plugin) {
		this.plugin = plugin;
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}	
	
	public static HashMap<String, Integer> timeout = new HashMap<String, Integer>();
	public static List<String> cancel = new ArrayList<String>();
	public static List<String> accept = new ArrayList<String>();

	public static void main(Player p) {

		if (cancel.contains(p.getName())) {
			while (cancel.contains(p.getName())) {
				cancel.remove(p.getName());
			}
		}
		timeout.put(p.getName(), Core.getInstance().getConfig().getInt("disband.expiresTime.GUI"));

		Inventory ivng = Bukkit.createInventory(p, guiFile.get().getInt("gui.disband.rows") * 9, color.add(guiFile.get()
				.getString("gui.disband.title").replace("%seconds%", String.valueOf(timeout.get(p.getName())))));

		if (guiFile.get().getBoolean("gui.noGuild.enableBorder") == true) {
			border.enableBorder(guiFile.get().getInt("gui.noGuild.rows"), ivng);
		}

		ivng.setItem(guiFile.get().getInt("gui.disband.items.cancel.slot"),
				guiItemUtils.getItem(guiFile.get().getString("gui.disband.items.cancel.type"),
						guiFile.get().getString("gui.disband.items.cancel.value"),
						(short) guiFile.get().getInt("gui.disband.items.cancel.id"),
						guiFile.get().getString("gui.disband.items.cancel.name"), "",
						guiFile.get().getStringList(("gui.disband.items.cancel.lore")), "", null));

		p.openInventory(ivng);

		new BukkitRunnable() {

			@Override
			public void run() {

				if (cancel.contains(p.getName())) {

					this.cancel();
					timeout.remove(p.getName());
					cancel.remove(p.getName());
					return;

				}

				if (timeout.get(p.getName()) > 0) {

					timeout.put(p.getName(), timeout.get(p.getName()) - 1);

					try {
						ivng.setItem(guiFile.get().getInt("gui.disband.items.seconds!=0.slot"),
								guiItemUtils.getItem(guiFile.get().getString("gui.disband.items.seconds!=0.type"),
										guiFile.get().getString("gui.disband.items.seconds!=0.value"),
										(short) guiFile.get().getInt("gui.disband.items.seconds!=0.id"),
										guiFile.get().getString("gui.disband.items.seconds!=0.name"), p.getName(),
										guiFile.get().getStringList(("gui.disband.items.seconds!=0.lore")), "timeout",
										null));

						p.updateInventory();
					} catch (Exception e) {
					}

					return;

				}

				if (timeout.get(p.getName()) == 0) {

					this.cancel();
					timeout.remove(p.getName());

					try {
						ivng.setItem(guiFile.get().getInt("gui.disband.items.seconds==0.slot"),
								guiItemUtils.getItem(guiFile.get().getString("gui.disband.items.seconds==0.type"),
										guiFile.get().getString("gui.disband.items.seconds==0.value"),
										(short) guiFile.get().getInt("gui.disband.items.seconds==0.id"),
										guiFile.get().getString("gui.disband.items.seconds==0.name"), "",
										guiFile.get().getStringList(("gui.disband.items.seconds==0.lore")), "", null));

						p.updateInventory();
					} catch (Exception e) {
					}
				}

			}
		}.runTaskTimerAsynchronously(Core.getInstance(), 0, 20);
	}
	
	@EventHandler
	public void clickEvent(InventoryClickEvent e) {
		
		if (e.getInventory().getTitle().equals(color.add(guiFile.get().getString("gui.disband.title")))) {

			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();

			InventoryAction action = e.getAction();
			switch (action) {
			case MOVE_TO_OTHER_INVENTORY:
				e.setCancelled(true);
				return;
			case HOTBAR_MOVE_AND_READD:
				e.setCancelled(true);
				return;
			case HOTBAR_SWAP:
				e.setCancelled(true);
				return;
			default:
				break;
			}

			if (!disbandGUI.timeout.containsKey(p.getName())) {

				if (e.getSlot() == guiFile.get().getInt("gui.disband.items.seconds==0.slot")) {

					disband.succes(p);
					disbandGUI.accept.add(p.getName());
					p.closeInventory();

				}
			}

			if (e.getSlot() == guiFile.get().getInt("gui.disband.items.cancel.slot")) {

				p.closeInventory();

			}

		}
		
	}	
	
}
