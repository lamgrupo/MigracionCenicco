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
@Table(name = "ADMPUESTOSTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PuestoOracle.findAll", query = "SELECT p FROM PuestoOracle p"),
    @NamedQuery(name = "PuestoOracle.findByIdpuesto", query = "SELECT p FROM PuestoOracle p WHERE p.idpuesto = :idpuesto"),
    @NamedQuery(name = "PuestoOracle.findByPuesto", query = "SELECT p FROM PuestoOracle p WHERE p.puesto = :puesto"),
    @NamedQuery(name = "PuestoOracle.findByNombre", query = "SELECT p FROM PuestoOracle p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PuestoOracle.findByPlazas", query = "SELECT p FROM PuestoOracle p WHERE p.plazas = :plazas"),
    @NamedQuery(name = "PuestoOracle.findByPlazasocupadas", query = "SELECT p FROM PuestoOracle p WHERE p.plazasocupadas = :plazasocupadas"),
    @NamedQuery(name = "PuestoOracle.findByPlazasreservadas", query = "SELECT p FROM PuestoOracle p WHERE p.plazasreservadas = :plazasreservadas"),
    @NamedQuery(name = "PuestoOracle.findByPlazasdisponibles", query = "SELECT p FROM PuestoOracle p WHERE p.plazasdisponibles = :plazasdisponibles")})
public class PuestoOracle implements Serializable {
    @OneToMany(mappedBy = "idpuesto")
    private List<PosicionOracle> posicionOracleList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDPUESTO")
    private Long idpuesto;
    @Basic(optional = false)
    @Column(name = "PUESTO")
    private String puesto;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "PLAZAS")
    private int plazas;
    @Basic(optional = false)
    @Column(name = "PLAZASOCUPADAS")
    private int plazasocupadas;
    @Basic(optional = false)
    @Column(name = "PLAZASRESERVADAS")
    private int plazasreservadas;
    @Basic(optional = false)
    @Column(name = "PLAZASDISPONIBLES")
    private int plazasdisponibles;
    @JoinColumn(name = "IDCOMPANIA", referencedColumnName = "IDCOMPANIA")
    @ManyToOne(optional = false)
    private CompaniaOracle idcompania;

    public PuestoOracle() {
    }

    public PuestoOracle(Long idpuesto) {
        this.idpuesto = idpuesto;
    }

    public PuestoOracle(Long idpuesto, String puesto, String nombre, int plazas, int plazasocupadas, int plazasreservadas, int plazasdisponibles) {
        this.idpuesto = idpuesto;
        this.puesto = puesto;
        this.nombre = nombre;
        this.plazas = plazas;
        this.plazasocupadas = plazasocupadas;
        this.plazasreservadas = plazasreservadas;
        this.plazasdisponibles = plazasdisponibles;
    }

    public Long getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(Long idpuesto) {
        this.idpuesto = idpuesto;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    public int getPlazasocupadas() {
        return plazasocupadas;
    }

    public void setPlazasocupadas(int plazasocupadas) {
        this.plazasocupadas = plazasocupadas;
    }

    public int getPlazasreservadas() {
        return plazasreservadas;
    }

    public void setPlazasreservadas(int plazasreservadas) {
        this.plazasreservadas = plazasreservadas;
    }

    public int getPlazasdisponibles() {
        return plazasdisponibles;
    }

    public void setPlazasdisponibles(int plazasdisponibles) {
        this.plazasdisponibles = plazasdisponibles;
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
        hash += (idpuesto != null ? idpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PuestoOracle)) {
            return false;
        }
        PuestoOracle other = (PuestoOracle) object;
        if ((this.idpuesto == null && other.idpuesto != null) || (this.idpuesto != null && !this.idpuesto.equals(other.idpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.PuestoOracle[ idpuesto=" + idpuesto + " ]";
    }

    @XmlTransient
    public List<PosicionOracle> getPosicionOracleList() {
        return posicionOracleList;
    }

    public void setPosicionOracleList(List<PosicionOracle> posicionOracleList) {
        this.posicionOracleList = posicionOracleList;
    }
    
}
