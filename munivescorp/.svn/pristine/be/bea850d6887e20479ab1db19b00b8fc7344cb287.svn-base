/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.nasqa.values.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eccopacondori
 */
@Entity
@Table(name = "distribucion_paquete", catalog = "nasqa_values", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DistribucionPaquete.findAll", query = "SELECT d FROM DistribucionPaquete d"),
    @NamedQuery(name = "DistribucionPaquete.findById", query = "SELECT d FROM DistribucionPaquete d WHERE d.id = :id"),
    @NamedQuery(name = "DistribucionPaquete.findByIdImport", query = "SELECT d FROM DistribucionPaquete d WHERE d.idImport = :idImport"),
    @NamedQuery(name = "DistribucionPaquete.findByCodBar", query = "SELECT d FROM DistribucionPaquete d WHERE d.codBar = :codBar"),
    @NamedQuery(name = "DistribucionPaquete.findByNroSec", query = "SELECT d FROM DistribucionPaquete d WHERE d.nroSec = :nroSec"),
    @NamedQuery(name = "DistribucionPaquete.findByNomDes", query = "SELECT d FROM DistribucionPaquete d WHERE d.nomDes = :nomDes"),
    @NamedQuery(name = "DistribucionPaquete.findByNroDoc", query = "SELECT d FROM DistribucionPaquete d WHERE d.nroDoc = :nroDoc"),
    @NamedQuery(name = "DistribucionPaquete.findByNroIde", query = "SELECT d FROM DistribucionPaquete d WHERE d.nroIde = :nroIde"),
    @NamedQuery(name = "DistribucionPaquete.findByCodOtr", query = "SELECT d FROM DistribucionPaquete d WHERE d.codOtr = :codOtr"),
    @NamedQuery(name = "DistribucionPaquete.findByNomOtr", query = "SELECT d FROM DistribucionPaquete d WHERE d.nomOtr = :nomOtr"),
    @NamedQuery(name = "DistribucionPaquete.findByTlfRef", query = "SELECT d FROM DistribucionPaquete d WHERE d.tlfRef = :tlfRef"),
    @NamedQuery(name = "DistribucionPaquete.findByUsuCre", query = "SELECT d FROM DistribucionPaquete d WHERE d.usuCre = :usuCre"),
    @NamedQuery(name = "DistribucionPaquete.findByFecCre", query = "SELECT d FROM DistribucionPaquete d WHERE d.fecCre = :fecCre"),
    @NamedQuery(name = "DistribucionPaquete.findByValDes", query = "SELECT d FROM DistribucionPaquete d WHERE d.valDes = :valDes")})
public class DistribucionPaquete implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_import", nullable = false)
    private int idImport;
    @Column(name = "cod_bar", length = 30)
    private String codBar;
    @Column(name = "nro_sec", length = 10)
    private String nroSec;
    @Column(name = "nom_des", length = 200)
    private String nomDes;
    @Column(name = "nro_doc", length = 100)
    private String nroDoc;
    @Column(name = "nro_ide", length = 100)
    private String nroIde;
    @Column(name = "cod_otr", length = 100)
    private String codOtr;
    @Column(name = "nom_otr", length = 200)
    private String nomOtr;
    @Column(name = "tlf_ref", length = 60)
    private String tlfRef;
    @Column(name = "usu_cre")
    private Integer usuCre;
    @Column(name = "fec_cre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCre;
    @Column(name = "val_des", length = 250)
    private String valDes;

    public DistribucionPaquete() {
    }

    public DistribucionPaquete(Integer id) {
        this.id = id;
    }

    public DistribucionPaquete(Integer id, int idImport) {
        this.id = id;
        this.idImport = idImport;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdImport() {
        return idImport;
    }

    public void setIdImport(int idImport) {
        this.idImport = idImport;
    }

    public String getCodBar() {
        return codBar;
    }

    public void setCodBar(String codBar) {
        this.codBar = codBar;
    }

    public String getNroSec() {
        return nroSec;
    }

    public void setNroSec(String nroSec) {
        this.nroSec = nroSec;
    }

    public String getNomDes() {
        return nomDes;
    }

    public void setNomDes(String nomDes) {
        this.nomDes = nomDes;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
    }

    public String getNroIde() {
        return nroIde;
    }

    public void setNroIde(String nroIde) {
        this.nroIde = nroIde;
    }

    public String getCodOtr() {
        return codOtr;
    }

    public void setCodOtr(String codOtr) {
        this.codOtr = codOtr;
    }

    public String getNomOtr() {
        return nomOtr;
    }

    public void setNomOtr(String nomOtr) {
        this.nomOtr = nomOtr;
    }

    public String getTlfRef() {
        return tlfRef;
    }

    public void setTlfRef(String tlfRef) {
        this.tlfRef = tlfRef;
    }

    public Integer getUsuCre() {
        return usuCre;
    }

    public void setUsuCre(Integer usuCre) {
        this.usuCre = usuCre;
    }

    public Date getFecCre() {
        return fecCre;
    }

    public void setFecCre(Date fecCre) {
        this.fecCre = fecCre;
    }

    public String getValDes() {
        return valDes;
    }

    public void setValDes(String valDes) {
        this.valDes = valDes;
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
        if (!(object instanceof DistribucionPaquete)) {
            return false;
        }
        DistribucionPaquete other = (DistribucionPaquete) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.DistribucionPaquete[ id=" + id + " ]";
    }
    
}
