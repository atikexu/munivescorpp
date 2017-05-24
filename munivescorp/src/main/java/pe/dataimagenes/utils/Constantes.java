package pe.dataimagenes.utils;
import pe.nasqa.values.control.ClienteEnum;

import com.itextpdf.text.BaseColor;

public interface Constantes {
	
	String PATH_REPOSITORIO_CARGOS = "\\\\172.24.147.21\\cargos\\";
	String PATH_REPOSITORIO_CARGOS_REPLICAS = "\\\\172.24.147.84\\replica\\cargos\\";
	String PATH_REPOSITORIO_REPLICAS = "\\\\172.24.147.84\\replica\\";
	String CONSTANTE_PRUEBA = "D:\\CARGOS\\";	
	
	String PATH_PRODUCTO = PATH_REPOSITORIO_CARGOS + "CLIENTE_PRODUCTO\\";
	String PATH_EM = PATH_REPOSITORIO_CARGOS + "COURIER\\";	
	String PATH_PLANTILLA = PATH_REPOSITORIO_CARGOS + "PLANTILLAS\\";
	String PATH_RECURSOS = PATH_REPOSITORIO_CARGOS + "RECURSOS\\";
	String PATH_SALIDAS = PATH_REPOSITORIO_CARGOS + "SALIDAS\\";
	String PATH_EYH = PATH_REPOSITORIO_CARGOS + "EYH\\";
	String PATH_EYH_MIGRACION = PATH_REPOSITORIO_CARGOS + "EYH\\MIGRACION\\";
	String PATH_LOG = PATH_REPOSITORIO_CARGOS + "LOG\\";
	String PATH_LOG_REPLICAS = PATH_REPOSITORIO_REPLICAS + "LOG\\";
	
	String PATH_REPOSITORIO_CARTA_CARGO = "\\\\172.24.147.21\\CARTAS_CARGOS\\";
	
	String PATH_REPOSITORIO_DOC1 = "\\\\172.24.147.17\\Proyectos\\Dataimagenes\\CARGOS\\";
	
	String PATH_DOC1_ENTRADA = PATH_REPOSITORIO_DOC1  +  "ENTRADAS\\";
	String PATH_DOC1_SALIDA =  PATH_REPOSITORIO_DOC1  +  "SALIDAS\\";
	String PATH_DOC1_PROGRAMA =  PATH_REPOSITORIO_DOC1  +  "PROGRAMA\\";
	
	String RUTA_JASPER = "C:\\Users\\proyecto.migracion2\\Documents\\workspace-sts-3.7.2.RELEASE\\nasqa.values\\src\\main\\java\\pe\\nasqa\\values\\PdfRutas\\";
	String RUTA_PDF = "C:\\Users\\proyecto.migracion2\\Documents\\workspace-sts-3.7.2.RELEASE\\nasqa.values\\src\\main\\webapp\\pdf\\";
	
//	String POSTGRESQL_CONEC ="jdbc:postgresql://localhost:5432/nasqa_values";
//	String POSTGRESQL_USUARIO ="User_val";
//	String POSTGRESQL_PASSWORD ="V@l@2017";
		
	
	String POSTGRESQL_CONEC ="jdbc:postgresql://127.0.0.1:5432/nasqa_values";
	String POSTGRESQL_USUARIO ="postgres";
	String POSTGRESQL_PASSWORD ="1234"; 		
//	
	////////////////////////////
	String FTP_USER = "ftpuser";
    String FTP_PASSWORD = "2P&H$S%b";
    String FTP_IP = "10.100.110.12";
    int FTP_PORT = 21;
    String FTP_PATH = "Solomon/Distribucion/DI_OCR/";
    
    
    /******QA****///
//    String FTP_USER = "ftpqauser";
//    String FTP_PASSWORD = "ftpqa";
//    String FTP_IP = "10.100.110.12";
//    int FTP_PORT = 21;
//    String FTP_PATH = "/SolomonQA/Produccion/";


		
	////////////////////////
	
	String FILE_OPS_NAME = "Cargos.ops";
	String FILE_CARGOS_OPS = PATH_DOC1_PROGRAMA + FILE_OPS_NAME;
	
	String FILE_IMG_CUBRE_CUADROS =  PATH_RECURSOS  + "IMG\\" + "000.tif";
	
	///////////////////////////////////TIPO
	String CARGOS_TIPO_CARGOS = "1";
	String CARGOS_TIPO_RECUPERACION = "2";
	String CARGOS_TIPO_RECUPERACION_A4 = "3";
	String CARGOS_TIPO_REPLICA = "4";
	
	int INT_CARGOS_TIPO_CARGOS = 1;
	int INT_CARGOS_TIPO_RECUPERACION = 2;
	int INT_CARGOS_TIPO_RECUPERACION_A4 = 3;
	int INT_CARGOS_TIPO_REPLICA = 4;
	
	////////////////////////////////////////////////////////////////
	
	int ESTADO_SOLICITADO                   = 1;
	int ESTADO_GENERANDO                    = 2;
	int ESTADO_FINALIZADO                   = 3;
	int ESTADO_ERROR_AL_GENERAR             = 4;
	int ESTADO_ERROR_CON_TAYLOR = 5;
	int ESTADO_ERROR_CON_SCDIS    = 6;
	int ESTADO_ERROR_AL_OBTENER_CARGOS_DETALLE = 7;
	int ESTADO_ERROR_AL_TRAER_FECHAS_TAYLOR = 8;
	int ESTADO_ERROR_NO_EXISTE_CARPETA_CARTA_CARGO = 9;
	int ESTADO_ERROR_NO_EXISTE_MARCA_IDENTIFICACION = 10;
	int ESTADO_ERROR_DOC1 = 11;
	int ESTADO_ERROR_NO_SE_GENERAN_REPLICAS_PARA_CARTAS_CARGO = 12;
	int ESTADO_MIGRACION_DATOS_SERVIDOR_REPLICAS = 13;
	int ESTADO_ERROR_NO_SE_GENERARON_IMAGENES_REPLICAS = 14;
	
	////////////////////////////////////////////////////////////////
	
	
	String PROCESO_GENERA_CARGO = "CARGOS";
	String PROCESO_GENERA_REPLICA = "GENERACION_REPLICAS";
	
	/***********************PROFUNDIDAD***/
	int PROFUNDIDAD = 1889;
	
	/**************MEDIDAS*/
	//CARGO
	float anchoCargo = 294;//297.5f;
	float altoCargo  = 267;// 280.66666667f; // 206.7605634f;  //

	//LOGO EM
	float anchoEM = 80f;
	float altoEM  = 20f;
	//LOGO CLIENTE-PRODUCTO
	float anchoCLIPRO = 80f;
	float altoCLIPRO  = 20f;
	//CB - H
	float anchoCBH = 160f;
	float altoCBH  = 30f;
	//CB - V
	float anchoCBV = 80f;
	float altoCBV  = 20f;
	//SIZE TEXTO GENERICO
	float sizeTextGeneral = 6.5f;
	float sizeTextMini = 4;
	float sizeTextPriRei = 13;
	float sizeTextFechaGestion = 3.5f;
	//COLOR TEXTO GENERAL
	BaseColor colorTexto = BaseColor.BLACK;
	BaseColor colorLineasGuia = BaseColor.BLUE;
	BaseColor colorLineasGuia2 = BaseColor.GREEN;
	
	// CONSTANTES PARA CONVERTIR DE PDF A TIFF
	String ENCODE = "encode";
    String FORMATO_TIFF = "TIFF";
    String FOLDER_TIFFS = "archivos_tiff";
    
    String EXTENSION_TIFF = ".tiff";
    String EXTENSION_PNG = ".png";
    String EXTENSION_JPG = ".jpg";
    
    String _TIFF = "tiff";
    String _PNG = "png";
    String _JPG = "jpg";
    
    int IMAGEN_TIFF = 1;
    int IMAGEN_PNG = 2;
    int IMAGEN_JPG = 3;
    
    int FORMATO_TIPO_TIFF = 1;
    int FORMATO_TIPO_PNG = 2;
    int FORMATO_TIPO_JPG = 3;
    int FORMATO_TIPO_PDF = 4;
	
	String PATH_SALIDAS_REPLICAS = PATH_REPOSITORIO_CARGOS_REPLICAS + "SALIDAS\\";
	String PATH_REPOSITORIO_CARTA_CARGO_REPLICAS = "\\\\172.24.147.84\\CARTAS_CARGOS\\";

/////////////////////////////////////////

	//----------------  Anadir constantes
	
		/************GENERALES**************/
		String ERROR = "ERROR";
		String SUCCESS = "SUCCESS";

		String[] listaClientes = {
				ClienteEnum.GNB.toString(), 
				ClienteEnum.BCP.toString(), 
				ClienteEnum.BBVA.toString(), 
				ClienteEnum.TEBCA.toString(), 
				ClienteEnum.SERVITEBCA.toString(), 
				ClienteEnum.CENCOSUD.toString(), 
				ClienteEnum.HERBALIFE.toString(), 
				ClienteEnum.INTERBANK.toString(), 
				ClienteEnum.AFP_PROFUTURO.toString(), 
				ClienteEnum.DIGITAL_COMMERCE_SAC.toString(), 
				ClienteEnum.ORBIS_VENTURES.toString()
				};
		
		
		int ESTADO_ACTIVO = 1;
		int ESTADO_INACTIVO = 0;
		/**********************************/
		
		/************CONSTANTES RUTAS SFT*******************/
		/*
		String RUTA_FOLDER_ORIGEN_FTP = "E:\\GBRADAR";
		String RUTA_FOLDER_LOG_ERRORES = "E:\\ERROR";
		String RUTA_OUTPUT_ARCHIVO_VAL = "E:\\GBRADAR\\OUTPUT";
		String RUTA_OUTPUT_ARCHIVO_VALDAT = "E:\\GBRADAR\\OUTPUT";	
		*/
		/***************************************************/
		
		/********CONSTANTES RUTAS DESTINO LOCAL*************/
			
		/***************************************************/
		
		/**********COSNTANTES CONEXION SFTP************/	
		String SFTPHOST = "172.24.144.16";
	    //String SFTPHOSTE = "190.81.45.62";
		String SFTPHOSTE = "190.119.245.14";
	    int SFTPPORT = 22;
	    String SFTPUSER = "ingenieria";
	    String SFTPPASS = "4+TaFyqBHqfQ";
	    String PPK = "\\\\172.24.147.21\\salidas_especiales\\GBRADAR\\PPK\\ingenieria.ppk";
	    String SSH = "\\\\172.24.147.21\\salidas_especiales\\GBRADAR\\PPK\\values_ppk.ppk";


	    
//	    String PPK = "\\\\C:\\PPK\\ingenieria.ppk";
	//    String SSH = "\\\\C:\\PPK\\values_ppk.ppk";
	   // String RUTADESTINO = "\\\\172.24.147.22\\Proyectos\\SMS\\
	    String SFTPWORKINGDIR = "home/Banco_de_Credito_del_Peru/BCP_-_Tarjetas_de_Credito/";			
		/*****************************************************/
		
	
////////////////////////////////////////	


}
