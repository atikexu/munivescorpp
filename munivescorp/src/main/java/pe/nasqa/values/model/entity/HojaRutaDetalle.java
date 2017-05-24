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
@Table(name = "hoja_ruta_detalle", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "HojaRutaDetalle.findAll", query = "SELECT m FROM HojaRutaDetalle m"),
    @NamedQuery(name = "HojaRutaDetalle.findById", query = "SELECT m FROM HojaRutaDetalle m WHERE m.id = :id"),
    @NamedQuery(name = "HojaRutaDetalle.findByCodigo", query = "SELECT m FROM HojaRutaDetalle m WHERE m.cod_bar = :cod_bar"),
    @NamedQuery(name = "HojaRutaDetalle.findByRuta", query = "SELECT m FROM HojaRutaDetalle m WHERE m.id_ruta = :id_ruta"),
    @NamedQuery(name = "HojaRutaDetalle.findByFecha", query = "SELECT m FROM HojaRutaDetalle m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "HojaRutaDetalle.findByEstado", query = "SELECT m FROM HojaRutaDetalle m WHERE m.estado = :estado"),
    @NamedQuery(name = "HojaRutaDetalle.findByDireccion", query = "SELECT m FROM HojaRutaDetalle m WHERE m.direccion = :direccion"),
    @NamedQuery(name = "HojaRutaDetalle.findByOrden", query = "SELECT m FROM HojaRutaDetalle m WHERE m.orden = :orden"),
    @NamedQuery(name = "HojaRutaDetalle.findByVisitas", query = "SELECT m FROM HojaRutaDetalle m WHERE m.visitas = :visitas")})

public class HojaRutaDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cod_bar", length = 30)
    private String cod_bar;

    @Column(name = "id_ruta")
    private Integer id_ruta;
    
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "estado", length = 1)
    private String estado;
    
    @Column(name = "direccion", length = 300)
    private String direccion;
    
    @Column(name = "orden")
    private Integer orden;
    
    @Column(name = "visitas")
    private Integer visitas;
    
    @Column(name = "carga")
    private Integer carga;

    public HojaRutaDetalle() {
    }

    public HojaRutaDetalle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodBar() {
        return cod_bar;
    }

    public void setCodBar(String cod_bar) {
        this.cod_bar = cod_bar;
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
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
    
    public Integer getVisitas() {
        return visitas;
    }

    public void setVisitas(Integer visitas) {
        this.visitas = visitas;
    }
    
    public Integer getCarga() {
        return carga;
    }

    public void setCarga(Integer carga) {
        this.carga = carga;
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
        if (!(object instanceof HojaRutaDetalle)) {
            return false;
        }
        HojaRutaDetalle other = (HojaRutaDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.HojaRutaDetalle[ id=" + id + " ]";
    }
    
}
