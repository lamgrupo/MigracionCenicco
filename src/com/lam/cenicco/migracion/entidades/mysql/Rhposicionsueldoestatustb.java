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
@Table(name = "rhposicionsueldoestatustb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rhposicionsueldoestatustb.findAll", query = "SELECT r FROM Rhposicionsueldoestatustb r"),
    @NamedQuery(name = "Rhposicionsueldoestatustb.findByIdposicionsueldoestatus", query = "SELECT r FROM Rhposicionsueldoestatustb r WHERE r.idposicionsueldoestatus = :idposicionsueldoestatus"),
    @NamedQuery(name = "Rhposicionsueldoestatustb.findByFechaact", query = "SELECT r FROM Rhposicionsueldoestatustb r WHERE r.fechaact = :fechaact"),
    @NamedQuery(name = "Rhposicionsueldoestatustb.findByIdusuario", query = "SELECT r FROM Rhposicionsueldoestatustb r WHERE r.idusuario = :idusuario"),
    @NamedQuery(name = "Rhposicionsueldoestatustb.findByIpact", query = "SELECT r FROM Rhposicionsueldoestatustb r WHERE r.ipact = :ipact"),
    @NamedQuery(name = "Rhposicionsueldoestatustb.findByMacact", query = "SELECT r FROM Rhposicionsueldoestatustb r WHERE r.macact = :macact"),
    @NamedQuery(name = "Rhposicionsueldoestatustb.findByObservacion", query = "SELECT r FROM Rhposicionsueldoestatustb r WHERE r.observacion = :observacion")})
public class Rhposicionsueldoestatustb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPOSICIONSUELDOESTATUS")
    private Integer idposicionsueldoestatus;
    @Column(name = "FECHAACT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaact;
    @Column(name = "IDUSUARIO")
    private Integer idusuario;
    @Column(name = "IPACT")
    private String ipact;
    @Column(name = "MACACT")
    private String macact;
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinColumn(name = "IDPOSICIONSUELDO", referencedColumnName = "IDPOSICIONSUELDO")
    @ManyToOne
    private Rhposicionsueldotb idposicionsueldo;

    public Rhposicionsueldoestatustb() {
    }

    public Rhposicionsueldoestatustb(Integer idposicionsueldoestatus) {
        this.idposicionsueldoestatus = idposicionsueldoestatus;
    }

    public Integer getIdposicionsueldoestatus() {
        return idposicionsueldoestatus;
    }

    public void setIdposicionsueldoestatus(Integer idposicionsueldoestatus) {
        this.idposicionsueldoestatus = idposicionsueldoestatus;
    }

    public Date getFechaact() {
        return fechaact;
    }

    public void setFechaact(Date fechaact) {
        this.fechaact = fechaact;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getIpact() {
        return ipact;
    }

    public void setIpact(String ipact) {
        this.ipact = ipact;
    }

    public String getMacact() {
        return macact;
    }

    public void setMacact(String macact) {
        this.macact = macact;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Rhposicionsueldotb getIdposicionsueldo() {
        return idposicionsueldo;
    }

    public void setIdposicionsueldo(Rhposicionsueldotb idposicionsueldo) {
        this.idposicionsueldo = idposicionsueldo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idposicionsueldoestatus != null ? idposicionsueldoestatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rhposicionsueldoestatustb)) {
            return false;
        }
        Rhposicionsueldoestatustb other = (Rhposicionsueldoestatustb) object;
        if ((this.idposicionsueldoestatus == null && other.idposicionsueldoestatus != null) || (this.idposicionsueldoestatus != null && !this.idposicionsueldoestatus.equals(other.idposicionsueldoestatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Rhposicionsueldoestatustb[ idposicionsueldoestatus=" + idposicionsueldoestatus + " ]";
    }
    
}
