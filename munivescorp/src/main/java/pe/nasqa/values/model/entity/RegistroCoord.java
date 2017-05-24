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
@Table(name = "registro_coord", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "RegistroCoord.findAll", query = "SELECT r FROM RegistroCoord r"),
    @NamedQuery(name = "RegistroCoord.findById", query = "SELECT r FROM RegistroCoord r WHERE r.id = :id"),
    @NamedQuery(name = "RegistroCoord.findByCodBar", query = "SELECT r FROM RegistroCoord r WHERE r.codBar = :codBar"),
    @NamedQuery(name = "RegistroCoord.findByFecReg", query = "SELECT r FROM RegistroCoord r WHERE r.fecReg = :fecReg"),
    @NamedQuery(name = "RegistroCoord.findByFecCoo", query = "SELECT r FROM RegistroCoord r WHERE r.fecCoo = :fecCoo"),
    @NamedQuery(name = "RegistroCoord.findByHorCoo", query = "SELECT r FROM RegistroCoord r WHERE r.horCoo = :horCoo"),
    @NamedQuery(name = "RegistroCoord.findByDirCoo", query = "SELECT r FROM RegistroCoord r WHERE r.dirCoo = :dirCoo"),
    @NamedQuery(name = "RegistroCoord.findByPosCoo", query = "SELECT r FROM RegistroCoord r WHERE r.posCoo = :posCoo"),
    @NamedQuery(name = "RegistroCoord.findByUbiCoo", query = "SELECT r FROM RegistroCoord r WHERE r.ubiCoo = :ubiCoo"),
    @NamedQuery(name = "RegistroCoord.findByRefCoo", query = "SELECT r FROM RegistroCoord r WHERE r.refCoo = :refCoo"),
    @NamedQuery(name = "RegistroCoord.findByObsCoo", query = "SELECT r FROM RegistroCoord r WHERE r.obsCoo = :obsCoo"),
    @NamedQuery(name = "RegistroCoord.findByTlfCoo", query = "SELECT r FROM RegistroCoord r WHERE r.tlfCoo = :tlfCoo"),
    @NamedQuery(name = "RegistroCoord.findByCodAge", query = "SELECT r FROM RegistroCoord r WHERE r.codAge = :codAge"),
    @NamedQuery(name = "RegistroCoord.findByNomAge", query = "SELECT r FROM RegistroCoord r WHERE r.nomAge = :nomAge"),
    @NamedQuery(name = "RegistroCoord.findByCodUsu", query = "SELECT r FROM RegistroCoord r WHERE r.codUsu = :codUsu"),
    @NamedQuery(name = "RegistroCoord.findByNomUsu", query = "SELECT r FROM RegistroCoord r WHERE r.nomUsu = :nomUsu"),
    @NamedQuery(name = "RegistroCoord.findByIndUsu", query = "SELECT r FROM RegistroCoord r WHERE r.indUsu = :indUsu"),
    @NamedQuery(name = "RegistroCoord.findByDesUsu", query = "SELECT r FROM RegistroCoord r WHERE r.desUsu = :desUsu"),
    @NamedQuery(name = "RegistroCoord.findByIndLug", query = "SELECT r FROM RegistroCoord r WHERE r.indLug = :indLug"),
    @NamedQuery(name = "RegistroCoord.findByDesLug", query = "SELECT r FROM RegistroCoord r WHERE r.desLug = :desLug"),
    @NamedQuery(name = "RegistroCoord.findByIndDir", query = "SELECT r FROM RegistroCoord r WHERE r.indDir = :indDir"),
    @NamedQuery(name = "RegistroCoord.findByDesDir", query = "SELECT r FROM RegistroCoord r WHERE r.desDir = :desDir"),
    @NamedQuery(name = "RegistroCoord.findByIndPri", query = "SELECT r FROM RegistroCoord r WHERE r.indPri = :indPri"),
    @NamedQuery(name = "RegistroCoord.findByDesPri", query = "SELECT r FROM RegistroCoord r WHERE r.desPri = :desPri"),
    @NamedQuery(name = "RegistroCoord.findByIndLla", query = "SELECT r FROM RegistroCoord r WHERE r.indLla = :indLla"),
    @NamedQuery(name = "RegistroCoord.findByDesLla", query = "SELECT r FROM RegistroCoord r WHERE r.desLla = :desLla"),
    @NamedQuery(name = "RegistroCoord.findByUsuCre", query = "SELECT r FROM RegistroCoord r WHERE r.usuCre = :usuCre"),
    @NamedQuery(name = "RegistroCoord.findByFecCre", query = "SELECT r FROM RegistroCoord r WHERE r.fecCre = :fecCre"),
    @NamedQuery(name = "RegistroCoord.findByFlgStt", query = "SELECT r FROM RegistroCoord r WHERE r.flgStt = :flgStt")})
public class RegistroCoord implements Serializable {
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
    @Column(name = "fec_coo")
    @Temporal(TemporalType.DATE)
    private Date fecCoo;
    @Column(name = "hor_coo", length = 60)
    private String horCoo;
    @Column(name = "dir_coo", length = 200)
    private String dirCoo;
    @Column(name = "pos_coo", length = 200)
    private String posCoo;
    @Column(name = "ubi_coo", length = 200)
    private String ubiCoo;
    @Column(name = "ref_coo", length = 200)
    private String refCoo;
    @Column(name = "obs_coo", length = 120)
    private String obsCoo;
    @Column(name = "tlf_coo", length = 20)
    private String tlfCoo;
    @Column(name = "cod_age", length = 8)
    private String codAge;
    @Column(name = "nom_age", length = 40)
    private String nomAge;
    @Column(name = "cod_usu", length = 4)
    private String codUsu;
    @Column(name = "nom_usu", length = 40)
    private String nomUsu;
    @Column(name = "ind_usu")
    private Integer indUsu;
    @Column(name = "des_usu", length = 20)
    private String desUsu;
    @Column(name = "ind_lug")
    private Integer indLug;
    @Column(name = "des_lug", length = 200)
    private String desLug;
    @Column(name = "ind_dir")
    private Integer indDir;
    @Column(name = "des_dir", length = 20)
    private String desDir;
    @Column(name = "ind_pri")
    private Integer indPri;
    @Column(name = "des_pri", length = 20)
    private String desPri;
    @Column(name = "ind_lla")
    private Integer indLla;
    @Column(name = "des_lla", length = 20)
    private String desLla;
    @Column(name = "usu_cre")
    private Integer usuCre;
    @Column(name = "fec_cre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCre;
    @Column(name = "flg_stt", length = 2)
    private String flgStt;

    public RegistroCoord() {
    }

    public RegistroCoord(Integer id) {
        this.id = id;
    }

    public RegistroCoord(Integer id, String codBar) {
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

    public Date getFecCoo() {
        return fecCoo;
    }

    public void setFecCoo(Date fecCoo) {
        this.fecCoo = fecCoo;
    }

    public String getHorCoo() {
        return horCoo;
    }

    public void setHorCoo(String horCoo) {
        this.horCoo = horCoo;
    }

    public String getDirCoo() {
        return dirCoo;
    }

    public void setDirCoo(String dirCoo) {
        this.dirCoo = dirCoo;
    }

    public String getPosCoo() {
        return posCoo;
    }

    public void setPosCoo(String posCoo) {
        this.posCoo = posCoo;
    }

    public String getUbiCoo() {
        return ubiCoo;
    }

    public void setUbiCoo(String ubiCoo) {
        this.ubiCoo = ubiCoo;
    }

    public String getRefCoo() {
        return refCoo;
    }

    public void setRefCoo(String refCoo) {
        this.refCoo = refCoo;
    }

    public String getObsCoo() {
        return obsCoo;
    }

    public void setObsCoo(String obsCoo) {
        this.obsCoo = obsCoo;
    }

    public String getTlfCoo() {
        return tlfCoo;
    }

    public void setTlfCoo(String tlfCoo) {
        this.tlfCoo = tlfCoo;
    }

    public String getCodAge() {
        return codAge;
    }

    public void setCodAge(String codAge) {
        this.codAge = codAge;
    }

    public String getNomAge() {
        return nomAge;
    }

    public void setNomAge(String nomAge) {
        this.nomAge = nomAge;
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

    public Integer getIndUsu() {
        return indUsu;
    }

    public void setIndUsu(Integer indUsu) {
        this.indUsu = indUsu;
    }

    public String getDesUsu() {
        return desUsu;
    }

    public void setDesUsu(String desUsu) {
        this.desUsu = desUsu;
    }

    public Integer getIndLug() {
        return indLug;
    }

    public void setIndLug(Integer indLug) {
        this.indLug = indLug;
    }

    public String getDesLug() {
        return desLug;
    }

    public void setDesLug(String desLug) {
        this.desLug = desLug;
    }

    public Integer getIndDir() {
        return indDir;
    }

    public void setIndDir(Integer indDir) {
        this.indDir = indDir;
    }

    public String getDesDir() {
        return desDir;
    }

    public void setDesDir(String desDir) {
        this.desDir = desDir;
    }

    public Integer getIndPri() {
        return indPri;
    }

    public void setIndPri(Integer indPri) {
        this.indPri = indPri;
    }

    public String getDesPri() {
        return desPri;
    }

    public void setDesPri(String desPri) {
        this.desPri = desPri;
    }

    public Integer getIndLla() {
        return indLla;
    }

    public void setIndLla(Integer indLla) {
        this.indLla = indLla;
    }

    public String getDesLla() {
        return desLla;
    }

    public void setDesLla(String desLla) {
        this.desLla = desLla;
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
        if (!(object instanceof RegistroCoord)) {
            return false;
        }
        RegistroCoord other = (RegistroCoord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.RegistroCoord[ id=" + id + " ]";
    }
    
}
