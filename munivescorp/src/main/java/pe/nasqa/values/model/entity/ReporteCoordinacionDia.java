package pe.nasqa.values.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReporteCoordinacionDia implements Serializable{
	
	 @Id
 	private String vid;
	 @Column		
	 private String	ccod_bar;	
	 @Column		
	 private String	cnro_ref;	
	 @Column		
	 private String	ctit_num_doc;	
	 @Column		
	 private String	ctit_tlf_dom;	
	 @Column		
	 private String	ctit_tlf_lab;	
	 @Column		
	 private String	cfec_coo;	
	 @Column		
	 private String	chor_coo;	
	 @Column		
	 private String	cdir_coo;	
	 @Column		
	 private String	cdes_mot;	
	 @Column		
	 private String	cfec_ultima_visita;	
	 @Column		
	 private String	cres_ultima_visita;	
	 @Column		
	 private String	clugar_ultimo;	
	 @Column		
	 private String	ctipo_entrega;	
	 @Column		
	 private String	ccod_cli;	
	 @Column		
	 private String	cnom_cli;
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
	public String getCnro_ref() {
		return cnro_ref;
	}
	public void setCnro_ref(String cnro_ref) {
		this.cnro_ref = cnro_ref;
	}
	public String getCtit_num_doc() {
		return ctit_num_doc;
	}
	public void setCtit_num_doc(String ctit_num_doc) {
		this.ctit_num_doc = ctit_num_doc;
	}
	public String getCtit_tlf_dom() {
		return ctit_tlf_dom;
	}
	public void setCtit_tlf_dom(String ctit_tlf_dom) {
		this.ctit_tlf_dom = ctit_tlf_dom;
	}
	public String getCtit_tlf_lab() {
		return ctit_tlf_lab;
	}
	public void setCtit_tlf_lab(String ctit_tlf_lab) {
		this.ctit_tlf_lab = ctit_tlf_lab;
	}
	public String getCfec_coo() {
		return cfec_coo;
	}
	public void setCfec_coo(String cfec_coo) {
		this.cfec_coo = cfec_coo;
	}
	public String getChor_coo() {
		return chor_coo;
	}
	public void setChor_coo(String chor_coo) {
		this.chor_coo = chor_coo;
	}
	public String getCdir_coo() {
		return cdir_coo;
	}
	public void setCdir_coo(String cdir_coo) {
		this.cdir_coo = cdir_coo;
	}
	public String getCdes_mot() {
		return cdes_mot;
	}
	public void setCdes_mot(String cdes_mot) {
		this.cdes_mot = cdes_mot;
	}
	public String getCfec_ultima_visita() {
		return cfec_ultima_visita;
	}
	public void setCfec_ultima_visita(String cfec_ultima_visita) {
		this.cfec_ultima_visita = cfec_ultima_visita;
	}
	public String getCres_ultima_visita() {
		return cres_ultima_visita;
	}
	public void setCres_ultima_visita(String cres_ultima_visita) {
		this.cres_ultima_visita = cres_ultima_visita;
	}
	public String getClugar_ultimo() {
		return clugar_ultimo;
	}
	public void setClugar_ultimo(String clugar_ultimo) {
		this.clugar_ultimo = clugar_ultimo;
	}
	public String getCtipo_entrega() {
		return ctipo_entrega;
	}
	public void setCtipo_entrega(String ctipo_entrega) {
		this.ctipo_entrega = ctipo_entrega;
	}
	public String getCcod_cli() {
		return ccod_cli;
	}
	public void setCcod_cli(String ccod_cli) {
		this.ccod_cli = ccod_cli;
	}
	public String getCnom_cli() {
		return cnom_cli;
	}
	public void setCnom_cli(String cnom_cli) {
		this.cnom_cli = cnom_cli;
	}	

	 
	 
	   
	 
     
     
	 		 		

		
		
}
