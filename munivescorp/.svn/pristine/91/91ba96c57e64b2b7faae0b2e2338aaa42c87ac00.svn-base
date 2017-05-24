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
@Table(name = "distribucion_coord", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "DistribucionCoord.findAll", query = "SELECT d FROM DistribucionCoord d"),
    @NamedQuery(name = "DistribucionCoord.findById", query = "SELECT d FROM DistribucionCoord d WHERE d.id = :id"),
    @NamedQuery(name = "DistribucionCoord.findByIdImport", query = "SELECT d FROM DistribucionCoord d WHERE d.idImport = :idImport"),
    @NamedQuery(name = "DistribucionCoord.findByNroCoo", query = "SELECT d FROM DistribucionCoord d WHERE d.nroCoo = :nroCoo"),
    @NamedQuery(name = "DistribucionCoord.findByCodBar", query = "SELECT d FROM DistribucionCoord d WHERE d.codBar = :codBar"),
    @NamedQuery(name = "DistribucionCoord.findByFecReg", query = "SELECT d FROM DistribucionCoord d WHERE d.fecReg = :fecReg"),
    @NamedQuery(name = "DistribucionCoord.findByFecCoo", query = "SELECT d FROM DistribucionCoord d WHERE d.fecCoo = :fecCoo"),
    @NamedQuery(name = "DistribucionCoord.findByHorCoo", query = "SELECT d FROM DistribucionCoord d WHERE d.horCoo = :horCoo"),
    @NamedQuery(name = "DistribucionCoord.findByNroRef", query = "SELECT d FROM DistribucionCoord d WHERE d.nroRef = :nroRef"),
    @NamedQuery(name = "DistribucionCoord.findByDirCoo", query = "SELECT d FROM DistribucionCoord d WHERE d.dirCoo = :dirCoo"),
    @NamedQuery(name = "DistribucionCoord.findByPosCoo", query = "SELECT d FROM DistribucionCoord d WHERE d.posCoo = :posCoo"),
    @NamedQuery(name = "DistribucionCoord.findByUbiCoo", query = "SELECT d FROM DistribucionCoord d WHERE d.ubiCoo = :ubiCoo"),
    @NamedQuery(name = "DistribucionCoord.findByRefCoo", query = "SELECT d FROM DistribucionCoord d WHERE d.refCoo = :refCoo"),
    @NamedQuery(name = "DistribucionCoord.findByObsCoo", query = "SELECT d FROM DistribucionCoord d WHERE d.obsCoo = :obsCoo"),
    @NamedQuery(name = "DistribucionCoord.findByCodAge", query = "SELECT d FROM DistribucionCoord d WHERE d.codAge = :codAge"),
    @NamedQuery(name = "DistribucionCoord.findByNomAge", query = "SELECT d FROM DistribucionCoord d WHERE d.nomAge = :nomAge"),
    @NamedQuery(name = "DistribucionCoord.findByTlfCoo", query = "SELECT d FROM DistribucionCoord d WHERE d.tlfCoo = :tlfCoo"),
    @NamedQuery(name = "DistribucionCoord.findByNroTlf", query = "SELECT d FROM DistribucionCoord d WHERE d.nroTlf = :nroTlf"),
    @NamedQuery(name = "DistribucionCoord.findByCodUsu", query = "SELECT d FROM DistribucionCoord d WHERE d.codUsu = :codUsu"),
    @NamedQuery(name = "DistribucionCoord.findByNomUsu", query = "SELECT d FROM DistribucionCoord d WHERE d.nomUsu = :nomUsu"),
    @NamedQuery(name = "DistribucionCoord.findByIndUsu", query = "SELECT d FROM DistribucionCoord d WHERE d.indUsu = :indUsu"),
    @NamedQuery(name = "DistribucionCoord.findByDesUsu", query = "SELECT d FROM DistribucionCoord d WHERE d.desUsu = :desUsu"),
    @NamedQuery(name = "DistribucionCoord.findByIndLug", query = "SELECT d FROM DistribucionCoord d WHERE d.indLug = :indLug"),
    @NamedQuery(name = "DistribucionCoord.findByDesLug", query = "SELECT d FROM DistribucionCoord d WHERE d.desLug = :desLug"),
    @NamedQuery(name = "DistribucionCoord.findByIndDir", query = "SELECT d FROM DistribucionCoord d WHERE d.indDir = :indDir"),
    @NamedQuery(name = "DistribucionCoord.findByDesDir", query = "SELECT d FROM DistribucionCoord d WHERE d.desDir = :desDir"),
    @NamedQuery(name = "DistribucionCoord.findByIndPri", query = "SELECT d FROM DistribucionCoord d WHERE d.indPri = :indPri"),
    @NamedQuery(name = "DistribucionCoord.findByDesPri", query = "SELECT d FROM DistribucionCoord d WHERE d.desPri = :desPri"),
    @NamedQuery(name = "DistribucionCoord.findByIndLla", query = "SELECT d FROM DistribucionCoord d WHERE d.indLla = :indLla"),
    @NamedQuery(name = "DistribucionCoord.findByDesLla", query = "SELECT d FROM DistribucionCoord d WHERE d.desLla = :desLla"),
    @NamedQuery(name = "DistribucionCoord.findByUsuCre", query = "SELECT d FROM DistribucionCoord d WHERE d.usuCre = :usuCre"),
    @NamedQuery(name = "DistribucionCoord.findByFecCre", query = "SELECT d FROM DistribucionCoord d WHERE d.fecCre = :fecCre")})
public class DistribucionCoord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_import", nullable = false)
    private int idImport;
    @Column(name = "nro_coo", length = 10)
    private String nroCoo;
    @Column(name = "cod_bar", length = 30)
    private String codBar;
    @Column(name = "fec_reg")
    @Temporal(TemporalType.DATE)
    private Date fecReg;
    @Column(name = "fec_coo")
    @Temporal(TemporalType.DATE)
    private Date fecCoo;
    @Column(name = "hor_coo", length = 60)
    private String horCoo;
    @Column(name = "nro_ref", length = 60)
    private String nroRef;
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
    @Column(name = "cod_age", length = 20)
    private String codAge;
    @Column(name = "nom_age", length = 200)
    private String nomAge;
    @Column(name = "tlf_coo", length = 20)
    private String tlfCoo;
    @Column(name = "nro_tlf", length = 20)
    private String nroTlf;
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

    public DistribucionCoord() {
    }

    public DistribucionCoord(Integer id) {
        this.id = id;
    }

    public DistribucionCoord(Integer id, int idImport) {
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

    public String getNroCoo() {
        return nroCoo;
    }

    public void setNroCoo(String nroCoo) {
        this.nroCoo = nroCoo;
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

    public String getNroRef() {
        return nroRef;
    }

    public void setNroRef(String nroRef) {
        this.nroRef = nroRef;
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

    public String getTlfCoo() {
        return tlfCoo;
    }

    public void setTlfCoo(String tlfCoo) {
        this.tlfCoo = tlfCoo;
    }

    public String getNroTlf() {
        return nroTlf;
    }

    public void setNroTlf(String nroTlf) {
        this.nroTlf = nroTlf;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DistribucionCoord)) {
            return false;
        }
        DistribucionCoord other = (DistribucionCoord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.DistribucionCoord[ id=" + id + " ]";
    }
    
}
