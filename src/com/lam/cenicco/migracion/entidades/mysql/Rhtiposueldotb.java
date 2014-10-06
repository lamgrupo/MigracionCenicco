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
@Table(name = "rhtiposueldotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rhtiposueldotb.findAll", query = "SELECT r FROM Rhtiposueldotb r"),
    @NamedQuery(name = "Rhtiposueldotb.findByIdtiposueldo", query = "SELECT r FROM Rhtiposueldotb r WHERE r.idtiposueldo = :idtiposueldo"),
    @NamedQuery(name = "Rhtiposueldotb.findByNombre", query = "SELECT r FROM Rhtiposueldotb r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Rhtiposueldotb.findByPeriodicidad", query = "SELECT r FROM Rhtiposueldotb r WHERE r.periodicidad = :periodicidad"),
    @NamedQuery(name = "Rhtiposueldotb.findByTiposueldo", query = "SELECT r FROM Rhtiposueldotb r WHERE r.tiposueldo = :tiposueldo")})
public class Rhtiposueldotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTIPOSUELDO")
    private Integer idtiposueldo;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "PERIODICIDAD")
    private Integer periodicidad;
    @Column(name = "TIPOSUELDO")
    private String tiposueldo;
    @OneToMany(mappedBy = "idtiposueldo")
    private List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbList;
    @OneToMany(mappedBy = "idtiposueldo")
    private List<Rhposicionsueldotb> rhposicionsueldotbList;
    @OneToMany(mappedBy = "idtiposueldo")
    private List<Rhposicionsueldohtb> rhposicionsueldohtbList;

    public Rhtiposueldotb() {
    }

    public Rhtiposueldotb(Integer idtiposueldo) {
        this.idtiposueldo = idtiposueldo;
    }

    public Integer getIdtiposueldo() {
        return idtiposueldo;
    }

    public void setIdtiposueldo(Integer idtiposueldo) {
        this.idtiposueldo = idtiposueldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Integer periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getTiposueldo() {
        return tiposueldo;
    }

    public void setTiposueldo(String tiposueldo) {
        this.tiposueldo = tiposueldo;
    }

    @XmlTransient
    public List<Rhrelacionlaboralposiciontb> getRhrelacionlaboralposiciontbList() {
        return rhrelacionlaboralposiciontbList;
    }

    public void setRhrelacionlaboralposiciontbList(List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbList) {
        this.rhrelacionlaboralposiciontbList = rhrelacionlaboralposiciontbList;
    }

    @XmlTransient
    public List<Rhposicionsueldotb> getRhposicionsueldotbList() {
        return rhposicionsueldotbList;
    }

    public void setRhposicionsueldotbList(List<Rhposicionsueldotb> rhposicionsueldotbList) {
        this.rhposicionsueldotbList = rhposicionsueldotbList;
    }

    @XmlTransient
    public List<Rhposicionsueldohtb> getRhposicionsueldohtbList() {
        return rhposicionsueldohtbList;
    }

    public void setRhposicionsueldohtbList(List<Rhposicionsueldohtb> rhposicionsueldohtbList) {
        this.rhposicionsueldohtbList = rhposicionsueldohtbList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtiposueldo != null ? idtiposueldo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rhtiposueldotb)) {
            return false;
        }
        Rhtiposueldotb other = (Rhtiposueldotb) object;
        if ((this.idtiposueldo == null && other.idtiposueldo != null) || (this.idtiposueldo != null && !this.idtiposueldo.equals(other.idtiposueldo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Rhtiposueldotb[ idtiposueldo=" + idtiposueldo + " ]";
    }
    
}
