package pe.nasqa.values.control;

public class CVConstante {
	
	public static final String USUARIO_TIPO_CLIENTE = "CL";
	public static final String USUARIO_TIPO_DATAIMG = "DI";
	public static final String USUARIO_TIPO_PUBLICO = "PU";
	
	public static String getPageTitle(String section){
		switch (section.charAt(0)) {
		case 'A':
			return "Inicio";
		case 'B':
			return "Consultas";
		case 'C':
			return "Reportes";
		case 'D':
			return "Coordinaci&oacute;n";
		case 'E':
			return "Usuarios";
		case 'F':
			return "Importar / Exportar DB";
		case 'G':
			return "Imagenes de Cargos / Documentos";
		case 'K':
			return "Admin";
		default:
			return "";
		}
	}
	
	public static final String DEFAULT_IMAGE_CARGO="sin-cargo.tif";
	
	public static final float IMAGE_QUALITY_LOW = .35f;
	public static final float IMAGE_QUALITY_MEDIUM = .55f;
	public static final float IMAGE_QUALITY_HIGH = .75f;
	
	public static final int IMAGE_SIZE_ICON = 160;
	public static final int IMAGE_SIZE_MEDIUM = 900;
	public static final int IMAGE_SIZE_FULL = 1600;
	
	public static final String IMAGE_TYPE_JPG = "jpg";
	public static final String IMAGE_TYPE_PNG = "png";
	
//	public static final String EMAIL_SENDER_USERNAME_FIX="soporte.values@dataimagenes.pe";
//	public static final String EMAIL_SENDER_PASSWORD_FIX="Joga2465";
	public static final String EMAIL_SENDER_USERNAME_FIX="alaizlia20@gmail.com";
	public static final String EMAIL_SENDER_PASSWORD_FIX="lizyalan20";
	
	public static final String COD_CLIENTE_GNB = "0007";
}
