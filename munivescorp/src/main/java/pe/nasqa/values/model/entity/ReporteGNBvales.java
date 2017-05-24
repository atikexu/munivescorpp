package pe.nasqa.values.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReporteGNBvales implements Serializable{
	
	 @Id
 	private String vid;
	
	 @Column		
	 private String	cfecha_ingreso	;
	 
	 @Column		
	 private String	ccargo	;
	 
	 
	 @Column		
	 private String	cinstitucion	;
	 @Column		
	 private String	cdocumento	;
	 @Column		
	 private String	cnro_tdc	;
	 @Column		
	 private String	capell_nom_cliente	;
	 
	 @Column		
	 private String	ccertificadoo	;
	 
	 @Column		
	 private String	ctipo_vale	;
	 @Column		
	 private String	ccod_vale	;
	 @Column		
	 private String	cpuntaje	;
	 @Column		
	 private String	cfecha_entrega	;
	 @Column		
	 private String	cfecha_actualizacion_tabla	;
	 @Column		
	 private String	ccertificado	;
	 @Column		
	 private String	cdireccion	;
	 @Column		
	 private String	cdistrito	;
	 @Column		
	 private String	cprovincia 	;
	 @Column		
	 private String	cdepartamento	;
	 @Column		
	 private String	cmovil	;
	 @Column		
	 private String	cdocumento_receptor	;
	 @Column		
	 private String	cnombre_receptor	;
	 @Column		
	 private String	cemail	;
	 @Column		
	 private String	clifemiles	;
	 
	 @Column		
	 private String	cubicacion_actual	;
	 @Column		
	 private String	cfecha_ultima	;
	 @Column		
	 private String	cresultado_ultimo	;
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getCfecha_ingreso() {
		return cfecha_ingreso;
	}
	public void setCfecha_ingreso(String cfecha_ingreso) {
		this.cfecha_ingreso = cfecha_ingreso;
	}
	public String getCcargo() {
		return ccargo;
	}
	public void setCcargo(String ccargo) {
		this.ccargo = ccargo;
	}
	public String getCinstitucion() {
		return cinstitucion;
	}
	public void setCinstitucion(String cinstitucion) {
		this.cinstitucion = cinstitucion;
	}
	public String getCdocumento() {
		return cdocumento;
	}
	public void setCdocumento(String cdocumento) {
		this.cdocumento = cdocumento;
	}
	public String getCnro_tdc() {
		return cnro_tdc;
	}
	public void setCnro_tdc(String cnro_tdc) {
		this.cnro_tdc = cnro_tdc;
	}
	public String getCapell_nom_cliente() {
		return capell_nom_cliente;
	}
	public void setCapell_nom_cliente(String capell_nom_cliente) {
		this.capell_nom_cliente = capell_nom_cliente;
	}
	public String getCcertificadoo() {
		return ccertificadoo;
	}
	public void setCcertificadoo(String ccertificadoo) {
		this.ccertificadoo = ccertificadoo;
	}
	public String getCtipo_vale() {
		return ctipo_vale;
	}
	public void setCtipo_vale(String ctipo_vale) {
		this.ctipo_vale = ctipo_vale;
	}
	public String getCcod_vale() {
		return ccod_vale;
	}
	public void setCcod_vale(String ccod_vale) {
		this.ccod_vale = ccod_vale;
	}
	public String getCpuntaje() {
		return cpuntaje;
	}
	public void setCpuntaje(String cpuntaje) {
		this.cpuntaje = cpuntaje;
	}
	public String getCfecha_entrega() {
		return cfecha_entrega;
	}
	public void setCfecha_entrega(String cfecha_entrega) {
		this.cfecha_entrega = cfecha_entrega;
	}
	public String getCfecha_actualizacion_tabla() {
		return cfecha_actualizacion_tabla;
	}
	public void setCfecha_actualizacion_tabla(String cfecha_actualizacion_tabla) {
		this.cfecha_actualizacion_tabla = cfecha_actualizacion_tabla;
	}
	public String getCcertificado() {
		return ccertificado;
	}
	public void setCcertificado(String ccertificado) {
		this.ccertificado = ccertificado;
	}
	public String getCdireccion() {
		return cdireccion;
	}
	public void setCdireccion(String cdireccion) {
		this.cdireccion = cdireccion;
	}
	public String getCdistrito() {
		return cdistrito;
	}
	public void setCdistrito(String cdistrito) {
		this.cdistrito = cdistrito;
	}
	public String getCprovincia() {
		return cprovincia;
	}
	public void setCprovincia(String cprovincia) {
		this.cprovincia = cprovincia;
	}
	public String getCdepartamento() {
		return cdepartamento;
	}
	public void setCdepartamento(String cdepartamento) {
		this.cdepartamento = cdepartamento;
	}
	public String getCmovil() {
		return cmovil;
	}
	public void setCmovil(String cmovil) {
		this.cmovil = cmovil;
	}
	public String getCdocumento_receptor() {
		return cdocumento_receptor;
	}
	public void setCdocumento_receptor(String cdocumento_receptor) {
		this.cdocumento_receptor = cdocumento_receptor;
	}
	public String getCnombre_receptor() {
		return cnombre_receptor;
	}
	public void setCnombre_receptor(String cnombre_receptor) {
		this.cnombre_receptor = cnombre_receptor;
	}
	public String getCemail() {
		return cemail;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public String getClifemiles() {
		return clifemiles;
	}
	public void setClifemiles(String clifemiles) {
		this.clifemiles = clifemiles;
	}
	public String getCubicacion_actual() {
		return cubicacion_actual;
	}
	public void setCubicacion_actual(String cubicacion_actual) {
		this.cubicacion_actual = cubicacion_actual;
	}
	public String getCfecha_ultima() {
		return cfecha_ultima;
	}
	public void setCfecha_ultima(String cfecha_ultima) {
		this.cfecha_ultima = cfecha_ultima;
	}
	public String getCresultado_ultimo() {
		return cresultado_ultimo;
	}
	public void setCresultado_ultimo(String cresultado_ultimo) {
		this.cresultado_ultimo = cresultado_ultimo;
	}
	 
		 		

		
		
}
