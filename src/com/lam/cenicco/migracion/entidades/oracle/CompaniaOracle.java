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
@Table(name = "ADMCOMPANIASTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompaniaOracle.findAll", query = "SELECT c FROM CompaniaOracle c"),
    @NamedQuery(name = "CompaniaOracle.findByIdcompania", query = "SELECT c FROM CompaniaOracle c WHERE c.idcompania = :idcompania"),
    @NamedQuery(name = "CompaniaOracle.findByCompania", query = "SELECT c FROM CompaniaOracle c WHERE c.compania = :compania"),
    @NamedQuery(name = "CompaniaOracle.findByNombre", query = "SELECT c FROM CompaniaOracle c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CompaniaOracle.findByRfc", query = "SELECT c FROM CompaniaOracle c WHERE c.rfc = :rfc"),
    @NamedQuery(name = "CompaniaOracle.findByEstatus", query = "SELECT c FROM CompaniaOracle c WHERE c.estatus = :estatus")})
public class CompaniaOracle implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcompania")
    private List<DepartamentoOracle> departamentoOracleList;
    @OneToMany(mappedBy = "idcompania")
    private List<GrupoPagoOracle> grupoPagoOracleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcompania")
    private List<RelacionLaboralOracle> relacionLaboralOracleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companiaOracle")
    private List<RegistroPatronalOracle> registroPatronalOracleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcompania")
    private List<PuestoOracle> puestoOracleList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDCOMPANIA")
    private Long idcompania;
    @Basic(optional = false)
    @Column(name = "COMPANIA")
    private String compania;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "RFC")
    private String rfc;
    @Basic(optional = false)
    @Column(name = "ESTATUS")
    private String estatus;

    public CompaniaOracle() {
    }

    public CompaniaOracle(Long idcompania) {
        this.idcompania = idcompania;
    }

    public CompaniaOracle(Long idcompania, String compania, String nombre, String estatus) {
        this.idcompania = idcompania;
        this.compania = compania;
        this.nombre = nombre;
        this.estatus = estatus;
    }

    public Long getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(Long idcompania) {
        this.idcompania = idcompania;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompania != null ? idcompania.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompaniaOracle)) {
            return false;
        }
        CompaniaOracle other = (CompaniaOracle) object;
        if ((this.idcompania == null && other.idcompania != null) || (this.idcompania != null && !this.idcompania.equals(other.idcompania))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.CompaniaOracle[ idcompania=" + idcompania + " ]";
    }

    @XmlTransient
    public List<PuestoOracle> getPuestoOracleList() {
        return puestoOracleList;
    }

    public void setPuestoOracleList(List<PuestoOracle> puestoOracleList) {
        this.puestoOracleList = puestoOracleList;
    }

    @XmlTransient
    public List<GrupoPagoOracle> getGrupoPagoOracleList() {
        return grupoPagoOracleList;
    }

    public void setGrupoPagoOracleList(List<GrupoPagoOracle> grupoPagoOracleList) {
        this.grupoPagoOracleList = grupoPagoOracleList;
    }

    @XmlTransient
    public List<RelacionLaboralOracle> getRelacionLaboralOracleList() {
        return relacionLaboralOracleList;
    }

    public void setRelacionLaboralOracleList(List<RelacionLaboralOracle> relacionLaboralOracleList) {
        this.relacionLaboralOracleList = relacionLaboralOracleList;
    }

    @XmlTransient
    public List<RegistroPatronalOracle> getRegistroPatronalOracleList() {
        return registroPatronalOracleList;
    }

    public void setRegistroPatronalOracleList(List<RegistroPatronalOracle> registroPatronalOracleList) {
        this.registroPatronalOracleList = registroPatronalOracleList;
    }

    @XmlTransient
    public List<DepartamentoOracle> getDepartamentoOracleList() {
        return departamentoOracleList;
    }

    public void setDepartamentoOracleList(List<DepartamentoOracle> departamentoOracleList) {
        this.departamentoOracleList = departamentoOracleList;
    }
    
}
