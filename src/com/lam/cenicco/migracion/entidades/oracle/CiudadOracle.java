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
@Table(name = "CATCIUDADESTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CiudadOracle.findAll", query = "SELECT c FROM CiudadOracle c"),
    @NamedQuery(name = "CiudadOracle.findByIdciudad", query = "SELECT c FROM CiudadOracle c WHERE c.ciudadOraclePK.idciudad = :idciudad"),
    @NamedQuery(name = "CiudadOracle.findByIdpais", query = "SELECT c FROM CiudadOracle c WHERE c.ciudadOraclePK.idpais = :idpais"),
    @NamedQuery(name = "CiudadOracle.findByIdestado", query = "SELECT c FROM CiudadOracle c WHERE c.ciudadOraclePK.idestado = :idestado"),
    @NamedQuery(name = "CiudadOracle.findByIdmunicipio", query = "SELECT c FROM CiudadOracle c WHERE c.ciudadOraclePK.idmunicipio = :idmunicipio"),
    @NamedQuery(name = "CiudadOracle.findByCiudad", query = "SELECT c FROM CiudadOracle c WHERE c.ciudad = :ciudad"),
    @NamedQuery(name = "CiudadOracle.findByNombre", query = "SELECT c FROM CiudadOracle c WHERE c.nombre = :nombre")})
public class CiudadOracle implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudadOracle")
    private List<AsentamientoOracle> asentamientoOracleList;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CiudadOraclePK ciudadOraclePK;
    @Column(name = "CIUDAD")
    private String ciudad;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumns({
        @JoinColumn(name = "IDMUNICIPIO", referencedColumnName = "IDMUNICIPIO", insertable = false, updatable = false),
        @JoinColumn(name = "IDPAIS", referencedColumnName = "IDPAIS", insertable = false, updatable = false),
        @JoinColumn(name = "IDESTADO", referencedColumnName = "IDESTADO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private MunicipioOracle municipioOracle;

    public CiudadOracle() {
    }

    public CiudadOracle(CiudadOraclePK ciudadOraclePK) {
        this.ciudadOraclePK = ciudadOraclePK;
    }

    public CiudadOracle(CiudadOraclePK ciudadOraclePK, String nombre) {
        this.ciudadOraclePK = ciudadOraclePK;
        this.nombre = nombre;
    }

    public CiudadOracle(long idciudad, long idpais, long idestado, long idmunicipio) {
        this.ciudadOraclePK = new CiudadOraclePK(idciudad, idpais, idestado, idmunicipio);
    }

    public CiudadOraclePK getCiudadOraclePK() {
        return ciudadOraclePK;
    }

    public void setCiudadOraclePK(CiudadOraclePK ciudadOraclePK) {
        this.ciudadOraclePK = ciudadOraclePK;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MunicipioOracle getMunicipioOracle() {
        return municipioOracle;
    }

    public void setMunicipioOracle(MunicipioOracle municipioOracle) {
        this.municipioOracle = municipioOracle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ciudadOraclePK != null ? ciudadOraclePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CiudadOracle)) {
            return false;
        }
        CiudadOracle other = (CiudadOracle) object;
        if ((this.ciudadOraclePK == null && other.ciudadOraclePK != null) || (this.ciudadOraclePK != null && !this.ciudadOraclePK.equals(other.ciudadOraclePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.CiudadOracle[ ciudadOraclePK=" + ciudadOraclePK + " ]";
    }

    @XmlTransient
    public List<AsentamientoOracle> getAsentamientoOracleList() {
        return asentamientoOracleList;
    }

    public void setAsentamientoOracleList(List<AsentamientoOracle> asentamientoOracleList) {
        this.asentamientoOracleList = asentamientoOracleList;
    }
    
}
