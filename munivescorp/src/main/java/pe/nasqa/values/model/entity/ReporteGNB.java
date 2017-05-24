package pe.nasqa.values.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReporteGNB implements Serializable{
	@Id
	private String id;
	@Column
	private String fecha_ingreso;
	@Column
	private String cargo;
	@Column
	private String institucion;
	@Column
	private String documento;
	@Column
	private String nro_tdc;
	@Column
	private String nombres_apellidos;
	@Column
	private String tipo_vale;
	@Column
	private String codigo_vale;
	@Column
	private String puntaje;	
	@Column
	private String fecha_actualizacion_tabla;
	@Column
	private String direccion;
	@Column
	private String distrito;
	@Column
	private String provincia;
	@Column
	private String departamento;
	@Column
	private String telefono_movil;
	@Column
	private String documento_receptor;
	@Column
	private String email;
	@Column
	private String lifemiles;
	@Column
	private String fecha_ultima;
	@Column
	private String resultado_ultimo;
	@Column
	private String certificado_1;
	@Column
	private String certificado_2;
	@Column
	private String fecha_entrega;
	@Column
	private String ubicacion_actual;
	@Column
	private String nombre_receptor;
	@Column
	private String resultado_ultima_visita;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(String fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getInstitucion() {
		return institucion;
	}
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNro_tdc() {
		return nro_tdc;
	}
	public void setNro_tdc(String nro_tdc) {
		this.nro_tdc = nro_tdc;
	}
	public String getNombres_apellidos() {
		return nombres_apellidos;
	}
	public void setNombres_apellidos(String nombres_apellidos) {
		this.nombres_apellidos = nombres_apellidos;
	}
	public String getTipo_vale() {
		return tipo_vale;
	}
	public void setTipo_vale(String tipo_vale) {
		this.tipo_vale = tipo_vale;
	}
	public String getCodigo_vale() {
		return codigo_vale;
	}
	public void setCodigo_vale(String codigo_vale) {
		this.codigo_vale = codigo_vale;
	}
	public String getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(String puntaje) {
		this.puntaje = puntaje;
	}
	public String getFecha_actualizacion_tabla() {
		return fecha_actualizacion_tabla;
	}
	public void setFecha_actualizacion_tabla(String fecha_actualizacion_tabla) {
		this.fecha_actualizacion_tabla = fecha_actualizacion_tabla;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getTelofono_movil() {
		return telefono_movil;
	}
	public void setTelofono_movil(String telefono_movil) {
		this.telefono_movil = telefono_movil;
	}
	public String getDocumento_receptor() {
		return documento_receptor;
	}
	public void setDocumento_receptor(String documento_receptor) {
		this.documento_receptor = documento_receptor;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLifemiles() {
		return lifemiles;
	}
	public void setLifemiles(String lifemiles) {
		this.lifemiles = lifemiles;
	}
	public String getFecha_ultima() {
		return fecha_ultima;
	}
	public void setFecha_ultima(String fecha_ultima) {
		this.fecha_ultima = fecha_ultima;
	}
	public String getResultado_ultimo() {
		return resultado_ultimo;
	}
	public void setResultado_ultimo(String resultado_ultimo) {
		this.resultado_ultimo = resultado_ultimo;
	}
	public String getCertificado_1() {
		return certificado_1;
	}
	public void setCertificado_1(String certificado_1) {
		this.certificado_1 = certificado_1;
	}
	public String getCertificado_2() {
		return certificado_2;
	}
	public void setCertificado_2(String certificado_2) {
		this.certificado_2 = certificado_2;
	}
	public String getFecha_entrega() {
		return fecha_entrega;
	}
	public void setFecha_entrega(String fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}
	public String getUbicacion_actual() {
		return ubicacion_actual;
	}
	public void setUbicacion_actual(String ubicacion_actual) {
		this.ubicacion_actual = ubicacion_actual;
	}
	public String getNombre_receptor() {
		return nombre_receptor;
	}
	public void setNombre_receptor(String nombre_receptor) {
		this.nombre_receptor = nombre_receptor;
	}
	public String getResultado_ultima_visita() {
		return resultado_ultima_visita;
	}
	public void setResultado_ultima_visita(String resultado_ultima_visita) {
		this.resultado_ultima_visita = resultado_ultima_visita;
	}
	
}
