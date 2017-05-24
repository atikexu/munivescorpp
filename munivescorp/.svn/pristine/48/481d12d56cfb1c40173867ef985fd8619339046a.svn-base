/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.nasqa.values.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Edwin
 */
@Entity
@Table(name = "valorado_tipo", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "ValoradoTipo.findAll", query = "SELECT v FROM ValoradoTipo v"),
    @NamedQuery(name = "ValoradoTipo.findByCodTipo", query = "SELECT v FROM ValoradoTipo v WHERE v.codTipo = :codTipo"),
    @NamedQuery(name = "ValoradoTipo.findByNombre", query = "SELECT v FROM ValoradoTipo v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "ValoradoTipo.findByDescripcion", query = "SELECT v FROM ValoradoTipo v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "ValoradoTipo.findByEstado", query = "SELECT v FROM ValoradoTipo v WHERE v.estado = :estado"),
    @NamedQuery(name = "ValoradoTipo.findByFecha", query = "SELECT v FROM ValoradoTipo v WHERE v.fecha = :fecha")})
public class ValoradoTipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_tipo", nullable = false, length = 10)
    private String codTipo;
    @Column(name = "nombre", length = 150)
    private String nombre;
    @Column(name = "descripcion", length = 250)
    private String descripcion;
    @Column(name = "estado", length = 2)
    private String estado;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToMany(mappedBy = "codTipo", fetch = FetchType.EAGER)
    private Set<Valorado> valoradoSet;

    public ValoradoTipo() {
    }

    public ValoradoTipo(String codTipo) {
        this.codTipo = codTipo;
    }

    public String getCodTipo() {
        return codTipo;
    }

    public void setCodTipo(String codTipo) {
        this.codTipo = codTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Set<Valorado> getValoradoSet() {
        return valoradoSet;
    }

    public void setValoradoSet(Set<Valorado> valoradoSet) {
        this.valoradoSet = valoradoSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codTipo != null ? codTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValoradoTipo)) {
            return false;
        }
        ValoradoTipo other = (ValoradoTipo) object;
        if ((this.codTipo == null && other.codTipo != null) || (this.codTipo != null && !this.codTipo.equals(other.codTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.ValoradoTipo[ codTipo=" + codTipo + " ]";
    }
    
}
