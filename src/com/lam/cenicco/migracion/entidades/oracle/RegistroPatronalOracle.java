/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.oracle;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "ADMCOMPANIASREGPATTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroPatronalOracle.findAll", query = "SELECT r FROM RegistroPatronalOracle r"),
    @NamedQuery(name = "RegistroPatronalOracle.findByIdcompania", query = "SELECT r FROM RegistroPatronalOracle r WHERE r.registroPatronalOraclePK.idcompania = :idcompania"),
    @NamedQuery(name = "RegistroPatronalOracle.findByRegistropatronal", query = "SELECT r FROM RegistroPatronalOracle r WHERE r.registroPatronalOraclePK.registropatronal = :registropatronal"),
    @NamedQuery(name = "RegistroPatronalOracle.findByComentarios", query = "SELECT r FROM RegistroPatronalOracle r WHERE r.comentarios = :comentarios"),
    @NamedQuery(name = "RegistroPatronalOracle.findByPrincipal", query = "SELECT r FROM RegistroPatronalOracle r WHERE r.principal = :principal")})
public class RegistroPatronalOracle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegistroPatronalOraclePK registroPatronalOraclePK;
    @Column(name = "COMENTARIOS")
    private String comentarios;
    @Column(name = "PRINCIPAL")
    private Short principal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registroPatronalOracle")
    private List<RelacionLaboralOracle> relacionLaboralOracleList;
    @JoinColumn(name = "IDCOMPANIA", referencedColumnName = "IDCOMPANIA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CompaniaOracle companiaOracle;

    public RegistroPatronalOracle() {
    }

    public RegistroPatronalOracle(RegistroPatronalOraclePK registroPatronalOraclePK) {
        this.registroPatronalOraclePK = registroPatronalOraclePK;
    }

    public RegistroPatronalOracle(long idcompania, String registropatronal) {
        this.registroPatronalOraclePK = new RegistroPatronalOraclePK(idcompania, registropatronal);
    }

    public RegistroPatronalOraclePK getRegistroPatronalOraclePK() {
        return registroPatronalOraclePK;
    }

    public void setRegistroPatronalOraclePK(RegistroPatronalOraclePK registroPatronalOraclePK) {
        this.registroPatronalOraclePK = registroPatronalOraclePK;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Short getPrincipal() {
        return principal;
    }

    public void setPrincipal(Short principal) {
        this.principal = principal;
    }

    @XmlTransient
    public List<RelacionLaboralOracle> getRelacionLaboralOracleList() {
        return relacionLaboralOracleList;
    }

    public void setRelacionLaboralOracleList(List<RelacionLaboralOracle> relacionLaboralOracleList) {
        this.relacionLaboralOracleList = relacionLaboralOracleList;
    }

    public CompaniaOracle getCompaniaOracle() {
        return companiaOracle;
    }

    public void setCompaniaOracle(CompaniaOracle companiaOracle) {
        this.companiaOracle = companiaOracle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroPatronalOraclePK != null ? registroPatronalOraclePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroPatronalOracle)) {
            return false;
        }
        RegistroPatronalOracle other = (RegistroPatronalOracle) object;
        if ((this.registroPatronalOraclePK == null && other.registroPatronalOraclePK != null) || (this.registroPatronalOraclePK != null && !this.registroPatronalOraclePK.equals(other.registroPatronalOraclePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.RegistroPatronalOracle[ registroPatronalOraclePK=" + registroPatronalOraclePK + " ]";
    }
    
}
