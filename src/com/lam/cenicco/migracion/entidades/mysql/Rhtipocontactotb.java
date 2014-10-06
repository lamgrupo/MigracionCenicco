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
@Table(name = "rhtipocontactotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rhtipocontactotb.findAll", query = "SELECT r FROM Rhtipocontactotb r"),
    @NamedQuery(name = "Rhtipocontactotb.findByIdtipocontacto", query = "SELECT r FROM Rhtipocontactotb r WHERE r.idtipocontacto = :idtipocontacto"),
    @NamedQuery(name = "Rhtipocontactotb.findByNombre", query = "SELECT r FROM Rhtipocontactotb r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Rhtipocontactotb.findByPersonal", query = "SELECT r FROM Rhtipocontactotb r WHERE r.personal = :personal")})
public class Rhtipocontactotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTIPOCONTACTO")
    private Integer idtipocontacto;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "PERSONAL")
    private String personal;
    @OneToMany(mappedBy = "idtipocontacto")
    private List<Rhempleadocontactotb> rhempleadocontactotbList;

    public Rhtipocontactotb() {
    }

    public Rhtipocontactotb(Integer idtipocontacto) {
        this.idtipocontacto = idtipocontacto;
    }

    public Integer getIdtipocontacto() {
        return idtipocontacto;
    }

    public void setIdtipocontacto(Integer idtipocontacto) {
        this.idtipocontacto = idtipocontacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    @XmlTransient
    public List<Rhempleadocontactotb> getRhempleadocontactotbList() {
        return rhempleadocontactotbList;
    }

    public void setRhempleadocontactotbList(List<Rhempleadocontactotb> rhempleadocontactotbList) {
        this.rhempleadocontactotbList = rhempleadocontactotbList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipocontacto != null ? idtipocontacto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rhtipocontactotb)) {
            return false;
        }
        Rhtipocontactotb other = (Rhtipocontactotb) object;
        if ((this.idtipocontacto == null && other.idtipocontacto != null) || (this.idtipocontacto != null && !this.idtipocontacto.equals(other.idtipocontacto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Rhtipocontactotb[ idtipocontacto=" + idtipocontacto + " ]";
    }
    
}
