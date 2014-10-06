/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.oracle;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JoséAntonio
 */
@Entity
@Table(name = "RHTIPOSRELLABTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRelacionLaboralOracle.findAll", query = "SELECT t FROM TipoRelacionLaboralOracle t"),
    @NamedQuery(name = "TipoRelacionLaboralOracle.findByTiporellab", query = "SELECT t FROM TipoRelacionLaboralOracle t WHERE t.tiporellab = :tiporellab"),
    @NamedQuery(name = "TipoRelacionLaboralOracle.findByNombre", query = "SELECT t FROM TipoRelacionLaboralOracle t WHERE t.nombre = :nombre")})
public class TipoRelacionLaboralOracle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TIPORELLAB")
    private String tiporellab;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiporellab")
    private List<RelacionLaboralOracle> relacionLaboralOracleList;

    public TipoRelacionLaboralOracle() {
    }

    public TipoRelacionLaboralOracle(String tiporellab) {
        this.tiporellab = tiporellab;
    }

    public TipoRelacionLaboralOracle(String tiporellab, String nombre) {
        this.tiporellab = tiporellab;
        this.nombre = nombre;
    }

    public String getTiporellab() {
        return tiporellab;
    }

    public void setTiporellab(String tiporellab) {
        this.tiporellab = tiporellab;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<RelacionLaboralOracle> getRelacionLaboralOracleList() {
        return relacionLaboralOracleList;
    }

    public void setRelacionLaboralOracleList(List<RelacionLaboralOracle> relacionLaboralOracleList) {
        this.relacionLaboralOracleList = relacionLaboralOracleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tiporellab != null ? tiporellab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRelacionLaboralOracle)) {
            return false;
        }
        TipoRelacionLaboralOracle other = (TipoRelacionLaboralOracle) object;
        if ((this.tiporellab == null && other.tiporellab != null) || (this.tiporellab != null && !this.tiporellab.equals(other.tiporellab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.TipoRelacionLaboralOracle[ tiporellab=" + tiporellab + " ]";
    }
    
}
