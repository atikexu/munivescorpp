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
@Table(name = "indicador_gestion_telf", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "IndicadorGestionTelf.findAll", query = "SELECT i FROM IndicadorGestionTelf i"),
    @NamedQuery(name = "IndicadorGestionTelf.findByCodGesTel", query = "SELECT i FROM IndicadorGestionTelf i WHERE i.codGesTel = :codGesTel"),
    @NamedQuery(name = "IndicadorGestionTelf.findByDesGesTel", query = "SELECT i FROM IndicadorGestionTelf i WHERE i.desGesTel = :desGesTel"),
    @NamedQuery(name = "IndicadorGestionTelf.findBySitGesTel", query = "SELECT i FROM IndicadorGestionTelf i WHERE i.sitGesTel = :sitGesTel"),
    @NamedQuery(name = "IndicadorGestionTelf.findByEstGesTel", query = "SELECT i FROM IndicadorGestionTelf i WHERE i.estGesTel = :estGesTel")})
public class IndicadorGestionTelf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_ges_tel", nullable = false, length = 10)
    private String codGesTel;
    @Column(name = "des_ges_tel", length = 200)
    private String desGesTel;
    @Column(name = "sit_ges_tel", length = 200)
    private String sitGesTel;
    @Column(name = "est_ges_tel")
    private Integer estGesTel;

    public IndicadorGestionTelf() {
    }

    public IndicadorGestionTelf(String codGesTel) {
        this.codGesTel = codGesTel;
    }

    public String getCodGesTel() {
        return codGesTel;
    }

    public void setCodGesTel(String codGesTel) {
        this.codGesTel = codGesTel;
    }

    public String getDesGesTel() {
        return desGesTel;
    }

    public void setDesGesTel(String desGesTel) {
        this.desGesTel = desGesTel;
    }

    public String getSitGesTel() {
        return sitGesTel;
    }

    public void setSitGesTel(String sitGesTel) {
        this.sitGesTel = sitGesTel;
    }

    public Integer getEstGesTel() {
        return estGesTel;
    }

    public void setEstGesTel(Integer estGesTel) {
        this.estGesTel = estGesTel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codGesTel != null ? codGesTel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndicadorGestionTelf)) {
            return false;
        }
        IndicadorGestionTelf other = (IndicadorGestionTelf) object;
        if ((this.codGesTel == null && other.codGesTel != null) || (this.codGesTel != null && !this.codGesTel.equals(other.codGesTel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.IndicadorGestionTelf[ codGesTel=" + codGesTel + " ]";
    }
    
}
