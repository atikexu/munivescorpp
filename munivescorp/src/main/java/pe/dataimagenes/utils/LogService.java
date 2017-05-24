package pe.dataimagenes.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import pe.dataimagenes.utils.DateUtil;

public class LogService {

	public static void logEncrip(String id, String ok){
		String path = System.getProperty("user.dir");
		Date   date = new Date();
		String name = "log-"+DateUtil.getDateFormat("yyyy-MM");
		try{
			File file = new File(path,name);
			FileWriter fw = new FileWriter(file,true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(id+"\t"+ok+"\t"+DateUtil.getDateFormat("yyyy-MM-dd HH:mm:ss"));
			pw.close();
			fw.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void logSystem(String msg){
		String path = System.getProperty("user.dir");
		Date   date = new Date();
		String name = "log-error-"+DateUtil.getDateFormat("yyyy-MM");
		try{
			File file = new File(path,name);
			FileWriter fw = new FileWriter(file,true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(">"+DateUtil.getDateFormat("yyyy-MM-dd HH:mm:ss")+"\n"+msg+"\n");
			pw.close();
			fw.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
