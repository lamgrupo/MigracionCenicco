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
@Table(name = "catasentamientotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catasentamientotb.findAll", query = "SELECT c FROM Catasentamientotb c"),
    @NamedQuery(name = "Catasentamientotb.findByIdasentamiento", query = "SELECT c FROM Catasentamientotb c WHERE c.idasentamiento = :idasentamiento"),
    @NamedQuery(name = "Catasentamientotb.findByAsentamiento", query = "SELECT c FROM Catasentamientotb c WHERE c.asentamiento = :asentamiento"),
    @NamedQuery(name = "Catasentamientotb.findByCodigopostal", query = "SELECT c FROM Catasentamientotb c WHERE c.codigopostal = :codigopostal")})
public class Catasentamientotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDASENTAMIENTO")
    private Integer idasentamiento;
    @Column(name = "ASENTAMIENTO")
    private String asentamiento;
    @Column(name = "CODIGOPOSTAL")
    private String codigopostal;
    @JoinColumn(name = "IDCIUDAD", referencedColumnName = "IDCIUDAD")
    @ManyToOne
    private Catciudadtb idciudad;
    @JoinColumn(name = "IDTIPOASENTAMIENTO", referencedColumnName = "IDTIPOASENTAMIENTO")
    @ManyToOne
    private Cattipoasentamientotb idtipoasentamiento;
    @OneToMany(mappedBy = "idasentamiento")
    private List<Rhempleadodomiciliotb> rhempleadodomiciliotbList;

    public Catasentamientotb() {
    }

    public Catasentamientotb(Integer idasentamiento) {
        this.idasentamiento = idasentamiento;
    }

    public Integer getIdasentamiento() {
        return idasentamiento;
    }

    public void setIdasentamiento(Integer idasentamiento) {
        this.idasentamiento = idasentamiento;
    }

    public String getAsentamiento() {
        return asentamiento;
    }

    public void setAsentamiento(String asentamiento) {
        this.asentamiento = asentamiento;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    public Catciudadtb getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(Catciudadtb idciudad) {
        this.idciudad = idciudad;
    }

    public Cattipoasentamientotb getIdtipoasentamiento() {
        return idtipoasentamiento;
    }

    public void setIdtipoasentamiento(Cattipoasentamientotb idtipoasentamiento) {
        this.idtipoasentamiento = idtipoasentamiento;
    }

    @XmlTransient
    public List<Rhempleadodomiciliotb> getRhempleadodomiciliotbList() {
        return rhempleadodomiciliotbList;
    }

    public void setRhempleadodomiciliotbList(List<Rhempleadodomiciliotb> rhempleadodomiciliotbList) {
        this.rhempleadodomiciliotbList = rhempleadodomiciliotbList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idasentamiento != null ? idasentamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catasentamientotb)) {
            return false;
        }
        Catasentamientotb other = (Catasentamientotb) object;
        if ((this.idasentamiento == null && other.idasentamiento != null) || (this.idasentamiento != null && !this.idasentamiento.equals(other.idasentamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Catasentamientotb[ idasentamiento=" + idasentamiento + " ]";
    }
    
}
