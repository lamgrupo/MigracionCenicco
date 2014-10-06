/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.mysql;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JoséAntonio
 */
@Entity
@Table(name = "rhempleadocontactotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rhempleadocontactotb.findAll", query = "SELECT r FROM Rhempleadocontactotb r"),
    @NamedQuery(name = "Rhempleadocontactotb.findByIdcontacto", query = "SELECT r FROM Rhempleadocontactotb r WHERE r.idcontacto = :idcontacto"),
    @NamedQuery(name = "Rhempleadocontactotb.findByValor", query = "SELECT r FROM Rhempleadocontactotb r WHERE r.valor = :valor")})
public class Rhempleadocontactotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCONTACTO")
    private Integer idcontacto;
    @Column(name = "VALOR")
    private String valor;
    @JoinColumn(name = "IDTIPOCONTACTO", referencedColumnName = "IDTIPOCONTACTO")
    @ManyToOne
    private Rhtipocontactotb idtipocontacto;
    @JoinColumn(name = "IDEMPLEADO", referencedColumnName = "IDEMPLEADO")
    @ManyToOne
    private Rhempleadotb idempleado;

    public Rhempleadocontactotb() {
    }

    public Rhempleadocontactotb(Integer idcontacto) {
        this.idcontacto = idcontacto;
    }

    public Integer getIdcontacto() {
        return idcontacto;
    }

    public void setIdcontacto(Integer idcontacto) {
        this.idcontacto = idcontacto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Rhtipocontactotb getIdtipocontacto() {
        return idtipocontacto;
    }

    public void setIdtipocontacto(Rhtipocontactotb idtipocontacto) {
        this.idtipocontacto = idtipocontacto;
    }

    public Rhempleadotb getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Rhempleadotb idempleado) {
        this.idempleado = idempleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontacto != null ? idcontacto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rhempleadocontactotb)) {
            return false;
        }
        Rhempleadocontactotb other = (Rhempleadocontactotb) object;
        if ((this.idcontacto == null && other.idcontacto != null) || (this.idcontacto != null && !this.idcontacto.equals(other.idcontacto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Rhempleadocontactotb[ idcontacto=" + idcontacto + " ]";
    }
    
}
