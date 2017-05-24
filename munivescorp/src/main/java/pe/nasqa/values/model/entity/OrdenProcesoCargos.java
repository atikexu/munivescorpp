package pe.nasqa.values.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrdenProcesoCargos implements Serializable{
	@Id
	@Column
	private String codigo;
	@Column
	private String gestion;
	@Id
	@Column
	private String cargos;
	@Column
	private Integer cantidad;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getGestion() {
		return gestion;
	}
	public void setGestion(String gestion) {
		this.gestion = gestion;
	}
	public String getCargos() {
		return cargos;
	}
	public void setCargos(String cargos) {
		this.cargos = cargos;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}
