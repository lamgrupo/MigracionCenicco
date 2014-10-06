/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.oracle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "RHPOSICIONESSUELDOSTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PosicionSueldoOracle.findAll", query = "SELECT p FROM PosicionSueldoOracle p ORDER BY p.posicionSueldoOraclePK.fecha DESC"),
    @NamedQuery(name = "PosicionSueldoOracle.findByIdrellab", query = "SELECT p FROM PosicionSueldoOracle p WHERE p.posicionSueldoOraclePK.idrellab = :idrellab"),
    @NamedQuery(name = "PosicionSueldoOracle.findBySec", query = "SELECT p FROM PosicionSueldoOracle p WHERE p.posicionSueldoOraclePK.sec = :sec"),
    @NamedQuery(name = "PosicionSueldoOracle.findByTiposueldo", query = "SELECT p FROM PosicionSueldoOracle p WHERE p.posicionSueldoOraclePK.tiposueldo = :tiposueldo"),
    @NamedQuery(name = "PosicionSueldoOracle.findByFecha", query = "SELECT p FROM PosicionSueldoOracle p WHERE p.posicionSueldoOraclePK.fecha = :fecha"),
    @NamedQuery(name = "PosicionSueldoOracle.findBySueldo", query = "SELECT p FROM PosicionSueldoOracle p WHERE p.sueldo = :sueldo"),
    @NamedQuery(name = "PosicionSueldoOracle.findByEstatus", query = "SELECT p FROM PosicionSueldoOracle p WHERE p.estatus = :estatus"),
    @NamedQuery(name = "PosicionSueldoOracle.findByFechaFin", query = "SELECT p FROM PosicionSueldoOracle p WHERE p.fechaFin = :fechaFin")})
public class PosicionSueldoOracle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PosicionSueldoOraclePK posicionSueldoOraclePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "SUELDO")
    private BigDecimal sueldo;
    @Basic(optional = false)
    @Column(name = "ESTATUS")
    private String estatus;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @JoinColumns({
        @JoinColumn(name = "IDRELLAB", referencedColumnName = "IDRELLAB", insertable = false, updatable = false),
        @JoinColumn(name = "SEC", referencedColumnName = "SEC", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PosicionOracle posicionOracle;

    public PosicionSueldoOracle() {
    }

    public PosicionSueldoOracle(PosicionSueldoOraclePK posicionSueldoOraclePK) {
        this.posicionSueldoOraclePK = posicionSueldoOraclePK;
    }

    public PosicionSueldoOracle(PosicionSueldoOraclePK posicionSueldoOraclePK, BigDecimal sueldo, String estatus) {
        this.posicionSueldoOraclePK = posicionSueldoOraclePK;
        this.sueldo = sueldo;
        this.estatus = estatus;
    }

    public PosicionSueldoOracle(long idrellab, int sec, String tiposueldo, Date fecha) {
        this.posicionSueldoOraclePK = new PosicionSueldoOraclePK(idrellab, sec, tiposueldo, fecha);
    }

    public PosicionSueldoOraclePK getPosicionSueldoOraclePK() {
        return posicionSueldoOraclePK;
    }

    public void setPosicionSueldoOraclePK(PosicionSueldoOraclePK posicionSueldoOraclePK) {
        this.posicionSueldoOraclePK = posicionSueldoOraclePK;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public PosicionOracle getPosicionOracle() {
        return posicionOracle;
    }

    public void setPosicionOracle(PosicionOracle posicionOracle) {
        this.posicionOracle = posicionOracle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (posicionSueldoOraclePK != null ? posicionSueldoOraclePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosicionSueldoOracle)) {
            return false;
        }
        PosicionSueldoOracle other = (PosicionSueldoOracle) object;
        if ((this.posicionSueldoOraclePK == null && other.posicionSueldoOraclePK != null) || (this.posicionSueldoOraclePK != null && !this.posicionSueldoOraclePK.equals(other.posicionSueldoOraclePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.PosicionSueldoOracle[ posicionSueldoOraclePK=" + posicionSueldoOraclePK + " ]";
    }
    
}
