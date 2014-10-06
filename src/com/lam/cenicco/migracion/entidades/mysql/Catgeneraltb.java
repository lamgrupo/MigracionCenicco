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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JoséAntonio
 */
@Entity
@Table(name = "catgeneraltb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catgeneraltb.findAll", query = "SELECT c FROM Catgeneraltb c"),
    @NamedQuery(name = "Catgeneraltb.findByIdcatgeneral", query = "SELECT c FROM Catgeneraltb c WHERE c.idcatgeneral = :idcatgeneral"),
    @NamedQuery(name = "Catgeneraltb.findByColumna", query = "SELECT c FROM Catgeneraltb c WHERE c.columna = :columna"),
    @NamedQuery(name = "Catgeneraltb.findByNombre", query = "SELECT c FROM Catgeneraltb c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Catgeneraltb.findByTabla", query = "SELECT c FROM Catgeneraltb c WHERE c.tabla = :tabla"),
    @NamedQuery(name = "Catgeneraltb.findByValor", query = "SELECT c FROM Catgeneraltb c WHERE c.valor = :valor")})
public class Catgeneraltb implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCATGENERAL")
    private Integer idcatgeneral;
    @Column(name = "COLUMNA")
    private String columna;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "TABLA")
    private String tabla;
    @Column(name = "VALOR")
    private String valor;

    public Catgeneraltb() {
    }

    public Catgeneraltb(Integer idcatgeneral) {
        this.idcatgeneral = idcatgeneral;
    }

    public Integer getIdcatgeneral() {
        return idcatgeneral;
    }

    public void setIdcatgeneral(Integer idcatgeneral) {
        this.idcatgeneral = idcatgeneral;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcatgeneral != null ? idcatgeneral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catgeneraltb)) {
            return false;
        }
        Catgeneraltb other = (Catgeneraltb) object;
        if ((this.idcatgeneral == null && other.idcatgeneral != null) || (this.idcatgeneral != null && !this.idcatgeneral.equals(other.idcatgeneral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lam.cenicco.migracion.entidades.mysql.Catgeneraltb[ idcatgeneral=" + idcatgeneral + " ]";
    }
    
}
