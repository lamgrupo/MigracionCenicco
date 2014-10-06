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
public class RegistroPatronalOraclePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "IDCOMPANIA")
    private long idcompania;
    @Basic(optional = false)
    @Column(name = "REGISTROPATRONAL")
    private String registropatronal;

    public RegistroPatronalOraclePK() {
    }

    public RegistroPatronalOraclePK(long idcompania, String registropatronal) {
        this.idcompania = idcompania;
        this.registropatronal = registropatronal;
    }

    public long getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(long idcompania) {
        this.idcompania = idcompania;
    }

    public String getRegistropatronal() {
        return registropatronal;
    }

    public void setRegistropatronal(String registropatronal) {
        this.registropatronal = registropatronal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcompania;
        hash += (registropatronal != null ? registropatronal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroPatronalOraclePK)) {
            return false;
        }
        RegistroPatronalOraclePK other = (RegistroPatronalOraclePK) object;
        if (this.idcompania != other.idcompania) {
            return false;
        }
        if ((this.registropatronal == null && other.registropatronal != null) || (this.registropatronal != null && !this.registropatronal.equals(other.registropatronal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.RegistroPatronalOraclePK[ idcompania=" + idcompania + ", registropatronal=" + registropatronal + " ]";
    }
    
}
