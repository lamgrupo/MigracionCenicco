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
@Table(name = "rhtiporelacionlaboraltb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rhtiporelacionlaboraltb.findAll", query = "SELECT r FROM Rhtiporelacionlaboraltb r"),
    @NamedQuery(name = "Rhtiporelacionlaboraltb.findByIdtiporelacionlaboral", query = "SELECT r FROM Rhtiporelacionlaboraltb r WHERE r.idtiporelacionlaboral = :idtiporelacionlaboral"),
    @NamedQuery(name = "Rhtiporelacionlaboraltb.findByNombre", query = "SELECT r FROM Rhtiporelacionlaboraltb r WHERE r.nombre = :nombre")})
public class Rhtiporelacionlaboraltb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTIPORELACIONLABORAL")
    private Integer idtiporelacionlaboral;
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idtiporellab")
    private List<Rhrelacionlaboraltb> rhrelacionlaboraltbList;

    public Rhtiporelacionlaboraltb() {
    }

    public Rhtiporelacionlaboraltb(Integer idtiporelacionlaboral) {
        this.idtiporelacionlaboral = idtiporelacionlaboral;
    }

    public Integer getIdtiporelacionlaboral() {
        return idtiporelacionlaboral;
    }

    public void setIdtiporelacionlaboral(Integer idtiporelacionlaboral) {
        this.idtiporelacionlaboral = idtiporelacionlaboral;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Rhrelacionlaboraltb> getRhrelacionlaboraltbList() {
        return rhrelacionlaboraltbList;
    }

    public void setRhrelacionlaboraltbList(List<Rhrelacionlaboraltb> rhrelacionlaboraltbList) {
        this.rhrelacionlaboraltbList = rhrelacionlaboraltbList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtiporelacionlaboral != null ? idtiporelacionlaboral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rhtiporelacionlaboraltb)) {
            return false;
        }
        Rhtiporelacionlaboraltb other = (Rhtiporelacionlaboraltb) object;
        if ((this.idtiporelacionlaboral == null && other.idtiporelacionlaboral != null) || (this.idtiporelacionlaboral != null && !this.idtiporelacionlaboral.equals(other.idtiporelacionlaboral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Rhtiporelacionlaboraltb[ idtiporelacionlaboral=" + idtiporelacionlaboral + " ]";
    }
    
}
