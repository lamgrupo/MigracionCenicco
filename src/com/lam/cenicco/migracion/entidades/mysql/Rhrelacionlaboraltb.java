/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.mysql;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JoséAntonio
 */
@Entity
@Table(name = "rhrelacionlaboraltb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rhrelacionlaboraltb.findAll", query = "SELECT r FROM Rhrelacionlaboraltb r"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findByIdrellab", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.idrellab = :idrellab"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findByCausabaja", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.causabaja = :causabaja"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findByCausabajaimss", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.causabajaimss = :causabajaimss"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findByDuracion", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.duracion = :duracion"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findByEstatus", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.estatus = :estatus"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findByFechaantiguedad1", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.fechaantiguedad1 = :fechaantiguedad1"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findByFechaantiguedad2", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.fechaantiguedad2 = :fechaantiguedad2"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findByFechaantiguedad3", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.fechaantiguedad3 = :fechaantiguedad3"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findByFechabaja", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.fechabaja = :fechabaja"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findByFechaeventocontrato", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.fechaeventocontrato = :fechaeventocontrato"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findByFechaingreso", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.fechaingreso = :fechaingreso"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findByFechainiciocontrato", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.fechainiciocontrato = :fechainiciocontrato"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findByNumeroempleado", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.numeroempleado = :numeroempleado"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findBySistemaantiguedad", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.sistemaantiguedad = :sistemaantiguedad"),
    @NamedQuery(name = "Rhrelacionlaboraltb.findByTipocontrato", query = "SELECT r FROM Rhrelacionlaboraltb r WHERE r.tipocontrato = :tipocontrato")})
public class Rhrelacionlaboraltb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDRELLAB")
    private Integer idrellab;
    @Column(name = "CAUSABAJA")
    private String causabaja;
    @Column(name = "CAUSABAJAIMSS")
    private String causabajaimss;
    @Column(name = "DURACION")
    private Integer duracion;
    @Column(name = "ESTATUS")
    private String estatus;
    @Column(name = "FECHAANTIGUEDAD1")
    @Temporal(TemporalType.DATE)
    private Date fechaantiguedad1;
    @Column(name = "FECHAANTIGUEDAD2")
    @Temporal(TemporalType.DATE)
    private Date fechaantiguedad2;
    @Column(name = "FECHAANTIGUEDAD3")
    @Temporal(TemporalType.DATE)
    private Date fechaantiguedad3;
    @Column(name = "FECHABAJA")
    @Temporal(TemporalType.DATE)
    private Date fechabaja;
    @Column(name = "FECHAEVENTOCONTRATO")
    @Temporal(TemporalType.DATE)
    private Date fechaeventocontrato;
    @Column(name = "FECHAINGRESO")
    @Temporal(TemporalType.DATE)
    private Date fechaingreso;
    @Column(name = "FECHAINICIOCONTRATO")
    @Temporal(TemporalType.DATE)
    private Date fechainiciocontrato;
    @Column(name = "NUMEROEMPLEADO")
    private String numeroempleado;
    @Column(name = "SISTEMAANTIGUEDAD")
    private String sistemaantiguedad;
    @Column(name = "TIPOCONTRATO")
    private String tipocontrato;
    @OneToMany(mappedBy = "idrellab")
    private List<Rhrelacionlaboralcuentatb> rhrelacionlaboralcuentatbList;
    @JoinColumn(name = "IDTIPORELLAB", referencedColumnName = "IDTIPORELACIONLABORAL")
    @ManyToOne
    private Rhtiporelacionlaboraltb idtiporellab;
    @JoinColumn(name = "IDRELACIONLABORALPOSICION", referencedColumnName = "IDRELACIONLABORALPOSICION")
    @ManyToOne
    private Rhrelacionlaboralposiciontb idrelacionlaboralposicion;
    @JoinColumn(name = "IDREGISTROPATRONAL", referencedColumnName = "IDREGISTROPATRONAL")
    @ManyToOne
    private Admregistropatronaltb idregistropatronal;
    @JoinColumn(name = "IDPOSICIONSUELDO", referencedColumnName = "IDPOSICIONSUELDO")
    @ManyToOne
    private Rhposicionsueldotb idposicionsueldo;
    @JoinColumn(name = "IDGRUPOPAGO", referencedColumnName = "IDGRUPOPAGO")
    @ManyToOne
    private Nomgrupopagotb idgrupopago;
    @JoinColumn(name = "IDEMPLEADO", referencedColumnName = "IDEMPLEADO")
    @ManyToOne
    private Rhempleadotb idempleado;
    @JoinColumn(name = "IDCOMPANIA", referencedColumnName = "IDCOMPANIA")
    @ManyToOne
    private Admcompaniatb idcompania;
    @OneToMany(mappedBy = "idrellab")
    private List<Rhposicionsueldohtb> rhposicionsueldohtbList;

    public Rhrelacionlaboraltb() {
    }

    public Rhrelacionlaboraltb(Integer idrellab) {
        this.idrellab = idrellab;
    }

    public Integer getIdrellab() {
        return idrellab;
    }

    public void setIdrellab(Integer idrellab) {
        this.idrellab = idrellab;
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

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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

    public Date getFechaeventocontrato() {
        return fechaeventocontrato;
    }

    public void setFechaeventocontrato(Date fechaeventocontrato) {
        this.fechaeventocontrato = fechaeventocontrato;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public Date getFechainiciocontrato() {
        return fechainiciocontrato;
    }

    public void setFechainiciocontrato(Date fechainiciocontrato) {
        this.fechainiciocontrato = fechainiciocontrato;
    }

    public String getNumeroempleado() {
        return numeroempleado;
    }

    public void setNumeroempleado(String numeroempleado) {
        this.numeroempleado = numeroempleado;
    }

    public String getSistemaantiguedad() {
        return sistemaantiguedad;
    }

    public void setSistemaantiguedad(String sistemaantiguedad) {
        this.sistemaantiguedad = sistemaantiguedad;
    }

    public String getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }

    @XmlTransient
    public List<Rhrelacionlaboralcuentatb> getRhrelacionlaboralcuentatbList() {
        return rhrelacionlaboralcuentatbList;
    }

    public void setRhrelacionlaboralcuentatbList(List<Rhrelacionlaboralcuentatb> rhrelacionlaboralcuentatbList) {
        this.rhrelacionlaboralcuentatbList = rhrelacionlaboralcuentatbList;
    }

    public Rhtiporelacionlaboraltb getIdtiporellab() {
        return idtiporellab;
    }

    public void setIdtiporellab(Rhtiporelacionlaboraltb idtiporellab) {
        this.idtiporellab = idtiporellab;
    }

    public Rhrelacionlaboralposiciontb getIdrelacionlaboralposicion() {
        return idrelacionlaboralposicion;
    }

    public void setIdrelacionlaboralposicion(Rhrelacionlaboralposiciontb idrelacionlaboralposicion) {
        this.idrelacionlaboralposicion = idrelacionlaboralposicion;
    }

    public Admregistropatronaltb getIdregistropatronal() {
        return idregistropatronal;
    }

    public void setIdregistropatronal(Admregistropatronaltb idregistropatronal) {
        this.idregistropatronal = idregistropatronal;
    }

    public Rhposicionsueldotb getIdposicionsueldo() {
        return idposicionsueldo;
    }

    public void setIdposicionsueldo(Rhposicionsueldotb idposicionsueldo) {
        this.idposicionsueldo = idposicionsueldo;
    }

    public Nomgrupopagotb getIdgrupopago() {
        return idgrupopago;
    }

    public void setIdgrupopago(Nomgrupopagotb idgrupopago) {
        this.idgrupopago = idgrupopago;
    }

    public Rhempleadotb getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Rhempleadotb idempleado) {
        this.idempleado = idempleado;
    }

    public Admcompaniatb getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(Admcompaniatb idcompania) {
        this.idcompania = idcompania;
    }

    @XmlTransient
    public List<Rhposicionsueldohtb> getRhposicionsueldohtbList() {
        return rhposicionsueldohtbList;
    }

    public void setRhposicionsueldohtbList(List<Rhposicionsueldohtb> rhposicionsueldohtbList) {
        this.rhposicionsueldohtbList = rhposicionsueldohtbList;
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
        if (!(object instanceof Rhrelacionlaboraltb)) {
            return false;
        }
        Rhrelacionlaboraltb other = (Rhrelacionlaboraltb) object;
        if ((this.idrellab == null && other.idrellab != null) || (this.idrellab != null && !this.idrellab.equals(other.idrellab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboraltb[ idrellab=" + idrellab + " ]";
    }
    
}
