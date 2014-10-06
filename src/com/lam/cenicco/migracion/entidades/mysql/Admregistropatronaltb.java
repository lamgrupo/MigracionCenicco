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
@Table(name = "admregistropatronaltb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admregistropatronaltb.findAll", query = "SELECT a FROM Admregistropatronaltb a"),
    @NamedQuery(name = "Admregistropatronaltb.findByIdregistropatronal", query = "SELECT a FROM Admregistropatronaltb a WHERE a.idregistropatronal = :idregistropatronal"),
    @NamedQuery(name = "Admregistropatronaltb.findByComentarios", query = "SELECT a FROM Admregistropatronaltb a WHERE a.comentarios = :comentarios"),
    @NamedQuery(name = "Admregistropatronaltb.findByPrincipal", query = "SELECT a FROM Admregistropatronaltb a WHERE a.principal = :principal"),
    @NamedQuery(name = "Admregistropatronaltb.findByRegistropatronal", query = "SELECT a FROM Admregistropatronaltb a WHERE a.registropatronal = :registropatronal")})
public class Admregistropatronaltb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDREGISTROPATRONAL")
    private Integer idregistropatronal;
    @Column(name = "COMENTARIOS")
    private String comentarios;
    @Column(name = "PRINCIPAL")
    private Integer principal;
    @Column(name = "REGISTROPATRONAL")
    private String registropatronal;
    @JoinColumn(name = "IDCOMPANIA", referencedColumnName = "IDCOMPANIA")
    @ManyToOne
    private Admcompaniatb idcompania;
    @OneToMany(mappedBy = "idregistropatronal")
    private List<Rhrelacionlaboraltb> rhrelacionlaboraltbList;

    public Admregistropatronaltb() {
    }

    public Admregistropatronaltb(Integer idregistropatronal) {
        this.idregistropatronal = idregistropatronal;
    }

    public Integer getIdregistropatronal() {
        return idregistropatronal;
    }

    public void setIdregistropatronal(Integer idregistropatronal) {
        this.idregistropatronal = idregistropatronal;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getPrincipal() {
        return principal;
    }

    public void setPrincipal(Integer principal) {
        this.principal = principal;
    }

    public String getRegistropatronal() {
        return registropatronal;
    }

    public void setRegistropatronal(String registropatronal) {
        this.registropatronal = registropatronal;
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
        hash += (idregistropatronal != null ? idregistropatronal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admregistropatronaltb)) {
            return false;
        }
        Admregistropatronaltb other = (Admregistropatronaltb) object;
        if ((this.idregistropatronal == null && other.idregistropatronal != null) || (this.idregistropatronal != null && !this.idregistropatronal.equals(other.idregistropatronal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Admregistropatronaltb[ idregistropatronal=" + idregistropatronal + " ]";
    }
    
}
