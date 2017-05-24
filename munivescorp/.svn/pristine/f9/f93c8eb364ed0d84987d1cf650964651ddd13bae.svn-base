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
@Table(name = "ubigeo", catalog = "nasqa_values", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Ubigeo.findAll", query = "SELECT u FROM Ubigeo u"),
    @NamedQuery(name = "Ubigeo.findByUbigeo", query = "SELECT u FROM Ubigeo u WHERE u.ubigeo = :ubigeo"),
    @NamedQuery(name = "Ubigeo.findByDepartamento", query = "SELECT u FROM Ubigeo u WHERE u.departamento = :departamento"),
    @NamedQuery(name = "Ubigeo.findByProvincia", query = "SELECT u FROM Ubigeo u WHERE u.provincia = :provincia"),
    @NamedQuery(name = "Ubigeo.findByDistrito", query = "SELECT u FROM Ubigeo u WHERE u.distrito = :distrito"),
    @NamedQuery(name = "Ubigeo.findByCapital", query = "SELECT u FROM Ubigeo u WHERE u.capital = :capital"),
    @NamedQuery(name = "Ubigeo.findByVista", query = "SELECT u FROM Ubigeo u WHERE u.vista = :vista"),
    @NamedQuery(name = "Ubigeo.findByLat", query = "SELECT u FROM Ubigeo u WHERE u.lat = :lat"),
    @NamedQuery(name = "Ubigeo.findByLon", query = "SELECT u FROM Ubigeo u WHERE u.lon = :lon"),
    @NamedQuery(name = "Ubigeo.findByDestino", query = "SELECT u FROM Ubigeo u WHERE u.destino = :destino"),
    @NamedQuery(name = "Ubigeo.findByDane", query = "SELECT u FROM Ubigeo u WHERE u.dane = :dane"),
    @NamedQuery(name = "Ubigeo.findByZip", query = "SELECT u FROM Ubigeo u WHERE u.zip = :zip")})
public class Ubigeo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ubigeo", nullable = false, length = 8)
    private String ubigeo;
    @Column(name = "departamento", length = 100)
    private String departamento;
    @Column(name = "provincia", length = 100)
    private String provincia;
    @Column(name = "distrito", length = 100)
    private String distrito;
    @Column(name = "capital", length = 100)
    private String capital;
    @Column(name = "vista", length = 50)
    private String vista;
    @Column(name = "lat", length = 30)
    private String lat;
    @Column(name = "lon", length = 30)
    private String lon;
    @Column(name = "destino", length = 30)
    private String destino;
    @Column(name = "dane", length = 10)
    private String dane;
    @Column(name = "zip", length = 10)
    private String zip;

    public Ubigeo() {
    }

    public Ubigeo(String ubigeo) {
        this.ubigeo = ubigeo;
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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDane() {
        return dane;
    }

    public void setDane(String dane) {
        this.dane = dane;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ubigeo != null ? ubigeo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubigeo)) {
            return false;
        }
        Ubigeo other = (Ubigeo) object;
        if ((this.ubigeo == null && other.ubigeo != null) || (this.ubigeo != null && !this.ubigeo.equals(other.ubigeo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.nasqa.values.model.entity.Ubigeo[ ubigeo=" + ubigeo + " ]";
    }
    
}
