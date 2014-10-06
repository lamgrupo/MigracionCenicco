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
@Table(name = "nomgrupopagotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nomgrupopagotb.findAll", query = "SELECT n FROM Nomgrupopagotb n"),
    @NamedQuery(name = "Nomgrupopagotb.findByIdgrupopago", query = "SELECT n FROM Nomgrupopagotb n WHERE n.idgrupopago = :idgrupopago"),
    @NamedQuery(name = "Nomgrupopagotb.findByGrupopago", query = "SELECT n FROM Nomgrupopagotb n WHERE n.grupopago = :grupopago"),
    @NamedQuery(name = "Nomgrupopagotb.findByNombre", query = "SELECT n FROM Nomgrupopagotb n WHERE n.nombre = :nombre")})
public class Nomgrupopagotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDGRUPOPAGO")
    private Integer idgrupopago;
    @Column(name = "GRUPOPAGO")
    private String grupopago;
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "IDCOMPANIA", referencedColumnName = "IDCOMPANIA")
    @ManyToOne
    private Admcompaniatb idcompania;
    @OneToMany(mappedBy = "idgrupopago")
    private List<Rhrelacionlaboraltb> rhrelacionlaboraltbList;

    public Nomgrupopagotb() {
    }

    public Nomgrupopagotb(Integer idgrupopago) {
        this.idgrupopago = idgrupopago;
    }

    public Integer getIdgrupopago() {
        return idgrupopago;
    }

    public void setIdgrupopago(Integer idgrupopago) {
        this.idgrupopago = idgrupopago;
    }

    public String getGrupopago() {
        return grupopago;
    }

    public void setGrupopago(String grupopago) {
        this.grupopago = grupopago;
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
    public List<Rhrelacionlaboraltb> getRhrelacionlaboraltbList() {
        return rhrelacionlaboraltbList;
    }

    public void setRhrelacionlaboraltbList(List<Rhrelacionlaboraltb> rhrelacionlaboraltbList) {
        this.rhrelacionlaboraltbList = rhrelacionlaboraltbList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgrupopago != null ? idgrupopago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nomgrupopagotb)) {
            return false;
        }
        Nomgrupopagotb other = (Nomgrupopagotb) object;
        if ((this.idgrupopago == null && other.idgrupopago != null) || (this.idgrupopago != null && !this.idgrupopago.equals(other.idgrupopago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Nomgrupopagotb[ idgrupopago=" + idgrupopago + " ]";
    }
    
}
