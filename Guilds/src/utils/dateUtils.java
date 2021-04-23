package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.Cortez.guilds.Core;

public class dateUtils {
	
	public static String main(Integer dateint) {
		
		Integer di = dateint;
		Date date = new Date(((long)di)*1000L);
		SimpleDateFormat formatter = new SimpleDateFormat(Core.getInstance().getConfig().getString("dateFormat"));
		String dateString = formatter.format(date);			
		
		return dateString;
	}
	
}
