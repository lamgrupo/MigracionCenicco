/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.oracle;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author JoséAntonio
 */
@Embeddable
public class PosicionOraclePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDRELLAB")
    private long idrellab;
    @Basic(optional = false)
    @Column(name = "SEC")
    private int sec;

    public PosicionOraclePK() {
    }

    public PosicionOraclePK(long idrellab, int sec) {
        this.idrellab = idrellab;
        this.sec = sec;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idrellab;
        hash += (int) sec;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosicionOraclePK)) {
            return false;
        }
        PosicionOraclePK other = (PosicionOraclePK) object;
        if (this.idrellab != other.idrellab) {
            return false;
        }
        if (this.sec != other.sec) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.PosicionOraclePK[ idrellab=" + idrellab + ", sec=" + sec + " ]";
    }
    
}
