package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SysTime {
	public static String GetCurDate(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
		return date.format(calendar.getTime()).toString().substring(6, 10)+"-"+
		date.format(calendar.getTime()).toString().substring(3, 5)+"-"+
	    date.format(calendar.getTime()).toString().substring(0, 2);
	}

}
