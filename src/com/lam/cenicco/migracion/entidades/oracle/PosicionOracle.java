/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.oracle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JoséAntonio
 */
@Entity
@Table(name = "RHRELACIONLABORALPOSICIONESTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PosicionOracle.findAll", query = "SELECT p FROM PosicionOracle p"),
    @NamedQuery(name = "PosicionOracle.findByIdrellab", query = "SELECT p FROM PosicionOracle p WHERE p.posicionOraclePK.idrellab = :idrellab"),
    @NamedQuery(name = "PosicionOracle.findBySec", query = "SELECT p FROM PosicionOracle p WHERE p.posicionOraclePK.sec = :sec"),
    @NamedQuery(name = "PosicionOracle.findByFechainicio", query = "SELECT p FROM PosicionOracle p WHERE p.fechainicio = :fechainicio"),
    @NamedQuery(name = "PosicionOracle.findByFechafin", query = "SELECT p FROM PosicionOracle p WHERE p.fechafin = :fechafin"),
    @NamedQuery(name = "PosicionOracle.findByNivel", query = "SELECT p FROM PosicionOracle p WHERE p.nivel = :nivel"),
    @NamedQuery(name = "PosicionOracle.findBySistemahorario", query = "SELECT p FROM PosicionOracle p WHERE p.sistemahorario = :sistemahorario"),
    @NamedQuery(name = "PosicionOracle.findByJornadalaboral", query = "SELECT p FROM PosicionOracle p WHERE p.jornadalaboral = :jornadalaboral"),
    @NamedQuery(name = "PosicionOracle.findByTiposalario", query = "SELECT p FROM PosicionOracle p WHERE p.tiposalario = :tiposalario"),
    @NamedQuery(name = "PosicionOracle.findByFormapago", query = "SELECT p FROM PosicionOracle p WHERE p.formapago = :formapago"),
    @NamedQuery(name = "PosicionOracle.findBySindicalizado", query = "SELECT p FROM PosicionOracle p WHERE p.sindicalizado = :sindicalizado"),
    @NamedQuery(name = "PosicionOracle.findByEstatus", query = "SELECT p FROM PosicionOracle p WHERE p.estatus = :estatus")})
public class PosicionOracle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PosicionOraclePK posicionOraclePK;
    @Basic(optional = false)
    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainicio;
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechafin;
    @Column(name = "NIVEL")
    private String nivel;
    @Column(name = "SISTEMAHORARIO")
    private String sistemahorario;
    @Column(name = "JORNADALABORAL")
    private String jornadalaboral;
    @Column(name = "TIPOSALARIO")
    private String tiposalario;
    @Basic(optional = false)
    @Column(name = "FORMAPAGO")
    private String formapago;
    @Basic(optional = false)
    @Column(name = "SINDICALIZADO")
    private int sindicalizado;
    @Basic(optional = false)
    @Column(name = "ESTATUS")
    private String estatus;
    @JoinColumn(name = "IDTURNO", referencedColumnName = "IDTURNO")
    @ManyToOne
    private TurnoOracle idturno;
    @JoinColumn(name = "IDPUESTO", referencedColumnName = "IDPUESTO")
    @ManyToOne
    private PuestoOracle idpuesto;
    @JoinColumn(name = "IDDEPARTAMENTO", referencedColumnName = "IDDEPARTAMENTO")
    @ManyToOne
    private DepartamentoOracle iddepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "posicionOracle")
    private List<PosicionSueldoOracle> posicionSueldoOracleList;

    public PosicionOracle() {
    }

    public PosicionOracle(PosicionOraclePK posicionOraclePK) {
        this.posicionOraclePK = posicionOraclePK;
    }

    public PosicionOracle(PosicionOraclePK posicionOraclePK, Date fechainicio, String formapago, int sindicalizado, String estatus) {
        this.posicionOraclePK = posicionOraclePK;
        this.fechainicio = fechainicio;
        this.formapago = formapago;
        this.sindicalizado = sindicalizado;
        this.estatus = estatus;
    }

    public PosicionOracle(long idrellab, int sec) {
        this.posicionOraclePK = new PosicionOraclePK(idrellab, sec);
    }

    public PosicionOraclePK getPosicionOraclePK() {
        return posicionOraclePK;
    }

    public void setPosicionOraclePK(PosicionOraclePK posicionOraclePK) {
        this.posicionOraclePK = posicionOraclePK;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getSistemahorario() {
        return sistemahorario;
    }

    public void setSistemahorario(String sistemahorario) {
        this.sistemahorario = sistemahorario;
    }

    public String getJornadalaboral() {
        return jornadalaboral;
    }

    public void setJornadalaboral(String jornadalaboral) {
        this.jornadalaboral = jornadalaboral;
    }

    public String getTiposalario() {
        return tiposalario;
    }

    public void setTiposalario(String tiposalario) {
        this.tiposalario = tiposalario;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public int getSindicalizado() {
        return sindicalizado;
    }

    public void setSindicalizado(int sindicalizado) {
        this.sindicalizado = sindicalizado;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public TurnoOracle getIdturno() {
        return idturno;
    }

    public void setIdturno(TurnoOracle idturno) {
        this.idturno = idturno;
    }

    public PuestoOracle getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(PuestoOracle idpuesto) {
        this.idpuesto = idpuesto;
    }

    public DepartamentoOracle getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(DepartamentoOracle iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    @XmlTransient
    public List<PosicionSueldoOracle> getPosicionSueldoOracleList() {
        return posicionSueldoOracleList;
    }

    public void setPosicionSueldoOracleList(List<PosicionSueldoOracle> posicionSueldoOracleList) {
        this.posicionSueldoOracleList = posicionSueldoOracleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (posicionOraclePK != null ? posicionOraclePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PosicionOracle)) {
            return false;
        }
        PosicionOracle other = (PosicionOracle) object;
        if ((this.posicionOraclePK == null && other.posicionOraclePK != null) || (this.posicionOraclePK != null && !this.posicionOraclePK.equals(other.posicionOraclePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.PosicionOracle[ posicionOraclePK=" + posicionOraclePK + " ]";
    }
    
}
