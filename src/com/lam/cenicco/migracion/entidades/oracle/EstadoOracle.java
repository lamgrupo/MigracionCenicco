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
@Table(name = "CATESTADOSTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoOracle.findAll", query = "SELECT e FROM EstadoOracle e"),
    @NamedQuery(name = "EstadoOracle.findByIdestado", query = "SELECT e FROM EstadoOracle e WHERE e.estadoOraclePK.idestado = :idestado"),
    @NamedQuery(name = "EstadoOracle.findByIdpais", query = "SELECT e FROM EstadoOracle e WHERE e.estadoOraclePK.idpais = :idpais"),
    @NamedQuery(name = "EstadoOracle.findByEstado", query = "SELECT e FROM EstadoOracle e WHERE e.estado = :estado"),
    @NamedQuery(name = "EstadoOracle.findByNombre", query = "SELECT e FROM EstadoOracle e WHERE e.nombre = :nombre")})
public class EstadoOracle implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoOracle")
    private List<EmpleadoOracle> empleadoOracleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoOracle")
    private List<EmpleadoOracle> empeladoOracleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoOracle")
    private List<MunicipioOracle> municipioOracleList;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EstadoOraclePK estadoOraclePK;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private String estado;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "IDPAIS", referencedColumnName = "IDPAIS", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PaisOracle paisOracle;

    public EstadoOracle() {
    }

    public EstadoOracle(EstadoOraclePK estadoOraclePK) {
        this.estadoOraclePK = estadoOraclePK;
    }

    public EstadoOracle(EstadoOraclePK estadoOraclePK, String estado, String nombre) {
        this.estadoOraclePK = estadoOraclePK;
        this.estado = estado;
        this.nombre = nombre;
    }

    public EstadoOracle(long idestado, long idpais) {
        this.estadoOraclePK = new EstadoOraclePK(idestado, idpais);
    }

    public EstadoOraclePK getEstadoOraclePK() {
        return estadoOraclePK;
    }

    public void setEstadoOraclePK(EstadoOraclePK estadoOraclePK) {
        this.estadoOraclePK = estadoOraclePK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PaisOracle getPaisOracle() {
        return paisOracle;
    }

    public void setPaisOracle(PaisOracle paisOracle) {
        this.paisOracle = paisOracle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoOraclePK != null ? estadoOraclePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoOracle)) {
            return false;
        }
        EstadoOracle other = (EstadoOracle) object;
        if ((this.estadoOraclePK == null && other.estadoOraclePK != null) || (this.estadoOraclePK != null && !this.estadoOraclePK.equals(other.estadoOraclePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.EstadoOracle[ estadoOraclePK=" + estadoOraclePK + " ]";
    }

    @XmlTransient
    public List<MunicipioOracle> getMunicipioOracleList() {
        return municipioOracleList;
    }

    public void setMunicipioOracleList(List<MunicipioOracle> municipioOracleList) {
        this.municipioOracleList = municipioOracleList;
    }

    @XmlTransient
    public List<EmpleadoOracle> getEmpeladoOracleList() {
        return empeladoOracleList;
    }

    public void setEmpeladoOracleList(List<EmpleadoOracle> empeladoOracleList) {
        this.empeladoOracleList = empeladoOracleList;
    }

    @XmlTransient
    public List<EmpleadoOracle> getEmpleadoOracleList() {
        return empleadoOracleList;
    }

    public void setEmpleadoOracleList(List<EmpleadoOracle> empleadoOracleList) {
        this.empleadoOracleList = empleadoOracleList;
    }
    
}
