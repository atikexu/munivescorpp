/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.nasqa.values.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 
 */
@Entity
@Table(name = "rendicion", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Rendicion.findAll", query = "SELECT m FROM Rendicion m"),
    @NamedQuery(name = "Rendicion.findById", query = "SELECT m FROM Rendicion m WHERE m.id = :id"),
    @NamedQuery(name = "Rendicion.findByFecha", query = "SELECT m FROM Rendicion m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Rendicion.findByFechaProceso", query = "SELECT m FROM Rendicion m WHERE m.fecha_proceso = :fecha_proceso")
})
public class Rendicion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "fecha_proceso", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha_proceso;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "piezas")
    private Integer piezas;
    @Column(name = "id_cliente", length = 20)
    private String id_cliente;
    @Column(name = "destino", length = 250)
    private String destino;
    @Column(name = "situacion", length = 20)
    private String situacion;
    @Column(name = "motivo", length = 50)
    private String motivo;
    @Column(name = "documento", length = 50)
    private String documento;
    @Column(name = "solicitado", length = 250)
    private String solicitado;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "recibido", length = 250)
    private String recibido;
    @Column(name = "usuario", length = 250)
    private String usuario;
    @Column(name = "fecha_registro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha_registro;
    @Column(name = "cod_agencia", length = 20)
    private String cod_agencia;
    @Column(name = "cod_bar_rendicion", length = 30)
    private String cod_bar_rendicion;
    @Column(name = "nro_rendicion")
    private Integer nro_rendicion;
    @Column(name = "cod_situacion", length = 2)
    private String cod_situacion;
    @Column(name = "cod_motivo", length = 2)
    private String cod_motivo;
    @Column(name = "est_carga")
    private Integer est_carga;

    public Rendicion() {
    }

    public Rendicion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Date getFechaProceso() {
        return fecha_proceso;
    }

    public void setFechaProceso(Date fecha_proceso) {
        this.fecha_proceso = fecha_proceso;
    }

    public Integer getIdNumero() {
        return numero;
    }

    public void setIdNumero(Integer numero) {
        this.numero = numero;
    }
    
    public Integer getIPiezas() {
        return piezas;
    }
    
    public void setPieza(Integer piezas) {
        this.piezas = piezas;
    }
    
    public String getIdCliente() {
        return id_cliente;
    }

    public void setIdCliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
       
    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    public String getSolicitado() {
        return solicitado;
    }

    public void setSolicitado(String solicitado) {
        this.solicitado = solicitado;
    }
    
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    
    public String getRecibido() {
        return recibido;
    }

    public void setRecibido(String recibido) {
        this.recibido = recibido;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public Date getFechaRegistro() {
        return fecha_registro;
    }

    public void setFechaRegistro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    
    public String getCodAgencia() {
        return cod_agencia;
    }

    public void setCodAgencia(String cod_agencia) {
        this.cod_agencia = cod_agencia;
    }
    
    public String getCodBarRendicion() {
        return cod_bar_rendicion;
    }

    public void setCodBarRendicion(String cod_bar_rendicion) {
        this.cod_bar_rendicion = cod_bar_rendicion;
    }
    
    public Integer getNroRendicion() {
        return nro_rendicion;
    }

    public void setNroRendicion(Integer nro_rendicion) {
        this.nro_rendicion = nro_rendicion;
    }
    
    public String getCodSituacion() {
        return cod_situacion;
    }

    public void setCodSituacion(String cod_situacion) {
        this.cod_situacion = cod_situacion;
    }
    
    public String getCodMotivo() {
        return cod_motivo;
    }

    public void setCodMotivo(String cod_motivo) {
        this.cod_motivo = cod_motivo;
    }
    
    public Integer getEstCarga() {
        return est_carga;
    }

    public void setEstCarga(Integer est_carga) {
        this.est_carga = est_carga;
    }

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rendicion)) {
            return false;
        }
        Rendicion other = (Rendicion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.Mensajero[ id=" + id + " ]";
    }
    
}
