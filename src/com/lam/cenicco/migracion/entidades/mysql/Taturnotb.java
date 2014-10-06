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
@Table(name = "taturnotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taturnotb.findAll", query = "SELECT t FROM Taturnotb t"),
    @NamedQuery(name = "Taturnotb.findByIdturno", query = "SELECT t FROM Taturnotb t WHERE t.idturno = :idturno"),
    @NamedQuery(name = "Taturnotb.findByNombre", query = "SELECT t FROM Taturnotb t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Taturnotb.findByTurno", query = "SELECT t FROM Taturnotb t WHERE t.turno = :turno")})
public class Taturnotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTURNO")
    private Integer idturno;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "TURNO")
    private String turno;
    @OneToMany(mappedBy = "idturno")
    private List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbList;
    @JoinColumn(name = "IDCOMPANIA", referencedColumnName = "IDCOMPANIA")
    @ManyToOne
    private Admcompaniatb idcompania;

    public Taturnotb() {
    }

    public Taturnotb(Integer idturno) {
        this.idturno = idturno;
    }

    public Integer getIdturno() {
        return idturno;
    }

    public void setIdturno(Integer idturno) {
        this.idturno = idturno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @XmlTransient
    public List<Rhrelacionlaboralposiciontb> getRhrelacionlaboralposiciontbList() {
        return rhrelacionlaboralposiciontbList;
    }

    public void setRhrelacionlaboralposiciontbList(List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbList) {
        this.rhrelacionlaboralposiciontbList = rhrelacionlaboralposiciontbList;
    }

    public Admcompaniatb getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(Admcompaniatb idcompania) {
        this.idcompania = idcompania;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idturno != null ? idturno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taturnotb)) {
            return false;
        }
        Taturnotb other = (Taturnotb) object;
        if ((this.idturno == null && other.idturno != null) || (this.idturno != null && !this.idturno.equals(other.idturno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Taturnotb[ idturno=" + idturno + " ]";
    }
    
}
