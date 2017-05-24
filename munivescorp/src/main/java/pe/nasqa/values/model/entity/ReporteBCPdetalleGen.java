package pe.nasqa.values.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReporteBCPdetalleGen implements Serializable{
	@Id
	private String  vid;
	 @Column
	 private String    cnroman;
	  @Column
	 private String    ccodigo_seguimiento;
	  @Column
	 private String    cresultado;
	  @Column
	 private String    cdetalle_resultado;
	  @Column
		 private String    cfecha_ultima_coor;
	  @Column
	 private String    cdestino;
	 
	  @Column
	 private String    cprov_domicilio;
	  @Column
	 private String    cprov_trabajo;
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getCnroman() {
		return cnroman;
	}
	public void setCnroman(String cnroman) {
		this.cnroman = cnroman;
	}
	public String getCcodigo_seguimiento() {
		return ccodigo_seguimiento;
	}
	public void setCcodigo_seguimiento(String ccodigo_seguimiento) {
		this.ccodigo_seguimiento = ccodigo_seguimiento;
	}
	public String getCresultado() {
		return cresultado;
	}
	public void setCresultado(String cresultado) {
		this.cresultado = cresultado;
	}
	public String getCdetalle_resultado() {
		return cdetalle_resultado;
	}
	public void setCdetalle_resultado(String cdetalle_resultado) {
		this.cdetalle_resultado = cdetalle_resultado;
	}
	public String getCfecha_ultima_coor() {
		return cfecha_ultima_coor;
	}
	public void setCfecha_ultima_coor(String cfecha_ultima_coor) {
		this.cfecha_ultima_coor = cfecha_ultima_coor;
	}
	public String getCdestino() {
		return cdestino;
	}
	public void setCdestino(String cdestino) {
		this.cdestino = cdestino;
	}
	public String getCprov_domicilio() {
		return cprov_domicilio;
	}
	public void setCprov_domicilio(String cprov_domicilio) {
		this.cprov_domicilio = cprov_domicilio;
	}
	public String getCprov_trabajo() {
		return cprov_trabajo;
	}
	public void setCprov_trabajo(String cprov_trabajo) {
		this.cprov_trabajo = cprov_trabajo;
	}
	


}
