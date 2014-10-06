/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.mysql;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JoséAntonio
 */
@Entity
@Table(name = "rhposicionsueldohtb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rhposicionsueldohtb.findAll", query = "SELECT r FROM Rhposicionsueldohtb r"),
    @NamedQuery(name = "Rhposicionsueldohtb.findByIdposicionsueldo", query = "SELECT r FROM Rhposicionsueldohtb r WHERE r.idposicionsueldo = :idposicionsueldo"),
    @NamedQuery(name = "Rhposicionsueldohtb.findByFechainicio", query = "SELECT r FROM Rhposicionsueldohtb r WHERE r.fechainicio = :fechainicio"),
    @NamedQuery(name = "Rhposicionsueldohtb.findByFechafin", query = "SELECT r FROM Rhposicionsueldohtb r WHERE r.fechafin = :fechafin"),
    @NamedQuery(name = "Rhposicionsueldohtb.findBySueldo", query = "SELECT r FROM Rhposicionsueldohtb r WHERE r.sueldo = :sueldo")})
public class Rhposicionsueldohtb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPOSICIONSUELDO")
    private Integer idposicionsueldo;
    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SUELDO")
    private Double sueldo;
    @JoinColumn(name = "IDTIPOSUELDO", referencedColumnName = "IDTIPOSUELDO")
    @ManyToOne
    private Rhtiposueldotb idtiposueldo;
    @JoinColumn(name = "IDRELLAB", referencedColumnName = "IDRELLAB")
    @ManyToOne
    private Rhrelacionlaboraltb idrellab;

    public Rhposicionsueldohtb() {
    }

    public Rhposicionsueldohtb(Integer idposicionsueldo) {
        this.idposicionsueldo = idposicionsueldo;
    }

    public Integer getIdposicionsueldo() {
        return idposicionsueldo;
    }

    public void setIdposicionsueldo(Integer idposicionsueldo) {
        this.idposicionsueldo = idposicionsueldo;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Rhtiposueldotb getIdtiposueldo() {
        return idtiposueldo;
    }

    public void setIdtiposueldo(Rhtiposueldotb idtiposueldo) {
        this.idtiposueldo = idtiposueldo;
    }

    public Rhrelacionlaboraltb getIdrellab() {
        return idrellab;
    }

    public void setIdrellab(Rhrelacionlaboraltb idrellab) {
        this.idrellab = idrellab;
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
        if (!(object instanceof Rhposicionsueldohtb)) {
            return false;
        }
        Rhposicionsueldohtb other = (Rhposicionsueldohtb) object;
        if ((this.idposicionsueldo == null && other.idposicionsueldo != null) || (this.idposicionsueldo != null && !this.idposicionsueldo.equals(other.idposicionsueldo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Rhposicionsueldohtb[ idposicionsueldo=" + idposicionsueldo + " ]";
    }
    
}
