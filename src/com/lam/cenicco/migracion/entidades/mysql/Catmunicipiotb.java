/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.mysql;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JoséAntonio
 */
@Entity
@Table(name = "catmunicipiotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catmunicipiotb.findAll", query = "SELECT c FROM Catmunicipiotb c"),
    @NamedQuery(name = "Catmunicipiotb.findByIdmunicipio", query = "SELECT c FROM Catmunicipiotb c WHERE c.idmunicipio = :idmunicipio"),
    @NamedQuery(name = "Catmunicipiotb.findByMunicipio", query = "SELECT c FROM Catmunicipiotb c WHERE c.municipio = :municipio"),
    @NamedQuery(name = "Catmunicipiotb.findByNombre", query = "SELECT c FROM Catmunicipiotb c WHERE c.nombre = :nombre")})
public class Catmunicipiotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDMUNICIPIO")
    private Integer idmunicipio;
    @Column(name = "MUNICIPIO")
    private String municipio;
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "IDESTADO", referencedColumnName = "IDESTADO")
    @ManyToOne
    private Catestadotb idestado;
    @OneToMany(mappedBy = "idmunicipio")
    private List<Catciudadtb> catciudadtbList;

    public Catmunicipiotb() {
    }

    public Catmunicipiotb(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public Integer getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Catestadotb getIdestado() {
        return idestado;
    }

    public void setIdestado(Catestadotb idestado) {
        this.idestado = idestado;
    }

    @XmlTransient
    public List<Catciudadtb> getCatciudadtbList() {
        return catciudadtbList;
    }

    public void setCatciudadtbList(List<Catciudadtb> catciudadtbList) {
        this.catciudadtbList = catciudadtbList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmunicipio != null ? idmunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catmunicipiotb)) {
            return false;
        }
        Catmunicipiotb other = (Catmunicipiotb) object;
        if ((this.idmunicipio == null && other.idmunicipio != null) || (this.idmunicipio != null && !this.idmunicipio.equals(other.idmunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Catmunicipiotb[ idmunicipio=" + idmunicipio + " ]";
    }
    
}
