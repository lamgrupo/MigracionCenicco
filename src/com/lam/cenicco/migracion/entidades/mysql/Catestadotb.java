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
@Table(name = "catestadotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catestadotb.findAll", query = "SELECT c FROM Catestadotb c"),
    @NamedQuery(name = "Catestadotb.findByIdestado", query = "SELECT c FROM Catestadotb c WHERE c.idestado = :idestado"),
    @NamedQuery(name = "Catestadotb.findByEstado", query = "SELECT c FROM Catestadotb c WHERE c.estado = :estado"),
    @NamedQuery(name = "Catestadotb.findByNombre", query = "SELECT c FROM Catestadotb c WHERE c.nombre = :nombre")})
public class Catestadotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDESTADO")
    private Integer idestado;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "IDPAIS", referencedColumnName = "IDPAIS")
    @ManyToOne
    private Catpaistb idpais;
    @OneToMany(mappedBy = "idestado")
    private List<Catmunicipiotb> catmunicipiotbList;
    @OneToMany(mappedBy = "idestadonacimiento")
    private List<Rhempleadotb> rhempleadotbList;

    public Catestadotb() {
    }

    public Catestadotb(Integer idestado) {
        this.idestado = idestado;
    }

    public Integer getIdestado() {
        return idestado;
    }

    public void setIdestado(Integer idestado) {
        this.idestado = idestado;
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

    public Catpaistb getIdpais() {
        return idpais;
    }

    public void setIdpais(Catpaistb idpais) {
        this.idpais = idpais;
    }

    @XmlTransient
    public List<Catmunicipiotb> getCatmunicipiotbList() {
        return catmunicipiotbList;
    }

    public void setCatmunicipiotbList(List<Catmunicipiotb> catmunicipiotbList) {
        this.catmunicipiotbList = catmunicipiotbList;
    }

    @XmlTransient
    public List<Rhempleadotb> getRhempleadotbList() {
        return rhempleadotbList;
    }

    public void setRhempleadotbList(List<Rhempleadotb> rhempleadotbList) {
        this.rhempleadotbList = rhempleadotbList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestado != null ? idestado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catestadotb)) {
            return false;
        }
        Catestadotb other = (Catestadotb) object;
        if ((this.idestado == null && other.idestado != null) || (this.idestado != null && !this.idestado.equals(other.idestado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Catestadotb[ idestado=" + idestado + " ]";
    }
    
}
