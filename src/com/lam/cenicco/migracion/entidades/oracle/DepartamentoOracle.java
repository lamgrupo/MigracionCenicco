/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.oracle;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "ADMDEPARTAMENTOSTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DepartamentoOracle.findAll", query = "SELECT d FROM DepartamentoOracle d"),
    @NamedQuery(name = "DepartamentoOracle.findByIddepartamento", query = "SELECT d FROM DepartamentoOracle d WHERE d.iddepartamento = :iddepartamento"),
    @NamedQuery(name = "DepartamentoOracle.findByDepartamento", query = "SELECT d FROM DepartamentoOracle d WHERE d.departamento = :departamento"),
    @NamedQuery(name = "DepartamentoOracle.findByNombre", query = "SELECT d FROM DepartamentoOracle d WHERE d.nombre = :nombre")})
public class DepartamentoOracle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDDEPARTAMENTO")
    private Long iddepartamento;
    @Basic(optional = false)
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "iddepartamento")
    private List<PosicionOracle> posicionOracleList;
    @JoinColumn(name = "IDCOMPANIA", referencedColumnName = "IDCOMPANIA")
    @ManyToOne(optional = false)
    private CompaniaOracle idcompania;

    public DepartamentoOracle() {
    }

    public DepartamentoOracle(Long iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public DepartamentoOracle(Long iddepartamento, String departamento, String nombre) {
        this.iddepartamento = iddepartamento;
        this.departamento = departamento;
        this.nombre = nombre;
    }

    public Long getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Long iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<PosicionOracle> getPosicionOracleList() {
        return posicionOracleList;
    }

    public void setPosicionOracleList(List<PosicionOracle> posicionOracleList) {
        this.posicionOracleList = posicionOracleList;
    }

    public CompaniaOracle getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(CompaniaOracle idcompania) {
        this.idcompania = idcompania;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddepartamento != null ? iddepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DepartamentoOracle)) {
            return false;
        }
        DepartamentoOracle other = (DepartamentoOracle) object;
        if ((this.iddepartamento == null && other.iddepartamento != null) || (this.iddepartamento != null && !this.iddepartamento.equals(other.iddepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.DepartamentoOracle[ iddepartamento=" + iddepartamento + " ]";
    }
    
}
