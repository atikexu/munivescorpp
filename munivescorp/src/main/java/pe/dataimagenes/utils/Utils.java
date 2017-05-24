package pe.dataimagenes.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {
/*
	public static void writeTxtError(String metodo, String mensaje, StackTraceElement [] stackTrace){
		String rutaError = Constantes.RUTA_FOLDER_LOG_ERRORES+File.separator+DateUtil.getDateFormat("yyyyMMdd");
		File carpeta = new File(rutaError);
		if(!carpeta.exists())carpeta.mkdirs();
		
		File f = new File(rutaError+File.separator+"LOG_ERROR_"+DateUtil.fechaHoyISO());//RUTA DONDE GUARDAR EL TXT CON LA RELACION DE ARCHIVOS FALLADOS	
		try{
			FileWriter w = new FileWriter(f,true);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);
			wr.println("METODO:"+metodo+" - "+new Date());
			wr.println();
			wr.println("MENSAJE:"+mensaje);
			wr.println();
			wr.println("StackTraceElement:");
			for(StackTraceElement e : stackTrace){
				wr.println(e.toString());
			}
			wr.println();
			wr.close();
			bw.close();
			w.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	*/
	public static String getCodigoBarras(int op, int correlativo){
        String barras = String.format("%08d", op) + String.format("%07d", correlativo);
        int check = 0;
        for(int i=0;i<barras.length();++i){
               int x = barras.charAt(i)-'0';
               check+=((i%2==0)?3*x:x);
        }
        return barras+((10-check%10)%10);
	}
	
    
    public static String getFechaISO(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
	return simpleDateFormat.format(Calendar.getInstance().getTime());
    }
    
    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(ahora);
    }
    
    
    public static String getFechacadena(String dateInString) throws ParseException {

        SimpleDateFormat formater = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat formater1 = new SimpleDateFormat("dd/MM/yyyy");
       // String dateInString = "7-Jun-2013";
         Date date = formater.parse(dateInString);
          
           // System.out.println(date);
           // System.out.println(formatter.format(date));
           
            return formater1.format(date);
       
    }
    
    public static String getFechacadenaReporte(String dateInString) throws ParseException {

        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formater1 = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formater.parse(dateInString);
        return formater1.format(date);
    }
    
    
    private static final String ORIGINAL
    	= "��������������{}�]['�!#$%&/";
    private static final String REPLACEMENT
    	= "AaEeIiOoUuNnUu_____________";
    public static String stripAccents(String str) {
		if (str == null) {
		    return null;
		}
		char[] array = str.toCharArray();
		for (int index = 0; index < array.length; index++) {
		    int pos = ORIGINAL.indexOf(array[index]);
		    if (pos > -1) {
		        array[index] = REPLACEMENT.charAt(pos);
		    }
		}
		return new String(array);
		}
    
}