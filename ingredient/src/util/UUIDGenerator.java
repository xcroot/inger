package util;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.UUID;
public class UUIDGenerator {
	public static String getUUID(){
		String strUid = UUID.randomUUID().toString();
		strUid = strUid.substring(0, 8);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
		String seril = date.format(calendar.getTime()).toString().substring(6, 10)+
		date.format(calendar.getTime()).toString().substring(3, 5)+
	    date.format(calendar.getTime()).toString().substring(0, 2);
		return seril + strUid.toUpperCase();
	}
}
