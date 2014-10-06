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
@Table(name = "CATPAISESTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaisOracle.findAll", query = "SELECT p FROM PaisOracle p"),
    @NamedQuery(name = "PaisOracle.findByIdpais", query = "SELECT p FROM PaisOracle p WHERE p.idpais = :idpais"),
    @NamedQuery(name = "PaisOracle.findByPais", query = "SELECT p FROM PaisOracle p WHERE p.pais = :pais"),
    @NamedQuery(name = "PaisOracle.findByNombre", query = "SELECT p FROM PaisOracle p WHERE p.nombre = :nombre")})
public class PaisOracle implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paisOracle")
    private List<EstadoOracle> estadoOracleList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDPAIS")
    private Long idpais;
    @Basic(optional = false)
    @Column(name = "PAIS")
    private String pais;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;

    public PaisOracle() {
    }

    public PaisOracle(Long idpais) {
        this.idpais = idpais;
    }

    public PaisOracle(Long idpais, String pais, String nombre) {
        this.idpais = idpais;
        this.pais = pais;
        this.nombre = nombre;
    }

    public Long getIdpais() {
        return idpais;
    }

    public void setIdpais(Long idpais) {
        this.idpais = idpais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
        hash += (idpais != null ? idpais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaisOracle)) {
            return false;
        }
        PaisOracle other = (PaisOracle) object;
        if ((this.idpais == null && other.idpais != null) || (this.idpais != null && !this.idpais.equals(other.idpais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.PaisOracle[ idpais=" + idpais + " ]";
    }

    @XmlTransient
    public List<EstadoOracle> getEstadoOracleList() {
        return estadoOracleList;
    }

    public void setEstadoOracleList(List<EstadoOracle> estadoOracleList) {
        this.estadoOracleList = estadoOracleList;
    }
    
}
