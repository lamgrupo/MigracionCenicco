/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.oracle;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JoséAntonio
 */
@Embeddable
public class PosicionSueldoOraclePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDRELLAB")
    private long idrellab;
    @Basic(optional = false)
    @Column(name = "SEC")
    private int sec;
    @Basic(optional = false)
    @Column(name = "TIPOSUELDO")
    private String tiposueldo;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public PosicionSueldoOraclePK() {
    }

    public PosicionSueldoOraclePK(long idrellab, int sec, String tiposueldo, Date fecha) {
        this.idrellab = idrellab;
        this.sec = sec;
        this.tiposueldo = tiposueldo;
        this.fecha = fecha;
    }

    public long getIdrellab() {
        return idrellab;
    }

    public void setIdrellab(long idrellab) {
        this.idrellab = idrellab;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public String getTiposueldo() {
        return tiposueldo;
    }

    public void setTiposueldo(String tiposueldo) {
        this.tiposueldo = tiposueldo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idrellab;
        hash += (int) sec;
        hash += (tiposueldo != null ? tiposueldo.hashCode() : 0);
        hash += (fecha != null ? fecha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosicionSueldoOraclePK)) {
            return false;
        }
        PosicionSueldoOraclePK other = (PosicionSueldoOraclePK) object;
        if (this.idrellab != other.idrellab) {
            return false;
        }
        if (this.sec != other.sec) {
            return false;
        }
        if ((this.tiposueldo == null && other.tiposueldo != null) || (this.tiposueldo != null && !this.tiposueldo.equals(other.tiposueldo))) {
            return false;
        }
        if ((this.fecha == null && other.fecha != null) || (this.fecha != null && !this.fecha.equals(other.fecha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.PosicionSueldoOraclePK[ idrellab=" + idrellab + ", sec=" + sec + ", tiposueldo=" + tiposueldo + ", fecha=" + fecha + " ]";
    }
    
}
