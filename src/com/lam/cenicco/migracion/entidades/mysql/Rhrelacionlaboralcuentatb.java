/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.entidades.mysql;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JoséAntonio
 */
@Entity
@Table(name = "rhrelacionlaboralcuentatb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rhrelacionlaboralcuentatb.findAll", query = "SELECT r FROM Rhrelacionlaboralcuentatb r"),
    @NamedQuery(name = "Rhrelacionlaboralcuentatb.findByIdcuenta", query = "SELECT r FROM Rhrelacionlaboralcuentatb r WHERE r.idcuenta = :idcuenta"),
    @NamedQuery(name = "Rhrelacionlaboralcuentatb.findByClabe", query = "SELECT r FROM Rhrelacionlaboralcuentatb r WHERE r.clabe = :clabe"),
    @NamedQuery(name = "Rhrelacionlaboralcuentatb.findByCuenta", query = "SELECT r FROM Rhrelacionlaboralcuentatb r WHERE r.cuenta = :cuenta"),
    @NamedQuery(name = "Rhrelacionlaboralcuentatb.findByEstatus", query = "SELECT r FROM Rhrelacionlaboralcuentatb r WHERE r.estatus = :estatus"),
    @NamedQuery(name = "Rhrelacionlaboralcuentatb.findByPorcentaje", query = "SELECT r FROM Rhrelacionlaboralcuentatb r WHERE r.porcentaje = :porcentaje"),
    @NamedQuery(name = "Rhrelacionlaboralcuentatb.findByProrrateo", query = "SELECT r FROM Rhrelacionlaboralcuentatb r WHERE r.prorrateo = :prorrateo"),
    @NamedQuery(name = "Rhrelacionlaboralcuentatb.findByTipocuenta", query = "SELECT r FROM Rhrelacionlaboralcuentatb r WHERE r.tipocuenta = :tipocuenta")})
public class Rhrelacionlaboralcuentatb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCUENTA")
    private Integer idcuenta;
    @Column(name = "CLABE")
    private String clabe;
    @Column(name = "CUENTA")
    private String cuenta;
    @Column(name = "ESTATUS")
    private String estatus;
    @Column(name = "PORCENTAJE")
    private Integer porcentaje;
    @Column(name = "PRORRATEO")
    private Integer prorrateo;
    @Column(name = "TIPOCUENTA")
    private String tipocuenta;
    @JoinColumn(name = "IDRELLAB", referencedColumnName = "IDRELLAB")
    @ManyToOne
    private Rhrelacionlaboraltb idrellab;
    @JoinColumn(name = "IDBANCO", referencedColumnName = "IDBANCO")
    @ManyToOne
    private Catbancotb idbanco;

    public Rhrelacionlaboralcuentatb() {
    }

    public Rhrelacionlaboralcuentatb(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public Integer getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Integer idcuenta) {
        this.idcuenta = idcuenta;
    }

    public String getClabe() {
        return clabe;
    }

    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Integer getProrrateo() {
        return prorrateo;
    }

    public void setProrrateo(Integer prorrateo) {
        this.prorrateo = prorrateo;
    }

    public String getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(String tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public Rhrelacionlaboraltb getIdrellab() {
        return idrellab;
    }

    public void setIdrellab(Rhrelacionlaboraltb idrellab) {
        this.idrellab = idrellab;
    }

    public Catbancotb getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(Catbancotb idbanco) {
        this.idbanco = idbanco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcuenta != null ? idcuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rhrelacionlaboralcuentatb)) {
            return false;
        }
        Rhrelacionlaboralcuentatb other = (Rhrelacionlaboralcuentatb) object;
        if ((this.idcuenta == null && other.idcuenta != null) || (this.idcuenta != null && !this.idcuenta.equals(other.idcuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboralcuentatb[ idcuenta=" + idcuenta + " ]";
    }
    
}
