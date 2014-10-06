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
public class MunicipioOraclePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDMUNICIPIO")
    private long idmunicipio;
    @Basic(optional = false)
    @Column(name = "IDPAIS")
    private long idpais;
    @Basic(optional = false)
    @Column(name = "IDESTADO")
    private long idestado;

    public MunicipioOraclePK() {
    }

    public MunicipioOraclePK(long idmunicipio, long idpais, long idestado) {
        this.idmunicipio = idmunicipio;
        this.idpais = idpais;
        this.idestado = idestado;
    }

    public long getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(long idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public long getIdpais() {
        return idpais;
    }

    public void setIdpais(long idpais) {
        this.idpais = idpais;
    }

    public long getIdestado() {
        return idestado;
    }

    public void setIdestado(long idestado) {
        this.idestado = idestado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idmunicipio;
        hash += (int) idpais;
        hash += (int) idestado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MunicipioOraclePK)) {
            return false;
        }
        MunicipioOraclePK other = (MunicipioOraclePK) object;
        if (this.idmunicipio != other.idmunicipio) {
            return false;
        }
        if (this.idpais != other.idpais) {
            return false;
        }
        if (this.idestado != other.idestado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.MunicipioOraclePK[ idmunicipio=" + idmunicipio + ", idpais=" + idpais + ", idestado=" + idestado + " ]";
    }
    
}
