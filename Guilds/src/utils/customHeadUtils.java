package utils;

import java.lang.reflect.Field;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class customHeadUtils {

	public static ItemStack setcustomHead(String customHead) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM,1,(byte) SkullType.PLAYER.ordinal());
        SkullMeta itemmeta = (SkullMeta) item.getItemMeta();
       
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", 
        		customHead));
        Field field;
        try {
            field = itemmeta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(itemmeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException x) {
            x.printStackTrace();
        }
       
        item.setItemMeta(itemmeta);
        return item;
	}
	
	
}
