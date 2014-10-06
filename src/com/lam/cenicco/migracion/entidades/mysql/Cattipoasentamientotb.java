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
@Table(name = "cattipoasentamientotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cattipoasentamientotb.findAll", query = "SELECT c FROM Cattipoasentamientotb c"),
    @NamedQuery(name = "Cattipoasentamientotb.findByIdtipoasentamiento", query = "SELECT c FROM Cattipoasentamientotb c WHERE c.idtipoasentamiento = :idtipoasentamiento"),
    @NamedQuery(name = "Cattipoasentamientotb.findByNombre", query = "SELECT c FROM Cattipoasentamientotb c WHERE c.nombre = :nombre")})
public class Cattipoasentamientotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTIPOASENTAMIENTO")
    private Integer idtipoasentamiento;
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idtipoasentamiento")
    private List<Catasentamientotb> catasentamientotbList;

    public Cattipoasentamientotb() {
    }

    public Cattipoasentamientotb(Integer idtipoasentamiento) {
        this.idtipoasentamiento = idtipoasentamiento;
    }

    public Integer getIdtipoasentamiento() {
        return idtipoasentamiento;
    }

    public void setIdtipoasentamiento(Integer idtipoasentamiento) {
        this.idtipoasentamiento = idtipoasentamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idtipoasentamiento != null ? idtipoasentamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cattipoasentamientotb)) {
            return false;
        }
        Cattipoasentamientotb other = (Cattipoasentamientotb) object;
        if ((this.idtipoasentamiento == null && other.idtipoasentamiento != null) || (this.idtipoasentamiento != null && !this.idtipoasentamiento.equals(other.idtipoasentamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Cattipoasentamientotb[ idtipoasentamiento=" + idtipoasentamiento + " ]";
    }

}
