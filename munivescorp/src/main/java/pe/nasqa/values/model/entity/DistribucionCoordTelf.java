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

/**
 *
 * @author Edwin
 */
@Entity
@Table(name = "distribucion_coord_telf", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "DistribucionCoordTelf.findAll", query = "SELECT d FROM DistribucionCoordTelf d"),
    @NamedQuery(name = "DistribucionCoordTelf.findById", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.id = :id"),
    @NamedQuery(name = "DistribucionCoordTelf.findByIdImport", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.idImport = :idImport"),
    @NamedQuery(name = "DistribucionCoordTelf.findByNroSec", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.nroSec = :nroSec"),
    @NamedQuery(name = "DistribucionCoordTelf.findByCodBar", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.codBar = :codBar"),
    @NamedQuery(name = "DistribucionCoordTelf.findByNroRef", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.nroRef = :nroRef"),
    @NamedQuery(name = "DistribucionCoordTelf.findByFecReg", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.fecReg = :fecReg"),
    @NamedQuery(name = "DistribucionCoordTelf.findByHorReg", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.horReg = :horReg"),
    @NamedQuery(name = "DistribucionCoordTelf.findByNroTlf", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.nroTlf = :nroTlf"),
    @NamedQuery(name = "DistribucionCoordTelf.findByCodMot", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.codMot = :codMot"),
    @NamedQuery(name = "DistribucionCoordTelf.findByDesMot", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.desMot = :desMot"),
    @NamedQuery(name = "DistribucionCoordTelf.findByDetObs", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.detObs = :detObs"),
    @NamedQuery(name = "DistribucionCoordTelf.findByCodUsu", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.codUsu = :codUsu"),
    @NamedQuery(name = "DistribucionCoordTelf.findByNomUsu", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.nomUsu = :nomUsu"),
    @NamedQuery(name = "DistribucionCoordTelf.findByIndSeg", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.indSeg = :indSeg"),
    @NamedQuery(name = "DistribucionCoordTelf.findByDesSeg", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.desSeg = :desSeg"),
    @NamedQuery(name = "DistribucionCoordTelf.findByUsuCre", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.usuCre = :usuCre"),
    @NamedQuery(name = "DistribucionCoordTelf.findByFecCre", query = "SELECT d FROM DistribucionCoordTelf d WHERE d.fecCre = :fecCre")})
public class DistribucionCoordTelf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_import", nullable = false)
    private int idImport;
    @Column(name = "nro_sec", length = 10)
    private String nroSec;
    @Column(name = "cod_bar", length = 30)
    private String codBar;
    @Column(name = "nro_ref", length = 60)
    private String nroRef;
    @Column(name = "fec_reg")
    @Temporal(TemporalType.DATE)
    private Date fecReg;
    @Column(name = "hor_reg", length = 60)
    private String horReg;
    @Column(name = "nro_tlf", length = 20)
    private String nroTlf;
    @Column(name = "cod_mot", length = 4)
    private String codMot;
    @Column(name = "des_mot", length = 40)
    private String desMot;
    @Column(name = "det_obs", length = 200)
    private String detObs;
    @Column(name = "cod_usu", length = 4)
    private String codUsu;
    @Column(name = "nom_usu", length = 40)
    private String nomUsu;
    @Column(name = "ind_seg", length = 1)
    private String indSeg;
    @Column(name = "des_seg", length = 20)
    private String desSeg;
    @Column(name = "usu_cre")
    private Integer usuCre;
    @Column(name = "fec_cre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCre;

    public DistribucionCoordTelf() {
    }

    public DistribucionCoordTelf(Integer id) {
        this.id = id;
    }

    public DistribucionCoordTelf(Integer id, int idImport) {
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

    public String getNroSec() {
        return nroSec;
    }

    public void setNroSec(String nroSec) {
        this.nroSec = nroSec;
    }

    public String getCodBar() {
        return codBar;
    }

    public void setCodBar(String codBar) {
        this.codBar = codBar;
    }

    public String getNroRef() {
        return nroRef;
    }

    public void setNroRef(String nroRef) {
        this.nroRef = nroRef;
    }

    public Date getFecReg() {
        return fecReg;
    }

    public void setFecReg(Date fecReg) {
        this.fecReg = fecReg;
    }

    public String getHorReg() {
        return horReg;
    }

    public void setHorReg(String horReg) {
        this.horReg = horReg;
    }

    public String getNroTlf() {
        return nroTlf;
    }

    public void setNroTlf(String nroTlf) {
        this.nroTlf = nroTlf;
    }

    public String getCodMot() {
        return codMot;
    }

    public void setCodMot(String codMot) {
        this.codMot = codMot;
    }

    public String getDesMot() {
        return desMot;
    }

    public void setDesMot(String desMot) {
        this.desMot = desMot;
    }

    public String getDetObs() {
        return detObs;
    }

    public void setDetObs(String detObs) {
        this.detObs = detObs;
    }

    public String getCodUsu() {
        return codUsu;
    }

    public void setCodUsu(String codUsu) {
        this.codUsu = codUsu;
    }

    public String getNomUsu() {
        return nomUsu;
    }

    public void setNomUsu(String nomUsu) {
        this.nomUsu = nomUsu;
    }

    public String getIndSeg() {
        return indSeg;
    }

    public void setIndSeg(String indSeg) {
        this.indSeg = indSeg;
    }

    public String getDesSeg() {
        return desSeg;
    }

    public void setDesSeg(String desSeg) {
        this.desSeg = desSeg;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DistribucionCoordTelf)) {
            return false;
        }
        DistribucionCoordTelf other = (DistribucionCoordTelf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.DistribucionCoordTelf[ id=" + id + " ]";
    }
    
}
