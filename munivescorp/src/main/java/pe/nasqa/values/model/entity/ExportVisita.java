package pe.nasqa.values.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExportVisita implements Serializable{
	@Id
	private String id_visita;
	@Column
	private String fecha_hoja;
	@Column
	private String codigo_mensajero;
	@Column
	private String codigo_barras;
	@Column
	private String codigo_motivo;
	@Column
	private String tipo_direccion;
	@Column
	private String hora_visita;
	@Column
	private String persona_recibida;
	@Column
	private String codigo_vinculo;
	@Column
	private String fec_creacion;
	
	public String getId_visita() {
		return id_visita;
	}
	public void setId_visita(String id_visita) {
		this.id_visita = id_visita;
	}
	public String getFecha_hoja() {
		return fecha_hoja;
	}
	public void setFecha_hoja(String fecha_hoja) {
		this.fecha_hoja = fecha_hoja;
	}
	public String getCodigo_mensajero() {
		return codigo_mensajero;
	}
	public void setCodigo_mensajero(String codigo_mensajero) {
		this.codigo_mensajero = codigo_mensajero;
	}
	public String getCodigo_barras() {
		return codigo_barras;
	}
	public void setCodigo_barras(String codigo_barras) {
		this.codigo_barras = codigo_barras;
	}
	public String getCodigo_motivo() {
		return codigo_motivo;
	}
	public void setCodigo_motivo(String codigo_motivo) {
		this.codigo_motivo = codigo_motivo;
	}
	public String getTipo_direccion() {
		return tipo_direccion;
	}
	public void setTipo_direccion(String tipo_direccion) {
		this.tipo_direccion = tipo_direccion;
	}
	public String getHora_visita() {
		return hora_visita;
	}
	public void setHora_visita(String hora_visita) {
		this.hora_visita = hora_visita;
	}
	public String getPersona_recibida() {
		return persona_recibida;
	}
	public void setPersona_recibida(String persona_recibida) {
		this.persona_recibida = persona_recibida;
	}
	public String getCodigo_vinculo() {
		return codigo_vinculo;
	}
	public void setCodigo_vinculo(String codigo_vinculo) {
		this.codigo_vinculo = codigo_vinculo;
	}
	public String getFec_creacion() {
		return fec_creacion;
	}
	public void setFec_creacion(String fec_creacion) {
		this.fec_creacion = fec_creacion;
	}	
	
	
}
