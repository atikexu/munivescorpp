package pe.dataimagenes.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil { 

	private static SimpleDateFormat simpleDateFormat;
	
	/**
	 * Fecha del dia de hoy en formato ISO yyyymmdd
	 * */
	public static String fechaHoyISO(){
		simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		Date date = Calendar.getInstance().getTime();
		return simpleDateFormat.format(date);
	}
	
	public static String getDateFormat(String format){
		Date date = new Date(); 
		SimpleDateFormat dt1 = new SimpleDateFormat(format);
		return dt1.format(date);
	}
	
	public static String getDateFormatToString(Date date, String format){
		SimpleDateFormat dt1 = new SimpleDateFormat(format);
		return dt1.format(date);
	}
}
