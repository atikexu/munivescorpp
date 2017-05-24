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
 * @author Eccopacondori
 */
@Entity
@Table(name = "registro_coord_telf", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "RegistroCoordTelf.findAll", query = "SELECT r FROM RegistroCoordTelf r"),
    @NamedQuery(name = "RegistroCoordTelf.findById", query = "SELECT r FROM RegistroCoordTelf r WHERE r.id = :id"),
    @NamedQuery(name = "RegistroCoordTelf.findByCodBar", query = "SELECT r FROM RegistroCoordTelf r WHERE r.codBar = :codBar"),
    @NamedQuery(name = "RegistroCoordTelf.findByFecReg", query = "SELECT r FROM RegistroCoordTelf r WHERE r.fecReg = :fecReg"),
    @NamedQuery(name = "RegistroCoordTelf.findByHorReg", query = "SELECT r FROM RegistroCoordTelf r WHERE r.horReg = :horReg"),
    @NamedQuery(name = "RegistroCoordTelf.findByNroTlf", query = "SELECT r FROM RegistroCoordTelf r WHERE r.nroTlf = :nroTlf"),
    @NamedQuery(name = "RegistroCoordTelf.findByCodMot", query = "SELECT r FROM RegistroCoordTelf r WHERE r.codMot = :codMot"),
    @NamedQuery(name = "RegistroCoordTelf.findByDesMot", query = "SELECT r FROM RegistroCoordTelf r WHERE r.desMot = :desMot"),
    @NamedQuery(name = "RegistroCoordTelf.findByDetObs", query = "SELECT r FROM RegistroCoordTelf r WHERE r.detObs = :detObs"),
    @NamedQuery(name = "RegistroCoordTelf.findByCodUsu", query = "SELECT r FROM RegistroCoordTelf r WHERE r.codUsu = :codUsu"),
    @NamedQuery(name = "RegistroCoordTelf.findByNomUsu", query = "SELECT r FROM RegistroCoordTelf r WHERE r.nomUsu = :nomUsu"),
    @NamedQuery(name = "RegistroCoordTelf.findByIndSeg", query = "SELECT r FROM RegistroCoordTelf r WHERE r.indSeg = :indSeg"),
    @NamedQuery(name = "RegistroCoordTelf.findByDesSeg", query = "SELECT r FROM RegistroCoordTelf r WHERE r.desSeg = :desSeg"),
    @NamedQuery(name = "RegistroCoordTelf.findByUsuCre", query = "SELECT r FROM RegistroCoordTelf r WHERE r.usuCre = :usuCre"),
    @NamedQuery(name = "RegistroCoordTelf.findByFecCre", query = "SELECT r FROM RegistroCoordTelf r WHERE r.fecCre = :fecCre"),
    @NamedQuery(name = "RegistroCoordTelf.findByFlgStt", query = "SELECT r FROM RegistroCoordTelf r WHERE r.flgStt = :flgStt")})
public class RegistroCoordTelf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "cod_bar", nullable = false, length = 30)
    private String codBar;
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
    @Column(name = "flg_stt", length = 2)
    private String flgStt;

    public RegistroCoordTelf() {
    }

    public RegistroCoordTelf(Integer id) {
        this.id = id;
    }

    public RegistroCoordTelf(Integer id, String codBar) {
        this.id = id;
        this.codBar = codBar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodBar() {
        return codBar;
    }

    public void setCodBar(String codBar) {
        this.codBar = codBar;
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

    public String getFlgStt() {
        return flgStt;
    }

    public void setFlgStt(String flgStt) {
        this.flgStt = flgStt;
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
        if (!(object instanceof RegistroCoordTelf)) {
            return false;
        }
        RegistroCoordTelf other = (RegistroCoordTelf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.RegistroCoordTelf[ id=" + id + " ]";
    }
    
}
