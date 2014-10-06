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
public class AsentamientoOraclePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDASENTAMIENTO")
    private long idasentamiento;
    @Basic(optional = false)
    @Column(name = "IDPAIS")
    private long idpais;
    @Basic(optional = false)
    @Column(name = "IDESTADO")
    private long idestado;
    @Basic(optional = false)
    @Column(name = "IDMUNICIPIO")
    private long idmunicipio;
    @Basic(optional = false)
    @Column(name = "IDCIUDAD")
    private long idciudad;
    @Basic(optional = false)
    @Column(name = "CODIGOPOSTAL")
    private long codigopostal;

    public AsentamientoOraclePK() {
    }

    public AsentamientoOraclePK(long idasentamiento, long idpais, long idestado, long idmunicipio, long idciudad, long codigopostal) {
        this.idasentamiento = idasentamiento;
        this.idpais = idpais;
        this.idestado = idestado;
        this.idmunicipio = idmunicipio;
        this.idciudad = idciudad;
        this.codigopostal = codigopostal;
    }

    public long getIdasentamiento() {
        return idasentamiento;
    }

    public void setIdasentamiento(long idasentamiento) {
        this.idasentamiento = idasentamiento;
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

    public long getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(long idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    public long getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(long idciudad) {
        this.idciudad = idciudad;
    }

    public long getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(long codigopostal) {
        this.codigopostal = codigopostal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idasentamiento;
        hash += (int) idpais;
        hash += (int) idestado;
        hash += (int) idmunicipio;
        hash += (int) idciudad;
        hash += (int) codigopostal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsentamientoOraclePK)) {
            return false;
        }
        AsentamientoOraclePK other = (AsentamientoOraclePK) object;
        if (this.idasentamiento != other.idasentamiento) {
            return false;
        }
        if (this.idpais != other.idpais) {
            return false;
        }
        if (this.idestado != other.idestado) {
            return false;
        }
        if (this.idmunicipio != other.idmunicipio) {
            return false;
        }
        if (this.idciudad != other.idciudad) {
            return false;
        }
        if (this.codigopostal != other.codigopostal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.AsentamientoOraclePK[ idasentamiento=" + idasentamiento + ", idpais=" + idpais + ", idestado=" + idestado + ", idmunicipio=" + idmunicipio + ", idciudad=" + idciudad + ", codigopostal=" + codigopostal + " ]";
    }
    
}
