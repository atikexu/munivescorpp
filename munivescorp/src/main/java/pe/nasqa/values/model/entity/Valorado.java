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
@Table(name = "valorado", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Valorado.findAll", query = "SELECT v FROM Valorado v"),
    @NamedQuery(name = "Valorado.findById", query = "SELECT v FROM Valorado v WHERE v.id = :id"),
    @NamedQuery(name = "Valorado.findByCodTranProd", query = "SELECT v FROM Valorado v WHERE v.codTranProd = :codTranProd"),
    @NamedQuery(name = "Valorado.findByCodTranSubprod", query = "SELECT v FROM Valorado v WHERE v.codTranSubprod = :codTranSubprod"),
    @NamedQuery(name = "Valorado.findByNombre", query = "SELECT v FROM Valorado v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "Valorado.findByDescripcion", query = "SELECT v FROM Valorado v WHERE v.descripcion = :descripcion"),
    @NamedQuery(name = "Valorado.findByEstado", query = "SELECT v FROM Valorado v WHERE v.estado = :estado"),
    @NamedQuery(name = "Valorado.findByFecCrea", query = "SELECT v FROM Valorado v WHERE v.fecCrea = :fecCrea"),
    @NamedQuery(name = "Valorado.findByFecMod", query = "SELECT v FROM Valorado v WHERE v.fecMod = :fecMod"),
    @NamedQuery(name = "Valorado.findByCodProd", query = "SELECT v FROM Valorado v WHERE v.codProd = :codProd"),
	@NamedQuery(name = "Valorado.findByRuta", query = "SELECT v FROM Valorado v WHERE v.ruta = :ruta"),
	@NamedQuery(name = "Valorado.findByActivo", query = "SELECT v FROM Valorado v WHERE v.activo = :activo")		
	 })
public class Valorado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "cod_tran_prod", length = 20)
    private String codTranProd;
    @Column(name = "cod_tran_subprod", length = 20)
    private String codTranSubprod;
    @Column(name = "nombre", length = 150)
    private String nombre;
    @Column(name = "descripcion", length = 250)
    private String descripcion;
    @Column(name = "estado", length = 2)
    private String estado;
    @Column(name = "fec_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCrea;
    @Column(name = "fec_mod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecMod;
    
  //-------------------------------------------------------------------------- CP
  	@Column(name = "cod_prod", length = 30)
  	private String codProd;
  	
  	@Column(name = "ruta", length = 255)
  	private String ruta;

  	@Column(name = "activo", length =2)
  	private String activo;
    
    @ManyToMany(mappedBy = "valoradoSet", fetch = FetchType.EAGER)
    
    
    private Set<Usuario> usuarioSet;
    @JoinColumn(name = "cod_tipo", referencedColumnName = "cod_tipo")
    @ManyToOne(fetch = FetchType.EAGER)
    private ValoradoTipo codTipo;
    @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente")
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente codCliente;

    public Valorado() {
    }

    public Valorado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodTranProd() {
        return codTranProd;
    }

    public void setCodTranProd(String codTranProd) {
        this.codTranProd = codTranProd;
    }

    public String getCodTranSubprod() {
        return codTranSubprod;
    }

    public void setCodTranSubprod(String codTranSubprod) {
        this.codTranSubprod = codTranSubprod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecCrea() {
        return fecCrea;
    }

    public void setFecCrea(Date fecCrea) {
        this.fecCrea = fecCrea;
    }

    public Date getFecMod() {
        return fecMod;
    }

    public void setFecMod(Date fecMod) {
        this.fecMod = fecMod;
    }


//  --------------------------------------------------  CP
	public String getCodProd() {
		return codProd;
	}

	public void setCodProd(String codProd) {
		this.codProd = codProd;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}


	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	//----------------------------------------------------------

    
    public Set<Usuario> getUsuarioSet() {
        return usuarioSet;
    }

    public void setUsuarioSet(Set<Usuario> usuarioSet) {
        this.usuarioSet = usuarioSet;
    }

    public ValoradoTipo getCodTipo() {
        return codTipo;
    }

    public void setCodTipo(ValoradoTipo codTipo) {
        this.codTipo = codTipo;
    }

    public Cliente getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Cliente codCliente) {
        this.codCliente = codCliente;
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
        if (!(object instanceof Valorado)) {
            return false;
        }
        Valorado other = (Valorado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.Valorado[ id=" + id + " ]";
    }
    
}
