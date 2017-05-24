package pe.nasqa.values.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReporteEnvioProv implements Serializable{
	
	 @Id
 	private String vid;
	 @Column		
	 private String	ccod_bar	;
	 @Column		
	 private String	ccod_mensajero	;
	 @Column		
	 private String	ccod_zona	;
	 @Column		
	 private String	cdes_mot	;
	 @Column		
	 private String	cdes_nom_ape	;
	 @Column		
	 private String	cdias_transcurrido	;
	 @Column		
	 private String	cdir_dom_des	;
	 @Column		
	 private String	cfec_ultima	;
	 @Column		
	 private String	cfec_ultima_visita	;
	 @Column		
	 private String	cfecha	;
	 @Column		
	 private String	cnom_cli	;
	 @Column		
	 private String	cnombres	;
	 @Column		
	 private String	cnro_hoj	;
	 @Column		
	 private String	cres_ultima_visita	;
	 @Column		
	 private String	cval_nro_ide	;
	 @Column		
	 private String	czona	;
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getCcod_bar() {
		return ccod_bar;
	}
	public void setCcod_bar(String ccod_bar) {
		this.ccod_bar = ccod_bar;
	}
	public String getCcod_mensajero() {
		return ccod_mensajero;
	}
	public void setCcod_mensajero(String ccod_mensajero) {
		this.ccod_mensajero = ccod_mensajero;
	}
	public String getCcod_zona() {
		return ccod_zona;
	}
	public void setCcod_zona(String ccod_zona) {
		this.ccod_zona = ccod_zona;
	}
	public String getCdes_mot() {
		return cdes_mot;
	}
	public void setCdes_mot(String cdes_mot) {
		this.cdes_mot = cdes_mot;
	}
	public String getCdes_nom_ape() {
		return cdes_nom_ape;
	}
	public void setCdes_nom_ape(String cdes_nom_ape) {
		this.cdes_nom_ape = cdes_nom_ape;
	}
	public String getCdias_transcurrido() {
		return cdias_transcurrido;
	}
	public void setCdias_transcurrido(String cdias_transcurrido) {
		this.cdias_transcurrido = cdias_transcurrido;
	}
	public String getCdir_dom_des() {
		return cdir_dom_des;
	}
	public void setCdir_dom_des(String cdir_dom_des) {
		this.cdir_dom_des = cdir_dom_des;
	}
	public String getCfec_ultima() {
		return cfec_ultima;
	}
	public void setCfec_ultima(String cfec_ultima) {
		this.cfec_ultima = cfec_ultima;
	}
	public String getCfec_ultima_visita() {
		return cfec_ultima_visita;
	}
	public void setCfec_ultima_visita(String cfec_ultima_visita) {
		this.cfec_ultima_visita = cfec_ultima_visita;
	}
	public String getCfecha() {
		return cfecha;
	}
	public void setCfecha(String cfecha) {
		this.cfecha = cfecha;
	}
	public String getCnom_cli() {
		return cnom_cli;
	}
	public void setCnom_cli(String cnom_cli) {
		this.cnom_cli = cnom_cli;
	}
	public String getCnombres() {
		return cnombres;
	}
	public void setCnombres(String cnombres) {
		this.cnombres = cnombres;
	}
	public String getCnro_hoj() {
		return cnro_hoj;
	}
	public void setCnro_hoj(String cnro_hoj) {
		this.cnro_hoj = cnro_hoj;
	}
	public String getCres_ultima_visita() {
		return cres_ultima_visita;
	}
	public void setCres_ultima_visita(String cres_ultima_visita) {
		this.cres_ultima_visita = cres_ultima_visita;
	}
	public String getCval_nro_ide() {
		return cval_nro_ide;
	}
	public void setCval_nro_ide(String cval_nro_ide) {
		this.cval_nro_ide = cval_nro_ide;
	}
	public String getCzona() {
		return czona;
	}
	public void setCzona(String czona) {
		this.czona = czona;
	}
   
	 
     
     
	 		 		

		
		
}
