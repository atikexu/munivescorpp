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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "datobase_log", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "DatoBaseLog.findAll", query = "SELECT v FROM DatoBaseLog v"),
    @NamedQuery(name = "DatoBaseLog.findByCodProd", query = "SELECT v FROM DatoBaseLog v WHERE v.codProd = :codProd"),
    @NamedQuery(name = "DatoBaseLog.findByNomArchivo", query = "SELECT v FROM DatoBaseLog v WHERE v.nomArchivo = :nomArchivo"),
    @NamedQuery(name = "DatoBaseLog.findByEstado", query = "SELECT v FROM DatoBaseLog v WHERE v.estado = :estado"),
    @NamedQuery(name = "DatoBaseLog.findByUsuario", query = "SELECT v FROM DatoBaseLog v WHERE v.usuario = :usuario"),
    @NamedQuery(name = "DatoBaseLog.findByFecCarga", query = "SELECT v FROM DatoBaseLog v WHERE v.fecCarga = :fecCarga"),
    @NamedQuery(name = "DatoBaseLog.findByFecMod", query = "SELECT v FROM DatoBaseLog v WHERE v.fecMod = :fecMod"),
    @NamedQuery(name = "DatoBaseLog.findById", query = "SELECT v FROM DatoBaseLog v WHERE v.id = :id")
     })
public class DatoBaseLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Column(name = "cod_prod", length = 30)
  	private String codProd;
  	
    @Column(name = "nom_archivo", length = 255)
  	private String nomArchivo;
  	
    
    @Column(name = "estado", length = 255)
  	private String estado;
    
    @Column(name = "usuario", length = 255)
  	private String usuario;
  	
  	@Column(name = "fec_carga")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCarga;
    
  	@Column(name = "fec_mod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecMod;
  	
  	    
   // @ManyToMany(mappedBy = "valoradoSet", fetch = FetchType.EAGER)
    // private Set<Usuario> usuarioSet;
    
   
   
   
  	
  	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodProd() {
		return codProd;
	}

	public void setCodProd(String codProd) {
		this.codProd = codProd;
	}

	public String getNomArchivo() {
		return nomArchivo;
	}

	public void setNomArchivo(String nomArchivo) {
		this.nomArchivo = nomArchivo;
	}

	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecCarga() {
		return fecCarga;
	}

	public void setFecCarga(Date fecCarga) {
		this.fecCarga = fecCarga;
	}

	public Date getFecMod() {
		return fecMod;
	}

	public void setFecMod(Date fecMod) {
		this.fecMod = fecMod;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatoBaseLog)) {
            return false;
        }
        DatoBaseLog other = (DatoBaseLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.DatoBaseLog[ id=" + id + " ]";
    }
    
}
