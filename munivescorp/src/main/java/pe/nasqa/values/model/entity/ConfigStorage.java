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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Eccopacondori
 */
@Entity
@Table(name = "config_storage", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "ConfigStorage.findAll", query = "SELECT c FROM ConfigStorage c"),
    @NamedQuery(name = "ConfigStorage.findById", query = "SELECT c FROM ConfigStorage c WHERE c.id = :id"),
    @NamedQuery(name = "ConfigStorage.findByRootPathCargo", query = "SELECT c FROM ConfigStorage c WHERE c.rootPathCargo = :rootPathCargo"),
    @NamedQuery(name = "ConfigStorage.findByRootPathDocumento", query = "SELECT c FROM ConfigStorage c WHERE c.rootPathDocumento = :rootPathDocumento")})
public class ConfigStorage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "root_path_cargo", length = 250)
    private String rootPathCargo;
    @Column(name = "root_path_documento", length = 250)
    private String rootPathDocumento;

    public ConfigStorage() {
    }

    public ConfigStorage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRootPathCargo() {
        return rootPathCargo;
    }

    public void setRootPathCargo(String rootPathCargo) {
        this.rootPathCargo = rootPathCargo;
    }

    public String getRootPathDocumento() {
        return rootPathDocumento;
    }

    public void setRootPathDocumento(String rootPathDocumento) {
        this.rootPathDocumento = rootPathDocumento;
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
        if (!(object instanceof ConfigStorage)) {
            return false;
        }
        ConfigStorage other = (ConfigStorage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.ConfigStorage[ id=" + id + " ]";
    }
    
}
