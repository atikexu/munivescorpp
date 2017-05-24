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
@Table(name = "hoja_ruta", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "HojaRuta.findAll", query = "SELECT m FROM HojaRuta m"),
    @NamedQuery(name = "HojaRuta.findById", query = "SELECT m FROM HojaRuta m WHERE m.id_ruta = :id_ruta"),
    @NamedQuery(name = "HojaRuta.findByCodigo", query = "SELECT m FROM HojaRuta m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "HojaRuta.findByNombres", query = "SELECT m FROM HojaRuta m WHERE m.fecha_proceso = :fecha_proceso"),
    @NamedQuery(name = "HojaRuta.findByApellidoPat", query = "SELECT m FROM HojaRuta m WHERE m.id_mensajero = :id_mensajero"),
    @NamedQuery(name = "HojaRuta.findByApellidoMat", query = "SELECT m FROM HojaRuta m WHERE m.ruta = :ruta"),
    @NamedQuery(name = "HojaRuta.findByDni", query = "SELECT m FROM HojaRuta m WHERE m.piezas = :piezas"),
    @NamedQuery(name = "HojaRuta.findByCorreo", query = "SELECT m FROM HojaRuta m WHERE m.situacion = :situacion"),
    @NamedQuery(name = "HojaRuta.findByTelefono", query = "SELECT m FROM HojaRuta m WHERE m.zona = :zona"),
    @NamedQuery(name = "HojaRuta.findByEstado", query = "SELECT m FROM HojaRuta m WHERE m.estado = :estado")})
public class HojaRuta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_ruta", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_ruta;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "fecha_proceso", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha_proceso;
    @Column(name = "id_mensajero")
    private Integer id_mensajero;
    @Column(name = "ruta", length = 10)
    private String ruta;
    @Column(name = "piezas")
    private Integer piezas;
    @Column(name = "situacion", length = 20)
    private String situacion;
    @Column(name = "zona", length = 20)
    private String zona;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "cod_bar_ruta", length = 30)
    private String cod_bar_ruta;
    @Column(name = "nro_hoja")
    private Integer nro_hoja;
    @Column(name = "usuario", length = 100)
    private String usuario;
    @Column(name = "est_carga")
    private Integer est_carga;

    public HojaRuta() {
    }

    public HojaRuta(Integer id_ruta) {
        this.id_ruta = id_ruta;
    }

    public Integer getIdRuta() {
        return id_ruta;
    }

    public void setIdRuta(Integer id_ruta) {
        this.id_ruta = id_ruta;
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

    public Integer getIdMensajero() {
        return id_mensajero;
    }

    public void setIdMensajero(Integer id_mensajero) {
        this.id_mensajero = id_mensajero;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    public Integer getPiezas() {
        return piezas;
    }

    public void setPiezas(Integer piezas) {
        this.piezas = piezas;
    }
    
    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    
    public String getCodBarRuta() {
        return cod_bar_ruta;
    }

    public void setCodBarRuta(String cod_bar_ruta) {
        this.cod_bar_ruta = cod_bar_ruta;
    }
    
    public Integer getNroHoja() {
        return nro_hoja;
    }

    public void setNroHoja(Integer nro_hoja) {
        this.nro_hoja = nro_hoja;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
        hash += (id_ruta != null ? id_ruta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HojaRuta)) {
            return false;
        }
        HojaRuta other = (HojaRuta) object;
        if ((this.id_ruta == null && other.id_ruta != null) || (this.id_ruta != null && !this.id_ruta.equals(other.id_ruta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.Mensajero[ id=" + id_ruta + " ]";
    }
    
}
