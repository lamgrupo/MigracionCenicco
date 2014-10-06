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
@Table(name = "TATURNOSTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TurnoOracle.findAll", query = "SELECT t FROM TurnoOracle t"),
    @NamedQuery(name = "TurnoOracle.findByIdturno", query = "SELECT t FROM TurnoOracle t WHERE t.idturno = :idturno"),
    @NamedQuery(name = "TurnoOracle.findByIdcompania", query = "SELECT t FROM TurnoOracle t WHERE t.idcompania = :idcompania"),
    @NamedQuery(name = "TurnoOracle.findByTurno", query = "SELECT t FROM TurnoOracle t WHERE t.turno = :turno"),
    @NamedQuery(name = "TurnoOracle.findByNombre", query = "SELECT t FROM TurnoOracle t WHERE t.nombre = :nombre")})
public class TurnoOracle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDTURNO")
    private Long idturno;
    @Basic(optional = false)
    @Column(name = "IDCOMPANIA")
    private long idcompania;
    @Basic(optional = false)
    @Column(name = "TURNO")
    private String turno;
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idturno")
    private List<PosicionOracle> posicionOracleList;

    public TurnoOracle() {
    }

    public TurnoOracle(Long idturno) {
        this.idturno = idturno;
    }

    public TurnoOracle(Long idturno, long idcompania, String turno) {
        this.idturno = idturno;
        this.idcompania = idcompania;
        this.turno = turno;
    }

    public Long getIdturno() {
        return idturno;
    }

    public void setIdturno(Long idturno) {
        this.idturno = idturno;
    }

    public long getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(long idcompania) {
        this.idcompania = idcompania;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<PosicionOracle> getPosicionOracleList() {
        return posicionOracleList;
    }

    public void setPosicionOracleList(List<PosicionOracle> posicionOracleList) {
        this.posicionOracleList = posicionOracleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idturno != null ? idturno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurnoOracle)) {
            return false;
        }
        TurnoOracle other = (TurnoOracle) object;
        if ((this.idturno == null && other.idturno != null) || (this.idturno != null && !this.idturno.equals(other.idturno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.TurnoOracle[ idturno=" + idturno + " ]";
    }
    
}
