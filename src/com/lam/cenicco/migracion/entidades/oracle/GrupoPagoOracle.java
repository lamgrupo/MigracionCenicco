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
@Table(name = "NOMGRUPOSPAGOTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoPagoOracle.findAll", query = "SELECT g FROM GrupoPagoOracle g"),
    @NamedQuery(name = "GrupoPagoOracle.findByIdgrupopago", query = "SELECT g FROM GrupoPagoOracle g WHERE g.idgrupopago = :idgrupopago"),
    @NamedQuery(name = "GrupoPagoOracle.findByGrupopago", query = "SELECT g FROM GrupoPagoOracle g WHERE g.grupopago = :grupopago"),
    @NamedQuery(name = "GrupoPagoOracle.findByNombre", query = "SELECT g FROM GrupoPagoOracle g WHERE g.nombre = :nombre")})
public class GrupoPagoOracle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDGRUPOPAGO")
    private Long idgrupopago;
    @Basic(optional = false)
    @Column(name = "GRUPOPAGO")
    private String grupopago;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "IDCOMPANIA", referencedColumnName = "IDCOMPANIA")
    @ManyToOne
    private CompaniaOracle idcompania;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idgrupopago")
    private List<RelacionLaboralOracle> relacionLaboralOracleList;

    public GrupoPagoOracle() {
    }

    public GrupoPagoOracle(Long idgrupopago) {
        this.idgrupopago = idgrupopago;
    }

    public GrupoPagoOracle(Long idgrupopago, String grupopago, String nombre) {
        this.idgrupopago = idgrupopago;
        this.grupopago = grupopago;
        this.nombre = nombre;
    }

    public Long getIdgrupopago() {
        return idgrupopago;
    }

    public void setIdgrupopago(Long idgrupopago) {
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

    public CompaniaOracle getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(CompaniaOracle idcompania) {
        this.idcompania = idcompania;
    }

    @XmlTransient
    public List<RelacionLaboralOracle> getRelacionLaboralOracleList() {
        return relacionLaboralOracleList;
    }

    public void setRelacionLaboralOracleList(List<RelacionLaboralOracle> relacionLaboralOracleList) {
        this.relacionLaboralOracleList = relacionLaboralOracleList;
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
        if (!(object instanceof GrupoPagoOracle)) {
            return false;
        }
        GrupoPagoOracle other = (GrupoPagoOracle) object;
        if ((this.idgrupopago == null && other.idgrupopago != null) || (this.idgrupopago != null && !this.idgrupopago.equals(other.idgrupopago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.GrupoPagoOracle[ idgrupopago=" + idgrupopago + " ]";
    }
    
}
