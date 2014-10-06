/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.oracle;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JoséAntonio
 */
@Entity
@Table(name = "CATGENERALESTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatalogoGeneralOracle.findAll", query = "SELECT c FROM CatalogoGeneralOracle c"),
    @NamedQuery(name = "CatalogoGeneralOracle.findByTabla", query = "SELECT c FROM CatalogoGeneralOracle c WHERE c.catalogoGeneralOraclePK.tabla = :tabla"),
    @NamedQuery(name = "CatalogoGeneralOracle.findByColumna", query = "SELECT c FROM CatalogoGeneralOracle c WHERE c.catalogoGeneralOraclePK.columna = :columna"),
    @NamedQuery(name = "CatalogoGeneralOracle.findByValor", query = "SELECT c FROM CatalogoGeneralOracle c WHERE c.catalogoGeneralOraclePK.valor = :valor"),
    @NamedQuery(name = "CatalogoGeneralOracle.findByNombre", query = "SELECT c FROM CatalogoGeneralOracle c WHERE c.catalogoGeneralOraclePK.tabla = :tabla AND c.catalogoGeneralOraclePK.columna = :columna")})
public class CatalogoGeneralOracle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CatalogoGeneralOraclePK catalogoGeneralOraclePK;
    @Column(name = "NOMBRE")
    private String nombre;

    public CatalogoGeneralOracle() {
    }

    public CatalogoGeneralOracle(CatalogoGeneralOraclePK catalogoGeneralOraclePK) {
        this.catalogoGeneralOraclePK = catalogoGeneralOraclePK;
    }

    public CatalogoGeneralOracle(String tabla, String columna, String valor) {
        this.catalogoGeneralOraclePK = new CatalogoGeneralOraclePK(tabla, columna, valor);
    }

    public CatalogoGeneralOraclePK getCatalogoGeneralOraclePK() {
        return catalogoGeneralOraclePK;
    }

    public void setCatalogoGeneralOraclePK(CatalogoGeneralOraclePK catalogoGeneralOraclePK) {
        this.catalogoGeneralOraclePK = catalogoGeneralOraclePK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catalogoGeneralOraclePK != null ? catalogoGeneralOraclePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoGeneralOracle)) {
            return false;
        }
        CatalogoGeneralOracle other = (CatalogoGeneralOracle) object;
        if ((this.catalogoGeneralOraclePK == null && other.catalogoGeneralOraclePK != null) || (this.catalogoGeneralOraclePK != null && !this.catalogoGeneralOraclePK.equals(other.catalogoGeneralOraclePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.CatalogoGeneralOracle[ catalogoGeneralOraclePK=" + catalogoGeneralOraclePK + " ]";
    }
    
}
