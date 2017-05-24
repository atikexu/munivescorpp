/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.nasqa.values.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author 
 */
@Entity
@Table(name = "mensajero", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Mensajero.findAll", query = "SELECT m FROM Mensajero m"),
    @NamedQuery(name = "Mensajero.findById", query = "SELECT m FROM Mensajero m WHERE m.id_mensajero = :id_mensajero"),
    @NamedQuery(name = "Mensajero.findByCodigo", query = "SELECT m FROM Mensajero m WHERE m.cod_mensajero = :cod_mensajero"),
    @NamedQuery(name = "Mensajero.findByNombres", query = "SELECT m FROM Mensajero m WHERE m.nombres = :nombres"),
    @NamedQuery(name = "Mensajero.findByApellidoPat", query = "SELECT m FROM Mensajero m WHERE m.apellido_pat = :apellido_pat"),
    @NamedQuery(name = "Mensajero.findByApellidoMat", query = "SELECT m FROM Mensajero m WHERE m.apellido_mat = :apellido_mat"),
    @NamedQuery(name = "Mensajero.findByDni", query = "SELECT m FROM Mensajero m WHERE m.dni = :dni"),
    @NamedQuery(name = "Mensajero.findByCorreo", query = "SELECT m FROM Mensajero m WHERE m.correo = :correo"),
    @NamedQuery(name = "Mensajero.findByTelefono", query = "SELECT m FROM Mensajero m WHERE m.telefono = :telefono"),
    @NamedQuery(name = "Mensajero.findByEmpresa", query = "SELECT m FROM Mensajero m WHERE m.empresa = :empresa"),
    @NamedQuery(name = "Mensajero.findByEstado", query = "SELECT m FROM Mensajero m WHERE m.estado = :estado")})
public class Mensajero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_mensajero", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_mensajero;

    @Column(name = "cod_mensajero", length = 15)
    private String cod_mensajero;
    @Column(name = "nombres", length = 50)
    private String nombres;
    @Column(name = "apellido_pat", length = 50)
    private String apellido_pat;
    @Column(name = "apellido_mat", length = 50)
    private String apellido_mat;
    @Column(name = "dni", length = 8)
    private String dni;
    @Column(name = "correo", length = 50)
    private String correo;
    @Column(name = "telefono", length = 10)
    private String telefono;
    @Column(name = "empresa", length = 50)
    private String empresa;
    @Column(name = "estado")
    private Integer estado;

    public Mensajero() {
    }

    public Mensajero(Integer id_mensajero) {
        this.id_mensajero = id_mensajero;
    }

    public Integer getIdMensajero() {
        return id_mensajero;
    }

    public void setIdMensajero(Integer id_mensajero) {
        this.id_mensajero = id_mensajero;
    }

    public String getCodMensajero() {
        return cod_mensajero;
    }

    public void setCodMensajero(String cod_mensajero) {
        this.cod_mensajero = cod_mensajero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPat() {
        return apellido_pat;
    }

    public void setApellidoPat(String apellido_pat) {
        this.apellido_pat = apellido_pat;
    }
    
    public String getApellidoMat() {
        return apellido_mat;
    }

    public void setApellidoMat(String apellido_mat) {
        this.apellido_mat = apellido_mat;
    }
    
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id_mensajero != null ? id_mensajero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensajero)) {
            return false;
        }
        Mensajero other = (Mensajero) object;
        if ((this.id_mensajero == null && other.id_mensajero != null) || (this.id_mensajero != null && !this.id_mensajero.equals(other.id_mensajero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.Mensajero[ id=" + id_mensajero + " ]";
    }
    
}
