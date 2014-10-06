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
@Table(name = "catpaistb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catpaistb.findAll", query = "SELECT c FROM Catpaistb c"),
    @NamedQuery(name = "Catpaistb.findByIdpais", query = "SELECT c FROM Catpaistb c WHERE c.idpais = :idpais"),
    @NamedQuery(name = "Catpaistb.findByNombre", query = "SELECT c FROM Catpaistb c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Catpaistb.findByPais", query = "SELECT c FROM Catpaistb c WHERE c.pais = :pais")})
public class Catpaistb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPAIS")
    private Integer idpais;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "PAIS")
    private String pais;
    @OneToMany(mappedBy = "idpais")
    private List<Catestadotb> catestadotbList;

    public Catpaistb() {
    }

    public Catpaistb(Integer idpais) {
        this.idpais = idpais;
    }

    public Integer getIdpais() {
        return idpais;
    }

    public void setIdpais(Integer idpais) {
        this.idpais = idpais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @XmlTransient
    public List<Catestadotb> getCatestadotbList() {
        return catestadotbList;
    }

    public void setCatestadotbList(List<Catestadotb> catestadotbList) {
        this.catestadotbList = catestadotbList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpais != null ? idpais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catpaistb)) {
            return false;
        }
        Catpaistb other = (Catpaistb) object;
        if ((this.idpais == null && other.idpais != null) || (this.idpais != null && !this.idpais.equals(other.idpais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Catpaistb[ idpais=" + idpais + " ]";
    }
    
}
