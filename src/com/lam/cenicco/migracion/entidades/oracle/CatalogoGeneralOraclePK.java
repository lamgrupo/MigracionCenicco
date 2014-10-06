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
public class CatalogoGeneralOraclePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "TABLA")
    private String tabla;
    @Basic(optional = false)
    @Column(name = "COLUMNA")
    private String columna;
    @Basic(optional = false)
    @Column(name = "VALOR")
    private String valor;

    public CatalogoGeneralOraclePK() {
    }

    public CatalogoGeneralOraclePK(String tabla, String columna, String valor) {
        this.tabla = tabla;
        this.columna = columna;
        this.valor = valor;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tabla != null ? tabla.hashCode() : 0);
        hash += (columna != null ? columna.hashCode() : 0);
        hash += (valor != null ? valor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoGeneralOraclePK)) {
            return false;
        }
        CatalogoGeneralOraclePK other = (CatalogoGeneralOraclePK) object;
        if ((this.tabla == null && other.tabla != null) || (this.tabla != null && !this.tabla.equals(other.tabla))) {
            return false;
        }
        if ((this.columna == null && other.columna != null) || (this.columna != null && !this.columna.equals(other.columna))) {
            return false;
        }
        if ((this.valor == null && other.valor != null) || (this.valor != null && !this.valor.equals(other.valor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.CatalogoGeneralOraclePK[ tabla=" + tabla + ", columna=" + columna + ", valor=" + valor + " ]";
    }
    
}
