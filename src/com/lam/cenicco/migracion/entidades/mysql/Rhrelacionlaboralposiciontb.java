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
@Table(name = "rhrelacionlaboralposiciontb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rhrelacionlaboralposiciontb.findAll", query = "SELECT r FROM Rhrelacionlaboralposiciontb r"),
    @NamedQuery(name = "Rhrelacionlaboralposiciontb.findByIdrelacionlaboralposicion", query = "SELECT r FROM Rhrelacionlaboralposiciontb r WHERE r.idrelacionlaboralposicion = :idrelacionlaboralposicion"),
    @NamedQuery(name = "Rhrelacionlaboralposiciontb.findByEstatus", query = "SELECT r FROM Rhrelacionlaboralposiciontb r WHERE r.estatus = :estatus"),
    @NamedQuery(name = "Rhrelacionlaboralposiciontb.findByFechafin", query = "SELECT r FROM Rhrelacionlaboralposiciontb r WHERE r.fechafin = :fechafin"),
    @NamedQuery(name = "Rhrelacionlaboralposiciontb.findByFechainicio", query = "SELECT r FROM Rhrelacionlaboralposiciontb r WHERE r.fechainicio = :fechainicio"),
    @NamedQuery(name = "Rhrelacionlaboralposiciontb.findByFormapago", query = "SELECT r FROM Rhrelacionlaboralposiciontb r WHERE r.formapago = :formapago"),
    @NamedQuery(name = "Rhrelacionlaboralposiciontb.findByJornadalaboral", query = "SELECT r FROM Rhrelacionlaboralposiciontb r WHERE r.jornadalaboral = :jornadalaboral"),
    @NamedQuery(name = "Rhrelacionlaboralposiciontb.findByNivel", query = "SELECT r FROM Rhrelacionlaboralposiciontb r WHERE r.nivel = :nivel"),
    @NamedQuery(name = "Rhrelacionlaboralposiciontb.findBySindicalizado", query = "SELECT r FROM Rhrelacionlaboralposiciontb r WHERE r.sindicalizado = :sindicalizado"),
    @NamedQuery(name = "Rhrelacionlaboralposiciontb.findBySistemahorario", query = "SELECT r FROM Rhrelacionlaboralposiciontb r WHERE r.sistemahorario = :sistemahorario")})
public class Rhrelacionlaboralposiciontb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDRELACIONLABORALPOSICION")
    private Integer idrelacionlaboralposicion;
    @Column(name = "ESTATUS")
    private String estatus;
    @Column(name = "FECHAFIN")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Column(name = "FECHAINICIO")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "FORMAPAGO")
    private String formapago;
    @Column(name = "JORNADALABORAL")
    private String jornadalaboral;
    @Column(name = "NIVEL")
    private String nivel;
    @Column(name = "SINDICALIZADO")
    private String sindicalizado;
    @Column(name = "SISTEMAHORARIO")
    private String sistemahorario;
    @OneToMany(mappedBy = "idrelacionlaboralposicion")
    private List<Rhrelacionlaboraltb> rhrelacionlaboraltbList;
    @JoinColumn(name = "IDTURNO", referencedColumnName = "IDTURNO")
    @ManyToOne
    private Taturnotb idturno;
    @JoinColumn(name = "IDTIPOSUELDO", referencedColumnName = "IDTIPOSUELDO")
    @ManyToOne
    private Rhtiposueldotb idtiposueldo;
    @JoinColumn(name = "IDPUESTO", referencedColumnName = "IDPUESTO")
    @ManyToOne
    private Admpuestotb idpuesto;
    @JoinColumn(name = "IDDEPARTAMENTO", referencedColumnName = "IDDEPARTAMENTO")
    @ManyToOne
    private Admdepartamentotb iddepartamento;

    public Rhrelacionlaboralposiciontb() {
    }

    public Rhrelacionlaboralposiciontb(Integer idrelacionlaboralposicion) {
        this.idrelacionlaboralposicion = idrelacionlaboralposicion;
    }

    public Integer getIdrelacionlaboralposicion() {
        return idrelacionlaboralposicion;
    }

    public void setIdrelacionlaboralposicion(Integer idrelacionlaboralposicion) {
        this.idrelacionlaboralposicion = idrelacionlaboralposicion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getFormapago() {
        return formapago;
    }

    public void setFormapago(String formapago) {
        this.formapago = formapago;
    }

    public String getJornadalaboral() {
        return jornadalaboral;
    }

    public void setJornadalaboral(String jornadalaboral) {
        this.jornadalaboral = jornadalaboral;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getSindicalizado() {
        return sindicalizado;
    }

    public void setSindicalizado(String sindicalizado) {
        this.sindicalizado = sindicalizado;
    }

    public String getSistemahorario() {
        return sistemahorario;
    }

    public void setSistemahorario(String sistemahorario) {
        this.sistemahorario = sistemahorario;
    }

    @XmlTransient
    public List<Rhrelacionlaboraltb> getRhrelacionlaboraltbList() {
        return rhrelacionlaboraltbList;
    }

    public void setRhrelacionlaboraltbList(List<Rhrelacionlaboraltb> rhrelacionlaboraltbList) {
        this.rhrelacionlaboraltbList = rhrelacionlaboraltbList;
    }

    public Taturnotb getIdturno() {
        return idturno;
    }

    public void setIdturno(Taturnotb idturno) {
        this.idturno = idturno;
    }

    public Rhtiposueldotb getIdtiposueldo() {
        return idtiposueldo;
    }

    public void setIdtiposueldo(Rhtiposueldotb idtiposueldo) {
        this.idtiposueldo = idtiposueldo;
    }

    public Admpuestotb getIdpuesto() {
        return idpuesto;
    }

    public void setIdpuesto(Admpuestotb idpuesto) {
        this.idpuesto = idpuesto;
    }

    public Admdepartamentotb getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(Admdepartamentotb iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrelacionlaboralposicion != null ? idrelacionlaboralposicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rhrelacionlaboralposiciontb)) {
            return false;
        }
        Rhrelacionlaboralposiciontb other = (Rhrelacionlaboralposiciontb) object;
        if ((this.idrelacionlaboralposicion == null && other.idrelacionlaboralposicion != null) || (this.idrelacionlaboralposicion != null && !this.idrelacionlaboralposicion.equals(other.idrelacionlaboralposicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboralposiciontb[ idrelacionlaboralposicion=" + idrelacionlaboralposicion + " ]";
    }
    
}
