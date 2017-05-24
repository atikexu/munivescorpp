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
@Table(name = "rendicion_detalle", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "RendicionDetalle.findAll", query = "SELECT m FROM RendicionDetalle m"),
    @NamedQuery(name = "RendicionDetalle.findById", query = "SELECT m FROM RendicionDetalle m WHERE m.id = :id"),
    @NamedQuery(name = "RendicionDetalle.findByFecha", query = "SELECT m FROM RendicionDetalle m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "RendicionDetallev.findByCargo", query = "SELECT m FROM RendicionDetalle m WHERE m.cargo = :cargo")
})
public class RendicionDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;  
    @Column(name = "id_rendicion")
    private Integer id_rendicion;
    @Column(name = "cargo", length = 20)
    private String cargo;
    @Column(name = "motivo", length = 250)
    private String motivo;
    @Column(name = "referencia", length = 20)
    private String referencia;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "cod_motivo", length = 20)
    private String cod_motivo;
    @Column(name = "desc_motivo", length = 50)
    private String desc_motivo;
    @Column(name = "cod_situacion", length = 20)
    private String cod_situacion;
    @Column(name = "desc_situacion", length = 50)
    private String desc_situacion;

    public RendicionDetalle() {
    }

    public RendicionDetalle(Integer id) {
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
    

    public Integer getIdRendicion() {
        return id_rendicion;
    }

    public void setIdRendicion(Integer id_rendicion) {
        this.id_rendicion = id_rendicion;
    }
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
       
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
    public String getCodMotivo() {
        return cod_motivo;
    }

    public void setCodMotivo(String cod_motivo) {
        this.cod_motivo = cod_motivo;
    }
    
    public String getDescMotivo() {
        return desc_motivo;
    }

    public void setDescMotivo(String desc_motivo) {
        this.desc_motivo = desc_motivo;
    }
    
    public String getCodSituacion() {
        return cod_situacion;
    }

    public void setCodSituacion(String cod_situacion) {
        this.cod_situacion = cod_situacion;
    }
    
    public String getDescSituacion() {
        return desc_situacion;
    }

    public void setDescSituacion(String desc_situacion) {
        this.desc_situacion = desc_situacion;
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
        if (!(object instanceof RendicionDetalle)) {
            return false;
        }
        RendicionDetalle other = (RendicionDetalle) object;
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
