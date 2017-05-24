package pe.nasqa.values.control;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.codec.digest.DigestUtils;

import pe.nasqa.values.model.MVDinamico;

public class CVDinamico {
	public static String getDateInFormat(String format){
		Date date=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	public static Date getDateFromString(String stringDate, String format){
		SimpleDateFormat dateFormat=new SimpleDateFormat(format);
		try {
			return dateFormat.parse(stringDate);
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	public static boolean isWeekend(String fechaString){
		try {
			int year = Integer.parseInt(fechaString.substring(6, 10));
			int month = Integer.parseInt(fechaString.substring(3, 5));
			int day = Integer.parseInt(fechaString.substring(0, 2));
			Calendar cal = new GregorianCalendar(year, month - 1, day);
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			return (Calendar.SUNDAY == dayOfWeek || Calendar.SATURDAY == dayOfWeek);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public static boolean isSunDay(String fechaString){
		try {
			int year = Integer.parseInt(fechaString.substring(6, 10));
			int month = Integer.parseInt(fechaString.substring(3, 5));
			int day = Integer.parseInt(fechaString.substring(0, 2));
			Calendar cal = new GregorianCalendar(year, month - 1, day);
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			return (Calendar.SUNDAY == dayOfWeek);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public static boolean isFutureDate(String fechaString){
		try {
			Date seleccion = getDateFromString(fechaString, "dd/MM/yyyy");
			Date actual = new Date();
			return (actual.before(seleccion));
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public static boolean isDate(String dateString) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(dateString);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
	
	public static String getHashMD5(String string) {
	    try {
	    	return DigestUtils.md5Hex(string);
	    } catch (Exception ex) {
	        return "";
	    }
	}
	
	public static String getPasswordGen(int length) {
		String pswd = "";
		String NUMEROS = "0123456789";
		String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
		String key=MAYUSCULAS+MAYUSCULAS+NUMEROS;
		for (int i = 0; i < length; i++) {
			pswd+=(key.charAt((int)(Math.random() * key.length())));
		}
		return pswd;
	}
	
	public static File getPasswordFileTxt(File path, String password, String md5) {
		String text = "Password : "+password ;
		File file = new File(path, "PASSWDTXT-"+getDateInFormat("yyyyMMdd-hhmm")+".txt");
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(file);
            pw = new PrintWriter(fichero);
 
            pw.println(text);
            pw.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
		return file;
	}
	
	public static int nombresValidosCargos(List<String> files){
		int validos=0;
		for (int i = 0; i < files.size(); i++) {
			boolean req1=false;
			req1=files.get(i).length()>=20;
			
			boolean req2=false;
			if(req1){
				req2=(files.get(i).toUpperCase().indexOf(".TIF")>=16);
			}
			
			boolean req3=false;
			if(req2){
				String nombre = files.get(i);
				System.out.println((nombre.substring(nombre.lastIndexOf(".")-16, nombre.lastIndexOf("."))));
				String codigoBarra = nombre.lastIndexOf(".")>=16?(nombre.substring(nombre.lastIndexOf(".")-16, nombre.lastIndexOf("."))):"X";
				req3=MVDinamico.isNumericString(codigoBarra);
			}
			
			validos+=(req1 && req2 && req3)?1:0;
		}
		return validos;
	}	

}
