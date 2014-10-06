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
@Table(name = "admcompaniatb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admcompaniatb.findAll", query = "SELECT a FROM Admcompaniatb a"),
    @NamedQuery(name = "Admcompaniatb.findByIdcompania", query = "SELECT a FROM Admcompaniatb a WHERE a.idcompania = :idcompania"),
    @NamedQuery(name = "Admcompaniatb.findByCompania", query = "SELECT a FROM Admcompaniatb a WHERE a.compania = :compania"),
    @NamedQuery(name = "Admcompaniatb.findByEstatus", query = "SELECT a FROM Admcompaniatb a WHERE a.estatus = :estatus"),
    @NamedQuery(name = "Admcompaniatb.findByNombre", query = "SELECT a FROM Admcompaniatb a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Admcompaniatb.findByRfc", query = "SELECT a FROM Admcompaniatb a WHERE a.rfc = :rfc")})
public class Admcompaniatb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCOMPANIA")
    private Integer idcompania;
    @Column(name = "COMPANIA")
    private String compania;
    @Column(name = "ESTATUS")
    private String estatus;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "RFC")
    private String rfc;
    @OneToMany(mappedBy = "idcompania")
    private List<Admregistropatronaltb> admregistropatronaltbList;
    @OneToMany(mappedBy = "idcompania")
    private List<Admdepartamentotb> admdepartamentotbList;
    @OneToMany(mappedBy = "idcompania")
    private List<Nomgrupopagotb> nomgrupopagotbList;
    @OneToMany(mappedBy = "idcompania")
    private List<Rhrelacionlaboraltb> rhrelacionlaboraltbList;
    @OneToMany(mappedBy = "idcompania")
    private List<Admpuestotb> admpuestotbList;
    @OneToMany(mappedBy = "idcompania")
    private List<Taturnotb> taturnotbList;

    public Admcompaniatb() {
    }

    public Admcompaniatb(Integer idcompania) {
        this.idcompania = idcompania;
    }

    public Integer getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(Integer idcompania) {
        this.idcompania = idcompania;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @XmlTransient
    public List<Admregistropatronaltb> getAdmregistropatronaltbList() {
        return admregistropatronaltbList;
    }

    public void setAdmregistropatronaltbList(List<Admregistropatronaltb> admregistropatronaltbList) {
        this.admregistropatronaltbList = admregistropatronaltbList;
    }

    @XmlTransient
    public List<Admdepartamentotb> getAdmdepartamentotbList() {
        return admdepartamentotbList;
    }

    public void setAdmdepartamentotbList(List<Admdepartamentotb> admdepartamentotbList) {
        this.admdepartamentotbList = admdepartamentotbList;
    }

    @XmlTransient
    public List<Nomgrupopagotb> getNomgrupopagotbList() {
        return nomgrupopagotbList;
    }

    public void setNomgrupopagotbList(List<Nomgrupopagotb> nomgrupopagotbList) {
        this.nomgrupopagotbList = nomgrupopagotbList;
    }

    @XmlTransient
    public List<Rhrelacionlaboraltb> getRhrelacionlaboraltbList() {
        return rhrelacionlaboraltbList;
    }

    public void setRhrelacionlaboraltbList(List<Rhrelacionlaboraltb> rhrelacionlaboraltbList) {
        this.rhrelacionlaboraltbList = rhrelacionlaboraltbList;
    }

    @XmlTransient
    public List<Admpuestotb> getAdmpuestotbList() {
        return admpuestotbList;
    }

    public void setAdmpuestotbList(List<Admpuestotb> admpuestotbList) {
        this.admpuestotbList = admpuestotbList;
    }

    @XmlTransient
    public List<Taturnotb> getTaturnotbList() {
        return taturnotbList;
    }

    public void setTaturnotbList(List<Taturnotb> taturnotbList) {
        this.taturnotbList = taturnotbList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompania != null ? idcompania.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admcompaniatb)) {
            return false;
        }
        Admcompaniatb other = (Admcompaniatb) object;
        if ((this.idcompania == null && other.idcompania != null) || (this.idcompania != null && !this.idcompania.equals(other.idcompania))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Admcompaniatb[ idcompania=" + idcompania + " ]";
    }
    
}
