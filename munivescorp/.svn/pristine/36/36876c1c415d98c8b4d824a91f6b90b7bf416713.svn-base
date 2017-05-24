package pe.nasqa.values.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MVDinamico {
	public static double numberRounder(double numero, int decimales) {
		return Math.round(numero * Math.pow(10, decimales))
				/ Math.pow(10, decimales);
	}
	
	public static List<String> stringSplitToArray(String string){
		String splitChar = ";";
		string=string.replace("\r\n", splitChar);
		string=string.replace("\n", splitChar);
		string=string.replace("\\r\\n", splitChar);
		string=string.replace(",", splitChar);
		string=string.replace(" ", splitChar);
		string=stringClearCharRecursive(string, splitChar);
		List<String> listAccept = new ArrayList<String>();
		for (String str : string.split(splitChar)) {
			if(isNumericString(str))listAccept.add(str);
		}
		return listAccept;
	}
	
	private static String stringClearCharRecursive(String string, String character){
		string = string.replace(character+character, character);
		if(string.indexOf(character+character)>-1){
			return stringClearCharRecursive(string, character);
		}else{
			return string;
		}
	}
	
	public static boolean isNumericString(String string){
		try {
			Long.parseLong(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String getDateInFormat(Date fecha, String format){
		SimpleDateFormat dateFormat=new SimpleDateFormat(format);
		return dateFormat.format(fecha);
	}
}
