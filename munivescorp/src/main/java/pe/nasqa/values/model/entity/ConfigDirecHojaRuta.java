/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.nasqa.values.model.entity;

import java.io.Serializable;
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

/**
 *
 * @author 
 */
@Entity
@Table(name = "config_direc_hoja_ruta", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "ConfigDirecHojaRuta.findAll", query = "SELECT m FROM ConfigDirecHojaRuta m"),
    @NamedQuery(name = "ConfigDirecHojaRuta.findById", query = "SELECT m FROM ConfigDirecHojaRuta m WHERE m.id_config = :id_config"),
    @NamedQuery(name = "ConfigDirecHojaRuta.findByCodigo", query = "SELECT m FROM ConfigDirecHojaRuta m WHERE m.cod_cliente = :cod_cliente"),
    @NamedQuery(name = "ConfigDirecHojaRuta.findByNombres", query = "SELECT m FROM ConfigDirecHojaRuta m WHERE m.cod_producto = :cod_producto"),
    @NamedQuery(name = "ConfigDirecHojaRuta.findByApellidoPat", query = "SELECT m FROM ConfigDirecHojaRuta m WHERE m.direccion = :direccion"),
    @NamedQuery(name = "ConfigDirecHojaRuta.findByApellidoMat", query = "SELECT m FROM ConfigDirecHojaRuta m WHERE m.orden = :orden"),
    @NamedQuery(name = "ConfigDirecHojaRuta.findByDni", query = "SELECT m FROM ConfigDirecHojaRuta m WHERE m.estado = :estado")})
public class ConfigDirecHojaRuta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_config", nullable = false)
    private Integer id_config;

    @Column(name = "cod_cliente", length = 20)
    private String cod_cliente;
    @Column(name = "cod_producto", length = 2)
    private String cod_producto;
    @Column(name = "direccion", length = 250)
    private String direccion;
    @Column(name = "orden")
    private Integer orden;
    @Column(name = "estado")
    private Integer estado;

    public ConfigDirecHojaRuta() {
    }

    public ConfigDirecHojaRuta(Integer id_config) {
        this.id_config = id_config;
    }

    public Integer getIdConfig() {
        return id_config;
    }

    public void setIdConfig(Integer id_config) {
        this.id_config = id_config;
    }

    public String getCodCliente() {
        return cod_cliente;
    }

    public void setCodCliente(String cod_cliente) {
        this.cod_cliente = cod_cliente;
    }
    
    public String getCodProducto() {
        return cod_producto;
    }

    public void setCodProducto(String cod_producto) {
        this.cod_producto = cod_producto;
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
    
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id_config != null ? id_config.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfigDirecHojaRuta)) {
            return false;
        }
        ConfigDirecHojaRuta other = (ConfigDirecHojaRuta) object;
        if ((this.id_config == null && other.id_config != null) || (this.id_config != null && !this.id_config.equals(other.id_config))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.ConfigDirecHojaRuta[ id=" + id_config + " ]";
    }
    
}
