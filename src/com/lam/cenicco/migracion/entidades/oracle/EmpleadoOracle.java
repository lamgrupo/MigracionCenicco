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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "RHEMPLEADOSTB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpleadoOracle.findAll", query = "SELECT e FROM EmpleadoOracle e"),
    @NamedQuery(name = "EmpleadoOracle.findByIdempleado", query = "SELECT e FROM EmpleadoOracle e WHERE e.idempleado = :idempleado"),
    @NamedQuery(name = "EmpleadoOracle.findByExpediente", query = "SELECT e FROM EmpleadoOracle e WHERE e.expediente = :expediente"),
    @NamedQuery(name = "EmpleadoOracle.findByApellidopaterno", query = "SELECT e FROM EmpleadoOracle e WHERE e.apellidopaterno = :apellidopaterno"),
    @NamedQuery(name = "EmpleadoOracle.findByApellidomaterno", query = "SELECT e FROM EmpleadoOracle e WHERE e.apellidomaterno = :apellidomaterno"),
    @NamedQuery(name = "EmpleadoOracle.findByNombres", query = "SELECT e FROM EmpleadoOracle e WHERE e.nombres = :nombres"),
    @NamedQuery(name = "EmpleadoOracle.findByIniciales", query = "SELECT e FROM EmpleadoOracle e WHERE e.iniciales = :iniciales"),
    @NamedQuery(name = "EmpleadoOracle.findByNombre", query = "SELECT e FROM EmpleadoOracle e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "EmpleadoOracle.findBySexo", query = "SELECT e FROM EmpleadoOracle e WHERE e.sexo = :sexo"),
    @NamedQuery(name = "EmpleadoOracle.findByEstadocivil", query = "SELECT e FROM EmpleadoOracle e WHERE e.estadocivil = :estadocivil"),
    @NamedQuery(name = "EmpleadoOracle.findByFechanacimiento", query = "SELECT e FROM EmpleadoOracle e WHERE e.fechanacimiento = :fechanacimiento"),
    @NamedQuery(name = "EmpleadoOracle.findByLugarnacimiento", query = "SELECT e FROM EmpleadoOracle e WHERE e.lugarnacimiento = :lugarnacimiento"),
    @NamedQuery(name = "EmpleadoOracle.findByRfc", query = "SELECT e FROM EmpleadoOracle e WHERE e.rfc = :rfc"),
    @NamedQuery(name = "EmpleadoOracle.findByCurp", query = "SELECT e FROM EmpleadoOracle e WHERE e.curp = :curp"),
    @NamedQuery(name = "EmpleadoOracle.findByAfiliacion", query = "SELECT e FROM EmpleadoOracle e WHERE e.afiliacion = :afiliacion"),
    @NamedQuery(name = "EmpleadoOracle.findByPathfoto", query = "SELECT e FROM EmpleadoOracle e WHERE e.pathfoto = :pathfoto"),
    @NamedQuery(name = "EmpleadoOracle.findByPathfirma", query = "SELECT e FROM EmpleadoOracle e WHERE e.pathfirma = :pathfirma"),
    @NamedQuery(name = "EmpleadoOracle.findByEstatus", query = "SELECT e FROM EmpleadoOracle e WHERE e.estatus = :estatus")})
public class EmpleadoOracle implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idempleado")
    private List<RelacionLaboralOracle> relacionLaboralOracleList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDEMPLEADO")
    private Long idempleado;
    @Basic(optional = false)
    @Column(name = "EXPEDIENTE")
    private String expediente;
    @Column(name = "APELLIDOPATERNO")
    private String apellidopaterno;
    @Column(name = "APELLIDOMATERNO")
    private String apellidomaterno;
    @Basic(optional = false)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "INICIALES")
    private String iniciales;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "SEXO")
    private short sexo;
    @Basic(optional = false)
    @Column(name = "ESTADOCIVIL")
    private String estadocivil;
    @Basic(optional = false)
    @Column(name = "FECHANACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechanacimiento;
    @Column(name = "LUGARNACIMIENTO")
    private String lugarnacimiento;
    @Basic(optional = false)
    @Column(name = "RFC")
    private String rfc;
    @Column(name = "CURP")
    private String curp;
    @Column(name = "AFILIACION")
    private String afiliacion;
    @Column(name = "PATHFOTO")
    private String pathfoto;
    @Column(name = "PATHFIRMA")
    private String pathfirma;
    @Basic(optional = false)
    @Column(name = "ESTATUS")
    private String estatus;
    @JoinColumns({
        @JoinColumn(name = "IDESTADONACIMIENTO", referencedColumnName = "IDESTADO"),
        @JoinColumn(name = "IDPAISNACIMIENTO", referencedColumnName = "IDPAIS")})
    @ManyToOne(optional = false)
    private EstadoOracle estadoOracle;

    public EmpleadoOracle() {
    }

    public EmpleadoOracle(Long idempleado) {
        this.idempleado = idempleado;
    }

    public EmpleadoOracle(Long idempleado, String expediente, String nombres, String iniciales, String nombre, short sexo, String estadocivil, Date fechanacimiento, String rfc, String estatus) {
        this.idempleado = idempleado;
        this.expediente = expediente;
        this.nombres = nombres;
        this.iniciales = iniciales;
        this.nombre = nombre;
        this.sexo = sexo;
        this.estadocivil = estadocivil;
        this.fechanacimiento = fechanacimiento;
        this.rfc = rfc;
        this.estatus = estatus;
    }

    public Long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Long idempleado) {
        this.idempleado = idempleado;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getApellidopaterno() {
        return apellidopaterno;
    }

    public void setApellidopaterno(String apellidopaterno) {
        this.apellidopaterno = apellidopaterno;
    }

    public String getApellidomaterno() {
        return apellidomaterno;
    }

    public void setApellidomaterno(String apellidomaterno) {
        this.apellidomaterno = apellidomaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getIniciales() {
        return iniciales;
    }

    public void setIniciales(String iniciales) {
        this.iniciales = iniciales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getSexo() {
        return sexo;
    }

    public void setSexo(short sexo) {
        this.sexo = sexo;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
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

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getAfiliacion() {
        return afiliacion;
    }

    public void setAfiliacion(String afiliacion) {
        this.afiliacion = afiliacion;
    }

    public String getPathfoto() {
        return pathfoto;
    }

    public void setPathfoto(String pathfoto) {
        this.pathfoto = pathfoto;
    }

    public String getPathfirma() {
        return pathfirma;
    }

    public void setPathfirma(String pathfirma) {
        this.pathfirma = pathfirma;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public EstadoOracle getEstadoOracle() {
        return estadoOracle;
    }

    public void setEstadoOracle(EstadoOracle estadoOracle) {
        this.estadoOracle = estadoOracle;
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
        if (!(object instanceof EmpleadoOracle)) {
            return false;
        }
        EmpleadoOracle other = (EmpleadoOracle) object;
        if ((this.idempleado == null && other.idempleado != null) || (this.idempleado != null && !this.idempleado.equals(other.idempleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.oracle.EmpleadoOracle[ idempleado=" + idempleado + " ]";
    }

    @XmlTransient
    public List<RelacionLaboralOracle> getRelacionLaboralOracleList() {
        return relacionLaboralOracleList;
    }

    public void setRelacionLaboralOracleList(List<RelacionLaboralOracle> relacionLaboralOracleList) {
        this.relacionLaboralOracleList = relacionLaboralOracleList;
    }
    
}
