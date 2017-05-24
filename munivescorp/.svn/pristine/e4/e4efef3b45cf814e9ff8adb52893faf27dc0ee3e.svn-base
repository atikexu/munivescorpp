package pe.nasqa.values.model;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.nasqa.values.dao.CargaImgDao;
import pe.nasqa.values.dao.ConfigStorageDao;
import pe.nasqa.values.model.entity.ConfigStorage;

@Service
public class CargaImgModel {
	
	@Autowired
	private ConfigStorageDao configStorageDao;
	
	@Autowired
	private CargaImgDao cargaImgDao;
	
	private Logger log = Logger.getLogger(CargaImgModel.class);
	
	@Transactional
	public String cargoPublicar(String pathFrom, List<String> unZipFiles) {
		System.out.println("pathFrom:"+pathFrom);
		String pathRoot=getPathRootStorage("CARGO");
		String logCarga="";
		int cant=0;
		int nulo=0;
		for (int i = 0; i < unZipFiles.size(); i++) {
			File fileFrom=new File(pathFrom,unZipFiles.get(i));
			System.out.println("unZipFiles.get(i):"+unZipFiles.get(i));
			String codBar=getCodBarFromFileName(unZipFiles.get(i));
			System.out.println("codBar:"+codBar);
			if(codBar!=null){
				File dirPathTo=new File(pathRoot,getOpFromCodBar(codBar));
				
				if(!dirPathTo.exists())dirPathTo.mkdirs();
				
				String imgCarNom=codBar+fileFrom.getName().substring(fileFrom.getName().lastIndexOf("."));
				System.out.println("imgCarNom:"+imgCarNom);
				File fileTo=new File(dirPathTo,imgCarNom);
				if(fileFrom.exists() && dirPathTo.exists()){
					try {
						FileUtils.copyFile(fileFrom, fileTo);
					} catch (IOException e) {
						log.error("No es posible copiar: "+fileFrom.getAbsolutePath()+" al destino: "+fileTo.getAbsolutePath());
					}
					
					if(fileTo.exists()){
						fileFrom.delete();
						if(fileTo.exists() && cargaImgDao.updateCargo(codBar, imgCarNom)){
							cant++;
						}else{
							fileTo.delete();
							nulo++;
							logCarga+=fileFrom.getName()+"\n";
							log.info("Imagen no fue cargado a falta de data: "+fileTo.getAbsolutePath());
						}
					}else{
						log.error("No fue movido el archivo, de: "+fileFrom.getAbsolutePath()+" al destino: "+fileTo.getAbsolutePath());
					}
				}else{
					log.error("No existe: Archivo de origen y carpeta de destino.");
				}
			}else{
				log.error("Codigo de barras invalido: "+unZipFiles.get(i));
			}
		}
		
		logCarga=(cant+" Imagenes publicadas.\n\n"+(nulo>0?nulo+" Erradas sin data: \n"+logCarga:""));
		
		return logCarga;
	}
	
	@Transactional
	public String cargoPublicarUno(String pathFrom, String imgFileName,String codBarImg) {
		System.out.println("CARGO PUBLICAR UNO");
		System.out.println("pathFrom:"+pathFrom);
		String pathRoot=getPathRootStorage("CARGO");
		System.out.println("imgFileName:"+imgFileName);
		String logCarga="";
		int cant=0;
		int nulo=0;
		String codBar=getCodBarFromFileName(imgFileName);
		if(codBar!=null){
			File fileFrom=new File(pathFrom,imgFileName);
			System.out.println("fileFrom:"+fileFrom.getPath());

			File dirPathTo=new File(pathRoot,getOpFromCodBar(codBar));
			System.out.println("dirPathTo.getPath():"+dirPathTo.getPath());
			if(!dirPathTo.exists())dirPathTo.mkdirs();	
			
			String imgCarNom=codBar+imgFileName.substring(imgFileName.lastIndexOf("."));
			File fileTo=new File(dirPathTo,imgFileName);
			if(fileFrom.exists() && dirPathTo.exists()){
				if(fileTo.exists()){					
					System.out.println("EXISTE-fileTo;"+fileTo.getAbsolutePath()+"-"+imgCarNom+"-"+imgFileName.substring(imgFileName.lastIndexOf(".")));
				}
								
				try {
					FileUtils.copyFile(fileFrom, fileTo);
				} catch (IOException e) {
					log.error("No es posible copiar: "+fileFrom.getAbsolutePath()+" al destino: "+fileTo.getAbsolutePath());
				}
				
				if(fileTo.exists()){
					fileFrom.delete();
					if(fileTo.exists() && cargaImgDao.updateCargo(codBar, imgCarNom)){
						cant++;
					}else{
						fileTo.delete();
						nulo++;
						logCarga+=fileFrom.getName();
						log.info("Imagen no fue cargado a falta de data: "+fileTo.getAbsolutePath());
					}
				}else{
					log.error("No fue movido el archivo, de: "+fileFrom.getAbsolutePath()+" al destino: "+fileTo.getAbsolutePath());
				}
				
			}else{
				log.error("No existe: Archivo de origen y carpeta de destino.");
			}
		}else{
			log.error("Codigo de barras invalido: "+imgFileName);
		}
		
		if(cant==0 && nulo==0){
			logCarga="ERROR: No publicado por error interno";
		}else if(cant>0 && nulo==0){
			logCarga="OK: Imagen publicado correctamente";
		}else{
			logCarga="ERROR: No publicado porque no existe data relacionado a "+imgFileName;
		}
		
		return logCarga;
	}
	
	@Transactional
	public String cargoPublicarUno(String pathFrom, String imgFileName) {
		System.out.println("CARGO PUBLICAR UNO");
		System.out.println("pathFrom:"+pathFrom);
		String pathRoot=getPathRootStorage("CARGO");
		System.out.println("imgFileName:"+imgFileName);
		String logCarga="";
		int cant=0;
		int nulo=0;
		String codBar=getCodBarFromFileName(imgFileName);
		if(codBar!=null){
			File fileFrom=new File(pathFrom,imgFileName);
			System.out.println("fileFrom:"+fileFrom.getPath());

			File dirPathTo=new File(pathRoot,getOpFromCodBar(codBar));
			System.out.println("dirPathTo.getPath():"+dirPathTo.getPath());
			if(!dirPathTo.exists())dirPathTo.mkdirs();	
			
			String imgCarNom=codBar+imgFileName.substring(imgFileName.lastIndexOf("."));
			File fileTo=new File(dirPathTo,imgFileName);
			if(fileFrom.exists() && dirPathTo.exists()){
				if(fileTo.exists()){					
					System.out.println("EXISTE-fileTo;"+fileTo.getAbsolutePath()+"-"+imgCarNom+"-"+imgFileName.substring(imgFileName.lastIndexOf(".")));
				}
								
				try {
					FileUtils.copyFile(fileFrom, fileTo);
				} catch (IOException e) {
					log.error("No es posible copiar: "+fileFrom.getAbsolutePath()+" al destino: "+fileTo.getAbsolutePath());
				}
				
				if(fileTo.exists()){
					fileFrom.delete();
					if(fileTo.exists() && cargaImgDao.updateCargo(codBar, imgCarNom)){
						cant++;
					}else{
						fileTo.delete();
						nulo++;
						logCarga+=fileFrom.getName();
						log.info("Imagen no fue cargado a falta de data: "+fileTo.getAbsolutePath());
					}
				}else{
					log.error("No fue movido el archivo, de: "+fileFrom.getAbsolutePath()+" al destino: "+fileTo.getAbsolutePath());
				}
				
			}else{
				log.error("No existe: Archivo de origen y carpeta de destino.");
			}
		}else{
			log.error("Codigo de barras invalido: "+imgFileName);
		}
		
		if(cant==0 && nulo==0){
			logCarga="ERROR: No publicado por error interno";
		}else if(cant>0 && nulo==0){
			logCarga="OK: Imagen publicado correctamente";
		}else{
			logCarga="ERROR: No publicado porque no existe data relacionado a "+imgFileName;
		}
		
		return logCarga;
	}
		
	@Transactional(readOnly=true)
	public String cargoBuscarPath(String fileName){
		String pathRoot=getPathRootStorage("CARGO");
		System.out.println("pathRoot:"+pathRoot+File.separatorChar+getOpFromCodBar(getCodBarFromFileName(fileName)));
		File file = new File(pathRoot+File.separatorChar+getOpFromCodBar(getCodBarFromFileName(fileName)), fileName);
		System.out.println("file.getName():"+file.getAbsolutePath());		
		if(file.exists() && file.isFile()){
			return file.getAbsolutePath();
		}else{
			return null;
		}
	}	
	
	@Transactional(readOnly=true)
	public String cargoBuscarPathBase(String fileName){
		String pathRoot=getPathRootStorage("CARGO");
		System.out.println("pathRootBase:"+pathRoot+File.separatorChar+getOpFromCodBar(getCodBarFromFileName(fileName)));
		File file = new File(pathRoot+File.separatorChar+getOpFromCodBar(getCodBarFromFileName(fileName)));
		if(file.exists() && file.isDirectory()){
			return file.getAbsolutePath();
		}else{
			return null;
		}
	}
	public String getCodBarFromFileName(String fileName){
		//System.out.println("getCodBarFromFileName");
		String codBar=fileName;
		String [] campos ;
		try {       
			campos = fileName.split("\\.");
			codBar = campos[0];
			//System.out.println("getCodBarFromFileName:"+codBar);
			//codBar=codBar.substring(0,codBar.lastIndexOf("."));
			//codBar=codBar.substring(0,16);
		} catch (Exception e) {
			//e.printStackTrace();
			// TODO: handle exception
			codBar=null;
		}
		return codBar;
	}
	
	private String getOpFromCodBar(String codBar){
		String pathOP=codBar;
		try {
			pathOP=pathOP.substring(0, MVConstante.ANCHO_NRO_OP_CODIGOBARRAS);
		} catch (Exception e) {
			// TODO: handle exception
			pathOP="ERROR";
		}
		return pathOP;
	}
	
	private String getPathRootStorage(String tipo){
		String path=null;
		List<ConfigStorage> listStorage=configStorageDao.findAll();
		for (int i = 0; i < listStorage.size(); i++) {
			if(tipo.equalsIgnoreCase("CARGO"))path=listStorage.get(i).getRootPathCargo();
			if(tipo.equalsIgnoreCase("DOCUMENTO"))path=listStorage.get(i).getRootPathDocumento();
			if(path!=null)break;
		}
		
		return path;
	}
}
