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
@Table(name = "catbancotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catbancotb.findAll", query = "SELECT c FROM Catbancotb c"),
    @NamedQuery(name = "Catbancotb.findByIdbanco", query = "SELECT c FROM Catbancotb c WHERE c.idbanco = :idbanco"),
    @NamedQuery(name = "Catbancotb.findByBanco", query = "SELECT c FROM Catbancotb c WHERE c.banco = :banco"),
    @NamedQuery(name = "Catbancotb.findByNombre", query = "SELECT c FROM Catbancotb c WHERE c.nombre = :nombre")})
public class Catbancotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDBANCO")
    private Integer idbanco;
    @Column(name = "BANCO")
    private String banco;
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idbanco")
    private List<Rhrelacionlaboralcuentatb> rhrelacionlaboralcuentatbList;

    public Catbancotb() {
    }

    public Catbancotb(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public Integer getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Rhrelacionlaboralcuentatb> getRhrelacionlaboralcuentatbList() {
        return rhrelacionlaboralcuentatbList;
    }

    public void setRhrelacionlaboralcuentatbList(List<Rhrelacionlaboralcuentatb> rhrelacionlaboralcuentatbList) {
        this.rhrelacionlaboralcuentatbList = rhrelacionlaboralcuentatbList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbanco != null ? idbanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catbancotb)) {
            return false;
        }
        Catbancotb other = (Catbancotb) object;
        if ((this.idbanco == null && other.idbanco != null) || (this.idbanco != null && !this.idbanco.equals(other.idbanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Catbancotb[ idbanco=" + idbanco + " ]";
    }
    
}
