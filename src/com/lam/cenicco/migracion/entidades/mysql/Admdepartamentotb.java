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
@Table(name = "admdepartamentotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admdepartamentotb.findAll", query = "SELECT a FROM Admdepartamentotb a"),
    @NamedQuery(name = "Admdepartamentotb.findByIddepartamento", query = "SELECT a FROM Admdepartamentotb a WHERE a.iddepartamento = :iddepartamento"),
    @NamedQuery(name = "Admdepartamentotb.findByDepartamento", query = "SELECT a FROM Admdepartamentotb a WHERE a.departamento = :departamento"),
    @NamedQuery(name = "Admdepartamentotb.findByNombre", query = "SELECT a FROM Admdepartamentotb a WHERE a.nombre = :nombre")})
public class Admdepartamentotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDEPARTAMENTO")
    private Integer iddepartamento;
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "IDCOMPANIA", referencedColumnName = "IDCOMPANIA")
    @ManyToOne
    private Admcompaniatb idcompania;
    @OneToMany(mappedBy = "iddepartamento")
    private List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbList;

    public Admdepartamentotb() {
    }

    public Admdepartamentotb(Integer iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public Integer getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Integer iddepartamento) {
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

    public Admcompaniatb getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(Admcompaniatb idcompania) {
        this.idcompania = idcompania;
    }

    @XmlTransient
    public List<Rhrelacionlaboralposiciontb> getRhrelacionlaboralposiciontbList() {
        return rhrelacionlaboralposiciontbList;
    }

    public void setRhrelacionlaboralposiciontbList(List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbList) {
        this.rhrelacionlaboralposiciontbList = rhrelacionlaboralposiciontbList;
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
        if (!(object instanceof Admdepartamentotb)) {
            return false;
        }
        Admdepartamentotb other = (Admdepartamentotb) object;
        if ((this.iddepartamento == null && other.iddepartamento != null) || (this.iddepartamento != null && !this.iddepartamento.equals(other.iddepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Admdepartamentotb[ iddepartamento=" + iddepartamento + " ]";
    }
    
}
