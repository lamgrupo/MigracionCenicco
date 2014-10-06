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
@Table(name = "rhempleadotb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rhempleadotb.findAll", query = "SELECT r FROM Rhempleadotb r"),
    @NamedQuery(name = "Rhempleadotb.findByIdempleado", query = "SELECT r FROM Rhempleadotb r WHERE r.idempleado = :idempleado"),
    @NamedQuery(name = "Rhempleadotb.findByAfiliacion", query = "SELECT r FROM Rhempleadotb r WHERE r.afiliacion = :afiliacion"),
    @NamedQuery(name = "Rhempleadotb.findByApellidomaterno", query = "SELECT r FROM Rhempleadotb r WHERE r.apellidomaterno = :apellidomaterno"),
    @NamedQuery(name = "Rhempleadotb.findByApellidopaterno", query = "SELECT r FROM Rhempleadotb r WHERE r.apellidopaterno = :apellidopaterno"),
    @NamedQuery(name = "Rhempleadotb.findByCurp", query = "SELECT r FROM Rhempleadotb r WHERE r.curp = :curp"),
    @NamedQuery(name = "Rhempleadotb.findByEstadocivil", query = "SELECT r FROM Rhempleadotb r WHERE r.estadocivil = :estadocivil"),
    @NamedQuery(name = "Rhempleadotb.findByEstatus", query = "SELECT r FROM Rhempleadotb r WHERE r.estatus = :estatus"),
    @NamedQuery(name = "Rhempleadotb.findByExpediente", query = "SELECT r FROM Rhempleadotb r WHERE r.expediente = :expediente"),
    @NamedQuery(name = "Rhempleadotb.findByFechanacimiento", query = "SELECT r FROM Rhempleadotb r WHERE r.fechanacimiento = :fechanacimiento"),
    @NamedQuery(name = "Rhempleadotb.findByLugarnacimiento", query = "SELECT r FROM Rhempleadotb r WHERE r.lugarnacimiento = :lugarnacimiento"),
    @NamedQuery(name = "Rhempleadotb.findByNombre", query = "SELECT r FROM Rhempleadotb r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Rhempleadotb.findByPathfirma", query = "SELECT r FROM Rhempleadotb r WHERE r.pathfirma = :pathfirma"),
    @NamedQuery(name = "Rhempleadotb.findByPathfoto", query = "SELECT r FROM Rhempleadotb r WHERE r.pathfoto = :pathfoto"),
    @NamedQuery(name = "Rhempleadotb.findByRfc", query = "SELECT r FROM Rhempleadotb r WHERE r.rfc = :rfc"),
    @NamedQuery(name = "Rhempleadotb.findBySexo", query = "SELECT r FROM Rhempleadotb r WHERE r.sexo = :sexo")})
public class Rhempleadotb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEMPLEADO")
    private Integer idempleado;
    @Column(name = "AFILIACION")
    private String afiliacion;
    @Column(name = "APELLIDOMATERNO")
    private String apellidomaterno;
    @Column(name = "APELLIDOPATERNO")
    private String apellidopaterno;
    @Column(name = "CURP")
    private String curp;
    @Column(name = "ESTADOCIVIL")
    private String estadocivil;
    @Column(name = "ESTATUS")
    private String estatus;
    @Column(name = "EXPEDIENTE")
    private String expediente;
    @Column(name = "FECHANACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Column(name = "LUGARNACIMIENTO")
    private String lugarnacimiento;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "PATHFIRMA")
    private String pathfirma;
    @Column(name = "PATHFOTO")
    private String pathfoto;
    @Column(name = "RFC")
    private String rfc;
    @Column(name = "SEXO")
    private String sexo;
    @OneToMany(mappedBy = "idempleado")
    private List<Rhempleadocontactotb> rhempleadocontactotbList;
    @OneToMany(mappedBy = "idempleado")
    private List<Rhrelacionlaboraltb> rhrelacionlaboraltbList;
    @JoinColumn(name = "IDESTADONACIMIENTO", referencedColumnName = "IDESTADO")
    @ManyToOne
    private Catestadotb idestadonacimiento;
    @OneToMany(mappedBy = "idempleado")
    private List<Rhempleadodomiciliotb> rhempleadodomiciliotbList;

    public Rhempleadotb() {
    }

    public Rhempleadotb(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public Integer getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Integer idempleado) {
        this.idempleado = idempleado;
    }

    public String getAfiliacion() {
        return afiliacion;
    }

    public void setAfiliacion(String afiliacion) {
        this.afiliacion = afiliacion;
    }

    public String getApellidomaterno() {
        return apellidomaterno;
    }

    public void setApellidomaterno(String apellidomaterno) {
        this.apellidomaterno = apellidomaterno;
    }

    public String getApellidopaterno() {
        return apellidopaterno;
    }

    public void setApellidopaterno(String apellidopaterno) {
        this.apellidopaterno = apellidopaterno;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getLugarnacimiento() {
        return lugarnacimiento;
    }

    public void setLugarnacimiento(String lugarnacimiento) {
        this.lugarnacimiento = lugarnacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPathfirma() {
        return pathfirma;
    }

    public void setPathfirma(String pathfirma) {
        this.pathfirma = pathfirma;
    }

    public String getPathfoto() {
        return pathfoto;
    }

    public void setPathfoto(String pathfoto) {
        this.pathfoto = pathfoto;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @XmlTransient
    public List<Rhempleadocontactotb> getRhempleadocontactotbList() {
        return rhempleadocontactotbList;
    }

    public void setRhempleadocontactotbList(List<Rhempleadocontactotb> rhempleadocontactotbList) {
        this.rhempleadocontactotbList = rhempleadocontactotbList;
    }

    @XmlTransient
    public List<Rhrelacionlaboraltb> getRhrelacionlaboraltbList() {
        return rhrelacionlaboraltbList;
    }

    public void setRhrelacionlaboraltbList(List<Rhrelacionlaboraltb> rhrelacionlaboraltbList) {
        this.rhrelacionlaboraltbList = rhrelacionlaboraltbList;
    }

    public Catestadotb getIdestadonacimiento() {
        return idestadonacimiento;
    }

    public void setIdestadonacimiento(Catestadotb idestadonacimiento) {
        this.idestadonacimiento = idestadonacimiento;
    }

    @XmlTransient
    public List<Rhempleadodomiciliotb> getRhempleadodomiciliotbList() {
        return rhempleadodomiciliotbList;
    }

    public void setRhempleadodomiciliotbList(List<Rhempleadodomiciliotb> rhempleadodomiciliotbList) {
        this.rhempleadodomiciliotbList = rhempleadodomiciliotbList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idempleado != null ? idempleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rhempleadotb)) {
            return false;
        }
        Rhempleadotb other = (Rhempleadotb) object;
        if ((this.idempleado == null && other.idempleado != null) || (this.idempleado != null && !this.idempleado.equals(other.idempleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Rhempleadotb[ idempleado=" + idempleado + " ]";
    }
    
}
