/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.oracle;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JoséAntonio
 */
@Entity
@Table(name = "RHRELACIONLABORALTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelacionLaboralOracle.findAll", query = "SELECT r FROM RelacionLaboralOracle r"),
    @NamedQuery(name = "RelacionLaboralOracle.findByIdrellab", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.idrellab = :idrellab"),
    @NamedQuery(name = "RelacionLaboralOracle.findByEmpleado", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.empleado = :empleado"),
    @NamedQuery(name = "RelacionLaboralOracle.findByFechaingreso", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.fechaingreso = :fechaingreso"),
    @NamedQuery(name = "RelacionLaboralOracle.findByTipocontrato", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.tipocontrato = :tipocontrato"),
    @NamedQuery(name = "RelacionLaboralOracle.findByFechainiciocontrato", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.fechainiciocontrato = :fechainiciocontrato"),
    @NamedQuery(name = "RelacionLaboralOracle.findByDuracion", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.duracion = :duracion"),
    @NamedQuery(name = "RelacionLaboralOracle.findByFechaventocontrato", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.fechaventocontrato = :fechaventocontrato"),
    @NamedQuery(name = "RelacionLaboralOracle.findBySistemaantiguedad", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.sistemaantiguedad = :sistemaantiguedad"),
    @NamedQuery(name = "RelacionLaboralOracle.findByFechaantiguedad1", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.fechaantiguedad1 = :fechaantiguedad1"),
    @NamedQuery(name = "RelacionLaboralOracle.findByFechaantiguedad2", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.fechaantiguedad2 = :fechaantiguedad2"),
    @NamedQuery(name = "RelacionLaboralOracle.findByFechaantiguedad3", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.fechaantiguedad3 = :fechaantiguedad3"),
    @NamedQuery(name = "RelacionLaboralOracle.findByFechabaja", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.fechabaja = :fechabaja"),
    @NamedQuery(name = "RelacionLaboralOracle.findByCausabaja", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.causabaja = :causabaja"),
    @NamedQuery(name = "RelacionLaboralOracle.findByCausabajaimss", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.causabajaimss = :causabajaimss"),
    @NamedQuery(name = "RelacionLaboralOracle.findByEstatus", query = "SELECT r FROM RelacionLaboralOracle r WHERE r.estatus = :estatus")})
public class RelacionLaboralOracle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDRELLAB")
    private Long idrellab;
    @Basic(optional = false)
    @Column(name = "EMPLEADO")
    private String empleado;
    @Basic(optional = false)
    @Column(name = "FECHAINGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaingreso;
    @Basic(optional = false)
    @Column(name = "TIPOCONTRATO")
    private String tipocontrato;
    @Basic(optional = false)
    @Column(name = "FECHAINICIOCONTRATO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainiciocontrato;
    @Column(name = "DURACION")
    private Integer duracion;
    @Column(name = "FECHAVENTOCONTRATO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaventocontrato;
    @Column(name = "SISTEMAANTIGUEDAD")
    private String sistemaantiguedad;
    @Column(name = "FECHAANTIGUEDAD1")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaantiguedad1;
    @Column(name = "FECHAANTIGUEDAD2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaantiguedad2;
    @Column(name = "FECHAANTIGUEDAD3")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaantiguedad3;
    @Column(name = "FECHABAJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechabaja;
    @Column(name = "CAUSABAJA")
    private String causabaja;
    @Column(name = "CAUSABAJAIMSS")
    private String causabajaimss;
    @Basic(optional = false)
    @Column(name = "ESTATUS")
    private String estatus;
    @JoinColumn(name = "TIPORELLAB", referencedColumnName = "TIPORELLAB")
    @ManyToOne(optional = false)
    private TipoRelacionLaboralOracle tiporellab;
    @JoinColumn(name = "IDEMPLEADO", referencedColumnName = "IDEMPLEADO")
    @ManyToOne(optional = false)
    private EmpleadoOracle idempleado;
    @JoinColumn(name = "IDGRUPOPAGO", referencedColumnName = "IDGRUPOPAGO")
    @ManyToOne(optional = false)
    private GrupoPagoOracle idgrupopago;
    @JoinColumn(name = "IDCOMPANIA", referencedColumnName = "IDCOMPANIA")
    @ManyToOne(optional = false)
    private CompaniaOracle idcompania;
    @JoinColumns({
        @JoinColumn(name = "IDCOMPANIA", referencedColumnName = "IDCOMPANIA", updatable = false,insertable = false),
        @JoinColumn(name = "REGISTROPATRONAL", referencedColumnName = "REGISTROPATRONAL")})
    @ManyToOne(optional = false)
    private RegistroPatronalOracle registroPatronalOracle;

    public RelacionLaboralOracle() {
    }

    public RelacionLaboralOracle(Long idrellab) {
        this.idrellab = idrellab;
    }

    public RelacionLaboralOracle(Long idrellab, String empleado, Date fechaingreso, String tipocontrato, Date fechainiciocontrato, String estatus) {
        this.idrellab = idrellab;
        this.empleado = empleado;
        this.fechaingreso = fechaingreso;
        this.tipocontrato = tipocontrato;
        this.fechainiciocontrato = fechainiciocontrato;
        this.estatus = estatus;
    }

    public Long getIdrellab() {
        return idrellab;
    }

    public void setIdrellab(Long idrellab) {
        this.idrellab = idrellab;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public String getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }

    public Date getFechainiciocontrato() {
        return fechainiciocontrato;
    }

    public void setFechainiciocontrato(Date fechainiciocontrato) {
        this.fechainiciocontrato = fechainiciocontrato;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Date getFechaventocontrato() {
        return fechaventocontrato;
    }

    public void setFechaventocontrato(Date fechaventocontrato) {
        this.fechaventocontrato = fechaventocontrato;
    }

    public String getSistemaantiguedad() {
        return sistemaantiguedad;
    }

    public void setSistemaantiguedad(String sistemaantiguedad) {
        this.sistemaantiguedad = sistemaantiguedad;
    }

    public Date getFechaantiguedad1() {
        return fechaantiguedad1;
    }

    public void setFechaantiguedad1(Date fechaantiguedad1) {
        this.fechaantiguedad1 = fechaantiguedad1;
    }

    public Date getFechaantiguedad2() {
        return fechaantiguedad2;
    }

    public void setFechaantiguedad2(Date fechaantiguedad2) {
        this.fechaantiguedad2 = fechaantiguedad2;
    }

    public Date getFechaantiguedad3() {
        return fechaantiguedad3;
    }

    public void setFechaantiguedad3(Date fechaantiguedad3) {
        this.fechaantiguedad3 = fechaantiguedad3;
    }

    public Date getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(Date fechabaja) {
        this.fechabaja = fechabaja;
    }

    public String getCausabaja() {
        return causabaja;
    }

    public void setCausabaja(String causabaja) {
        this.causabaja = causabaja;
    }

    public String getCausabajaimss() {
        return causabajaimss;
    }

    public void setCausabajaimss(String causabajaimss) {
        this.causabajaimss = causabajaimss;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public TipoRelacionLaboralOracle getTiporellab() {
        return tiporellab;
    }

    public void setTiporellab(TipoRelacionLaboralOracle tiporellab) {
        this.tiporellab = tiporellab;
    }

    public EmpleadoOracle getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(EmpleadoOracle idempleado) {
        this.idempleado = idempleado;
    }

    public GrupoPagoOracle getIdgrupopago() {
        return idgrupopago;
    }

    public void setIdgrupopago(GrupoPagoOracle idgrupopago) {
        this.idgrupopago = idgrupopago;
    }

    public CompaniaOracle getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(CompaniaOracle idcompania) {
        this.idcompania = idcompania;
    }

    public RegistroPatronalOracle getRegistroPatronalOracle() {
        return registroPatronalOracle;
    }

    public void setRegistroPatronalOracle(RegistroPatronalOracle registroPatronalOracle) {
        this.registroPatronalOracle = registroPatronalOracle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrellab != null ? idrellab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelacionLaboralOracle)) {
            return false;
        }
        RelacionLaboralOracle other = (RelacionLaboralOracle) object;
        if ((this.idrellab == null && other.idrellab != null) || (this.idrellab != null && !this.idrellab.equals(other.idrellab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.RelacionLaboralOracle[ idrellab=" + idrellab + " ]";
    }
    
}
