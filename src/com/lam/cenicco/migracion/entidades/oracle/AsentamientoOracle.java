/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.oracle;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JoséAntonio
 */
@Entity
@Table(name = "CATASENTAMIENTOSTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsentamientoOracle.findAll", query = "SELECT a FROM AsentamientoOracle a"),
    @NamedQuery(name = "AsentamientoOracle.findByIdasentamiento", query = "SELECT a FROM AsentamientoOracle a WHERE a.asentamientoOraclePK.idasentamiento = :idasentamiento"),
    @NamedQuery(name = "AsentamientoOracle.findByIdpais", query = "SELECT a FROM AsentamientoOracle a WHERE a.asentamientoOraclePK.idpais = :idpais"),
    @NamedQuery(name = "AsentamientoOracle.findByIdestado", query = "SELECT a FROM AsentamientoOracle a WHERE a.asentamientoOraclePK.idestado = :idestado"),
    @NamedQuery(name = "AsentamientoOracle.findByIdmunicipio", query = "SELECT a FROM AsentamientoOracle a WHERE a.asentamientoOraclePK.idmunicipio = :idmunicipio"),
    @NamedQuery(name = "AsentamientoOracle.findByIdciudad", query = "SELECT a FROM AsentamientoOracle a WHERE a.asentamientoOraclePK.idciudad = :idciudad"),
    @NamedQuery(name = "AsentamientoOracle.findByIdtipoasentamiento", query = "SELECT a FROM AsentamientoOracle a WHERE a.idtipoasentamiento = :idtipoasentamiento"),
    @NamedQuery(name = "AsentamientoOracle.findByCodigopostal", query = "SELECT a FROM AsentamientoOracle a WHERE a.asentamientoOraclePK.codigopostal = :codigopostal"),
    @NamedQuery(name = "AsentamientoOracle.findByAsentamiento", query = "SELECT a FROM AsentamientoOracle a WHERE a.asentamiento = :asentamiento"),
    @NamedQuery(name = "AsentamientoOracle.findByNombre", query = "SELECT a FROM AsentamientoOracle a WHERE a.nombre = :nombre")})
public class AsentamientoOracle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AsentamientoOraclePK asentamientoOraclePK;
    @Basic(optional = false)
    @Column(name = "IDTIPOASENTAMIENTO")
    private String idtipoasentamiento;
    @Basic(optional = false)
    @Column(name = "ASENTAMIENTO")
    private String asentamiento;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumns({
        @JoinColumn(name = "IDCIUDAD", referencedColumnName = "IDCIUDAD", insertable = false, updatable = false),
        @JoinColumn(name = "IDPAIS", referencedColumnName = "IDPAIS", insertable = false, updatable = false),
        @JoinColumn(name = "IDESTADO", referencedColumnName = "IDESTADO", insertable = false, updatable = false),
        @JoinColumn(name = "IDMUNICIPIO", referencedColumnName = "IDMUNICIPIO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CiudadOracle ciudadOracle;

    public AsentamientoOracle() {
    }

    public AsentamientoOracle(AsentamientoOraclePK asentamientoOraclePK) {
        this.asentamientoOraclePK = asentamientoOraclePK;
    }

    public AsentamientoOracle(AsentamientoOraclePK asentamientoOraclePK, String idtipoasentamiento, String asentamiento, String nombre) {
        this.asentamientoOraclePK = asentamientoOraclePK;
        this.idtipoasentamiento = idtipoasentamiento;
        this.asentamiento = asentamiento;
        this.nombre = nombre;
    }

    public AsentamientoOracle(long idasentamiento, long idpais, long idestado, long idmunicipio, long idciudad, long codigopostal) {
        this.asentamientoOraclePK = new AsentamientoOraclePK(idasentamiento, idpais, idestado, idmunicipio, idciudad, codigopostal);
    }

    public AsentamientoOraclePK getAsentamientoOraclePK() {
        return asentamientoOraclePK;
    }

    public void setAsentamientoOraclePK(AsentamientoOraclePK asentamientoOraclePK) {
        this.asentamientoOraclePK = asentamientoOraclePK;
    }

    public String getIdtipoasentamiento() {
        return idtipoasentamiento;
    }

    public void setIdtipoasentamiento(String idtipoasentamiento) {
        this.idtipoasentamiento = idtipoasentamiento;
    }

    public String getAsentamiento() {
        return asentamiento;
    }

    public void setAsentamiento(String asentamiento) {
        this.asentamiento = asentamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CiudadOracle getCiudadOracle() {
        return ciudadOracle;
    }

    public void setCiudadOracle(CiudadOracle ciudadOracle) {
        this.ciudadOracle = ciudadOracle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asentamientoOraclePK != null ? asentamientoOraclePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsentamientoOracle)) {
            return false;
        }
        AsentamientoOracle other = (AsentamientoOracle) object;
        if ((this.asentamientoOraclePK == null && other.asentamientoOraclePK != null) || (this.asentamientoOraclePK != null && !this.asentamientoOraclePK.equals(other.asentamientoOraclePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.AsentamientoOracle[ asentamientoOraclePK=" + asentamientoOraclePK + " ]";
    }
    
}
