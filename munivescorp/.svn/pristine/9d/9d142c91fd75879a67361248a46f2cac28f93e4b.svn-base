package pe.nasqa.values.control;

import pe.dataimagenes.utils.Constantes;

public enum ClienteEnum {

	// clientes
	GNB("/home/radar/preproceso/GNB", "E:/GBRADAR/VALORADOS/GNB", new String[] {"proyecto.dal@dataimagenes.pe"}),
	BCP("/home/radar/preproceso/BCP",	"E:/GBRADAR/VALORADOS/BCP",	new String[] {"proyecto.dal@dataimagenes.pe"}),
	BBVA("/home/radar/preproceso/BBVA",	"E:/GBRADAR/VALORADOS/BBVA",	new String[] {"proyecto.dal@dataimagenes.pe"}),
	TEBCA("/home/radar/preproceso/TEBCA",	"E:/GBRADAR/VALORADOS/TEBCA",	new String[] {"proyecto.dal@dataimagenes.pe"}),
	SERVITEBCA("/home/radar/preproceso/SERVITEBCA",	"E:/GBRADAR/VALORADOS/SERVITEBCA",	new String[] {"proyecto.dal@dataimagenes.pe"}),
	CENCOSUD("/home/radar/preproceso/CENCOSUD",	"E:/GBRADAR/VALORADOS/CENCOSUD",	new String[] {"proyecto.dal@dataimagenes.pe"}),
	HERBALIFE("/home/radar/preproceso/HERBALIFE",	"E:/GBRADAR/VALORADOS/HERBALIFE",	new String[] {"proyecto.dal@dataimagenes.pe"}),
	INTERBANK("/home/radar/preproceso/INTERBANK",	"E:/GBRADAR/VALORADOS/INTERBANK",	new String[] {"proyecto.dal@dataimagenes.pe"}),
	AFP_PROFUTURO("/home/radar/preproceso/PROFUTURO",	"E:/GBRADAR/VALORADOS/PROFUTURO",	new String[] {"proyecto.dal@dataimagenes.pe"}),
	DIGITAL_COMMERCE_SAC("/home/radar/preproceso/DIGITAL_COMMERCE",	"E:/GBRADAR/VALORADOS/DIGITAL_COMMERCE",	new String[] {"proyecto.dal@dataimagenes.pe"}),
	ORBIS_VENTURES("/home/radar/preproceso/ORBIS",	"E:/GBRADAR/VALORADOS/ORBIS",	new String[] {"proyecto.dal@dataimagenes.pe"});
    public String[] listaClientes(){
		return new String[] {"GNB", "BCP", "BBVA","TEBCA", "SERVITEBCA", "CENCOSUD","HERBALIFE", "INTERBANK", "AFP_PROFUTURO","DIGITAL_COMMERCE_SAC", "ORBIS_VENTURES"};
	}
    
    // variable
    private String rutaFTP;
    private String rutaServer;
    private String[] correoPara;
    
	private ClienteEnum(String rutaFTP, String rutaServer, String[] correoPara) {
		this.rutaFTP = rutaFTP;
		this.rutaServer = rutaServer;
		this.correoPara = correoPara;
	}

	public String getRutaFTP() {
		return rutaFTP;
	}

	public String getRutaServer() {
		return rutaServer;
	}

	public String[] getCorreoPara() {
		return correoPara;
	}	
	
}
