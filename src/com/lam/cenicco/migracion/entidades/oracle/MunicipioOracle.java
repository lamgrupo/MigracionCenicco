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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "CATMUNICIPIOSTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MunicipioOracle.findAll", query = "SELECT m FROM MunicipioOracle m"),
    @NamedQuery(name = "MunicipioOracle.findByIdmunicipio", query = "SELECT m FROM MunicipioOracle m WHERE m.municipioOraclePK.idmunicipio = :idmunicipio"),
    @NamedQuery(name = "MunicipioOracle.findByIdpais", query = "SELECT m FROM MunicipioOracle m WHERE m.municipioOraclePK.idpais = :idpais"),
    @NamedQuery(name = "MunicipioOracle.findByIdestado", query = "SELECT m FROM MunicipioOracle m WHERE m.municipioOraclePK.idestado = :idestado"),
    @NamedQuery(name = "MunicipioOracle.findByMunicipio", query = "SELECT m FROM MunicipioOracle m WHERE m.municipio = :municipio"),
    @NamedQuery(name = "MunicipioOracle.findByNombre", query = "SELECT m FROM MunicipioOracle m WHERE m.nombre = :nombre")})
public class MunicipioOracle implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipioOracle")
    private List<CiudadOracle> ciudadOracleList;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MunicipioOraclePK municipioOraclePK;
    @Basic(optional = false)
    @Column(name = "MUNICIPIO")
    private String municipio;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumns({
        @JoinColumn(name = "IDESTADO", referencedColumnName = "IDESTADO", insertable = false, updatable = false),
        @JoinColumn(name = "IDPAIS", referencedColumnName = "IDPAIS", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private EstadoOracle estadoOracle;

    public MunicipioOracle() {
    }

    public MunicipioOracle(MunicipioOraclePK municipioOraclePK) {
        this.municipioOraclePK = municipioOraclePK;
    }

    public MunicipioOracle(MunicipioOraclePK municipioOraclePK, String municipio, String nombre) {
        this.municipioOraclePK = municipioOraclePK;
        this.municipio = municipio;
        this.nombre = nombre;
    }

    public MunicipioOracle(long idmunicipio, long idpais, long idestado) {
        this.municipioOraclePK = new MunicipioOraclePK(idmunicipio, idpais, idestado);
    }

    public MunicipioOraclePK getMunicipioOraclePK() {
        return municipioOraclePK;
    }

    public void setMunicipioOraclePK(MunicipioOraclePK municipioOraclePK) {
        this.municipioOraclePK = municipioOraclePK;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EstadoOracle getEstadoOracle() {
        return estadoOracle;
    }

    public void setEstadoOracle(EstadoOracle estadoOracle) {
        this.estadoOracle = estadoOracle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (municipioOraclePK != null ? municipioOraclePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MunicipioOracle)) {
            return false;
        }
        MunicipioOracle other = (MunicipioOracle) object;
        if ((this.municipioOraclePK == null && other.municipioOraclePK != null) || (this.municipioOraclePK != null && !this.municipioOraclePK.equals(other.municipioOraclePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.MunicipioOracle[ municipioOraclePK=" + municipioOraclePK + " ]";
    }

    @XmlTransient
    public List<CiudadOracle> getCiudadOracleList() {
        return ciudadOracleList;
    }

    public void setCiudadOracleList(List<CiudadOracle> ciudadOracleList) {
        this.ciudadOracleList = ciudadOracleList;
    }
    
}
