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
@Table(name = "log_cargadata", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "LogCargaData.findAll", query = "SELECT m FROM LogCargaData m"),
    @NamedQuery(name = "LogCargaData.findById", query = "SELECT m FROM LogCargaData m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "LogCargaData.findByCodigo", query = "SELECT m FROM LogCargaData m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "LogCargaData.findByNombres", query = "SELECT m FROM LogCargaData m WHERE m.usuario = :usuario")})

public class LogCargaData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    
    @Column(name = "nombre", length = 255)
    private String nombre;
    
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    
    @Column(name = "usuario", length = 255)
    private String usuario;


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
   /* 
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id_ruta != null ? id_ruta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogCargaData)) {
            return false;
        }
        LogCargaData other = (LogCargaData) object;
        if ((this.id_ruta == null && other.id_ruta != null) || (this.id_ruta != null && !this.id_ruta.equals(other.id_ruta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.Mensajero[ id=" + id_ruta + " ]";
    }
    */
}
