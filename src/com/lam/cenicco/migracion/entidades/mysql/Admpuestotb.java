/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.mysql;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "admpuestotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admpuestotb.findAll", query = "SELECT a FROM Admpuestotb a"),
    @NamedQuery(name = "Admpuestotb.findByIdpuesto", query = "SELECT a FROM Admpuestotb a WHERE a.idpuesto = :idpuesto"),
    @NamedQuery(name = "Admpuestotb.findByNombre", query = "SELECT a FROM Admpuestotb a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Admpuestotb.findByPlazas", query = "SELECT a FROM Admpuestotb a WHERE a.plazas = :plazas"),
    @NamedQuery(name = "Admpuestotb.findByPlazasdisponibles", query = "SELECT a FROM Admpuestotb a WHERE a.plazasdisponibles = :plazasdisponibles"),
    @NamedQuery(name = "Admpuestotb.findByPlazasocupadas", query = "SELECT a FROM Admpuestotb a WHERE a.plazasocupadas = :plazasocupadas"),
    @NamedQuery(name = "Admpuestotb.findByPlazasreservadas", query = "SELECT a FROM Admpuestotb a WHERE a.plazasreservadas = :plazasreservadas"),
    @NamedQuery(name = "Admpuestotb.findByPuesto", query = "SELECT a FROM Admpuestotb a WHERE a.puesto = :puesto")})
public class Admpuestotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPUESTO")
    private Integer idpuesto;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "PLAZAS")
    private Integer plazas;
    @Column(name = "PLAZASDISPONIBLES")
    private Integer plazasdisponibles;
    @Column(name = "PLAZASOCUPADAS")
    private Integer plazasocupadas;
    @Column(name = "PLAZASRESERVADAS")
    private Integer plazasreservadas;
    @Column(name = "PUESTO")
    private String puesto;
    @OneToMany(mappedBy = "idpuesto")
    private List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbList;
    @JoinColumn(name = "IDCOMPANIA", referencedColumnName = "IDCOMPANIA")
    @ManyToOne
    private Admcompaniatb idcompania;

    public Admpuestotb() {
    }

    public Admpuestotb(Integer idpuesto) {
        this.idpuesto = idpuesto;
    }

    public Integer getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(Integer idpuesto) {
        this.idpuesto = idpuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPlazas() {
        return plazas;
    }

    public void setPlazas(Integer plazas) {
        this.plazas = plazas;
    }

    public Integer getPlazasdisponibles() {
        return plazasdisponibles;
    }

    public void setPlazasdisponibles(Integer plazasdisponibles) {
        this.plazasdisponibles = plazasdisponibles;
    }

    public Integer getPlazasocupadas() {
        return plazasocupadas;
    }

    public void setPlazasocupadas(Integer plazasocupadas) {
        this.plazasocupadas = plazasocupadas;
    }

    public Integer getPlazasreservadas() {
        return plazasreservadas;
    }

    public void setPlazasreservadas(Integer plazasreservadas) {
        this.plazasreservadas = plazasreservadas;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @XmlTransient
    public List<Rhrelacionlaboralposiciontb> getRhrelacionlaboralposiciontbList() {
        return rhrelacionlaboralposiciontbList;
    }

    public void setRhrelacionlaboralposiciontbList(List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbList) {
        this.rhrelacionlaboralposiciontbList = rhrelacionlaboralposiciontbList;
    }

    public Admcompaniatb getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(Admcompaniatb idcompania) {
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
        if (!(object instanceof Admpuestotb)) {
            return false;
        }
        Admpuestotb other = (Admpuestotb) object;
        if ((this.idpuesto == null && other.idpuesto != null) || (this.idpuesto != null && !this.idpuesto.equals(other.idpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Admpuestotb[ idpuesto=" + idpuesto + " ]";
    }
    
}
