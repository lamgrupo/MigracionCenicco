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
@Table(name = "catciudadtb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catciudadtb.findAll", query = "SELECT c FROM Catciudadtb c"),
    @NamedQuery(name = "Catciudadtb.findByIdciudad", query = "SELECT c FROM Catciudadtb c WHERE c.idciudad = :idciudad"),
    @NamedQuery(name = "Catciudadtb.findByCiudad", query = "SELECT c FROM Catciudadtb c WHERE c.ciudad = :ciudad"),
    @NamedQuery(name = "Catciudadtb.findByNombre", query = "SELECT c FROM Catciudadtb c WHERE c.nombre = :nombre")})
public class Catciudadtb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCIUDAD")
    private Integer idciudad;
    @Column(name = "CIUDAD")
    private String ciudad;
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "IDMUNICIPIO", referencedColumnName = "IDMUNICIPIO")
    @ManyToOne
    private Catmunicipiotb idmunicipio;
    @OneToMany(mappedBy = "idciudad")
    private List<Catasentamientotb> catasentamientotbList;

    public Catciudadtb() {
    }

    public Catciudadtb(Integer idciudad) {
        this.idciudad = idciudad;
    }

    public Integer getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(Integer idciudad) {
        this.idciudad = idciudad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Catmunicipiotb getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Catmunicipiotb idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    @XmlTransient
    public List<Catasentamientotb> getCatasentamientotbList() {
        return catasentamientotbList;
    }

    public void setCatasentamientotbList(List<Catasentamientotb> catasentamientotbList) {
        this.catasentamientotbList = catasentamientotbList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idciudad != null ? idciudad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catciudadtb)) {
            return false;
        }
        Catciudadtb other = (Catciudadtb) object;
        if ((this.idciudad == null && other.idciudad != null) || (this.idciudad != null && !this.idciudad.equals(other.idciudad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Catciudadtb[ idciudad=" + idciudad + " ]";
    }
    
}
