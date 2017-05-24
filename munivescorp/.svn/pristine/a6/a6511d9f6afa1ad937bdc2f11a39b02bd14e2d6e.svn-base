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
@Table(name = "distribucion_visita", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "DistribucionVisita.findAll", query = "SELECT d FROM DistribucionVisita d"),
    @NamedQuery(name = "DistribucionVisita.findById", query = "SELECT d FROM DistribucionVisita d WHERE d.id = :id"),
    @NamedQuery(name = "DistribucionVisita.findByIdImport", query = "SELECT d FROM DistribucionVisita d WHERE d.idImport = :idImport"),
    @NamedQuery(name = "DistribucionVisita.findByCodBar", query = "SELECT d FROM DistribucionVisita d WHERE d.codBar = :codBar"),
    @NamedQuery(name = "DistribucionVisita.findByNroHoj", query = "SELECT d FROM DistribucionVisita d WHERE d.nroHoj = :nroHoj"),
    @NamedQuery(name = "DistribucionVisita.findByFecHoj", query = "SELECT d FROM DistribucionVisita d WHERE d.fecHoj = :fecHoj"),
    @NamedQuery(name = "DistribucionVisita.findByCodMsj", query = "SELECT d FROM DistribucionVisita d WHERE d.codMsj = :codMsj"),
    @NamedQuery(name = "DistribucionVisita.findByNomMsj", query = "SELECT d FROM DistribucionVisita d WHERE d.nomMsj = :nomMsj"),
    @NamedQuery(name = "DistribucionVisita.findByIndSit", query = "SELECT d FROM DistribucionVisita d WHERE d.indSit = :indSit"),
    @NamedQuery(name = "DistribucionVisita.findByDesSit", query = "SELECT d FROM DistribucionVisita d WHERE d.desSit = :desSit"),
    @NamedQuery(name = "DistribucionVisita.findByCodMot", query = "SELECT d FROM DistribucionVisita d WHERE d.codMot = :codMot"),
    @NamedQuery(name = "DistribucionVisita.findByDesMot", query = "SELECT d FROM DistribucionVisita d WHERE d.desMot = :desMot"),
    @NamedQuery(name = "DistribucionVisita.findByHorVis", query = "SELECT d FROM DistribucionVisita d WHERE d.horVis = :horVis"),
    @NamedQuery(name = "DistribucionVisita.findByTipDir", query = "SELECT d FROM DistribucionVisita d WHERE d.tipDir = :tipDir"),
    @NamedQuery(name = "DistribucionVisita.findByDesTip", query = "SELECT d FROM DistribucionVisita d WHERE d.desTip = :desTip"),
    @NamedQuery(name = "DistribucionVisita.findByCodUsu", query = "SELECT d FROM DistribucionVisita d WHERE d.codUsu = :codUsu"),
    @NamedQuery(name = "DistribucionVisita.findByNomUsu", query = "SELECT d FROM DistribucionVisita d WHERE d.nomUsu = :nomUsu"),
    @NamedQuery(name = "DistribucionVisita.findByNroSec", query = "SELECT d FROM DistribucionVisita d WHERE d.nroSec = :nroSec"),
    @NamedQuery(name = "DistribucionVisita.findByUsuCre", query = "SELECT d FROM DistribucionVisita d WHERE d.usuCre = :usuCre"),
    @NamedQuery(name = "DistribucionVisita.findByFecCre", query = "SELECT d FROM DistribucionVisita d WHERE d.fecCre = :fecCre")})
public class DistribucionVisita implements Serializable {
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
    @Column(name = "nro_hoj")
    private Integer nroHoj;
    @Column(name = "fec_hoj")
    @Temporal(TemporalType.DATE)
    private Date fecHoj;
    @Column(name = "cod_msj")
    private Integer codMsj;
    @Column(name = "nom_msj", length = 60)
    private String nomMsj;
    @Column(name = "ind_sit", length = 2)
    private String indSit;
    @Column(name = "des_sit", length = 50)
    private String desSit;
    @Column(name = "cod_mot", length = 2)
    private String codMot;
    @Column(name = "des_mot", length = 50)
    private String desMot;
    @Column(name = "hor_vis", length = 10)
    private String horVis;
    @Column(name = "tip_dir", length = 2)
    private String tipDir;
    @Column(name = "des_tip", length = 20)
    private String desTip;
    @Column(name = "cod_usu", length = 10)
    private String codUsu;
    @Column(name = "nom_usu", length = 60)
    private String nomUsu;
    @Column(name = "nro_sec", length = 10)
    private String nroSec;
    @Column(name = "usu_cre")
    private Integer usuCre;
    @Column(name = "fec_cre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCre;
    @Column(name = "from_ui", length = 20)
    private String fromUi;
    @Column(name = "descargado", length = 20)
    private String descargado;
    @Column(name = "comentario", length = 500)
    private String comentario;
    @Column(name = "gestion_visita", length = 50)
    private String gestionVisita;
    @Column(name = "resultado_visita", length = 50)
    private String resultadoVisita;
    @Column(name = "lugar_visita", length = 50)
    private String lugarVisita;
    
    public DistribucionVisita() {
    }

    public DistribucionVisita(Integer id) {
        this.id = id;
    }

    public DistribucionVisita(Integer id, int idImport) {
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

    public Integer getNroHoj() {
        return nroHoj;
    }

    public void setNroHoj(Integer nroHoj) {
        this.nroHoj = nroHoj;
    }

    public Date getFecHoj() {
        return fecHoj;
    }

    public void setFecHoj(Date fecHoj) {
        this.fecHoj = fecHoj;
    }

    public Integer getCodMsj() {
        return codMsj;
    }

    public void setCodMsj(Integer codMsj) {
        this.codMsj = codMsj;
    }

    public String getNomMsj() {
        return nomMsj;
    }

    public void setNomMsj(String nomMsj) {
        this.nomMsj = nomMsj;
    }

    public String getIndSit() {
        return indSit;
    }

    public void setIndSit(String indSit) {
        this.indSit = indSit;
    }

    public String getDesSit() {
        return desSit;
    }

    public void setDesSit(String desSit) {
        this.desSit = desSit;
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

    public String getHorVis() {
        return horVis;
    }

    public void setHorVis(String horVis) {
        this.horVis = horVis;
    }

    public String getTipDir() {
        return tipDir;
    }

    public void setTipDir(String tipDir) {
        this.tipDir = tipDir;
    }

    public String getDesTip() {
        return desTip;
    }

    public void setDesTip(String desTip) {
        this.desTip = desTip;
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

    public String getNroSec() {
        return nroSec;
    }

    public void setNroSec(String nroSec) {
        this.nroSec = nroSec;
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
    
    public String getFromUi() {
		return fromUi;
	}

	public void setFromUi(String fromUi) {
		this.fromUi = fromUi;
	}

	public String getDescargado() {
		return descargado;
	}

	public void setDescargado(String descargado) {
		this.descargado = descargado;
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
        if (!(object instanceof DistribucionVisita)) {
            return false;
        }
        DistribucionVisita other = (DistribucionVisita) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.DistribucionVisita[ id=" + id + " ]";
    }

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getGestionVisita() {
		return gestionVisita;
	}

	public void setGestionVisita(String gestionVisita) {
		this.gestionVisita = gestionVisita;
	}

	public String getResultadoVisita() {
		return resultadoVisita;
	}

	public void setResultadoVisita(String resultadoVisita) {
		this.resultadoVisita = resultadoVisita;
	}

	public String getLugarVisita() {
		return lugarVisita;
	}

	public void setLugarVisita(String lugarVisita) {
		this.lugarVisita = lugarVisita;
	}
    
}
