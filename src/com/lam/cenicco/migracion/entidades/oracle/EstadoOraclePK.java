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
public class EstadoOraclePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDESTADO")
    private long idestado;
    @Basic(optional = false)
    @Column(name = "IDPAIS")
    private long idpais;

    public EstadoOraclePK() {
    }

    public EstadoOraclePK(long idestado, long idpais) {
        this.idestado = idestado;
        this.idpais = idpais;
    }

    public long getIdestado() {
        return idestado;
    }

    public void setIdestado(long idestado) {
        this.idestado = idestado;
    }

    public long getIdpais() {
        return idpais;
    }

    public void setIdpais(long idpais) {
        this.idpais = idpais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idestado;
        hash += (int) idpais;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoOraclePK)) {
            return false;
        }
        EstadoOraclePK other = (EstadoOraclePK) object;
        if (this.idestado != other.idestado) {
            return false;
        }
        if (this.idpais != other.idpais) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.EstadoOraclePK[ idestado=" + idestado + ", idpais=" + idpais + " ]";
    }
    
}
