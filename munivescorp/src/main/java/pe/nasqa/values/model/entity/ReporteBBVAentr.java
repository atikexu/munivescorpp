package pe.nasqa.values.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReporteBBVAentr implements Serializable{
	
	 @Id
 	private String vid;
	   @Column
	   private String      cval_nro_ide;
	      @Column
	   private String      ctit_nom_ape;
	      @Column
	   private String      ccodclitit;
	      @Column
	   private String      ctit_num_doc;
	      @Column
	   private String      cval_fec_emi;
	      @Column
	   private String      cfec_pro;
	      @Column
	   private String      cfech_entr_ult_ges;
	      @Column
	   private String      cnroctt;
	      @Column
	   private String      clocalidad;
	      @Column
	   private String      ccodigo_situacion;
	      @Column
	   private String      cind_act;
	      @Column
	   private String      cindval;
	      @Column
	   private String      cindtjtbco;
	      @Column
	   private String      clugarentr;
	      @Column
	   private String      cofiges;
	      @Column
	   private String      ctiptjttit;
	      @Column
	   private String      ccodref;
	      @Column
	   private String      cnrorcc;
	      @Column
	   private String      cval_tip;
	      @Column
	   private String      chora_ult;
	      @Column
	   private String      ccodresul1;
	      @Column
	   private String      ccodresul2;
	      @Column
	   private String      ctip_carpoder;
	      @Column
	   private String      cindfast;
	      @Column
	   private String      cbascodubi;
	      @Column
	   private String      caltcodubi;
	      @Column
	   private String      cnroval;
		public String getVid() {
			return vid;
		}
		public void setVid(String vid) {
			this.vid = vid;
		}
		public String getCval_nro_ide() {
			return cval_nro_ide;
		}
		public void setCval_nro_ide(String cval_nro_ide) {
			this.cval_nro_ide = cval_nro_ide;
		}
		public String getCtit_nom_ape() {
			return ctit_nom_ape;
		}
		public void setCtit_nom_ape(String ctit_nom_ape) {
			this.ctit_nom_ape = ctit_nom_ape;
		}
		public String getCcodclitit() {
			return ccodclitit;
		}
		public void setCcodclitit(String ccodclitit) {
			this.ccodclitit = ccodclitit;
		}
		public String getCtit_num_doc() {
			return ctit_num_doc;
		}
		public void setCtit_num_doc(String ctit_num_doc) {
			this.ctit_num_doc = ctit_num_doc;
		}
		public String getCval_fec_emi() {
			return cval_fec_emi;
		}
		public void setCval_fec_emi(String cval_fec_emi) {
			this.cval_fec_emi = cval_fec_emi;
		}
		public String getCfec_pro() {
			return cfec_pro;
		}
		public void setCfec_pro(String cfec_pro) {
			this.cfec_pro = cfec_pro;
		}
		public String getCfech_entr_ult_ges() {
			return cfech_entr_ult_ges;
		}
		public void setCfech_entr_ult_ges(String cfech_entr_ult_ges) {
			this.cfech_entr_ult_ges = cfech_entr_ult_ges;
		}
		public String getCnroctt() {
			return cnroctt;
		}
		public void setCnroctt(String cnroctt) {
			this.cnroctt = cnroctt;
		}
		public String getClocalidad() {
			return clocalidad;
		}
		public void setClocalidad(String clocalidad) {
			this.clocalidad = clocalidad;
		}
		public String getCcodigo_situacion() {
			return ccodigo_situacion;
		}
		public void setCcodigo_situacion(String ccodigo_situacion) {
			this.ccodigo_situacion = ccodigo_situacion;
		}
		public String getCind_act() {
			return cind_act;
		}
		public void setCind_act(String cind_act) {
			this.cind_act = cind_act;
		}
		public String getCindval() {
			return cindval;
		}
		public void setCindval(String cindval) {
			this.cindval = cindval;
		}
		public String getCindtjtbco() {
			return cindtjtbco;
		}
		public void setCindtjtbco(String cindtjtbco) {
			this.cindtjtbco = cindtjtbco;
		}
		public String getClugarentr() {
			return clugarentr;
		}
		public void setClugarentr(String clugarentr) {
			this.clugarentr = clugarentr;
		}
		public String getCofiges() {
			return cofiges;
		}
		public void setCofiges(String cofiges) {
			this.cofiges = cofiges;
		}
		public String getCtiptjttit() {
			return ctiptjttit;
		}
		public void setCtiptjttit(String ctiptjttit) {
			this.ctiptjttit = ctiptjttit;
		}
		public String getCcodref() {
			return ccodref;
		}
		public void setCcodref(String ccodref) {
			this.ccodref = ccodref;
		}
		public String getCnrorcc() {
			return cnrorcc;
		}
		public void setCnrorcc(String cnrorcc) {
			this.cnrorcc = cnrorcc;
		}
		public String getCval_tip() {
			return cval_tip;
		}
		public void setCval_tip(String cval_tip) {
			this.cval_tip = cval_tip;
		}
		public String getChora_ult() {
			return chora_ult;
		}
		public void setChora_ult(String chora_ult) {
			this.chora_ult = chora_ult;
		}
		public String getCcodresul1() {
			return ccodresul1;
		}
		public void setCcodresul1(String ccodresul1) {
			this.ccodresul1 = ccodresul1;
		}
		public String getCcodresul2() {
			return ccodresul2;
		}
		public void setCcodresul2(String ccodresul2) {
			this.ccodresul2 = ccodresul2;
		}
		public String getCtip_carpoder() {
			return ctip_carpoder;
		}
		public void setCtip_carpoder(String ctip_carpoder) {
			this.ctip_carpoder = ctip_carpoder;
		}
		public String getCindfast() {
			return cindfast;
		}
		public void setCindfast(String cindfast) {
			this.cindfast = cindfast;
		}
		public String getCbascodubi() {
			return cbascodubi;
		}
		public void setCbascodubi(String cbascodubi) {
			this.cbascodubi = cbascodubi;
		}
		public String getCaltcodubi() {
			return caltcodubi;
		}
		public void setCaltcodubi(String caltcodubi) {
			this.caltcodubi = caltcodubi;
		}
		public String getCnroval() {
			return cnroval;
		}
		public void setCnroval(String cnroval) {
			this.cnroval = cnroval;
		}

	 
   
	 
     
     
	 		 		

		
		
}
