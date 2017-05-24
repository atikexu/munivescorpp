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
@Table(name = "cliente_agencia", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "ClienteAgencia.findAll", query = "SELECT c FROM ClienteAgencia c"),
    @NamedQuery(name = "ClienteAgencia.findByCodAgencia", query = "SELECT c FROM ClienteAgencia c WHERE c.codAgencia = :codAgencia"),
    @NamedQuery(name = "ClienteAgencia.findByCodCliente", query = "SELECT c FROM ClienteAgencia c WHERE c.codCliente = :codCliente"),
    @NamedQuery(name = "ClienteAgencia.findByTipo", query = "SELECT c FROM ClienteAgencia c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "ClienteAgencia.findByNombre", query = "SELECT c FROM ClienteAgencia c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "ClienteAgencia.findByDireccion", query = "SELECT c FROM ClienteAgencia c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "ClienteAgencia.findByUbigeo", query = "SELECT c FROM ClienteAgencia c WHERE c.ubigeo = :ubigeo"),
    @NamedQuery(name = "ClienteAgencia.findByDepartamento", query = "SELECT c FROM ClienteAgencia c WHERE c.departamento = :departamento"),
    @NamedQuery(name = "ClienteAgencia.findByProvincia", query = "SELECT c FROM ClienteAgencia c WHERE c.provincia = :provincia"),
    @NamedQuery(name = "ClienteAgencia.findByDistrito", query = "SELECT c FROM ClienteAgencia c WHERE c.distrito = :distrito"),
    @NamedQuery(name = "ClienteAgencia.findByCoordenadas", query = "SELECT c FROM ClienteAgencia c WHERE c.coordenadas = :coordenadas"),
    @NamedQuery(name = "ClienteAgencia.findByEstado", query = "SELECT c FROM ClienteAgencia c WHERE c.estado = :estado")})
public class ClienteAgencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cod_agencia", nullable = false, length = 20)
    private String codAgencia;
    @Basic(optional = false)
    @Column(name = "cod_cliente", nullable = false, length = 20)
    private String codCliente;
    @Column(name = "tipo", length = 200)
    private String tipo;
    @Column(name = "nombre", length = 200)
    private String nombre;
    @Column(name = "direccion", length = 250)
    private String direccion;
    @Column(name = "ubigeo", length = 6)
    private String ubigeo;
    @Column(name = "departamento", length = 50)
    private String departamento;
    @Column(name = "provincia", length = 50)
    private String provincia;
    @Column(name = "distrito", length = 50)
    private String distrito;
    @Column(name = "coordenadas", length = 60)
    private String coordenadas;
    @Column(name = "estado")
    private Integer estado;

    public ClienteAgencia() {
    }

    public ClienteAgencia(String codAgencia) {
        this.codAgencia = codAgencia;
    }

    public ClienteAgencia(String codAgencia, String codCliente) {
        this.codAgencia = codAgencia;
        this.codCliente = codCliente;
    }

    public String getCodAgencia() {
        return codAgencia;
    }

    public void setCodAgencia(String codAgencia) {
        this.codAgencia = codAgencia;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
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
        hash += (codAgencia != null ? codAgencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteAgencia)) {
            return false;
        }
        ClienteAgencia other = (ClienteAgencia) object;
        if ((this.codAgencia == null && other.codAgencia != null) || (this.codAgencia != null && !this.codAgencia.equals(other.codAgencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.ClienteAgencia[ codAgencia=" + codAgencia + " ]";
    }
    
}
