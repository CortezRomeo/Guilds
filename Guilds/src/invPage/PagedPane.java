   package invPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import color.color;
import files.guiFile;
import utils.customHeadUtils;

public class PagedPane implements InventoryHolder {

    private Inventory inventory;

    private SortedMap<Integer, Page> pages = new TreeMap<>();
    private int currentIndex;
    private int pageSize;

    protected Button controlBack;
    protected Button controlNext;

    public PagedPane(int pageSize, int rows, String title) {
        Objects.requireNonNull(title, "Title không được để trống!");
        if (rows > 6) {
            throw new IllegalArgumentException("Độ dài cần <= 6, đang có " + rows);
        }
        if (pageSize > 6) {
            throw new IllegalArgumentException("Độ dài cần <= 6, đang có" + pageSize);
        }
        
        this.pageSize = pageSize;
        inventory = Bukkit.createInventory(this, rows * 9, color(title));

        pages.put(0, new Page(pageSize));
    }

    public void addButton(Button button) {
        for (Entry<Integer, Page> entry : pages.entrySet()) {
            if (entry.getValue().addButton(button)) {
                if (entry.getKey() == currentIndex) {
                    reRender();
                }
                return;
            }
        }
        Page page = new Page(pageSize);
        page.addButton(button);
        pages.put(pages.lastKey() + 1, page);

        reRender();
    }

    public void removeButton(Button button) {
        for (Iterator<Entry<Integer, Page>> iterator = pages.entrySet().iterator(); iterator.hasNext(); ) {
            Entry<Integer, Page> entry = iterator.next();
            if (entry.getValue().removeButton(button)) {

                if (entry.getValue().isEmpty()) {
                    if (pages.size() > 1) {
                        iterator.remove();
                    }
                    if (currentIndex >= pages.size()) {
                        currentIndex--;
                    }
                }
                if (entry.getKey() >= currentIndex) {
                    reRender();
                }
                return;
            }
        }
    }


    public int getPageAmount() {
        return pages.size();
    }


    public int getCurrentPage() {
        return currentIndex + 1;
    }

	private static String getLang(String str) {
		return guiFile.get().getString(str);
	}	
    
    public void selectPage(int index) {
        if (index < 0 || index >= getPageAmount()) {
            throw new IllegalArgumentException(
                    "Error"
            );
        }
        if (index == currentIndex) {
            return;
        }

        currentIndex = index;
        reRender();
    }


    public void reRender() {
        inventory.clear();
        pages.get(currentIndex).render(inventory);

        controlBack = null;	
        controlNext = null;
        createControls(inventory);
    }

   
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        
        if(checkInventoryName().equals("listguild")) {
        
            if (event.getSlot()==guiFile.get().getInt("gui.listGuild.items.prevPage.slot")) {
                if (controlBack != null) {
                    controlBack.onClick(event);
                }
                return;
            }
            if (event.getSlot()==guiFile.get().getInt("gui.listGuild.items.nextPage.slot")) {
                if (controlNext != null) {
                    controlNext.onClick(event);
                }
                return;
            }
        } else
        if(checkInventoryName().equals("vm")) {
               
               if (event.getSlot()==guiFile.get().getInt("gui.viewMembers.items.prevPage.slot")) {
                   if (controlBack != null) {
                       controlBack.onClick(event);
                   }
                   return;
               }
               if (event.getSlot()==guiFile.get().getInt("gui.viewMembers.items.nextPage.slot")) {
                   if (controlNext != null) {
                       controlNext.onClick(event);
                   }
                   return;
               }
           }         	
        pages.get(currentIndex).handleClick(event);

    }


    @Override
    public Inventory getInventory() {
        return inventory;
    }

    protected String checkInventoryName() {    	    
    	
    	if(inventory.getName().equals(color.add(guiFile.get().getString("gui.listGuild.title")))) {
    		return "listguild";
    	} else 
    	if(inventory.getName().equals(color.add(guiFile.get().getString("gui.viewMembers.title")))) 
        	return "vm";
    	
    	return null;
    }
    protected void createControls(Inventory inventory) {
    	
    	int prevpageslot=1;
    	int nextpageslot=1;
		int pageinfoslot=1;
		int backslot=1;
    	if(checkInventoryName().equals("listguild")) {
    		nextpageslot=guiFile.get().getInt("gui.listGuild.items.nextPage.slot");
    		prevpageslot=guiFile.get().getInt("gui.listGuild.items.prevPage.slot");
    		pageinfoslot=guiFile.get().getInt("gui.listGuild.items.pageInfo.slot");
    		backslot=guiFile.get().getInt("gui.listGuild.items.back.slot");
    		
    		
    	    for (int i=guiFile.get().getInt("gui.listGuild.rows")*9-9; i<guiFile.get().getInt("gui.listGuild.rows")*9;i++)
       		inventory.setItem(i, gui.border.borderItem());
    		
    	} else
    	if(checkInventoryName().equals("vm")) {
        	nextpageslot=guiFile.get().getInt("gui.viewMembers.items.nextPage.slot");
        	prevpageslot=guiFile.get().getInt("gui.viewMembers.items.prevPage.slot");
        	pageinfoslot=guiFile.get().getInt("gui.viewMembers.items.pageInfo.slot");
        	backslot=guiFile.get().getInt("gui.viewMembers.items.back.slot");        	
        	
        	
            for (int i=guiFile.get().getInt("gui.viewMembers.rows")*9-9; i<guiFile.get().getInt("gui.viewMembers.rows")*9;i++)
          		inventory.setItem(i, gui.border.borderItem());        	
        }     		
    		
    	
        if (getCurrentPage() > 1) {
        	                                         
            ItemStack itemStack = getItem(
    				getLang("prevPage.type"),
    				getLang("prevPage.value"),(short)
    				guiFile.get().getInt("prevPage.id"),
    				getLang("prevPage.name"),
    				guiFile.get().getStringList(("prevPage.lore")));
            controlBack = new Button(itemStack, event -> selectPage(currentIndex - 1));
            inventory.setItem(prevpageslot, itemStack);
        }

        if (getCurrentPage() < getPageAmount()) {
            ItemStack itemStack = getItem(
    				getLang("nextPage.type"),
    				getLang("nextPage.value"),(short)
    				guiFile.get().getInt("nextPage.id"),
    				getLang("nextPage.name"),
    				guiFile.get().getStringList(("nextPage.lore")));
            controlNext = new Button(itemStack, event -> selectPage(getCurrentPage()));
            inventory.setItem(nextpageslot, itemStack);
        }
                    
            ItemStack itemStack = getItem(
    				getLang("pageInfo.type"),
    				getLang("pageInfo.value"),(short)
    				guiFile.get().getInt("pageInfo.id"),
    				getLang("pageInfo.name"),
    				guiFile.get().getStringList(("pageInfo.lore")));
            inventory.setItem(pageinfoslot, itemStack);                 
            
            ItemStack back = getItem(
    				getLang("back.type"),
    				getLang("back.value"),(short)
    				guiFile.get().getInt("back.id"),
    				getLang("back.name"),
    				guiFile.get().getStringList(("back.lore")));
            inventory.setItem(backslot, back);                         
            
       }
    

    protected ItemStack getItemStack(Material type, int durability, String name, String... lore) {
        ItemStack itemStack = new ItemStack(type, 1, (short) durability);

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (name != null) {
            itemMeta.setDisplayName(color(name));
        }
        if (lore != null && lore.length != 0) {
            itemMeta.setLore(Arrays.stream(lore).map(this::color).collect(Collectors.toList()));
        }
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    protected String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public void open(Player player) {
        reRender();
        player.openInventory(getInventory());
    }


    private static class Page {
        private List<Button> buttons = new ArrayList<>();
        private int maxSize;

        Page(int maxSize) {
            this.maxSize = maxSize;
        }

        void handleClick(InventoryClickEvent event) {

            if (event.getRawSlot() > event.getInventory().getSize()) {
                return;
            }

            if (event.getSlotType() == InventoryType.SlotType.OUTSIDE) {
                return;
            }
            if (event.getSlot() >= buttons.size()) {
                return;
            }
            Button button = buttons.get(event.getSlot());
            button.onClick(event);
        }


        boolean hasSpace() {
            return buttons.size() < maxSize * 9;
        }


        boolean addButton(Button button) {
            if (!hasSpace()) {
                return false;
            }
            buttons.add(button);

            return true;
        }


        boolean removeButton(Button button) {
            return buttons.remove(button);
        }


        void render(Inventory inventory) {
            for (int i = 0; i < buttons.size(); i++) {
                Button button = buttons.get(i);

                inventory.setItem(i, button.getItemStack());
            }
        }

        boolean isEmpty() {
            return buttons.isEmpty();
        }
    }
	protected ItemStack getItem(String type,String value,Short id,String name,List<String> lore) {
		ItemStack nulls = new ItemStack(Material.BEDROCK);
		if(type.equalsIgnoreCase("customhead")) {
			ItemStack head=customHeadUtils.setcustomHead(value);
			ItemMeta headmeta = head.getItemMeta();
	            name=name.replace("%trang%", String.valueOf(getCurrentPage()));
	            name=name.replace("%trangcuoi%", String.valueOf(getPageAmount()));			
			headmeta.setDisplayName(color.add(name));
	        List<String> createLore = new ArrayList<String>();
	        for (Object lores : (List<String>) lore) {
	        	createLore.add(ChatColor.translateAlternateColorCodes('&', lores.toString()));	        	       
	        } 

		        List<String> newList = new ArrayList<String>();
		        for (String string : createLore) {
		        	string=string.replace("%trang%", String.valueOf(getCurrentPage()));
		        	string=string.replace("%trangsau%", String.valueOf(getCurrentPage()+1));
		        	string=string.replace("%trangtruoc%", String.valueOf(getCurrentPage()-1));
		        	string=string.replace("%trangcuoi%", String.valueOf(getPageAmount()));
		        newList.add(string);
		        }	        	
		        headmeta.setLore(newList);
	        head.setItemMeta(headmeta);
	        return head;	        
		}
		if(type.equalsIgnoreCase("material")) {
			ItemStack material=new ItemStack(Material.valueOf(value),1,id);
			ItemMeta mmeta = material.getItemMeta();
	            name=name.replace("%trang%", String.valueOf(getCurrentPage()));
	            name=name.replace("%trangcuoi%", String.valueOf(getPageAmount()));
			mmeta.setDisplayName(color.add(name));
	        List<String> createLore = new ArrayList<String>();
	        for (Object lores : (List<String>) lore) {
	        	createLore.add(ChatColor.translateAlternateColorCodes('&', lores.toString()));
	        }
		        List<String> newList = new ArrayList<String>();
		        for (String string : createLore) {
		        	string=string.replace("%trang%", String.valueOf(getCurrentPage()));
		        	string=string.replace("%trangsau%", String.valueOf(getCurrentPage()+1));
		        	string=string.replace("%trangtruoc%", String.valueOf(getCurrentPage()-1));
		        	string=string.replace("%trangcuoi%", String.valueOf(getPageAmount()));
		        newList.add(string);
		        }	        	
		        mmeta.setLore(newList);
	        material.setItemMeta(mmeta);
	        return material;				
		}
		
		return nulls;
	}

}