/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.mysql;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JoséAntonio
 */
@Entity
@Table(name = "rhposicionsueldotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rhposicionsueldotb.findAll", query = "SELECT r FROM Rhposicionsueldotb r"),
    @NamedQuery(name = "Rhposicionsueldotb.findByIdposicionsueldo", query = "SELECT r FROM Rhposicionsueldotb r WHERE r.idposicionsueldo = :idposicionsueldo"),
    @NamedQuery(name = "Rhposicionsueldotb.findByEstatus", query = "SELECT r FROM Rhposicionsueldotb r WHERE r.estatus = :estatus"),
    @NamedQuery(name = "Rhposicionsueldotb.findByFechafin", query = "SELECT r FROM Rhposicionsueldotb r WHERE r.fechafin = :fechafin"),
    @NamedQuery(name = "Rhposicionsueldotb.findByFechainicio", query = "SELECT r FROM Rhposicionsueldotb r WHERE r.fechainicio = :fechainicio"),
    @NamedQuery(name = "Rhposicionsueldotb.findBySueldo", query = "SELECT r FROM Rhposicionsueldotb r WHERE r.sueldo = :sueldo")})
public class Rhposicionsueldotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPOSICIONSUELDO")
    private Integer idposicionsueldo;
    @Column(name = "ESTATUS")
    private String estatus;
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "SUELDO")
    private BigInteger sueldo;
    @OneToMany(mappedBy = "idposicionsueldo")
    private List<Rhposicionsueldoestatustb> rhposicionsueldoestatustbList;
    @OneToMany(mappedBy = "idposicionsueldo")
    private List<Rhrelacionlaboraltb> rhrelacionlaboraltbList;
    @JoinColumn(name = "IDTIPOSUELDO", referencedColumnName = "IDTIPOSUELDO")
    @ManyToOne
    private Rhtiposueldotb idtiposueldo;

    public Rhposicionsueldotb() {
    }

    public Rhposicionsueldotb(Integer idposicionsueldo) {
        this.idposicionsueldo = idposicionsueldo;
    }

    public Integer getIdposicionsueldo() {
        return idposicionsueldo;
    }

    public void setIdposicionsueldo(Integer idposicionsueldo) {
        this.idposicionsueldo = idposicionsueldo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public BigInteger getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigInteger sueldo) {
        this.sueldo = sueldo;
    }

    @XmlTransient
    public List<Rhposicionsueldoestatustb> getRhposicionsueldoestatustbList() {
        return rhposicionsueldoestatustbList;
    }

    public void setRhposicionsueldoestatustbList(List<Rhposicionsueldoestatustb> rhposicionsueldoestatustbList) {
        this.rhposicionsueldoestatustbList = rhposicionsueldoestatustbList;
    }

    @XmlTransient
    public List<Rhrelacionlaboraltb> getRhrelacionlaboraltbList() {
        return rhrelacionlaboraltbList;
    }

    public void setRhrelacionlaboraltbList(List<Rhrelacionlaboraltb> rhrelacionlaboraltbList) {
        this.rhrelacionlaboraltbList = rhrelacionlaboraltbList;
    }

    public Rhtiposueldotb getIdtiposueldo() {
        return idtiposueldo;
    }

    public void setIdtiposueldo(Rhtiposueldotb idtiposueldo) {
        this.idtiposueldo = idtiposueldo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idposicionsueldo != null ? idposicionsueldo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rhposicionsueldotb)) {
            return false;
        }
        Rhposicionsueldotb other = (Rhposicionsueldotb) object;
        if ((this.idposicionsueldo == null && other.idposicionsueldo != null) || (this.idposicionsueldo != null && !this.idposicionsueldo.equals(other.idposicionsueldo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Rhposicionsueldotb[ idposicionsueldo=" + idposicionsueldo + " ]";
    }
    
}
