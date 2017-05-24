/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.nasqa.values.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Eccopacondori
 */
@Entity
@Table(name = "registro_feriado", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "RegistroFeriado.findAll", query = "SELECT r FROM RegistroFeriado r"),
    @NamedQuery(name = "RegistroFeriado.findById", query = "SELECT r FROM RegistroFeriado r WHERE r.id = :id"),
    @NamedQuery(name = "RegistroFeriado.findByFecha", query = "SELECT r FROM RegistroFeriado r WHERE r.fecha = :fecha")})
public class RegistroFeriado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "fecha", length = 10)
    private String fecha;

    public RegistroFeriado() {
    }

    public RegistroFeriado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof RegistroFeriado)) {
            return false;
        }
        RegistroFeriado other = (RegistroFeriado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.RegistroFeriado[ id=" + id + " ]";
    }
    
}
