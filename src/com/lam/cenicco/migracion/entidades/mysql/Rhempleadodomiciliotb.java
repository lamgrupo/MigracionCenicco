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
@Table(name = "rhempleadodomiciliotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rhempleadodomiciliotb.findAll", query = "SELECT r FROM Rhempleadodomiciliotb r"),
    @NamedQuery(name = "Rhempleadodomiciliotb.findByIddomicilio", query = "SELECT r FROM Rhempleadodomiciliotb r WHERE r.iddomicilio = :iddomicilio"),
    @NamedQuery(name = "Rhempleadodomiciliotb.findByCalle", query = "SELECT r FROM Rhempleadodomiciliotb r WHERE r.calle = :calle"),
    @NamedQuery(name = "Rhempleadodomiciliotb.findByCoordenadax", query = "SELECT r FROM Rhempleadodomiciliotb r WHERE r.coordenadax = :coordenadax"),
    @NamedQuery(name = "Rhempleadodomiciliotb.findByCoordenaday", query = "SELECT r FROM Rhempleadodomiciliotb r WHERE r.coordenaday = :coordenaday"),
    @NamedQuery(name = "Rhempleadodomiciliotb.findByEstatus", query = "SELECT r FROM Rhempleadodomiciliotb r WHERE r.estatus = :estatus"),
    @NamedQuery(name = "Rhempleadodomiciliotb.findByFecha", query = "SELECT r FROM Rhempleadodomiciliotb r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Rhempleadodomiciliotb.findByNumeroexterior", query = "SELECT r FROM Rhempleadodomiciliotb r WHERE r.numeroexterior = :numeroexterior"),
    @NamedQuery(name = "Rhempleadodomiciliotb.findByNumerointerior", query = "SELECT r FROM Rhempleadodomiciliotb r WHERE r.numerointerior = :numerointerior"),
    @NamedQuery(name = "Rhempleadodomiciliotb.findByTipodomicilio", query = "SELECT r FROM Rhempleadodomiciliotb r WHERE r.tipodomicilio = :tipodomicilio")})
public class Rhempleadodomiciliotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDOMICILIO")
    private Integer iddomicilio;
    @Column(name = "CALLE")
    private String calle;
    @Column(name = "COORDENADAX")
    private String coordenadax;
    @Column(name = "COORDENADAY")
    private String coordenaday;
    @Column(name = "ESTATUS")
    private String estatus;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "NUMEROEXTERIOR")
    private String numeroexterior;
    @Column(name = "NUMEROINTERIOR")
    private String numerointerior;
    @Column(name = "TIPODOMICILIO")
    private String tipodomicilio;
    @JoinColumn(name = "IDEMPLEADO", referencedColumnName = "IDEMPLEADO")
    @ManyToOne
    private Rhempleadotb idempleado;
    @JoinColumn(name = "IDASENTAMIENTO", referencedColumnName = "IDASENTAMIENTO")
    @ManyToOne
    private Catasentamientotb idasentamiento;

    public Rhempleadodomiciliotb() {
    }

    public Rhempleadodomiciliotb(Integer iddomicilio) {
        this.iddomicilio = iddomicilio;
    }

    public Integer getIddomicilio() {
        return iddomicilio;
    }

    public void setIddomicilio(Integer iddomicilio) {
        this.iddomicilio = iddomicilio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCoordenadax() {
        return coordenadax;
    }

    public void setCoordenadax(String coordenadax) {
        this.coordenadax = coordenadax;
    }

    public String getCoordenaday() {
        return coordenaday;
    }

    public void setCoordenaday(String coordenaday) {
        this.coordenaday = coordenaday;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumeroexterior() {
        return numeroexterior;
    }

    public void setNumeroexterior(String numeroexterior) {
        this.numeroexterior = numeroexterior;
    }

    public String getNumerointerior() {
        return numerointerior;
    }

    public void setNumerointerior(String numerointerior) {
        this.numerointerior = numerointerior;
    }

    public String getTipodomicilio() {
        return tipodomicilio;
    }

    public void setTipodomicilio(String tipodomicilio) {
        this.tipodomicilio = tipodomicilio;
    }

    public Rhempleadotb getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Rhempleadotb idempleado) {
        this.idempleado = idempleado;
    }

    public Catasentamientotb getIdasentamiento() {
        return idasentamiento;
    }

    public void setIdasentamiento(Catasentamientotb idasentamiento) {
        this.idasentamiento = idasentamiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddomicilio != null ? iddomicilio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rhempleadodomiciliotb)) {
            return false;
        }
        Rhempleadodomiciliotb other = (Rhempleadodomiciliotb) object;
        if ((this.iddomicilio == null && other.iddomicilio != null) || (this.iddomicilio != null && !this.iddomicilio.equals(other.iddomicilio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Rhempleadodomiciliotb[ iddomicilio=" + iddomicilio + " ]";
    }
    
}
